CREATE OR REPLACE FUNCTION enrich_transaction_data(
    transaction_data STRING,
    pii_data VARIANT
)
RETURNS STRING
LANGUAGE PYTHON
RUNTIME_VERSION = '3.8'
PACKAGES = ('xmltodict', 'boto3')
HANDLER = 'handler'
AS $$
import xml.etree.ElementTree as ET
import json
import boto3
from datetime import datetime

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
        enriched_xml = ET.tostring(root, encoding="utf-8", method="xml").decode("utf-8")

        # Initialize the boto3 client for S3
        s3 = boto3.client('s3')
        
        # Define the S3 bucket name and the file name
        bucket_name = "your-s3-bucket-name"
        file_name = f"enriched_data_{datetime.now().strftime('%Y%m%d%H%M%S')}.xml"
        
        # Upload the enriched XML to S3
        s3.put_object(
            Bucket=bucket_name,
            Key=file_name,
            Body=enriched_xml,
            ContentType="application/xml"
        )

        # Return the S3 file URL for reference
        return {"status": "success", "s3_file_url": f"s3://{bucket_name}/{file_name}"}

    except Exception as e:
        return {"status": "error", "message": str(e)}
$$;

--Step to Call this method for enriching the XML and storing to S3
WITH enriched_data AS (
    SELECT enrich_transaction_data(
        t.transaction_data, 
        OBJECT_CONSTRUCT(
            'BuyerFirstName', p.buyer_first_name,
            'BuyerSurname', p.buyer_surname,
            'BuyerDateOfBirth', p.buyer_date_of_birth,
            'SellerFirstName', p.seller_first_name,
            'SellerSurname', p.seller_surname,
            'InvestmentDecisionWithinFirm', p.investment_decision_within_firm
        ) AS enriched_xml
    )
    FROM transaction_temp t
    JOIN PII_DATA p
    ON p.pii_vault_id = extract_vault_id(t.transaction_data)
)

-- Extracting the S3 file URL from the returned JSON object
SELECT 
    enriched_xml:s3_file_url::STRING AS s3_file_url
FROM enriched_data;
