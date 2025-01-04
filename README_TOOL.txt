SELECT enrich_xml_dynamic(t.transaction_data, p.pii_data_variant)
FROM transaction_temp t
JOIN PII_DATA p ON p.pii_vault_id = extract_vault_id(t.transaction_data);
