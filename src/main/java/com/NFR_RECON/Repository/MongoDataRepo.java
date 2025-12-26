package com.NFR_RECON.Repository;

import com.NFR_RECON.Entity.Entity_Mongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MongoDataRepo extends MongoRepository<Entity_Mongo, ObjectId> {
    List<Entity_Mongo> findByGstinAndTxnIdIn(String gstin, String txnId);
}
