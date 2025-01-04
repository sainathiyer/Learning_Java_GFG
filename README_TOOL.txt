package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new java.io.ByteArrayInputStream(xmlContent.getBytes()));

        NodeList vaultIdNodes = document.getElementsByTagName("VaultId");
        if (vaultIdNodes.getLength() == 0) {
            throw new Exception("VaultId tag not found in XML.");
        }
        String vaultId = vaultIdNodes.item(0).getTextContent();

        // Step 2: Connect to Snowflake and fetch data for the Vault ID
        Map<String, String> piiData = new HashMap<>();
        try (Connection conn = DriverManager.getConnection("jdbc:snowflake://<account>.snowflakecomputing.com", "<username>", "<password>")) {
            String query = "SELECT buyer_first_name, buyer_last_name, buyer_date_of_birth, " +
                           "seller_first_name, seller_last_name, seller_date_of_birth, " +
                           "investment_decision_maker, execution_decision_maker " +
                           "FROM PII_DATA WHERE vault_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, vaultId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        piiData.put("buyerfirstname", rs.getString("buyer_first_name"));
                        piiData.put("buyerlastname", rs.getString("buyer_last_name"));
                        piiData.put("buyerdateofbirth", rs.getString("buyer_date_of_birth"));
                        piiData.put("sellerfirstname", rs.getString("seller_first_name"));
                        piiData.put("sellerlastname", rs.getString("seller_last_name"));
                        piiData.put("sellerdateofbirth", rs.getString("seller_date_of_birth"));
                        piiData.put("investmentdecisionmaker", rs.getString("investment_decision_maker"));
                        piiData.put("executiondecisionmaker", rs.getString("execution_decision_maker"));
                    }
                }
            }
        }

        // Step 3: Enrich the missing fields in the XML
        String[] piiFields = {
            "BuyerFirstName", "BuyerLastName", "BuyerDateOfBirth",
            "SellerFirstName", "SellerLastName", "SellerDateOfBirth",
            "InvestmentDecisionMaker", "ExecutionDecisionMaker"
        };

        for (String field : piiFields) {
            NodeList fieldNodes = document.getElementsByTagName(field);
            if (fieldNodes.getLength() > 0) {
                Element fieldElement = (Element) fieldNodes.item(0);
                if (fieldElement.getTextContent().trim().isEmpty()) {
                    String fieldValue = piiData.getOrDefault(field.toLowerCase(), "MASKED");
                    fieldElement.setTextContent(fieldValue);
                }
            }
        }

        // Step 4: Convert the enriched XML back to a string
        java.io.StringWriter writer = new java.io.StringWriter();
        javax.xml.transform.Transformer transformer = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
        transformer.transform(new javax.xml.transform.dom.DOMSource(document), new javax.xml.transform.stream.StreamResult(writer));

        // Step 5: Return the enriched XML
        return writer.toString();
    }
}
