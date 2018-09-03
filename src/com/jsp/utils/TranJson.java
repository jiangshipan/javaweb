package com.jsp.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class TranJson extends HttpServlet {

	public void getJson(HttpServletRequest request,HttpServletResponse response,Object object){
        response.setContentType("text/html;charset=UTF-8");  
        //禁用缓存，确保网页信息是最新数据  
        response.setHeader("Pragma","No-cache");      
        response.setHeader("Cache-Control","no-cache");      
        response.setDateHeader("Expires", -10);  
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String jsonStr = JSON.toJSONString(object);
            out.print("{"+"\"result\":"+jsonStr+"}");  
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            out.close();
        }
    }

}
