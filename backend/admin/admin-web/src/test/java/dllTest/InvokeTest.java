package dllTest;
/**
作者：fengchase
时间：2018年7月20日
文件：InvokeTest.java
项目：admin-web
*/

/**
 * 需要引入jna-4.4.0.jar 和 jna-platform-4.4.0
 * 包下载地址：https://github.com/java-native-access/jna
 * @author stagebo
 *
 */
public class InvokeTest {
    /**
     * 调用示例
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.version"));//输出当前jdk版本号
        System.out.println(System.getProperty("sun.arch.data.model"));//输出当前jdk所用平台
         
         
    }
}
