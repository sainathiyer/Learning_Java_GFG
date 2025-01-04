SELECT enrich_xml_dynamic('<Transaction><BuyerFirstName>John</BuyerFirstName></Transaction>', 
    OBJECT_CONSTRUCT('BuyerFirstName', 'Jane', 'BuyerSurname', 'Smith'));
