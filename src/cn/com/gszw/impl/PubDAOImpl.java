package cn.com.gszw.impl;

import java.sql.ResultSet;
import java.sql.SQLException;


import cn.com.gszw.dao.PubDAO;
import cn.com.gszw.model.Nsrxx;
import cn.com.gszw.util.SQLHelper;

public class PubDAOImpl implements PubDAO {
//
	// 查询纳税人基本信息
	@Override
	public Nsrxx getNsrByBm(String nsrbm) {
		// TODO Auto-generated method stub
		String sql = "seLECT TO_CHAR(NSRKZ.KYDJRQ, 'YYYY-MM-DD') kyrq, "
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
				+ " and xx.zsfs_dm = zsfs.zsfs_dm(+) " + " and xx.nsrbm = ? ";
		try {
			ResultSet rs = SQLHelper.getResultSet(sql,nsrbm);
			if (rs.next()) {
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
}
