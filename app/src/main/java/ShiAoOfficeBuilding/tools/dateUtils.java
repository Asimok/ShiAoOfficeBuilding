package ShiAoOfficeBuilding.tools;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateUtils {



    public static String getdetailYear() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reStr = sdf.format(date);
        System.out.println(reStr);
        return reStr;
    }
    public static String getMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String reStr = sdf.format(date);
        System.out.println(reStr);
        return reStr;
    }
    public static String getYear() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String reStr = sdf.format(date);
        System.out.println(reStr);
        return reStr;
    }
    public static String subMonth(String Month, int num) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        Date date = sdf.parse(Month);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);


        calendar.add(Calendar.MONTH, num);
        Date dt1 = calendar.getTime();
        String reStr = sdf.format(dt1);
        System.out.println(reStr);
        return reStr;

    }
    public static String subyear(String year, int num) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        Date date = sdf.parse(year);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.add(Calendar.YEAR, num);
        Date dt1 = calendar.getTime();
        String reStr = sdf.format(dt1);
        System.out.println(reStr);
        return reStr;

    }
    public static String subhour(String hour, int num) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = sdf.parse(hour);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.add(Calendar.HOUR, num);
        Date dt1 = calendar.getTime();
        String reStr = sdf.format(dt1);
        System.out.println(reStr);
        return reStr;

    }
    public static boolean comparedate(String date1,String date2)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean next=false;
        try {
            Date date3 = format.parse(date1);
            Date date4 = format.parse(date2);
            //compareDate(date3,date4);//方式一
            next=compareDateByGetTime(date3, date4);//方式二
            return  next;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  next;
    }
    public static boolean compareDateByGetTime(Date date1, Date date2) {
        if (date1.before(date2)) {
            System.out.println(date1 + "在" + date2 + "前面");
            Log.d("gg", date1 + "在" + date2 + "前面");
            return true;
        } else if (date1.after(date2)) {
            System.out.println(date1 + "在" + date2 + "后面");
            Log.d("gg", date1 + "在" + date2 + "后面");
            return false;
        } else {
            System.out.println("是同一天的同一时间");
            Log.d("gg", "是同一天的同一时间");
            return true;
        }
    }


//    public static void main(String[] args) throws ParseException {
//
//        subMonth("2018-1",-2);
//
//    }


}
