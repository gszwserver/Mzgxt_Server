<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrSbmx,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String nsrnbm = request.getParameter("nsrnbm");
	String sb_rq = request.getParameter("OtherTj");
	String userKey = request.getParameter("userKey");
//	System.out.println("sxxxxxxxx=" + sb_rq);
	UserDAOImpl dao = new UserDAOImpl();
	if (dao.YzKey(userKey)) {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		List<NsrSbmx> sblist = nsrxxDao.getSbmxList(nsrnbm,sb_rq);
		if (sblist != null  ) {
			if(sblist.size() != 0){
				session.removeAttribute("sblist");
				session.setAttribute("sblist", sblist);	
				%>
				<c:forEach var="sbxx" items="${sblist}">
					<label>申报日期:</label>${sbxx.sb_rq}</br>
					<label>申报凭证号:</label>${sbxx.pz_xh}</br>
					<label>税款所属期:</label>${sbxx.ssq}</br>
					<label>征收项目:</label>${sbxx.zsxm}</br>
					<label>计税金额:</label>${sbxx.jsje}</br>					
					<label>实缴金额:</label>${sbxx.sbje}</br>
					<label>未开票金额:</label>${sbxx.wkpje}</br>
					<p>----------------------------</p>
				</c:forEach>	
				<%
			}else{
				out.println("没有信息！");
			}
		}else {
			out.println("没有信息！");
		}
	} else {
		out.println("用户校验错误！");
	}
%>
<html>
<head>
</head>
<body>

</body>
</html>