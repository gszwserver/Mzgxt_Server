package cn.com.gszw.dao;

import java.sql.SQLException;
import java.util.List;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;

import cn.com.gszw.model.FpTs;
import cn.com.gszw.model.Fp_zcxx;
import cn.com.gszw.model.SwjgList;
import cn.com.gszw.model.User;

public interface UserDAO {
	/**
	 * 根据输入的税务人员代码及用户密码进行用户验证（true验证成功；false验证失败 ）
	 * 
	 * @author 赵帆
	 * @date 2013-3-30
	 */
	public boolean validateYHYZ(String swrydm, String password);

	/**
	 * 根据税务人员代码，SIM卡串码，手机串码进行手机验证（fhjg为"000"验证成功，"010"表示手机已注册但未审核，"011"表示手机未注册）
	 * 
	 * @author 赵帆
	 * @date 2013-3-30
	 */
	public JSONObject validateSJYZ(String swrydm, String imsi, String imei) throws JSONException, SQLException;

	/**
	 * 根据税务人员代码获取税务人员基本信息
	 * 
	 * @author 赵帆
	 * @date 2013-3-30
	 */
	public JSONObject getSwjgSwry(String swrydm);
	/**
	 * 手机信息注册（fhjg为"0110"表示手机信息注册成功，等待管理员审核；"0111"表示手机信息注册失败）
	* @author 赵帆                                           
	* @date 2013-3-30                                                                                                                                                                                                      
	 */
	public JSONObject insertSJYH(String swrydm,String imsi,String imei) throws SQLException, JSONException;

	public User login(String account, String password, String telId);

	public String login_zc(String account, String telId, String jgjc);

	public List<SwjgList> getSwjgList(String swjg);

	public boolean test();

	// 写手机消费者断的登录和查询日值信息
	public void login_sj(Fp_zcxx xx);

	public String Fp_Xfzts(FpTs xx);
}
