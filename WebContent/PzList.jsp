<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.impl.PzcxDAOImpl,java.util.List,cn.com.gszw.model.PzCxMx"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String pzhm = request.getParameter("pzhm");
	String pzzb_dm = request.getParameter("pzzb_dm");
	String userKey = request.getParameter("userKey");

	
	UserDAOImpl dao = new UserDAOImpl();
	System.out.println(dao.YzKey(userKey));
	if (dao.YzKey(userKey)) {
		PzcxDAOImpl pz = new PzcxDAOImpl();
		List<PzCxMx> list = pz.GetPzMx(pzhm,pzzb_dm);
		if (list != null  ) {
			if(list.size() != 0){
				session.removeAttribute("fpfxlist");
				session.setAttribute("fpfxlist", list);
				%>
				<c:forEach var="fpfx" items="${fpfxlist}">
					<label>税种:</label>${fpfx.zsxm}</br>
					<label>税目:</label>${fpfx.zspm}</br>
					<label>金额:</label>${fpfx.je}</br>
					<label>征收单位:</label>${fpfx.swjg}</br>
					<label>开票人:</label>${fpfx.lrry}</br>

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