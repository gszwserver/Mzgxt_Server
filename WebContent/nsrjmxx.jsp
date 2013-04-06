<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrJmxx,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
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
		List<NsrJmxx> jmlist = nsrxxDao.getNsrJmxxList(nsrnbm);
		if (jmlist != null  ) {
			
			if(jmlist.size() != 0){
				session.removeAttribute("jmlist");
				session.setAttribute("jmlist", jmlist);	
				%>
					<c:forEach var="jmxx" items="${jmlist}">
						<label>征收项目:</label>${jmxx.zsxm}</br>
						<label>征收品目:</label>${jmxx.zspm}</br>
						<label>批准减免起时间:</label>${jmxx.pzjmqsrq}</br>
						<label>批准减免止时间:</label>${jmxx.pzjmzzrq}</br>
						<label>批准减免幅度:</label>${jmxx.pzjmfd}</br>
						<label>申请减免金额:</label>${jmxx.sqjmje}</br>
						<label>批准减免税率:</label>${jmxx.pzjmsl}</br>
						<label>减免余额:</label>${jmxx.jmye}</br>
						<label>减免依据:</label>${jmxx.jmyj}</br>
						<label>减免类型:</label>${jmxx.jmlx}</br>
						<label>减免原因:</label>${jmxx.jmyy}</br>
						<label>文书受理人员:</label>${jmxx.wsslry}</br>
						<label>文书受理日期:</label>${jmxx.wsslrq}</br>
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