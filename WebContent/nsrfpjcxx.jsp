<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrFpjc,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
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
		List<NsrFpjc> fpjclist = nsrxxDao.getFpjcxxList(nsrnbm);
		if (fpjclist != null  ) {
			if(fpjclist.size() != 0){
				session.removeAttribute("fpjclist");
				session.setAttribute("fpjclist", fpjclist);	
				%>
				<c:forEach var="fpjc" items="${fpjclist}">
					<label>发售日期:</label>${fpjc.fsrq}</br>
					<label>发售人员:</label>${fpjc.fsry}</br>
					<label>发票种类:</label>${fpjc.fpzldm}</br>
					<label>发票代码:</label>${fpjc.fpdm}</br>
					<label>发票名称:</label>${fpjc.fp_jc}</br>
					<label>起始号码:</label>${fpjc.qshm}</br>
					<label>终止号码:</label>${fpjc.zzhm}</br>
					<label>发售数量:</label>${fpjc.fssl}</br>
					<label>发售份数:</label>${fpjc.fsfs}</br>
					<label>缴销数量:</label>${fpjc.jxsl}</br>		
					<label>缴销金额:</label>${fpjc.jxje}</br>		
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