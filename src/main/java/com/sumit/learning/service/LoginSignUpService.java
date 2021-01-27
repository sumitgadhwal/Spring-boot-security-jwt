package com.sumit.learning.service;

import com.sumit.learning.entity.ConfirmationTokenEntity;
import com.sumit.learning.entity.UserEntity;

public interface LoginSignUpService {

    public UserEntity findUserByEmailId(String email);
    public UserEntity addNewUserOrUpdateUser(UserEntity userEntity);
    public ConfirmationTokenEntity findConfirmationTokenByToken(String token);
    public ConfirmationTokenEntity addNewConfirmationToken(ConfirmationTokenEntity confirmationTokenEntity);
}
