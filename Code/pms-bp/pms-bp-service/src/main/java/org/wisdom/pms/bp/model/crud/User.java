package org.wisdom.pms.bp.model.crud;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    /**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	private String Id;

    private String Name;

    private String Pass;

    private Date updateTime;

    private Date createTime;

    private String role;

    private String dept;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id == null ? null : Id.trim();
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name == null ? null : Name.trim();
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass == null ? null : Pass.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }
}