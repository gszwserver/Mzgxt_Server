package cn.com.gszw.impl;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;

import cn.com.gszw.dao.NsrxxDAO;
import cn.com.gszw.dao.UserDAO;
import cn.com.gszw.model.FpTs;
import cn.com.gszw.model.Fp_zcxx;
import cn.com.gszw.model.NsrYh;
import cn.com.gszw.model.SwjgList;
import cn.com.gszw.model.User;
import cn.com.gszw.util.DBUtil;
import cn.com.gszw.util.MD5EncryptUtils;
import cn.com.gszw.util.SQLHelper;

public class UserDAOImpl implements UserDAO {
	private String sql;

	@Override
	public List<SwjgList> getSwjgList(String swjg) {
		return null;
	}

	@Override
	public User login(String account, String password, String telId) {
		sql = "select 1 aa\n" + "from T_GS_SJYZ a\n" + "where a.swry_dm=?\n"
				+ "and a.telid=?\n" + "and a.xybz='Y'";
		// 验证手机号是否通过
		int bool = 1;

		ResultSet rs = SQLHelper.getResultSet(sql, account, telId);

		// 如果不存在
		try {
			if (!rs.next()) {
				// 再查询是否在表中有数据
				sql = "select 1 aa\n" + "from T_GS_SJYZ a\n"
						+ "where a.swry_dm=?\n" + "and a.telid=?\n"
						+ "and a.xybz='N'";
				ResultSet rs1 = SQLHelper.getResultSet(sql, account, telId);
				if (!rs1.next()) {

					bool = -2;
				} else {
					bool = -1;
				}
			} else {
				// 如果手机号注册成功
				// 对于已存在用户，记录其最后登陆时间
				sql = "update T_GS_SJYZ\n" + "set last_time=sysdate\n"
						+ "where swry_dm=?\n" + "and telid=?";
				SQLHelper.ExecSql(sql, account, telId);

				bool = 1;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		User user = new User();
		switch (bool) {
		case 1:// 如果是已注册人员，则验证人员信息
			sql = "select ry.xb ,ry.mc rymc, ry.swjg_dm jgdm, jg.mc jgmc\n"
					+ "  from t_dm_gy_swry ry, t_dm_gy_swjg jg\n"
					+ " where ry.swjg_dm = jg.swjg_dm\n"
					+ "   and SWRY_DM = ?\n" + "   and USERPASSWORD = ?";

			ResultSet rs4 = SQLHelper.getResultSet(sql, account,
					MD5EncryptUtils.MD5Encode(password));

			try {
				if (rs4.next()) {

					user.setAccount(account);

					user.setPassword(password);
					user.setUsername(rs4.getString("rymc"));
					user.setSwjgmc(rs4.getString("jgmc"));
					user.setSwjgdm(rs4.getString("jgdm"));
					user.setUserKey(GetKey(account));
					user.setXb(rs4.getString("xb"));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case -1:// 如果是已注册，但还没有得到管理员审批人员

			user.setAccount("-1");
			break;

		case -2:// 还没有不册人员

			user.setAccount("-2");
			break;

		}

		return user;
	}

	@Override
	public String login_zc(String account, String telId, String jgjc) {
		// 首先检测手机是否在征管系统中注册，如果没有注册，则返回account为-1
		sql = "select 1 aa\n" + "from T_GS_SJYZ a\n" + "where a.swry_dm=?\n"
				+ "and a.telid=?\n";

		String jj = "-1";

		try {

			ResultSet rs = SQLHelper.getResultSet(sql, account, telId);

			// 如果不存在
			if (!rs.next()) {

				// 如果表中没有数据，需要新增数据行
				sql = "insert into T_GS_SJYZ(swry_dm,telid,xybz,lr_sj,jgjc) values (?,?,?,sysdate,?)";

				SQLHelper.ExecSql(sql, account, telId, "Y", jgjc);

				jj = "1";
			} else {
				// 如果手机号注册成功
				// 对于已存在用户，记录其最后登陆时间
				sql = "update T_GS_SJYZ\n" + "set jgjc=?\n"
						+ "where swry_dm=?\n" + "and telid=?";
				SQLHelper.ExecSql(sql, jgjc, account, telId);
				jj = "1";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jj;
	}

	public String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	// 为安全考虑YzKey和GetKey需要进一步优化
	// 根据人员编码产生Key
	public String GetKey(String s) {
		long i = 0;
		i = Long.parseLong(s);
		i = i + 720806;
		String key = KL(String.valueOf(i));
		// System.out.println("KEY:="+key);

		return key;
	}

	// 根据Key验证是不是本系统用户发来的数据，如果是则返加真，否则返回假
	public boolean YzKey(String s) {
		boolean flag = false;

		if (!s.equals("0")) {
			String swry_dm = JM(s);
			long ss = Long.parseLong(swry_dm);
			ss = ss - 720806;
			swry_dm = String.valueOf(ss);

			// 把税务人员编码解密，再在系统中查是否是该税务人员
			sql = "select * from t_dm_gy_swry ry where  SWRY_DM=? ";
			try {
				ResultSet rs = SQLHelper.getResultSet(sql, swry_dm);
				if (rs.next()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}

		} else {
			flag = false;
		}

		return flag;
	}

	// 可逆的加密算法
	public static String KL(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 'w');
		}
		String s = new String(a);
		return s;
	}

	// 加密后解密
	public static String JM(String inStr) {
		if (inStr == null) {
			return "";
		}
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 'w');
		}
		String k = new String(a);
		return k;
	}

	@Override
	public boolean test() {
		sql = "select sysdate from dual";
		boolean result = false;
		try {

			ResultSet rs = SQLHelper.getResultSet(sql);
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}

	@Override
	// 写手机消费者断的登录和查询日值信息
	public void login_sj(Fp_zcxx xx) {
		try {

			// 如果表中没有数据，需要新增数据行
			sql = "insert into \n"
					+ "T_GS_SJ_xfz (msg,Fpdm,Fphm,Jd,Wd,Dz,Lr_Sj)\n"
					+ " values \n" + "(?,?,?,?,?,?,sysdate)";

			SQLHelper.ExecSql(sql, xx.getMsg(), xx.getFpdm(), xx.getFphm(),
					xx.getJd(), xx.getWd(), xx.getDz());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	@Override
	// 发票举报信息保存
	public String Fp_Xfzts(FpTs xx) {
		String ret = "-1";
		try {

			// 如果表中没有数据，需要新增数据行
			sql = "insert into T_GS_SJ_ts\n" + "(\n" + "  nsrmc ,\n"
					+ "  FPDM  ,\n" + "  FPHM  ,\n" + "  JD    ,\n"
					+ "  WD    ,\n" + "  DZ    ,\n" + "  xm    ,\n"
					+ "  dh    ,\n" + "  sx    ,\n" + "  LR_SJ,je\n"
					+ ") values\n" + "(?,?,?,?,?,?,?,?,?,sysdate,?)";
			SQLHelper.ExecSql(sql, xx.getNsrmc(), xx.getFpdm(), xx.getFphm(),
					xx.getJd(), xx.getWd(), xx.getDz(), xx.getXm(), xx.getDh(),
					xx.getSx(), xx.getJe());

			ret = "1";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return ret;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.gszw.dao.UserDAO#validateYHYZ(java.lang.Object[])
	 */
	@Override
	public boolean validateYHYZ(String swrydm, String password) {
		String mString = "select 1 from t_dm_gy_swry ry where ry.swry_dm=? and ry.userpassword=?";
		ResultSet mResultSet = SQLHelper
				.getResultSet(mString, swrydm, password);
		try {
			if (mResultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.gszw.dao.UserDAO#validateSJYZ(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject validateSJYZ(String swrydm, String imsi, String imei)
			throws JSONException, SQLException {
		String mString = "select 1 from t_mobile_validatesjyz yz where yz.swrydm=? and yz.imsi=? and yz.imei=? and yz.xybj='Y'";
		ResultSet mResultSet = SQLHelper.getResultSet(mString, swrydm, imsi,
				imei);
		if (mResultSet.next()) {
			// 更新用户最近登录时间
			mString = "update t_mobile_validatesjyz yz set yz.lasttime=sysdate where yz.swrydm=? and yz.imsi=? and yz.imei=? and yz.xybj='Y'";
			if (SQLHelper.ExecSql(mString, swrydm, imsi, imei) > -1)
				// 手机验证成功
				return new JSONObject().put("fhjg", "000");
			else {
				return new JSONObject().put("fhjg", "999");
			}
		} else {
			mString = "select 1 from t_mobile_validatesjyz yz where yz.swrydm=? and yz.imsi=? and yz.imei=? and yz.xybj='N'";
			mResultSet = SQLHelper.getResultSet(mString, swrydm, imsi, imei);
			if (mResultSet.next()) {
				// 手机已注册但未通过审核
				return new JSONObject().put("fhjg", "010");
			} else {
				// 手机未注册
				return new JSONObject().put("fhjg", "011");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.gszw.dao.UserDAO#getSwjg(java.lang.String)
	 */
	@Override
	public JSONObject getSwjgSwry(String swrydm) {
		String mString = "select jg.swjg_dm swjgdm,jg.mc swjgmc,ry.mc swrymc,ry.xb from t_dm_gy_swry ry ,t_dm_gy_swjg jg where jg.swjg_dm=ry.swjg_dm and ry.swry_dm=?";
		ResultSet mResultSet = SQLHelper.getResultSet(mString, swrydm);
		try {
			if (mResultSet.next()) {
				JSONObject mJsonObject = new JSONObject();
				mJsonObject.put("swrymc", mResultSet.getString("swrymc"));
				mJsonObject.put("swjgdm", mResultSet.getString("swjgdm"));
				mJsonObject.put("swjgmc", mResultSet.getString("swjgmc"));
				mJsonObject.put("xb", mResultSet.getString("xb"));
				mJsonObject.put("userKey", GetKey(swrydm));
				return mJsonObject;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} catch (JSONException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.gszw.dao.UserDAO#insertSJYH(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject insertSJYH(String swrydm, String imsi, String imei)
			throws SQLException, JSONException {
		String mString = "insert into t_mobile_validatesjyz(swrydm,imsi,imei,registtime,lasttime,xybj)values(?,?,?,sysdate,sysdate,'N')";
		if (SQLHelper.ExecSql(mString, swrydm, imsi, imei) > -1) {
			return new JSONObject().put("fhjg", "0110");
		} else {
			return new JSONObject().put("fhjg", "0111");
		}
	}

}
