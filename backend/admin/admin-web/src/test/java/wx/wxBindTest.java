package wx;

import java.util.Map;

import org.junit.Test;

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
		String xml="  <xml>  <ToUserName><![CDATA[gh_05fc6bc0923c]]></ToUserName>\r\n" + 
				"    <Encrypt><![CDATA[lTnv9TUvP3bCYbV9PpZwl4VtZSC8d7Va2sZFlXomB9070AhwpNsnCPr4t7\r\n" + 
				"DTMV4UN4fGaQL3kNbwU/u3JgviwCovtSHyI7fmtm+dbgImoKNm6TjNJSmmKbPzkwaRYnw6bL8hhG18oZ\r\n" + 
				"tz6eEuYDNOZD6k5mvRqp9NxWEwNKgUbMfxLF1VECfG241NMKtnquDlsuFmwmVLbikjBvMvvFLG06t9uQ\r\n" + 
				"/82zmcIAkP0xpjZi5iWw+6FblcqVm1Hsyf+KzY2M7jxEHrS14f/xLbwnQ7Wz6nGuVVr8SenWzDGVktE1\r\n" + 
				"ufwLKQu/bCtsrtZYGCAJXkAytbNlyGzRTO4ACRdyyAfXuuBcoj1DagOvOif/k96bP3K2ffeVtDHJRg4A\r\n" + 
				"J8dOmBqiLMFnhxSV8Z5BiHsxeZr4fj3m1Mh1NJZn+GJteagu0L4SWYqNQeaJd14NZD9WPRZ49EWhx0Vy\r\n" + 
				"SWKS8gz98wXMRekasJ+NiKeqwISBncJPfeGGv1cVeTWY2cNVKq8oGX]]></Encrypt>\r\n" + 
				"</xml>\r\n" + 
				"";
		try {
			//解密
			  WXBizMsgCrypt wx=WXBizMsgCrypt.getWxCrypt();
		      String res=wx.decryptMsg(xml);
		      Map<String,String> map=StrXmlToMap.strXmltoMap(res);
		      String result=wx.encryptMsg("nihao", "132", "456");
		      System.out.println(res);
		      System.out.println(result);
		      System.out.println(map.get("MsgType"));
			// HttpUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appsecret+"");
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}
	}
}
