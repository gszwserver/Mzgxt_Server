package cn.com.gszw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gszw.dao.NsrxxDAO;
import cn.com.gszw.impl.NsrxxDAOImpl;
import cn.com.gszw.impl.UserDAOImpl;
import cn.com.gszw.model.PubView;

public class GetPublicView extends HttpServlet {
	// TODO Auto-generated method stub
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 需要接受三个参数
		// 人员编号
		String account = req.getParameter("account");
		// 系统密钥
		String userKey = req.getParameter("userKey");
		// 表格代号
		String TableId = req.getParameter("TableId");
		// 附加条件
		String Tj = req.getParameter("Tj");
		//
		String l1, l2, l3;
		PrintWriter out = resp.getWriter();
		// 以下代码是后台验证Key信息，如果验证不通过，则直接返回null;
		// 所有的Servlet都要调用这段代码，主要是为了数据安全考虑


		UserDAOImpl ud = new UserDAOImpl();
		if (!ud.YzKey(req.getParameter("userKey"))) {
			out.write("");
			out.flush();
			out.close();
		} else {



		///	out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD></HEAD>");
			out.println("  <BODY>");
			out.println("<table  border='1' cellpadding='0' cellspacing='0' align='center'>");
			NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
			List<PubView> List = nsrxxDao.GetPublicList(account, TableId, Tj);

			if (List.size() == 0) {
				out.write("<tr><td>没有信息</td></tr>");
			} else if (List == null) {
				out.write("<tr><td>没有信息</td></tr>");
			} else {
				// 拼HTML页面展示

				out.println("<col width='25%'>");
				out.println("<col width='40%'>");
				out.println("<col width='35%'>");

				// 表格起始行
				String tab_first = "";
				// 表格中间行
				String tab_mid = "";
				// 根据List写表格内容
				String dyl = "";// 表格第一列
				String tab1_nr = "";
				int i = 1;
				int j = 0;
				for (PubView xx : List) {
					j = j + 1;
					// 取出一行的值
					l1 = xx.getL1();
					l2 = xx.getL2();
					l3 = xx.getL3();
					// System.out.println("j="+j+"  l1"+l1+":::l2"+l2);
					// System.out.println("List.size()==="+List.size());
					if (l1.equals(l2) && l2.equals(l3)) {

						if (tab_first.length() > 0) {
							tab_first = "<td  rowspan='" + i + "'>" + tab1_nr
									+ "</td>" + tab_first;
							tab_first = "<tr>" + tab_first + "</tr>";
							dyl = tab_first + tab_mid;
							tab_first = "";
						} else {
							dyl = "";
						}
						// 如果列1、列2、列3为同一值，合并1、2、3
						dyl = dyl + "<tr>";
						dyl = dyl + "<td  colspan='3' align='center'>" + l1
								+ "</td>";
						dyl = dyl + "</tr>";

					} else {

						if (tab_first.equals("")) {
							tab_first = "<td>" + l2 + "</td>";
							tab_first = tab_first + "<td align='right'>" + l3
									+ "</td>";
							tab1_nr = l1;
						} else {
							if (tab1_nr.equals(l1)) {

								tab_mid = tab_mid + "<tr>";
								tab_mid = tab_mid + "<td>" + l2 + "</td>";
								tab_mid = tab_mid + "<td align='right'>" + l3
										+ "</td>";
								tab_mid = tab_mid + "</tr>";

								i = i + 1;
							} else {
								tab_first = "<td  rowspan='" + i + "'>"
										+ tab1_nr + "</td>" + tab_first;
								tab_first = "<tr>" + tab_first + "</tr>";
								dyl = tab_first + tab_mid;
								i = 1;
								tab_first = "<td>" + l2 + "</td>";
								tab_first = tab_first + "<td align='right'>"
										+ l3 + "</td>";
								tab1_nr = l1;
								tab_mid = "";
							}

						}
						if (j == List.size()) {
							tab_first = "<td  rowspan='" + i + "'>" + tab1_nr
									+ "</td>" + tab_first;
							tab_first = "<tr>" + tab_first + "</tr>";
							dyl = dyl + tab_first + tab_mid;
						}
					}

					if (!dyl.equals("")) {

						out.println(dyl);
						dyl = "";
					}

				}

			}
			out.print("</table>");
			out.println(" <p>----------------------------</p>");
			out.println(" <p>----------------------------</p>");
			out.println(" <p>----------------------------</p>");
			out.println("  </BODY>");
			out.println("</HTML>");
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
