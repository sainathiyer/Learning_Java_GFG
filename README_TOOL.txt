CREATE OR REPLACE FUNCTION enrich_transaction_xml(xml_content STRING)
RETURNS STRING
LANGUAGE JAVA
RUNTIME_VERSION = '11'
IMPORTS = ('@your_stage_name/EnrichTransactionXML.jar')
HANDLER = 'com.example.EnrichTransactionXML.enrichXML';
