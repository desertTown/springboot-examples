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
        Assert.assertNotNull(user.getCreatedByDomainId());
        Assert.assertNotNull(user.getCreatedByGsid());
        Assert.assertNotNull(user.getCreatedTime());
        Assert.assertNotNull(user.getUpdatedByDomainId());
        Assert.assertNotNull(user.getUpdatedByGsid());
        Assert.assertNotNull(user.getUpdatedTime());
    }

    @Test
    @Transactional
    public void updateUser() throws Exception {
        Optional<User> oldUser = userRepository.findById(1L);
        if (oldUser.isPresent()) {
            Date oldUpdatedTime = oldUser.get().getUpdatedTime();
            String oldDomainId = oldUser.get().getCreatedByDomainId();
            oldUser.get().setName("Evan");
            User newUser = userRepository.saveAndFlush(oldUser.get());
            Assert.assertNotEquals(oldUpdatedTime, newUser.getUpdatedTime());
            Assert.assertNotEquals(oldDomainId, newUser.getUpdatedByDomainId());
        } else {
            Assert.fail();
        }

    }

}