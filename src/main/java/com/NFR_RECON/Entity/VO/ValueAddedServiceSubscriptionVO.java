package com.NFR_RECON.Entity.VO;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = DbTables.TBL_VALUE_ADDED_SERVICE_SUB)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate()
@Data
public class ValueAddedServiceSubscriptionVO implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "GSTIN_NUMBER")
    private String gstinNumber;

    @Column(name = "FEATURE")
    private String feature;

    @Column(name = "IS_ACTIVE", columnDefinition = "BIT",length = 1, nullable = false)
    private boolean isActive;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "START_DATE", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "END_DATE", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "SUBSCRIPTION_ID")
    private String subscriptionId;
}

