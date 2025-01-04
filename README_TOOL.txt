CREATE OR REPLACE FUNCTION enrich_xml_dynamic(xml_content STRING, pii_data VARIANT)
RETURNS STRING
LANGUAGE PYTHON
RUNTIME_VERSION = '3.8'
HANDLER = 'enrich_xml_dynamic'
AS $$
import xml.etree.ElementTree as ET

def enrich_xml_dynamic(xml_content: str, pii_data: dict) -> str:
    try:
        # Step 1: Parse the XML content
        tree = ET.ElementTree(ET.fromstring(xml_content))
        root = tree.getroot()

        # Step 2: Enrich the missing fields in the XML using pii_data
        pii_fields = [
            "BuyerFirstName", "BuyerSurname", "BuyerDateOfBirth", "BuyerDecisionMakerCode", 
            "BuyerDecisionMakerFirstName", "BuyerDecisionMakerSurname", "BuyerDecisionMakerDateOfBirth", 
            "SellerIdentificationCode", "SellerFirstName", "SellerSurname", "SellerDateOfBirth", 
            "SellerDecisionMakerCode", "SellerDecisionMakerFirstName", "SellerDecisionMakerSurname", 
            "SellerDecisionMakerDateOfBirth", "InvestmentDecisionWithinFirm", "CountryOfBranchMakingInvestmentDecision", 
            "ExecutionWithinFirm", "CountryOfBranchSupervisingExecution"
        ]

        for field in pii_fields:
            # Find the element and fill in if it is empty
            element = root.find(f".//{field}")
            if element is not None and element.text is None:
                element.text = pii_data.get(field, "")

        # Step 3: Return the enriched XML as a string
        return ET.tostring(root, encoding="unicode")
    
    except Exception as e:
        return str(e)
$$;



-------------------------
WITH extracted_vault_id AS (
    SELECT extract_vault_id(transaction_data) AS vault_id
    FROM transaction_temp
),
pii_data AS (
    SELECT *
    FROM PII_DATA
    WHERE pii_vault_id IN (SELECT vault_id FROM extracted_vault_id)
)
SELECT enrich_xml_dynamic(transaction_data, OBJECT_CONSTRUCT(
    'BuyerFirstName', buyer_first_name,
    'BuyerSurname', buyer_surname,
    'BuyerDateOfBirth', buyer_date_of_birth,
    'BuyerDecisionMakerCode', buyer_decision_maker_code,
    'BuyerDecisionMakerFirstName', buyer_decision_maker_first_name,
    'BuyerDecisionMakerSurname', buyer_decision_maker_surname,
    'BuyerDecisionMakerDateOfBirth', buyer_decision_maker_date_of_birth,
    'SellerIdentificationCode', seller_identification_code,
    'SellerFirstName', seller_first_name,
    'SellerSurname', seller_surname,
    'SellerDateOfBirth', seller_date_of_birth,
    'SellerDecisionMakerCode', seller_decision_maker_code,
    'SellerDecisionMakerFirstName', seller_decision_maker_first_name,
    'SellerDecisionMakerSurname', seller_decision_maker_surname,
    'SellerDecisionMakerDateOfBirth', seller_decision_maker_date_of_birth,
    'InvestmentDecisionWithinFirm', investment_decision_within_firm,
    'CountryOfBranchMakingInvestmentDecision', country_of_branch_making_investment_decision,
    'ExecutionWithinFirm', execution_within_firm,
    'CountryOfBranchSupervisingExecution', country_of_branch_supervising_execution
)) AS enriched_xml
FROM pii_data;
