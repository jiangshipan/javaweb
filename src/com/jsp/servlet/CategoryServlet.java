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
 * 分类的servlet
 * @author asus
 *
 */
public class CategoryServlet extends BaseServlet {
	
	//查询所有的分类
	public String findAllCategory(HttpServletRequest req, HttpServletResponse resp) {
		//调用方法查询所有的分类
		CategoryService service = new CategoryService();
		List<Category> list = service.findAllCategory();
	//	new TranJson().getJson(req, resp, list);
		req.setAttribute("list", list);
		return "/mainface/buttom.jsp";	
	}
	
	public String findCategory(HttpServletRequest req, HttpServletResponse resp) {
		//调用方法查询所有的分类
		CategoryService service = new CategoryService();
		List<Category> list = service.findAllCategory();
	//	new TranJson().getJson(req, resp, list);
		req.setAttribute("list", list);
		return "/other/category.jsp";	
	}
	//后台管理员查询所有的分类
	public String findAlladminCategory(HttpServletRequest req, HttpServletResponse resp) {
		//调用方法查询所有的分类
		CategoryService service = new CategoryService();
		List<Category> list = service.findAllCategory();
		req.setAttribute("list", list);
		return "/adminjsps/admin/category/list.jsp";
	}
	
	//后台管理员增加分类
	public String addCategory(HttpServletRequest req, HttpServletResponse resp) {
		//得到提交的数据
		String cname = req.getParameter("cname");
		String uuid = UUID.randomUUID().toString();
		//封装实体
		Category category = new Category();
		category.setCid(uuid);
		category.setCname(cname);
		
		//调用方法实现增加
		CategoryService service = new CategoryService();
		service.addCategory(category);
		return "/category?method=findAlladminCategory";
	}
	//根据查询分类，把分类显示到页面上
	public String dispatcherCategory(HttpServletRequest req, HttpServletResponse resp) {
		//得到操作分类的id
		String cid = req.getParameter("cid");
		//根据id查询分类
		CategoryService service = new CategoryService();
		Category cate = service.findByCid(cid);
		req.setAttribute("cate", cate);
		return "/adminjsps/admin/category/mod.jsp";
	}
	
	//根据cid修改分类名称
	public String updateCategory(HttpServletRequest req, HttpServletResponse resp) {
		String cid = req.getParameter("cid");
		String cname = req.getParameter("cname");
		//封装实体
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		//调用方法实现
		CategoryService service = new CategoryService();
		service.updateCategory(category) ;
		return "/category?method=findAlladminCategory";
	}
	
	//根据id删除分类
	public String deleteCategory(HttpServletRequest req, HttpServletResponse resp) {
		//得到要删除分类的id
		String cid = req.getParameter("cid");
		//首先把id下面的图书，图书下面的id设置为空
		GoodsService bookService = new GoodsService();
		List<Goods> booklist = bookService.findGoodsByCid(cid);
		//根据这些图书的id，把这些图书的id值设置为null
		CategoryService service = new CategoryService();
		service.updateGoodsCid(booklist);
		//再进行分类的删除
		service.deleteCategoryByCid(cid);
		return "/category?method=findAlladminCategory";
	}
}





