package cn.com.gszw.model;

public class SwjgList {
	public String getSwjg_dm() {
		return swjg_dm;
	}
	public void setSwjg_dm(String swjg_dm) {
		this.swjg_dm = swjg_dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getSjswjg_dm() {
		return sjswjg_dm;
	}
	public void setSjswjg_dm(String sjswjg_dm) {
		this.sjswjg_dm = sjswjg_dm;
	}
	public String getSsjswjg_dm() {
		return ssjswjg_dm;
	}
	public void setSsjswjg_dm(String ssjswjg_dm) {
		this.ssjswjg_dm = ssjswjg_dm;
	}
	private String swjg_dm;//税务机关代码
	private String mc;//税务机关名称
	private String sjswjg_dm;//上级税务机关代码
	private String ssjswjg_dm;//上上级税务机关代码
	

}
