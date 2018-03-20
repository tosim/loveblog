package blog.util;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class StringUtil {
    public static String Html2Text(String htmlStr)
    {
        if (htmlStr == null || htmlStr.equals(""))
        {
            return "";
        }
        htmlStr  = htmlStr.replaceAll("</?[^>]+>", ""); //剔出<html>的标签

        htmlStr = htmlStr.replaceAll("\\s*|\t|\r|\n", "");//去除字符串中的空格,回车,换行符,制表符
//        System.out.println(htmlStr);
        htmlStr = htmlStr.trim();
        if(htmlStr.length() > 100){
            htmlStr = htmlStr.substring(0,100);
        }
        return htmlStr;
    }
}
