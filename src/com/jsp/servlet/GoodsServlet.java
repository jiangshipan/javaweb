package com.jsp.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.jsp.bean.Goods;
import com.jsp.bean.Category;
import com.jsp.bean.OrderItem;
import com.jsp.service.GoodsService;
import com.jsp.service.CategoryService;
import com.jsp.service.OrderService;
import com.jsp.utils.TranJson;
/**
 * 商品模块操作的servlet
 * @author asus
 *
 */
public class GoodsServlet extends BaseServlet {
	
	//后台修改商品的功能
	public String mod(HttpServletRequest req, HttpServletResponse resp) {
		//把输入的数据封装到javabean里面
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, req.getParameterMap());
			//手动把价格封装进去，因为对象里面的价格是double
			double price = Double.parseDouble(req.getParameter("price"));
			goods.setPrice(price);
			//调用方法把商品存到数据库
			GoodsService service = new GoodsService();
			service.updateGoods(goods);
			//到所有商品的页面
			return "/goods?method=findAlladmin";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//后台删除商品的功能
	public String del(HttpServletRequest req, HttpServletResponse resp) {
		//获取商品的bid
		String gid = req.getParameter("gid");
		//调用方法实现删除
		GoodsService service = new GoodsService();
		service.delGoods(gid);
		//到所有商品的页面
		return "/goods?method=findAlladmin";
	}
	
	//跳转到显示商品详情的页面
	public String findGoodsAdmin(HttpServletRequest request, HttpServletResponse response) {
		//得到商品的id
		String gid = request.getParameter("gid");
		GoodsService service = new GoodsService();
		Goods goods = service.findById(gid);
		//查询所有的分类
		CategoryService categoryservice = new CategoryService();
		List<Category> list = categoryservice.findAllCategory();
		request.setAttribute("category", list);
		request.setAttribute("goods", goods);
		return "/adminjsps/admin/goods/desc.jsp";
	}
	
	//后台管理员查询所有的商品
	public String findAlladmin(HttpServletRequest request, HttpServletResponse response) {
		//调用service里面的方法实现查询
		GoodsService service = new GoodsService();
		List<Goods> list = service.findAllGoods();
		request.setAttribute("list", list);
		return "/adminjsps/admin/goods/list.jsp";
	}

	//跳转到增加商品的页面
	public String disAddGoods(HttpServletRequest request, HttpServletResponse response) {
		//调用方法查询所有的分类
		CategoryService service = new CategoryService();
		List<Category> list = service.findAllCategory();
		//把分类传递到增加的页面
		request.setAttribute("list", list);
		return "/adminjsps/admin/goods/add.jsp";
	}
	
	/**
	 * 查询所有的商品
	 */
	public String findAllGoods(HttpServletRequest request, HttpServletResponse response){
		//调用service里面的方法实现查询
		GoodsService service = new GoodsService();
		List<Goods> list = service.findAllGoods();
		request.setAttribute("list2", list);
		return "/mainface/buttom.jsp";
	}

	//根据cid查询商品
	public String findGoodsByCid(HttpServletRequest request, HttpServletResponse response) {
		
		/* * 1、获取传递的分类的id 
		 * 2、调用方法实现分类的查询
		 * */
		String cid = request.getParameter("cid");
		GoodsService service = new GoodsService();
		List<Goods> list = service.findGoodsByCid(cid);
		request.setAttribute("list2", list);
		return "/other/goods.jsp";
	}
	public String findGoodsByCid1(HttpServletRequest request, HttpServletResponse response) {
		
		/* * 1、获取传递的分类的id 
		 * 2、调用方法实现分类的查询
		 * */
		String cid = request.getParameter("cid");
		/*GoodsService service = new GoodsService();
		List<Goods> list = service.findGoodsByCid(cid);*/
		request.setAttribute("cid", cid);
		return "/mainface/middle.jsp";
	}
	
	//查询某个商品的信息
	public String findGoods(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1、获取要查询的商品的id
		 * 2、调用方法查询商品信息
		 * */
		String gid = request.getParameter("gid");
		GoodsService service = new GoodsService();
		Goods goods = service.findById(gid);
		request.setAttribute("goods", goods);
		return "/other/goodsinfo.jsp";
	}
	//实现搜索功能
	public String findBySimple(HttpServletRequest req, HttpServletResponse resp) {
		//得到传递过来的name
		String name = req.getParameter("name");
		GoodsService service = new GoodsService();
		List<Goods> list = service.findBySimpleName(name);
		req.setAttribute("list", list);
		return "null";
	}
	//随机推荐商品
	public String findByRand(HttpServletRequest req, HttpServletResponse resp) {
		GoodsService service = new GoodsService();
		List<Goods> list = service.findByRand();
		req.setAttribute("list", list);
		return "/other/hotgoods.jsp";
	}
	public String dispatcher(HttpServletRequest req, HttpServletResponse resp) {
		String cid = req.getParameter("cid");		
		return "/goods?method=findGoodsByCid&cid="+cid;
	}

}













