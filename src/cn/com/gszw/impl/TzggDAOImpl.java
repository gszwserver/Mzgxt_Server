package cn.com.gszw.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.com.gszw.dao.TzggDAO;
import cn.com.gszw.model.Tzgg;
import cn.com.gszw.util.SQLHelper;

public  class TzggDAOImpl implements TzggDAO {

	public List<Tzgg> getTzggList(String swjgdm) {
		String sql = "select no.xh,no.image,no.title,no.notice,to_char(no.create_time,'YYYY-MM-DD') create_time,no.area"+
	                 " from t_mobile_notice no where no.area like'%"
				     + swjgdm + "%'"+"  or no.area = '26200000000' order by create_time desc";

		List<Tzgg> list = new ArrayList<Tzgg>();
		try {
			ResultSet rs = SQLHelper.getResultSet(sql);
			if (rs == null) {
				return null;
			} else {
				while (rs.next()) {
					Tzgg gg = new Tzgg();
					gg.setXh(rs.getString("xh"));
					gg.setImage(rs.getString("image"));
					gg.setTitle(rs.getString("title"));
					gg.setNotice(rs.getString("notice"));
				    gg.setCreate_time(rs.getString("create_time"));
					gg.setArea(rs.getString("area"));
					list.add(gg);
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
	
	public Tzgg getTzggmx(String xh) {
		String sql =  "select no.xh,no.image,no.title,no.author,to_char(no.create_time,'YYYY-MM-DD') create_time,no.contxt"+
	                 " from t_mobile_notice no where xh = ? ";

		
		try {
			ResultSet rs = SQLHelper.getResultSet(sql,xh);
			if (rs == null) {
				return null;
			} else {
				while (rs.next()) {
					Tzgg gg = new Tzgg();
					gg.setXh(rs.getString("xh"));
					gg.setImage(rs.getString("image"));
					gg.setTitle(rs.getString("title"));
					gg.setNotice(rs.getString("author"));
					gg.setCreate_time(rs.getString("create_time"));
					gg.setContxt(rs.getString("contxt"));
					return gg;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return null;

	}
}
