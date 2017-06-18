package io.github.igordonxiao.controller;

import io.github.igordonxiao.common.HttpException;
import io.github.igordonxiao.model.User;
import io.github.igordonxiao.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 */
@Api(value = "User", description = "")
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
     * @return User
     */
    @ApiOperation(value = "get one user", notes = "get user by id")
    @GetMapping("/{id}")
    @ResponseBody
    public User findById(@ApiParam(value = "user id", type = "Long") @PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) throw HttpException.NOT_FOUND;
        return user;
    }

    /**
     * add a User
     *
     * @param user
     * @return User
     */
    @ApiOperation(value = "add user", notes = "")
    @ApiImplicitParam(name = "user", value = "user entity", required = true, dataType = "User")
    @PostMapping
    @ResponseBody
    public User add(@ApiParam(value = "user entity", type = "User") @RequestBody User user) {
        User savedUser = userService.save(user);
        if (savedUser == null) throw HttpException.SERVER_ERROR;
        return savedUser;
    }

    /**
     * update a User
     *
     * @param user
     * @return User
     */
    @ApiOperation(value = "update user", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user entity", required = true, dataType = "User")
    })
    @PutMapping
    @ResponseBody
    public User update(@ApiParam(value = "user entity", type = "User") @RequestBody User user) {
        if (user.getId() == null) throw HttpException.BAD_REQUEST;
        User userDb = userService.getById(user.getId());
        if (userDb == null) throw HttpException.NOT_FOUND;
        BeanUtils.copyProperties(user, userDb);
        userService.save(userDb);
        return userDb;
    }

    /**
     * delete a User
     *
     * @param id
     * @return User
     */
    @ApiOperation(value = "delete user by id", notes = "")
    @ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public User delete(@ApiParam(value = "user id", type = "Long") @PathVariable Long id) {
        if (id <= 0) throw HttpException.BAD_REQUEST;
        User userDb = userService.getById(id);
        if (userDb == null) throw HttpException.NOT_FOUND;
        userService.delete(id);
        return userDb;
    }
}
