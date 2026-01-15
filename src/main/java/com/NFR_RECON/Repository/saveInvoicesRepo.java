package com.NFR_RECON.Repository;

import com.NFR_RECON.Entity.VO.InvoiceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface saveInvoicesRepo extends JpaRepository<InvoiceVO, Long> {
}
