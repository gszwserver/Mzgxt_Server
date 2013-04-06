package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gszw.dao.TzggDAO;
import cn.com.gszw.dao.WscxDAO;
import cn.com.gszw.impl.TzggDAOImpl;
import cn.com.gszw.impl.UserDAOImpl;
import cn.com.gszw.impl.WscxDAOImpl;
import cn.com.gszw.model.Tzgg;
import cn.com.gszw.model.Wscx;

import com.url.ajax.json.JSONArray;
import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;




public class GetWsxx extends HttpServlet {
	// TODO Auto-generated method stub
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub	
	req.setCharacterEncoding("utf-8");
	resp.setContentType("text/html;charset=utf-8");
	PrintWriter out = resp.getWriter();
	String sqlstr = req.getParameter("sqlstr");
	String swrydm = req.getParameter("swrydm");
	UserDAOImpl ud = new UserDAOImpl();
	if (!ud.YzKey(req.getParameter("userKey"))) {
		out.write("");
		out.flush();
		out.close();
	} else {
		WscxDAO wscxDao = new WscxDAOImpl();
		List<Wscx> list = wscxDao.getWsxx(sqlstr,swrydm);

		if ((list==null)||(list.size()==0)) {
			out.write("-1");
		} else {
			JSONArray array = new JSONArray();
			for (Wscx gg : list) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("xh", gg.getXh());
					obj.put("hjmc", gg.getHjmc());
					obj.put("lcmc", gg.getLcmc());
					obj.put("wsh", gg.getWsh());
					obj.put("nsrbm", gg.getNsrbm());
					obj.put("nsrmc",gg.getNsrmc());
					obj.put("tjr", gg.getTjr());
					obj.put("tjsj",gg.getTjsj());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				array.put(obj);
			}
			out.write(array.toString());
		}

		out.flush();
		out.close();
	}
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(req, resp);
}
}
