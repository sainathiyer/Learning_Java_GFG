WITH extracted_vault_id AS (
    SELECT extract_vault_id(transaction_data) AS vault_id
    FROM transaction_temp
)
SELECT enrich_xml_dynamic(transaction_data, OBJECT_CONSTRUCT(
    'BuyerFirstName', pii_data.buyer_first_name,
    'BuyerSurname', pii_data.buyer_surname,
    'BuyerDateOfBirth', pii_data.buyer_date_of_birth,
    'BuyerDecisionMakerCode', pii_data.buyer_decision_maker_code,
    'BuyerDecisionMakerFirstName', pii_data.buyer_decision_maker_first_name,
    'BuyerDecisionMakerSurname', pii_data.buyer_decision_maker_surname,
    'BuyerDecisionMakerDateOfBirth', pii_data.buyer_decision_maker_date_of_birth,
    'SellerIdentificationCode', pii_data.seller_identification_code,
    'SellerFirstName', pii_data.seller_first_name,
    'SellerSurname', pii_data.seller_surname,
    'SellerDateOfBirth', pii_data.seller_date_of_birth,
    'SellerDecisionMakerCode', pii_data.seller_decision_maker_code,
    'SellerDecisionMakerFirstName', pii_data.seller_decision_maker_first_name,
    'SellerDecisionMakerSurname', pii_data.seller_decision_maker_surname,
    'SellerDecisionMakerDateOfBirth', pii_data.seller_decision_maker_date_of_birth,
    'InvestmentDecisionWithinFirm', pii_data.investment_decision_within_firm,
    'CountryOfBranchMakingInvestmentDecision', pii_data.country_of_branch_making_investment_decision,
    'ExecutionWithinFirm', pii_data.execution_within_firm,
    'CountryOfBranchSupervisingExecution', pii_data.country_of_branch_supervising_execution
)) AS enriched_xml
FROM pii_data
JOIN extracted_vault_id
    ON pii_data.pii_vault_id = extracted_vault_id.vault_id;
