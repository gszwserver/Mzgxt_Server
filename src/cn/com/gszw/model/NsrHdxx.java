package cn.com.gszw.model;
/**
 * 纳税人核定税收
 * @author wang
 *
 */
public class NsrHdxx {
	private String zsxm;//征收项目
	private String zspm;//征收品目
	private String zsfs;//征收方式
	private String jsje;// 计税金额
	private String hdqsrq;//核定起始日期
	private String hdzzrq;//核定终止日期
	private String ynse;//应纳金额
	private String nsqx;//纳税期限
	private String sbqx;//申报期限
	private String jkqx;//缴款期限
	private String sl;//税率
	private String ysfpbl;//预算分配比例
	private String gkid;//国库

	public String getZsxm() {
		return zsxm;
	}
	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}
	public String getZspm() {
		return zspm;
	}
	public void setZspm(String zspm) {
		this.zspm = zspm;
	}
	public String getZsfs() {
		return zsfs;
	}
	public void setZsfs(String zsfs) {
		this.zsfs = zsfs;
	}

	public String getHdqsrq() {
		return hdqsrq;
	}
	public void setHdqsrq(String hdqsrq) {
		this.hdqsrq = hdqsrq;
	}
	public String getHdzzrq() {
		return hdzzrq;
	}
	public void setHdzzrq(String hdzzrq) {
		this.hdzzrq = hdzzrq;
	}

	public String getYnse() {
		return ynse;
	}
	public void setYnse(String ynse) {
		this.ynse = ynse;
	}
	public String getNsqx() {
		return nsqx;
	}
	public void setNsqx(String nsqx) {
		this.nsqx = nsqx;
	}
	public String getSbqx() {
		return sbqx;
	}
	public void setSbqx(String sbqx) {
		this.sbqx = sbqx;
	}
	public String getJkqx() {
		return jkqx;
	}
	public void setJkqx(String jkqx) {
		this.jkqx = jkqx;
	}

	public String getJsje() {
		return jsje;
	}
	public void setJsje(String jsje) {
		this.jsje = jsje;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getYsfpbl() {
		return ysfpbl;
	}
	public void setYsfpbl(String ysfpbl) {
		this.ysfpbl = ysfpbl;
	}
	public String getGkid() {
		return gkid;
	}
	public void setGkid(String gkid) {
		this.gkid = gkid;
	}
	
}
