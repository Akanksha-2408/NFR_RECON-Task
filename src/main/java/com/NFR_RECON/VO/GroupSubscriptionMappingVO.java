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
@Table(name = DbTables.TBL_GROUP_SUBSCRIPTION_MAPPING)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert()
@DynamicUpdate()
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupSubscriptionMappingVO{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "PRIMARY_GSTIN_ID")
	private long primaryGstinId;

	@Column(name = "PAN")
	private String pan;

	@Column(name = "SUBSCRIPTION_ID", length = 50)
	private String subscriptionId;

	@Column(name = "PRODUCT_NAME", length = 50)
	private String productName;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANNUAL_RECORD_COUNT_DETAILS_ID")
	private AnnualRecordCountDetailsVO annualRecordCountDetails;

	@Column(name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "UPDATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
}
