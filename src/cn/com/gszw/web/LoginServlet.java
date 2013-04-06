package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;

import cn.com.gszw.dao.UserDAO;
import cn.com.gszw.impl.UserDAOImpl;
import cn.com.gszw.model.User;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding(HTTP.UTF_8);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		String swrydm = req.getParameter("swrydm");
		String password = req.getParameter("password");
		String imsi = req.getParameter("imsi");
		String imei = req.getParameter("imei");
		UserDAO dao = new UserDAOImpl();
		JSONObject mJsonObject = new JSONObject();
		if (dao.validateYHYZ(swrydm, password)) {
			try {
				mJsonObject = dao.validateSJYZ(swrydm, imsi, imei);
				if ("000".equals(mJsonObject.getString("fhjg"))) {
					mJsonObject = dao.getSwjgSwry(swrydm);
					mJsonObject.put("fhjg", "000");
				} else if ("010".equals(mJsonObject.getString("fhjg"))) {

				} else if ("011".equals(mJsonObject.getString("fhjg"))) {

				} else {
					mJsonObject.put("fhgj", "999");
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		else {
			try {
				mJsonObject.put("fhjg", "001");
			} catch (JSONException e) {
				e.printStackTrace();
			}
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
