package com.jsp.servlet;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.bean.Goods;
import com.jsp.bean.Category;
import com.jsp.service.GoodsService;
import com.jsp.service.CategoryService;
import com.jsp.utils.TranJson;
/**
 * �����servlet
 * @author asus
 *
 */
public class CategoryServlet extends BaseServlet {
	
	//��ѯ���еķ���
	public String findAllCategory(HttpServletRequest req, HttpServletResponse resp) {
		//���÷�����ѯ���еķ���
		CategoryService service = new CategoryService();
		List<Category> list = service.findAllCategory();
	//	new TranJson().getJson(req, resp, list);
		req.setAttribute("list", list);
		return "/mainface/buttom.jsp";	
	}
	
	public String findCategory(HttpServletRequest req, HttpServletResponse resp) {
		//���÷�����ѯ���еķ���
		CategoryService service = new CategoryService();
		List<Category> list = service.findAllCategory();
	//	new TranJson().getJson(req, resp, list);
		req.setAttribute("list", list);
		return "/other/category.jsp";	
	}
	//��̨����Ա��ѯ���еķ���
	public String findAlladminCategory(HttpServletRequest req, HttpServletResponse resp) {
		//���÷�����ѯ���еķ���
		CategoryService service = new CategoryService();
		List<Category> list = service.findAllCategory();
		req.setAttribute("list", list);
		return "/adminjsps/admin/category/list.jsp";
	}
	
	//��̨����Ա���ӷ���
	public String addCategory(HttpServletRequest req, HttpServletResponse resp) {
		//�õ��ύ������
		String cname = req.getParameter("cname");
		String uuid = UUID.randomUUID().toString();
		//��װʵ��
		Category category = new Category();
		category.setCid(uuid);
		category.setCname(cname);
		
		//���÷���ʵ������
		CategoryService service = new CategoryService();
		service.addCategory(category);
		return "/category?method=findAlladminCategory";
	}
	//���ݲ�ѯ���࣬�ѷ�����ʾ��ҳ����
	public String dispatcherCategory(HttpServletRequest req, HttpServletResponse resp) {
		//�õ����������id
		String cid = req.getParameter("cid");
		//����id��ѯ����
		CategoryService service = new CategoryService();
		Category cate = service.findByCid(cid);
		req.setAttribute("cate", cate);
		return "/adminjsps/admin/category/mod.jsp";
	}
	
	//����cid�޸ķ�������
	public String updateCategory(HttpServletRequest req, HttpServletResponse resp) {
		String cid = req.getParameter("cid");
		String cname = req.getParameter("cname");
		//��װʵ��
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		//���÷���ʵ��
		CategoryService service = new CategoryService();
		service.updateCategory(category) ;
		return "/category?method=findAlladminCategory";
	}
	
	//����idɾ������
	public String deleteCategory(HttpServletRequest req, HttpServletResponse resp) {
		//�õ�Ҫɾ�������id
		String cid = req.getParameter("cid");
		//���Ȱ�id�����ͼ�飬ͼ�������id����Ϊ��
		GoodsService bookService = new GoodsService();
		List<Goods> booklist = bookService.findGoodsByCid(cid);
		//������Щͼ���id������Щͼ���idֵ����Ϊnull
		CategoryService service = new CategoryService();
		service.updateGoodsCid(booklist);
		//�ٽ��з����ɾ��
		service.deleteCategoryByCid(cid);
		return "/category?method=findAlladminCategory";
	}
}





