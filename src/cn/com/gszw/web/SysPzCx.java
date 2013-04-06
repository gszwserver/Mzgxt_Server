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
import cn.com.gszw.model.SysPzList;

import com.url.ajax.json.JSONArray;
import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;


public class SysPzCx extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		String sphm = req.getParameter("sphm");
		String swjg = req.getParameter("swjg");


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
			// 返回nsrxx列表
			System.out.println("tmd");
			List<SysPzList> nsrxxList = nsrxxDao.GetPzList(sphm,swjg);
			if (nsrxxList.size() == 0) {
				out.write("-1");
			} else {
				JSONArray array = new JSONArray();
				for (SysPzList nsr : nsrxxList) {
					JSONObject obj = new JSONObject();
					try {
						obj.put("Kpjg", nsr.getKpjg());
						obj.put("Pzzb_dm", nsr.getPzzb_dm());
						obj.put("Pzzb_mc", nsr.getPzzb_mc());
						obj.put("Pzzl_dm", nsr.getPzzl_dm());
						obj.put("Pzzl_mc", nsr.getPzzl_mc());
						obj.put("Pzhm", nsr.getPzhm());
						obj.put("Je", nsr.getJe());

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
