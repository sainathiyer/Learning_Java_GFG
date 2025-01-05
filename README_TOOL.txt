CREATE OR REPLACE FUNCTION enrich_transaction_data(
    transaction_data STRING,
    pii_data VARIANT,
    stage_name STRING,  -- The name of the Snowflake stage
    file_name STRING    -- The desired file name in the stage
)
RETURNS STRING
LANGUAGE PYTHON
RUNTIME_VERSION = '3.8'
PACKAGES = ('xmltodict', 'snowflake-connector-python')
HANDLER = 'handler'
AS $$

import xml.etree.ElementTree as ET
import json
import snowflake.connector

# Snowflake connection parameters
def get_snowflake_connection():
    conn = snowflake.connector.connect(
        user='<your_snowflake_username>',
        password='<your_snowflake_password>',
        account='<your_account>',
        warehouse='<your_warehouse>',
        database='<your_database>',
        schema='<your_schema>'
    )
    return conn

# Function to write the XML content to a file on Snowflake stage
def write_to_stage(xml_data, stage_name, file_name):
    try:
        # Establish Snowflake connection
        conn = get_snowflake_connection()
        cursor = conn.cursor()

        # Write the XML content to the stage as a file
        file_path = f'@{stage_name}/{file_name}'
        cursor.execute(f"PUT FILE://{file_path} @<your_stage>")

        cursor.close()
        conn.close()
    except Exception as e:
        return f"Error writing to stage: {str(e)}"

def handler(transaction_data: str, pii_data: dict, stage_name: str, file_name: str) -> str:
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

        # Write the enriched XML content to the stage as a file
        write_to_stage(enriched_xml, stage_name, file_name)

        return f"XML successfully enriched and written to stage {stage_name} as {file_name}"

    except Exception as e:
        return f"Error processing XML: {str(e)}"

$$;
