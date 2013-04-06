package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.gszw.dao.NsrxxDAO;
import cn.com.gszw.impl.NsrxxDAOImpl;
import cn.com.gszw.model.NsrTfyxx;

public class GetNsrTfyxx extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String nsrnbm = req.getParameter("nsrnbm");
		System.out.println(nsrnbm+"---------------------");
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		List<NsrTfyxx> list = nsrxxDao.getNsrTfyxx(nsrnbm);
		if(list!=null&&list.size()!=0){
			HttpSession session = req.getSession();
			session.setAttribute("list", list);
			resp.sendRedirect("/nsrtfyxx.jsp");

		}else{
			out.println("没有信息");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
