package com.cunha.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cunha.calculator.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT user FROM User user where user.userName=:userName")
	User findByUsername(@Param("userName") String userName);

}
