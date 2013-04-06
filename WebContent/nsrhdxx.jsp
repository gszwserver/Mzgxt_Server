<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrHdxx,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
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
		List<NsrHdxx> hdlist = nsrxxDao.getNsrHdxxList(nsrnbm);
		if (hdlist != null  ) {
			if(hdlist.size() != 0){
				session.removeAttribute("hdlist");
				session.setAttribute("hdlist", hdlist);	
				%>
				<c:forEach var="hdxx" items="${hdlist}">
					<label>征收项目:</label>${hdxx.zsxm}</br>
					<label>征收品目:</label>${hdxx.zspm}</br>
					<label>征收方式:</label>${hdxx.zsfs}</br>
					<label>计税金额:</label>${hdxx.jsje}</br>
					<label>核定起始日期:</label>${hdxx.hdqsrq}</br>
					<label>核定终止日期:</label>${hdxx.hdzzrq}</br>
					<label>应纳金额:</label>${hdxx.ynse}</br>
					<label>纳税期限:</label>${hdxx.nsqx}</br>
					<label>申报期限:</label>${hdxx.sbqx}</br>
					<label>缴款期限:</label>${hdxx.jkqx}</br>
					<label>税率:</label>${hdxx.sl}</br>
					<label>预算分配比例:</label>${hdxx.ysfpbl}</br>
					<label>国库:</label>${hdxx.gkid}</br>		
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