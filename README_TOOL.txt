import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.stream.Collectors;

public class ParseXmlTag {

    public String parseXml(String xmlContent, String tagName) throws Exception {
        // Initialize the XML parser
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML content
        Document doc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes()));

        // Normalize the XML structure
        doc.getDocumentElement().normalize();

        // Get elements by tag name
        NodeList nodeList = doc.getElementsByTagName(tagName);

        // Return the text content of the first matching tag
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return null; // Tag not found
        }
    }
}


javac ParseXmlTag.java
jar cf ParseXmlTag.jar ParseXmlTag.class


PUT file://ParseXmlTag.jar @~;


CREATE OR REPLACE FUNCTION parse_xml_tag(xml_content STRING, tag_name STRING)
RETURNS STRING
LANGUAGE JAVA
RUNTIME_VERSION = '11'
IMPORTS = ('@~ParseXmlTag.jar')
HANDLER = 'ParseXmlTag.parseXml';


SELECT 
    parse_xml_tag('<CustomerPIIData><BuyerFirstName>John</BuyerFirstName></CustomerPIIData>', 'BuyerFirstName') AS buyer_first_name;

