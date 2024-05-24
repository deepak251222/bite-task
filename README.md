In this Java application, built with Java 8, Spring Boot, and MySQL
HERE ALL API With RESPONSE CODE like  200,201
   
  Identify API  (base on documents conditions like if data is not present it will create new record and retruning data like below Response)

  POST   http://localhost:8080/api/contacts/identify
  
  request :-

          {
            "phoneNumber": "8118818157",
           "email": "deepak251222@gmail.com"
           }

   Response :-

    {
    "primaryContactId": 1,
    "emails": [
        "deepak251222@gmail.com",
        "deepak251222@gmail.com",
        "deepak251222@gmail.com",
        "deepak25122cd2@gmail.com",
        "deepak251222@gmail.com"
    ],
    "phoneNumbers": [
        "123456",
        "123456",
        "8118818157",
        "8118818157",
        "8118818157"
    ],
    "secondaryContactIds": [
        2,
        3,
        4,
        6
    ]
    }




FOR CREATE CONTACT (ALSO I CREATE NEW API FOR CREATE CONTEACT)
 POST API :  http://localhost:8080/api/contacts/create
    
 request: - 
 1.     {
          "phoneNumber": "8118818157",
          "email": "deepak251222@gmail.com"
        }
   2.      {
          "phoneNumber": "8118818157",
          "email": "abc@gmail.com.com"
          }

 Response : - 
  1.     {
         "id": 1,
         "phoneNumber": "8118818157",
         "email": "deepak251222@gmail.com",
         "linkedId": null,
         "linkPrecedence": "PRIMARY",
         "createdAt": "2024-05-24T10:43:47.8515379",
         "updatedAt": "2024-05-24T10:43:47.8515379",
         "deletedAt": null
         }
  2.     {
          "id": 2,
         "phoneNumber": "8118818157",
         "email": "abc@gmail.com",
         "linkedId": 1,
         "linkPrecedence": "SECONDARY",
         "createdAt": "2024-05-24T10:45:47.8515379",
         "updatedAt": "2024-05-24T10:45:47.8515379",
         "deletedAt": null
         }


 GET API FOR GETTING ALL RECORDS : -
   GET  API : -  http://localhost:8080/api/contacts
    
   response : -  
             
    [
     {
        "id": 1,
        "phoneNumber": "123456",
        "email": "deepak251222@gmail.com",
        "linkedId": 1,
        "linkPrecedence": "PRIMARY",
        "createdAt": "2024-05-24T10:41:32.651464",
        "updatedAt": "2024-05-24T10:41:32.651464",
        "deletedAt": null
    },
    {
        "id": 2,
        "phoneNumber": "123456",
        "email": "deepak251222@gmail.com",
        "linkedId": 1,
        "linkPrecedence": "SECONDARY",
        "createdAt": "2024-05-24T10:41:39.819955",
        "updatedAt": "2024-05-24T10:41:39.819955",
        "deletedAt": null
    },
    {
        "id": 3,
        "phoneNumber": "8118818157",
        "email": "deepak251222@gmail.com",
        "linkedId": 1,
        "linkPrecedence": "SECONDARY",
        "createdAt": "2024-05-24T10:41:57.194247",
        "updatedAt": "2024-05-24T10:41:57.194247",
        "deletedAt": null
    }
    ]

 
 
 API FOR GET OWN DETATILS

  GET METHOD    http://localhost:8080/api/contacts/get_own_details
  
    request :- 
   
     {
         "phoneNumber": "8118818157",
        "email": "deepak251222@gmail.com"
      }
      
      respose :- 
      
    [
    {
        "id": 1,
        "phoneNumber": "8118818157",
        "email": "deepak251222@gmail.com",
        "linkedId": null,
        "linkPrecedence": "PRIMARY",
        "createdAt": "2024-05-24T12:07:31.576124",
        "updatedAt": "2024-05-24T12:07:31.576124",
        "deletedAt": null
    },
    
    {
        "id": 2,
        "phoneNumber": "8118818157",
        "email": "deepak251222@gmail.com",
        "linkedId": 1,
        "linkPrecedence": "SECONDARY",
        "createdAt": "2024-05-24T12:21:04.330487",
        "updatedAt": "2024-05-24T12:21:04.330487",
        "deletedAt": null
    },
    
    {
        "id": 3,
        "phoneNumber": "8118818157",
        "email": "deepak251222@gmail.com",
        "linkedId": 1,
        "linkPrecedence": "SECONDARY",
        "createdAt": "2024-05-24T12:42:00.316261",
        "updatedAt": "2024-05-24T12:42:00.316261",
        "deletedAt": null
    }
]


FOR UPDATE 
  i did not use put method i used patch method for some field update.

  update api:-  (patch method)     http://localhost:8080/api/contacts/2
     request:-   
     
     {
            "linkPrecedence": "PRIMARY"
    }

  delete api:- (Delete method)   http://localhost:8080/api/contacts/4
