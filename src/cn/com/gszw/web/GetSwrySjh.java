package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gszw.dao.NsrxxDAO;
import cn.com.gszw.impl.NsrxxDAOImpl;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;

public class GetSwrySjh extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String sqlstr = req.getParameter("sj");
		PrintWriter out = resp.getWriter();
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();

		String number = nsrxxDao.getSjNum(sqlstr);

		// 转换为json数组
		JSONObject obj = new JSONObject();

		try {
			obj.put("Swry_dm", number);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(obj.toString());
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
