package com.dj.cloud.user.repository;

import com.dj.cloud.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: steven
 * @Date: 2020/10/05
 * @Description: UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
