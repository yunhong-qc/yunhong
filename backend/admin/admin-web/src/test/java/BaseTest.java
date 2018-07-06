import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
作者：fengchase
时间：2018年7月6日
文件：BaseTest.java
项目：admin-web
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-*.xml"})
public class BaseTest {}


