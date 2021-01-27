package com.sumit.learning.service.impl;

import com.sumit.learning.entity.ConfirmationTokenEntity;
import com.sumit.learning.entity.UserEntity;
import com.sumit.learning.repository.ConfirmationTokenRepository;
import com.sumit.learning.repository.UserRepository;
import com.sumit.learning.service.LoginSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginSignUpServiceImpl implements LoginSignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public UserEntity findUserByEmailId(String email) {
        return userRepository.findByEmailIgnoreCase(email).orElse(null);
    }

    @Override
    public UserEntity addNewUserOrUpdateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public ConfirmationTokenEntity findConfirmationTokenByToken(String token) {
        return confirmationTokenRepository.findByConfirmationToken(token).orElse(null);
    }

    @Override
    public ConfirmationTokenEntity addNewConfirmationToken(ConfirmationTokenEntity confirmationTokenEntity) {
        return confirmationTokenRepository.save(confirmationTokenEntity);
    }
}
