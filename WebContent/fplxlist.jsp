<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.Fplist,cn.com.gszw.impl.UserDAOImpl"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String fpdm = request.getParameter("fpdm");
	String fphm = request.getParameter("fphm");
	String userKey = request.getParameter("userKey");
	System.out.println("userKey=" + userKey);
	UserDAOImpl dao = new UserDAOImpl();
	System.out.println(dao.YzKey(userKey));
	if (dao.YzKey(userKey)) {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		List<Fplist> fpfxlist = nsrxxDao.getFpList(fphm,fpdm);
		if (fpfxlist != null  ) {
			if(fpfxlist.size() != 0){
				session.removeAttribute("fpfxlist");
				session.setAttribute("fpfxlist", fpfxlist);
				%>
				<c:forEach var="fpfx" items="${fpfxlist}">
					<label>状态:</label>${fpfx.zt}</br>
					<label>日期:</label>${fpfx.rq}</br>
					<label>发出机关:</label>${fpfx.fcjg_dm}</br>
					<label>发出仓库:</label>${fpfx.fcckmc}</br>
					<label>发出人:</label>${fpfx.fcr_dm}</br>	
					<label>接受机关:</label>${fpfx.jsjg_dm}</br>
					<label>接受仓库:</label>${fpfx.jsckmc}</br>
					<label>接受人:</label>${fpfx.jsr_dm}</br>
					<label>购买纳税人:</label>${fpfx.nsrmc}</br>
					
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