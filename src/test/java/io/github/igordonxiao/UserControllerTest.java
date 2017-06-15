package io.github.igordonxiao;

import io.github.igordonxiao.controller.UserController;
import io.github.igordonxiao.model.User;
import io.github.igordonxiao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGet() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Gordon");

        given(userService.getById(Mockito.anyLong())).willReturn(user);
        mvc.perform(get("/users/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("{\"status\":200")));
    }

}
