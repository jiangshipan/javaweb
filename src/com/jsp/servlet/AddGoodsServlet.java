package com.jsp.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.bean.Goods;
import com.jsp.service.GoodsService;
/**
 * 实现增加商品
 * @author asus
 *
 */
public class AddGoodsServlet extends HttpServlet {

	/**
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 上传的步骤：
		 * 1、创建磁盘文件项工厂
		 * 2、创建核心上传类
		 * 3、解析request对象
		 * 4、返回list集合
		 * 5、遍历集合，判断普通输入项还是文件上传项
		 * */
		try {
			//创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//创建核心上传类
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			//处理上传文件名称包含中文
			fileUpload.setHeaderEncoding("utf-8");
			//解析request对象
			List<FileItem> list = fileUpload.parseRequest(request);
			//创建map集合
			Map<String,Object> map = new HashMap<String,Object>();
			String filename = null;
			//遍历集合
			for (FileItem fileItem : list) {
				//判断普通输入项
				if(fileItem.isFormField()) {
					//循环，每次只能得到一个名称和值，不能一次性把所有的值都得到
					String name = fileItem.getFieldName();
					//获取普通输入项的值，和处理中文问题
					String value = fileItem.getString("utf-8");
					//可以把name和value放到map集合里面
					map.put(name, value);
//					System.out.println(map);
				} else {//文件上传项
					//得到上传的文件的名称 c:\test\1.txt
					filename = fileItem.getName();
					int lens = filename.lastIndexOf("\\");//最后一个\位置
					if(lens != -1) {
						//截取
						filename = filename.substring(lens+1);
					}
					//得到上传的路径 （完全路径）
					String path = getServletContext().getRealPath("/goods_img");
					//得到文件上传输入流
					InputStream in = fileItem.getInputStream();
					//使用输出流写到文件夹里面
					OutputStream out = new FileOutputStream(path+"/"+filename);
					//流对接
					int len = 0;
					byte[] b = new byte[1024];
					while((len=in.read(b))!=-1) {
						out.write(b, 0, len);
					}
					//关闭流
					in.close();
					out.close();
				}
			}
			
			//最终目的：把商品信息存到数据库
			//把商品信息封装到对象里面，因为大部分都在map里面，使用beanutils实现封装
			Goods goods = new Goods();
			BeanUtils.populate(goods, map);
			//缺少了两个值，一个是gid，一个图片的地址
			goods.setGid(UUID.randomUUID().toString());
			goods.setImage("goods_img/"+filename);
			//调用方法把商品存到数据库
			GoodsService service = new GoodsService();
			service.addGoodsAdmin(goods);
			
			//转发到商品列表的页面
			request.getRequestDispatcher("/goods?method=findAlladmin").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
