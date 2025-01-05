CREATE OR REPLACE PROCEDURE store_enriched_xml()
RETURNS STRING
LANGUAGE SQL
AS
$$
DECLARE
    enriched_xml STRING;
BEGIN
    -- Call the function to enrich the data
    LET enriched_xml = enrich_transaction_data(transaction_data, pii_data);
    
    -- Upload the enriched XML string to stage
    PUT 'file://path/to/local/file.xml' @your_stage;

    -- Return a success message
    RETURN 'Enriched XML file uploaded to stage';
END;
$$;
