INSERT INTO PII_DATA (
    pii_vault_id,
    buyer_identification_code,
    buyer_first_name,
    buyer_surname,
    buyer_date_of_birth
)
SELECT
    UUID_STRING() AS pii_vault_id,
    parse_xml_tag(raw_data, 'BuyerIdentificationCode') AS buyer_id,
    parse_xml_tag(raw_data, 'BuyerFirstName') AS buyer_first_name,
    parse_xml_tag(raw_data, 'BuyerSurname') AS buyer_surname,
    parse_xml_tag(raw_data, 'BuyerDateOfBirth')::DATE AS buyer_date_of_birth
FROM customer_pii_temp;
