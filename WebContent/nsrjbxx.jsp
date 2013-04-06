<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,cn.com.gszw.model.Nsrxx,cn.com.gszw.impl.UserDAOImpl"%>
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
		Nsrxx nsrxx = nsrxxDao.getNsrBasicInfo(nsrnbm);
		if (nsrxx != null) {
			session = request.getSession();
			session.setAttribute("nsrxx", nsrxx);
			%>
			<label>开业日期:</label>${nsrxx.kyrq}</br>
			<label>纳税人识别码:</label>${nsrxx.nsrsbm}</br>
			<label>纳税人名称:</label>${nsrxx.nsrmc}</br>
			<label>法定代表人:</label>${nsrxx.fddbr}</br>
			<label>组织机构代码:</label>${nsrxx.zzjgdm}</br>
			<label>登记类型:</label>${nsrxx.djlx}</br>
			<label>登记状态:</label>${nsrxx.djzt}</br>
			<label>注册类型:</label>${nsrxx.zclx}</br>
			<label>隶属关系:</label>${nsrxx.lsgx}</br>		
			<label>行业:</label>${nsrxx.hy}</br>
			<label>申报方式:</label>${nsrxx.sbfs}</br>
			<label>征收方式:</label>${nsrxx.zsfs}</br>
			<label>县镇街道:</label>${nsrxx.xzjd}</br>
			<label>管理机关:</label>${nsrxx.gljg}</br>
			<label>专管员:</label>${nsrxx.zgy}</br>
		
			<label>生产经营地址:</label>${nsrxx.scjydz}</br>
			<label>注册地址:</label>${nsrxx.zcdz}</br>
			<label>注册地电话:</label>${nsrxx.zcddh}</br>
			<label>经营地电话:</label>${nsrxx.jyddh}</br>
			<label>从业人数:</label>${nsrxx.cyrs}</br>		
			<label>注册资本:</label>${nsrxx.zczb}</br>
			<label>法人证件号:</label>${nsrxx.frzjh}</br>		
			<label>法人电话:</label>${nsrxx.frdh}</br>
			<label>财务负责人:</label>${nsrxx.cwfzr}</br>
			<label>财务负责人电话:</label>${nsrxx.cwfzrdh}</br>
			<label>办税人:</label>${nsrxx.bsr}</br>		
			<label>办税人电话:</label>${nsrxx.bsrdh}</br>
			<label>税务代理人姓名:</label>${nsrxx.swdlrmc}</br>	
			<label>税务代理人电话:</label>${nsrxx.swdlrdh}</br>		
			<p>----------------------------</p>		
			<%
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