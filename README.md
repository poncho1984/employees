# employees
Rest Service to manage employees

**Running**

To run the service just clone the repo, and from command line run:
gradle bootRun

This will start the microservice into on **http://localhost:8080**

On startup the service will start an H2 db and will prepopulate data found on data.sql file under main/resources directory.


**Endpoints Specs**


**- GET employee by ID**

http://localhost:8080/employee/1

**Response:**

{
    "id": 1,
    "firstName": "Scott",
    "middleInitial": "A.",
    "lastName": "Rush",
    "dateOfBirth": 457250400000,
    "dateOfEmployment": 1546322400000
}




**- Create new employee**

**Post:** localhost:8080/employee

**Payload:**

{
    "firstName" : "Andrew",
    "middleInitial" : "",
    "lastName" : "Eng",
    "dateOfBirth" : "1998-06-28",
    "dateOfEmployment" : "2019-01-25"
}

**Response**

2 _**new created Id**_


**- Edit employee**

**PUT:** localhost:8080/employee/2

**Payload:**

{
    "firstName" : "Andrew",
    "middleInitial" : "E.",
    "lastName" : "Eng",
    "dateOfBirth" : "1998-06-28",
    "dateOfEmployment" : "2019-01-25"
}

**Response:** 204 No Content

**- Get all employees**

localhost:8080/employees

**Response:**

[
    {
        "id": 3,
        "firstName": "Jose Alfonso",
        "middleInitial": "A.",
        "lastName": "Noguez Flores",
        "dateOfBirth": 457228800000,
        "dateOfEmployment": 1519516800000
    },
    {
        "id": 4,
        "firstName": "Alfonso",
        "middleInitial": "",
        "lastName": "Noguez Ceballos",
        "dateOfBirth": 898992000000,
        "dateOfEmployment": 1548374400000
    }
]

**- Deactivated an employee**

**DELETE** 

For this example you need the next Basic Authorization header, otherwise you will get a 403 Forbidden Response.
  
**Authorization Basic cG9uY2hvOlAwbmNoTw==**

**Responses:**

204 No Content

402 Forbidden without correct Authorization HEADER