package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gszw.dao.NsrxxDAO;
import cn.com.gszw.impl.NsrxxDAOImpl;
import cn.com.gszw.impl.UserDAOImpl;
import cn.com.gszw.model.NsrYh;

import com.url.ajax.json.JSONArray;
import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;

@SuppressWarnings("serial")
public class QueryNsrYhList extends HttpServlet {
	public QueryNsrYhList() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Begin getNsrYList");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String nsrnbm = req.getParameter("nsrnbm");

		PrintWriter out = resp.getWriter();
		// 以下代码是后台验证Key信息，如果验证不通过，则直接返回null;
		// 所有的Servlet都要调用这段代码，主要是为了数据安全考虑
		UserDAOImpl ud = new UserDAOImpl();
		if (!ud.YzKey(req.getParameter("userKey"))) {
			out.write("");
			out.flush();
			out.close();
		} else {
			NsrxxDAO nsrxxDao = new NsrxxDAOImpl();

			List<NsrYh> nsryhList = nsrxxDao.getNsrYList(nsrnbm);
			JSONArray array = new JSONArray();
			for (NsrYh yh : nsryhList) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("hb", yh.getHb());
					obj.put("khhmc", yh.getKhhmc());
					obj.put("zh", yh.getZh());
					obj.put("zhbj", yh.getZhbj());
					obj.put("kh_rq", yh.getKh_rq());

					obj.put("nsrkh_mc", yh.getNsrkh_mc());

					obj.put("xy", yh.getXy());

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				array.put(obj);
			}
			out.write(array.toString());
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
