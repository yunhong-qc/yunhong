package com.admin.common.utils;

import java.util.Random;

/**
 * @author Admin
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    /**
      * @Description: 获取两位随机数
      * @Author: luojing
      * @Date: 2018/9/10 10:29
      */
    public static String getTwoNumber(Long no){
        if(no == 0){
            Random random = new Random();
            int ends = random.nextInt(999);
            return String.format("%02d",ends);//如果不足两位，前面补0
        }else{
            return String.format("%02d",no);//如果不足两位，前面补0
        }
    }
}
