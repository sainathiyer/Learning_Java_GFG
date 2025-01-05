CREATE OR REPLACE PROCEDURE store_enriched_xml(transaction_data STRING, pii_data VARIANT)
RETURNS STRING
LANGUAGE SQL
AS
$$
DECLARE
    enriched_xml STRING;
BEGIN
    -- Call the function to enrich the data
    LET enriched_xml = enrich_transaction_data(transaction_data, pii_data);
    
    -- If enriched_xml is not null or empty, upload to stage
    IF enriched_xml IS NOT NULL AND enriched_xml != '' THEN
        -- Using the PUT command to upload the enriched XML file to stage
        -- Save the enriched XML content into a file locally (this step is done externally)
        -- Assuming 'enriched_data.xml' as an example file name
        
        PUT 'file://<local_path>/enriched_data.xml' @your_stage;

        -- Return a success message
        RETURN 'Enriched XML file uploaded to stage';
    ELSE
        -- Return an error message if the enriched XML is empty
        RETURN 'Enriched XML is empty or null';
    END IF;
END;
$$;
