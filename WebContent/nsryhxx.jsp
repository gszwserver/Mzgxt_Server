<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrYh,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String nsrnbm = request.getParameter("nsrnbm");
	String userKey = request.getParameter("userKey");
	System.out.println("userKey=" + userKey);
	UserDAOImpl dao = new UserDAOImpl();
	System.out.println(dao.YzKey(userKey));
	if (dao.YzKey(userKey)) {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		List<NsrYh> yhlist = nsrxxDao.getNsrYList(nsrnbm);
		if (yhlist != null  ) {
			if(yhlist.size() != 0){
				session.removeAttribute("yhlist");
				session.setAttribute("yhlist", yhlist);		
				%>
				<c:forEach var="yhxx" items="${yhlist}">
					<label>银行行别:</label>${yhxx.hb}</br>
					<label>开户行名称:</label>${yhxx.khhmc}</br>
					<label>银行帐号:</label>${yhxx.zh}</br>
					<label>帐号标记:</label>${yhxx.zhbj}</br>
					<label>开户日期:</label>${yhxx.kh_rq}</br>
					<label>开户行名称:</label>${yhxx.nsrkh_mc}</br>
					<label>选用标记:</label>${yhxx.xy}</br>	
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