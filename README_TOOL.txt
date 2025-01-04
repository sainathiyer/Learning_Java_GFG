WITH extracted_vault_id AS (
    SELECT extract_vault_id(xml_content) AS vault_id
    FROM @customer_pii_stage
)
SELECT *
FROM PII_DATA
WHERE pii_vault_id IN (SELECT vault_id FROM extracted_vault_id);
