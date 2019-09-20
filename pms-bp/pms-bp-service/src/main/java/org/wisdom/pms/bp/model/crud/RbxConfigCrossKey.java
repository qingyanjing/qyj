package org.wisdom.pms.bp.model.crud;

public class RbxConfigCrossKey {
    private String CrossNo;

    private String Mac;

    public String getCrossNo() {
        return CrossNo;
    }

    public void setCrossNo(String CrossNo) {
        this.CrossNo = CrossNo == null ? null : CrossNo.trim();
    }

    public String getMac() {
        return Mac;
    }

    public void setMac(String Mac) {
        this.Mac = Mac == null ? null : Mac.trim();
    }
}