import xml.etree.ElementTree as ET
import json

def enrich_xml_dynamic(xml_content: str, pii_data: dict) -> str:
    """
    Enriches the XML content with PII data.
    
    Parameters:
        xml_content (str): The XML string to be enriched.
        pii_data (dict): A dictionary-like object (Snowflake VARIANT) containing PII fields and values.

    Returns:
        str: The enriched XML as a string.
    """
    try:
        # Parse the XML content
        tree = ET.ElementTree(ET.fromstring(xml_content))
        root = tree.getroot()

        # Iterate over the PII data and update XML tags
        for field, value in pii_data.items():
            # Find all elements in the XML matching the field name
            elements = root.findall(f".//{field}")

            if elements:
                # If the tag exists, update its value
                for elem in elements:
                    elem.text = str(value)
            else:
                # If the tag doesn't exist, add it to the root (or you can choose a specific node)
                new_elem = ET.Element(field)
                new_elem.text = str(value)
                root.append(new_elem)

        # Convert the updated XML tree back to a string
        enriched_xml = ET.tostring(root, encoding="unicode")
        return enriched_xml

    except Exception as e:
        # Handle parsing or enrichment errors
        return f"Error enriching XML: {str(e)}"
