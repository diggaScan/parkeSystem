package com.sunland.xsparkmobile.Bean.i_queryCompulsoryProofInfo;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class Qzcfpz {
    private int lsh;//	int流水号
    private String zt;//	char 操操作标识(null未初始化，0未到时，1准备，2成功，3异常，4-在线实时失败his表；7-未打印完成；8-删除；9-转递超时)
    private String lrsj;//	Date 录入时间
    private String gxsj;//	Date 更新时间
    private String zdsj;//	Date 转递时间
    private String ywzl;//	char 业务种类(1-强制措施；2-违法通知书)
    private String pdams;//	char 终端模式 (1-在线；2-离线；)
    private String pzbh;//	String 凭证编号
    private String wslb;//	String 文书类别
    private String qzcslx;//	String 强制措施类型
    private String klwpcfd;//	String 扣留物品存放地
    private String sjxm;//	String 收缴项目
    private String sjwpmc;//	String 收缴物品名称
    private String sjwpcfd;//String 收缴物品存放地
    private String ryfl;//	String 人员分类
    private String jszh;//	String 驾驶证号
    private String dabh;//	String 档案编号
    private String fzjg;//	String 发证机关
    private String zjcx;//	String 准驾车型
    private String dsr;//String 住所行政区划
    private String zsxzqh;//	String 住所行政区划
    private String zsxxdz;//	String 住所详细地址
    private String dh;//	String 电话
    private String lxfs;//	String 联系方式
    private String clfl;//	String 车辆分类
    private String hpzl;//	String 号牌种类
    private String hphm;//String 号牌号码
    private String jdcsyr;//	String 机动车所有人
    private String fdjh;//String 发动机号
    private String clsbdm;//String 车辆识别代号
    private String syxz;//	String 使用性质
    private String jtfs;//	String 交通方式
    private String wfsj;//	Date 违法时间
    private String xzqh;//	String 行政区划　
    private String wfdd;//	String 违法地点
    private String ldhm;//	String 路段号码
    private String ddms;//	String 地点米数
    private String wfdz;//	String 违法地址
    private String wfxw1;//	String 违法行为
    private String scz1;//	String 实测值
    private String bzz1;//	String 标准值
    private String wfxw2;//	String 违法行为2
    private String scz2;//	String 实测值2
    private String bzz2;//	String 标准值2
    private String wfxw3;//	String 违法行为3
    private String scz3;//	String 实测值3
    private String bzz3;//	String 标准值3
    private String wfxw4;//String 违法行为4
    private String scz4;//String 实测值4
    private String bzz4;//String 标准值4
    private String wfxw5;//String 违法行为5
    private String scz5;//String 实测值5
    private String bzz5;//String 标准值5
    private String zqmj;//String 执勤民警
    private String fxjg;//String 发现机关
    private String jsjqbj;//String拒收拒签标记
    private String sgdj;//String 事故等级
    private String mjyj;//String 民警意见
    private String jd;//String 经度
    private String wd;//String 纬度
    private String pdaid;//String PDA机号
    private String yl1;//String 预留1(六合一返回错误信息)
    private String yl2;//String 预留2(停车场)
    private String yl3;//String 预留3
    private String yl4;//String 预留4(备注)
    private String wtbj;//String 委托标记
    private String wtbh;//String 委托编号
    private String wtbz;//String 委托备注
    private String zxbh;//String 证芯编号
    private String sfzpry;//	String 是否指导人员
    private String xcfw;//	String 校车服务
    private String zjmc;//String 证件名称
    private int zpsl;//	Int 照片数量
    private String kdsj;//	Date 开单时间

    public int getLsh() {
        return lsh;
    }

    public void setLsh(int lsh) {
        this.lsh = lsh;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }

    public String getZdsj() {
        return zdsj;
    }

    public void setZdsj(String zdsj) {
        this.zdsj = zdsj;
    }

    public String getYwzl() {
        return ywzl;
    }

    public void setYwzl(String ywzl) {
        this.ywzl = ywzl;
    }

    public String getPdams() {
        return pdams;
    }

    public void setPdams(String pdams) {
        this.pdams = pdams;
    }

    public String getPzbh() {
        return pzbh;
    }

    public void setPzbh(String pzbh) {
        this.pzbh = pzbh;
    }

    public String getWslb() {
        return wslb;
    }

    public void setWslb(String wslb) {
        this.wslb = wslb;
    }

    public String getQzcslx() {
        return qzcslx;
    }

    public void setQzcslx(String qzcslx) {
        this.qzcslx = qzcslx;
    }

    public String getKlwpcfd() {
        return klwpcfd;
    }

    public void setKlwpcfd(String klwpcfd) {
        this.klwpcfd = klwpcfd;
    }

    public String getSjxm() {
        return sjxm;
    }

    public void setSjxm(String sjxm) {
        this.sjxm = sjxm;
    }

    public String getSjwpmc() {
        return sjwpmc;
    }

    public void setSjwpmc(String sjwpmc) {
        this.sjwpmc = sjwpmc;
    }

    public String getSjwpcfd() {
        return sjwpcfd;
    }

    public void setSjwpcfd(String sjwpcfd) {
        this.sjwpcfd = sjwpcfd;
    }

    public String getRyfl() {
        return ryfl;
    }

    public void setRyfl(String ryfl) {
        this.ryfl = ryfl;
    }

    public String getJszh() {
        return jszh;
    }

    public void setJszh(String jszh) {
        this.jszh = jszh;
    }

    public String getDabh() {
        return dabh;
    }

    public void setDabh(String dabh) {
        this.dabh = dabh;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getZjcx() {
        return zjcx;
    }

    public void setZjcx(String zjcx) {
        this.zjcx = zjcx;
    }

    public String getDsr() {
        return dsr;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    public String getZsxzqh() {
        return zsxzqh;
    }

    public void setZsxzqh(String zsxzqh) {
        this.zsxzqh = zsxzqh;
    }

    public String getZsxxdz() {
        return zsxxdz;
    }

    public void setZsxxdz(String zsxxdz) {
        this.zsxxdz = zsxxdz;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getClfl() {
        return clfl;
    }

    public void setClfl(String clfl) {
        this.clfl = clfl;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getJdcsyr() {
        return jdcsyr;
    }

    public void setJdcsyr(String jdcsyr) {
        this.jdcsyr = jdcsyr;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    public String getClsbdm() {
        return clsbdm;
    }

    public void setClsbdm(String clsbdm) {
        this.clsbdm = clsbdm;
    }

    public String getSyxz() {
        return syxz;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz;
    }

    public String getJtfs() {
        return jtfs;
    }

    public void setJtfs(String jtfs) {
        this.jtfs = jtfs;
    }

    public String getWfsj() {
        return wfsj;
    }

    public void setWfsj(String wfsj) {
        this.wfsj = wfsj;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getWfdd() {
        return wfdd;
    }

    public void setWfdd(String wfdd) {
        this.wfdd = wfdd;
    }

    public String getLdhm() {
        return ldhm;
    }

    public void setLdhm(String ldhm) {
        this.ldhm = ldhm;
    }

    public String getDdms() {
        return ddms;
    }

    public void setDdms(String ddms) {
        this.ddms = ddms;
    }

    public String getWfdz() {
        return wfdz;
    }

    public void setWfdz(String wfdz) {
        this.wfdz = wfdz;
    }

    public String getWfxw1() {
        return wfxw1;
    }

    public void setWfxw1(String wfxw1) {
        this.wfxw1 = wfxw1;
    }

    public String getScz1() {
        return scz1;
    }

    public void setScz1(String scz1) {
        this.scz1 = scz1;
    }

    public String getBzz1() {
        return bzz1;
    }

    public void setBzz1(String bzz1) {
        this.bzz1 = bzz1;
    }

    public String getWfxw2() {
        return wfxw2;
    }

    public void setWfxw2(String wfxw2) {
        this.wfxw2 = wfxw2;
    }

    public String getScz2() {
        return scz2;
    }

    public void setScz2(String scz2) {
        this.scz2 = scz2;
    }

    public String getBzz2() {
        return bzz2;
    }

    public void setBzz2(String bzz2) {
        this.bzz2 = bzz2;
    }

    public String getWfxw3() {
        return wfxw3;
    }

    public void setWfxw3(String wfxw3) {
        this.wfxw3 = wfxw3;
    }

    public String getScz3() {
        return scz3;
    }

    public void setScz3(String scz3) {
        this.scz3 = scz3;
    }

    public String getBzz3() {
        return bzz3;
    }

    public void setBzz3(String bzz3) {
        this.bzz3 = bzz3;
    }

    public String getWfxw4() {
        return wfxw4;
    }

    public void setWfxw4(String wfxw4) {
        this.wfxw4 = wfxw4;
    }

    public String getScz4() {
        return scz4;
    }

    public void setScz4(String scz4) {
        this.scz4 = scz4;
    }

    public String getBzz4() {
        return bzz4;
    }

    public void setBzz4(String bzz4) {
        this.bzz4 = bzz4;
    }

    public String getWfxw5() {
        return wfxw5;
    }

    public void setWfxw5(String wfxw5) {
        this.wfxw5 = wfxw5;
    }

    public String getScz5() {
        return scz5;
    }

    public void setScz5(String scz5) {
        this.scz5 = scz5;
    }

    public String getBzz5() {
        return bzz5;
    }

    public void setBzz5(String bzz5) {
        this.bzz5 = bzz5;
    }

    public String getZqmj() {
        return zqmj;
    }

    public void setZqmj(String zqmj) {
        this.zqmj = zqmj;
    }

    public String getFxjg() {
        return fxjg;
    }

    public void setFxjg(String fxjg) {
        this.fxjg = fxjg;
    }

    public String getJsjqbj() {
        return jsjqbj;
    }

    public void setJsjqbj(String jsjqbj) {
        this.jsjqbj = jsjqbj;
    }

    public String getSgdj() {
        return sgdj;
    }

    public void setSgdj(String sgdj) {
        this.sgdj = sgdj;
    }

    public String getMjyj() {
        return mjyj;
    }

    public void setMjyj(String mjyj) {
        this.mjyj = mjyj;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getPdaid() {
        return pdaid;
    }

    public void setPdaid(String pdaid) {
        this.pdaid = pdaid;
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1;
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2;
    }

    public String getYl3() {
        return yl3;
    }

    public void setYl3(String yl3) {
        this.yl3 = yl3;
    }

    public String getYl4() {
        return yl4;
    }

    public void setYl4(String yl4) {
        this.yl4 = yl4;
    }

    public String getWtbj() {
        return wtbj;
    }

    public void setWtbj(String wtbj) {
        this.wtbj = wtbj;
    }

    public String getWtbh() {
        return wtbh;
    }

    public void setWtbh(String wtbh) {
        this.wtbh = wtbh;
    }

    public String getWtbz() {
        return wtbz;
    }

    public void setWtbz(String wtbz) {
        this.wtbz = wtbz;
    }

    public String getZxbh() {
        return zxbh;
    }

    public void setZxbh(String zxbh) {
        this.zxbh = zxbh;
    }

    public String getSfzpry() {
        return sfzpry;
    }

    public void setSfzpry(String sfzpry) {
        this.sfzpry = sfzpry;
    }

    public String getXcfw() {
        return xcfw;
    }

    public void setXcfw(String xcfw) {
        this.xcfw = xcfw;
    }

    public String getZjmc() {
        return zjmc;
    }

    public void setZjmc(String zjmc) {
        this.zjmc = zjmc;
    }

    public int getZpsl() {
        return zpsl;
    }

    public void setZpsl(int zpsl) {
        this.zpsl = zpsl;
    }

    public String getKdsj() {
        return kdsj;
    }

    public void setKdsj(String kdsj) {
        this.kdsj = kdsj;
    }
}
