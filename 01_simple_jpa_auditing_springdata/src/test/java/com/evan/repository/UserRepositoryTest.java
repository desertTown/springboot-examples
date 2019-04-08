package com.evan.repository;

import com.evan.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void createUser() throws Exception {
        User newUser = new User();
        newUser.setName("Evan");
        User user = userRepository.save(newUser);
        log.info(user.toString());
        Assert.assertNotNull(user.getCreatedBy());
        Assert.assertNotNull(user.getCreatedTime());
        Assert.assertNotNull(user.getUpdatedBy());
        Assert.assertNotNull(user.getUpdatedTime());
    }

    @Test
    public void findById() throws Exception {
        Optional<User> user = userRepository.findById(1L);
        if (user.isPresent()) {
            Assert.assertNotNull(user.get().getCreatedBy());
            Assert.assertNotNull(user.get().getCreatedTime());
            Assert.assertNotNull(user.get().getUpdatedBy());
            Assert.assertNotNull(user.get().getUpdatedTime());
        } else {
            Assert.fail();
        }
    }

    @Test
    @Transactional
    public void updateUser() throws Exception {
        Optional<User> oldUser = userRepository.findById(1L);
        if (oldUser.isPresent()) {
            Date oldUpdatedTime = oldUser.get().getUpdatedTime();
            String oldUpdater = oldUser.get().getUpdatedBy();
            oldUser.get().setName("Evan");
            User newUser = userRepository.saveAndFlush(oldUser.get());
            Assert.assertNotEquals(oldUpdatedTime, newUser.getUpdatedTime());
            Assert.assertNotEquals(oldUpdater, newUser.getUpdatedBy());
        } else {
            Assert.fail();
        }

    }

    @Test
    public void deleteById() throws Exception {
    }

}