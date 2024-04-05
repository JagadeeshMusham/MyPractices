package com.ciena.secureappwithpostgre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//	public User findByUserName(String userName);
	
	User findByName(String name);
}
