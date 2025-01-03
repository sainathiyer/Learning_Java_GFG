Step 1: Create the Dummy XML for PII Data

<?xml version="1.0" encoding="UTF-8"?>
<CustomerPIIData>
    <BuyerIdentificationCode>123456</BuyerIdentificationCode>
    <BuyerFirstName>John</BuyerFirstName>
    <BuyerSurname>Doe</BuyerSurname>
    <BuyerDateOfBirth>1990-01-01</BuyerDateOfBirth>
    <BuyerDecisionMakerCode>ABC123</BuyerDecisionMakerCode>
    <BuyerDecisionMakerFirstName>Jane</BuyerDecisionMakerFirstName>
    <BuyerDecisionMakerSurname>Smith</BuyerDecisionMakerSurname>
    <BuyerDecisionMakerDateOfBirth>1985-05-15</BuyerDecisionMakerDateOfBirth>
    <SellerIdentificationCode>654321</SellerIdentificationCode>
    <SellerFirstName>Mark</SellerFirstName>
    <SellerSurname>Johnson</SellerSurname>
    <SellerDateOfBirth>1982-02-20</SellerDateOfBirth>
    <SellerDecisionMakerCode>DEF456</SellerDecisionMakerCode>
    <SellerDecisionMakerFirstName>Emily</SellerDecisionMakerFirstName>
    <SellerDecisionMakerSurname>Williams</SellerDecisionMakerSurname>
    <SellerDecisionMakerDateOfBirth>1980-08-25</SellerDecisionMakerDateOfBirth>
    <InvestmentDecisionWithinFirm>Yes</InvestmentDecisionWithinFirm>
    <CountryOfBranchMakingInvestmentDecision>US</CountryOfBranchMakingInvestmentDecision>
    <ExecutionWithinFirm>Execution Decision</ExecutionWithinFirm>
    <CountryOfBranchSupervisingExecution>Canada</CountryOfBranchSupervisingExecution>
</CustomerPIIData>

Step 2: Snowflake Integration and Table Creation
Snowflake SQL to create the table:

CREATE TABLE pii_data (
    pii_vault_id STRING,
    buyer_identification_code STRING,
    buyer_first_name STRING,
    buyer_surname STRING,
    buyer_date_of_birth DATE,
    buyer_decision_maker_code STRING,
    buyer_decision_maker_first_name STRING,
    buyer_decision_maker_surname STRING,
    buyer_decision_maker_date_of_birth DATE,
    seller_identification_code STRING,
    seller_first_name STRING,
    seller_surname STRING,
    seller_date_of_birth DATE,
    seller_decision_maker_code STRING,
    seller_decision_maker_first_name STRING,
    seller_decision_maker_surname STRING,
    seller_decision_maker_date_of_birth DATE,
    investment_decision_within_firm STRING,
    country_of_branch_making_investment_decision STRING,
    execution_within_firm STRING,
    country_of_branch_supervising_execution STRING
);

Step 3: Masking and Encrypting PII Data

CREATE MASKING POLICY pii_masking_policy 
  AS (val STRING) 
  RETURNS STRING ->
    CASE
      WHEN CURRENT_ROLE() IN ('ADMIN_ROLE') THEN val
      ELSE '**********'
    END;

Then, apply this policy to the PII columns:

ALTER TABLE pii_data 
  MODIFY COLUMN buyer_identification_code SET MASKING POLICY pii_masking_policy,
  MODIFY COLUMN buyer_first_name SET MASKING POLICY pii_masking_policy,
  MODIFY COLUMN buyer_surname SET MASKING POLICY pii_masking_policy,
  -- Apply this to all PII-related columns.
  ;

------------------------------------
INSERT INTO PII_DATA (
    pii_vault_id,
    buyer_identification_code,
    buyer_first_name,
    buyer_surname,
    buyer_date_of_birth,
    buyer_decision_maker_code,
    buy_decision_maker_first_name,
    buy_decision_maker_surname,
    buy_decision_maker_date_of_birth,
    seller_identification_code,
    seller_first_name,
    seller_surname,
    seller_date_of_birth,
    seller_decision_maker_code,
    sell_decision_maker_first_name,
    sell_decision_maker_surname,
    sell_decision_maker_date_of_birth,
    investment_decision_within_firm,
    country_responsible_for_investment,
    execution_within_firm,
    country_supervising_execution
)
SELECT
    UUID_STRING() AS pii_vault_id,
    raw_data:"BuyerIdentificationCode"::STRING,
    raw_data:"BuyerFirstName"::STRING,
    raw_data:"BuyerSurname"::STRING,
    TO_DATE(raw_data:"BuyerDateOfBirth"::STRING, 'YYYY-MM-DD'),
    raw_data:"BuyerDecisionMakerCode"::STRING,
    raw_data:"BuyDecisionMakerFirstName"::STRING,
    raw_data:"BuyDecisionMakerSurname"::STRING,
    TO_DATE(raw_data:"BuyDecisionMakerDateOfBirth"::STRING, 'YYYY-MM-DD'),
    raw_data:"SellerIdentificationCode"::STRING,
    raw_data:"SellerFirstName"::STRING,
    raw_data:"SellerSurname"::STRING,
    TO_DATE(raw_data:"SellerDateOfBirth"::STRING, 'YYYY-MM-DD'),
    raw_data:"SellerDecisionMakerCode"::STRING,
    raw_data:"SellDecisionMakerFirstName"::STRING,
    raw_data:"SellDecisionMakerSurname"::STRING,
    TO_DATE(raw_data:"SellDecisionMakerDateOfBirth"::STRING, 'YYYY-MM-DD'),
    raw_data:"InvestmentDecisionWithinFirm"::STRING,
    raw_data:"CountryResponsibleForInvestment"::STRING,
    raw_data:"ExecutionWithinFirm"::STRING,
    raw_data:"CountrySupervisingExecution"::STRING
FROM customer_pii_temp;

