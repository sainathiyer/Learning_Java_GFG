CREATE OR REPLACE FUNCTION extract_vault_id(xml_content STRING)
RETURNS STRING
LANGUAGE PYTHON
RUNTIME_VERSION = '3.8'
PACKAGES = ('lxml')
HANDLER = 'extract_vault_id'
AS $$
from lxml import etree

def extract_vault_id(xml_content):
    try:
        # Parse the XML content
        root = etree.fromstring(xml_content)
        # Extract VaultId using XPath
        vault_id = root.xpath('//VaultId')
        if vault_id:
            return vault_id[0].text
        else:
            return None
    except Exception as e:
        return str(e)
$$;


CREATE OR REPLACE FUNCTION find_matching_pii_row(vault_id STRING)
RETURNS OBJECT
LANGUAGE PYTHON
RUNTIME_VERSION = '3.8'
HANDLER = 'find_matching_pii_row'
AS $$
import snowflake.snowpark as snowpark

def find_matching_pii_row(vault_id):
    session = snowpark.Session.builder.configs("snowflake").create()
    try:
        # Query to find the matching row
        result = session.table("PII_DATA").filter(session.col("pii_vault_id") == vault_id).collect()
        if result:
            return result[0].as_dict()  # Return the first matching row as a dictionary
        else:
            return None
    except Exception as e:
        return str(e)
    finally:
        session.close()
$$;


WITH xml_data AS (
    SELECT $1 AS xml_content
    FROM TABLE(
        FILES(
            '@customer_pii_stage',
            PATTERN => '.*Transaction_MQ.xml'
        )
    )
)
SELECT extract_vault_id(xml_content)
FROM xml_data;



SELECT find_matching_pii_row('47dbcf05-ea8e-4c25-a5e4-acea1315f758');
