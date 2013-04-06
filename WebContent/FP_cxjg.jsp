<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.model.Fpfplist,cn.com.gszw.model.FpDelist,cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.FpCx,cn.com.gszw.model.Fplist,cn.com.gszw.impl.UserDAOImpl"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String fpdm = request.getParameter("fpdm");
	String fphm = request.getParameter("fphm");
	String num = request.getParameter("num");
	String userKey = request.getParameter("userKey");
	if (userKey==null){
		userKey="0";
	}
	UserDAOImpl dao = new UserDAOImpl();
	NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
	FpCx cx = nsrxxDao.getFpCxList(fphm, fpdm, num);

	if (!num.equals("-1") && dao.YzKey(userKey)) {
		out.println("用户校验错误！");
	} else {
		if (cx != null) {
			switch (cx.getFlag()) {
			case 0:
				session.removeAttribute("cx");
				session.setAttribute("cx", cx);
%>
<strong><span style="color: #a90c0c">查询结果</span></strong></br>
<label>你所查询的发票不存在！</label>
</br>
<label>备注:</label>
${cx.bz}
</br>
<p>----------------------------</p>
<%
	break;
			case 1:
				FpDelist de = cx.getDe();
				session.removeAttribute("de");
				session.setAttribute("de", de);
				session.removeAttribute("cx");
				session.setAttribute("cx", cx);
%>
<strong><span style="color: #a90c0c">查询结果</span></strong></br>
<label>发票种类:</label>
${cx.fpzl}
</br>
<p>----------------------------</p>
<label>发票名称:</label>
${de.fpmc}
</br>
<label>发票代码:</label>
${de.fp_dm}
</br>
<label>起始号码:</label>
${de.fp_qshm}
</br>
<label>终止号码:</label>
${de.fp_zzhm}
</br>
<label>发售日期:</label>
${de.rq}
</br>
<label>纳税人名称:</label>
${de.nsrmc}
</br>
<label>纳税人编码:</label>
${de.nsrbm}
</br>
<label>机关名称:</label>
${de.fcjg_dm}
</br>
<p>----------------------------</p>
<label>注意事项:</label>
${cx.bz}
</br>
<p>----------------------------</p>
<p>----------------------------</p>
<p>----------------------------</p>
<p>----------------------------</p>

<%
	break;
			case 2:
				Fpfplist f = cx.getJkp();
				session.removeAttribute("f");
				session.setAttribute("f", f);
				session.removeAttribute("cx");
				session.setAttribute("cx", cx);
%>
<strong><span style="color: #a90c0c">查询结果</span></strong></br>
<label>发票种类</label>
${cx.fpzl}
</br>
<p>----------------------------</p>

<label>付款方名称:</label>
${f.fk}
</br>
<label>收款方名称:</label>
${f.sk}
</br>
<label>金额:</label>
${f.je}
</br>
<label>开票日期:</label>
${f.rq}
</br>
<label>发票状态:</label>
${f.zt}
</br>
<p>----------------------------</p>
<label>注意事项:</label>
${cx.bz}
</br>
<p>----------------------------</p>
<p>----------------------------</p>
<p>----------------------------</p>
<p>----------------------------</p>

<%
	break;
			case 3:
				List<Fplist> l = cx.getFplx();
				session.removeAttribute("l");
				session.setAttribute("l", l);
				session.removeAttribute("cx");
				session.setAttribute("cx", cx);
%>
<strong><span style="color: #a90c0c">查询结果</span></strong></br>
<label>发票种类</label>
${cx.fpzl}
</br>
<p>----------------------------</p>
<c:forEach var="fpfx" items="${l}">
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
	<label>注意事项:</label>${cx.bz}</br>
	<p>----------------------------</p>
	<p>----------------------------</p>
	<p>----------------------------</p>
	<p>----------------------------</p>
</c:forEach>
<%
	break;
			case 9:
				session.removeAttribute("cx");
				session.setAttribute("cx", cx);
%>
<strong><span style="color: #a90c0c">查询结果</span></strong></br>
<label>发票种类</label>
${cx.fpzl}
</br>
<p>----------------------------</p>
<label>你所查询的发票不存在！</label>
</br>
<label>备注：</label>
${cx.bz}
</br>
<p>----------------------------</p>
<%
	break;
			}

		} else {
			out.println("没有信息！");
		}
	}
%>
<html>
<head>
</head>
<body>

</body>
</html>