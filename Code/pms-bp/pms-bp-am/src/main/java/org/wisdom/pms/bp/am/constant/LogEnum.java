package org.wisdom.pms.bp.am.constant;
/**
 * 本地日志枚举
 * @author Administrator
 *
 */
public enum  LogEnum {
 
 
    BUSSINESS("bussiness"),
 
    PLATFORM("platform"),
 
    DATABASE("database"),
 
    EXCEPTION("exception"),
 
    ;
 
	// 类别
    private String category;
 
 
    LogEnum(String category) {
        this.category = category;
    }
 
    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
}