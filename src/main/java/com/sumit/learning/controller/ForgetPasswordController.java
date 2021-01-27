package com.sumit.learning.controller;

import com.sumit.learning.Utils.Logger;
import com.sumit.learning.entity.ConfirmationTokenEntity;
import com.sumit.learning.entity.UserEntity;
import com.sumit.learning.service.LoginSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ForgetPasswordController {


    @Autowired
    private LoginSignUpService loginSignUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/api/forgetpassword/changepassword")
    public String forGetPassWord(@RequestParam("token")String token, Model model)
    {
        model.addAttribute("token",token);
        return "ForgetPassword";
    }
    @PostMapping("/api/forgetpassword/changepassword")
    public String saveNewPassword(@RequestParam("token")String token,@RequestParam("password")String password,
                                  Model model)
    {
        System.out.println(password);
        ConfirmationTokenEntity confirmationTokenEntity=loginSignUpService.findConfirmationTokenByToken(token);
        if(confirmationTokenEntity!=null)
        {
            if(!confirmationTokenEntity.getExpireDate().before(new Date())) {
                UserEntity userEntity = confirmationTokenEntity.getUserEntity();
                userEntity.setPassword(passwordEncoder.encode(password));
                loginSignUpService.addNewUserOrUpdateUser(userEntity);
                Logger.printLog("Updated password for :" + userEntity.getEmail());
                return "SuccessChangePassword";
            }
            else
            {
                return "TokenExpired";
            }
        }
        else
        {
            return "errorConfirmation";
        }
    }
}
