package test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

/**
作者：fengchase
时间：2018年9月11日
文件：chtest.java
项目：changxiang
*/
public class chtest {

	@Test
	public void test() {
		String vcode="";
		for(int i=0;i<6;i++) {
			Random rm=new Random();
			vcode=vcode+rm.nextInt(10);
		}
		System.out.println(vcode);
	}

}

