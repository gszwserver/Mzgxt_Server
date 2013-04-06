package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gszw.dao.UserDAO;
import cn.com.gszw.impl.UserDAOImpl;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;


public class Sjxxzc extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		String swrydm = req.getParameter("swrydm");
		String imsi = req.getParameter("imsi");
		String imei = req.getParameter("imei");
		UserDAO dao = new UserDAOImpl();
		JSONObject mJsonObject = new JSONObject();
		try {
			mJsonObject = dao.insertSJYH(swrydm, imsi, imei);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.write(mJsonObject.toString());
		out.flush();
		out.close();

		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
	
}
