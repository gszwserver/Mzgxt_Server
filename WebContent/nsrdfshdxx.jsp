<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrDfsxx,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
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
		List<NsrDfsxx> dfslist = nsrxxDao.getNsrDfsxxList(nsrnbm);
		if (dfslist != null  ) {
			if(dfslist.size() != 0){
				session.removeAttribute("dfslist");
				session.setAttribute("dfslist", dfslist);
				%>
				<c:forEach var="dfshdxx" items="${dfslist}">
					<label>征收项目:</label>${dfshdxx.zsxm}</br>
					<label>征收品目:</label>${dfshdxx.zspm}</br>
					<label>税率:</label>${dfshdxx.sl}</br>
					<label>年应纳税额:</label>${dfshdxx.nynse}</br>
					<label>核定起始日期:</label>${dfshdxx.hdqsrq}</br>
					<label>核定终止日期:</label>${dfshdxx.hdzzrq}</br>
					<label>纳税期限:</label>${dfshdxx.nsqx}</br>
					<label>申报期限:</label>${dfshdxx.sbqx}</br>
					<label>缴款期限:</label>${dfshdxx.jkqx}</br>		
					<label>预算分配比例:</label>${dfshdxx.ysfpbl}</br>
					<label>国库:</label>${dfshdxx.gkid}</br>		
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