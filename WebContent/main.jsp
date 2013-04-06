<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="cn.com.gszw.dao.NsrxxDAO,cn.com.gszw.impl.NsrxxDAOImpl,java.util.List,cn.com.gszw.model.main,cn.com.gszw.impl.UserDAOImpl,cn.com.gszw.dao.UserDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String account = request.getParameter("account");
	String qt = request.getParameter("qt");	
	String userKey = request.getParameter("userKey");
	System.out.println("userKey=" + userKey);
	UserDAOImpl dao = new UserDAOImpl();
	System.out.println("jsp====="+dao.YzKey(userKey));
	if (dao.YzKey(userKey)) {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		main mainlist = nsrxxDao.GetMainList(account,qt);
		if (mainlist != null  ) {
			session.removeAttribute("mainlist");
			session.setAttribute("mainlist", mainlist);
				%>
<table border="1" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td colspan="4" width="100%" align="center">管户情况</td>
  </tr>
  <tr>
    <td colspan="3" align="center">管户总计</td>
    <td align="right">${mainlist.sj01}</td>
  </tr>
  <tr>
    <td colspan="2" rowspan="5">其中：</td>
    <td>正常</td>
    <td align="right">${mainlist.sj02}</td>
  </tr>
  <tr>
    <td>停业</td>
    <td align="right">${mainlist.sj03}</td>
  </tr>
  <tr>
    <td>非正常</td>
    <td align="right">${mainlist.sj04}</td>
  </tr>
  <tr>
    <td>注销</td>
    <td align="right">${mainlist.sj05}</td>
  </tr>
  <tr>
    <td>其他</td>
    <td align="right">${mainlist.sj06}</td>
  </tr>
  <tr>
    <td colspan="3" align="center">税收管户</td>
    <td align="right">${mainlist.sj07}</td>
  </tr>
  <tr>
    <td colspan="2" rowspan="5">其中：</td>
    <td>单位纳税人</td>
    <td align="right">${mainlist.sj08}</td>
  </tr>
  <tr>
    <td>个体经营户</td>
    <td align="right">${mainlist.sj09}</td>
  </tr>
  <tr>
    <td>扣缴义务人</td>
    <td align="right">${mainlist.sj10}</td>
  </tr>
  <tr>
    <td>内部户</td>
    <td align="right">${mainlist.sj11}</td>
  </tr>
  <tr>
    <td>其他纳税人</td>
    <td align="right">${mainlist.sj12}</td>
  </tr>
  <tr>
    <td colspan="2">其中：</td>
    <td>用票户</td>
    <td align="right">${mainlist.sj13}</td>
  </tr>
  <tr>
    <td colspan="3" align="center">社保管户</td>
    <td align="right">${mainlist.sj14}</td>
  </tr>
  <tr>
    <td colspan="2" rowspan="2">其中：</td>
    <td>单位纳费人</td>
    <td align="right">${mainlist.sj15}</td>
  </tr>
  <tr>
    <td>个人纳费人</td>
    <td align="right">${mainlist.sj16}</td>
  </tr>
  <tr>
    <td colspan="2" rowspan="3">其中：</td>
    <td>纯社保户</td>
    <td align="right">${mainlist.sj17}</td>
  </tr>
  <tr>
    <td>灵活就业人员</td>
    <td align="right">${mainlist.sj18}</td>
  </tr>
  <tr>
    <td>交叉管户</td>
    <td align="right">${mainlist.sj19}</td>
  </tr>
  <tr>
    <td colspan="4" align="center">缴税情况</td>
  </tr>
  <tr>
    <td colspan="3" align="center">累计申报</td>
    <td align="right">${mainlist.sj20}</td>
  </tr>
  <tr>
    <td colspan="2" rowspan="2">其中：</td>
    <td>已开票</td>
    <td align="right">${mainlist.sj21}</td>
  </tr>
  <tr>
    <td>未开票</td>
    <td align="right">${mainlist.sj22}</td>
  </tr>
  <tr>
    <td colspan="3" align="center">累计征收</td>
    <td align="right">${mainlist.sj23}</td>
  </tr>
  <tr>
    <td colspan="2" rowspan="2">其中：</td>
    <td>已入库</td>
    <td align="right">${mainlist.sj24}</td>
  </tr>
  <tr>
    <td>未入库</td>
    <td align="right">${mainlist.sj25}</td>
  </tr>
</table>	
					<p>----------------------------</p>
					<p>----------------------------</p>
					<p>----------------------------</p>
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
<body >

</body>
</html>



