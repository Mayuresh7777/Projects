package com.cg.repo;

import org.springframework.data.repository.CrudRepository;

import com.cg.bean.User;

public interface UserRepo extends CrudRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

	User findByMobileAndPassword(String mobile, String password);

	User findByEmail(String email);

	User findByMobile(String mobile);
}
