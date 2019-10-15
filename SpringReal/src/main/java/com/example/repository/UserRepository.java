package com.example.repository;



import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.UserBO;



public interface UserRepository  extends JpaRepository<UserBO, Serializable>{

	@Query(name="Select ub From UserBO ub where ub.userId like %:searchValue%")
	public List<UserBO> findByUserId(@Param("searchValue")String searchValue);
}

