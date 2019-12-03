package com.cxf.webservice.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;
import java.util.Date;

@XStreamAlias("User")
public class SyncMemberDto {

    private BigDecimal id;

    private String code;

    private String username;

    private String phoneticcode;

    private String customcode;

    private String loginname;

    private String password;

    private BigDecimal sexid;

    private Date birthday;

    private BigDecimal identitytype;

    private BigDecimal diplomaid;

    private BigDecimal nationid;

    private BigDecimal bloodtypeid;

    private BigDecimal credentialsid;

    private String identitycard;

    private String phonesel;

    private String linkman;

    private String linkmanphonesel;

    private BigDecimal resourceid;

    private String remark;

    private Date createtime;

    private Date updatetime;

    private BigDecimal recordstate;

    private Date lastuploadtime;

    private BigDecimal degreeid;

    private BigDecimal studymajorid;

    private BigDecimal formajorid;

    private BigDecimal nativeprovincial;

    private BigDecimal nativecity;

    private BigDecimal nativecounty;

    private String nativeaddress;

    private BigDecimal liveprovincial;

    private BigDecimal livecity;

    private BigDecimal livecounty;

    private String liveaddress;

    private String postcode;

    private String officephone;

    private String email;

    private BigDecimal politicalstatus;

    private Date entertime;

    private BigDecimal householdproperty;

    private String disabilitycode;

    private BigDecimal disabilitytype;

    private BigDecimal disabilitylevel;

    //必填:外部医生主键 id
    private BigDecimal outeruserid;
    //必填: 医生的his 键
    private String hiscode;
    //必填:外部人员所在的部门 id
    private BigDecimal outerorganid;
    //其他必填:userName,LoginName,code(工号)


    private String outerorganname;
    private String outerorganhiscode;

    private String nativeprovincialname;
    private String nativecityname;
    private String nativecountyname;
    private String liveprovincialname;
    private String livecityname;
    private String livecountyname;

    private String nationname;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneticcode() {
        return phoneticcode;
    }

    public void setPhoneticcode(String phoneticcode) {
        this.phoneticcode = phoneticcode;
    }

    public String getCustomcode() {
        return customcode;
    }

