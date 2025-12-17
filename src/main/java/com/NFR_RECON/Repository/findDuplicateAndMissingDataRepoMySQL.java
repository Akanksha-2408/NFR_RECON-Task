package com.NFR_RECON.Repository;

import com.NFR_RECON.DTO.classDTOMySQL;
import com.NFR_RECON.Entity.classForMySQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface findDuplicateAndMissingDataRepoMySQL extends CrudRepository<classForMySQL, Long> {

    @Query("Select c from classForMySQL c where c.gstin = :gstin and c.txn_id = :txn_id")
    List<classForMySQL> getAll(@Param("gstin") String gstin, @Param("txn_id") String txn_id);
}