package dllTest;

import org.junit.Test;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

/**
 * 作者：fengchase 时间：2018年7月20日 文件：test2.java 项目：admin-web
 */
public class test2 {

	@Test
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

}
