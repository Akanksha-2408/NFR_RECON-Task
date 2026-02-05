package com.NFR_RECON.VO;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert()
@DynamicUpdate()
@Table(name = DbTables.TBL_ANNUAL_RECORD_COUNT_DETAILS)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnnualRecordCountDetailsVO{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "GSTIN_ID")
    private long gstinId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "TOTAL_OUTWARD_SALES_COUNT")
    private long totalOutwardSalesCount;

    @Column(name = "TOTAL_B2C_COUNT")
    private long totalB2cCount;

    @Column(name = "TOTAL_SALES_COUNT")
    private long totalSalesCount;

    @Column(name = "TOTAL_DELIVERY_CHALLAN_COUNT")
    private long totalDeliveryChallanCount;

    @Column(name = "TOTAL_INWARD_RECORD_COUNT")
    private long totalInwardRecordCount;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    private Date updatedAt;
}
