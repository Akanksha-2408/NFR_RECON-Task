package com.NFR_RECON.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface findDuplicateAndMissingDataRepo extends JpaRepository<Long, String> {

}
