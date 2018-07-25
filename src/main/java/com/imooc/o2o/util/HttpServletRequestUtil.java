package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Alex
 * @Date: created in 15:29  2018/7/4
 * @Annotation:负责处理HttpServletRequest参数
 */
public class HttpServletRequestUtil {

    public static int getInt(HttpServletRequest request,String key){
        try{
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

   public static long getLong(HttpServletRequest request,String key){
       try{
           return Long.valueOf(request.getParameter(key));
       }catch (Exception e){
           return -1;
       }
   }

   public static Double getDouble(HttpServletRequest request,String key){
       try{
           return Double.valueOf(request.getParameter(key));
       }catch (Exception e){
           return -1d;
       }
   }

   public static Boolean getBoolean(HttpServletRequest request,String key){
       try{
           return Boolean.valueOf(request.getParameter(key));
       }catch (Exception e){
           return false;
       }
   }

   public static String getString(HttpServletRequest request,String key){
        try{
            String result = request.getParameter(key);
            System.out.println("结果： "+result);
            if(result != null){
                result = result.trim();
            }
            if("".equals(result)){
                result = null;
            }
            return result;
        }catch (Exception e){
            return null;
        }
   }
}
