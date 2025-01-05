{ "status": "success", "s3_file_url": "s3://your-bucket/enriched_data_20250101123000.xml" }


 # Enrich the XML with the PII data by checking each field and updating corresponding XML tags
        if 'BuyerFirstName' in pii_dict:
            buyer_element = root.find('.//BuyerFirstName')
            if buyer_element is not None:
                buyer_element.text = pii_dict['BuyerFirstName']

        if 'BuyerSurname' in pii_dict:
            surname_element = root.find('.//BuyerSurname')
            if surname_element is not None:
                surname_element.text = pii_dict['BuyerSurname']

        if 'BuyerDateOfBirth' in pii_dict:
            dob_element = root.find('.//BuyerDateOfBirth')
            if dob_element is not None:
                dob_element.text = pii_dict['BuyerDateOfBirth']

        if 'SellerFirstName' in pii_dict:
            seller_firstname_element = root.find('.//SellerFirstName')
            if seller_firstname_element is not None:
                seller_firstname_element.text = pii_dict['SellerFirstName']

        if 'SellerSurname' in pii_dict:
            seller_surname_element = root.find('.//SellerSurname')
            if seller_surname_element is not None:
                seller_surname_element.text = pii_dict['SellerSurname']

        if 'InvestmentDecisionWithinFirm' in pii_dict:
            investment_element = root.find('.//InvestmentDecisionWithinFirm')
            if investment_element is not None:
                investment_element.text = pii_dict['InvestmentDecisionWithinFirm']

        if 'BuyerIdentificationCode' in pii_dict:
            buyer_id_element = root.find('.//BuyerIdentificationCode')
            if buyer_id_element is not None:
                buyer_id_element.text = pii_dict['BuyerIdentificationCode']

        if 'BuyerDecisionMakerCode' in pii_dict:
            buyer_decision_maker_code_element = root.find('.//BuyerDecisionMakerCode')
            if buyer_decision_maker_code_element is not None:
                buyer_decision_maker_code_element.text = pii_dict['BuyerDecisionMakerCode']

        if 'SellerIdentificationCode' in pii_dict:
            seller_id_element = root.find('.//SellerIdentificationCode')
            if seller_id_element is not None:
                seller_id_element.text = pii_dict['SellerIdentificationCode']

        if 'SellerDecisionMakerCode' in pii_dict:
            seller_decision_maker_code_element = root.find('.//SellerDecisionMakerCode')
            if seller_decision_maker_code_element is not None:
                seller_decision_maker_code_element.text = pii_dict['SellerDecisionMakerCode']

        if 'BuyerDecisionMakerFirstName' in pii_dict:
            buyer_decision_maker_firstname_element = root.find('.//BuyerDecisionMakerFirstName')
            if buyer_decision_maker_firstname_element is not None:
                buyer_decision_maker_firstname_element.text = pii_dict['BuyerDecisionMakerFirstName']

        if 'BuyerDecisionMakerSurname' in pii_dict:
            buyer_decision_maker_surname_element = root.find('.//BuyerDecisionMakerSurname')
            if buyer_decision_maker_surname_element is not None:
                buyer_decision_maker_surname_element.text = pii_dict['BuyerDecisionMakerSurname']

        if 'BuyerDecisionMakerDateOfBirth' in pii_dict:
            buyer_decision_maker_dob_element = root.find('.//BuyerDecisionMakerDateOfBirth')
            if buyer_decision_maker_dob_element is not None:
                buyer_decision_maker_dob_element.text = pii_dict['BuyerDecisionMakerDateOfBirth']

        if 'SellerDecisionMakerFirstName' in pii_dict:
            seller_decision_maker_firstname_element = root.find('.//SellerDecisionMakerFirstName')
            if seller_decision_maker_firstname_element is not None:
                seller_decision_maker_firstname_element.text = pii_dict['SellerDecisionMakerFirstName']

        if 'SellerDecisionMakerSurname' in pii_dict:
            seller_decision_maker_surname_element = root.find('.//SellerDecisionMakerSurname')
            if seller_decision_maker_surname_element is not None:
                seller_decision_maker_surname_element.text = pii_dict['SellerDecisionMakerSurname']

        if 'SellerDecisionMakerDateOfBirth' in pii_dict:
            seller_decision_maker_dob_element = root.find('.//SellerDecisionMakerDateOfBirth')
            if seller_decision_maker_dob_element is not None:
                seller_decision_maker_dob_element.text = pii_dict['SellerDecisionMakerDateOfBirth']

        if 'CountryOfBranchResponsibleForInvestmentDecision' in pii_dict:
            country_of_branch_investment_element = root.find('.//CountryOfBranchResponsibleForInvestmentDecision')
            if country_of_branch_investment_element is not None:
                country_of_branch_investment_element.text = pii_dict['CountryOfBranchResponsibleForInvestmentDecision']

        if 'CountryOfBranchSupervisingExecution' in pii_dict:
            country_of_branch_execution_element = root.find('.//CountryOfBranchSupervisingExecution')
            if country_of_branch_execution_element is not None:
                country_of_branch_execution_element.text = pii_dict['CountryOfBranchSupervisingExecution']

        if 'ExecutionWithinFirm' in pii_dict:
            execution_element = root.find('.//ExecutionWithinFirm')
            if execution_element is not None:
                execution_element.text = pii_dict['ExecutionWithinFirm']
