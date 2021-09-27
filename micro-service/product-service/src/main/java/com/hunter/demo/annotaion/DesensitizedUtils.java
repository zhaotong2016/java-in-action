package com.hunter.demo.annotaion;

import org.apache.commons.lang3.StringUtils;

public class DesensitizedUtils {

    public static String userName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return "";
        }
        return strProcess(userName);
    }


    public static String accountNo(String accountNo) {
        if (StringUtils.isBlank(accountNo)) {
            return "";
        }
        return strProcess(accountNo);
    }

    /**
     * a.长度5个及以下字符的，首尾各保留1个，其他用***代替；
     *
     * b.长度6-8个字符的，首尾各保留2个，其他用***代替；
     *
     * c.长度9-12个字符的，首尾各保留3个，其他用***代替；
     *
     * d.长度12个字符以上，首尾各保留4个，其他用***代替 ；
     *
     * 匹配规则
     * @param str
     * @return
     */
    private static String strProcess(String str){
        int before,after;

        if (str.length() <= 5){
            before = 1;
            after = 1;
        }else if (str.length() >= 6 && str.length() <= 8){
            before = 2;
            after = 2;
        }else if (str.length() >= 9 && str.length() <= 12){
            before = 3;
            after = 3;
        }else {
            before = 4;
            after = 4;
        }

        String subBefore = str.substring(0,before);
        String subAfter = str.substring(str.length()-after,str.length());

        return subBefore + "" + StringUtils.leftPad(subAfter,str.length()-before,"*" );
    }


    public static void main(String[] args) {


        System.out.println(strProcess("12345"));

        System.out.println(strProcess("123456"));

        System.out.println(strProcess("123456789"));
        System.out.println(strProcess("1234567891023"));
    }

}
