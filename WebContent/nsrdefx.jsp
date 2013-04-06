<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrDefx,cn.com.gszw.impl.UserDAOImpl"%>
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
		List<NsrDefx> defxlist = nsrxxDao.getDefxList(nsrnbm);
		if (defxlist != null  ) {
			if(defxlist.size() != 0){
				session.removeAttribute("defxlist");
				session.setAttribute("defxlist", defxlist);
				%>
				<c:forEach var="defx" items="${defxlist}">
					<label>分析月份(申报时间):</label>${defx.fxyf}</br>
					<label>定额:</label>${defx.de}</br>
					<label>超定额补税:</label>${defx.cdebs}</br>
					<label>正常申报:</label>${defx.zcsb}</br>
					<label>其他申报:</label>${defx.qtsb}</br>
					<label>停业天数:</label>${defx.tyts}</br>
					<label>停业扣除:</label>${defx.tykc}</br>
					<label>停业预缴:</label>${defx.tyyj}</br>
					<label>多(少)征税款:</label>${defx.lzje}</br>			
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