package com.NFR_RECON.Repository;

import com.NFR_RECON.DTO.classDTOMongo;
import com.NFR_RECON.Entity.classForMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface findDuplicateAndMissingDataRepoMongo extends MongoRepository<classForMongo, ObjectId> {

    List<classForMongo> findByGstinAndTxnId(String gstin, String txnId);
}
