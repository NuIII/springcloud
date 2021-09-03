package com.atguigu.springcloud.util;


import org.apache.commons.lang3.StringUtils;

public class ConvertUtil {


    public static String convertTableToStandStr(String tableName, String prefix, String split) {
        String res = "";
        if (StringUtils.isNotBlank(tableName) && StringUtils.isNotBlank(prefix)) {
            if (tableName.contains(prefix)) {
                tableName = tableName.replace(prefix, "");
            }
            if (tableName.contains(split)) {
                String[] strArr = tableName.split(split);
                if (strArr != null && strArr.length > 0) {
                    for(String s : strArr){
                        String middle = s.toLowerCase();
                        res = res + middle.substring(0, 1).toUpperCase() + middle.substring(1);
                    }
                }
            }else{
                res = res + tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(convertTableToStandStr("GAIA_SD_PRODUCT_LIMIT_SET","GAIA_SD_","_"));
    }
}
