package com.NFR_RECON.Repository;

import com.NFR_RECON.Entity.Entity_Mysql;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MysqlDataRepo extends CrudRepository<Entity_Mysql, Long> {

    @Query("Select c from Entity_Mysql c where c.gstin = :gstin and c.txn_id = :txn_id")
    List<Entity_Mysql> getAll(@Param("gstin") String gstin, @Param("txn_id") String txn_id);

}