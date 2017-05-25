FORMAT: 1A
HOST: http://sjsuconnect.apiblueprint.org/

# SJSU Connect REST APIs

APIs for SJSU Connect Node Express Server

## Register [/register]
This API will be used to register new user. User can register either using email address or phone number.
Sensitive data such as passwords will be encrypted using bcrypt in NodeJS.

### Register New User [POST]

+ Request (application/json)

        {
            "first_name": "Sagar",
            "last_name": "Mane",
            "phone_number": "669-225-9371",
            "country_code": 1,
            "email": "sagar.mane@sjsu.edu",
            "password": "**"
        }

+ Response 200 (application/json)

        [
            {
                "verification_code":4444
            }
        ]
        
## Login [/login]
This API will be used to login the user into the application. If username and passwords match then user will be logged into the system. 

### Login Existing User [POST]

+ Request (application/json)

        {
            "email": "sagar.mane@sjsu.edu",
            "password": "****"
            
        }

+ Response 200 (application/json)

        [
            {
                "status":"SignIn Successful"
            }
        ]
        
## ValidateUser [/validateUser]
When user register's he will get an 4 digit verification code in his email addresss.
He will have to enter the verification code in order to complete the registration.
Once Verification is done he will be able to login to the application.

### Validate Newly Registered User [POST]

+ Request (application/json)

        {
            "verification_code": "****",
        }

+ Response 200 (application/json)

        [
            {
                "status":"Validation Successful"
            }
        ]

## Forgot Password [/forgotPassword]
This will be used to reset the password for specific profile.

### Reset Password [POST]

+ Request (application/json)

        {
            "email": "sagar.mane@gmail.com",
        }

+ Response 200 (application/json)

        [
            {
                "verification_id":"****"
            }
        ]
                

## Get Posts [/getPosts]
When user logs in he will be presented with recent post feed from his friends and people he is following.
Posts will be displayed according to the timestamp when they were posted.A

### Get Posts Feed [GET]

+ Request (application/json)

        {
            "email": "abc@example.com",
        }

+ Response 200 (application/json)

        [
            {
                "posts":
                {
                    [
                        {
                             "owner_email_id":"abc@example.com",
                             "content":"Feeling amazing using Apiary. Give it a Try!!1",
                             "date":"12-05-2017",
                             "time":"8:00AM",
                             "media":"link to image in s3",
                             "comments":
                             {
                                "email":"xyz@example.com",
                                "content":"Great, will try doing that",
                                "date":"12-05-2017",                                "time":"10:23AM",
                                        
                             }
                        
                        }
                    ],
                    [
                        {
                            "owner_email_id":"vedant@gmail.com",
                             "content":"Thinking of going to gym in summer",
                             "date":"12-05-2017",
                             "time":"9:00AM",
                             "media":"link to image in s3",
                             "comments":
                             {
                                "email":"sagar@gmail.com",
                                "content":"Good luck doing that 😅",
                                "date":"12-05-2017",
                                "time":"10:25AM",
                                        
                             }
                        }
                    ]
                }
            }
        ]

## Update Profile [/updateProfile]      
Every registered use has a profile. User can update his profile at any time by going in the profile view. 

### Update User Profile [POST]


+ Request (application/json)

        {
            "nick_name"       : "Maddy",
            "email"           : "abc@gmail.com",
            "current_location": "San Jose",
            "home_town"       : "Nagput",
            "profession"      : "Student",
            "profile_pic"     : "link to s3",
            "birth_date"      : "09-02-1995"
            "bio"             : "Dream Discover Achieve",
            "interests"       : "Sports",
            
        }

+ Response 200 (application/json)

        [
            {
                "status":"Updation Successful"
            }
        ]
    
## Browse Friends [/friends]
This API will be used to browse through your friends list.

### See Your Friends [GET]

+ Request (application/json)

        {
            "email": "sagar.mane@sjsu.edu",
            
        }

+ Response 200 (application/json)

        [
            {
                "first_name":"Sagar",
                "last_name":"Mane",
                "profile_pic":"",
            }
        ]
        
## Update Status [/update]
This API will be used to update your status with or without the media(pic).

### Update Status [POST]

+ Request (application/json)

        {
            "email": "sagar.mane@sjsu.edu",
            "status": "",
            "media": ""
            
        }

+ Response 200 (application/json)

        [
            {
                "status":"Status Updated"
            }
        ]
        
## Send Private Message [/send_message]
This API will be used to send private messages to friends or anyone with the public profile.

### Sending private message [POST]

+ Request (application/json)

        {
            "email": "sagar.mane@sjsu.edu",
            "recipient": "vedant@gmail.com",
            "subject":"How are you",
            "message":"Hello",
            
        }

+ Response 200 (application/json)

        [
            {
                "status":"Sent"
            }
        ]

## Receive Private Message [/receive_message]
This API will be used to receive messages.

### Receiving messages  [GET]

+ Request (application/json)

        {
            "sender":"sagar.mane@gmail.com",
            "subject":"How are you",
            "body":"Hey there, how are you doing!!"
        }

+ Response 200 (application/json)

        [
            {
                "status":"received"
            }
        ]

## Approve Friend Request [/add_friend]
This API will be used to approve friend requests.

### Approving friend requests received.  [POST]

+ Request (application/json)

        {
            "email":"sagar.mane@gmail.com",
            "friend_email":"vedant@gmail.com"
        }

+ Response 200 (application/json)

        [
            {
                "status":"Added to your friend list"
            }
        ]
        
## Reject Friend Request [/reject_friend]
This API will be used to approve friend requests.

### Deleting unwanted friend requests. [DELETE]

+ Request (application/json)

        {
            "email":"sagar.mane@gmail.com",
            "friend_email":"vedant@gmail.com"
        }

+ Response 200 (application/json)

        [
            {
                "status":"Friend request deleted."
            }
        ]

## Get Friend Requests [/getFriendRequests]
This API will be used for reponding to friend requests.

### Repond to friend requests. [GET]

+ Request (application/json)

        {
            "email":"sagar.mane@gmail.com"
        }

+ Response 200 (application/json)

        [
            {
                "response":"Array of pending friend requests"
            }
        ]

## Add friend by Email Address [/addByEmail]
This API will be used for adding friend with email addresss.

### Add By EMail [POST]

+ Request (application/json)

        {
            "email":"sagar.mane@gmail.com",
            "friend_email":"vedant@example.com",
            "first_name" : "Vedant"
        }

+ Response 200 (application/json)

        [
            {
                "message":"Successful ! Added to your friend list"
            }
        ]

## Follow others [/follow]
This API will be used for following other users on SJSU connect.

### Follow [POST]

+ Request (application/json)

        {
            "email":"sagar.mane@gmail.com",
            "follow_user_email":"vedant@example.com",
            "first_name" : "Vedant"
        }

+ Response 200 (application/json)

        [
            {
                "message":"Successful ! Following user"
            }
        ]
        
## Get Timeline [/getTimeline]
This API will be used for displaying timeline.

### Show Timeline [POST]

+ Request (application/json)

        {
            "email":"sagar.mane@gmail.com"    
        }

+ Response 200 (application/json)

        [
            {
                "response":"Current Timeline"
            }
        ]
