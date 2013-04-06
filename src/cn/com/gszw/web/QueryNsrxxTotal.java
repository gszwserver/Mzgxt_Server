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
/**
 * 根据纳税人名称查询纳税人总数
 * @author wang
 *
 */
public class QueryNsrxxTotal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String nsrmc = req.getParameter("nsrmc");
		PrintWriter out = resp.getWriter();
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		String number = nsrxxDao.getNsrxxTotal(nsrmc);
		System.out.println(number+"333333333333333");
		if(number!=null&&!number.equals("")){
			JSONObject obj = new JSONObject();
			try {
				obj.put("number", number);
				out.write(obj.toString());
				out.flush();
				out.close();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doGet(req, resp);
	}

}
