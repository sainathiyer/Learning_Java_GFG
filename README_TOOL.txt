If you need to apply a masking policy to many columns, you can automate the process using Snowflake's scripting or external tools.

DECLARE col_name STRING;
BEGIN
    FOR col_name IN (
        SELECT column_name
        FROM information_schema.columns
        WHERE table_name = 'PII_DATA'
          AND column_name IN ('buyer_first_name', 'buyer_surname', 'buyer_date_of_birth', 'seller_first_name', 'seller_surname')
    )
    DO
        EXECUTE IMMEDIATE 
            'ALTER TABLE PII_DATA MODIFY COLUMN ' || col_name || ' SET MASKING POLICY pii_masking_policy';
    END FOR;
END;
