<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrFphd,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
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
		List<NsrFphd> fphdlist = nsrxxDao.getFphdxxList(nsrnbm);
		if (fphdlist != null  ) {
			if(fphdlist.size() != 0){
				session.removeAttribute("fphdlist");
				session.setAttribute("fphdlist", fphdlist);	
				%>
				<c:forEach var="fphdxx" items="${fphdlist}">
					<label>发票种类代码:</label>${fphdxx.fpzldm}</br>
					<label>发票种类名称:</label>${fphdxx.fpzlmc}</br>
					<label>供票方式:</label>${fphdxx.gpfs}</br>
					<label>供票数量:</label>${fphdxx.gpsl}</br>
					<label>月购票数量:</label>${fphdxx.ygpsl}</br>
					<label>总购票量:</label>${fphdxx.zgpl}</br>
					<label>购票金额:</label>${fphdxx.gpje}</br>
					<label>验销方式:</label>${fphdxx.yxfs}</br>
					<label>核定人员:</label>${fphdxx.hdry}</br>		
					<label>核定日期:</label>${fphdxx.hdrq}</br>		
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