package com.widetech.funds_transfer.repository;

import com.widetech.funds_transfer.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

  List<Account> findAllByUserId(Long userId);
}
