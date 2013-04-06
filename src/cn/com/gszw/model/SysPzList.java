package cn.com.gszw.model;

public class SysPzList {
	public String getPzzl_dm() {
		return pzzl_dm;
	}
	public void setPzzl_dm(String pzzl_dm) {
		this.pzzl_dm = pzzl_dm;
	}
	public String getPzzl_mc() {
		return pzzl_mc;
	}
	public void setPzzl_mc(String pzzl_mc) {
		this.pzzl_mc = pzzl_mc;
	}
	public String getPzzb_dm() {
		return pzzb_dm;
	}
	public void setPzzb_dm(String pzzb_dm) {
		this.pzzb_dm = pzzb_dm;
	}
	public String getPzzb_mc() {
		return pzzb_mc;
	}
	public void setPzzb_mc(String pzzb_mc) {
		this.pzzb_mc = pzzb_mc;
	}
	public String getPzhm() {
		return pzhm;
	}
	public void setPzhm(String pzhm) {
		this.pzhm = pzhm;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getKpjg() {
		return kpjg;
	}
	public void setKpjg(String kpjg) {
		this.kpjg = kpjg;
	}
	private String kpjg;//开票机关
	private String pzzl_dm;//票证种类代码
	private String pzzl_mc;//票证种类名称
	private String pzzb_dm;//票证字别代码
	private String pzzb_mc;//票证字别名称
	private String pzhm;//票证号码
	private String je;//开票金额

}
