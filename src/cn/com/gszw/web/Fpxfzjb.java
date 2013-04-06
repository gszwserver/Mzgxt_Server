package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gszw.dao.UserDAO;
import cn.com.gszw.impl.UserDAOImpl;
import cn.com.gszw.model.FpTs;


public class Fpxfzjb extends HttpServlet{



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		FpTs fp=new FpTs();
		fp.setNsrmc(req.getParameter("nsrmc"));
		fp.setFpdm(req.getParameter("fpdm"));
		fp.setFphm(req.getParameter("fphm"));
		fp.setJd(req.getParameter("jd"));
		fp.setWd(req.getParameter("wd"));
		fp.setDz(req.getParameter("dz"));
		fp.setXm(req.getParameter("xm"));
		fp.setDh(req.getParameter("dh"));
		fp.setSx(req.getParameter("sx"));
		fp.setJe(req.getParameter("je"));		
		UserDAO dao = new UserDAOImpl();
		
		String flag = dao.Fp_Xfzts(fp);;


		out.write(flag);

		out.flush();
		out.close();

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
	
}