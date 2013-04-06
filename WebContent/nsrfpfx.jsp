<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.NsrFpfx,cn.com.gszw.impl.UserDAOImpl"%>
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
		List<NsrFpfx> fpfxlist = nsrxxDao.getFpfxList(nsrnbm);
		if (fpfxlist != null  ) {
			if(fpfxlist.size() != 0){
				session.removeAttribute("fpfxlist");
				session.setAttribute("fpfxlist", fpfxlist);
				%>
				<c:forEach var="fpfx" items="${fpfxlist}">
					<label>月定额:</label>${fpfx.de}</br>
					<label>累计申报月份:</label>${fpfx.pzs}</br>
					<label>累计申报金额:</label>${fpfx.sbje}</br>
					<label>其中：正常申报:</label>${fpfx.zcsb}</br>
					<label>其中：发票补税:</label>${fpfx.bssb}</br>	
					<label>累计缴销数量:</label>${fpfx.jxsl}</br>
					<label>累计缴销金额:</label>${fpfx.jxje}</br>
					<label>累计发售份数:</label>${fpfx.fsfs}</br>
					<label>累计发售金额:</label>${fpfx.fsje}</br>
					<label>累计缴销差额:</label>${fpfx.ce}</br>	
					<label>累计发售差额:</label>${fpfx.fsce}</br>	
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