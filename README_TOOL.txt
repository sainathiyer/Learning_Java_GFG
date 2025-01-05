CREATE OR REPLACE FUNCTION enrich_transaction_data(
    transaction_data STRING,
    pii_data VARIANT
)
RETURNS STRING
LANGUAGE PYTHON
RUNTIME_VERSION = '3.8'
PACKAGES = ('xmltodict')
HANDLER = 'handler'
AS $$
import xml.etree.ElementTree as ET
import json

def handler(transaction_data: str, pii_data: dict) -> str:
    try:
        # Parse the transaction_data XML
        root = ET.fromstring(transaction_data)
        
        # Convert PII data to a dictionary for easier processing
        pii_dict = json.loads(json.dumps(pii_data))

        # Example of enriching XML with PII data
        if 'BuyerFirstName' in pii_dict:
            buyer_element = root.find('.//BuyerFirstName')
            if buyer_element is not None:
                buyer_element.text = pii_dict['BuyerFirstName']

        if 'BuyerSurname' in pii_dict:
            surname_element = root.find('.//BuyerSurname')
            if surname_element is not None:
                surname_element.text = pii_dict['BuyerSurname']

        # Add other fields from pii_dict to enrich the XML as needed...

        # Convert the enriched XML back to a string
        enriched_xml = ET.tostring(root, encoding='unicode')

        return enriched_xml

    except Exception as e:
        return f"Error processing XML: {str(e)}"
$$;


SELECT 
    t.transaction_data,
    enrich_transaction_data(t.transaction_data, OBJECT_CONSTRUCT(
        'BuyerFirstName', p.buyer_first_name,
        'BuyerSurname', p.buyer_surname,
        'BuyerDateOfBirth', p.buyer_date_of_birth,
        'SellerFirstName', p.seller_first_name,
        'SellerSurname', p.seller_surname,
        'InvestmentDecisionWithinFirm', p.investment_decision_within_firm
        -- Add all necessary PII fields here
    )) AS enriched_xml
FROM transaction_temp t
JOIN PII_DATA p 
ON p.pii_vault_id = extract_vault_id(t.transaction_data);


CREATE OR REPLACE STAGE enriched_xml_stage;

-- Export enriched XML to the stage
COPY INTO @enriched_xml_stage/enriched_data.xml
FROM (
    SELECT enriched_xml 
    FROM (
        SELECT 
            t.transaction_data,
            enrich_transaction_data(t.transaction_data, OBJECT_CONSTRUCT(
                'BuyerFirstName', p.buyer_first_name,
                'BuyerSurname', p.buyer_surname,
                'BuyerDateOfBirth', p.buyer_date_of_birth,
                'SellerFirstName', p.seller_first_name,
                'SellerSurname', p.seller_surname,
                'InvestmentDecisionWithinFirm', p.investment_decision_within_firm
            )) AS enriched_xml
        FROM transaction_temp t
        JOIN PII_DATA p 
        ON p.pii_vault_id = extract_vault_id(t.transaction_data)
    )
)
FILE_FORMAT = (TYPE = 'CSV' FIELD_OPTIONALLY_ENCLOSED_BY = '"' ESCAPE_UNENCLOSED_FIELD = NONE);
