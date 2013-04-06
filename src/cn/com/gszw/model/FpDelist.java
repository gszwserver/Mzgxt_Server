package cn.com.gszw.model;

public class FpDelist {
	public String getFpmc() {
		return fpmc;
	}
	public void setFpmc(String fpmc) {
		this.fpmc = fpmc;
	}
	public String getFp_dm() {
		return fp_dm;
	}
	public void setFp_dm(String fp_dm) {
		this.fp_dm = fp_dm;
	}
	public String getFp_qshm() {
		return fp_qshm;
	}
	public void setFp_qshm(String fp_qshm) {
		this.fp_qshm = fp_qshm;
	}
	public String getFp_zzhm() {
		return fp_zzhm;
	}
	public void setFp_zzhm(String fp_zzhm) {
		this.fp_zzhm = fp_zzhm;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getFcjg_dm() {
		return fcjg_dm;
	}
	public void setFcjg_dm(String fcjg_dm) {
		this.fcjg_dm = fcjg_dm;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getNsrbm() {
		return nsrbm;
	}
	public void setNsrbm(String nsrbm) {
		this.nsrbm = nsrbm;
	}
	public String getFpmm() {
		return fpmm;
	}
	public void setFpmm(String fpmm) {
		this.fpmm = fpmm;
	}
	private String fpmc;//发票名称
	private String fp_dm;//发票代码
	private String fp_qshm;//起始号码
	private String fp_zzhm;//终止号码
	private String rq;//发票发售日期
	private String fcjg_dm;//发出机关名称
	private String nsrmc;//纳税人名称
	private String nsrbm;//纳税人编码
	private String fpmm;//发票密码是否验证通过
	
	
	
}
