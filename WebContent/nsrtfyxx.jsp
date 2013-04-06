<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrTfyxx,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
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
		List<NsrTfyxx> tfylist =nsrxxDao.getNsrTfyxx(nsrnbm);
		if (tfylist != null  ) {
			if(tfylist.size() != 0){
				session.removeAttribute("tfylist");
				session.setAttribute("tfylist", tfylist);	
				%>
					<c:forEach var="tfyxx" items="${tfylist}">
					<label>停业日期起:</label>${tfyxx.tyqsrq}</br>
					<label>停业日期止:</label>${tfyxx.tyzzrq}</br>
					<label>实际复业日期:</label>${tfyxx.sjfyrq}</br>
					<label>复业类型:</label>${tfyxx.fylx}</br>
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