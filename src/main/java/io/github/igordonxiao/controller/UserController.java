package io.github.igordonxiao.controller;

import io.github.igordonxiao.common.JSONResponse;
import io.github.igordonxiao.model.User;
import io.github.igordonxiao.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * get a User
     *
     * @return JSONResponse
     */
    @RequestMapping("/{id}")
    @ResponseBody
    public JSONResponse one(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) return JSONResponse.ERROR_FOR_NOT_FOUND();
        return JSONResponse.OK(user);
    }

    /**
     * add a User
     *
     * @param user
     * @return JSONResponse
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public JSONResponse add(@RequestBody User user) {
        User savedUser = userService.save(user);
        if (savedUser == null) return JSONResponse.ERROR_FOR_SERVER_ERROR();
        return JSONResponse.OK(user);
    }

    /**
     * update a User
     *
     * @param user
     * @return JSONResponse
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public JSONResponse update(@RequestBody User user) {
        if (user.getId() == null) return JSONResponse.ERROR_FOR_BAD_PARAM();
        User userDb = userService.getById(user.getId());
        if (userDb == null) return JSONResponse.ERROR_FOR_NOT_FOUND();
        BeanUtils.copyProperties(user, userDb);
        userService.save(userDb);
        return JSONResponse.OK(userDb);
    }

    /**
     * delete a User
     *
     * @param id
     * @return JSONResponse
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONResponse delete(@PathVariable Long id) {
        if (id <= 0) return JSONResponse.ERROR_FOR_BAD_PARAM();
        User userDb = userService.getById(id);
        if (userDb == null) return JSONResponse.ERROR_FOR_NOT_FOUND();
        userService.delete(id);
        return JSONResponse.OK(userDb);
    }
}
