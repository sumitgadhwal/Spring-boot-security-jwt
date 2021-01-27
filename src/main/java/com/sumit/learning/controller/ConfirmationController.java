package com.sumit.learning.controller;

import com.sumit.learning.Utils.Logger;
import com.sumit.learning.entity.ConfirmationTokenEntity;
import com.sumit.learning.entity.UserEntity;
import com.sumit.learning.service.LoginSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ConfirmationController {

    @Autowired
    private LoginSignUpService loginSignUpService;

    @RequestMapping(value = "/signup/confirmation", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmTokenForSignUp(@RequestParam("token")String token, Model model)
    {
        ConfirmationTokenEntity confirmationTokenEntity=loginSignUpService.findConfirmationTokenByToken(token);
        if(confirmationTokenEntity!=null)
        {
            if(!confirmationTokenEntity.getExpireDate().before(new Date())) {
                UserEntity userEntity = confirmationTokenEntity.getUserEntity();
                userEntity.setEnabled(true);
                loginSignUpService.addNewUserOrUpdateUser(userEntity);
                Logger.printLog("confirmed account of :" + userEntity.getEmail());
                return "successConfirmation";
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
