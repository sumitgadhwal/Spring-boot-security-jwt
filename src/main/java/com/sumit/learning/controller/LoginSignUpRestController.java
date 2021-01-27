package com.sumit.learning.controller;

import com.sumit.learning.Utils.JwtUtil;
import com.sumit.learning.config.ConfigProperties;
import com.sumit.learning.entity.ConfirmationTokenEntity;
import com.sumit.learning.entity.UserEntity;
import com.sumit.learning.entity.MessageToUI;
import com.sumit.learning.service.EmailSenderService;
import com.sumit.learning.service.LoginSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginSignUpRestController {

    @Autowired
    LoginSignUpService loginSignUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/api/signup")
    public ResponseEntity<?> signUpNewUser(@RequestBody UserEntity userEntity)
    {
        userEntity.setEmail(userEntity.getEmail().toLowerCase());
        MessageToUI msg= new MessageToUI();
        UserEntity userEntity1=loginSignUpService.findUserByEmailId(userEntity.getEmail());
        if(userEntity1!=null)
        {
            msg.setMessage("This Email Id already exists, try logging in");
        }
        else
        {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity=loginSignUpService.addNewUserOrUpdateUser(userEntity);
            boolean isSaved=userEntity.getId()!=0;
            if(isSaved)
            {
                msg.setMessage("Confirmation link sent to email, after clicking on link your account will become active ");
                ConfirmationTokenEntity confirmationTokenEntity=new ConfirmationTokenEntity(userEntity);
                loginSignUpService.addNewConfirmationToken(confirmationTokenEntity);


                emailSenderService.sendEmail(userEntity.getEmail(),
                        ConfigProperties.getProperties().getProperty("EmailToSendConfirmation")
                ,"Confirmation link for your mail",
                        "To confirm your account click on link:"+
                                ConfigProperties.getProperties().getProperty("ApplicationUrl")+"/signup/confirmation?token="
                                +confirmationTokenEntity.getConfirmationToken());
            }
            else
            {
                msg.setMessage("Something went wrong while signing you up, please give it another try");
            }
        }
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/api/login")
    public ResponseEntity<?> loginAndReturnToken(@RequestBody UserEntity userEntity)
    {
        userEntity.setEmail(userEntity.getEmail().toLowerCase());
        MessageToUI msg= new MessageToUI();
        UserEntity userEntityFetchedFromDB=loginSignUpService.findUserByEmailId(userEntity.getEmail());
        if(userEntityFetchedFromDB==null)
        {
            msg.setMessage("Email not registered, signup first");
        }
        else if(!userEntityFetchedFromDB.isEnabled())
        {
            msg.setMessage("Confirmation link sent to email, only after confirmation you will be able to login ");
            ConfirmationTokenEntity confirmationTokenEntity=new ConfirmationTokenEntity(userEntityFetchedFromDB);
            loginSignUpService.addNewConfirmationToken(confirmationTokenEntity);

            emailSenderService.sendEmail(userEntity.getEmail(),
                    ConfigProperties.getProperties().getProperty("EmailToSendConfirmation")
                    ,"Confirmation link for your mail",
                    "To confirm your account click on link:"+
                            ConfigProperties.getProperties().getProperty("ApplicationUrl")+"/signup/confirmation?token="
                            +confirmationTokenEntity.getConfirmationToken());
        }
        else
        {
            if(passwordEncoder.matches(userEntity.getPassword(),userEntityFetchedFromDB.getPassword()))
            {


                String jwt= jwtTokenUtil.generateToken(userEntityFetchedFromDB);
                msg.setToken(jwt);
                msg.setMessage("Login successful");
            }
            else
            {
                msg.setMessage("Login UnSuccessful");
            }
        }
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/api/forgetpassword")
    public ResponseEntity<?> forgetPassword(@RequestBody UserEntity userEntity)
    {
        userEntity.setEmail(userEntity.getEmail().toLowerCase());
        MessageToUI msg= new MessageToUI();
        UserEntity userEntityFetchedFromDB=loginSignUpService.findUserByEmailId(userEntity.getEmail());
        if(userEntityFetchedFromDB==null)
        {
            msg.setMessage("Email not registered, signup first");
        }
        else
        {
            ConfirmationTokenEntity confirmationTokenEntity=new ConfirmationTokenEntity(userEntityFetchedFromDB);
            loginSignUpService.addNewConfirmationToken(confirmationTokenEntity);


            emailSenderService.sendEmail(userEntityFetchedFromDB.getEmail(),
                    ConfigProperties.getProperties().getProperty("EmailToSendConfirmation")
                    ,"Change password for your account",
                    "click here to change your password :"+
                            ConfigProperties.getProperties().getProperty("ApplicationUrl")+"/api/forgetpassword/changepassword?token="
                            +confirmationTokenEntity.getConfirmationToken());
            msg.setMessage("Change password link sent on mail");
        }
        return ResponseEntity.ok(msg);
    }

    /*
        Test jwt by calling this api, unless you are passing a valid token, you will receive
        forbidden error on this api
    */

    @GetMapping("/api/test/loginsuccess")
    public ResponseEntity<?> loginSuccess()
    {
        MessageToUI msg=new MessageToUI();
        msg.setMessage("Able to access api");
        return ResponseEntity.ok(msg);
    }

}
