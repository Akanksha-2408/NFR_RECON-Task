package com.NFR_RECON.Entity.VO;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = DbTables.TBL_INVOICE_BATCH_SUMMARY_DETAILS)
@DynamicInsert()
@DynamicUpdate()
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Data
public class InvoiceBatchSummaryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BATCH_SIZE")
    private int batchSize;

    @Column(name = "FROM_RECORD")
    private int fromInvoice;

    @Column(name = "TO_RECORD")
    private int toInvoice;


    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "GSTIN")
    private String GSTIN;

    @Column(name = "RETURN_PERIOD")
    private String returnPeriod;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "TAX_AMOUNT_SUMMARY", columnDefinition = "text")
    private String taxAmountSummary;

    @Column(name = "IS_USED", columnDefinition = "BIT", length = 1, nullable = false)
    private boolean isUsed;

    public InvoiceBatchSummaryVO(long id, int batchSize, int fromInvoice, int toInvoice, Date createdAt, Date updatedAt, String transactionId, String taxAmountSummary) {
        this.id = id;
        this.batchSize = batchSize;
        this.fromInvoice = fromInvoice;
        this.toInvoice = toInvoice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.transactionId = transactionId;
        this.taxAmountSummary = taxAmountSummary;
    }

    public InvoiceBatchSummaryVO() {

    }

}
