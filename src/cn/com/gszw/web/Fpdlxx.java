package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import cn.com.gszw.dao.UserDAO;
import cn.com.gszw.impl.UserDAOImpl;
import cn.com.gszw.model.Fp_zcxx;

public class Fpdlxx extends HttpServlet{



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Fp_zcxx fp=new Fp_zcxx();
		fp.setMsg(req.getParameter("msg"));
		fp.setFpdm(req.getParameter("fpdm"));
		fp.setFphm(req.getParameter("fphm"));
		fp.setJd(req.getParameter("jd"));
		fp.setWd(req.getParameter("wd"));
		fp.setDz(req.getParameter("dz"));
		System.out.println(req.getParameter("dz"));
		UserDAO dao = new UserDAOImpl();
		dao.login_sj(fp);


		out.flush();
		out.close();

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
	
}