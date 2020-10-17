package com.dj.cloud.user.service;

import com.dj.cloud.user.model.User;
import com.dj.cloud.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: steven
 * @Date: 2020/10/05
 * @Description: UserService
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById (Long id) {

       return userRepository.getOne(id);
    }

}
