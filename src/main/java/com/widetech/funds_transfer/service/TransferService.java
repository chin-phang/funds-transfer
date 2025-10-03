package com.widetech.funds_transfer.service;

import com.widetech.funds_transfer.dto.SearchTransferRequest;
import com.widetech.funds_transfer.dto.TransferInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransferService {

  Page<TransferInfo> findAll(SearchTransferRequest searchTransferReq, Pageable pageable);
}
