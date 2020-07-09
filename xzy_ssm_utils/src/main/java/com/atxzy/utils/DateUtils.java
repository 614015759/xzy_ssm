package com.atxzy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //日期格式转String
    public static String DatetoString(Date pruductDate,String pattern){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(pruductDate);

        return format;
    }


    //字符串转日期
    public static Date StringtoDate(String productDateStr,String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parse = simpleDateFormat.parse(productDateStr);
        return parse;
    }

}
