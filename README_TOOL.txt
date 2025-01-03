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

Step 4: Creating UDFs to Handle XML Parsing in Snowflake
