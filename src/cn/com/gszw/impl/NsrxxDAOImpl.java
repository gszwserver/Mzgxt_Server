package cn.com.gszw.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.gszw.dao.NsrxxDAO;
import cn.com.gszw.model.FpCx;
import cn.com.gszw.model.FpDelist;
import cn.com.gszw.model.Fpfplist;
import cn.com.gszw.model.Fplist;
import cn.com.gszw.model.NsrDefx;
import cn.com.gszw.model.NsrDfsxx;
import cn.com.gszw.model.NsrFpfx;
import cn.com.gszw.model.NsrFphd;
import cn.com.gszw.model.NsrFpjc;
import cn.com.gszw.model.NsrHdxx;
import cn.com.gszw.model.NsrJbzq;
import cn.com.gszw.model.NsrJkxx;
import cn.com.gszw.model.NsrJkxxmx;
import cn.com.gszw.model.NsrJmxx;
import cn.com.gszw.model.NsrList;
import cn.com.gszw.model.NsrSb;
import cn.com.gszw.model.NsrSbmx;
import cn.com.gszw.model.NsrTfyxx;
import cn.com.gszw.model.NsrYh;
import cn.com.gszw.model.Nsrxx;
import cn.com.gszw.model.PubView;
import cn.com.gszw.model.Rkfx_jc;
import cn.com.gszw.model.SysPzList;
import cn.com.gszw.model.main;
import cn.com.gszw.util.DBUtil;
import cn.com.gszw.util.MD5EncryptUtils;
import cn.com.gszw.util.Public_Date;
import cn.com.gszw.util.SQLHelper;

/**
 * 
 * @author xiaobo
 * @param <Public_Date>
 * 
 */
public class NsrxxDAOImpl implements NsrxxDAO {

	// 第页条数
	int PageSize = 7;
	private String sql;

