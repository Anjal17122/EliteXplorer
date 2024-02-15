package com.elitexplorer.backend.userdetail.repository;

import com.elitexplorer.backend.userdetail.model.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail,Integer> {

    UserDetail findByUsername(String username);
}
