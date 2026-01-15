package com.NFR_RECON.Entity.VO;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = DbTables.TBL_INVOICE)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceVO {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "INVOICE")
    private String invoice;

}
