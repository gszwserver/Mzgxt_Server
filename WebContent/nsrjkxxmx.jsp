<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrJkxxmx,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String nsrnbm = request.getParameter("nsrnbm");
	String jk_rq = request.getParameter("OtherTj");
	String userKey = request.getParameter("userKey");
	System.out.println("userKey=" + userKey);
	UserDAOImpl dao = new UserDAOImpl();
	System.out.println(dao.YzKey(userKey));
	if (dao.YzKey(userKey)) {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		List<NsrJkxxmx> jkxxlist = nsrxxDao.getJkxxmxList(nsrnbm,jk_rq);
		if (jkxxlist != null  ) {
			if(jkxxlist.size() != 0){
				session.removeAttribute("jkxxlist");
				session.setAttribute("jkxxlist", jkxxlist);	
				%>
				<c:forEach var="jkxx" items="${jkxxlist}">
					<label>缴款日期:</label>${jkxx.jkrq}</br>
					<label>征收项目:</label>${jkxx.zsxm}</br>
					<label>票证号码:</label>${jkxx.sbpzs}</br>
					<label>开票金额:</label>${jkxx.kpje}</br>
					<label>入库金额:</label>${jkxx.rkje}</br>
					<label>未开票金额:</label>${jkxx.wkpje}</br>
					<p>----------------------------</p>
				</c:forEach>				
				<%
			}else{
				out.println("没有信息！");
			}

		} else {
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