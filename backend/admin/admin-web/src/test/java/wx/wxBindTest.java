package wx;

import java.util.Map;

import org.junit.Test;

import com.admin.utils.FileLog;
import com.admin.utils.wx.SHA1;
import com.admin.utils.wx.StrXmlToMap;
import com.admin.utils.wx.WXBizMsgCrypt;
import com.admin.utils.wx.WxUtils;

/**
 * 作者：fengchase 时间：2018年7月6日 文件：wxBindTest.java 项目：admin-web
 */
public class wxBindTest {
	@Test
	public void wxtest() {
		/*WxApiController wac = new WxApiController();
		wac.goReadCardAnniu2();*/
//		String token = "11_E8M2F2f0_SYcRnPuvCFTEB3Hm7KnDm_Ye5aOHq2jdHa8GtDVg628DenMCyOIBM-5W2LAxWQzXy4ilnuM6kUVu44OU8nRMQAcrmdzsI9RNqIBAS-iQjawv292EFdUcdJ-MLvJ0wrL4CFD2BKsGORiAHAVDG";
		// String appId = "wx3d7f7c26f2369785";// 应用id
		// String appsecret = "c34fde54dfac1f9c2870ee8edf467ebf";// 应用秘钥
		try {
			//解密
			FileLog.errorLog("test1");
			FileLog.debugLog("test2");
			FileLog.systemLog("test3");
			// HttpUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appsecret+"");
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}
	}
}
