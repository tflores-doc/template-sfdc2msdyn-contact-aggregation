
# Anypoint Template: MS Dynamics and Salesforce Contact Aggregation

+ [License Agreement](#licenseagreement)
+ [Use Case](#usecase)
+ [Considerations](#considerations)
	* [Salesforce Considerations](#salesforceconsiderations)
	* [Microsoft Dynamics CRM Considerations](#msdynamicsconsiderations)
+ [Run it!](#runit)
	* [Running on premise](#runonopremise)
	* [Running on Studio](#runonstudio)
	* [Running on Anypoint Studio stand alone](#runonmuleesbstandalone)
	* [Running on CloudHub](#runoncloudhub)
	* [Deploying your Anypoint Template on CloudHub](#deployingyouranypointtemplateoncloudhub)
	* [Properties to be configured (With examples)](#propertiestobeconfigured)
+ [API Calls](#apicalls)
+ [Customize It!](#customizeit)
	* [config.xml](#configxml)
	* [businessLogic.xml](#businesslogicxml)
	* [endpoints.xml](#endpointsxml)
	* [errorHandling.xml](#errorhandlingxml)


# License Agreement <a name="licenseagreement"/>
Note that using this template is subject to the conditions of this [License Agreement](AnypointTemplateLicense.pdf).
Please review the terms of the license before downloading and using this template. In short, you are allowed to use the template for free with CloudHub or as a trial in Anypoint Studio.

# Use Case <a name="usecase"/>
As an admin I want to aggregate contacts from Salesforce and MS Dynamics instances and compare them to see which contacts can only be found in one of the two and which contacts are in both instances. 

For practical purposes this Template will generate the result in the format of a CSV Report sent by email.

This Template should serve as a foundation for extracting data from two systems, aggregating data, comparing values of fields for the objects, and generating a report on the differences. 

As implemented, it gets contacts from Salesforce and MS Dynamics, compares by the email address of the contacts, and generates a CSV file which shows contact name in Salesforce, contact name in MS Dynamics, Email and contacts IDs in Salesforce and MS Dynamics. The report is sent by the email to a configured group of email addresses.

# Considerations <a name="considerations"/>

To make this Anypoint Template run, there are certain preconditions that must be considered. All of them deal with the preparations  that must be made in both Salesforce and MS Dynamics in order for all to run smoothly. **Failing to do so could lead to unexpected behavior of the template.**



## Salesforce Considerations <a name="salesforceconsiderations"/>

There may be a few things that you need to know regarding Salesforce, in order for this template to work.

In order to have this template working as expected, you should be aware of your own Salesforce field configuration.

### FAQ

 - Where can I check that the field configuration for my Salesforce instance is the right one?

    [Salesforce: Checking Field Accessibility for a Particular Field][1]

- Can I modify the Field Access Settings? How?

    [Salesforce: Modifying Field Access Settings][2]


[1]: https://help.salesforce.com/HTViewHelpDoc?id=checking_field_accessibility_for_a_particular_field.htm&language=en_US
[2]: https://help.salesforce.com/HTViewHelpDoc?id=modifying_field_access_settings.htm&language=en_US

### As source of data

If the user configured in the template for the source system does not have at least *read only* permissions for the fields that are fetched, then you will get a *InvalidFieldFault* API fault.

```
java.lang.RuntimeException: [InvalidFieldFault [ApiQueryFault [ApiFault  exceptionCode='INVALID_FIELD'
exceptionMessage='
Account.Phone, Account.Rating, Account.RecordTypeId, Account.ShippingCity
^
ERROR at Row:1:Column:486
No such column 'RecordTypeId' on entity 'Account'. If you are attempting to use a custom field, be sure to append the '__c' after the custom field name. Please reference your WSDL or the describe call for the appropriate names.'
]
row='1'
column='486'
]
]
```







## Microsoft Dynamics CRM Considerations <a name="msdynamicsconsiderations"/>


### As destination of data

There are no particular considerations for this Anypoint Template regarding Microsoft Dynamics CRM as data destination.



# Run it! <a name="runit"/>
Simple steps to get MS Dynamics and Salesforce Contact Aggregation running.


## Running on premise <a name="runonopremise"/>
After this, to trigger the use case you just need to hit the local HTTP endpoint with the port you configured in [common.properties](../blob/master/src/main/resources/common.properties). If this is, for instance, `9090` then you should hit: `http://localhost:9090/generatereport` and this will create a CSV report and send it to the email set.


### Where to Download Anypoint Studio 
First thing to know if you are a newcomer to Mule is where to get the tools.

+ You can download Anypoint Studio from this [Location](https://www.mulesoft.com/platform/studio)
+ You can download Mule runtime from this [Location](https://docs.mulesoft.com/mule4-user-guide/v/4.1/runtime-installation-task)


### Importing an Anypoint Template into Studio
Anypoint Studio offers several ways to import a project into the workspace, for instance: 

+ Anypoint Studio Project from File System
+ Packaged mule application (.jar)

You can find a detailed description on how to do so in this [Documentation Page](https://docs.mulesoft.com/anypoint-studio/v/7.2/import-export-packages).


### Running on Studio <a name="runonstudio"/>
Once you have imported you Anypoint Template into Anypoint Studio you need to follow these steps to run it:

+ Locate the properties file `mule.dev.properties`, in src/main/resources
+ Complete all the properties required as per the examples in the section [Properties to be configured](#propertiestobeconfigured)
+ Once that is done, right click on you Anypoint Template project folder 
+ Hover you mouse over `"Run as"`
+ Click on  `"Mule Application (configure)"`
+ Inside the dialog, select Environment and set the variable `"mule.env"` to the value `"dev"`
+ Click `"Run"`


### Running on Anypoint Stusio stand alone <a name="runonmuleesbstandalone"/>
Complete all properties in one of the property files, for example in [mule.prod.properties] (../master/src/main/resources/mule.prod.properties) and run your app with the corresponding environment variable to use it. To follow the example, this will be `mule.env=prod`. 


## Running on CloudHub <a name="runoncloudhub"/>
While [creating your application on CloudHub](http://https://docs.mulesoft.com/anypoint-studio/v/7.1/deploy-mule-application-task) (Or you can do it later as a next step), you need to go to Deployment > Advanced to set all environment variables detailed in **Properties to be configured** as well as the **mule.env**.
Once your app is all set and started, supposing you choose as domain name `msdynandsfdccontactaggregation` to trigger the use case you just need to hit `http://msdynandsfdccontactaggregation.cloudhub.io/generatereport` and the report will be sent to the emails configured.

### Deploying your Anypoint Template on CloudHub <a name="deployingyouranypointtemplateoncloudhub"/>
Anypoint Studio provides you with really easy way to deploy your Template directly to CloudHub, for the specific steps to do so please check this [link](https://docs.mulesoft.com/anypoint-studio/v/7.1/deploy-mule-application-task)


## Properties to be configured (With examples) <a name="propertiestobeconfigured"/>
In order to use this Anypoint Template you need to configure properties (Credentials, configurations, etc.) either in properties file or in CloudHub as Environment Variables. Detail list with examples:
### Application configuration
**HTTP Connector configuration**   
+ http.port `9090` 

**Salesforce Connector configuration**
+ sfdc.username `bob.dylan@orga`
+ sfdc.password `DylanPassword123`
+ sfdc.securityToken `avsfwCUl7apQs56Xq2AKi3X`

**MS Dynamics Connector configuration**
+ msdyn.user `user@yourorg.onmicrosoft.com`
+ msdyn.password `yourPassword`
+ msdyn.url `https://yourorg.api.crm4.dynamics.com/XRMServices/2011/Organization.svc`
+ msdyn.retries `2`

**SMTP Services configuration**
+ smtp.host `smtp.gmail.com`
+ smtp.port `587`
+ smtp.user `exampleuser@gmail.com`
+ smtp.password `ExamplePassword456`

**Mail details**
+ mail.from `exampleuser1@gmail.com`
+ mail.to `exampleuser2@gmail.com`
+ mail.subject `SFDC Contacts Report`
+ mail.body `Contacts report comparing contacts from SFDC Accounts`
+ attachment.name `report.csv`

# API Calls <a name="apicalls"/>
Salesforce imposes limits on the number of API Calls that can be made. However, we make API call to Salesforce only once during aggregation.


# Customize It!<a name="customizeit"/>
This brief guide intends to give a high level idea of how this Anypoint Template is built and how you can change it according to your needs.
As Mule applications are based on XML files, this page is organized by describing the XML that conforms to the Anypoint Template.
More files can be found such as Test Classes and Mule Application Files, but to keep it simple we will focus on the XML. [Mule Application Files](http://www.mulesoft.org/documentation/display/current/Application+Format), 
Here is a list of the main XML files you'll find in this application:

* [config.xml](#configxml)
* [endpoints.xml](#endpointsxml)
* [businessLogic.xml](#businesslogicxml)
* [errorHandling.xml](#errorhandlingxml)


## config.xml<a name="configxml"/>
Configuration for Connectors and [Configuration Properties](https://docs.mulesoft.com/mule4-user-guide/v/4.1/configuring-properties) are set in this file. **Even you can change the configuration here, all parameters that can be modified here are in properties file, and this is the recommended place to do it so.** Of course if you want to do core changes to the logic you will probably need to modify this file.

In the visual editor they can be found on the *Global Element* tab.


## businessLogic.xml<a name="businesslogicxml"/>
Functional aspect of the Template is implemented on this XML, directed by one flow responsible of conducting the aggregation of data, comparing records and finally formatting the output, in this case being a report.
        
Using Scatter-Gather component we are querying the data in different systems. After that the aggregation is implemented in DataWeave 2 script using Transform component.
Aggregated results are sorted by source of existence:

1. Accounts only in Salesforce
2. Accounts only in MS Dynamics
3. Accounts in both Salesforce and MS Dynamics

and transformed to CSV format. Final report in CSV format is sent to email, that you configured in mule.\*.properties file.



## endpoints.xml<a name="endpointsxml"/>
This is the file where you will find the endpoint to start the aggregation. This Template has an HTTP Inbound Endpoint as the way to trigger the use case.

### Trigger Flow
**HTTP Inbound Endpoint** - Start Report Generation
+ `${http.port}` is set as a property to be defined either on a property file or in CloudHub environment variables.
+ The path configured by default is `generatereport` and you are free to change for the one you prefer.
+ The host name for all endpoints in your CloudHub configuration should be defined as `localhost`. CloudHub will then route requests from your application domain URL to the endpoint.



## errorHandling.xml<a name="errorhandlingxml"/>
This is the right place to handle how your integration will react depending on the different exceptions. 
This file holds an [Error Handler](https://docs.mulesoft.com/mule4-user-guide/v/4.1/intro-error-handlers) that is referenced by the main flow in the business logic.



