package io.github.igordonxiao;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
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

    private String JSON_MEDIA_TYPE = "application/json;charset=UTF-8";


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetById() throws Exception {
        User userEntity = new User();
        userEntity.setId(1L);
        given(userService.getById(Mockito.anyLong()))
                .willReturn(userEntity);
        mvc.perform(MockMvcRequestBuilders.get(
                "/users/1")
                .accept(MediaType.parseMediaType(JSON_MEDIA_TYPE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1")));
    }

    @Test
    public void testPost() throws Exception {
        User userEntity = new User();
        userEntity.setId(1L);
        given(userService.save(Mockito.any(User.class)))
                .willReturn(userEntity);

        User postUserEntity = new User();
        mvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).accept(JSON_MEDIA_TYPE).content(objectMapper.writeValueAsBytes(postUserEntity))).andDo(print()).andExpect(status().isCreated()).andExpect(content().string(containsString("{\"id\":1")));
    }

    @Test
    public void testUpdate() throws Exception {
        User userEntity = new User();
        userEntity.setId(1L);
        given(userService.getById(Mockito.anyLong()))
                .willReturn(userEntity);
        given(userService.save(Mockito.any(User.class)))
                .willReturn(userEntity);

        User postUserEntity = new User();
        postUserEntity.setId(1L);
        postUserEntity.setName("updated name");
        mvc.perform(MockMvcRequestBuilders.put("/users").contentType(MediaType.APPLICATION_JSON).accept(JSON_MEDIA_TYPE).content(objectMapper.writeValueAsBytes(postUserEntity))).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("updated name")));
    }

    @Test
    public void testDelete() throws Exception {
        User user = new User();
        user.setId(1L);
        given(userService.getById(Mockito.anyLong())).willReturn(user);
        //given(userService.delete(Mockito.anyLong())).willReturn(Unit)

        mvc.perform(MockMvcRequestBuilders.delete("/users/1").contentType(MediaType.APPLICATION_JSON).accept(JSON_MEDIA_TYPE)).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("{\"id\":1")));
    }

}
