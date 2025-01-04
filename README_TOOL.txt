CREATE OR REPLACE FUNCTION enrich_xml_dynamic(xml_content STRING, vault_id STRING)
RETURNS STRING
LANGUAGE PYTHON
RUNTIME_VERSION = '3.8'
HANDLER = 'enrich_xml_dynamic'
AS $$
import snowflake.connector
import xml.etree.ElementTree as ET

# Snowflake connection credentials (Ensure these are securely handled in a real implementation)
connection_params = {
    'user': 'YOUR_USER',
    'password': 'YOUR_PASSWORD',
    'account': 'YOUR_ACCOUNT',
    'warehouse': 'YOUR_WAREHOUSE',
    'database': 'YOUR_DATABASE',
    'schema': 'YOUR_SCHEMA'
}

def enrich_xml_dynamic(xml_content: str, vault_id: str) -> str:
    try:
        # Step 1: Establish connection to Snowflake
        conn = snowflake.connector.connect(**connection_params)
        cursor = conn.cursor()

        # Step 2: Fetch data from PII_DATA table based on vault_id
        query = """
        SELECT buyer_identification_code, buyer_first_name, buyer_surname, buyer_date_of_birth, 
               buyer_decision_maker_code, buyer_decision_maker_first_name, buyer_decision_maker_surname, 
               buyer_decision_maker_date_of_birth, seller_identification_code, seller_first_name, 
               seller_surname, seller_date_of_birth, seller_decision_maker_code, seller_decision_maker_first_name, 
               seller_decision_maker_surname, seller_decision_maker_date_of_birth, investment_decision_within_firm, 
               country_of_branch_making_investment_decision, execution_within_firm, country_of_branch_supervising_execution
        FROM PII_DATA
        WHERE pii_vault_id = %s
        """
        cursor.execute(query, (vault_id,))
        result = cursor.fetchone()

        if result is None:
            raise Exception(f"No data found for Vault ID: {vault_id}")

        # Step 3: Map the result into a dictionary
        pii_data = {
            "BuyerFirstName": result[1],
            "BuyerSurname": result[2],
            "BuyerDateOfBirth": result[3],
            "BuyerDecisionMakerCode": result[4],
            "BuyerDecisionMakerFirstName": result[5],
            "BuyerDecisionMakerSurname": result[6],
            "BuyerDecisionMakerDateOfBirth": result[7],
            "SellerIdentificationCode": result[8],
            "SellerFirstName": result[9],
            "SellerSurname": result[10],
            "SellerDateOfBirth": result[11],
            "SellerDecisionMakerCode": result[12],
            "SellerDecisionMakerFirstName": result[13],
            "SellerDecisionMakerSurname": result[14],
            "SellerDecisionMakerDateOfBirth": result[15],
            "InvestmentDecisionWithinFirm": result[16],
            "CountryOfBranchMakingInvestmentDecision": result[17],
            "ExecutionWithinFirm": result[18],
            "CountryOfBranchSupervisingExecution": result[19]
        }

        # Step 4: Parse the XML content
        tree = ET.ElementTree(ET.fromstring(xml_content))
        root = tree.getroot()

        # Step 5: Enrich the missing fields in the XML
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

        # Step 6: Return the enriched XML as a string
        return ET.tostring(root, encoding="unicode")
    
    except Exception as e:
        return str(e)
    finally:
        cursor.close()
        conn.close()
$$;
