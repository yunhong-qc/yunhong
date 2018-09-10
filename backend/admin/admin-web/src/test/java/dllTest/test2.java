package dllTest;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

import com.admin.pay.domain.QcPayOrderDO;
import com.admin.utils.DateUtils;
import com.admin.utils.FileLog;
import com.admin.utils.HttpUtils;
import com.admin.utils.TimeUtils;

/**
 * 作者：fengchase 时间：2018年7月20日 文件：test2.java 项目：admin-web
 */
public class test2 {

	public void dllTest() {
		String Privatekey = "<RSAKeyValue><Modulus>ijo+E5uPYPKMniDsopiGX/ZMRovAEL196dXYHcLG7/xckq2h0mMjuMwgoIA/JFAYu49I8dKeq9YGQBDcZ6XPkyBadGququoV3LfUNd1VF4FjnK9zYqEZpNVziXI/mmV09LzCOvYVtGkJNjXhzuVjk2lyIYFW5ZQxJfmPWZYOljc=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
		System.load("E:\\RSASecurity.dll");
		JNative jNative;
		try {
			jNative = new JNative("RSASecurity", "dataToEncrypt");

			jNative.setRetVal(Type.STRING); // 设置调用后的返回值类型
			jNative.setParameter(0, "你是二货"); // 参数下标是从0开始
			jNative.setParameter(1, Privatekey); // 参数下标是从0开始
			jNative.getFunctionName(); // dll 函数名
			jNative.getDLLName(); // dll文件名
			jNative.invoke(); // 执行dll方法
			System.out.println(jNative.getRetVal()); // 获取返回结果
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}
	}

	public void test44() {
		System.out.println(0.01 * 100);
	}

