package cn.com.gszw.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.gszw.dao.WscxDAO;
import cn.com.gszw.model.Wscx;
import cn.com.gszw.util.SQLHelper;

public class WscxDAOImpl implements WscxDAO {

	public List<Wscx> getWsxx(String sqlstr,String swrydm) {
		String sql = "select  row_number() over ( order by lz.bl_rq desc) xh,xx.wsh wsh,lz.hjmc hjmc, zl.mc lcmc,dj.nsrbm nsrbm,dj.nsr_mc nsrmc,ry.mc tjr,lz.bl_rq tjsj"
				+" from t_ws_sswsjbxx xx,t_dm_gy_wszl zl,t_dj_nsrxx dj,t_dm_gy_swry ry,"
				+" (select max(lz.bl_xh),lz.wf_id,lz.xyblry_dm,lz.hjmc,lz.bl_rq,blry_dm from t_wf_lzxx lz" 
				+" where lz.wf_id not in (select wf_id from t_wf_lzxx lz where lz.xyblry_dm is null)"
				+" and lz.xyblry_dm=?"
				+" and (lz.bl_xh,lz.wf_id) in (select max(bl_xh),wf_id from t_wf_lzxx group by wf_id)"
				+" group by lz.wf_id,lz.xyblry_dm ,lz.hjmc,lz.bl_rq,blry_dm )lz"
				+" where lz.wf_id=xx.wf_id"
				+" and xx.wszl_dm=zl.swwszl_dm(+)"
				+" and xx.nsrnbm=dj.nsrnbm(+)"
				+" and lz.blry_dm=ry.swry_dm(+)";
		sql = sql  + " and "+ sqlstr;

		List<Wscx> list = new ArrayList<Wscx>();
		try {
			ResultSet rs = SQLHelper.getResultSet(sql,swrydm);
			if (rs == null) {
				return null;
			} else {
				while (rs.next()) {
					Wscx ws = new Wscx();
					ws.setXh(rs.getString("xh"));
					ws.setHjmc(rs.getString("hjmc"));
					ws.setLcmc(rs.getString("lcmc"));
					ws.setWsh(rs.getString("wsh"));
				    ws.setNsrbm(rs.getString("nsrbm"));
					ws.setNsrmc(rs.getString("nsrmc"));
					ws.setTjr(rs.getString("tjr"));
					ws.setTjsj(rs.getString("tjsj"));
					list.add(ws);
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
