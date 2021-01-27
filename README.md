# Spring-boot-security-jwt

Basic Features: Spring boot apis for signup, getting confirmation token on email, login, forgetpassword link on email, Jwt 

token on login, and securing the remaining apis by jwt token, Encrypt password by bcrypt


Put in your Email Id and Passsword in application.properties. If you face some error, turn on less secure app access for 

your google account in security.
In config.properties, put in the same mail id

Now you can run the application, you will be able to access signup api, receive confirmation link on email, after 

confirmation only you will be able to login

You have forgetpassword api, to change the password

On success call to login api, it will return a valid jwt token, now all these api(ex: signup, login, forgetpassword) are 

excluded from token verification, but all the rest apis are protected by http security( Look at WebSecurityConfig, 

JwtUtil, JwtFilter classes), to test the jwt token working properly, Test jwt by calling this api(/api/test/loginsuccess), 

unless you are passing a valid token, you will receive forbidden error on this api.
