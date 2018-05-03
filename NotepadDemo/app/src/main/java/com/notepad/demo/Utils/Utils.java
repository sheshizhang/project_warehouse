package com.notepad.demo.Utils;

import java.util.Calendar;

/**
 * Created by feiran.zhang on 2018/4/30.
 */

public class Utils {
//    public static String getDateTimer(){
//        Date date=new Date();
//        return date.toString();
//
//    }

    public static String getDateTimer2(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        String strtimer=year+"年"+month+"月"+day+"日"+hour+"点"+minute+"分";

        return strtimer;
    }


//    public static void SavaPadid(Context context){
//        SharedPreferences sharedPreferences=context.getSharedPreferences("ipad", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putInt(EditNotePadActivity.KEY,(int) EditNotePadActivity.padid);
//        editor.commit();
//    }
//
//    public static void GetPadid(Context context){
//        SharedPreferences sharedPreferences=context.getSharedPreferences("ipad",Context.MODE_PRIVATE);
//        EditNotePadActivity.padid=sharedPreferences.getInt(EditNotePadActivity.KEY,0);
//    }

}
