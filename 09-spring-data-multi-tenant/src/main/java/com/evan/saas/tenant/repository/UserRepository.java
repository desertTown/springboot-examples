package com.evan.saas.tenant.repository;

import com.evan.saas.tenant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    User findByUsername(String username);
}