	public void test33() {
		try {
			String result = "\"token\":123123123,\"rspCode\":123213}";
			String token = null;
			String respCode = null;
			String tokenRegex = "\"token\":(.*?),";// 使用非贪婪模式
			String rspCodeRegex = "\"rspCode\":(.*?),";// 使用非贪婪模式
			Matcher tokenmatcher = Pattern.compile(tokenRegex).matcher(result);
			Matcher codematcher = Pattern.compile(rspCodeRegex).matcher(result);
			//
			if (tokenmatcher.find()) {
				token = tokenmatcher.group(1).replace("\"", "");
			} else {
				tokenRegex = "\"token\":(.*?)}";
				tokenmatcher = Pattern.compile(tokenRegex).matcher(result);
				if (tokenmatcher.find()) {
					token = tokenmatcher.group(1).replace("\"", "");
				}
			}
			if (codematcher.find()) {
				respCode = codematcher.group(1).replace("\"", "");
			} else {
				rspCodeRegex = "\"rspCode\":(.*?)}";// 使用非贪婪模式
				codematcher = Pattern.compile(rspCodeRegex).matcher(result);
				if (codematcher.find()) {
					respCode = codematcher.group(1).replace("\"", "");
				}
			}
			if (token != null) {
				token = token.replace("}", "");
				respCode = respCode.replace(",", "");
			}
			System.out.println(token + "--" + respCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void test13334() {
		try {
			String payPrice="100";
			Integer pr=Integer.parseInt(payPrice);
			if(pr==null || pr<1) {
				System.out.println(payPrice);
			}else {
				if(pr>=100) {
					System.out.println(pr/100+"");
				}else {
					System.out.println(pr/100.00+"");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			FileLog.errorLog(e,"465465465");
		}
	}

	@Test
	public void test454454() {
		System.out.println(new Date().getTime());
	}
	public void test234234() {
		String result;
		try {
			String html="<li><div class=\"sl-zx-list-t bg\" style=\"height:28rem;\"><span>饰品回购行情</span></div>\r\n" + 
					"					<div class=\"sl-zx-tb1 sl-zx-tb-ys1\">\r\n" + 
					"                                <div class=\"sl-zx-tb2\">千足金</div>\r\n" + 
					"                                <div class=\"sl-zx-tb3 sl-zx-tb-zt1\">263.76</div>\r\n" + 
					"							\r\n" + 
					"                                <div class=\"sl-zx-tb3 sl-zx-tb-zt1\">--</div><div class=\"sl-zx-tb3\">\r\n" + 
					"                        <div class=\"sl-zx-tb4\">264.91</div>\r\n" + 
					"                        <div class=\"sl-zx-tb4\">264.02</div>\r\n" + 
					"                    </div>\r\n" + 
					"                   </div><div class=\"sl-zx-tb1 sl-zx-tb-bg sl-zx-tb-ys1\">\r\n" + 
					"                                <div class=\"sl-zx-tb2\">18K金</div>\r\n" + 
					"                                <div class=\"sl-zx-tb3 sl-zx-tb-zt1\">195.17</div>\r\n" + 
					"							\r\n" + 
					"                                <div class=\"sl-zx-tb3 sl-zx-tb-zt1\">--</div><div class=\"sl-zx-tb3\">\r\n" + 
					"                        <div class=\"sl-zx-tb4\">196.02</div>\r\n" + 
					"                        <div class=\"sl-zx-tb4\">195.36</div>\r\n" + 
					"                    </div>\r\n" + 
					"                   </div><div class=\"sl-zx-tb1 sl-zx-tb-ys2\">\r\n" + 
					"                                <div class=\"sl-zx-tb2\">Pt950</div>\r\n" + 
					"                                <div class=\"sl-zx-tb3 sl-zx-tb-zt1\">162.74</div>\r\n" + 
					"							\r\n" + 
					"                                <div class=\"sl-zx-tb3 sl-zx-tb-zt1\">--</div><div class=\"sl-zx-tb3\">\r\n" + 
					"                        <div class=\"sl-zx-tb4\">165.07</div>\r\n" + 
					"                        <div class=\"sl-zx-tb4\">164.08</div>\r\n" + 
					"                    </div>\r\n" + 
					"                   </div><div class=\"sl-zx-tb1 sl-zx-tb-bg sl-zx-tb-ys2\">\r\n" + 
					"                                <div class=\"sl-zx-tb2\">Pt990</div>\r\n" + 
					"                                <div class=\"sl-zx-tb3 sl-zx-tb-zt1\">169.67</div>\r\n" + 
					"							\r\n" + 
					"                                <div class=\"sl-zx-tb3 sl-zx-tb-zt1\">--</div><div class=\"sl-zx-tb3\">\r\n" + 
					"                        <div class=\"sl-zx-tb4\">172.10</div>\r\n" + 
					"                        <div class=\"sl-zx-tb4\">171.06</div>\r\n" + 
					"                    </div>\r\n" + 
					"                   </div><div class=\"sl-zx-tb1 sl-zx-tb-ys2\">\r\n" + 
					"                                <div class=\"sl-zx-tb2\">Pd950</div>\r\n" + 
					"                                <div class=\"sl-zx-tb3 \">189.31</div>\r\n" + 
					"							\r\n" + 
					"                                <div class=\"sl-zx-tb3 \">--</div><div class=\"sl-zx-tb3\">\r\n" + 
					"                        <div class=\"sl-zx-tb4\">192.49</div>\r\n" + 
					"                        <div class=\"sl-zx-tb4\">190.68</div>\r\n" + 
					"                    </div>\r\n" + 
					"                   </div><div class=\"sl-zx-tb1 sl-zx-tb-bg sl-zx-tb-ys2\">\r\n" + 
					"                                <div class=\"sl-zx-tb2\">Pd990</div>\r\n" + 
					"                                <div class=\"sl-zx-tb3 \">197.50</div>\r\n" + 
					"							\r\n" + 
					"                                <div class=\"sl-zx-tb3 \">--</div><div class=\"sl-zx-tb3\">\r\n" + 
					"                        <div class=\"sl-zx-tb4\">200.82</div>\r\n" + 
					"                        <div class=\"sl-zx-tb4\">198.93</div>\r\n" + 
					"                    </div>\r\n" + 
					"                   </div><div class=\"sl-zx-tb1 sl-zx-tb-ys2\">\r\n" + 
					"                                <div class=\"sl-zx-tb2\">Ag925</div>\r\n" + 
					"                                <div class=\"sl-zx-tb3 \">2.751</div>\r\n" + 
					"							\r\n" + 
					"                                <div class=\"sl-zx-tb3 \">--</div><div class=\"sl-zx-tb3\">\r\n" + 
					"                        <div class=\"sl-zx-tb4\">2.799</div>\r\n" + 
					"                        <div class=\"sl-zx-tb4\">2.779</div>\r\n" + 
					"                    </div>\r\n" + 
					"                   </div> </li> ";
			Document doc = Jsoup.parse(html);
			// 获取html的标题
			String title = doc.select("title").text();
			System.out.println(title);
			
			// 获取按钮的文本
			String btnText = doc.select("div div div div div form").select("#su").attr("value");
			System.out.println(btnText);
			// 获取导航栏文本
			Elements elements = doc.select(".sl-zx-list-t");
		
			for (Element e : elements) {
				String childName=e.child(0).text();
				System.out.println("childName"+childName);
				if(childName.contains("回购")) {
					Elements tb1=doc.select(".sl-zx-tb1");
					for(Element e2 : tb1) {
						Elements tb2=e2.select(".sl-zx-tb3");
						for(Element e3 : tb2) {
							String e2h=e3.html();
							if(e2h.contains("-")) {
								continue;
							}
							System.out.println("值"+e2h);
							Double b=Double.parseDouble(e2h)+0.5;
							e3.html(b.toString());
						}
					}
				}
				
			}
			System.out.println(doc.toString());
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}
		
	}

}
