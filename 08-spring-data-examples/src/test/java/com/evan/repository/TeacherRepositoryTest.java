package com.evan.repository;

import com.evan.model.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void testOrderBy() {
        List<Teacher> teachers = teacherRepository.findByNameNotNullOrderByAgeAsc();
        Assert.assertEquals("王五", teachers.get(0).getName());
    }
}
