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
import cn.com.gszw.model.main;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;

public class GetDefault extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String account = req.getParameter("account");
		String qt = req.getParameter("qt");	
		String userKey = req.getParameter("userKey");
		PrintWriter out = resp.getWriter();
		//以下代码是后台验证Key信息，如果验证不通过，则直接返回null;
		//所有的Servlet都要调用这段代码，主要是为了数据安全考虑
		UserDAOImpl ud=new UserDAOImpl();
		if (!ud.YzKey(req.getParameter("userKey"))){
			out.write("");
			out.flush();
			out.close();
		}else{

			NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
			main mainlist = nsrxxDao.GetMainList(account,qt);

			//转换为json数组
			JSONObject obj = new JSONObject();
			if(mainlist!=null&&!mainlist.equals("")){
				try {
					obj.put("sj01", mainlist.getSj01());
					obj.put("sj02", mainlist.getSj07());
					obj.put("sj03", mainlist.getSj14());
					obj.put("sj04", mainlist.getSj20());
					obj.put("sj05", mainlist.getSj21());
					obj.put("sj06", mainlist.getSj22());
					obj.put("sj07", mainlist.getSj23());
					obj.put("sj08", mainlist.getSj24());
					obj.put("sj09", mainlist.getSj25());
					obj.put("sj10", mainlist.getSj26());
					obj.put("sj11", mainlist.getSj27());
					obj.put("sj12", mainlist.getSj28());
					obj.put("sj13", mainlist.getSj29());
					obj.put("sj14", mainlist.getSj02());
				
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
