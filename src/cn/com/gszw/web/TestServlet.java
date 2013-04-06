package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gszw.dao.UserDAO;
import cn.com.gszw.impl.NsrxxDAOImpl;
import cn.com.gszw.impl.UserDAOImpl;
import cn.com.gszw.model.FpCx;
import cn.com.gszw.model.Fp_zcxx;
import cn.com.gszw.model.Fplist;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		UserDAO dao = new UserDAOImpl();
		boolean flag = dao.test();
		if(flag){
			out.write("test success!");
		}
		out.write("Load NsrxxDAOImpl!");
		NsrxxDAOImpl aa= new NsrxxDAOImpl();
		FpCx jj=aa.getFpCxList("00684002","262011141601","0");
        //查询结果标志  
		//-1 发票代码不存在；
		//1 发票存在并领购至纳税人；需要展示DE类中的信息
		//2 系统中存在发票开票明细信息；需要展示JKP类中的信息
		//0发票验证不存在；需要展示FPLX类中的信息
		
		out.write(jj.getFlag());
		switch (jj.getFlag()) {
		case 0:
			for (Fplist fp : jj.getFplx()) {
				out.write(fp.getRq());
				
			}
			break;
		case 1:
			out.write(jj.getDe().getNsrmc());
			break;
		case 2:
			out.write(jj.getJkp().getFk());
			break;
		case -1:
			break;
		}
		
		Fp_zcxx fp=new Fp_zcxx();
		fp.setMsg("wwwwww");
		fp.setFpdm("wwwwww");
		fp.setFphm("wwwwww");
		fp.setJd("wwwwww");
		fp.setWd("wwwwww");
		fp.setDz("wwwwww");
		dao.login_sj(fp);

//		
		out.write(jj.getFpzl());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
