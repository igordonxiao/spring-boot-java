package io.github.igordonxiao.dao;

import io.github.igordonxiao.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * User Repository
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
