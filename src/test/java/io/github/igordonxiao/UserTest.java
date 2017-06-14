package io.github.igordonxiao;

import io.github.igordonxiao.model.User;
import io.github.igordonxiao.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Unit Test for User
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
    private static User savedUser = null;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    @Rollback(false)
    public void test001Save() {
        User user = new User();
        user.setName("savedUser");
        savedUser = userService.save(user);
        assertEquals("savedUser", savedUser.getName());
    }

    @Test
    @Rollback(false)
    public void test002Query() {
        assertEquals("savedUser", userService.getById(savedUser.getId()).getName());
    }

    @Test
    @Rollback(false)
    public void test003Update() {
        User user = userService.getById(savedUser.getId());
        user.setName("updatedUser");
        User updatedUser = userService.save(user);
        assertEquals("updatedUser", userService.getById(updatedUser.getId()).getName());
    }

    @Test
    @Rollback(false)
    public void test004Delete() {
        Long savedUserId = savedUser.getId();
        userService.delete(savedUserId);
        assertEquals(null, userService.getById(savedUserId));
    }
}
