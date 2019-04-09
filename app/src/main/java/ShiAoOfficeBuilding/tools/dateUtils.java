package ShiAoOfficeBuilding.tools;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateUtils {



    public static String getYearAndMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
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
//    public static void main(String[] args) throws ParseException {
//
//        subMonth("2018-1",-2);
//
//    }


}
