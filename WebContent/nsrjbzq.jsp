<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrJbzq,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String nsrnbm = request.getParameter("nsrnbm");
	String userKey = request.getParameter("userKey");
//	System.out.println("sxxxxxxxx=" + sb_rq);
	UserDAOImpl dao = new UserDAOImpl();
	if (dao.YzKey(userKey)) {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		List<NsrJbzq> jbzqlist = nsrxxDao.getNsrJbzqList(nsrnbm);
		if (jbzqlist != null  ) {
			if(jbzqlist.size() != 0){
				session.removeAttribute("jbzqlist");
				session.setAttribute("jbzqlist", jbzqlist);	
				%>
				<c:forEach var="sbxx" items="${jbzqlist}">
					<label>受理日期:</label>${sbxx.slrq}</br>
					<label>受理人员:</label>${sbxx.slry}</br>
					<label>月核定税额:</label>${sbxx.yhdse}</br>
					<label>减并纳税期限:</label>${sbxx.jbnsqx}</br>
					<label>起始日期:</label>${sbxx.qsrq}</br>
					<label>终止日期:</label>${sbxx.zzrq}</br>
					<label>减并月份:</label>${sbxx.jbyf}</br>
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