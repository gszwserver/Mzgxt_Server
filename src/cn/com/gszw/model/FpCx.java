package cn.com.gszw.model;

import java.util.List;



public class FpCx {


	public String getFp_dm() {
		return fp_dm;
	}

	public void setFp_dm(String fp_dm) {
		this.fp_dm = fp_dm;
	}

	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getFpzl() {
		return fpzl;
	}

	public void setFpzl(String fpzl) {
		this.fpzl = fpzl;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public FpDelist getDe() {
		return de;
	}

	public void setDe(FpDelist de) {
		this.de = de;
	}

	public Fpfplist getJkp() {
		return jkp;
	}

	public void setJkp(Fpfplist jkp) {
		this.jkp = jkp;
	}

	public List<Fplist> getFplx() {
		return fplx;
	}

	public void setFplx(List<Fplist> fplx) {
		this.fplx = fplx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	String fp_dm;
	String fphm;
	String num;
	String fpzl;//发票种类标志
	int flag;//查询结果标志  

	//1 发票存在并领购至纳税人；需要展示DE类中的信息
	//2 系统中存在发票开票明细信息；需要展示JKP类中的信息
	//3发票在税务机关调拨过程中，还没有开具，需要展示FPLX类中的信息
	//0发票验证不存在；直接显示bz信息就可以了
	//9 发票代码不存在；直接显示bz信息就可以了
	FpDelist de;//定额票信息和纳税人购票信息
	Fpfplist jkp;//机开发票信息
	List<Fplist> fplx;//发票流向信息
	
	String bz;//查询备注信息

}
