package com.jsp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.bean.Goods;
import com.jsp.bean.Cart;
import com.jsp.bean.CartItem;
import com.jsp.service.GoodsService;
import com.jsp.utils.TranJson;
/**
 * 购物车功能的实现servlet
 * @author asus
 *
 */
public class CartServlet extends BaseServlet {

	//从session里面获取购物车
	public Cart getCart(HttpServletRequest req) {
		//从session里面查看是否有购物车信息 cart
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if(cart == null) {
			//创建购物车
			cart = new Cart();
			req.getSession().setAttribute("cart", cart);
		} 
		return cart;
	}
	
	/**
	 * 添加购物项到购物车
	 */
	public String addCart(HttpServletRequest req, HttpServletResponse resp){
		/*
		 * 1、获取添加商品的id和数量
		 * 2、封装实体，首先封装购物项 CartItem
		 * 3、封装购物车的实体 Cart
		 * 	* 从session获取购物车
		 * 4、调用购物车实体里面的方法实现添加
		 * 
		 * */
		String gid = req.getParameter("gid");
		String con = req.getParameter("count");
		int count = 0;
		if(con != null) {
			count = Integer.parseInt(con);
		}
		//封装购物项的实体
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		if(gid!=null&&con!=null){
			//根据id查询商品
			GoodsService service = new GoodsService();
			Goods goods = service.findById(gid);
			if(goods == null) {
				return "/index.jsp";
			}else{
				cartItem.setGoods(goods);
				//封装购物车
				/*
				 * 首先从session里面获取购物车
				 * 如果没有创建购物车
				 * */
				Cart cart = getCart(req);
				cart.addCart(cartItem);
				return "/other/cart.jsp";
			}
		}
		return null;
	}
	
	//根据商品的id删除购物项
	public String removeCart(HttpServletRequest req, HttpServletResponse resp) {
		/*
		 * 1、得到商品的id
		 * 2、从session里面得到购物车
		 * 3、调用方法实现删除操作
		 * */
		String gid = req.getParameter("gid");
		if(gid!=null){
			Cart cart = getCart(req);
			//调用方法实现删除操作
			cart.removeCart(gid);
			return "/other/cart.jsp";
		}
		return null;	
	}
	//实现清空购物车的功能
	public String clearCart(HttpServletRequest req, HttpServletResponse resp) {
		//得到session里面的购物车
		Cart cart = getCart(req);
		cart.clearCart();
		return "/other/cart.jsp";
	}
	
}











