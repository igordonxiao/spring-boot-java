package io.github.igordonxiao.service;

import io.github.igordonxiao.dao.UserRepository;
import io.github.igordonxiao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

/**
 * User Service
 */
@Service("userService")
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *  fetch all
     *
     * @return
     */
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * fetch one by id
     *
     * @param id
     * @return
     */
    public User getById(@NotNull Long id) {
        return userRepository.findOne(id);
    }

    /**
     * add or update one
     *
     * @param user
     */
    public User save(@NotNull User user) {
        return (User) userRepository.save(user);
    }

    /**
     * delete one
     *
     * @param id
     */
    public void delete(@NotNull Long id) {
        userRepository.delete(id);
    }
}
