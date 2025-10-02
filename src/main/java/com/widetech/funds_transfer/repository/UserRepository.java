package com.widetech.funds_transfer.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.widetech.funds_transfer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByUsername(String username, EntityGraph entityGraph);
}
