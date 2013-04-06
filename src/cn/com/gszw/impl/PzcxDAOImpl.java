package cn.com.gszw.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.gszw.dao.PzcxDAO;
import cn.com.gszw.model.PzCx;
import cn.com.gszw.model.PzCxMx;
import cn.com.gszw.util.SQLHelper;

public class PzcxDAOImpl implements PzcxDAO {

	public List<PzCx> GetPzList(String pzhm) {
		String sql = 
			"select zl.mc zl,\n" +
			"zb.mc zb,\n" + 
			"sy.pzzb_dm,\n" + 
			"sy.pzhm,\n" + 
			"to_char(sy.tf_sj,'yyyy-mm-dd') sj,\n" + 
			"decode(sy.zfr_dm,null,'正常','作废') zt\n" + 
			"from t_zs_pzsyqk sy,\n" + 
			"t_dm_pz_pzzl zl,\n" + 
			"t_dm_pz_pzzb zb\n" + 
			"where sy.pzzl_dm=zl.pzzl_dm\n" + 
			"and sy.pzzb_dm=zb.pzzb_dm\n" + 
			"and sy.pzhm=?\n" + 
			"order by sy.tf_sj desc";



		List<PzCx> list = new ArrayList<PzCx>();
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, pzhm);
			if (rs == null) {
				return null;
			} else {
				while (rs.next()) {
					PzCx pz = new PzCx();
					pz.setZl(rs.getString("zl"));
					pz.setZb(rs.getString("zb"));
					pz.setPzhm(rs.getString("pzhm"));
					pz.setSj(rs.getString("sj"));
					pz.setZt(rs.getString("zt"));
					pz.setPzzb_dm(rs.getString("pzzb_dm"));
					list.add(pz);
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return null;

	}
	public List<PzCxMx> GetPzMx(String pzhm,String pzzb_dm) {
		String sql =
			"select xm.mc zsxm, pm.mc zspm, mx.sj_je je, ry.mc lrry, jg.mc swjg\n" +
			"  from t_zs_pzsyqk  sy,\n" + 
			"       t_zs_zjjkmx  mx,\n" + 
			"       t_dm_gy_swjg jg,\n" + 
			"       t_dm_gy_swry ry,\n" + 
			"       t_dm_gy_zsxm xm,\n" + 
			"       t_dm_gy_zspm pm\n" + 
			" where sy.dzsph_xh = mx.dzsph_xh\n" + 
			"   and mx.lrry_dm = ry.swry_dm\n" + 
			"   and mx.zsjg_dm = jg.swjg_dm\n" + 
			"   and mx.zsxm_dm = xm.zsxm_dm\n" + 
			"   and mx.zsxm_dm = pm.zsxm_dm\n" + 
			"   and mx.zspm_dm = pm.zspm_dm\n" + 
			"   and sy.pzzb_dm = ?\n" + 
			"   and sy.pzhm = ?\n" + 
			"union all\n" + 
			"select xm.mc zsxm, pm.mc zspm, mx.sj_je je, ry.mc lrry, jg.mc swjg\n" + 
			"  from t_zs_pzsyqk  sy,\n" + 
			"       t_zs_xjjkmx  mx,\n" + 
			"       t_dm_gy_swjg jg,\n" + 
			"       t_dm_gy_swry ry,\n" + 
			"       t_dm_gy_zsxm xm,\n" + 
			"       t_dm_gy_zspm pm\n" + 
			" where sy.dzsph_xh = mx.dzsph_xh\n" + 
			"   and mx.lrry_dm = ry.swry_dm\n" + 
			"   and mx.zsjg_dm = jg.swjg_dm\n" + 
			"   and mx.zsxm_dm = xm.zsxm_dm\n" + 
			"   and mx.zsxm_dm = pm.zsxm_dm\n" + 
			"   and mx.zspm_dm = pm.zspm_dm\n" + 
			"   and sy.pzzb_dm = ?\n" + 
			"   and sy.pzhm = ?";

		List<PzCxMx> list = new ArrayList<PzCxMx>();
		try {
			ResultSet rs = SQLHelper.getResultSet(sql, pzzb_dm,pzhm,pzzb_dm,pzhm);
			if (rs == null) {
				return null;
			} else {
				while (rs.next()) {
					PzCxMx pz = new PzCxMx();
					pz.setSwjg(rs.getString("swjg"));
					pz.setJe(rs.getString("je"));
					pz.setLrry(rs.getString("lrry"));
					pz.setZspm(rs.getString("zspm"));
					pz.setZsxm(rs.getString("zsxm"));

					list.add(pz);
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return null;

	}
}