    public void setCustomcode(String customcode) {
        this.customcode = customcode;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getSexid() {
        return sexid;
    }

    public void setSexid(BigDecimal sexid) {
        this.sexid = sexid;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getIdentitytype() {
        return identitytype;
    }

    public void setIdentitytype(BigDecimal identitytype) {
        this.identitytype = identitytype;
    }

    public BigDecimal getDiplomaid() {
        return diplomaid;
    }

    public void setDiplomaid(BigDecimal diplomaid) {
        this.diplomaid = diplomaid;
    }

    public BigDecimal getNationid() {
        return nationid;
    }

    public void setNationid(BigDecimal nationid) {
        this.nationid = nationid;
    }

    public BigDecimal getBloodtypeid() {
        return bloodtypeid;
    }

    public void setBloodtypeid(BigDecimal bloodtypeid) {
        this.bloodtypeid = bloodtypeid;
    }

    public BigDecimal getCredentialsid() {
        return credentialsid;
    }

    public void setCredentialsid(BigDecimal credentialsid) {
        this.credentialsid = credentialsid;
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard;
    }

    public String getPhonesel() {
        return phonesel;
    }

    public void setPhonesel(String phonesel) {
        this.phonesel = phonesel;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmanphonesel() {
        return linkmanphonesel;
    }

    public void setLinkmanphonesel(String linkmanphonesel) {
        this.linkmanphonesel = linkmanphonesel;
    }

    public BigDecimal getResourceid() {
        return resourceid;
    }

    public void setResourceid(BigDecimal resourceid) {
        this.resourceid = resourceid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public BigDecimal getRecordstate() {
        return recordstate;
    }

    public void setRecordstate(BigDecimal recordstate) {
        this.recordstate = recordstate;
    }

    public Date getLastuploadtime() {
        return lastuploadtime;
    }

    public void setLastuploadtime(Date lastuploadtime) {
        this.lastuploadtime = lastuploadtime;
    }

    public BigDecimal getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(BigDecimal degreeid) {
        this.degreeid = degreeid;
    }

    public BigDecimal getStudymajorid() {
        return studymajorid;
    }

    public void setStudymajorid(BigDecimal studymajorid) {
        this.studymajorid = studymajorid;
    }

    public BigDecimal getFormajorid() {
        return formajorid;
    }

    public void setFormajorid(BigDecimal formajorid) {
        this.formajorid = formajorid;
    }

    public BigDecimal getNativeprovincial() {
        return nativeprovincial;
    }

    public void setNativeprovincial(BigDecimal nativeprovincial) {
        this.nativeprovincial = nativeprovincial;
    }

    public BigDecimal getNativecity() {
        return nativecity;
    }

    public void setNativecity(BigDecimal nativecity) {
        this.nativecity = nativecity;
    }

    public BigDecimal getNativecounty() {
        return nativecounty;
    }

    public void setNativecounty(BigDecimal nativecounty) {
        this.nativecounty = nativecounty;
    }

    public String getNativeaddress() {
        return nativeaddress;
    }

    public void setNativeaddress(String nativeaddress) {
        this.nativeaddress = nativeaddress;
    }

    public BigDecimal getLiveprovincial() {
        return liveprovincial;
    }

    public void setLiveprovincial(BigDecimal liveprovincial) {
        this.liveprovincial = liveprovincial;
    }

    public BigDecimal getLivecity() {
        return livecity;
    }

    public void setLivecity(BigDecimal livecity) {
        this.livecity = livecity;
    }

    public BigDecimal getLivecounty() {
        return livecounty;
    }

    public void setLivecounty(BigDecimal livecounty) {
        this.livecounty = livecounty;
    }

    public String getLiveaddress() {
        return liveaddress;
    }

    public void setLiveaddress(String liveaddress) {
        this.liveaddress = liveaddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getOfficephone() {
        return officephone;
    }

    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getPoliticalstatus() {
        return politicalstatus;
    }

    public void setPoliticalstatus(BigDecimal politicalstatus) {
        this.politicalstatus = politicalstatus;
    }

    public Date getEntertime() {
        return entertime;
    }

    public void setEntertime(Date entertime) {
        this.entertime = entertime;
    }

    public BigDecimal getHouseholdproperty() {
        return householdproperty;
    }

    public void setHouseholdproperty(BigDecimal householdproperty) {
        this.householdproperty = householdproperty;
    }

    public String getDisabilitycode() {
        return disabilitycode;
    }

    public void setDisabilitycode(String disabilitycode) {
        this.disabilitycode = disabilitycode;
    }

    public BigDecimal getDisabilitytype() {
        return disabilitytype;
    }

    public void setDisabilitytype(BigDecimal disabilitytype) {
        this.disabilitytype = disabilitytype;
    }

    public BigDecimal getDisabilitylevel() {
        return disabilitylevel;
    }

    public void setDisabilitylevel(BigDecimal disabilitylevel) {
        this.disabilitylevel = disabilitylevel;
    }

    public BigDecimal getOuteruserid() {
        return outeruserid;
    }

        public void setOuteruserid(BigDecimal outeruserid) {
        this.outeruserid = outeruserid;
    }

    public String getHiscode() {
        return hiscode;
    }

    public void setHiscode(String hiscode) {
        this.hiscode = hiscode;
    }

    public BigDecimal getOuterorganid() {
        return outerorganid;
    }

    public void setOuterorganid(BigDecimal outerorganid) {
        this.outerorganid = outerorganid;
    }

    public String getOuterorganname() {
        return outerorganname;
    }

    public void setOuterorganname(String outerorganname) {
        this.outerorganname = outerorganname;
    }

    public String getOuterorganhiscode() {
        return outerorganhiscode;
    }

    public void setOuterorganhiscode(String outerorganhiscode) {
        this.outerorganhiscode = outerorganhiscode;
    }

    public String getNativeprovincialname() {
        return nativeprovincialname;
    }

    public void setNativeprovincialname(String nativeprovincialname) {
        this.nativeprovincialname = nativeprovincialname;
    }

    public String getNativecityname() {
        return nativecityname;
    }

    public void setNativecityname(String nativecityname) {
        this.nativecityname = nativecityname;
    }

    public String getNativecountyname() {
        return nativecountyname;
    }

    public void setNativecountyname(String nativecountyname) {
        this.nativecountyname = nativecountyname;
    }

    public String getLiveprovincialname() {
        return liveprovincialname;
    }

    public void setLiveprovincialname(String liveprovincialname) {
        this.liveprovincialname = liveprovincialname;
    }

    public String getLivecityname() {
        return livecityname;
    }

    public void setLivecityname(String livecityname) {
        this.livecityname = livecityname;
    }

    public String getLivecountyname() {
        return livecountyname;
    }

    public void setLivecountyname(String livecountyname) {
        this.livecountyname = livecountyname;
    }

    public String getNationname() {
        return nationname;
    }

    public void setNationname(String nationname) {
        this.nationname = nationname;
    }
}
