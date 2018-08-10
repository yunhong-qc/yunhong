package dllTest;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

import com.admin.pay.domain.QcPayOrderDO;
import com.admin.utils.DateUtils;
import com.admin.utils.FileLog;
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

	
	public void test3334() {
	}
	@Test
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

}
