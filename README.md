# NinjaOne Backend Interview Project

## Interviewee Notes

There are three main resources - Device, Service, and Device Type. Each has CRUD endpoints to work with.

**Device**
* Create a new Device: http://localhost:8080/device POST

    Sample body 1:
    `{
    "systemName": "TEST2",
    "type" : "WW",
    "services" : ["BACKUP", "WIN_ANTIVIRUS", "SCREEN_SHARE"]
    }`

    Sample body 2:
    `{
    "systemName": "TEST5",
    "type" : "MAC",
    "services" : ["BACKUP", "MAC_ANTIVIRUS", "SCREEN_SHARE"]
    }`

* Get Device by id: http://localhost:8080/device/1 GET
* Get All Devices: http://localhost:8080/device/all GET
* Get Total Cost: http://localhost:8080/device/total-cost GET
* Update Device: http://localhost:8080/device/1 PUT
* Delete Device: http://localhost:8080/device/1 DELETE

**Service**
* Create a new Service: http://localhost:8080/service POST

    Sample body:
    `{
    "serviceId": "PROD",
    "cost" : "7",
    "displayValue" : "Productivity Software Bundle"
    }`

* Get Service by id: http://localhost:8080/service/WIN_ANTIVIRUS GET
* Get All Services: http://localhost:8080/service/all GET
* Get Total Cost Grouped by Service: http://localhost:8080/service/total-costs GET
* Delete Service: http://localhost:8080/service/1 DELETE

**Device Type**
* Create a new Device Type: http://localhost:8080/device-type POST

  Sample body:
  `{
  "deviceTypeId": "LX",
  "displayValue" : "Linux Server"
  }`

* Get Device Type by id: http://localhost:8080/device-type/WW GET
* Get All Device Types: http://localhost:8080/device-type/all GET


A couple of other points
* I have added a few services and device types to the data.sql startup script.
  * Device types available: `WW, WS, and MAC`
  * Services available:  `WIN_ANTIVIRUS, MAC_ANTIVIRUS, BACKUP, PSA, SCREEN_SHARE`. Please note the `DEVICE` service is automatically added by the app. You don't have to add it manually. And it can't be removed.
* What is missing: logging, auth, proper unit testing (there is some)

## Starting the Application

Run the `BackendInterviewProjectApplication` class

Go to:
* http://localhost:8080/service/SCREEN_SHARE
* http://localhost:8080/device-type/all


You should see results for both of these. The application is working and connected to the H2 database. 

## H2 Console 

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```

You should be able to see a db console now that has the Sample Repository in it.

Type:

```sql
SELECT * FROM SVC;
````

Click `Run`, you should see two rows
