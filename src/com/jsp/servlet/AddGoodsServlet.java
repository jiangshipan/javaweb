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
 * ʵ��������Ʒ
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
		 * �ϴ��Ĳ��裺
		 * 1�����������ļ����
		 * 2�����������ϴ���
		 * 3������request����
		 * 4������list����
		 * 5���������ϣ��ж���ͨ��������ļ��ϴ���
		 * */
		try {
			//���������ļ����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//���������ϴ���
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			//�����ϴ��ļ����ư�������
			fileUpload.setHeaderEncoding("utf-8");
			//����request����
			List<FileItem> list = fileUpload.parseRequest(request);
			//����map����
			Map<String,Object> map = new HashMap<String,Object>();
			String filename = null;
			//��������
			for (FileItem fileItem : list) {
				//�ж���ͨ������
				if(fileItem.isFormField()) {
					//ѭ����ÿ��ֻ�ܵõ�һ�����ƺ�ֵ������һ���԰����е�ֵ���õ�
					String name = fileItem.getFieldName();
					//��ȡ��ͨ�������ֵ���ʹ�����������
					String value = fileItem.getString("utf-8");
					//���԰�name��value�ŵ�map��������
					map.put(name, value);
//					System.out.println(map);
				} else {//�ļ��ϴ���
					//�õ��ϴ����ļ������� c:\test\1.txt
					filename = fileItem.getName();
					int lens = filename.lastIndexOf("\\");//���һ��\λ��
					if(lens != -1) {
						//��ȡ
						filename = filename.substring(lens+1);
					}
					//�õ��ϴ���·�� ����ȫ·����
					String path = getServletContext().getRealPath("/goods_img");
					//�õ��ļ��ϴ�������
					InputStream in = fileItem.getInputStream();
					//ʹ�������д���ļ�������
					OutputStream out = new FileOutputStream(path+"/"+filename);
					//���Խ�
					int len = 0;
					byte[] b = new byte[1024];
					while((len=in.read(b))!=-1) {
						out.write(b, 0, len);
					}
					//�ر���
					in.close();
					out.close();
				}
			}
			
			//����Ŀ�ģ�����Ʒ��Ϣ�浽���ݿ�
			//����Ʒ��Ϣ��װ���������棬��Ϊ�󲿷ֶ���map���棬ʹ��beanutilsʵ�ַ�װ
			Goods goods = new Goods();
			BeanUtils.populate(goods, map);
			//ȱ��������ֵ��һ����gid��һ��ͼƬ�ĵ�ַ
			goods.setGid(UUID.randomUUID().toString());
			goods.setImage("goods_img/"+filename);
			//���÷�������Ʒ�浽���ݿ�
			GoodsService service = new GoodsService();
			service.addGoodsAdmin(goods);
			
			//ת������Ʒ�б��ҳ��
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
