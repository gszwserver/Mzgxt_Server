package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gszw.dao.PubDAO;
import cn.com.gszw.impl.PubDAOImpl;
import cn.com.gszw.impl.UserDAOImpl;
import cn.com.gszw.model.Nsrxx;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;



public class GetNsrxxBybm extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String sqlstr = req.getParameter("nsrbm");
		PrintWriter out = resp.getWriter();
		//以下代码是后台验证Key信息，如果验证不通过，则直接返回null;
		//所有的Servlet都要调用这段代码，主要是为了数据安全考虑
		UserDAOImpl ud=new UserDAOImpl();
		if (!ud.YzKey(req.getParameter("userKey"))){
			out.write("");
			out.flush();
			out.close();
		}else{

			PubDAO Dao = new PubDAOImpl();
			//返回nsrxx列表
			
			Nsrxx nsr = Dao.getNsrByBm(sqlstr);

			//转换为json数组
			JSONObject obj = new JSONObject();
			if(nsr!=null&&!nsr.equals("")){
				try {
					obj.put("nsrmc",nsr.getNsrmc() );
					obj.put("fr",nsr.getFddbr() );
					obj.put("scjydz", nsr.getScjydz());
					obj.put("nsrnbm", nsr.getNsrsbm());				
					obj.put("gljg", nsr.getGljg());				
					obj.put("zgy", nsr.getZgy());				
					obj.put("cwfzr", nsr.getCwfzr());				
					obj.put("bsr", nsr.getBsr());				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}else{
				out.write("-1");
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
