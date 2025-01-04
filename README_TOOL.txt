package com.piivault.utils;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class EnrichTransactionXML {

    public static String enrichXML(String xmlContent) throws Exception {
        // Step 1: Parse the XML and extract the Vault ID
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);  // Disable namespace awareness
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new java.io.ByteArrayInputStream(xmlContent.getBytes()));

        // Extract VaultId using normal XPath query (without namespaces)
        String vaultId = document.getElementsByTagName("VaultId").item(0).getTextContent();

        // Step 2: Connect to Snowflake and fetch data for the Vault ID
        Map<String, String> piiData = new HashMap<>();
        try (Connection conn = DriverManager.getConnection("jdbc:snowflake://dtccdrvesdev.us-east-1.snowflakecomputing.com", "srvc_dse_app_dev", "Temp4567!")) {
            String query = "SELECT buyer_identification_code, buyer_first_name, buyer_surname, buyer_date_of_birth, buyer_decision_maker_code, " +
                           "buyer_decision_maker_first_name, buyer_decision_maker_surname, buyer_decision_maker_date_of_birth, seller_identification_code, " +
                           "seller_first_name, seller_surname, seller_date_of_birth, seller_decision_maker_code, seller_decision_maker_first_name, " +
                           "seller_decision_maker_surname, seller_decision_maker_date_of_birth, investment_decision_within_firm, country_of_branch_making_investment_decision, " +
                           "execution_within_firm, country_of_branch_supervising_execution FROM PII_DATA WHERE vault_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, vaultId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        piiData.put("buyeridentificationcode", rs.getString("buyer_identification_code"));
                        piiData.put("buyerfirstname", rs.getString("buyer_first_name"));
                        piiData.put("buyersurname", rs.getString("buyer_surname"));
                        piiData.put("buyerdateofbirth", rs.getString("buyer_date_of_birth"));
                        piiData.put("buyerdecisionmakercode", rs.getString("buyer_decision_maker_code"));
                        piiData.put("buyerdecisionmakerfirstname", rs.getString("buyer_decision_maker_first_name"));
                        piiData.put("buyerdecisionmakersurname", rs.getString("buyer_decision_maker_surname"));
                        piiData.put("buyerdecisionmakerdateofbirth", rs.getString("buyer_decision_maker_date_of_birth"));
                        piiData.put("selleridentificationcode", rs.getString("seller_identification_code"));
                        piiData.put("sellerfirstname", rs.getString("seller_first_name"));
                        piiData.put("sellersurname", rs.getString("seller_surname"));
                        piiData.put("sellerdateofbirth", rs.getString("seller_date_of_birth"));
                        piiData.put("sellerdecisionmakercode", rs.getString("seller_decision_maker_code"));
                        piiData.put("sellerdecisionmakerfirstname", rs.getString("seller_decision_maker_first_name"));
                        piiData.put("sellerdecisionmakersurname", rs.getString("seller_decision_maker_surname"));
                        piiData.put("sellerdecisionmakerdateofbirth", rs.getString("seller_decision_maker_date_of_birth"));
                        piiData.put("investmentdecisionwithinfirm", rs.getString("investment_decision_within_firm"));
                        piiData.put("countryofbranchmakinginvestmentdecision", rs.getString("country_of_branch_making_investment_decision"));
                        piiData.put("executionwithinfirm", rs.getString("execution_within_firm"));
                        piiData.put("countryofbranchsupervisingexecution", rs.getString("country_of_branch_supervising_execution"));
                    }
                }
            }
        }

        // Step 3: Enrich the missing fields in the XML
        String[] piiFields = {
            "BuyerFirstName", "BuyerSurname", "BuyerDateOfBirth", "BuyerDecisionMakerCode", "BuyerDecisionMakerFirstName", "BuyerDecisionMakerSurname", 
            "BuyerDecisionMakerDateOfBirth", "SellerIdentificationCode", "SellerFirstName", "SellerSurname", "SellerDateOfBirth", "SellerDecisionMakerCode",
            "SellerDecisionMakerFirstName", "SellerDecisionMakerSurname", "SellerDecisionMakerDateOfBirth", "InvestmentDecisionWithinFirm", 
            "CountryOfBranchMakingInvestmentDecision", "ExecutionWithinFirm", "CountryOfBranchSupervisingExecution"
        };

        for (String field : piiFields) {
            NodeList fieldNodes = document.getElementsByTagName(field);  // No namespace here

            if (fieldNodes.getLength() > 0) {
                Element fieldElement = (Element) fieldNodes.item(0);
                if (fieldElement.getTextContent().trim().isEmpty()) {
                    String fieldValue = piiData.getOrDefault(field.toLowerCase(), "");
                    fieldElement.setTextContent(fieldValue);
                }
            }
        }

        // Step 4: Return the updated XML as string
        return documentToString(document);
    }

    private static String documentToString(Document doc) throws Exception {
        // Convert Document back to String
        java.io.StringWriter writer = new java.io.StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.toString();
    }

    public static void main(String[] args) {
        // Sample usage
        try {
            String xml = "<your XML string here>";
            String enrichedXml = enrichXML(xml);
            System.out.println(enrichedXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