	@Override
	public String getNsrxxTotal(String nsrmc) {
		// TODO Auto-generated method stub
		sql = "select count(*) num from t_dj_nsrxx where nsr_mc like '%"
				+ nsrmc + "%'";
		String number = null;
		try {

			ResultSet rs = SQLHelper.getResultSet(sql);
			if (rs!=null&&rs.next()) {
				number = rs.getString("num");
				return number;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	// 查询纳税人基本信息
	@Override
	public Nsrxx getNsrBasicInfo(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "seLECT TO_CHAR(NSRKZ.KYDJRQ, 'YYYY-MM-DD') kyrq, "
				+ " xx.NSRSBM nsrsbm, " + "  xx.NSR_MC nsrmc, "
				+ " NSRKZ.FDDBR fddbr, " + " xx.ZZJG_DM zzjgdm, "
				+ " djzclx.mc djlx, " + " zt.mc djzt, " + "  zclx.mc zclx, "
				+ "  lsgx.mc lsgx, " + "  hy.mc hy, " + "  fs.mc sbfs, "
				+ "  zsfs.mc zsfs, " + "  jd.mc xzjd, " + "  jg.mc gljg, "
				+ "  zgy.mc zgy, " + "  NSRKZ.SCJYDZ scjydz, "
				+ " NSRKZ.ZC_DZ zcdz, " + " NSRKZ.ZCDZDH zcddh, "
				+ " NSRKZ.JYDZDH jyddh, " + " NSRKZ.CYRS cyrs, "
				+ " NSRKZ.ZCZB zczb, " + " NSRKZ.FRDB_ZJHM frzjh, "
				+ " NSRKZ.FRYDDH frdh, " + "  NSRKZ.CWFZR cwfzr, "
				+ " NSRKZ.CWFZRYDDH cwfzrdh, " + " NSRKZ.BSY_MC bsr, "
				+ " NSRKZ.BSYYDDH bsrdh, " + " NSRKZ.SWDLRMC swdlrmc, "
				+ " NSRKZ.SWDLRLXDH swdlrdh " + " FROM T_DJ_NSRXX       XX, "
				+ "   T_DJ_JGNSR_KZ    NSRKZ, " + "  t_dm_gy_wsspxmxl djzclx, "
				+ "  t_dm_gy_nsrzt    zt, " + "  t_dm_gy_djzclx   zclx, "
				+ "  t_dm_gy_lsgx     lsgx, " + "  t_dm_gy_hy       hy, "
				+ " t_dm_gy_sbfs     fs, " + " t_dm_gy_zsfs     zsfs, "
				+ "  t_dm_gy_xzjd     jd, " + " t_dm_gy_swry     zgy, "
				+ " t_dm_gy_swjg     jg "
				+ " WHERE xx.NSRNBM = NSRKZ.NSRNBM(+) "
				+ " and xx.gljg_dm = jg.swjg_dm "
				+ " and xx.djlx_dm = djzclx.wsspxmxl_dm(+) "
				+ " and xx.zgy_dm = zgy.swry_dm(+) "
				+ " and xx.xzjd_dm = jd.xzjd_dm(+) "
				+ " and xx.dj_zt = zt.nsrzt_dm "
				+ " and xx.zclx_dm = zclx.djzclx_dm "
				+ " and xx.lsgx_dm = lsgx.lsgx_dm "
				+ " and xx.hy_dm = hy.hy_dm "
				+ " and xx.sbfs_dm = fs.sbfs_dm(+) "
				+ " and xx.zsfs_dm = zsfs.zsfs_dm(+) " + " and xx.nsrnbm = ? ";
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm);
			if (rs!=null&&rs.next()) {
				Nsrxx nsrxx = new Nsrxx();
				nsrxx.setKyrq(rs.getString("kyrq"));
				nsrxx.setNsrsbm(rs.getString("nsrsbm"));
				nsrxx.setNsrmc(rs.getString("nsrmc"));
				nsrxx.setFddbr(rs.getString("fddbr"));
				nsrxx.setZzjgdm(rs.getString("zzjgdm"));
				nsrxx.setDjlx(rs.getString("djlx"));
				nsrxx.setDjzt(rs.getString("djzt"));
				nsrxx.setZclx(rs.getString("zclx"));
				nsrxx.setLsgx(rs.getString("lsgx"));
				nsrxx.setHy(rs.getString("hy"));
				nsrxx.setSbfs(rs.getString("sbfs"));
				nsrxx.setZsfs(rs.getString("zsfs"));
				nsrxx.setXzjd(rs.getString("xzjd"));
				nsrxx.setGljg(rs.getString("gljg"));
				nsrxx.setZgy(rs.getString("zgy"));

				nsrxx.setScjydz(rs.getString("scjydz"));
				nsrxx.setZcdz(rs.getString("zcdz"));
				nsrxx.setZcddh(rs.getString("zcddh"));
				nsrxx.setJyddh(rs.getString("jyddh"));
				nsrxx.setCyrs(rs.getString("cyrs"));
				nsrxx.setZczb(rs.getString("zczb"));
				nsrxx.setFrzjh(rs.getString("frzjh"));
				nsrxx.setFrdh(rs.getString("frdh"));

				nsrxx.setCwfzr(rs.getString("cwfzr"));
				nsrxx.setCwfzrdh(rs.getString("cwfzrdh"));
				nsrxx.setBsr(rs.getString("bsr"));
				nsrxx.setBsrdh(rs.getString("bsrdh"));
				nsrxx.setSwdlrmc(rs.getString("swdlrmc"));
				nsrxx.setSwdlrdh(rs.getString("swdlrdh"));
				return nsrxx;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 根据纳税人内部码，返回该纳税人银行信息
	// 王朝玉增加
	@Override
	public List<NsrYh> getNsrYList(String nsrnbm) {
		// TODO Auto-generated method stub

		sql = "select zl.mc hb,\n" + "    yh.mc khhmc,\n"
				+ "    zh.yh_zh zh,\n" + "    bj.mc zhbj,\n"
				+ "    to_char(zh.kh_rq,'yyyy-mm-dd') kh_rq,\n"
				+ "    zh.nsrkh_mc,\n"
				+ "    decode(zh.xy_bj,'0','未选用','选用') xy\n"
				+ "from t_dj_nsryhzh zh,\n" + " t_dm_gy_yhzl zl,\n"
				+ " t_dm_gy_yh yh,\n" + " T_DM_DJ_YHZHBZ bj\n"
				+ "where zh.yhhb_dm=zl.yhzl_dm\n" + "and zh.yh_dm=yh.yh_dm\n"
				+ "and zh.yhhb_dm=yh.yhzl_dm\n" + "and zh.zh_bj=bj.yhzhbz_dm\n"
				+ "and zh.nsrnbm=?";

		List<NsrYh> nsryhList = new ArrayList<NsrYh>();
		// nsryhList=null;
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm);
			while (rs!=null&&rs.next()) {
				NsrYh Nsryh = new NsrYh();
				Nsryh.setHb(NullToString(rs.getString("hb")));
				Nsryh.setKhhmc(NullToString(rs.getString("khhmc")));
				Nsryh.setZh(NullToString(rs.getString("zh")));
				Nsryh.setZhbj(NullToString(rs.getString("zhbj")));
				Nsryh.setKh_rq(NullToString(rs.getString("kh_rq")));
				Nsryh.setNsrkh_mc(NullToString(rs.getString("nsrkh_mc")));
				Nsryh.setXy(NullToString(rs.getString("xy")));
				nsryhList.add(Nsryh);
			}
			return nsryhList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return nsryhList;
	}

	// 根据某条件，返回某页查询纳税人信息，多条
	@Override
	public List<NsrList> getMainList(String sqlstr, int PageNumber) {
		// TODO Auto-generated method stub

		String str = null;
		str = "select rownum r,\n" + "  xx.nsrbm,\n" + "    xx.nsrnbm,\n"
				+ "    xx.nsr_mc,\n" + "   zt.mc zt_dm,\n"
				+ "    decode(wsb.nsrnbm,null,'已申报','未申报') sbxx,\n"
				+ "       xx.gljg_dm,\n" + "      xx.zgy_dm\n"
				+ "from t_dj_nsrxx xx,\n" + "T_DJ_JGNSR_KZ kz,\n"
				+ "t_dm_gy_nsrzt zt,\n"
				+ "(select distinct nsrnbm from t_gs_sb_wsbsj_h) wsb\n"
				+ "where xx.dj_zt=zt.nsrzt_dm\n"
				+ "and xx.nsrnbm=kz.nsrnbm(+)\n"
				+ "and xx.nsrnbm=wsb.nsrnbm(+)";

		str = str + " and " + sqlstr;
		String cmdTxt = null;
		cmdTxt = "Select * From (" + str + ") Where r>" + (PageNumber - 1)
				* PageSize + " And r<=" + PageNumber * PageSize;

		List<NsrList> nsrxxList = new ArrayList<NsrList>();
		try {
			ResultSet rs = SQLHelper.getResultSet(cmdTxt);
			while (rs!=null&&rs.next()) {
				NsrList nsrxx = new NsrList();
				nsrxx.setNsrbm(NullToString(rs.getString("nsrbm")));
				nsrxx.setNsrmc(NullToString(rs.getString("nsr_mc")));
				nsrxx.setNsrnbm(NullToString(rs.getString("nsrnbm")));
				nsrxx.setDjzt(NullToString(rs.getString("zt_dm")));
				nsrxx.setGljg(NullToString(rs.getString("gljg_dm")));
				nsrxx.setZgy(NullToString(rs.getString("zgy_dm")));
				nsrxx.setSbqk(NullToString(rs.getString("sbxx")));
				nsrxxList.add(nsrxx);
			}
			return nsrxxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return nsrxxList;
	}

	// 根据某条件，返回某页查询纳税人总条数
	@Override
	public List<String> getMainCount(String sqlstr) {
		// TODO Auto-generated method stub

		String str = null;

		str = "select count(1) num01\n" + "from t_dj_nsrxx xx,\n"
				+ "T_DJ_JGNSR_KZ kz,\n" + "t_dm_gy_nsrzt zt,\n"
				+ "(select distinct nsrnbm from t_gs_sb_wsbsj_h) wsb\n"
				+ "where xx.dj_zt=zt.nsrzt_dm\n"
				+ "and xx.nsrnbm=kz.nsrnbm(+)\n"
				+ "and xx.nsrnbm=wsb.nsrnbm(+)";

		str = str + " and " + sqlstr;

		List<String> nsrxxList = new ArrayList<String>();
		try {
			ResultSet rs = SQLHelper.getResultSet(str);
			while (rs!=null&&rs.next()) {
				nsrxxList.add(rs.getString("num01"));
				int i = Integer.parseInt(rs.getString("num01")) / PageSize + 1;
				nsrxxList.add(String.valueOf(i));
			}
			return nsrxxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return nsrxxList;
	}

	// 根据手机号返回税务人员编码，如果为-１则表示该手机号不存在
	@Override
	public String getSjNum(String sj) {
		// TODO Auto-generated method stub
		String str = null;

		String num = "-1";
		str = "select swry_dm\n" + "from t_dm_gy_swry a\n" + "where a.sfzhm=?";

		try {
			ResultSet rs = SQLHelper.getResultSet(str, sj);
			while (rs.next()) {
				num = rs.getString("swry_dm");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return num;
	}

	// 空值转换通用，在数据表中有null值的时候，前台显示有问题，建议写入JAVAＢeen的时候调用此方法转换
	// 王朝玉增加
	private String NullToString(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}

	}

	// 将字符串第二位后的字符用符号替换
	// 王朝玉增加
	private String StringSub(String str) {
		if (str == null) {
			return "";
		} else {
			str = str.substring(0, 2) + "◆◆◆◆◆";
			return str;
		}

	}

	// 根据纳税人内部码，返回该纳税人税种核定信息
	@Override
	public List<NsrHdxx> getNsrHdxxList(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "select xm.mc zsxm, "
				+ " pm.mc zspm, "
				+ "  zs.jsje jsje, "
				+ "  zs.sl sl, "
				+ "  zs.ynse ynse, "
				+ "  qx.mc sbqx, "
				+ "  jk.mc jkqx, "
				+ "  ns.mc nsqx, "
				+ "  fs.mc zsfs, "
				+ "  zs.hd_qsrq hdqsrq, "
				+ "  zs.sj_zzrq hdzzrq, "
				+ "  km.mc ysfpbl, "
				+ "  gk.zh_mc gkid "
				+ " from t_hd_yjszsx    zs, "
				+ "  t_dm_gy_zsxm   xm, "
				+ "  t_dm_gy_zspm   pm, "
				+ "  t_dm_hd_sbqx   qx, "
				+ "  t_dm_hd_jkqx   jk, "
				+ "  t_dm_gy_nsqx   ns, "
				+ "  t_dm_gy_zsfs   fs, "
				+ "  t_dm_gy_ysfpbl km, "
				+ "  t_qs_skzh      gk "
				+ " where zs.zsxm_dm = xm.zsxm_dm "
				+ "  and zs.zsxm_dm = pm.zsxm_dm "
				+ " and zs.zspm_dm = pm.zspm_dm "
				+ " and zs.sbqx_dm = qx.sbqx_dm "
				+ " and zs.jkqx_dm = jk.jkqx_dm "
				+ " and zs.nsqx_dm = ns.nsqx_dm "
				+ " and zs.zsfs_dm = fs.zsfs_dm "
				+ " and zs.fpbl_dm = km.ysfpbl_dm "
				+ " and zs.gkid = gk.skzhid "
				+ " AND NVL(ZS.SJ_ZZRQ, SYSDATE + 9999) > TRUNC(SYSDATE, 'MM') - 1 "
				+ " and nsrnbm = ? "
				+ " union all "
				+ " select xm.mc zsxm, "
				+ " pm.mc zspm, "
				+ "  decode(fs.sl, 0, 0, fs.ynse / fs.sl) jsje, "
				+ "  fs.sl sl, "
				+ "  fs.ynse ynse, "
				+ "  '' sbqx, "
				+ "  '' jkqx, "
				+ "  '' nsqx, "
				+ "  '' zsfs, "
				+ "  zs.hd_qsrq hdqsrq, "
				+ "  zs.sj_zzrq hdzzrq, "
				+ "  km.mc ysfpbl, "
				+ "  '' gkid "
				+ " from t_hd_fs        fs, "
				+ "  t_hd_yjszsx    zs, "
				+ "  t_dm_gy_zsxm   xm, "
				+ "  t_dm_gy_ysfpbl km, "
				+ "  t_dm_gy_zspm   pm "
				+ " where fs.zsxm_dm = xm.zsxm_dm "
				+ " and fs.zsxm_dm = pm.zsxm_dm "
				+ " and fs.zspm_dm = pm.zspm_dm "
				+ " and fs.fpbl_dm = km.ysfpbl_dm "
				+ " and zs.nsrnbm = fs.nsrnbm "
				+ " and fs.zszsxm_dm = zs.zsxm_dm "
				+ " and (fs.zszspm_dm is null or fs.zszspm_dm = zs.zspm_dm) "
				+ " AND NVL(ZS.SJ_ZZRQ, SYSDATE + 9999) > TRUNC(SYSDATE, 'MM') - 1 "
				+ " and fs.nsrnbm = ? ";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm, nsrnbm);
			List<NsrHdxx> hdxxList = new ArrayList<NsrHdxx>();
			while (rs!=null&&rs.next()) {
				NsrHdxx hdxx = new NsrHdxx();
				hdxx.setZsxm(rs.getString("zsxm"));
				hdxx.setZspm(rs.getString("zspm"));
				hdxx.setZsfs(rs.getString("zsfs"));
				hdxx.setJsje(rs.getString("jsje"));
				hdxx.setSl(rs.getString("sl"));
				hdxx.setSbqx(rs.getString("sbqx"));
				hdxx.setNsqx(rs.getString("nsqx"));
				hdxx.setJkqx(rs.getString("jkqx"));
				hdxx.setYnse(rs.getString("ynse"));
				hdxx.setHdqsrq(rs.getString("hdqsrq"));
				hdxx.setHdzzrq(rs.getString("hdzzrq"));
				hdxx.setYsfpbl(rs.getString("ysfpbl"));
				hdxx.setGkid(rs.getString("gkid"));
				hdxxList.add(hdxx);
			}
			return hdxxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 纳税人停复业信息
	@Override
	public List<NsrTfyxx> getNsrTfyxx(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "SELECT DECODE(NSR.NSRBM, NULL, '--- ---', NSR.NSRBM) NSRBM,"
				+ " DECODE(TFY.HZTY_QSRQ, " + "        NULL, "
				+ "        '--- ---', "
				+ "        TO_CHAR(TFY.HZTY_QSRQ, 'YYYY-MM-DD')) TYQSRQ, "
				+ " DECODE(TFY.HZTY_ZZRQ, " + "        NULL, "
				+ "        '--- ---', "
				+ "        TO_CHAR(TFY.HZTY_ZZRQ, 'YYYY-MM-DD')) TYZZRQ, "
				+ " DECODE(TFY.SJFY_RQ, " + "        NULL, "
				+ "        '--- ---', "
				+ "        TO_CHAR(TFY.SJFY_RQ, 'YYYY-MM-DD')) SJFYRQ, "
				+ " NVL(DECODE(TFY.ZDFY_BZ, " + "            '1', "
				+ "            '自动复业', " + "            '0', "
				+ "            '非自动复业', " + "            NULL, "
				+ "            '非自动复业'), " + "     '--- ---') AS FYLX "
				+ " FROM T_DJ_TFYXX TFY, T_DJ_NSRXX NSR "
				+ " WHERE TFY.NSRNBM(+) = NSR.NSRNBM "
				+ " and to_char(tfy.hzty_qsrq,'yyyy')=to_char(sysdate,'yyyy') "
				+ " AND NSR.NSRNBM ='" + nsrnbm + "'";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql);
			List<NsrTfyxx> tfyxxList = new ArrayList<NsrTfyxx>();
			while (rs!=null&&rs.next()) {
				NsrTfyxx tfyxx = new NsrTfyxx();
				tfyxx.setNsrbm(rs.getString("nsrbm"));
				tfyxx.setTyqsrq(rs.getString("TYQSRQ"));
				tfyxx.setTyzzrq(rs.getString("TYZZRQ"));
				tfyxx.setSjfyrq(rs.getString("SJFYRQ"));
				tfyxx.setFylx(rs.getString("FYLX"));
				tfyxxList.add(tfyxx);
			}
			return tfyxxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 根据某纳税人内部码，返回该纳税人本年度申报情况总表数据
	@Override
	public List<NsrSb> getSbList(String nsrnbm) {
		// TODO Auto-generated method stub

		String str = null;
		str = "select to_char(a.sb_rq,'yyyy-mm') sbrq,\n"
				+ "count( distinct a.pz_xh) sbpzs,\n"
				+ "count( distinct a.zsxm_dm) sbsz,\n"
				+ "sum(a.yzsf_je) sbje,\n"
				+ "sum(decode(a.ykp_bj,0,a.yzsf_je,0)) wkpje\n"
				+ "from t_zs_yzpz a\n" + "where a.nsrnbm=?\n"
				+ "and to_char(a.lr_sj,'yyyy')=to_char(sysdate,'yyyy')\n"
				+ "   and a.zf_bj = '0'\n" + " and a.zsxm_dm<>'01'\n"
				+ "   and a.tzlx_dm = '00'\n"
				+ "group by to_char(a.sb_rq,'yyyy-mm')\n"
				+ "order by to_char(a.sb_rq,'yyyy-mm') desc";

		List<NsrSb> sbList = new ArrayList<NsrSb>();
		try {
			ResultSet rs = SQLHelper.getResultSet(str, nsrnbm);
			while (rs!=null&&rs.next()) {
				NsrSb sb = new NsrSb();
				sb.setSbrq(NullToString(rs.getString("sbrq")));
				sb.setSbpzs(NullToString(rs.getString("sbpzs")));
				sb.setSbsz(NullToString(rs.getString("sbsz")));
				sb.setSbje(NullToString(rs.getString("sbje")));
				sb.setWkpje(NullToString(rs.getString("wkpje")));
				sbList.add(sb);
			}
			return sbList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return sbList;
	}

	// 纳税人申报明细信息
	@Override
	public List<NsrSbmx> getSbmxList(String nsrnbm, String sb_rq) {
		sql = "select to_char(sb_rq,'yyyy-mm-dd') sb_rq,\n"
				+ " a.pz_xh,\n"
				+ " to_char(a.sfssq_qsrq,'yyyy-mm-dd')||'--'||to_char(a.sfssq_zzrq,'yyyy-mm-dd') ssq,\n"
				+ " xm.mc zsxm,\n"
				+ " sum(a.yzsf_je) sbje,\n"
				+ " max(a.js_yj) jsje,\n"
				+ " sum(decode(a.ykp_bj, 0, a.yzsf_je, 0)) wkpje\n"
				+ " from t_zs_yzpz a, t_dm_gy_zsxm xm\n"
				+ " where a.zsxm_dm = xm.zsxm_dm\n"
				+ " and a.nsrnbm = ?\n"
				+ " and to_char(a.sb_rq, 'yyyy-mm') = ?\n"
				+ " and a.zf_bj = '0'\n"
				+ " and a.zsxm_dm<>'01'\n"
				+ " and a.tzlx_dm = '00'\n"
				+ " group by sb_rq,pz_xh,\n"
				+ " to_char(a.sfssq_qsrq,'yyyy-mm-dd')||'--'||to_char(a.sfssq_zzrq,'yyyy-mm-dd'), xm.mc\n"
				+ " order by sb_rq,xm.mc";

		// System.out.println(nsrnbm+sb_rq);
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm, sb_rq);
			List<NsrSbmx> sbmxList = new ArrayList<NsrSbmx>();
			while (rs!=null&&rs.next()) {
				NsrSbmx sbmx = new NsrSbmx();
				sbmx.setSb_rq(rs.getString("sb_rq"));
				sbmx.setPz_xh(rs.getString("pz_xh"));
				sbmx.setSsq(rs.getString("ssq"));
				sbmx.setJsje(rs.getString("jsje"));
				sbmx.setZsxm(rs.getString("zsxm"));
				sbmx.setSbje(rs.getString("sbje"));
				sbmx.setWkpje(rs.getString("wkpje"));
				sbmxList.add(sbmx);
			}
			return sbmxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 纳税人 缴款信息 列表
	@Override
	public List<NsrJkxx> getJkxxList(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "select rq sbrq, "
				+ " count(distinct dzsph_xh) sbpzs, "
				+ " sum(sj_je) sbsz, "
				+ " sum(rkje) sbje, "
				+ " sum(wrkje) wkpje "
				+ " from (select to_char(xj.lr_sj, 'yyyy-mm') rq, "
				+ "  xj.sj_je, "
				+ "         xj.zsxm_dm, "
				+ "         decode(zj.rk_rq, null, 0, xj.sj_je) rkje, "
				+ "         decode(zj.rk_rq, null, xj.sj_je, 0) wrkje, "
				+ "         xj.dzsph_xh "
				+ "    from t_zs_xjjkmx xj, "
				+ "         (select * "
				+ "            from t_zs_zjjkmx "
				+ "           where nsrnbm is null "
				+ "             and zf_bj = '0') zj "
				+ "   where xj.hzjk_xh = zj.jk_xh(+) "
				+ "     and xj.hzjkmx_xh = zj.jkmx_xh(+) "
				+ "     and xj.nsrnbm = ? "
				+ "           and xj.zf_bj='0'\n"
				+ "     and to_char(xj.lr_sj, 'yyyy') = to_char(sysdate, 'yyyy') "
				+ "  union all "
				+ "  select to_char(xj.lr_sj, 'yyyy-mm') rq, "
				+ "         xj.sj_je, "
				+ "         xj.zsxm_dm, "
				+ "         decode(xj.rk_rq, null, 0, xj.sj_je) rkje, "
				+ "         decode(xj.rk_rq, null, xj.sj_je, 0) wrkje, "
				+ "         xj.dzsph_xh "
				+ "    from t_zs_zjjkmx xj "
				+ "   where xj.nsrnbm = ? "
				+ "           and xj.zf_bj='0'\n"
				+ "     and to_char(xj.lr_sj, 'yyyy') = to_char(sysdate, 'yyyy')) jjj "
				+ " group by rq " + " order by rq desc";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm, nsrnbm);
			List<NsrJkxx> jkxxList = new ArrayList<NsrJkxx>();
			while (rs!=null&&rs.next()) {
				NsrJkxx jkxx = new NsrJkxx();
				jkxx.setSbrq(rs.getString("sbrq"));
				jkxx.setSbpzs(rs.getString("sbpzs"));
				jkxx.setSbsz(rs.getString("sbsz"));
				jkxx.setSbje(rs.getString("sbje"));
				jkxx.setWkpje(rs.getString("wkpje"));
				jkxxList.add(jkxx);
			}
			return jkxxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 纳税人 缴款明细 信息
	@Override
	public List<NsrJkxxmx> getJkxxmxList(String nsrnbm, String jk_rq) {
		// TODO Auto-generated method stub
		sql = "select rq jkrq,\n"
				+ "       xm.mc zsxm,\n"
				+ "       pzhm sbpzs,\n"
				+ "       sum(sj_je) kpje,\n"
				+ "       sum(rkje) rkje,\n"
				+ "       sum(wrkje) wkpje\n"
				+ "  from (select to_char(xj.lr_sj, 'yyyy-mm_dd') rq,\n"
				+ "               xj.sj_je,\n"
				+ "               xj.zsxm_dm,\n"
				+ "               decode(zj.rk_rq, null, 0, xj.sj_je) rkje,\n"
				+ "               decode(zj.rk_rq, null, xj.sj_je, 0) wrkje,\n"
				+ "               sy.pzhm\n"
				+ "          from t_zs_xjjkmx xj,\n"
				+ "               (select *\n"
				+ "                  from t_zs_zjjkmx\n"
				+ "                 where nsrnbm is null\n"
				+ "                   and zf_bj = '0') zj,\n"
				+ "               t_zs_pzsyqk sy\n"
				+ "         where xj.hzjk_xh = zj.jk_xh(+)\n"
				+ "           and xj.hzjkmx_xh = zj.jkmx_xh(+)\n"
				+ "           and xj.dzsph_xh=sy.dzsph_xh\n"
				+ "           and to_char(xj.lr_sj, 'yyyy-mm') = ?\n"
				+ "           and xj.zf_bj='0'\n"
				+ "           and xj.nsrnbm = ?\n"
				+ "           and to_char(xj.lr_sj, 'yyyy') = to_char(sysdate, 'yyyy')\n"
				+ "        union all\n"
				+ "        select to_char(xj.lr_sj, 'yyyy-mm_dd') rq,\n"
				+ "               xj.sj_je,\n"
				+ "               xj.zsxm_dm,\n"
				+ "               decode(xj.rk_rq, null, 0, xj.sj_je) rkje,\n"
				+ "               decode(xj.rk_rq, null, xj.sj_je, 0) wrkje,\n"
				+ "               sy.pzhm\n"
				+ "          from t_zs_zjjkmx xj,\n"
				+ "               t_zs_pzsyqk sy\n"
				+ "         where xj.nsrnbm = ?\n"
				+ "           and xj.dzsph_xh=sy.dzsph_xh\n"
				+ "           and xj.zf_bj='0'\n"
				+ "           and to_char(xj.lr_sj, 'yyyy-mm') = ?\n"
				+ "           and to_char(xj.lr_sj, 'yyyy') = to_char(sysdate, 'yyyy')) jjj,\n"
				+ "       t_dm_gy_zsxm xm\n"
				+ " where jjj.zsxm_dm = xm.zsxm_dm\n"
				+ " group by rq, xm.mc,pzhm\n" + " order by rq,pzhm desc";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql, jk_rq, nsrnbm, nsrnbm,
					jk_rq);
			List<NsrJkxxmx> jkmxList = new ArrayList<NsrJkxxmx>();
			// jkmxList=null;
			while (rs!=null&&rs.next()) {
				NsrJkxxmx jkmx = new NsrJkxxmx();
				jkmx.setJkrq(rs.getString("jkrq"));
				jkmx.setZsxm(rs.getString("zsxm"));
				jkmx.setSbpzs(rs.getString("sbpzs"));
				jkmx.setKpje(rs.getString("kpje"));
				jkmx.setRkje(rs.getString("rkje"));
				jkmx.setWkpje(rs.getString("wkpje"));
				jkmxList.add(jkmx);
			}
			return jkmxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 查询纳税人地方税核定信息
	@Override
	public List<NsrDfsxx> getNsrDfsxxList(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "SELECT xm.mc      zsxm, "
				+ " pm.mc      zspm, "
				+ " zs.SYSL    sl, "
				+ " zs.NYNS_JE nynse, "
				+ " qx.mc      sbqx, "
				+ " jk.mc      jkqx, "
				+ " ns.mc      nsqx, "
				+ " zs.hd_qsrq hdqsrq, "
				+ " zs.sj_zzrq hdzzrq, "
				+ " km.mc      ysfpbl, "
				+ " gk.zh_mc   gkid "
				+ " FROM (SELECT NSRNBM, "
				+ "         ZSXM_DM, "
				+ "      ZSPM_DM, "
				+ "      NSQX_DM, "
				+ "      SBQX_DM, "
				+ "      JKQX_DM, "
				+ "      GKID, "
				+ "      YSKM_DM, "
				+ "      FPBL_DM, "
				+ "      SYSL, "
				+ "      NYNS_JE, "
				+ "      ZM_BJ, "
				+ "      HD_QSRQ, "
				+ "      SJ_ZZRQ, "
				+ "      '车船登记' || CLPH ZY, "
				+ "      YXZZ_RQ "
				+ " FROM T_DJ_CCSDJXX "
				+ " where nsrnbm = ? "
				+ "  and NVL(SJ_ZZRQ, SYSDATE + 9999) > TRUNC(SYSDATE, 'MM') - 1 "
				+ "  UNION ALL "
				+ "  SELECT NSRNBM, "
				+ "        XX.ZSXM_DM, "
				+ "      TDDJ_DM ZSPM_DM, "
				+ "      NSQX_DM, "
				+ "      SBQX_DM, "
				+ "      JKQX_DM,  "
				+ "      GKID, "
				+ "      YSKM_DM, "
				+ "      FPBL_DM, "
				+ "      PM.SL SYSL, "
				+ "      NYNS_JE, "
				+ "      ZM_BJ, "
				+ "      HD_QSRQ, "
				+ "      SJ_ZZRQ, "
				+ "      '土地使用税' ZY, "
				+ "      YXZZ_RQ "
				+ " FROM T_DJ_TDSYSDJXX XX, T_DM_GY_ZSPM PM "
				+ " WHERE XX.ZSXM_DM = PM.ZSXM_DM "
				+ "  AND XX.TDDJ_DM = PM.ZSPM_DM "
				+ "  and nsrnbm = ? "
				+ "  and NVL(SJ_ZZRQ, SYSDATE + 9999) > TRUNC(SYSDATE, 'MM') - 1 "
				+ "  UNION ALL "
				+ " SELECT NSRNBM, "
				+ "      XX.ZSXM_DM, "
				+ "      TDDJ_DM ZSPM_DM, "
				+ "      NSQX_DM, "
				+ "      SBQX_DM, "
				+ "      JKQX_DM, "
				+ "      GKID, "
				+ "      YSKM_DM, "
				+ "      FPBL_DM, "
				+ "      PM.SL SYSL, "
				+ "      NYNS_JE, "
				+ "      ZM_BJ, "
				+ "      HD_QSRQ, "
				+ "      SJ_ZZRQ, "
				+ "      '土地承租' ZY, "
				+ "      YXZZ_RQ "
				+ " FROM T_DJ_TDCZXX XX, T_DM_GY_ZSPM PM "
				+ " WHERE XX.ZSXM_DM = PM.ZSXM_DM "
				+ "  AND XX.TDDJ_DM = PM.ZSPM_DM "
				+ "  and nsrnbm = ? "
				+ "  and NVL(SJ_ZZRQ, SYSDATE + 9999) > TRUNC(SYSDATE, 'MM') - 1 "
				+ "  UNION ALL "
				+ " SELECT NSRNBM, "
				+ "         XX.ZSXM_DM, "
				+ "      XX.ZSPM_DM, "
				+ "      NSQX_DM, "
				+ "      SBQX_DM, "
				+ "      JKQX_DM, "
				+ "      GKID, "
				+ "      YSKM_DM, "
				+ "      FPBL_DM, "
				+ "      PM.SL SYSL, "
				+ "      NYNSE NYNS_JE, "
				+ "      ZM_BZ ZM_BJ, "
				+ "      HD_QSRQ, "
				+ "      SJ_ZZRQ, "
				+ "      '房产出租' ZY, "
				+ "      YXZZ_RQ "
				+ " FROM T_DJ_FCCZ XX, T_DM_GY_ZSPM PM "
				+ " WHERE XX.ZSXM_DM = PM.ZSXM_DM "
				+ "  AND XX.ZSPM_DM = PM.ZSPM_DM "
				+ "  and nsrnbm = ? "
				+ "  and NVL(SJ_ZZRQ, SYSDATE + 9999) > TRUNC(SYSDATE, 'MM') - 1 "
				+ " UNION ALL "
				+ "  SELECT NSRNBM, "
				+ "      XX.ZSXM_DM, "
				+ "      XX.ZSPM_DM, "
				+ "      NSQX_DM, "
				+ "      SBQX_DM, "
				+ "      JKQX_DM, "
				+ "      GKID, "
				+ "      YSKM_DM, "
				+ "      FPBL_DM, "
				+ "      PM.SL SYSL, "
				+ "      NYNSE NYNS_JE, "
				+ "      ZM_BZ ZM_BJ, "
				+ "      HD_QSRQ, "
				+ "      SJ_ZZRQ, "
				+ "      '房产承租登记' ZY, "
				+ "      YXZZ_RQ "
				+ " FROM T_DJ_FCCZDJ XX, T_DM_GY_ZSPM PM "
				+ " WHERE XX.ZSXM_DM = PM.ZSXM_DM "
				+ "  AND XX.ZSPM_DM = PM.ZSPM_DM "
				+ "  and nsrnbm = ? "
				+ "  and NVL(SJ_ZZRQ, SYSDATE + 9999) > TRUNC(SYSDATE, 'MM') - 1 "
				+ " UNION ALL "
				+ " SELECT NSRNBM, "
				+ "      XX.ZSXM_DM, "
				+ "      XX.ZSPM_DM, "
				+ "      NSQX_DM, "
				+ "      SBQX_DM, "
				+ "      JKQX_DM, "
				+ "      GKID, "
				+ "      YSKM_DM, "
				+ "      FPBL_DM, "
				+ "      PM.SL SYSL, "
				+ "      NYNS_JE NYNS_JE, "
				+ "      ZM_BZ ZM_BJ, "
				+ "         HD_QSRQ, "
				+ "         SJ_ZZRQ, "
				+ "         '应税房产税' ZY, "
				+ "        YXZZ_RQ "
				+ "    FROM T_DJ_YSFCSDJXX XX, T_DM_GY_ZSPM PM "
				+ "   WHERE XX.ZSXM_DM = PM.ZSXM_DM "
				+ "     AND XX.ZSPM_DM = PM.ZSPM_DM "
				+ "     and nsrnbm = ? "
				+ "     and NVL(SJ_ZZRQ, SYSDATE + 9999) > TRUNC(SYSDATE, 'MM') - 1) zs, "
				+ "  t_dm_gy_zsxm xm, " + "  t_dm_gy_zspm pm, "
				+ "  t_dm_hd_sbqx qx, " + "  t_dm_hd_jkqx jk, "
				+ " t_dm_gy_nsqx ns, " + " t_dm_gy_ysfpbl km, "
				+ " t_qs_skzh gk " + " where zs.zsxm_dm = xm.zsxm_dm "
				+ "   and zs.zsxm_dm = pm.zsxm_dm "
				+ "  and zs.zspm_dm = pm.zspm_dm "
				+ "  and zs.sbqx_dm = qx.sbqx_dm(+) "
				+ "  and zs.jkqx_dm = jk.jkqx_dm(+) "
				+ "  and zs.nsqx_dm = ns.nsqx_dm(+) "
				+ " and zs.fpbl_dm = km.ysfpbl_dm(+) "
				+ " and zs.gkid = gk.skzhid(+) ";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm, nsrnbm, nsrnbm,
					nsrnbm, nsrnbm, nsrnbm);
			List<NsrDfsxx> dfshdxxList = new ArrayList<NsrDfsxx>();
			while (rs!=null&&rs.next()) {
				NsrDfsxx dfsxx = new NsrDfsxx();
				dfsxx.setZsxm(rs.getString("zsxm"));
				dfsxx.setZspm(rs.getString("zspm"));
				dfsxx.setSl(rs.getString("sl"));
				dfsxx.setNynse(rs.getString("nynse"));
				dfsxx.setNsqx(rs.getString("nsqx"));
				dfsxx.setSbqx(rs.getString("sbqx"));
				dfsxx.setJkqx(rs.getString("jkqx"));
				dfsxx.setHdqsrq(rs.getString("hdqsrq"));
				dfsxx.setHdzzrq(rs.getString("hdzzrq"));
				dfsxx.setYsfpbl(rs.getString("ysfpbl"));
				dfsxx.setGkid(rs.getString("gkid"));
				dfshdxxList.add(dfsxx);
			}
			return dfshdxxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 查询发票核定
	@Override
	public List<NsrFphd> getFphdxxList(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "SELECT nsrfp.fpzl_dm fpzldm, " + " FPZL.MC       fpzlmc, "
				+ " fs.mc         gpfs, " + " NSRFP.GP_SL   gpsl, "
				+ "  NSRFP.YGPSL   ygpsl, " + " NSRFP.ZGPL    zgpl, "
				+ " NSRFP.GP_JE   gpje, " + "  yx.mc         yxfs, "
				+ " ry.mc         hdry, " + " NSRFP.HD_RQ   hdrq "
				+ " FROM T_FP_NSRFPHDMX NSRFP, " + "  T_DM_FP_FPZL   FPZL, "
				+ " t_dm_fp_gpfs   fs, " + " t_dm_fp_yxfs   yx, "
				+ " t_dm_gy_swry   ry "
				+ "  WHERE NSRFP.FPZL_DM = FPZL.FPZL_DM "
				+ " and nsrfp.gpfs_dm = fs.fp_gpfs_dm "
				+ "  and nsrfp.yxfs_dm = yx.yxfs_dm "
				+ " and nsrfp.hdry_dm = ry.swry_dm "
				+ "  AND nsrfp.nsrnbm = ? ";
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm);
			List<NsrFphd> fphdxxList = new ArrayList<NsrFphd>();
			while (rs!=null&&rs.next()) {
				NsrFphd fphdxx = new NsrFphd();
				fphdxx.setFpzldm(rs.getString("fpzldm"));
				fphdxx.setFpzlmc(rs.getString("fpzlmc"));
				fphdxx.setGpfs(rs.getString("gpfs"));
				fphdxx.setGpsl(rs.getString("gpsl"));
				fphdxx.setYgpsl(rs.getString("ygpsl"));
				fphdxx.setZgpl(rs.getString("zgpl"));
				fphdxx.setGpje(rs.getString("gpje"));
				fphdxx.setYxfs(rs.getString("yxfs"));
				fphdxx.setHdry(rs.getString("hdry"));
				fphdxx.setHdrq(rs.getString("hdrq"));
				fphdxxList.add(fphdxx);
			}
			return fphdxxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 纳税人发票结存信息
	@Override
	public List<NsrFpjc> getFpjcxxList(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "SELECT MAX(SJ) fsrq,\n"
				+ "       MAX(RY) fsRY,\n"
				+ "       FPZL_DM fpzldm,\n"
				+ "       FP_DM fpdm,\n"
				+ "       fp_jc,\n"
				+ "       FP_QSHM qshm,\n"
				+ "       FP_ZZHM zzhm,\n"
				+ "       SUM(NVL(FSSL, 0)) FSSL,\n"
				+ "       SUM(NVL(FSFS, 0)) FSFS,\n"
				+ "       SUM(NVL(JXSL, 0)) JXSL,\n"
				+ "       SUM(NVL(JXJE, 0)) JXJE\n"
				+ "  FROM (SELECT XX.NSR_MC,\n"
				+ "               XX.NSRBM,\n"
				+ "               XX.GLJG_DM SWJG_DM,\n"
				+ "               TO_CHAR(FS.LR_SJ, 'YYYY-MM-DD') SJ,\n"
				+ "               FS.JSR_DM RY,\n"
				+ "               zl.mc fpzl_dm,\n"
				+ "               MX.FP_DM,\n"
				+ "               fp.fp_jc,\n"
				+ "               MX.FP_QSHM,\n"
				+ "               MX.FP_ZZHM,\n"
				+ "               MX.SL FSSL,\n"
				+ "               MX.DJ FSDJ,\n"
				+ "               MX.JE FSJE,\n"
				+ "               DECODE(MX.SLDW_DM, '0612', MX.SL * FP.MBFS, MX.SL) FSFS,\n"
				+ "               NULL JXSL,\n"
				+ "               NULL JXJE,\n"
				+ "               NULL YXSJ,\n"
				+ "               NULL JXRY,\n"
				+ "               XX.ZGY_DM,\n"
				+ "               KZ.CWFZR,\n"
				+ "               KZ.SCJYDZ\n"
				+ "          FROM T_FP_FPFSPZ   FS,\n"
				+ "               T_FP_FPFSPZMX MX,\n"
				+ "               T_DJ_NSRXX    XX,\n"
				+ "               T_DJ_JGNSR_KZ KZ,\n"
				+ "               T_DM_GY_SWJG  SWJG1,\n"
				+ "               T_DM_GY_SWJG  SWJG2,\n"
				+ "               T_DM_GY_SWRY  SWRY,\n"
				+ "               T_DM_FP_FP    FP,\n"
				+ "               T_DM_FP_FPZL  ZL\n"
				+ "         WHERE FS.FPFSPZ_XH = MX.FPFSPZ_XH\n"
				+ "           AND FS.NSRNBM = XX.NSRNBM\n"
				+ "           AND FS.NSRNBM = KZ.NSRNBM(+)\n"
				+ "           AND MX.FPZL_DM = ZL.FPZL_DM\n"
				+ "           AND MX.FP_DM = FP.FP_DM\n"
				+ "           AND MX.FPZL_DM = FP.FPZL_DM\n"
				+ "           AND XX.GLJG_DM = SWJG1.SWJG_DM\n"
				+ "           AND SWJG1.SJSWJG_DM = SWJG2.SWJG_DM\n"
				+ "           AND XX.ZGY_DM = SWRY.SWRY_DM(+)\n"
				+ "           AND FS.ZF_BJ = '0'\n"
				+ "           and fs.nsrnbm = ?\n"
				+ "           and to_number(to_char(fs.lr_sj, 'yyyy')) >=\n"
				+ "               to_number(to_char(sysdate, 'yyyy')) - 1\n"
				+ "        UNION ALL\n"
				+ "        SELECT XX.NSR_MC,\n"
				+ "               XX.NSRBM,\n"
				+ "               XX.GLJG_DM SWJG_DM,\n"
				+ "               NULL SJ,\n"
				+ "               NULL RY,\n"
				+ "               zl.mc fpzl_dm,\n"
				+ "               MX.FP_DM,\n"
				+ "               fp.fp_jc,\n"
				+ "               MX.FP_QSHM,\n"
				+ "               MX.FP_ZZHM,\n"
				+ "               NULL FSSL,\n"
				+ "               NULL FSDJ,\n"
				+ "               NULL FSJE,\n"
				+ "               NULL FSFS,\n"
				+ "               MX.SL JXSL,\n"
				+ "               DECODE(FP.DEFP_BJ, '1', MX.SL * FP.FP_ME, MX.JE) JXJE,\n"
				+ "               TO_CHAR(YX.YX_SJ, 'YYYY-MM-DD') YXSJ,\n"
				+ "               YX.SWYPR_DM JXRY,\n"
				+ "               XX.ZGY_DM,\n" + "               KZ.CWFZR,\n"
				+ "               KZ.SCJYDZ\n"
				+ "          FROM T_FP_FPYX     YX,\n"
				+ "               T_FP_FPYXMX   MX,\n"
				+ "               T_DJ_NSRXX    XX,\n"
				+ "               T_DJ_JGNSR_KZ KZ,\n"
				+ "               T_DM_GY_SWJG  SWJG1,\n"
				+ "               T_DM_GY_SWJG  SWJG2,\n"
				+ "               T_DM_GY_SWRY  SWRY,\n"
				+ "               T_DM_FP_FP    FP,\n"
				+ "               T_DM_FP_FPZL  ZL\n"
				+ "         WHERE YX.YXPZ_XH = MX.YXPZ_XH\n"
				+ "           AND MX.FP_DM = FP.FP_DM\n"
				+ "           AND MX.FPZL_DM = FP.FPZL_DM\n"
				+ "           AND MX.FPZL_DM = ZL.FPZL_DM\n"
				+ "           AND YX.NSRNBM = XX.NSRNBM\n"
				+ "           AND YX.NSRNBM = KZ.NSRNBM(+)\n"
				+ "           AND XX.GLJG_DM = SWJG1.SWJG_DM\n"
				+ "           AND SWJG1.SJSWJG_DM = SWJG2.SWJG_DM\n"
				+ "           AND XX.ZGY_DM = SWRY.SWRY_DM(+)\n"
				+ "           AND YX.ZF_BJ = '0'\n"
				+ "           and yx.nsrnbm = ?\n"
				+ "           and to_number(to_char(yx.yx_sj, 'yyyy')) >=\n"
				+ "               to_number(to_char(sysdate, 'yyyy')) - 1) A\n"
				+ " GROUP BY FPZL_DM, FP_DM,fp_jc, FP_QSHM, FP_ZZHM\n"
				+ " ORDER BY MAX(SJ) desc";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm, nsrnbm);
			List<NsrFpjc> fpjcList = new ArrayList<NsrFpjc>();

			while (rs!=null &&rs.next()) {
				NsrFpjc fpjc = new NsrFpjc();
				fpjc.setFsrq(rs.getString("fsrq"));
				fpjc.setFsry(rs.getString("fsry"));
				fpjc.setFpzldm(rs.getString("fpzldm"));
				fpjc.setFpdm(rs.getString("fpdm"));
				fpjc.setFp_jc(rs.getString("fp_jc"));
				fpjc.setQshm(rs.getString("qshm"));
				fpjc.setZzhm(rs.getString("zzhm"));
				fpjc.setFssl(rs.getString("fssl"));
				fpjc.setFsfs(rs.getString("fsfs"));
				fpjc.setJxsl(rs.getString("jxsl"));
				fpjc.setJxje(rs.getString("jxje"));
				fpjcList.add(fpjc);
			}
			return fpjcList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 纳税人减免信息
	@Override
	public List<NsrJmxx> getNsrJmxxList(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "SELECT xm.mc           zsxm, " + " pm.mc           zspm, "
				+ "  xx.pzjmqxs_qsrq pzjmqsrq, "
				+ " xx.pzjmqxz_zzrq pzjmzzrq, " + " xx.pzjmfd       pzjmfd, "
				+ "  xx.sqjm_je      sqjmje, " + "  xx.pzjmsl       pzjmsl, "
				+ " xx.jmye_je      jmye, " + " xx.jmyj_bz      jmyj, "
				+ "  lx.mc           jmlx, " + "  yy.mc           jmyy, "
				+ "  ry.mc           wsslry, " + " ws.sl_rq        wsslrq "
				+ "  FROM T_SB_JMSQMX   XX, " + "   T_WS_SSWSJBXX WS, "
				+ " T_DM_GY_ZSXM  XM, " + " T_DM_GY_ZSPM  PM, "
				+ " t_dm_gy_jmyy  yy, " + " t_dm_sb_jmlx  lx, "
				+ " t_dm_gy_swry  ry " + "  WHERE XX.WSH = WS.WSH "
				+ "  and xx.jmlx_dm = lx.jmlx_dm "
				+ "  and xx.jmyy_dm = yy.jmyy_dm "
				+ "  and ws.slry_dm = ry.swry_dm(+) "
				+ " AND XM.ZSXM_DM = XX.ZSXM_DM "
				+ "  AND PM.ZSXM_DM = XM.ZSXM_DM "
				+ "  AND PM.ZSPM_DM = XX.ZSPM_DM " + "  AND WS.PZ_BJ = '1' "
				+ " and to_number(to_char(xx.lr_sj, 'yyyy')) >= "
				+ "     to_number(to_char(sysdate, 'yyyy')) - 1 "
				+ "  and nsrnbm = ?";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm);
			List<NsrJmxx> jmxxList = new ArrayList<NsrJmxx>();
			// jmxxList=null;
			if (rs!=null){
				while (rs.next()) {
					NsrJmxx jmxx = new NsrJmxx();
					jmxx.setZsxm(rs.getString("zsxm"));
					jmxx.setZspm(rs.getString("zspm"));
					jmxx.setPzjmqsrq(rs.getString("pzjmqsrq"));
					jmxx.setPzjmzzrq(rs.getString("pzjmzzrq"));
					jmxx.setPzjmfd(rs.getString("pzjmfd"));
					jmxx.setSqjmje(rs.getString("sqjmje"));
					jmxx.setPzjmsl(rs.getString("pzjmsl"));
					jmxx.setJmye(rs.getString("jmye"));
					jmxx.setJmlx(rs.getString("jmlx"));
					jmxx.setJmyj(rs.getString("jmyj"));
					jmxx.setJmyy(rs.getString("jmyy"));
					jmxx.setWsslry(rs.getString("wsslry"));
					jmxx.setWsslrq(rs.getString("wsslrq"));
					jmxxList.add(jmxx);
				}			
			}

			return jmxxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 减并征期
	@Override
	public List<NsrJbzq> getNsrJbzqList(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "SELECT WS.SL_RQ      slrq, "
				+ " WS.SLRY_DM    slry,  "
				+ " JBZQ.YHDSE_JE yhdse, "
				+ " qx.mc         jbnsqx, "
				+ " JBZQ.SQ_QSRQ  qsrq,  "
				+ " JBZQ.SQ_ZZRQ  zzrq, "
				+ " JBZQ.JSYFS    jbyf  "
				+ " FROM T_SB_JBZQ JBZQ, T_WS_SSWSJBXX WS, t_dm_gy_nsqx qx "
				+ " WHERE JBZQ.WSH = WS.WSH "
				+ "  and jbzq.nsqxdm_new = qx.nsqx_dm "
				+ " AND JBZQ.ZF_BJ = 0 "
				+ " AND NVL(JBZQ.SQ_ZZRQ, SYSDATE + 9999) > TRUNC(SYSDATE, 'MM') - 1 "
				+ " and jbzq.nsrnbm = ?";
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm);
			List<NsrJbzq> jbzqList = new ArrayList<NsrJbzq>();
			// jmxxList=null;
			if (rs!=null){
				while (rs.next()) {
					NsrJbzq jbzq = new NsrJbzq();
					jbzq.setSlrq(rs.getString("slrq"));
					jbzq.setSlry(rs.getString("slry"));
					jbzq.setYhdse(rs.getString("yhdse"));
					jbzq.setJbnsqx(rs.getString("jbnsqx"));
					jbzq.setQsrq(rs.getString("qsrq"));
					jbzq.setZzrq(rs.getString("zzrq"));
					jbzq.setJbyf(rs.getString("jbyf"));
					jbzqList.add(jbzq);
				}
			}

			return jbzqList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 判断一个人员是不是专管员，如果是返回该人员代码，否则返回空
	@Override
	public String getIfZgy(String zgydm) {
		// TODO Auto-generated method stub
		sql = "select zgy_dm num from t_dj_nsrxx where zgy_dm='" + zgydm + "'";
		String number = "";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql);
			if (rs!=null&&rs.next()) {
				number = rs.getString("num");

				return number;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
	}

	// 定额分析
	@Override
	public List<NsrDefx> getDefxList(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "SELECT RQ fxyf,\n"
				+ "       JSJE de,\n"
				+ "       FPBS cdebs,\n"
				+ "       ZCSB zcsb,\n"
				+ "       QTSB qtsb,\n"
				+ "       TYTS tyts,\n"
				+ "       KCJE tykc,\n"
				+ "       TYYJ tyyj,\n"
				+ "       (JSJE - NVL(ZCSB, 0) - NVL(KCJE, 0) - NVL(TYYJ, 0)) lzje\n"
				+ "  from (SELECT TO_CHAR(DJ.YF, 'YYYY-MM') RQ,\n"
				+ "               (SELECT SUM(HD.JSJE)\n"
				+ "                  FROM T_HD_YJSZSX HD\n"
				+ "                 WHERE ADD_MONTHS(DJ.YF, -1) BETWEEN HD.HD_QSRQ AND\n"
				+ "                       NVL(HD.SJ_ZZRQ, SYSDATE + 9999)\n"
				+ "                   AND HD.JSJE > 0\n"
				+ "                   AND HD.ZSXM_DM = '06'\n"
				+ "                   AND DJ.NSRNBM = HD.NSRNBM) JSJE,\n"
				+ "               (SELECT SUM(DECODE(YZ.SBSX_DM, 'A', YZ.JS_YJ, 0)) FPBS\n"
				+ "                  FROM T_ZS_YZPZ YZ\n"
				+ "                 WHERE YZ.JS_YJ > 0\n"
				+ "                   AND DJ.NSRNBM = YZ.NSRNBM\n"
				+ "                   AND YZ.ZSXM_DM = '06'\n"
				+ "                   AND TO_CHAR(DJ.YF, 'YYYY-MM') =\n"
				+ "                       TO_CHAR(YZ.SB_RQ, 'YYYY-MM')\n"
				+ "                   AND YZ.ZSPM_DM <> '9901'\n"
				+ "                   AND YZ.ZF_BJ = 0\n"
				+ "                   AND YZ.TZLX_DM = '00') FPBS,\n"
				+ "               (SELECT SUM(DECODE(YZ.SBSX_DM, '0', NVL(YZ.JS_YJ, 0), 0) /\n"
				+ "                           TRUNC(MONTHS_BETWEEN(YZ.SFSSQ_ZZRQ, YZ.SFSSQ_QSRQ) + 1)) ZCSB\n"
				+ "                  FROM T_ZS_YZPZ YZ\n"
				+ "                 WHERE ADD_MONTHS(DJ.YF, -1) BETWEEN YZ.SFSSQ_QSRQ AND\n"
				+ "                       YZ.SFSSQ_ZZRQ\n"
				+ "                   AND YZ.JS_YJ > 0\n"
				+ "                   AND DJ.NSRNBM = YZ.NSRNBM\n"
				+ "                   AND YZ.ZSXM_DM = '06'\n"
				+ "                   AND YZ.ZSPM_DM <> '9901'\n"
				+ "                   AND YZ.ZF_BJ = 0\n"
				+ "                   AND YZ.TZLX_DM = '00') ZCSB,\n"
				+ "               (SELECT SUM(DECODE(INSTR('A,0,E', YZ.SBSX_DM), 0, YZ.JS_YJ, 0)) ZCSB\n"
				+ "                  FROM T_ZS_YZPZ YZ\n"
				+ "                 WHERE YZ.JS_YJ > 0\n"
				+ "                   AND DJ.NSRNBM = YZ.NSRNBM\n"
				+ "                   AND YZ.ZSXM_DM = '06'\n"
				+ "                   AND YZ.ZSPM_DM <> '9901'\n"
				+ "                   AND ADD_MONTHS(DJ.YF, -1) BETWEEN YZ.SFSSQ_QSRQ AND\n"
				+ "                       YZ.SFSSQ_ZZRQ\n"
				+ "                   AND YZ.ZF_BJ = 0\n"
				+ "                   AND YZ.TZLX_DM = '00') QTSB,\n"
				+ "               (SELECT SUM(F_GS_QJTS(TY.HZTY_QSRQ,\n"
				+ "                                     TY.HZTY_ZZRQ,\n"
				+ "                                     TY.SJFY_RQ,\n"
				+ "                                     ADD_MONTHS(DJ.YF, -1)))\n"
				+ "                  FROM T_DJ_TFYXX TY, T_WS_SSWSJBXX WS\n"
				+ "                 WHERE TY.TYSQWSWH = WS.WSH\n"
				+ "                   AND WS.PZ_BJ = '1'\n"
				+ "                   AND DJ.NSRNBM = TY.NSRNBM\n"
				+ "                   AND (TO_CHAR(ADD_MONTHS(DJ.YF, -1), 'YYYY-MM') BETWEEN\n"
				+ "                       TO_CHAR(TY.HZTY_QSRQ, 'YYYY-MM') AND\n"
				+ "                       TO_CHAR(TY.HZTY_ZZRQ, 'YYYY-MM'))) TYTS,\n"
				+ "               (SELECT SUM(DECODE(YZ.SBSX_DM, 'E', YZ.JS_YJ, 0)) ZCSB\n"
				+ "                  FROM T_ZS_YZPZ YZ\n"
				+ "                 WHERE ADD_MONTHS(DJ.YF, -1) BETWEEN YZ.SFSSQ_QSRQ AND\n"
				+ "                       YZ.SFSSQ_ZZRQ\n"
				+ "                   AND YZ.JS_YJ > 0\n"
				+ "                   AND DJ.NSRNBM = YZ.NSRNBM\n"
				+ "                   AND YZ.ZSXM_DM = '06'\n"
				+ "                   AND YZ.ZSPM_DM <> '9901'\n"
				+ "                   AND YZ.ZF_BJ = 0\n"
				+ "                   AND YZ.TZLX_DM = '00') TYYJ,\n"
				+ "               F_GS_QJ_KC(DJ.NSRNBM, DJ.YF) KCJE\n"
				+ "          FROM (SELECT XX.NSRNBM,\n"
				+ "                       TO_DATE(TO_CHAR(SYSDATE, 'YYYY') || '-' || M || '-10',\n"
				+ "                               'YYYY-MM-DD') YF\n"
				+ "                  FROM (SELECT 1 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 2 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 3 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 4 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 5 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 6 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 7 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 8 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 9 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 10 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 11 M\n"
				+ "                          FROM DUAL\n"
				+ "                        UNION ALL\n"
				+ "                        SELECT 12 M FROM DUAL) YF,\n"
				+ "                       T_DJ_NSRXX XX\n"
				+ "                 WHERE M < TO_CHAR(SYSDATE, 'MM')\n"
				+ "                   AND EXISTS (SELECT 1\n"
				+ "                          FROM T_HD_YJSZSX YJ\n"
				+ "                         WHERE XX.NSRNBM = YJ.NSRNBM\n"
				+ "                           AND YJ.ZSXM_DM = '06'\n"
				+ "                           AND YJ.JSJE > 0\n"
				+ "                           AND YJ.ZSFS_DM = '41'\n"
				+ "                           AND NVL(YJ.SJ_ZZRQ, SYSDATE + 9999) >\n"
				+ "                               TRUNC(SYSDATE, 'MM') - 1)\n"
				+ "                   and xx.nsrnbm = ?\n" + "\n"
				+ "                ) DJ)";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm);
			List<NsrDefx> defxList = new ArrayList<NsrDefx>();
			// jmxxList=null;
			while (rs!=null&&rs.next()) {
				NsrDefx defx = new NsrDefx();
				defx.setFxyf(rs.getString("fxyf"));
				defx.setDe(rs.getString("de"));
				defx.setCdebs(rs.getString("cdebs"));
				defx.setZcsb(rs.getString("zcsb"));
				defx.setQtsb(rs.getString("qtsb"));
				defx.setTyyj(rs.getString("tyyj"));
				defx.setTykc(rs.getString("tykc"));
				defx.setTyts(rs.getString("tyts"));
				defx.setLzje(rs.getString("lzje"));
				defxList.add(defx);
			}
			return defxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 发票分析
	@Override
	public List<NsrFpfx> getFpfxList(String nsrnbm) {
		// TODO Auto-generated method stub
		sql = "SELECT SUM(PZS) pzs,\n"
				+ "       SUM(PZS * SBJE) sbje,\n"
				+ "       SUM(JXSL) jxsl,\n"
				+ "       SUM(JXJE) jxje,\n"
				+ "       SUM(FSFS) fsfs,\n"
				+ "       SUM(FSJE) fsje,\n"
				+ "       max(jsje) de,\n"
				+ "       SUM(DECODE(SBSX_DM, '0', PZS * SBJE, 0)) zcsb,\n"
				+ "       SUM(DECODE(SBSX_DM, 'A', PZS * SBJE, 0)) bssb,\n"
				+ "       (SUM(fsJE) - SUM(PZS * SBJE)) fsce,\n"
				+ "       (SUM(JXJE) - SUM(PZS * SBJE)) ce\n"
				+ "  from (select hd.nsrnbm,\n"
				+ "               null sbsx_dm,\n"
				+ "               0 pzs,\n"
				+ "               0 sbje,\n"
				+ "               0 jxsl,\n"
				+ "               0 jxje,\n"
				+ "               0 fsfs,\n"
				+ "               0 fsje,\n"
				+ "               max(hd.jsje) jsje\n"
				+ "          from t_hd_yjszsx hd, t_dj_nsrxx xx, T_FP_FPFSPZ fs\n"
				+ "         where hd.nsrnbm = xx.nsrnbm\n"
				+ "           and hd.nsrnbm = fs.nsrnbm\n"
				+ "           and hd.nsrnbm = ?\n"
				+ "           AND TO_CHAR(FS.LR_SJ, 'YYYY') >= to_char(sysdate, 'yyyy')\n"
				+ "         group by hd.nsrnbm\n"
				+ "        union all\n"
				+ "        select yz.nsrnbm,\n"
				+ "               yz.sbsx_dm,\n"
				+ "               count(distinct yz.pz_xh) pzs,\n"
				+ "               max(yz.js_yj) sbje,\n"
				+ "               0 jxsl,\n"
				+ "               0 jxje,\n"
				+ "               0 fsfs,\n"
				+ "               0 fsje,\n"
				+ "               0 jsje\n"
				+ "          from t_zs_yzpz yz, T_FP_FPFSPZ fs\n"
				+ "         where yz.nsrnbm = fs.nsrnbm\n"
				+ "           and yz.zf_bj = '0'\n"
				+ "           and YZ.TZLX_DM = '00'\n"
				+ "           and yz.nsrnbm = ?\n"
				+ "           and yz.zsxm_dm = '02'\n"
				+ "           AND TO_CHAR(yz.sb_rq, 'YYYY') >= to_char(sysdate, 'yyyy')\n"
				+ "         group by yz.nsrnbm, yz.sbsx_dm\n"
				+ "        union all\n"
				+ "        SELECT yx.nsrnbm,\n"
				+ "               null sbsx_dm,\n"
				+ "               0 pzs,\n"
				+ "               0 sbje,\n"
				+ "               sum(MX.SL) JXSL,\n"
				+ "               sum(DECODE(FP.DEFP_BJ, '1', MX.SL * FP.FP_ME, MX.JE)) JXJE,\n"
				+ "               0 fsfs,\n"
				+ "               0 fsje,\n"
				+ "               0 jsje\n"
				+ "          FROM T_FP_FPYX YX, t_dj_nsrxx xx, T_FP_FPYXMX MX, T_DM_FP_FP FP\n"
				+ "         WHERE YX.YXPZ_XH = MX.YXPZ_XH\n"
				+ "           and yx.nsrnbm = xx.nsrnbm\n"
				+ "           AND MX.FP_DM = FP.FP_DM\n"
				+ "           and yx.nsrnbm = ?\n"
				+ "           AND MX.FPZL_DM = FP.FPZL_DM\n"
				+ "           AND YX.ZF_BJ = '0'\n"
				+ "           AND TO_CHAR(YX.YX_SJ, 'YYYY') >= to_char(sysdate, 'yyyy')\n"
				+ "         group by yx.nsrnbm\n"
				+ "        union all\n"
				+ "        SELECT fs.nsrnbm,\n"
				+ "               null sbsx_dm,\n"
				+ "               0 pzs,\n"
				+ "               0 sbje,\n"
				+ "               0 jxsl,\n"
				+ "               0 jxje,\n"
				+ "               sum(DECODE(MX.SLDW_DM, '0612', MX.SL * FP.MBFS, MX.SL)) FSFS,\n"
				+ "               sum(DECODE(FP.DEFP_BJ, '1', MX.SL * fp.mbfs * FP.FP_ME, 0)) fsJE,\n"
				+ "               0 jsje\n"
				+ "          FROM T_FP_FPFSPZ   FS,\n"
				+ "               t_dj_nsrxx    xx,\n"
				+ "               T_FP_FPFSPZMX MX,\n"
				+ "               T_DM_FP_FP    FP\n"
				+ "         WHERE FS.FPFSPZ_XH = MX.FPFSPZ_XH\n"
				+ "           and fs.nsrnbm = xx.nsrnbm\n"
				+ "           and fs.nsrnbm = ?\n"
				+ "           AND MX.FP_DM = FP.FP_DM\n"
				+ "           AND MX.FPZL_DM = FP.FPZL_DM\n"
				+ "           AND FS.ZF_BJ = '0'\n"
				+ "           AND TO_CHAR(FS.LR_SJ, 'YYYY') >= to_char(sysdate, 'yyyy')\n"
				+ "         group by fs.nsrnbm)";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql, nsrnbm, nsrnbm, nsrnbm,
					nsrnbm);
			List<NsrFpfx> fpfxList = new ArrayList<NsrFpfx>();
			// jmxxList=null;
			while (rs!=null&&rs.next()) {
				NsrFpfx fpfx = new NsrFpfx();

				fpfx.setPzs(rs.getString("pzs"));
				fpfx.setSbje(rs.getString("sbje"));
				fpfx.setJxsl(rs.getString("jxsl"));
				fpfx.setJxje(rs.getString("jxje"));
				fpfx.setFsfs(rs.getString("fsfs"));
				fpfx.setFsje(rs.getString("fsje"));
				fpfx.setDe(rs.getString("de"));
				fpfx.setZcsb(rs.getString("zcsb"));
				fpfx.setBssb(rs.getString("bssb"));
				fpfx.setCe(rs.getString("ce"));
				fpfx.setFsce(rs.getString("fsce"));
				fpfxList.add(fpfx);
			}
			return fpfxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 取得某人员所在核算机关的某票证号码所对就的所有票证
	@Override
	public List<SysPzList> GetPzList(String pzhm, String swry_dm) {
		// TODO Auto-generated method stub

		//
		sql = "select jg.mc_j kpjg,\n" + "    pz.pzzl_dm ,\n"
				+ "    zl.mc_j pzzl_mc,\n" + "    pz.pzzb_dm,\n"
				+ "    zb.mc pzzb_mc,\n" + "    pz.pzhm,\n"
				+ "    pz.jehj_je je\n" + "from t_zs_pzsyqk pz,\n"
				+ "t_dm_pz_pzzb zb,\n" + "t_dm_pz_pzzl zl,\n"
				+ "t_dm_gy_swjg jg\n" + "where pz.pzzb_dm=zb.pzzb_dm\n"
				+ "and pz.pzzl_dm=zl.pzzl_dm\n" + "and pz.kpjg_dm=jg.swjg_dm\n"
				+ "and pz.zfr_dm is null\n" + "and pz.pzhm=?";

		try {
			ResultSet rs = SQLHelper.getResultSet(sql, pzhm);
			List<SysPzList> pzlist = new ArrayList<SysPzList>();
			while (rs!=null&&rs.next()) {
				SysPzList pz = new SysPzList();

				pz.setKpjg(rs.getString("kpjg"));
				pz.setPzzl_dm(rs.getString("pzzl_dm"));
				pz.setPzzl_mc(rs.getString("pzzl_mc"));
				pz.setPzzb_dm(rs.getString("pzzb_dm"));
				pz.setPzzb_mc(rs.getString("pzzb_mc"));
				pz.setPzhm(rs.getString("pzhm"));
				pz.setJe(rs.getString("je"));

				pzlist.add(pz);
			}
			return pzlist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 根据不同的人员级次，产生查询条件，主要目的是加快查询速度
	private String Gettj(String swry_dm) {
		String tj = "";
		// 读取人员级次
		int ryjc = GetJgjc(swry_dm);
		if (ryjc == 2) {
			return "";
		}
		// 查询税务人员所管辖的管理机关列表
		switch (ryjc) {
		case 4:// 市级人员

			sql = "select swjg_dm\n"
					+ "from T_XT_SWRY_CX a\n"
					+ "where a.swjg_dm in (select hsjg_dm from t_cs_znjgdzb )\n"
					+ "and a.swry_dm=?";

			break;
		case 6:// 县级人员

			sql = "select swjg_dm\n"
					+ "from T_XT_SWRY_CX a\n"
					+ "where a.swjg_dm in (select hsjg_dm from t_cs_znjgdzb )\n"
					+ "and a.swry_dm=?";

			break;
		case 8:// 所级人员

			sql =

			"select distinct hsjg_dm　swjg_dm\n" + "from t_cs_znjgdzb a,\n"
					+ "t_dm_gy_swry ry\n" + "where a.gljg_dm=ry.swjg_dm\n"
					+ "and ry.swry_dm=?";

			break;
		}
		String swjg = "";
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, swry_dm);
			while (rs!=null&&rs.next()) {
				swjg = swjg + "'" + rs.getString("swjg_dm") + "',";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (swjg.length()>0){
			swjg = swjg.substring(0, swjg.length() - 1);		
		}

		switch (ryjc) {
		case 4:// 市级人员

			tj = "hsjg_dm in(" + swjg + ")";
			break;
		case 6:// 县级人员
			tj = "hsjg_dm in(" + swjg + ")";
			break;
		case 8:// 所级人员
			tj = "hsjg_dm in (" + swjg + ")";
			break;
		}

		return tj;
	}

	// 根据不同的人员级次，产生查询条件，主要目的是加快查询速度
	private String GetSwjg(String swry_dm) {
		int ryjc = GetJgjc(swry_dm);
		if (ryjc == 8){
			sql =

				"select swjg_dm sjswjg_dm\n"
						+ "  from t_dm_gy_swjg jg\n"
						+ " where jg.swjg_dm in\n"
						+ "       (select swjg_dm from t_dm_gy_swry a where a.swry_dm = ?)";
			
		}else{
			sql =

				"select sjswjg_dm\n"
						+ "  from t_dm_gy_swjg jg\n"
						+ " where jg.swjg_dm in\n"
						+ "       (select swjg_dm from t_dm_gy_swry a where a.swry_dm = ?)";
			
		}


		String swjg = "";
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, swry_dm);
			while (rs!=null&&rs.next()) {
				swjg = rs.getString("sjswjg_dm");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return swjg;
	}

	// 取得主页面信息
	@Override
	public main GetMainList(String swry_dm, String qttj) {
		// TODO Auto-generated method stub
		String tj = "";
		// 读取人员级次
		int ryjc = GetJgjc(swry_dm);
		String swjg = GetSwjg(swry_dm);

		// 上年最后一天时间
		Public_Date dd = new Public_Date();

		String LastDate = "CAST(to_date('" + dd.getPreviousYearEnd()
				+ "','yyyy-mm-dd') AS TIMESTAMP)";

		// 先判断是不是专管员
		String zgy = getIfZgy(swry_dm);
		String gljg_tj = Gettj(swry_dm);

		String str = "";

		// 人员权限范围代码段 开始
		if (zgy.length() > 0) {
			str = " and xx.zgy_dm='" + swry_dm + "'";
		} else {
			if (gljg_tj.length() > 0) {
				str = " and EXISTS (SELECT 1\n" + " FROM T_XT_SWRY_CX CX\n"
						+ " WHERE xx.GLJG_DM=CX.SWJG_DM\n"
						+ " AND CX.SWRY_DM ='" + swry_dm + "')";
			} else {
				str = "";
			}

		}
		// 人员权限范围代码段 完成

		sql = "select sum(sj01) sj01,\n"
				+ "   sum(sj07) sj07,\n"
				+ "   sum(sj14) sj14\n"
				+ "from\n"
				+ "(\n"
				+ "        select 0 sj01, count(1) sj07, 0 sj14\n"
				+ "          from t_dj_nsrxx xx,\n"
				+ "               t_dm_gy_nsrzt zt,\n"
				+ "               (select distinct WSSPXMXL_DM, mc from t_dm_gy_wsspxmxl) lx\n"
				+ "         where xx.djlx_dm = lx.wsspxmxl_dm\n"
				+ "           and xx.dj_zt = zt.nsrzt_dm\n"
				+ "           and xx.djlx_dm not in ( '0122','0104', '0129')\n"
				+ "           and xx.dj_zt not in ('50', '51', '52')\n";

		sql = sql + str + "        union all\n"
				+ "        select 0 sj01, 0 sj07, count(1) sj14\n"
				+ "          from (select xx.nsrnbm,\n"
				+ "                       xx.DJLX_DM,\n"
				+ "                       xx.zclx_dm,\n"
				+ "                       substr(xx.hy_dm, 1, 2) hy,\n"
				+ "                       dw.SBDJ_ZT,\n"
				+ "                       dw.JFDWLX_DM,\n"
				+ "                       0 as fl\n"
				+ "                  from t_dj_nsrxx xx, T_SFDJ_DWJFDJXX dw\n"
				+ "                 where xx.nsrnbm = dw.nsrnbm\n"
				+ "                   and xx.dj_zt not in ('50', '51', '52')\n"
				+ "                   and dw.sbdj_zt = '10'\n";
		sql = sql + str + "                union all\n"
				+ "                select xx.nsrnbm,\n"
				+ "                       xx.DJLX_DM,\n"
				+ "                       xx.zclx_dm,\n"
				+ "                       substr(xx.hy_dm, 1, 2) hy,\n"
				+ "                       dw.SBDJ_ZT,\n"
				+ "                       dw.JFDWLX_DM,\n"
				+ "                       1 as fl\n"
				+ "                  from t_dj_nsrxx xx, T_SFDJ_SBGRXX_KZ dw\n"
				+ "                 where xx.nsrnbm = dw.nsrnbm\n"
				+ "                   and xx.dj_zt not in ('50', '51', '52')\n";
		sql = sql
				+ str
				+ "                   and dw.sbdj_zt = '10') hj,\n"
				+ "               (select distinct WSSPXMXL_DM, mc from t_dm_gy_wsspxmxl) lx\n"
				+ "         where hj.djlx_dm = lx.wsspxmxl_dm\n"
				+ "        union all\n"
				+ "        select count(1) sj01, 0 sj07, 0 sj14\n"
				+ "          from t_dj_nsrxx xx,\n"
				+ "               t_dm_gy_nsrzt zt,\n"
				+ "               (select distinct WSSPXMXL_DM, mc from t_dm_gy_wsspxmxl) lx\n"
				+ "         where xx.djlx_dm = lx.wsspxmxl_dm\n"
				+ "           and xx.dj_zt = zt.nsrzt_dm\n";
		sql = sql + str + "           and xx.dj_zt not in ('50', '51', '52')\n"
				+ ")";

		main mainlist = new main();
		try {
			ResultSet rs = SQLHelper.getResultSet(sql);
			while (rs!=null&&rs.next()) {
				mainlist.setSj01(rs.getString("sj01"));
				mainlist.setSj07(rs.getString("sj07"));
				mainlist.setSj14(rs.getString("sj14"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		sql = "select sum(yz.sbje) sj20,\n"
				+ "    sum(yz.kpje) sj21,\n"
				+ "    sum(yz.sbje-yz.kpje) sj22\n"
				+ "from t_gs_sj_sbxx yz\n";


		// 人员权限范围代码段 开始
		if (zgy.length() > 0) {
			str = " where zgy_dm='" + swry_dm + "'";
		} else {
			if (gljg_tj.length() > 0) {
				switch (ryjc) {
				case 4:// 市级人员

					str = " where sjjg ='" + swjg + "'";
					break;
				case 6:// 县级人员
					str = " where hsjg ='" + swjg + "'";
					break;
				case 8:// 所级人员
					str = " where gljg ='" + swjg + "'";
					break;
				}

			} else {
				str = "";
			}

		}
		// 人员权限范围代码段 完成
		sql = sql + str;

		try {
			ResultSet rs1 = SQLHelper.getResultSet(sql);
			while (rs1!=null&&rs1.next()) {
				mainlist.setSj20(rs1.getString("sj20"));
				mainlist.setSj21(rs1.getString("sj21"));
				mainlist.setSj22(rs1.getString("sj22"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		sql =

		"select sum(yz.kpje) sj23,\n"
				+ "    sum(decode(yz.zsfs_dm,'10',yz.kpje,0)) sj24,\n"
				+ "    sum(decode(yz.zsfs_dm,'10',0,yz.kpje)) sj25\n"
				+ "from t_gs_sj_sbxx yz"
				;

		// 人员权限范围代码段 开始
		if (zgy.length() > 0) {
			str = " where zgy_dm='" + swry_dm + "'";
		} else {
			if (gljg_tj.length() > 0) {
				switch (ryjc) {
				case 4:// 市级人员

					str = " where sjjg ='" + swjg + "'";
					break;
				case 6:// 县级人员
					str = " where hsjg ='" + swjg + "'";
					break;
				case 8:// 所级人员
					str = " where gljg ='" + swjg + "'";
					break;
				}

			} else {
				str = "";
			}

		}
		sql = sql + str;

		try {
			ResultSet rs2 = SQLHelper.getResultSet(sql);
			while (rs2!=null&&rs2.next()) {
				mainlist.setSj23(rs2.getString("sj23"));
				mainlist.setSj24(rs2.getString("sj24"));
				mainlist.setSj25(rs2.getString("sj25"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		sql =

		"SELECT sum(rk.zje) je_0,\n" + " sum(rk.zyj) JE_1,\n"
				+ " sum(rk.ssj) JE_2,\n" + " sum(rk.dsj) JE_3,\n"
				+ " sum(rk.qxj) JE_4\n" + " FROM t_gs_sj_rkxx rk";

		// 人员权限范围代码段 开始
		if (zgy.length() > 0) {
			str = " where zgy_dm='" + swry_dm + "'";
		} else {
			if (gljg_tj.length() > 0) {
				switch (ryjc) {
				case 4:// 市级人员

					str = " where SJSWJG_DM ='" + swjg + "'";
					break;
				case 6:// 县级人员
					str = " where HSJG_DM ='" + swjg + "'";
					break;
				case 8:// 所级人员
					str = " where GLJG_DM ='" + swjg + "'";
					break;
				}

			} else {
				str = "";
			}

		}
		// 人员权限范围代码段 完成
		sql = sql + str;

		try {
			ResultSet rs2 = SQLHelper.getResultSet(sql);
			while (rs2!=null&&rs2.next()) {
				mainlist.setSj26(rs2.getString("je_0"));
				mainlist.setSj27(rs2.getString("je_1"));
				mainlist.setSj28(rs2.getString("je_2"));
				mainlist.setSj29(rs2.getString("je_3"));
				mainlist.setSj02(rs2.getString("je_4"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return mainlist;
	}

	// 根据税务人员代码，取得该税务人员机关级次
	private int GetJgjc(String swry_dm) {

		// 如果有多条，取最大的级次
		sql = "select min(a.jgjc) jc\n" + "from t_gs_sjyz a\n"
				+ "where a.swry_dm=?";

		int jc = 4;
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, swry_dm);
			while (rs!=null&&rs.next()) {

				jc = Integer.parseInt(rs.getString("jc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jc;
	}

	// 根据不同的TableId返回不同的查询结果
	@Override
	public List<PubView> GetPublicList(String swry_dm, String TableId,
			String qttj) {
		// TODO Auto-generated method stub
		// 读取人员级次
		int ryjc = GetJgjc(swry_dm);
		String swjg = GetSwjg(swry_dm);

		// 上年最后一天时间
		Public_Date dd = new Public_Date();
		// 录入时间格式
		String LastDate = "CAST(to_date('" + dd.getPreviousYearEnd()
				+ "','yyyy-mm-dd') AS TIMESTAMP)";

		// 销号时间格式
		// String
		// LastDate_xh="to_date('"+dd.getPreviousYearEnd()+" 00:00:00','yyyy-mm-dd hh:mi:ss')";
		String LastDate_xh = "CAST(to_date('" + dd.getPreviousYearEnd()
				+ "','yyyy-mm-dd') AS TIMESTAMP)";
		// 查询人所机关级次
		int jgjc = GetJgjc(swry_dm);
		String gljg_tj = Gettj(swry_dm);

		// 先判断是不是专管员
		String zgy = getIfZgy(swry_dm);

		String str = "";

		switch (Integer.parseInt(TableId)) {
		case 0:// 申报分月份分税种
				// 人员权限范围代码段 开始
			if (zgy.length() > 0) {
				str = " and zgy_dm='" + swry_dm + "'";
			} else {
				if (gljg_tj.length() > 0) {
					switch (ryjc) {
					case 4:// 市级人员

						str = " and sjjg ='" + swjg + "'";
						break;
					case 6:// 县级人员
						str = " and hsjg ='" + swjg + "'";
						break;
					case 8:// 所级人员
						str = " and gljg ='" + swjg + "'";
						break;
					}

				} else {
					str = "";
				}

			}
			sql = "select nvl(yz.tj_sj,'合计') l1,\n"
					+ "nvl(xm.mc,'合计') l2,\n"
					+ "sum(yz.sbje) l3\n"
					+ "from t_zg_sb_sbfltj yz,\n"
					+ "t_dm_gy_zsxm xm\n"
					+ "where yz.zsxm_dm=xm.zsxm_dm\n"
					+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')";

			sql = sql + str + " group by rollup(yz.tj_sj,xm.mc)";

			break;
		case 1:// 申报申报方式分税种
				// 人员权限范围代码段 开始
			if (zgy.length() > 0) {
				str = " and zgy_dm='" + swry_dm + "'";
			} else {
				if (gljg_tj.length() > 0) {
					switch (ryjc) {
					case 4:// 市级人员

						str = " and sjjg ='" + swjg + "'";
						break;
					case 6:// 县级人员
						str = " and hsjg ='" + swjg + "'";
						break;
					case 8:// 所级人员
						str = " and gljg ='" + swjg + "'";
						break;
					}

				} else {
					str = "";
				}

			}
			// 人员权限范围代码段 完成
			sql = "select nvl(sx.mc,'合计') l1,\n"
					+ "nvl(xm.mc,'合计') l2,\n"
					+ "sum(yz.sbje) l3\n"
					+ "from t_zg_sb_sbfltj yz,\n"
					+ "t_dm_sb_sbsx sx,\n"
					+ "t_dm_gy_zsxm xm\n"
					+ "where yz.sbsx_dm=sx.sbsx_dm\n"
					+ "and yz.zsxm_dm=xm.zsxm_dm\n"
					+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
					+ str + " group by rollup(sx.mc,xm.mc)";

			break;

		case 2:// 按是申报户数分析
				// 人员权限范围代码段 开始
			if (zgy.length() > 0) {
				str = " and xx.zgy_dm='" + swry_dm + "'"
						+ " and EXISTS (SELECT 1\n" + " FROM T_XT_SWRY_CX CX\n"
						+ " WHERE xx.GLJG_DM=CX.SWJG_DM\n"
						+ " AND CX.SWRY_DM ='" + swry_dm + "')";
			} else {
				if (gljg_tj.length() > 0) {
					str = " and EXISTS (SELECT 1\n" + " FROM T_XT_SWRY_CX CX\n"
							+ " WHERE xx.GLJG_DM=CX.SWJG_DM\n"
							+ " AND CX.SWRY_DM ='" + swry_dm + "')";
				} else {
					str = "";
				}

			}
			// 人员权限范围代码段 完成
			sql = "select '税收管户(剔除注销、内部户)' l1,\n"
					+ "       '税收管户(剔除注销、内部户)' l2,\n"
					+ "       '税收管户(剔除注销、内部户)' l3\n"
					+ "from dual\n"
					+ "union all\n"
					+ "select nvl(zt.mc,'合计') l1,\n"
					+ "       nvl(lx.mc,'小计') l2,\n"
					+ "       ''||count(1) l3\n"
					+ "from t_dj_nsrxx xx,\n"
					+ "t_dm_gy_nsrzt zt,\n"
					+ "t_cs_znjgdzb yz,\n"
					+ "(select distinct WSSPXMXL_DM,mc  from t_dm_gy_wsspxmxl ) lx\n"
					+ "where xx.djlx_dm=lx.wsspxmxl_dm\n"
					+ "and xx.gljg_dm =yz.gljg_dm\n"
					+ "and xx.dj_zt=zt.nsrzt_dm\n"
					+ "and xx.djlx_dm not in ('0122','0104','0129')\n"
					+ "and xx.dj_zt not in ('50','51','52')\n";
			sql = sql
					+ str
					+ "group by rollup(zt.mc,lx.mc)\n"
					+ "union all\n"
					+ "select '其中：应申报户数' l1,\n"
					+ "       '其中：应申报户数' l2,\n"
					+ "       '其中：应申报户数' l3\n"
					+ "from dual\n"
					+ "union all\n"
					+ "select nvl(zt.mc,'合计') l1,\n"
					+ "       nvl(lx.mc,'小计') l2,\n"
					+ "       ''||count(1) l3\n"
					+ "from t_dj_nsrxx xx,\n"
					+ "t_dm_gy_nsrzt zt,\n"
					+ "t_cs_znjgdzb yz,\n"
					+ "(select distinct WSSPXMXL_DM,mc  from t_dm_gy_wsspxmxl ) lx\n"
					+ "where xx.djlx_dm=lx.wsspxmxl_dm\n"
					+ "and xx.gljg_dm=yz.gljg_dm\n"
					+ "and xx.dj_zt=zt.nsrzt_dm\n"
					+ "and xx.djlx_dm not in ('0122','0104','0129')\n"
					+ "and xx.dj_zt not in ('50','51','52')\n";
			sql = sql
					+ str
					+ "and exists (select 1\n"
					+ "              from t_gs_sb_ysbsj ysb\n"
					+ "              where xx.nsrnbm=ysb.nsrnbm\n"
					+ "           )\n"
					+ "group by rollup(zt.mc,lx.mc)\n"
					+ "union all\n"
					+ "select '其中：未申报户数' l1,\n"
					+ "       '其中：未申报户数' l2,\n"
					+ "       '其中：未申报户数' l3\n"
					+ "from dual\n"
					+ "union all\n"
					+ "select nvl(zt.mc,'合计') l1,\n"
					+ "       nvl(lx.mc,'小计') l2,\n"
					+ "       ''||count(1) l3\n"
					+ "from t_dj_nsrxx xx,\n"
					+ "t_dm_gy_nsrzt zt,\n"
					+ "t_cs_znjgdzb yz,\n"
					+ "(select distinct WSSPXMXL_DM,mc  from t_dm_gy_wsspxmxl ) lx\n"
					+ "where xx.djlx_dm=lx.wsspxmxl_dm\n"
					+ "and xx.gljg_dm=yz.gljg_dm\n"
					+ "and xx.dj_zt=zt.nsrzt_dm\n"
					+ "and xx.djlx_dm not in ('0122','0104','0129')\n"
					+ "and xx.dj_zt not in ('50','51','52')\n";
			sql = sql + str +

			"and exists (select 1\n"
					+ "              from t_gs_sb_wsbsj_h ysb\n"
					+ "              where xx.nsrnbm=ysb.nsrnbm\n"
					+ "           )\n" + "group by rollup(zt.mc,lx.mc)";
			
			break;
		case 9:// 申报分单位、分机构
				// 人员权限范围代码段 开始
			if (zgy.length() > 0) {
				str = " and xx.zgy_dm='" + swry_dm + "'"
						+ " and EXISTS (SELECT 1\n" + " FROM T_XT_SWRY_CX CX\n"
						+ " WHERE xx.GLJG_DM=CX.SWJG_DM\n"
						+ " AND CX.SWRY_DM ='" + swry_dm + "')";
			} else {
				if (gljg_tj.length() > 0) {
					str = " and EXISTS (SELECT 1\n" + " FROM T_XT_SWRY_CX CX\n"
							+ " WHERE xx.GLJG_DM=CX.SWJG_DM\n"
							+ " AND CX.SWRY_DM ='" + swry_dm + "')";
				} else {
					str = "";
				}

			}

			

			switch (jgjc) {
			case 2:// 省级
				sql = "select '分市州、县区列表' l1,\n"
						+ "       '分市州、县区列表' l2,\n"
						+ "       '分市州、县区列表' l3\n"
						+ "from dual\n"
						+ "union all\n"
						+ "select nvl(jg.mc,'合计') l1,\n"
						+ "nvl(jg1.mc,'合计') l2,\n"
						+ "''||sum(yz.sbje) l3\n"
						+ "from t_zg_sb_sbfltj yz,\n"
						+ "t_dm_gy_swjg jg,\n"
						+ "t_dm_gy_swjg jg1\n"
						+ "where yz.sjjg=jg.swjg_dm\n"
						+ "and yz.hsjg=jg1.swjg_dm\n"
						+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
						+ str + " group by rollup(jg.mc,jg1.mc)";
				break;
			case 4:// 市级
				sql = "select '分县区、税所列表' l1,\n"
						+ "       '分县区、税所列表' l2,\n"
						+ "       '分县区、税所列表' l3\n"
						+ "from dual\n"
						+ "union all\n"
						+ "select nvl(jg.mc,'合计') l1,\n"
						+ "nvl(jg1.mc,'合计') l2,\n"
						+ "''||sum(yz.sbje) l3\n"
						+ "from t_zg_sb_sbfltj yz,\n"
						+ "t_dm_gy_swjg jg,\n"
						+ "t_dm_gy_swjg jg1\n"
						+ "where yz.hsjg=jg.swjg_dm\n"
						+ "and yz.gljg=jg1.swjg_dm\n"
						+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
						+ str + " group by rollup(jg.mc,jg1.mc)";

				break;
			case 6:// 县级
				sql = "select '分税所、专管员列表' l1,\n"
						+ "       '分税所、专管员列表' l2,\n"
						+ "       '分税所、专管员列表' l3\n"
						+ "from dual\n"
						+ "union all\n"
						+ "select nvl(jg.mc,'合计') l1,\n"
						+ "nvl(ry.mc,'合计') l2,\n"
						+ "''||sum(yz.sbje) l3\n"
						+ "from t_zg_sb_sbfltj yz,\n"
						+ "t_dm_gy_swjg jg,\n"
						+ "t_dm_gy_swry ry\n"
						+ "where yz.gljg=jg.swjg_dm\n"
						+ "and yz.zgy=ry.swry_dm(+)\n"
						+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
						+ str + " group by rollup(jg.mc,ry.mc)";

				break;
			case 8:// 所级
				sql = "select '分专管员、税种列表' l1,\n"
						+ "       '分专管员、税种列表' l2,\n"
						+ "       '分专管员、税种列表' l3\n"
						+ "from dual\n"
						+ "union all\n"
						+ "select nvl(ry.mc,'合计') l1,\n"
						+ "nvl(xm.mc,'合计') l2,\n"
						+ "''||sum(yz.sbje) l3\n"
						+ "from t_zg_sb_sbfltj yz,\n"
						+ "t_dm_gy_zsxm xm,\n"
						+ "t_dm_gy_swry ry\n"
						+ "where yz.zsxm_dm=xm.zsxm_dm\n"
						+ "and yz.zgy=ry.swry_dm(+)\n"
						+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
						+ str + " group by rollup(ry.mc,xm.mc)";

				break;
			}

			break;

		case 10:// 管户分析总表
			// 人员权限范围代码段 开始
			if (zgy.length() > 0) {
				str = " and xx.zgy_dm='" + swry_dm + "'"
						+ " and EXISTS (SELECT 1\n" + " FROM T_XT_SWRY_CX CX\n"
						+ " WHERE xx.GLJG_DM=CX.SWJG_DM\n"
						+ " AND CX.SWRY_DM ='" + swry_dm + "')";
			} else {
				if (gljg_tj.length() > 0) {
					str = " and EXISTS (SELECT 1\n" + " FROM T_XT_SWRY_CX CX\n"
							+ " WHERE xx.GLJG_DM=CX.SWJG_DM\n"
							+ " AND CX.SWRY_DM ='" + swry_dm + "')";
				} else {
					str = "";
				}

			}

			
			// 人员权限范围代码段 完成
			sql = "---税收管户\n"
					+ "select '税收管户(剔除注销、内部户)' l1,\n"
					+ "       '税收管户(剔除注销、内部户)' l2,\n"
					+ "       '税收管户(剔除注销、内部户)' l3\n"
					+ "from dual\n"
					+ "union all\n"
					+ "select nvl(zt.mc,'合计') l1,\n"
					+ "       nvl(lx.mc,'小计') l2,\n"
					+ "       ''||count(1) l3\n"
					+ "from t_dj_nsrxx xx,\n"
					+ "t_dm_gy_nsrzt zt,\n"
					+ "(select distinct WSSPXMXL_DM,mc  from t_dm_gy_wsspxmxl ) lx\n"
					+ "where xx.djlx_dm=lx.wsspxmxl_dm\n"
					+ "and xx.dj_zt=zt.nsrzt_dm\n"
					+ "and xx.djlx_dm not in ('0122','0104','0129')\n"
					+ "and xx.dj_zt not in ('50','51','52')\n";
			sql = sql + str + "group by rollup(zt.mc,lx.mc)\n" + "union all\n"
					+ "select '社保管户(剔除注销)' l1,\n" + "       '社保管户(剔除注销)' l2,\n"
					+ "       '社保管户(剔除注销)' l3\n" + "from dual\n"
					+ "union all\n" + "select nvl(fl.mc,'合计') l1,\n"
					+ "      nvl(lx.mc,'小计') l2,\n" + "      ''||count(1) l3\n"
					+ "from\n" + "(\n" + "        select xx.nsrnbm,\n"
					+ "          xx.DJLX_DM,\n" + "          xx.zclx_dm,\n"
					+ "          substr(xx.hy_dm,1,2) hy,\n"
					+ "          dw.SBDJ_ZT,\n" + "          dw.JFDWLX_DM,\n"
					+ "          0 as fl\n" + "        from t_dj_nsrxx xx,\n"
					+ "        T_SFDJ_DWJFDJXX dw\n"
					+ "        where xx.nsrnbm=dw.nsrnbm\n"
					+ "        and xx.dj_zt not in ('50','51','52')\n"
					+ "        and dw.sbdj_zt='10'\n";
			sql = sql + str + "        union all\n"
					+ "        select xx.nsrnbm,\n" + "          xx.DJLX_DM,\n"
					+ "          xx.zclx_dm,\n"
					+ "          substr(xx.hy_dm,1,2) hy,\n"
					+ "          dw.SBDJ_ZT,\n" + "          dw.JFDWLX_DM,\n"
					+ "          1 as fl\n" + "        from t_dj_nsrxx xx,\n"
					+ "        T_SFDJ_SBGRXX_KZ dw\n"
					+ "        where xx.nsrnbm=dw.nsrnbm\n"
					+ "        and xx.dj_zt not in ('50','51','52')\n"
					+ "        and dw.sbdj_zt='10'\n";
			sql = sql
					+ str
					+ ")hj,\n"
					+ " (select distinct WSSPXMXL_DM,mc  from t_dm_gy_wsspxmxl ) lx,\n"
					+ " (select '0' dm, '单位纳税人' mc from dual\n"
					+ "  union all\n"
					+ "  select '1' , '灵活就业人员' from dual\n"
					+ " ) fl\n"
					+ "where hj.djlx_dm=lx.wsspxmxl_dm\n"
					+ "and hj.fl=fl.dm\n"
					+ "group by rollup(fl.mc,lx.mc)\n"
					+ "union all\n"
					+ "select '所有管户(包含注销、内部户)' l1,\n"
					+ "       '所有管户(包含注销、内部户)' l2,\n"
					+ "       '所有管户(包含注销、内部户)' l3\n"
					+ "from dual\n"
					+ "union all\n"
					+ "select nvl(zt.mc,'合计') l1,\n"
					+ "       nvl(lx.mc,'小计') l2,\n"
					+ "       ''||count(1) l3\n"
					+ "from t_dj_nsrxx xx,\n"
					+ "t_dm_gy_nsrzt zt,\n"
					+ "(select distinct WSSPXMXL_DM,mc  from t_dm_gy_wsspxmxl ) lx\n"
					+ "where xx.djlx_dm=lx.wsspxmxl_dm\n"
					+ "and xx.dj_zt=zt.nsrzt_dm\n";
			sql = sql + str + "group by rollup(zt.mc,lx.mc)";

			break;
		case 19:// 管户分机构
			// 人员权限范围代码段 开始
			if (zgy.length() > 0) {
				str = " and xx.zgy_dm='" + swry_dm + "'"
						+ " and EXISTS (SELECT 1\n" + " FROM T_XT_SWRY_CX CX\n"
						+ " WHERE xx.GLJG_DM=CX.SWJG_DM\n"
						+ " AND CX.SWRY_DM ='" + swry_dm + "')";
			} else {
				if (gljg_tj.length() > 0) {
					str = " and EXISTS (SELECT 1\n" + " FROM T_XT_SWRY_CX CX\n"
							+ " WHERE xx.GLJG_DM=CX.SWJG_DM\n"
							+ " AND CX.SWRY_DM ='" + swry_dm + "')";
				} else {
					str = "";
				}

			}
			// 人员权限范围代码段 完成
			sql = "SELECT jg2.mc_j sjmc,\n"
					+ "      jg1.mc_j xqjmc,\n"
					+ "      jg.mc_j fjmc,\n"
					+ "       nvl(ry.mc,'---') zgymc,\n"
					+ "      lx.mc szmc,\n"
					+ "      count(1) je\n"
					+ "from t_dj_nsrxx xx,\n"
					+ "  t_dm_gy_swjg jg,\n"
					+ "  t_dm_gy_swjg jg1,\n"
					+ "  t_dm_gy_swjg jg2,\n"
					+ "  t_dm_gy_swry ry,\n"
					+ "(select distinct WSSPXMXL_DM,mc  from t_dm_gy_wsspxmxl ) lx\n"
					+ "where xx.djlx_dm=lx.wsspxmxl_dm\n"
					+ "and xx.gljg_dm=jg.swjg_dm\n"
					+ "and jg.sjswjg_dm=jg1.swjg_dm\n"
					+ "and jg1.sjswjg_dm=jg2.swjg_dm\n"
					+ "and xx.zgy_dm=ry.swry_dm(+)\n"
					+ "and xx.djlx_dm not in ('0122','0104','0129')\n"
					+ "and xx.dj_zt not in ('50','51','52')\n";
			sql = sql + str
					+ " group by jg2.mc_j,jg1.mc_j,jg.mc_j , ry.mc,lx.mc";

			switch (jgjc) {
			case 2:// 省级
				sql = "select '分市州、县区列表' l1,\n" + "       '分市州、县区列表' l2,\n"
						+ "       '分市州、县区列表' l3\n" + "from dual\n"
						+ "union all\n" + "select nvl(sjmc,'合计') l1,\n"
						+ "       nvl(xqjmc,'小计') l2,\n"
						+ "       ''||sum(je) l3\n" + "from\n" + "(" + sql
						+ ")\n" + "group by rollup(sjmc,xqjmc)";
				break;
			case 4:// 市级
				sql = "select '分县区、税所列表' l1,\n" + "       '分县区、税所列表' l2,\n"
						+ "       '分县区、税所列表' l3\n" + "from dual\n"
						+ "union all\n" + "select nvl(xqjmc,'合计') l1,\n"
						+ "       nvl(fjmc,'小计') l2,\n"
						+ "       ''||sum(je) l3\n" + "from\n" + "(" + sql
						+ ")\n" + "group by rollup(xqjmc,fjmc)";

				break;
			case 6:// 县级
				sql = "select '分税所、专管员列表' l1,\n" + "       '分税所、专管员列表' l2,\n"
						+ "       '分税所、专管员列表' l3\n" + "from dual\n"
						+ "union all\n" + " select nvl(fjmc,'合计') l1,\n"
						+ "       nvl(zgymc,'小计') l2,\n"
						+ "       ''||sum(je) l3\n" + "from\n" + "(" + sql
						+ ")\n" + "group by rollup(fjmc,zgymc)";

				break;
			case 8:// 所级
				sql = "select '分专管员、登记类型列表' l1,\n"
						+ "       '分专管员、登记类型列表' l2,\n"
						+ "       '分专管员、登记类型列表' l3\n" + "from dual\n"
						+ "union all\n" + "select nvl(zgymc,'合计') l1,\n"
						+ "       nvl(szmc,'小计') l2,\n"
						+ "       ''||sum(je) l3\n" + "from\n" + "(" + sql
						+ ")\n" + "group by rollup(zgymc,szmc)";

				break;
			}

			break;

		case 20:// 征收分析总表
			// 人员权限范围代码段 开始
			// 人员权限范围代码段 开始
			if (zgy.length() > 0) {
				str = " and zgy_dm='" + swry_dm + "'";
			} else {
				if (gljg_tj.length() > 0) {
					switch (ryjc) {
					case 4:// 市级人员

						str = " and sjjg ='" + swjg + "'";
						break;
					case 6:// 县级人员
						str = " and hsjg ='" + swjg + "'";
						break;
					case 8:// 所级人员
						str = " and gljg ='" + swjg + "'";
						break;
					}

				} else {
					str = "";
				}

			}

			sql = "select nvl(sb.tj_sj,'合计') l1,\n"
					+ "nvl(xm.mc,'小计') l2,\n"
					+ "''||sum(sb.kpje) l3\n"
					+ "from t_zg_sb_sbfltj sb,\n"
					+ "t_dm_gy_zsxm xm\n"
					+ "where sb.zsxm_dm=xm.zsxm_dm\n"
					+ "and sb.tj_sj>=to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
					+ str + "group by rollup(tj_sj,xm.mc)";

			break;

		case 29:// 征收分单位
			// 人员权限范围代码段 开始
			if (zgy.length() > 0) {
				str = " and zgy_dm='" + swry_dm + "'";
			} else {
				if (gljg_tj.length() > 0) {
					switch (ryjc) {
					case 4:// 市级人员

						str = " and sjjg ='" + swjg + "'";
						break;
					case 6:// 县级人员
						str = " and hsjg ='" + swjg + "'";
						break;
					case 8:// 所级人员
						str = " and gljg ='" + swjg + "'";
						break;
					}

				} else {
					str = "";
				}

			}

			switch (jgjc) {
			case 2:// 省级
				sql = "select '分市州、县区列表' l1,\n"
						+ "       '分市州、县区列表' l2,\n"
						+ "       '分市州、县区列表' l3\n"
						+ "from dual\n"
						+ "union all\n"
						+ "select nvl(jg.mc,'合计') l1,\n"
						+ "nvl(jg1.mc,'合计') l2,\n"
						+ "''||sum(yz.kpje) l3\n"
						+ "from t_zg_sb_sbfltj yz,\n"
						+ "t_dm_gy_swjg jg,\n"
						+ "t_dm_gy_swjg jg1\n"
						+ "where yz.sjjg=jg.swjg_dm\n"
						+ "and yz.hsjg=jg1.swjg_dm\n"
						+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
						+ str + " group by rollup(jg.mc,jg1.mc)";
				break;
			case 4:// 市级
				sql = "select '分县区、税所列表' l1,\n"
						+ "       '分县区、税所列表' l2,\n"
						+ "       '分县区、税所列表' l3\n"
						+ "from dual\n"
						+ "union all\n"
						+ "select nvl(jg.mc,'合计') l1,\n"
						+ "nvl(jg1.mc,'合计') l2,\n"
						+ "''||sum(yz.kpje) l3\n"
						+ "from t_zg_sb_sbfltj yz,\n"
						+ "t_dm_gy_swjg jg,\n"
						+ "t_dm_gy_swjg jg1\n"
						+ "where yz.hsjg=jg.swjg_dm\n"
						+ "and yz.gljg=jg1.swjg_dm\n"
						+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
						+ str + " group by rollup(jg.mc,jg1.mc)";

				break;
			case 6:// 县级
				sql = "select '分税所、专管员列表' l1,\n"
						+ "       '分税所、专管员列表' l2,\n"
						+ "       '分税所、专管员列表' l3\n"
						+ "from dual\n"
						+ "union all\n"
						+ "select nvl(jg.mc,'合计') l1,\n"
						+ "nvl(ry.mc,'合计') l2,\n"
						+ "''||sum(yz.kpje) l3\n"
						+ "from t_zg_sb_sbfltj yz,\n"
						+ "t_dm_gy_swjg jg,\n"
						+ "t_dm_gy_swry ry\n"
						+ "where yz.gljg=jg.swjg_dm\n"
						+ "and yz.zgy=ry.swry_dm(+)\n"
						+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
						+ str + " group by rollup(jg.mc,ry.mc)";

				break;
			case 8:// 所级
				sql = "select '分专管员、税种列表' l1,\n"
						+ "       '分专管员、税种列表' l2,\n"
						+ "       '分专管员、税种列表' l3\n"
						+ "from dual\n"
						+ "union all\n"
						+ "select nvl(ry.mc,'合计') l1,\n"
						+ "nvl(xm.mc,'合计') l2,\n"
						+ "''||sum(yz.kpje) l3\n"
						+ "from t_zg_sb_sbfltj yz,\n"
						+ "t_dm_gy_zsxm xm,\n"
						+ "t_dm_gy_swry ry\n"
						+ "where yz.zsxm_dm=xm.zsxm_dm\n"
						+ "and yz.zgy=ry.swry_dm(+)\n"
						+ "and yz.tj_sj >= to_char(trunc(sysdate, 'yyyy'),'yyyy-mm')\n"
						+ str + " group by rollup(ry.mc,xm.mc)";

				break;
			}

			break;

		case 30:// 入库分析，税款属性2
			// 人员权限范围代码段 开始
			str = "";
			if (zgy.length() > 0) {
				str = " and zgy_dm='" + swry_dm + "'";
			} else {
				if (gljg_tj.length() > 0) {
					switch (ryjc) {
					case 4:// 市级人员

						str = " and rk.sjswjg_dm ='" + swjg + "'";
						break;
					case 6:// 县级人员
						str = " and rk.hsjg_dm ='" + swjg + "'";
						break;
					case 8:// 所级人员
						str = " and rk.gljg_dm ='" + swjg + "'";
						break;
					}

				} else {
					str = "";
				}

			}

			sql = "select '分税种、行业列表' l1,\n" + "       '分税种、行业列表' l2,\n"
					+ "       '分税种、行业列表' l3\n" + "from dual\n" + "union all\n"
					+ "select nvl(xm.mc,'合计') l1,\n" + "nvl(hy.mc,'小计')　l2,\n"
					+ "''||sum(rk.zje) l3\n" + "from t_zg_rk_jgjctj rk,\n"
					+ "t_dm_gy_zsxm xm,\n" + "t_dm_gy_hy hy\n"
					+ "where rk.zsxm_dm=xm.zsxm_dm\n"
					+ "and rk.hy_dm=hy.hy_dm\n"
					+ "and rk.tj_sj >= trunc(sysdate, 'yyyy')\n" + str
					+ " group by rollup(xm.mc,hy.mc)";

			break;
		case 31:// 分地区

			// 人员权限范围代码段 开始
			if (zgy.length() > 0) {
				str = " and zgy_dm='" + swry_dm + "'";
			} else {
				if (gljg_tj.length() > 0) {
					switch (ryjc) {
					case 4:// 市级人员

						str = " and yz.sjswjg_dm ='" + swjg + "'";
						break;
					case 6:// 县级人员
						str = " and yz.hsjg_dm ='" + swjg + "'";
						break;
					case 8:// 所级人员
						str = " and yz.gljg_dm ='" + swjg + "'";
						break;
					}

				} else {
					str = "";
				}

			}

			switch (jgjc) {
			case 2:// 省级
				sql = "select '分市州、县区列表' l1,\n" + "       '分市州、县区列表' l2,\n"
						+ "       '分市州、县区列表' l3\n" + "from dual\n"
						+ "union all\n" + "select nvl(jg.mc,'合计') l1,\n"
						+ "nvl(jg1.mc,'合计') l2,\n" + "''||sum(yz.zje) l3\n"
						+ "from t_zg_rk_jgjctj yz,\n" + "t_dm_gy_swjg jg,\n"
						+ "t_dm_gy_swjg jg1\n"
						+ "where yz.sjswjg_dm=jg.swjg_dm\n"
						+ "and yz.hsjg_dm=jg1.swjg_dm\n"
						+ "and yz.tj_sj >= trunc(sysdate, 'yyyy')\n" + str
						+ " group by rollup(jg.mc,jg1.mc)";

				break;
			case 4:// 市级
				sql = "select '分县区、税所列表' l1,\n" + "       '分县区、税所列表' l2,\n"
						+ "       '分县区、税所列表' l3\n" + "from dual\n"
						+ "union all\n" + "select nvl(jg.mc,'合计') l1,\n"
						+ "nvl(jg1.mc,'合计') l2,\n" + "''||sum(yz.zje) l3\n"
						+ "from t_zg_rk_jgjctj yz,\n" + "t_dm_gy_swjg jg,\n"
						+ "t_dm_gy_swjg jg1\n"
						+ "where yz.hsjg_dm=jg.swjg_dm\n"
						+ "and yz.gljg_dm=jg1.swjg_dm\n"
						+ "and yz.tj_sj >= trunc(sysdate, 'yyyy')\n" + str
						+ " group by rollup(jg.mc,jg1.mc)";

				break;
			case 6:// 县级
				sql = "select '分税所、专管员列表' l1,\n" + "       '分税所、专管员列表' l2,\n"
						+ "       '分税所、专管员列表' l3\n" + "from dual\n"
						+ "union all\n" + "select nvl(jg.mc,'合计') l1,\n"
						+ "nvl(ry.mc,'合计') l2,\n" + "''||sum(yz.zje) l3\n"
						+ "from t_zg_rk_jgjctj yz,\n" + "t_dm_gy_swjg jg,\n"
						+ "t_dm_gy_swry ry\n" + "where yz.gljg_dm=jg.swjg_dm\n"
						+ "and yz.zgy_dm=ry.swry_dm(+)\n"
						+ "and yz.tj_sj >= trunc(sysdate, 'yyyy')\n" + str
						+ " group by rollup(jg.mc,ry.mc)";

				break;
			case 8:// 所级
				sql = "select '专管员、税种列表' l1,\n" + "       '专管员、税种列表' l2,\n"
						+ "       '专管员、税种列表' l3\n" + "from dual\n"
						+ "union all\n" + "select nvl(ry.mc,'合计') l1,\n"
						+ "nvl(xm.mc,'合计') l2,\n" + "''||sum(yz.zje) l3\n"
						+ "from t_zg_rk_jgjctj yz,\n" + "t_dm_gy_zsxm xm,\n"
						+ "t_dm_gy_swry ry\n" + "where yz.zsxm_dm=xm.zsxm_dm\n"
						+ "and yz.zgy_dm=ry.swry_dm(+)\n"
						+ "and yz.tj_sj >= trunc(sysdate, 'yyyy')\n" + str
						+ " group by rollup(ry.mc,xm.mc)";

				break;
			}

			break;

		}

		try {
			// conn = util.openConnection();

			ResultSet rs = SQLHelper.getResultSet(sql);
			List<PubView> List = new ArrayList<PubView>();
			while (rs!=null&&rs.next()) {
				PubView dfsxx = new PubView();
				dfsxx.setL1(rs.getString("l1"));

				dfsxx.setL2(rs.getString("l2"));
				dfsxx.setL3(rs.getString("l3"));
				List.add(dfsxx);
			}
			return List;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// 根据发票号码和发票代码段，返回该票的流向信息
	// 王朝玉增加
	@Override
	public List<Fplist> getFpList(String fphm, String fpdm) {
		// TODO Auto-generated method stub

		/*
		 * 
		 * select zt, rq, fcjg.mc fcjg_dm, fcckmc, fcr.mc fcr_dm, jsjg.mc
		 * jsjg_dm, jsckmc, jsr.mc jsr_dm, nsrmc from ( select '库存' zt,
		 * rkd.zd_rq rq, '' fcjg_dm, '' fcckmc, '' fcr_dm, rkd.jsdw_dm jsjg_dm,
		 * ck.ck_mc jsckmc, rkd.ysr_dm jsr_dm, '' nsrmc from t_fp_fprkd rkd,
		 * t_fp_fpdjmx mx, t_fp_fpwlck ck where ck.ck_dm = rkd.jsck_dm and
		 * rkd.fprkd_xh = mx.fpdj_xh and mx.fpdj_lx = '0' and mx.fp_dm =
		 * '262000800330' and mx.fp_qshm <= '00037605' and mx.fp_zzhm >=
		 * '00037605' and nvl(rkd.rkqr_bj, 0) = '1' union all select '调拨' zt,
		 * dbd.zd_rq rq, dbd.fcjg_dm fcjg_dm, ck1.ck_mc fcckmc, dbd.fcfzr_dm
		 * fcr_dm, dbd.jsdw_dm jsjg_dm, ck2.ck_mc jsckmc, dbd.jsfzr_dm jsr_dm,
		 * '' nsrmc from t_fp_fpdbd dbd, t_fp_fpdjmx mx, t_fp_fpwlck ck1,
		 * t_fp_fpwlck ck2 where ck1.ck_dm = dbd.fcck_dm and ck2.ck_dm =
		 * dbd.jsck_dm and dbd.fpdbdbh_xh = mx.fpdj_xh and mx.fpdj_lx = '1' and
		 * mx.fp_dm = '262000800330' and mx.fp_qshm <= '00037605' and mx.fp_zzhm
		 * >= '00037605' and nvl(dbd.rkqr_bj, 0) = '1' union all select '发售' zt,
		 * pz.xs_rq rq, pz.fsjg_dm fcjg_dm, ck.ck_mc fcckmc, pz.jsr_dm fcr_dm,
		 * '' jsjg_dm, '' jsckmc, '' jsr_dm, nsr.nsr_mc nsrmc from t_fp_fpfspz
		 * pz, t_fp_fpfspzmx pzmx, t_dj_nsrxx nsr, t_fp_fpwlck ck where ck.ck_dm
		 * = pz.ck_dm and nsr.nsrnbm = pz.nsrnbm and pz.fpfspz_xh =
		 * pzmx.fpfspz_xh and pzmx.fp_dm = '262000800330' and pzmx.fp_qshm <=
		 * '00037605' and pzmx.fp_zzhm >= '00037605' and nvl(pz.zf_bj, 0) = 0
		 * union all select '验销' zt, yx.yx_sj rq, '' fcjg_dm, '' fcckmc, ''
		 * fcr_dm, yx.swjg_dm jsjg_dm, '' jsckmc, yx.swypr_dm jsr_dm, '' nsrmc
		 * from t_fp_fpyx yx, t_fp_fpyxmx yxmx where yx.yxpz_xh = yxmx.yxpz_xh
		 * and yxmx.fp_dm = '262000800330' and yxmx.fp_qshm <= '00037605' and
		 * yxmx.fp_zzhm >= '00037605' and nvl(yx.zf_bj, 0) = 0 union all select
		 * '核销' zt, hx.hx_rq rq, hx.swjg_dm fcjg_dm, ck.ck_mc fcckmc, hx.hxr_dm,
		 * '' jsjg_dm, '' jsckmc, '' jsr_dm, '' nsrmc from t_fp_fphx hx,
		 * t_fp_fphxmx hxmx, t_fp_fpwlck ck where ck.ck_dm = hx.ck_dm and hx.wsh
		 * = hxmx.wsh and hxmx.fp_dm = '262000800330' and hxmx.fp_qshm <=
		 * '00037605' and hxmx.fp_zzhm >='00037605' and nvl(hx.pz_bj, 0) = '1' )
		 * aa, t_dm_gy_swjg fcjg, t_dm_gy_swjg jsjg, t_dm_gy_swry fcr,
		 * t_dm_gy_swry jsr where aa.fcjg_dm=fcjg.swjg_dm(+) and
		 * aa.jsjg_dm=jsjg.swjg_dm(+) and aa.fcr_dm =fcr.swry_dm(+) and
		 * aa.jsr_dm=jsr.swry_dm(+);
		 */
		sql = "select\n"
				+ "  zt,\n"
				+ "  rq,\n"
				+ "  fcjg.mc fcjg_dm,\n"
				+ "  fcckmc,\n"
				+ "  fcr.mc fcr_dm,\n"
				+ "  jsjg.mc jsjg_dm,\n"
				+ "  jsckmc,\n"
				+ "  jsr.mc jsr_dm,\n"
				+ "  nsrmc\n"
				+ "from\n"
				+ "(\n"
				+ "      select '库存' zt,\n"
				+ "             rkd.zd_rq rq,\n"
				+ "             '' fcjg_dm,\n"
				+ "             '' fcckmc,\n"
				+ "             '' fcr_dm,\n"
				+ "             rkd.jsdw_dm jsjg_dm,\n"
				+ "             ck.ck_mc jsckmc,\n"
				+ "             rkd.ysr_dm jsr_dm,\n"
				+ "             '' nsrmc\n"
				+ "        from t_fp_fprkd rkd, t_fp_fpdjmx mx, t_fp_fpwlck ck\n"
				+ "       where ck.ck_dm = rkd.jsck_dm\n"
				+ "         and rkd.fprkd_xh = mx.fpdj_xh\n"
				+ "         and mx.fpdj_lx = '0'\n"
				+ "         and mx.fp_dm = ?\n"
				+ "         and mx.fp_qshm <= ?\n"
				+ "         and mx.fp_zzhm >= ?\n"
				+ "         and nvl(rkd.rkqr_bj, 0) = '1'\n"
				+ "      union all\n"
				+ "      select '调拨' zt,\n"
				+ "             dbd.zd_rq rq,\n"
				+ "             dbd.fcjg_dm fcjg_dm,\n"
				+ "             ck1.ck_mc fcckmc,\n"
				+ "             dbd.fcfzr_dm fcr_dm,\n"
				+ "             dbd.jsdw_dm jsjg_dm,\n"
				+ "             ck2.ck_mc jsckmc,\n"
				+ "             dbd.jsfzr_dm jsr_dm,\n"
				+ "             '' nsrmc\n"
				+ "        from t_fp_fpdbd dbd, t_fp_fpdjmx mx, t_fp_fpwlck ck1, t_fp_fpwlck ck2\n"
				+ "       where ck1.ck_dm = dbd.fcck_dm\n"
				+ "         and ck2.ck_dm = dbd.jsck_dm\n"
				+ "         and dbd.fpdbdbh_xh = mx.fpdj_xh\n"
				+ "         and mx.fpdj_lx = '1'\n"
				+ "         and mx.fp_dm = ?\n"
				+ "         and mx.fp_qshm <= ?\n"
				+ "         and mx.fp_zzhm >= ?\n"
				+ "         and nvl(dbd.rkqr_bj, 0) = '1'\n"
				+ "      union all\n"
				+ "      select '发售' zt,\n"
				+ "             pz.xs_rq rq,\n"
				+ "             pz.fsjg_dm fcjg_dm,\n"
				+ "             ck.ck_mc fcckmc,\n"
				+ "             pz.jsr_dm fcr_dm,\n"
				+ "             '' jsjg_dm,\n"
				+ "             '' jsckmc,\n"
				+ "             '' jsr_dm,\n"
				+ "             nsr.nsr_mc nsrmc\n"
				+ "        from t_fp_fpfspz pz, t_fp_fpfspzmx pzmx, t_dj_nsrxx nsr, t_fp_fpwlck ck\n"
				+ "       where ck.ck_dm = pz.ck_dm\n"
				+ "         and nsr.nsrnbm = pz.nsrnbm\n"
				+ "         and pz.fpfspz_xh = pzmx.fpfspz_xh\n"
				+ "         and pzmx.fp_dm = ?\n"
				+ "         and pzmx.fp_qshm <= ?\n"
				+ "         and pzmx.fp_zzhm >= ?\n"
				+ "         and nvl(pz.zf_bj, 0) = 0\n"
				+ "      union all\n"
				+ "      select '验销' zt,\n"
				+ "             yx.yx_sj rq,\n"
				+ "             '' fcjg_dm,\n"
				+ "             '' fcckmc,\n"
				+ "             '' fcr_dm,\n"
				+ "             yx.swjg_dm jsjg_dm,\n"
				+ "             '' jsckmc,\n"
				+ "             yx.swypr_dm jsr_dm,\n"
				+ "             '' nsrmc\n"
				+ "        from t_fp_fpyx yx, t_fp_fpyxmx yxmx\n"
				+ "       where yx.yxpz_xh = yxmx.yxpz_xh\n"
				+ "         and yxmx.fp_dm = ?\n"
				+ "         and yxmx.fp_qshm <= ?\n"
				+ "         and yxmx.fp_zzhm >= ?\n"
				+ "         and nvl(yx.zf_bj, 0) = 0\n"
				+ "      union all\n"
				+ "      select '核销' zt,\n"
				+ "             hx.hx_rq rq,\n"
				+ "             hx.swjg_dm fcjg_dm,\n"
				+ "             ck.ck_mc fcckmc,\n"
				+ "             hx.hxr_dm,\n"
				+ "             '' jsjg_dm,\n"
				+ "             '' jsckmc,\n"
				+ "             '' jsr_dm,\n"
				+ "             '' nsrmc\n"
				+ "        from t_fp_fphx hx, t_fp_fphxmx hxmx, t_fp_fpwlck ck\n"
				+ "       where ck.ck_dm = hx.ck_dm\n"
				+ "         and hx.wsh = hxmx.wsh\n"
				+ "         and hxmx.fp_dm = ?\n"
				+ "         and hxmx.fp_qshm <= ?\n"
				+ "         and hxmx.fp_zzhm >=?\n"
				+ "         and nvl(hx.pz_bj, 0) = '1'\n" + "   ) aa,\n"
				+ " t_dm_gy_swjg fcjg,\n" + " t_dm_gy_swjg jsjg,\n"
				+ " t_dm_gy_swry fcr,\n" + " t_dm_gy_swry jsr\n"
				+ "where aa.fcjg_dm=fcjg.swjg_dm(+)\n"
				+ "and aa.jsjg_dm=jsjg.swjg_dm(+)\n"
				+ "and aa.fcr_dm =fcr.swry_dm(+)\n"
				+ "and aa.jsr_dm=jsr.swry_dm(+)";

		List<Fplist> fplist = new ArrayList<Fplist>();
		// nsryhList=null;
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, fpdm, fphm, fphm, fpdm,
					fphm, fphm, fpdm, fphm, fphm, fpdm, fphm, fphm, fpdm, fphm,
					fphm);
			while (rs!=null&&rs.next()) {
				Fplist fp = new Fplist();

				fp.setZt(NullToString(rs.getString("zt")));
				fp.setRq(NullToString(rs.getString("rq")));
				fp.setFcjg_dm(NullToString(rs.getString("fcjg_dm")));
				fp.setFcckmc(NullToString(rs.getString("fcckmc")));
				fp.setFcr_dm(NullToString(rs.getString("fcr_dm")));
				fp.setJsjg_dm(NullToString(rs.getString("jsjg_dm")));
				fp.setJsckmc(NullToString(rs.getString("jsckmc")));
				fp.setJsr_dm(NullToString(rs.getString("jsr_dm")));
				fp.setNsrmc(NullToString(rs.getString("nsrmc")));

				fplist.add(fp);
			}
			return fplist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return fplist;
	}

	// 根据发票号码和发票代码段，返回开票明细信息
	// 王朝玉增加
	@Override
	public List<Fpfplist> getFpKpList(String fphm, String fpdm) {
		// TODO Auto-generated method stub
		/*
		 * select '货运代开' xm,a.shr sk, a.fhr || '(' || a.cyr || ')' fk, a.hj je
		 * from V_FP_HYDKFPXX a where a.JDDM = ? and a.jdhm = ? union all --货运自开
		 * select '货运自开' xm,a.shrmc sk, a.fhrmc || '(' || a.cyrmc || ')' fk,
		 * a.hj je from V_FP_HYZKFPXX a where a.FPDM = ? and a.FPHM = ? union
		 * all --销售不动产发票 select '销售不动产发票' xm,a.PAYER fk, a.PAYEE sk,
		 * a.INVOICEAMOUNT je from V_FP_XMRJFPXX a where a.WORDNO = ? and
		 * a.INVOICENO =? --建安发票 union all select '建安发票' xm,a.PAYER fk, a.PAYEE
		 * sk, a.INVOICEAMOUNT je from V_FP_XMRJFPXX a where a.WORDNO = ? and
		 * a.INVOICENO = ? union all -- 税务机关统一代开发票，根据金额进行校验 select '税务机关统一代开'
		 * xm,a.g_mc fk, a.nsrmc sk, a.fp_je je from t_fp_mqkp a where a.fp_dm =
		 * ? and a.fp_hm = ? and a.zf_bj = '0'
		 */
		sql =

		"select '货运代开' xm,a.shr sk, a.fhr || '(' || a.cyr || ')' fk, a.hj je\n"
				+ "  from V_FP_HYDKFPXX a\n"
				+ " where a.JDDM = ?\n"
				+ "   and a.jdhm = ?\n"
				+ "union all\n"
				+ "--货运自开\n"
				+ "select '货运自开' xm,a.shrmc sk, a.fhrmc || '(' || a.cyrmc || ')' fk, a.hj je\n"
				+ "  from V_FP_HYZKFPXX a\n"
				+ " where a.FPDM = ?\n"
				+ "   and a.FPHM = ?\n"
				+ "union all\n"
				+ "--销售不动产发票\n"
				+ "select '销售不动产发票' xm,a.PAYER fk, a.PAYEE sk, a.INVOICEAMOUNT je\n"
				+ "  from V_FP_XMRJFPXX a\n"
				+ " where a.WORDNO = ?\n"
				+ "   and a.INVOICENO =?\n"
				+ "--建安发票\n"
				+ "union all\n"
				+ "select '建安发票' xm,a.PAYER fk, a.PAYEE sk, a.INVOICEAMOUNT je\n"
				+ "  from V_FP_XMRJFPXX a\n" + " where a.WORDNO = ?\n"
				+ "   and a.INVOICENO = ?\n" + "union all\n"
				+ "-- 税务机关统一代开发票，根据金额进行校验\n"
				+ "select '税务机关统一代开' xm,a.g_mc fk, a.nsrmc sk, a.fp_je je\n"
				+ "  from t_fp_mqkp a\n" + " where a.fp_dm = ?\n"
				+ "   and a.fp_hm = ?\n" + "   and a.zf_bj = '0'";

		List<Fpfplist> fplist = new ArrayList<Fpfplist>();
		// nsryhList=null;
		try {

			ResultSet rs = SQLHelper.getResultSet(sql, fpdm, fphm, fpdm, fphm,
					fpdm, fphm, fpdm, fphm, fpdm, fphm);
			while (rs!=null&&rs.next()) {
				Fpfplist fp = new Fpfplist();

				fp.setXm(NullToString(rs.getString("xm")));
				fp.setFk(NullToString(rs.getString("fk")));
				fp.setSk(NullToString(rs.getString("sk")));
				fp.setJe(NullToString(rs.getString("je")));

				fplist.add(fp);
			}
			return fplist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return fplist;
	}

	// 根据税务人员编码返回单位人员所在机构的分级次入库信息
	// 王朝玉增加
	@Override
	public List<Rkfx_jc> getRk_jc(String swry) {
		// TODO Auto-generated method stub
		// 读取人员级次

		int ryjc = GetJgjc(swry);
		String swjg = GetSwjg(swry);

		String str = "";

		// 先判断是不是专管员
		String zgy = getIfZgy(swry);
		String gljg_tj = Gettj(swry);

		// 人员权限范围代码段 开始
		if (zgy.length() > 0) {
			str = " and zgy_dm='" + swry + "'";
		} else {
			if (gljg_tj.length() > 0) {
				switch (ryjc) {
				case 4:// 市级人员

					str = " and rk.sjswjg_dm ='" + swjg + "'";
					break;
				case 6:// 县级人员
					str = " and rk.hsjg_dm ='" + swjg + "'";
					break;
				case 8:// 所级人员
					str = " and rk.gljg_dm ='" + swjg + "'";
					break;
				}

			} else {
				str = "";
			}

		}
		switch (ryjc) {
		case 2:// 省级
			sql = "select nvl(jg.mc,'合计') xm ,\n" + "sum(zje) zje,\n"
					+ "sum(zyj) zyj,\n" + "sum(ssj) ssj,\n" + "sum(dsj) dsj,\n"
					+ "sum(qxj) qxj,\n" + "sum(xzj) xzj\n"
					+ "from t_zg_rk_jgjctj rk,\n" + "t_dm_gy_swjg jg\n"
					+ "where rk.tj_sj >= trunc(sysdate,'yyyy')\n"
					+ "and rk.sjswjg_dm=jg.swjg_dm\n" + str
					+ "group by rollup(jg.mc)\n" + "order by 2 desc";

			break;
		case 4:// 市级
			sql = "select nvl(jg.mc,'合计') xm ,\n" + "sum(zje) zje,\n"
					+ "sum(zyj) zyj,\n" + "sum(ssj) ssj,\n" + "sum(dsj) dsj,\n"
					+ "sum(qxj) qxj,\n" + "sum(xzj) xzj\n"
					+ "from t_zg_rk_jgjctj rk,\n" + "t_dm_gy_swjg jg\n"
					+ "where rk.tj_sj >= trunc(sysdate,'yyyy')\n"
					+ "and rk.hsjg_dm=jg.swjg_dm\n" + str
					+ "group by rollup(jg.mc)\n" + "order by 2 desc";

			break;
		case 6:// 县级
			sql = "select nvl(jg.mc,'合计') xm ,\n" + "sum(zje) zje,\n"
					+ "sum(zyj) zyj,\n" + "sum(ssj) ssj,\n" + "sum(dsj) dsj,\n"
					+ "sum(qxj) qxj,\n" + "sum(xzj) xzj\n"
					+ "from t_zg_rk_jgjctj rk,\n" + "t_dm_gy_swjg jg\n"
					+ "where rk.tj_sj >= trunc(sysdate,'yyyy')\n"
					+ "and rk.gljg_dm=jg.swjg_dm\n" + str
					+ "group by rollup(jg.mc)\n" + "order by 2 desc";

			break;
		case 8:// 所级
			sql = "select nvl(ry.mc,'合计') xm ,\n" + "sum(zje) zje,\n"
					+ "sum(zyj) zyj,\n" + "sum(ssj) ssj,\n" + "sum(dsj) dsj,\n"
					+ "sum(qxj) qxj,\n" + "sum(xzj) xzj\n"
					+ "from t_zg_rk_jgjctj rk,\n" + "t_dm_gy_swry ry\n"
					+ "where rk.tj_sj >= trunc(sysdate,'yyyy')\n"
					+ "and rk.zgy_dm=ry.swry_dm\n" + str
					+ "group by rollup(ry.mc)\n" + "order by 2 desc";

			break;
		}

		List<Rkfx_jc> fplist = new ArrayList<Rkfx_jc>();
		// nsryhList=null;
		try {

			ResultSet rs = SQLHelper.getResultSet(sql);
			while (rs!=null&&rs.next()) {
				Rkfx_jc fp = new Rkfx_jc();

				fp.setDw(NullToString(rs.getString("xm")));
				fp.setZje(NullToString(rs.getString("zje")));
				fp.setZyj(NullToString(rs.getString("zyj")));
				fp.setSsj(NullToString(rs.getString("ssj")));
				fp.setDsj(NullToString(rs.getString("dsj")));
				fp.setQxj(NullToString(rs.getString("qxj")));
				fp.setXzj(NullToString(rs.getString("xzj")));

				fplist.add(fp);
			}
			return fplist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return fplist;
	}

	// 根据发票号码和发票代码段，返回查询结果，这是最新查询
	// 王朝玉增加
	@Override
	public FpCx getFpCxList(String fphm, String fpdm, String num) {
		// TODO Auto-generated method stub
		FpCx cx = new FpCx();
		cx.setFp_dm(fpdm);
		cx.setFphm(fphm);
		cx.setNum(num);
		// 查询发票代码和号码是否存在

		// 根据发票代码查询出发表类型，按类型分别在不同的表中查询
		sql = "select dx.fpdl_dm\n" 
			+ "from t_dm_fp_fp fp,\n"
				+ "t_fp_fpzldzbforDx dx\n" 
				+ "where fp.fpzl_dm=dx.fpzl_dm\n"
				+ "and fp.fp_dm=?";
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, fpdm);
			if (rs.next()) {

				int kk = rs.getInt("fpdl_dm");
				String str = null;
				switch (kk) {
				// 1-定额发票，2-统一代开票，3-货运代开，4-货运自开，5-销售不动产发票，6-非定额票(填开式发票)，7-建安发票
				case 1:// 定额发票
						// 写发票种类标志
					cx.setFpzl("定额发票");
					// 先查是否发售给某个纳税人
					str = "select " + "      fp.fp_mc fpmc,\n"
							+ "      pzmx.fp_dm,\n" + "      pzmx.fp_qshm,\n"
							+ "      pzmx.fp_zzhm,\n" + "      pz.xs_rq rq,\n"
							+ "      jg.mc fcjg_dm,\n"
							+ "      nsr.nsr_mc nsrmc,\n" + "      nsr.nsrbm\n"
							+ " from t_fp_fpfspz pz,\n"
							+ " t_fp_fpfspzmx pzmx,\n" + " t_dj_nsrxx nsr,\n"
							+ " t_dm_gy_swjg jg,\n" + " t_dm_fp_fp fp\n"
							+ "where nsr.nsrnbm = pz.nsrnbm\n"
							+ "  and pz.fpfspz_xh = pzmx.fpfspz_xh\n"
							+ "  and pz.fsjg_dm=jg.swjg_dm\n"
							+ "  and pzmx.fp_dm=fp.fp_dm\n"
							+ "  and pzmx.fp_dm = ?\n"
							+ "  and pzmx.fp_qshm <= ?\n"
							+ "  and pzmx.fp_zzhm >= ?\n"
							+ "  and nvl(pz.zf_bj, 0) = 0";

					ResultSet rs1 = SQLHelper.getResultSet(str, fpdm, fphm,
							fphm);
					if (rs1!=null&&rs1.next()) {
						// 如果存在，说明已发售给某个纳税人
						cx.setFlag(1);// 说明发票验证存在

						FpDelist fp = new FpDelist();

						// 如果num=-1,说明是税务内部人员查询，不需要验证金额及密码信息
						if (!num.equals("-1")) {
							// 对于已发售的定额票，需要判断发票密码是否合适
							str = "select *\n" + "from mmb\n"
									+ "where fp_dm=?\n"
									+ "and fp_hm=? and fp_mm=?";

							ResultSet rs2 = SQLHelper.getResultSet(str, fpdm,
									fphm, MD5EncryptUtils.MD5Encode(num));

							if (rs2!=null&&rs2.next()) {
								// 发票密码合适
								// 发票名称
								fp.setFpmc(NullToString(rs1.getString("fpmc")));
								fp.setFp_dm(NullToString(rs1.getString("fp_dm")));
								fp.setFp_qshm(NullToString(rs1
										.getString("fp_qshm")));
								fp.setFp_zzhm(NullToString(rs1
										.getString("fp_zzhm")));
								fp.setRq(NullToString(rs1.getString("rq")));
								fp.setFcjg_dm(NullToString(rs1
										.getString("fcjg_dm")));
								fp.setNsrmc(NullToString(rs1.getString("nsrmc")));
								fp.setNsrbm(NullToString(rs1.getString("nsrbm")));
								cx.setBz("发票密码验证合适！");
							} else {
								// 发票密码不相符
								// 发票名称
								fp.setFpmc(StringSub(rs1.getString("fpmc")));
								fp.setFp_dm(StringSub(rs1.getString("fp_dm")));
								fp.setFp_qshm(StringSub(rs1
										.getString("fp_qshm")));
								fp.setFp_zzhm(StringSub(rs1
										.getString("fp_zzhm")));
								fp.setRq(StringSub(rs1.getString("rq")));
								fp.setFcjg_dm(StringSub(rs1
										.getString("fcjg_dm")));
								fp.setNsrmc(StringSub(rs1.getString("nsrmc")));
								fp.setNsrbm(StringSub(rs1.getString("nsrbm")));
								cx.setBz("发票密码与实际不符！");

							}

						} else {
							// 发票名称
							fp.setFpmc(NullToString(rs1.getString("fpmc")));
							fp.setFp_dm(NullToString(rs1.getString("fp_dm")));
							fp.setFp_qshm(NullToString(rs1.getString("fp_qshm")));
							fp.setFp_zzhm(NullToString(rs1.getString("fp_zzhm")));
							fp.setRq(NullToString(rs1.getString("rq")));
							fp.setFcjg_dm(NullToString(rs1.getString("fcjg_dm")));
							fp.setNsrmc(NullToString(rs1.getString("nsrmc")));
							fp.setNsrbm(NullToString(rs1.getString("nsrbm")));

						}
						cx.setDe(fp);

					} else {
						// 说明发票验证不存在
						// 对于非纳税人持票，再查询发票流向信息
						List l = getFpList1(fphm, fpdm);
						if (l.size() > 0) {
							cx.setFlag(3);
							cx.setFplx(l);
							cx.setBz("此发票还在税务机关库存调拨过程中，尚未开具！");

						} else {
							cx.setFlag(0);
							cx.setBz("你所查询的发票不存在！");
						}

					}

					break;
				case 2:// 统一代开票
					cx.setFpzl("税务机关代开发票");
					// 先查征管
					str = "select a.g_mc fk, a.nsrmc sk, a.fp_je je, a.kp_sj rq, a.zf_bj zf\n"
							+ "  from t_fp_mqkp a\n"
							+ " where a.fp_dm = ?\n"
							+ "   and a.fp_hm = ?\n" ;

					ResultSet rszg = SQLHelper.getResultSet(str, fpdm, fphm);
					if (rszg!=null&&rszg.next()) {
						// 写发票种类标志
						cx.setFpzl("税务机关代开发票");
						// 在征管代开发票中存在
						cx.setFlag(2);
						Fpfplist fp = new Fpfplist();

						// 如果num=-1,说明是税务内部人员查询，不需要验证金额及密码信息
						if (!num.equals("-1")) {
							if (num.equals(rszg.getString("je").toString())) {
								fp.setFk(NullToString(rszg.getString("fk")));
								fp.setSk(NullToString(rszg.getString("sk")));
								fp.setJe(NullToString(rszg.getString("je")));
								fp.setRq(NullToString(rszg.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证合适！");
							} else {
								fp.setFk(StringSub(rszg.getString("fk")));
								fp.setSk(StringSub(rszg.getString("sk")));
								fp.setJe(StringSub(rszg.getString("je")));
								fp.setRq(StringSub(rszg.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证与实际不符！");

							}
							if (rszg.getString("zf").equals("0")){
								fp.setZt("正常票");
							}else{
								fp.setZt("作废票");
							}

						} else {

							fp.setFk(NullToString(rszg.getString("fk")));
							fp.setSk(NullToString(rszg.getString("sk")));
							fp.setJe(NullToString(rszg.getString("je")));
							fp.setRq(NullToString(rszg.getString("rq")));
							cx.setJkp(fp);
						}

					} else {
						// 如果征管和兰州自开票系统中不存在，需要查询该 发票是否发售给某个具体的纳税人
						str = "select " + "      fp.fp_mc fpmc,\n"
								+ "      pzmx.fp_dm,\n"
								+ "      pzmx.fp_qshm,\n"
								+ "      pzmx.fp_zzhm,\n"
								+ "      pz.xs_rq rq,\n"
								+ "      jg.mc fcjg_dm,\n"
								+ "      nsr.nsr_mc nsrmc,\n"
								+ "      nsr.nsrbm\n"
								+ " from t_fp_fpfspz pz,\n"
								+ " t_fp_fpfspzmx pzmx,\n"
								+ " t_dj_nsrxx nsr,\n" + " t_dm_gy_swjg jg,\n"
								+ " t_dm_fp_fp fp\n"
								+ "where nsr.nsrnbm = pz.nsrnbm\n"
								+ "  and pz.fpfspz_xh = pzmx.fpfspz_xh\n"
								+ "  and pz.fsjg_dm=jg.swjg_dm\n"
								+ "  and pzmx.fp_dm=fp.fp_dm\n"
								+ "  and pzmx.fp_dm = ?\n"
								+ "  and pzmx.fp_qshm <= ?\n"
								+ "  and pzmx.fp_zzhm >= ?\n"
								+ "  and nvl(pz.zf_bj, 0) = 0";

						ResultSet rsnsr = SQLHelper.getResultSet(str, fpdm,
								fphm, fphm);
						if (rsnsr!=null&&rsnsr.next()) {
							cx.setFpzl("统一发票－纳税人自开票");
							// 如果存在，说明已发售给某个纳税人
							cx.setFlag(1);// 说明发票验证存在

							FpDelist fp = new FpDelist();

							// 发票名称
							fp.setFpmc(NullToString(rsnsr.getString("fpmc")));
							fp.setFp_dm(NullToString(rsnsr.getString("fp_dm")));
							fp.setFp_qshm(NullToString(rsnsr
									.getString("fp_qshm")));
							fp.setFp_zzhm(NullToString(rsnsr
									.getString("fp_zzhm")));
							fp.setRq(NullToString(rsnsr.getString("rq")));
							fp.setFcjg_dm(NullToString(rsnsr
									.getString("fcjg_dm")));
							fp.setNsrmc(NullToString(rsnsr.getString("nsrmc")));
							fp.setNsrbm(NullToString(rsnsr.getString("nsrbm")));
							cx.setDe(fp);

						} else {
							// 说明发票验证不存在
							// 对于非纳税人持票，再查询发票流向信息
							List l = getFpList1(fphm, fpdm);
							if (l.size() > 0) {
								cx.setFlag(3);
								cx.setFplx(l);
								cx.setBz("此发票还在税务机关库存调拨过程中，尚未开具！");

							} else {
								cx.setFlag(0);
								cx.setBz("你所查询的发票不存在！");
							}

						}

					}

					break;
				case 3:// 货运代开
					cx.setFpzl("货运代开发票");
					str ="select shrmc sk,\n" +
					"fhrmc || '(' || cyrmc || ')' fk,\n" + 
					"     hj je,\n" + 
					"     kprq rq,\n" + 
					"      decode(zfr, null, 0, 1) zf\n" + 
					" from JS_SKSKJDK_HYFPMX@HYFPZK_ODS\n" + 
					"  where FPDM = ?\n" + 
					" and FPHM = ?\n" + 
					"union all\n" + 
					"select a.shr sk,\n" + 
					"      a.fhr || '(' || a.cyr || ')' fk,\n" + 
					"     a.hj je,\n" + 
					"    a.kprq rq,\n" + 
					"     decode(zfr, null, 0, 1) zf\n" + 
					" from bdhwysytyfp@HYFPDK_ODS a\n" + 
					" where a.jdDM = ?\n" + 
					"  and a.jdHM = ?";

					ResultSet rshy = SQLHelper.getResultSet(str, fpdm, fphm,fpdm, fphm);
					if (rshy!=null&&rshy.next()) {
						// 写发票种类标志
						cx.setFpzl("货运代开－税务机关代开");
						// 在征管代开发票中存在
						cx.setFlag(2);
						Fpfplist fp = new Fpfplist();
						// 如果num=-1,说明是税务内部人员查询，不需要验证金额及密码信息
						if (!num.equals("-1")) {
							if (num.equals(rshy.getString("je").toString())) {
								fp.setFk(NullToString(rshy.getString("fk")));
								fp.setSk(NullToString(rshy.getString("sk")));
								fp.setJe(NullToString(rshy.getString("je")));
								fp.setRq(NullToString(rshy.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证合适！");
							} else {
								fp.setFk(StringSub(rshy.getString("fk")));
								fp.setSk(StringSub(rshy.getString("sk")));
								fp.setJe(StringSub(rshy.getString("je")));
								fp.setRq(StringSub(rshy.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证与实际不符！");

							}

						} else {
							fp.setFk(NullToString(rshy.getString("fk")));
							fp.setSk(NullToString(rshy.getString("sk")));
							fp.setJe(NullToString(rshy.getString("je")));
							fp.setRq(NullToString(rshy.getString("rq")));
							cx.setJkp(fp);

						}
						if (rshy.getString("zf").equals("0")){
							fp.setZt("正常票");
						}else{
							fp.setZt("作废票");
						}
					} else {
						// 如果货运代开系统中不存在，需要查询该 发票是否发售给某个具体的纳税人
						str = "select " + "      fp.fp_mc fpmc,\n"
								+ "      pzmx.fp_dm,\n"
								+ "      pzmx.fp_qshm,\n"
								+ "      pzmx.fp_zzhm,\n"
								+ "      pz.xs_rq rq,\n"
								+ "      jg.mc fcjg_dm,\n"
								+ "      nsr.nsr_mc nsrmc,\n"
								+ "      nsr.nsrbm\n"
								+ " from t_fp_fpfspz pz,\n"
								+ " t_fp_fpfspzmx pzmx,\n"
								+ " t_dj_nsrxx nsr,\n" + " t_dm_gy_swjg jg,\n"
								+ " t_dm_fp_fp fp\n"
								+ "where nsr.nsrnbm = pz.nsrnbm\n"
								+ "  and pz.fpfspz_xh = pzmx.fpfspz_xh\n"
								+ "  and pz.fsjg_dm=jg.swjg_dm\n"
								+ "  and pzmx.fp_dm=fp.fp_dm\n"
								+ "  and pzmx.fp_dm = ?\n"
								+ "  and pzmx.fp_qshm <= ?\n"
								+ "  and pzmx.fp_zzhm >= ?\n"
								+ "  and nvl(pz.zf_bj, 0) = 0";

						ResultSet rsnsr = SQLHelper.getResultSet(str, fpdm,
								fphm, fphm);
						if (rsnsr!=null&&rsnsr.next()) {
							cx.setFpzl("货运代开发票－纳税人自开票");
							// 如果存在，说明已发售给某个纳税人
							cx.setFlag(1);// 说明发票验证存在

							FpDelist fp = new FpDelist();

							// 发票名称
							fp.setFpmc(NullToString(rsnsr.getString("fpmc")));
							fp.setFp_dm(NullToString(rsnsr.getString("fp_dm")));
							fp.setFp_qshm(NullToString(rsnsr
									.getString("fp_qshm")));
							fp.setFp_zzhm(NullToString(rsnsr
									.getString("fp_zzhm")));
							fp.setRq(NullToString(rsnsr.getString("rq")));
							fp.setFcjg_dm(NullToString(rsnsr
									.getString("fcjg_dm")));
							fp.setNsrmc(NullToString(rsnsr.getString("nsrmc")));
							fp.setNsrbm(NullToString(rsnsr.getString("nsrbm")));
							cx.setDe(fp);
						} else {

							// 说明发票验证不存在
							// 对于非纳税人持票，再查询发票流向信息
							List l = getFpList1(fphm, fpdm);
							if (l.size() > 0) {
								cx.setFlag(3);
								cx.setFplx(l);
								cx.setBz("此发票还在税务机关库存调拨过程中，尚未开具！");

							} else {
								cx.setFlag(0);
								cx.setBz("你所查询的发票不存在！");
							}

						}
					}

					break;
				case 4:// 货运代开
					cx.setFpzl("货运自开发票");
					str =
						"select shrmc sk,\n" +
						"fhrmc || '(' || cyrmc || ')' fk,\n" + 
						"     hj je,\n" + 
						"     kprq rq,\n" + 
						"      decode(zfr, null, 0, 1) zf\n" + 
						" from JS_SKSKJDK_HYFPMX@HYFPZK_ODS\n" + 
						"  where FPDM = ?\n" + 
						" and FPHM = ?\n" + 
						"union all\n" + 
						"select a.shr sk,\n" + 
						"      a.fhr || '(' || a.cyr || ')' fk,\n" + 
						"     a.hj je,\n" + 
						"    a.kprq rq,\n" + 
						"     decode(zfr, null, 0, 1) zf\n" + 
						" from bdhwysytyfp@HYFPDK_ODS a\n" + 
						" where a.jdDM = ?\n" + 
						"  and a.jdHM = ?";


					ResultSet rshyzk = SQLHelper.getResultSet(str, fpdm, fphm,fpdm, fphm);
					if (rshyzk!=null&&rshyzk.next()) {
						// 写发票种类标志
						cx.setFpzl("货运自开－纳税人自开票");
						// 在征管代开发票中存在
						cx.setFlag(2);
						Fpfplist fp = new Fpfplist();
						// 如果num=-1,说明是税务内部人员查询，不需要验证金额及密码信息
						if (!num.equals("-1")) {
							if (num.equals(rshyzk.getString("je").toString())) {
								fp.setFk(NullToString(rshyzk.getString("fk")));
								fp.setSk(NullToString(rshyzk.getString("sk")));
								fp.setJe(NullToString(rshyzk.getString("je")));
								fp.setRq(NullToString(rshyzk.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证合适！");
							} else {
								fp.setFk(StringSub(rshyzk.getString("fk")));
								fp.setSk(StringSub(rshyzk.getString("sk")));
								fp.setJe(StringSub(rshyzk.getString("je")));
								fp.setRq(StringSub(rshyzk.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证与实际不符！");

							}
							if (rshyzk.getString("zf").equals("0")){
								fp.setZt("正常票");
							}else{
								fp.setZt("作废票");
							}
						} else {
							fp.setFk(NullToString(rshyzk.getString("fk")));
							fp.setSk(NullToString(rshyzk.getString("sk")));
							fp.setJe(NullToString(rshyzk.getString("je")));
							fp.setRq(NullToString(rshyzk.getString("rq")));
							cx.setJkp(fp);

						}
					} else {
						// 如果货运代开系统中不存在，需要查询该 发票是否发售给某个具体的纳税人
						str = "select " + "      fp.fp_mc fpmc,\n"
								+ "      pzmx.fp_dm,\n"
								+ "      pzmx.fp_qshm,\n"
								+ "      pzmx.fp_zzhm,\n"
								+ "      pz.xs_rq rq,\n"
								+ "      jg.mc fcjg_dm,\n"
								+ "      nsr.nsr_mc nsrmc,\n"
								+ "      nsr.nsrbm\n"
								+ " from t_fp_fpfspz pz,\n"
								+ " t_fp_fpfspzmx pzmx,\n"
								+ " t_dj_nsrxx nsr,\n" + " t_dm_gy_swjg jg,\n"
								+ " t_dm_fp_fp fp\n"
								+ "where nsr.nsrnbm = pz.nsrnbm\n"
								+ "  and pz.fpfspz_xh = pzmx.fpfspz_xh\n"
								+ "  and pz.fsjg_dm=jg.swjg_dm\n"
								+ "  and pzmx.fp_dm=fp.fp_dm\n"
								+ "  and pzmx.fp_dm = ?\n"
								+ "  and pzmx.fp_qshm <= ?\n"
								+ "  and pzmx.fp_zzhm >= ?\n"
								+ "  and nvl(pz.zf_bj, 0) = 0";

						ResultSet rsnsr = SQLHelper.getResultSet(str, fpdm,
								fphm, fphm);
						if (rsnsr!=null&&rsnsr.next()) {
							cx.setFpzl("货运代开发票－纳税人自开票");
							// 如果存在，说明已发售给某个纳税人
							cx.setFlag(1);// 说明发票验证存在

							cx.setBz("此纳税人系自开票,由于数据上传的时差,数据有可能滞后,请过段时间再验证!");

							FpDelist fp = new FpDelist();

							// 发票名称
							fp.setFpmc(NullToString(rsnsr.getString("fpmc")));
							fp.setFp_dm(NullToString(rsnsr.getString("fp_dm")));
							fp.setFp_qshm(NullToString(rsnsr
									.getString("fp_qshm")));
							fp.setFp_zzhm(NullToString(rsnsr
									.getString("fp_zzhm")));
							fp.setRq(NullToString(rsnsr.getString("rq")));
							fp.setFcjg_dm(NullToString(rsnsr
									.getString("fcjg_dm")));
							fp.setNsrmc(NullToString(rsnsr.getString("nsrmc")));
							fp.setNsrbm(NullToString(rsnsr.getString("nsrbm")));
							cx.setDe(fp);
						} else {

							// 说明发票验证不存在
							// 对于非纳税人持票，再查询发票流向信息
							List l = getFpList1(fphm, fpdm);
							if (l.size() > 0) {
								cx.setFlag(3);
								cx.setFplx(l);
								cx.setBz("此发票还在税务机关库存调拨过程中，尚未开具！");

							} else {
								cx.setFlag(0);
								cx.setBz("你所查询的发票不存在！");
							}

						}
					}

					break;
				case 5:// 销售不动产发票
					cx.setFpzl("销售不动产发票");
					str = 
						"select a.PAYER         fk,\n" +
						"       a.PAYEE         sk,\n" + 
						"       a.INVOICEAMOUNT je,\n" + 
						"       fillindate       rq,\n" + 
						"       cancelsign      zf\n" + 
						"  from INVOICE_MAIN@XMRJ_ODS a\n" + 
						" where a.INVOICENO = ?\n" + 
						"   and a.wordno = ?";
;

					ResultSet rsbdc = SQLHelper.getResultSet(str, fphm, fpdm);
					if (rsbdc!=null&&rsbdc.next()) {
						// 写发票种类标志
						cx.setFpzl("销售不动产发票");
						// 在征管代开发票中存在
						cx.setFlag(2);
						Fpfplist fp = new Fpfplist();
						// 如果num=-1,说明是税务内部人员查询，不需要验证金额及密码信息
						if (!num.equals("-1")) {
							if (num.equals(rsbdc.getString("je").toString())) {
								fp.setFk(NullToString(rsbdc.getString("fk")));
								fp.setSk(NullToString(rsbdc.getString("sk")));
								fp.setJe(NullToString(rsbdc.getString("je")));
								fp.setRq(NullToString(rsbdc.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证合适！");
							} else {
								fp.setFk(StringSub(rsbdc.getString("fk")));
								fp.setSk(StringSub(rsbdc.getString("sk")));
								fp.setJe(StringSub(rsbdc.getString("je")));
								fp.setRq(StringSub(rsbdc.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证与实际不符！");

							}
							if (rsbdc.getString("zf").equals("0")){
								fp.setZt("正常票");
							}else{
								fp.setZt("作废票");
							}
						} else {
							fp.setFk(NullToString(rsbdc.getString("fk")));
							fp.setSk(NullToString(rsbdc.getString("sk")));
							fp.setJe(NullToString(rsbdc.getString("je")));
							fp.setRq(NullToString(rsbdc.getString("rq")));
							cx.setJkp(fp);

						}
					} else {
						// 如果货运代开系统中不存在，需要查询该 发票是否发售给某个具体的纳税人
						str = "select " + "      fp.fp_mc fpmc,\n"
								+ "      pzmx.fp_dm,\n"
								+ "      pzmx.fp_qshm,\n"
								+ "      pzmx.fp_zzhm,\n"
								+ "      pz.xs_rq rq,\n"
								+ "      jg.mc fcjg_dm,\n"
								+ "      nsr.nsr_mc nsrmc,\n"
								+ "      nsr.nsrbm\n"
								+ " from t_fp_fpfspz pz,\n"
								+ " t_fp_fpfspzmx pzmx,\n"
								+ " t_dj_nsrxx nsr,\n" + " t_dm_gy_swjg jg,\n"
								+ " t_dm_fp_fp fp\n"
								+ "where nsr.nsrnbm = pz.nsrnbm\n"
								+ "  and pz.fpfspz_xh = pzmx.fpfspz_xh\n"
								+ "  and pz.fsjg_dm=jg.swjg_dm\n"
								+ "  and pzmx.fp_dm=fp.fp_dm\n"
								+ "  and pzmx.fp_dm = ?\n"
								+ "  and pzmx.fp_qshm <= ?\n"
								+ "  and pzmx.fp_zzhm >= ?\n"
								+ "  and nvl(pz.zf_bj, 0) = 0";

						ResultSet rsnsr = SQLHelper.getResultSet(str, fpdm,
								fphm, fphm);
						if (rsnsr!=null&&rsnsr.next()) {
							cx.setFpzl("销售不动产发票－纳税人自开票");
							// 如果存在，说明已发售给某个纳税人
							cx.setFlag(1);// 说明发票验证存在

							FpDelist fp = new FpDelist();

							// 发票名称
							fp.setFpmc(NullToString(rsnsr.getString("fpmc")));
							fp.setFp_dm(NullToString(rsnsr.getString("fp_dm")));
							fp.setFp_qshm(NullToString(rsnsr
									.getString("fp_qshm")));
							fp.setFp_zzhm(NullToString(rsnsr
									.getString("fp_zzhm")));
							fp.setRq(NullToString(rsnsr.getString("rq")));
							fp.setFcjg_dm(NullToString(rsnsr
									.getString("fcjg_dm")));
							fp.setNsrmc(NullToString(rsnsr.getString("nsrmc")));
							fp.setNsrbm(NullToString(rsnsr.getString("nsrbm")));
							cx.setDe(fp);
						} else {

							// 说明发票验证不存在
							// 对于非纳税人持票，再查询发票流向信息
							List l = getFpList1(fphm, fpdm);
							if (l.size() > 0) {
								cx.setFlag(3);
								cx.setFplx(l);
								cx.setBz("此发票还在税务机关库存调拨过程中，尚未开具！");

							} else {
								cx.setFlag(0);
								cx.setBz("你所查询的发票不存在！");
							}

						}
					}

					break;
				case 6:// 通用发票
					cx.setFpzl("通用发票");
					// 查兰州市纳税人自开票
					str = "select khmc fk,\n" + " nsrbm sk,\n" + " hjje je,\n"
							+ " kprq rq,\n" + " fpzt zt,\n" + " kpr kpr\n"
							+ "from t_gs_fp_yyp\n" + "where fpdm=?\n"
							+ "and fphm=?";

					ResultSet rslz = SQLHelper.getResultSet(
							SQLHelper.getLzConn(), str, fpdm, fphm);
					if (rslz!=null&&rslz.next()) {
						cx.setFpzl("通用发票－纳税人自开票");
						PubDAOImpl pub = new PubDAOImpl();
						Nsrxx nsr = pub.getNsrByBm(rslz.getString("sk"));

						// 在兰州市机开系统中存在
						cx.setFlag(2);
						Fpfplist fp = new Fpfplist();
						// 如果num=-1,说明是税务内部人员查询，不需要验证金额及密码信息
						if (!num.equals("-1")) {
							if (num.equals(rslz.getString("je").toString())) {
								fp.setFk(NullToString(rslz.getString("fk")));
								fp.setSk(NullToString(nsr.getNsrmc()));
								fp.setJe(NullToString(rslz.getString("je")));
								fp.setRq(NullToString(rslz.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证合适！");
							} else {
								fp.setFk(StringSub(rslz.getString("fk")));
								fp.setSk(StringSub(nsr.getNsrmc()));
								fp.setJe(StringSub(rslz.getString("je")));
								fp.setRq(StringSub(rslz.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证与实际不符！");

							}

						} else {
							fp.setFk(NullToString(rslz.getString("fk")));
							fp.setSk(NullToString(nsr.getNsrmc()));
							fp.setJe(NullToString(rslz.getString("je")));
							fp.setRq(NullToString(rslz.getString("rq")));
							cx.setJkp(fp);

						}
						if (rslz.getString("zt").equals("02")){
							fp.setZt("正常票");
						}else{
							fp.setZt("作废票");
						}
					} else {
						// 如果货运代开系统中不存在，需要查询该 发票是否发售给某个具体的纳税人
						str = "select " + "      fp.fp_mc fpmc,\n"
								+ "      pzmx.fp_dm,\n"
								+ "      pzmx.fp_qshm,\n"
								+ "      pzmx.fp_zzhm,\n"
								+ "      pz.xs_rq rq,\n"
								+ "      jg.mc fcjg_dm,\n"
								+ "      nsr.nsr_mc nsrmc,\n"
								+ "      nsr.nsrbm\n"
								+ " from t_fp_fpfspz pz,\n"
								+ " t_fp_fpfspzmx pzmx,\n"
								+ " t_dj_nsrxx nsr,\n" + " t_dm_gy_swjg jg,\n"
								+ " t_dm_fp_fp fp\n"
								+ "where nsr.nsrnbm = pz.nsrnbm\n"
								+ "  and pz.fpfspz_xh = pzmx.fpfspz_xh\n"
								+ "  and pz.fsjg_dm=jg.swjg_dm\n"
								+ "  and pzmx.fp_dm=fp.fp_dm\n"
								+ "  and pzmx.fp_dm = ?\n"
								+ "  and pzmx.fp_qshm <= ?\n"
								+ "  and pzmx.fp_zzhm >= ?\n"
								+ "  and nvl(pz.zf_bj, 0) = 0";

						ResultSet rsnsr = SQLHelper.getResultSet(str, fpdm,
								fphm, fphm);
						if (rsnsr!=null&&rsnsr.next()) {
							cx.setFpzl("通用发票－纳税人自开票");
							// 如果存在，说明已发售给某个纳税人
							cx.setFlag(1);// 说明发票验证存在

							FpDelist fp = new FpDelist();

							// 发票名称
							fp.setFpmc(NullToString(rsnsr.getString("fpmc")));
							fp.setFp_dm(NullToString(rsnsr.getString("fp_dm")));
							fp.setFp_qshm(NullToString(rsnsr
									.getString("fp_qshm")));
							fp.setFp_zzhm(NullToString(rsnsr
									.getString("fp_zzhm")));
							fp.setRq(NullToString(rsnsr.getString("rq")));
							fp.setFcjg_dm(NullToString(rsnsr
									.getString("fcjg_dm")));
							fp.setNsrmc(NullToString(rsnsr.getString("nsrmc")));
							fp.setNsrbm(NullToString(rsnsr.getString("nsrbm")));
							cx.setDe(fp);

							sql = "select sfwskp\n"
									+ "from t_gs_fp_nsrzt\n"
									+ "where nsrbm=?";
							String zt = (String) SQLHelper.ExecScalar(
									SQLHelper.getLzConn(), sql,
									rsnsr.getString("nsrbm"));
							if (zt==null){
								cx.setBz("此票已发售至纳税人!");
							}else{
								if (zt.equals("1")) {
									//cx.setBz("此纳税人系统离线开票,数据上传可能有点滞后,请过段时间再查!");
								} else {
									cx.setBz("此票已发售至纳税人!");
								}				
							}


						} else {

							// 说明发票验证不存在
							// 对于非纳税人持票，再查询发票流向信息
							List l = getFpList1(fphm, fpdm);
							if (l.size() > 0) {
								cx.setFlag(3);
								cx.setFplx(l);
								cx.setBz("此发票还在税务机关库存调拨过程中，尚未开具！");

							} else {
								cx.setFlag(0);
								cx.setBz("你所查询的发票不存在！");
							}

						}
					}

					break;
				case 7:// 建安发票

					cx.setFpzl("建安发票");
					str = "select a.PAYER         fk,\n" +
					"       a.PAYEE         sk,\n" + 
					"       a.INVOICEAMOUNT je,\n" + 
					"       fillindate       rq,\n" + 
					"       cancelsign      zf\n" + 
					"  from INVOICE_MAIN@XMRJ_ODS a\n" + 
					" where a.INVOICENO = ?\n" + 
					"   and a.wordno = ?";;

					ResultSet rsja = SQLHelper.getResultSet(str, fphm, fpdm);
					if (rsja!=null&&rsja.next()) {
						// 写发票种类标志
						cx.setFpzl("建安发票");
						// 在征管代开发票中存在
						cx.setFlag(2);
						Fpfplist fp = new Fpfplist();
						// 如果num=-1,说明是税务内部人员查询，不需要验证金额及密码信息
						if (!num.equals("-1")) {
							if (num.equals(rsja.getString("je").toString())) {
								fp.setFk(NullToString(rsja.getString("fk")));
								fp.setSk(NullToString(rsja.getString("sk")));
								fp.setJe(NullToString(rsja.getString("je")));
								fp.setRq(NullToString(rsja.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证合适！");
							} else {
								fp.setFk(StringSub(rsja.getString("fk")));
								fp.setSk(StringSub(rsja.getString("sk")));
								fp.setJe(StringSub(rsja.getString("je")));
								fp.setRq(StringSub(rsja.getString("rq")));
								cx.setJkp(fp);
								cx.setBz("开票金额验证与实际不符！");

							}
							if (rsja.getString("zf").equals("0")){
								fp.setZt("正常票");
							}else{
								fp.setZt("作废票");
							}
						} else {
							fp.setFk(NullToString(rsja.getString("fk")));
							fp.setSk(NullToString(rsja.getString("sk")));
							fp.setJe(NullToString(rsja.getString("je")));
							fp.setRq(NullToString(rsja.getString("rq")));
							cx.setJkp(fp);

						}
					} else {
						// 如果货运代开系统中不存在，需要查询该 发票是否发售给某个具体的纳税人
						str = "select " + "      fp.fp_mc fpmc,\n"
								+ "      pzmx.fp_dm,\n"
								+ "      pzmx.fp_qshm,\n"
								+ "      pzmx.fp_zzhm,\n"
								+ "      pz.xs_rq rq,\n"
								+ "      jg.mc fcjg_dm,\n"
								+ "      nsr.nsr_mc nsrmc,\n"
								+ "      nsr.nsrbm\n"
								+ " from t_fp_fpfspz pz,\n"
								+ " t_fp_fpfspzmx pzmx,\n"
								+ " t_dj_nsrxx nsr,\n" 
								+ " t_dm_gy_swjg jg,\n"
								+ " t_dm_fp_fp fp\n"
								+ "where nsr.nsrnbm = pz.nsrnbm\n"
								+ "  and pz.fpfspz_xh = pzmx.fpfspz_xh\n"
								+ "  and pz.fsjg_dm=jg.swjg_dm\n"
								+ "  and pzmx.fp_dm=fp.fp_dm\n"
								+ "  and pzmx.fp_dm = ?\n"
								+ "  and pzmx.fp_qshm <= ?\n"
								+ "  and pzmx.fp_zzhm >= ?\n"
								+ "  and nvl(pz.zf_bj, 0) = 0";

						ResultSet rsnsr1 = SQLHelper.getResultSet(str, fpdm,
								fphm, fphm);
						if (rsnsr1!=null&&rsnsr1.next()) {
							cx.setFpzl("建安发票－纳税人自开票");
							// 如果存在，说明已发售给某个纳税人
							cx.setFlag(1);// 说明发票验证存在

							FpDelist fp = new FpDelist();

							// 发票名称
							fp.setFpmc(NullToString(rsnsr1.getString("fpmc")));
							fp.setFp_dm(NullToString(rsnsr1.getString("fp_dm")));
							fp.setFp_qshm(NullToString(rsnsr1
									.getString("fp_qshm")));
							fp.setFp_zzhm(NullToString(rsnsr1
									.getString("fp_zzhm")));
							fp.setRq(NullToString(rsnsr1.getString("rq")));
							fp.setFcjg_dm(NullToString(rsnsr1
									.getString("fcjg_dm")));
							fp.setNsrmc(NullToString(rsnsr1.getString("nsrmc")));
							fp.setNsrbm(NullToString(rsnsr1.getString("nsrbm")));
							cx.setDe(fp);
						} else {

							// 说明发票验证不存在
							// 对于非纳税人持票，再查询发票流向信息
							List l = getFpList1(fphm, fpdm);
							if (l.size() > 0) {
								cx.setFlag(3);
								cx.setFplx(l);
								cx.setBz("此发票还在税务机关库存调拨过程中，尚未开具！");

							} else {
								cx.setFlag(0);
								cx.setBz("你所查询的发票不存在！");
							}
						}
					}

					break;
				}

			} else {
				cx.setFlag(9);
				cx.setBz("发票代码不存在！");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return cx;
	}

	private List<Fplist> getFpList1(String fphm, String fpdm) {
		// TODO Auto-generated method stub

		sql = "select\n"
				+ "  zt,\n"
				+ "  rq,\n"
				+ "  fcjg.mc fcjg_dm,\n"
				+ "  fcckmc,\n"
				+ "  fcr.mc fcr_dm,\n"
				+ "  jsjg.mc jsjg_dm,\n"
				+ "  jsckmc,\n"
				+ "  jsr.mc jsr_dm,\n"
				+ "  nsrmc\n"
				+ "from\n"
				+ "(\n"
				+ "      select '库存' zt,\n"
				+ "             rkd.zd_rq rq,\n"
				+ "             '' fcjg_dm,\n"
				+ "             '' fcckmc,\n"
				+ "             '' fcr_dm,\n"
				+ "             rkd.jsdw_dm jsjg_dm,\n"
				+ "             ck.ck_mc jsckmc,\n"
				+ "             rkd.ysr_dm jsr_dm,\n"
				+ "             '' nsrmc\n"
				+ "        from t_fp_fprkd rkd, t_fp_fpdjmx mx, t_fp_fpwlck ck\n"
				+ "       where ck.ck_dm = rkd.jsck_dm\n"
				+ "         and rkd.fprkd_xh = mx.fpdj_xh\n"
				+ "         and mx.fpdj_lx = '0'\n"
				+ "         and mx.fp_dm = ?\n"
				+ "         and mx.fp_qshm <= ?\n"
				+ "         and mx.fp_zzhm >= ?\n"
				+ "         and nvl(rkd.rkqr_bj, 0) = '1'\n"
				+ "      union all\n"
				+ "      select '调拨' zt,\n"
				+ "             dbd.zd_rq rq,\n"
				+ "             dbd.fcjg_dm fcjg_dm,\n"
				+ "             ck1.ck_mc fcckmc,\n"
				+ "             dbd.fcfzr_dm fcr_dm,\n"
				+ "             dbd.jsdw_dm jsjg_dm,\n"
				+ "             ck2.ck_mc jsckmc,\n"
				+ "             dbd.jsfzr_dm jsr_dm,\n"
				+ "             '' nsrmc\n"
				+ "        from t_fp_fpdbd dbd, t_fp_fpdjmx mx, t_fp_fpwlck ck1, t_fp_fpwlck ck2\n"
				+ "       where ck1.ck_dm = dbd.fcck_dm\n"
				+ "         and ck2.ck_dm = dbd.jsck_dm\n"
				+ "         and dbd.fpdbdbh_xh = mx.fpdj_xh\n"
				+ "         and mx.fpdj_lx = '1'\n"
				+ "         and mx.fp_dm = ?\n"
				+ "         and mx.fp_qshm <= ?\n"
				+ "         and mx.fp_zzhm >= ?\n"
				+ "         and nvl(dbd.rkqr_bj, 0) = '1'\n"
				+ "      union all\n"
				+ "      select '发售' zt,\n"
				+ "             pz.xs_rq rq,\n"
				+ "             pz.fsjg_dm fcjg_dm,\n"
				+ "             ck.ck_mc fcckmc,\n"
				+ "             pz.jsr_dm fcr_dm,\n"
				+ "             '' jsjg_dm,\n"
				+ "             '' jsckmc,\n"
				+ "             '' jsr_dm,\n"
				+ "             nsr.nsr_mc nsrmc\n"
				+ "        from t_fp_fpfspz pz, t_fp_fpfspzmx pzmx, t_dj_nsrxx nsr, t_fp_fpwlck ck\n"
				+ "       where ck.ck_dm = pz.ck_dm\n"
				+ "         and nsr.nsrnbm = pz.nsrnbm\n"
				+ "         and pz.fpfspz_xh = pzmx.fpfspz_xh\n"
				+ "         and pzmx.fp_dm = ?\n"
				+ "         and pzmx.fp_qshm <= ?\n"
				+ "         and pzmx.fp_zzhm >= ?\n"
				+ "         and nvl(pz.zf_bj, 0) = 0\n"
				+ "      union all\n"
				+ "      select '验销' zt,\n"
				+ "             yx.yx_sj rq,\n"
				+ "             '' fcjg_dm,\n"
				+ "             '' fcckmc,\n"
				+ "             '' fcr_dm,\n"
				+ "             yx.swjg_dm jsjg_dm,\n"
				+ "             '' jsckmc,\n"
				+ "             yx.swypr_dm jsr_dm,\n"
				+ "             '' nsrmc\n"
				+ "        from t_fp_fpyx yx, t_fp_fpyxmx yxmx\n"
				+ "       where yx.yxpz_xh = yxmx.yxpz_xh\n"
				+ "         and yxmx.fp_dm = ?\n"
				+ "         and yxmx.fp_qshm <= ?\n"
				+ "         and yxmx.fp_zzhm >= ?\n"
				+ "         and nvl(yx.zf_bj, 0) = 0\n"
				+ "      union all\n"
				+ "      select '核销' zt,\n"
				+ "             hx.hx_rq rq,\n"
				+ "             hx.swjg_dm fcjg_dm,\n"
				+ "             ck.ck_mc fcckmc,\n"
				+ "             hx.hxr_dm,\n"
				+ "             '' jsjg_dm,\n"
				+ "             '' jsckmc,\n"
				+ "             '' jsr_dm,\n"
				+ "             '' nsrmc\n"
				+ "        from t_fp_fphx hx, t_fp_fphxmx hxmx, t_fp_fpwlck ck\n"
				+ "       where ck.ck_dm = hx.ck_dm\n"
				+ "         and hx.wsh = hxmx.wsh\n"
				+ "         and hxmx.fp_dm = ?\n"
				+ "         and hxmx.fp_qshm <= ?\n"
				+ "         and hxmx.fp_zzhm >=?\n"
				+ "         and nvl(hx.pz_bj, 0) = '1'\n" + "   ) aa,\n"
				+ " t_dm_gy_swjg fcjg,\n" + " t_dm_gy_swjg jsjg,\n"
				+ " t_dm_gy_swry fcr,\n" + " t_dm_gy_swry jsr\n"
				+ "where aa.fcjg_dm=fcjg.swjg_dm(+)\n"
				+ "and aa.jsjg_dm=jsjg.swjg_dm(+)\n"
				+ "and aa.fcr_dm =fcr.swry_dm(+)\n"
				+ "and aa.jsr_dm=jsr.swry_dm(+)";

		List<Fplist> fplist = new ArrayList<Fplist>();
		// nsryhList=null;
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, fpdm, fphm, fphm, fpdm,
					fphm, fphm, fpdm, fphm, fphm, fpdm, fphm, fphm, fpdm, fphm,
					fphm);
			while (rs!=null&&rs.next()) {
				Fplist fp = new Fplist();

				fp.setZt(NullToString(rs.getString("zt")));
				fp.setRq(NullToString(rs.getString("rq")));
				fp.setFcjg_dm(NullToString(rs.getString("fcjg_dm")));
				fp.setFcckmc(NullToString(rs.getString("fcckmc")));
				fp.setFcr_dm(NullToString(rs.getString("fcr_dm")));
				fp.setJsjg_dm(NullToString(rs.getString("jsjg_dm")));
				fp.setJsckmc(NullToString(rs.getString("jsckmc")));
				fp.setJsr_dm(NullToString(rs.getString("jsr_dm")));
				fp.setNsrmc(NullToString(rs.getString("nsrmc")));

				fplist.add(fp);
			}
			return fplist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return fplist;
	}
}
