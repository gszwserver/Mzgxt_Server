package cn.com.gszw.model;
/**
 * 纳税人银行信息
 * @author wang
 *
 */
public class NsrYh {
	private String hb;//银行行别
	private String khhmc;//开户行名称
	private String zh;//银行帐号
	private String zhbj;//帐号标记
	private String kh_rq;//开户日期
	private String nsrkh_mc;//纳税人开户行名称
	private String xy;//选用标记
	
	public String getHb() {
		return hb;
	}
	public void setHb(String hb) {

			this.hb = hb;	


	}
	public String getKhhmc() {
		return khhmc;
	}
	public void setKhhmc(String khhmc) {
		this.khhmc = khhmc;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getZhbj() {
		return zhbj;
	}
	public void setZhbj(String zhbj) {
		this.zhbj = zhbj;
	}
	public String getKh_rq() {
		return kh_rq;
	}
	public void setKh_rq(String kh_rq) {
		this.kh_rq = kh_rq;
	}
	public String getNsrkh_mc() {
		return nsrkh_mc;
	}
	public void setNsrkh_mc(String nsrkh_mc) {
		this.nsrkh_mc = nsrkh_mc;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}

}
