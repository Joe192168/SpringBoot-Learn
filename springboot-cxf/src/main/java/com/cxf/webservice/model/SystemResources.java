package com.cxf.webservice.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.math.BigDecimal;

@XStreamAlias("Department")
public class SystemResources implements Serializable {

    private static final long serialVersionUID = -8035376037081187546L;
    //外部主键,基于 app 的,而非系统内唯一
    private BigDecimal outerid;
    //his主键
    private String hiscode;
    //部门名称
    private String officename;
    //外部 部门父级 id
    private BigDecimal outerparentid;
    //级别
    private Integer officelevel;
    //内部id
    private BigDecimal innerid;

    public BigDecimal getOuterid() {
        return outerid;
    }

    public void setOuterid(BigDecimal outerid) {
        this.outerid = outerid;
    }

    public String getHiscode() {
        return hiscode;
    }

    public void setHiscode(String hiscode) {
        this.hiscode = hiscode;
    }

    public String getOfficename() {
        return officename;
    }

    public void setOfficename(String officename) {
        this.officename = officename;
    }

    public BigDecimal getOuterparentid() {
        return outerparentid;
    }

    public void setOuterparentid(BigDecimal outerparentid) {
        this.outerparentid = outerparentid;
    }

    public Integer getOfficelevel() {
        return officelevel;
    }

    public void setOfficelevel(Integer officelevel) {
        this.officelevel = officelevel;
    }

    public BigDecimal getInnerid() {
        return innerid;
    }

    public void setInnerid(BigDecimal innerid) {
        this.innerid = innerid;
    }
}
