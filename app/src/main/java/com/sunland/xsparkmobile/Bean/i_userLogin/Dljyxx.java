package com.sunland.xsparkmobile.Bean.i_userLogin;

import com.sunlandgroup.def.bean.result.ResultBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class Dljyxx extends ResultBase {
    private String username;//	String	用户名
    private String realname;//	String	真实姓名
    private String rylx;//String	人员类别：1.民警2：拖车司机3：停车场管理员4：停车场门卫
    private String rylxstr;//	String	人员类别（中文）
    private String lxfs;//	联系方式
    private String departmentcode;//	String	所属部门code
    private String bmmc;//	String	部门名称
    private String remark;//	String	备注
    private String menuids;//	String	菜单ID    (以 # 隔开)
    private String privilege;//	String	权限ID    (细粒度权限预留)

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRylx() {
        return rylx;
    }

    public void setRylx(String rylx) {
        this.rylx = rylx;
    }

    public String getRylxstr() {
        return rylxstr;
    }

    public void setRylxstr(String rylxstr) {
        this.rylxstr = rylxstr;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMenuids() {
        return menuids;
    }

    public void setMenuids(String menuids) {
        this.menuids = menuids;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
