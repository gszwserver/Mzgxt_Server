package cn.com.gszw.dao;

import java.util.List;

import cn.com.gszw.model.PzCx;
import cn.com.gszw.model.Tzgg;

public interface TzggDAO {
	public List<Tzgg> getTzggList(String swjgdm) ;
	public Tzgg getTzggmx(String xh);
}
