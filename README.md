
# codefellowship

*ApplationUser has the following properties : 
    * username
    * password (will be hashed using BCrypt)
    * firstName
    * lastName
    * dateOfBirth
    * bio

* the App has pages: 
  * home page : allows user to sign uo and log in 
  * log in  : when user provide his/her username & password he will log in to app if he is signing up
  * sign up : allowed user to sign up to the app, user should provide: username, password, firstName, lastName, dateOfBirth, bio

* password encoder is used : if you write a password it will encrypted then saved to database 


* Routes of app: 
* (/) : shows the home page of app 
  * (if you not logged in it will return you to login page)
  * (when you logged in it will redirect you to app and shows you app information )
  * you can logout using button after logging in 
  
* (/signup): 
  * shows the page of sign up contains form to provide users information and sign up

* (login) : 
  * if user signed in it allows user enter the app (all pages of app )
  * your username will be shown at the top of page 


* (/myprofile):
  * displays user information, include a default profile picture, and their basic information 

* (/users/{id}): 
  * allows viewing the data about a single ApplicationUser, include a default profile picture, and their basic information

* (/Post): user should post a post , includes body and time in which post created 
  * Note : I make the form and collect data but it doesn't save to db 

   

