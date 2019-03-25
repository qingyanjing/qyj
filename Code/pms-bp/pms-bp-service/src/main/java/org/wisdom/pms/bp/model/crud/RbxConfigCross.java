package org.wisdom.pms.bp.model.crud;

import java.io.Serializable;
import java.math.BigDecimal;

public class RbxConfigCross extends RbxConfigCrossKey implements Serializable{
    /**
	 *  序列化id
	 */
	private static final long serialVersionUID = 1L;

	private String CrossDesc;

    private String ScatsId;

    private String Ip;

    private String AreaCode;

    private String AreaDesc;

    private BigDecimal LongItude;

    private BigDecimal LatItude;

    public String getCrossDesc() {
        return CrossDesc;
    }

    public void setCrossDesc(String CrossDesc) {
        this.CrossDesc = CrossDesc == null ? null : CrossDesc.trim();
    }

    public String getScatsId() {
        return ScatsId;
    }

    public void setScatsId(String ScatsId) {
        this.ScatsId = ScatsId == null ? null : ScatsId.trim();
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip == null ? null : Ip.trim();
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String AreaCode) {
        this.AreaCode = AreaCode == null ? null : AreaCode.trim();
    }

    public String getAreaDesc() {
        return AreaDesc;
    }

    public void setAreaDesc(String AreaDesc) {
        this.AreaDesc = AreaDesc == null ? null : AreaDesc.trim();
    }

    public BigDecimal getLongItude() {
        return LongItude;
    }

    public void setLongItude(BigDecimal LongItude) {
        this.LongItude = LongItude;
    }

    public BigDecimal getLatItude() {
        return LatItude;
    }

    public void setLatItude(BigDecimal LatItude) {
        this.LatItude = LatItude;
    }
}