package com.widetech.funds_transfer.repository;

import com.widetech.funds_transfer.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,String>, JpaSpecificationExecutor<Transfer> {

}
