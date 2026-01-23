package com.NFR_RECON.VO;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = DbTables.TBL_USER)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert()
@DynamicUpdate()
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @Column(name = "EMAIL", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "MOBILE", length = 15, nullable = false)
    private String mobile;

    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;

    @Column(name = "STATUS", length = 30, nullable = false)
    private String status;

    @Column(name = "UUID", length = 100)
    private String uuid;

    @Column(name = "IS_DELETED", columnDefinition = "BIT", length = 1, nullable = false)
    private boolean isDeleted;

    @Column(name = "PAN_NO", length = 32)
    private String panNumber;

    @Column(name = "DESIGNATION", length = 32)
    private String designation;

    @Column(name = "AADHAR_NO", length = 32)
    private String aadharNumber;

    @Column(name = "WEEKLY_UPDATE", length = 32)
    private String weeklyUpdate;

    @Column(name = "CREATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "LAST_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(name = "DEVICE_TOKEN_ANDROID", length = 255)
    private String deviceTokenAndroid;

    @Column(name = "DEVICE_TOKEN_IOS", length = 255)
    private String deviceTokenIOS;

    @Column(name = "DEVICE_TYPE", length = 32)
    private String deviceType;

    @Column(name = "SIGNUP_AS", length = 32)
    private String signUpAs;

    @Column(name = "REGISTRATION_STEP", length = 32)
    private int registrationStep;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getWeeklyUpdate() {
        return weeklyUpdate;
    }

    public void setWeeklyUpdate(String weeklyUpdate) {
        this.weeklyUpdate = weeklyUpdate;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getDeviceTokenAndroid() {
        return deviceTokenAndroid;
    }

    public void setDeviceTokenAndroid(String deviceTokenAndroid) {
        this.deviceTokenAndroid = deviceTokenAndroid;
    }

    public String getDeviceTokenIOS() {
        return deviceTokenIOS;
    }

    public void setDeviceTokenIOS(String deviceTokenIOS) {
        this.deviceTokenIOS = deviceTokenIOS;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getSignUpAs() {
        return signUpAs;
    }

    public void setSignUpAs(String signUpAs) {
        this.signUpAs = signUpAs;
    }

    public int getRegistrationStep() {
        return registrationStep;
    }

    public void setRegistrationStep(int registrationStep) {
        this.registrationStep = registrationStep;
    }
}
