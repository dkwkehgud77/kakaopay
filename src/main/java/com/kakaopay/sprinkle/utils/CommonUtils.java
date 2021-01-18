package com.kakaopay.sprinkle.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Component
public class CommonUtils {
    private static CommonUtils instance = new CommonUtils();
    private static Calendar cal = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private CommonUtils(){}
    public CommonUtils getInstances(){
        return instance;
    }


    public Long getNowDate(){

        Date date = new Date();
        return Long.parseLong(sdf.format(date));
    }

    //
    public Long getExpireMinute(int minute){
        Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);

        return Long.parseLong(sdf.format(cal.getTime()));
    }

    public Long getExpireDay(int day){
        Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, day);

        return Long.parseLong(sdf.format(cal.getTime()));

    }


    public boolean isNumber(String str_num) {
        try {
            long str = Long.parseLong(str_num);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }


}
