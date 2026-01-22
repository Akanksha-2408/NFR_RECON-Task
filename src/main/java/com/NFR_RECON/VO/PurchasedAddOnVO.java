package com.NFR_RECON.VO;

import java.util.Date;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Entity PurchasedAddOnVO
 *
 * @author sagar.tambade
 *
 */
@Entity
@Table(name = DbTables.TBL_PURCHASED_ADDONS)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert()
@DynamicUpdate()
public class PurchasedAddOnVO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EWB_SUBSCRIPTION_DETAILS_ID")
    private EwbSubscriptionDetailsVO ewbSubscription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDON_ID")
    private AddOnVO addon;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE", columnDefinition = "decimal(9,2) default 0.0")
    private double price;

    @Column(name = "TOTAL_PRICE", columnDefinition = "decimal(9,2) default 0.0")
    private double totalPrice;

    @Column(name = "TAX_PERCENTAGE", columnDefinition = "decimal(5,2) default 0.0")
    private double taxPercentage;

    @Column(name = "PARTNER_ID")
    private long partnerId;

    @Column(name = "PAYMENT_MODE", length = 50)
    private String paymentMode;

    @Column(name = "PAYMENT_STATUS", length = 50)
    private String paymentStatus;

    @Column(name = "CREATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "GSTIN_ID")
    private long gstinId;

    @Column(name = "INVOICE_ID")
    private String invoiceId;

    @Transient
    private double totalTax;

    @Transient
    private double totalWithTax;

    @Transient
    private String purchasedOn;

    public long getId() {
        return id;
    }

    public long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(long partnerId) {
        this.partnerId = partnerId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AddOnVO getAddon() {
        return addon;
    }

    public void setAddon(AddOnVO addon) {
        this.addon = addon;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public EwbSubscriptionDetailsVO getEwbSubscription() {
        return ewbSubscription;
    }

    public void setEwbSubscription(EwbSubscriptionDetailsVO ewbSubscription) {
        this.ewbSubscription = ewbSubscription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getGstinId() {
        return gstinId;
    }

    public void setGstinId(long gstinId) {
        this.gstinId = gstinId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public double getTotalWithTax() {
        return totalWithTax;
    }

    public void setTotalWithTax(double totalWithTax) {
        this.totalWithTax = totalWithTax;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPurchasedOn() {
        return purchasedOn;
    }

    public void setPurchasedOn(String purchasedOn) {
        this.purchasedOn = purchasedOn;
    }

}
