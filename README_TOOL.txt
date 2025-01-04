String query = "SELECT buyer_first_name, buyer_last_name, buyer_date_of_birth, " +
               "buyer_decision_maker_code, buyer_decision_maker_first_name, buyer_decision_maker_last_name, buyer_decision_maker_date_of_birth, " +
               "seller_first_name, seller_last_name, seller_date_of_birth, " +
               "seller_decision_maker_code, seller_decision_maker_first_name, seller_decision_maker_last_name, seller_decision_maker_date_of_birth, " +
               "investment_decision_within_firm, branch_country_investment_decision, " +
               "execution_within_firm, branch_country_execution " +
               "FROM PII_DATA WHERE vault_id = ?";

if (rs.next()) {
    piiData.put("buyerfirstname", rs.getString("buyer_first_name"));
    piiData.put("buyerlastname", rs.getString("buyer_last_name"));
    piiData.put("buyerdateofbirth", rs.getString("buyer_date_of_birth"));
    piiData.put("buyerdecisionmakercode", rs.getString("buyer_decision_maker_code"));
    piiData.put("buyerdecisionmakerfirstname", rs.getString("buyer_decision_maker_first_name"));
    piiData.put("buyerdecisionmakerlastname", rs.getString("buyer_decision_maker_last_name"));
    piiData.put("buyerdecisionmakerdateofbirth", rs.getString("buyer_decision_maker_date_of_birth"));
    piiData.put("sellerfirstname", rs.getString("seller_first_name"));
    piiData.put("sellerlastname", rs.getString("seller_last_name"));
    piiData.put("sellerdateofbirth", rs.getString("seller_date_of_birth"));
    piiData.put("sellerdecisionmakercode", rs.getString("seller_decision_maker_code"));
    piiData.put("sellerdecisionmakerfirstname", rs.getString("seller_decision_maker_first_name"));
    piiData.put("sellerdecisionmakerlastname", rs.getString("seller_decision_maker_last_name"));
    piiData.put("sellerdecisionmakerdateofbirth", rs.getString("seller_decision_maker_date_of_birth"));
    piiData.put("investmentdecisionwithinfirm", rs.getString("investment_decision_within_firm"));
    piiData.put("branchcountryinvestmentdecision", rs.getString("branch_country_investment_decision"));
    piiData.put("executionwithinfirm", rs.getString("execution_within_firm"));
    piiData.put("branchcountryexecution", rs.getString("branch_country_execution"));
}


String[] piiFields = {
    "BuyerFirstName", "BuyerLastName", "BuyerDateOfBirth",
    "BuyerDecisionMakerCode", "BuyerDecisionMakerFirstName", "BuyerDecisionMakerLastName", "BuyerDecisionMakerDateOfBirth",
    "SellerFirstName", "SellerLastName", "SellerDateOfBirth",
    "SellerDecisionMakerCode", "SellerDecisionMakerFirstName", "SellerDecisionMakerLastName", "SellerDecisionMakerDateOfBirth",
    "InvestmentDecisionWithinFirm", "BranchCountryInvestmentDecision",
    "ExecutionWithinFirm", "BranchCountryExecution"
};
