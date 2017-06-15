package io.github.igordonxiao;

import io.github.igordonxiao.model.User;
import io.github.igordonxiao.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @MockBean
    private UserService userService;

    @Test
    public void test001Save() {
        User user = new User();
        user.setId(1L);
        user.setName("Gordon");

        given(userService.getById(Mockito.anyLong())).willReturn(user);
        assertThat(user.getName().equals(userService.getById(1L).getName()));
    }

}
