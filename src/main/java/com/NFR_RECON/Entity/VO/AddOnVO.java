package com.NFR_RECON.Entity.VO;

import java.util.Set;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = DbTables.TBL_ADDON)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@DynamicInsert
@DynamicUpdate
public class AddOnVO {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "ADDON_CODE", length = 50, nullable = false)
    private String addonCode;

    @Column(name = "ALLOWED_API_HITS")
    private int allowedApiHits;

    @Column(name = "BASE_PRICE", columnDefinition = "DECIMAL")
    private double basePrice;

    @Column(name = "PRODUCT")
    private String product;

    @Column(name = "ADDON_NAME")
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "addon", fetch = FetchType.LAZY)
    private Set<PurchasedAddOnVO> purchasedAddons;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddonCode() {
        return addonCode;
    }

    public void setAddonCode(String addonCode) {
        this.addonCode = addonCode;
    }

    public Set<PurchasedAddOnVO> getPurchasedAddOns() {
        return purchasedAddons;
    }

    public void setPurchasedAddOns(Set<PurchasedAddOnVO> purchasedAddons) {
        this.purchasedAddons = purchasedAddons;
    }

    public int getAllowedApiHits() {
        return allowedApiHits;
    }

    public void setAllowedApiHits(int allowedApiHits) {
        this.allowedApiHits = allowedApiHits;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
