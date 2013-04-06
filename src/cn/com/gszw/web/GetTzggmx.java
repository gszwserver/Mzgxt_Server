package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gszw.dao.TzggDAO;
import cn.com.gszw.impl.TzggDAOImpl;
import cn.com.gszw.impl.UserDAOImpl;
import cn.com.gszw.model.Tzgg;

import com.url.ajax.json.JSONArray;
import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;




public class GetTzggmx extends HttpServlet {
	// TODO Auto-generated method stub
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub	
	req.setCharacterEncoding("utf-8");
	resp.setContentType("text/html;charset=utf-8");

	PrintWriter out = resp.getWriter();
	String xh = req.getParameter("xh");
	// 以下代码是后台验证Key信息，如果验证不通过，则直接返回null;
	// 所有的Servlet都要调用这段代码，主要是为了数据安全考虑
	UserDAOImpl ud = new UserDAOImpl();
	if (!ud.YzKey(req.getParameter("userKey"))) {
		out.write("");
		out.flush();
		out.close();
	} else {
		TzggDAO dao = new TzggDAOImpl();

		Tzgg gg = dao.getTzggmx(xh);
		JSONObject obj = new JSONObject();

		if ((gg==null)||(gg.equals(""))) {
			out.write("-1");
		} else {
				try {
					obj.put("xh", gg.getXh());
					obj.put("image", gg.getImage());
					obj.put("title", gg.getTitle());
					obj.put("author", gg.getNotice());
					obj.put("create_time", gg.getCreate_time());
					obj.put("contxt",gg.getContxt());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			out.write(obj.toString());
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
