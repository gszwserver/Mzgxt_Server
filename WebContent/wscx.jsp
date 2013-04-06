<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.List,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO,cn.com.gszw.model.Wscx,cn.com.gszw.impl.WscxDAOImpl,cn.com.gszw.dao.WscxDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String userKey = request.getParameter("userKey");
	String sqlstr = request.getParameter("sqlstr");
	String swrydm = request.getParameter("swrydm");
	System.out.println("userKey=" + userKey);
	UserDAOImpl dao = new UserDAOImpl();
	System.out.println(dao.YzKey(userKey));
	if (dao.YzKey(userKey)) {
		WscxDAO wscxDao = new WscxDAOImpl();
		List<Wscx> wslist = wscxDao.getWsxx(sqlstr,swrydm);
		if (wslist != null  ) {
			if(wslist.size() != 0){
				session.removeAttribute("wslist");
				session.setAttribute("wslist", wslist);	
				%>
				<font color="#0C6888"><c:forEach var="wsxx" items="${wslist}">
					<label>序号:</label>${wsxx.xh}</br>
					<label>环节名称:</label>${wsxx.hjmc}</br>
					<label>流程名称:</label>${wsxx.lcmc}</br>
					<label>文书号:</label>${wsxx.wsh}</br>
					<label>纳税人编码:</label>${wsxx.nsrbm}</br>
					<label>纳税人名称:</label>${wsxx.nsrmc}</br>
					<label>提交人:</label>${wsxx.tjr}</br>
					<label>提交时间:</label>${wsxx.tjsj}</br>
					<p>--------------------------------------</p>
				</c:forEach>
				</font>		
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