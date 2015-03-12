/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This transformer will take to list as input and create a third one that will
 * be the merge of the previous two. The identity of an element of the list is
 * defined by its email.
 * 
 * @author marek.pataky
 */
public class ContactMerger {
	
	 /**
     * The method will merge the users from the two lists creating a new one.
     *
     * @param contactsFromSalesforce  contacts from Salesforce
     * @param contactsFromMsDynamics contacts from MS Dynamics
     * @return a list with the merged content of the to input lists
     */
    List<Map<String, String>> mergeList(List<Map<String, String>> contactsFromSalesforce, List<Map<String, String>> contactsFromMsDynamics) {

        List<Map<String, String>> mergedContactsList = new ArrayList<Map<String, String>>();

        // Put all contacts from Salesforce in the merged mergedUsersList
        for (Map<String, String> contactFromSalesforce : contactsFromSalesforce) {
            Map<String, String> mergedContact = createMergedContact(contactFromSalesforce);
            mergedContact.put("IDInSalesforce", contactFromSalesforce.get("Id"));
            mergedContact.put("ContactNameInSalesforce", contactFromSalesforce.get("Name"));
            mergedContactsList.add(mergedContact);
        }

        // Add the new contacts from MS Dynamics and update the exiting ones
        for (Map<String, String> contactFromMsDynamics : contactsFromMsDynamics) {
            Map<String, String> contactFromSalesforce = findUserInList(contactFromMsDynamics.get("Email"), mergedContactsList);
            if (contactFromSalesforce != null) {
                contactFromSalesforce.put("IDInMsDynamics", contactFromMsDynamics.get("Id"));
                contactFromSalesforce.put("ContactNameInMsDynamics", contactFromMsDynamics.get("Name"));
            } else {
                Map<String, String> mergedContact = createMergedContact(contactFromMsDynamics);
                mergedContact.put("IDInMsDynamics", contactFromMsDynamics.get("Id"));
                mergedContact.put("ContactNameInMsDynamics", contactFromMsDynamics.get("Name"));
                mergedContactsList.add(mergedContact);
            }
        }
        return mergedContactsList;
    }

    private Map<String, String> createMergedContact(Map<String, String> contact) {

        Map<String, String> mergedContact = new HashMap<String, String>();
        mergedContact.put("ContactNameInSalesforce", "");        
        mergedContact.put("ContactNameInMsDynamics", "");
        mergedContact.put("Email", contact.get("Email"));
        mergedContact.put("IDInSalesforce", "");
        mergedContact.put("IDInMsDynamics", "");
        return mergedContact;
    }

    private Map<String, String> findUserInList(String email, List<Map<String, String>> userList) {

		if (email != null) {
			for (Map<String, String> user : userList) {
				if (email.equals(user.get("Email"))) {
					return user;
				}
			}
		}
		return null;
	}
}
