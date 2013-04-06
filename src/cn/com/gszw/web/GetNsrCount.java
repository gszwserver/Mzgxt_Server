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

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;


public class GetNsrCount extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String sqlstr = req.getParameter("sqlstr");
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
			//返回nsrxx列表
			
			List<String> number = nsrxxDao.getMainCount(sqlstr);

			//转换为json数组
			JSONObject obj = new JSONObject();
			if(number!=null&&!number.equals("")){
				try {
					obj.put("PageCount", number.get(1));//总页数
					obj.put("HsCount", number.get(0));//总户数
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}else{
				try {
					obj.put("PageCount", "-1");//总页数
					obj.put("HsCount", "-1");//总户数
				
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
