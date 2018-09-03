package com.jsp.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.bean.Cart;
import com.jsp.bean.CartItem;
import com.jsp.bean.Order;
import com.jsp.bean.OrderItem;
import com.jsp.bean.User;
import com.jsp.service.OrderService;
import com.jsp.utils.TranJson;
/**
 * 实现订单模块操作的servlet
 * @author asus
 *
 */
public class OrderServlet extends BaseServlet {
	
	/**
	 * 生成订单
	 */
	public String addOrder(HttpServletRequest req, HttpServletResponse resp) {
		/*
		 * 最终的目的：是把订单的信息存到数据库表里面
		 * 
		 * 1、封装实体对象 order
		 * 	* oid：使用uuid封装
		 *  * ordertime：得到当前时间封装
		 *  * total：首先从session里面得到购物车，从购物车里面得到total
		 *  * state：使用默认值 1
		 *  * address：手动填写地址
		 *  * user：现在做生成订单操作，要在登录状态，从登录session里面获取user对象，完成封装
		 * 2、封装订单项 orderItem
		 *  * 首先得到order里面订单项使用list集合
		 *  * 遍历list集合，依次向订单项实体里面封装数据
		 *  * 把封装之后的订单项实体放到list里面
		 * 3、把这些数据存到数据库
		 * 4、返回显示当前订单的页面，同时显示订单的信息
		 * */
		//封装order对象
		Order order = new Order();
		order.setOid(UUID.randomUUID().toString());
		Date date = new Date();
		order.setOrdertime(date.toLocaleString());
		order.setState(1);//没有付款
		order.setAddress(null);
		//从session里面获取购物车里面的信息
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		order.setTotal(cart.getTotal());
		//从session里面获取user的值
		User user = (User) req.getSession().getAttribute("user");
		if(user == null) {
			req.setAttribute("msg", "请登录");
			return "/mainface/login.jsp";
		}else{
			order.setUser(user);
			//从购物车里面获取所有的购物项
			for(CartItem cartItem : cart.getCartItems()) {
				//每个订单项的封装
				OrderItem orderItem = new OrderItem();
				orderItem.setIid(UUID.randomUUID().toString());
				orderItem.setCount(cartItem.getCount());
				orderItem.setSubtotal(cartItem.getSubtotal());
				orderItem.setOrder(order);
				orderItem.setGoods(cartItem.getGoods());
				//得到订单里面的list集合
				order.getOrderItems().add(orderItem);
			}
			//把订单的信息存到数据库
			OrderService service = new OrderService();
			service.saveOrder(order);
			req.setAttribute("order", order);
			//返回订单的数据
			return "/other/order.jsp";
		}
		
	}

	//查询当前用户的所有的订单
	public String findAllOrder(HttpServletRequest req, HttpServletResponse resp) {
		/*
		 * 1、得到当前用户的id 从session里面获取当前用户的id
		 * 2、调用方法查询，返回list集合
		 * */
		User user = (User) req.getSession().getAttribute("user");
		if(user == null) {
			req.setAttribute("msg", "请登录");
			return "/mainface/login.jsp";
		}else{
			//得到用户的id
			String uid = user.getUid();
			OrderService service = new OrderService();
			List<Order> list = service.findOrdersByUid(uid);
			req.setAttribute("order", list);
			return "/other/myorder.jsp";
		}
		
	}
	
	//根据订单的id查询所有的订单项
	public String findOrderItemByOid(HttpServletRequest req, HttpServletResponse resp) {
		User user = (User) req.getSession().getAttribute("user");
		if(user == null) {
			req.setAttribute("msg", "请登录");
			return "/mainface/login.jsp";
		}else{
			//得到传递的订单的id
			String oid = req.getParameter("oid");
			//调用方法，根据id进行查询
			//MapListHandler：先把数据放到map里面，在把map放到list里面
			//List<Map<String,Object>>
			OrderService service = new OrderService();
			
			List<Order> list = service.findOrdersByUid(user.getUid());
			req.setAttribute("order", list);
			
			List<Map<String,Object>> orderitem = service.findOrderItemByOid(oid);
			req.setAttribute("orderitem", orderitem);
			return "/other/myorder.jsp";
		}	
	}
	
	//查询当前用户的所有的订单（封装订单项）
	public String findAllOrderTest(HttpServletRequest req, HttpServletResponse resp) {
		try {
			/*
			 * 1、得到当前用户的id 从session里面获取当前用户的id
			 * 2、调用方法查询，返回list集合
			 * */
			User user = (User) req.getSession().getAttribute("user");
			if(user == null) {
				req.setAttribute("msg", "请登录");
				return "/mainface/login.jsp";
			}else{
				//得到用户的id
				String uid = user.getUid();
				OrderService service = new OrderService();
				List<Order> list = service.findOrdersByUidTest(uid);
				req.setAttribute("order", list);
				return "/other/myorder.jsp";
			}
			
		}catch(Exception e) {
			return null;
		}

	}
	
	//后台管理员查询所有的订单
	public String findAllOrderAdmin(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//查询所有的订单 order
			//封装order对象（包含订单项和商品）
			OrderService service = new OrderService();
			List<Order> list = service.findAllOrder();
			req.setAttribute("order", list);
			return "/adminjsps/admin/order/list.jsp";
		}catch(Exception e) {
			return null;
		}
	}
	
	//根据订单的状态查询订单
	public String findOrderByState(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//得到传递的要查询的订单状态
			String s = req.getParameter("state");
			int state = Integer.parseInt(s);
			//调用方法，根据状态查询
			OrderService service = new OrderService();
			List<Order> list = service.findOrderByState(state);
			req.setAttribute("order", list);
			return "/adminjsps/admin/order/list.jsp";
		}catch(Exception e) {
			return null;
		}
	}
	
	//完成付款
	public void finish(HttpServletRequest req, HttpServletResponse resp){
		try{
			resp.setContentType("text/html;charset=utf-8");
			//得到传递过来的oid
			String oid = req.getParameter("oid");
			//根据oid修改状态为2
			OrderService service = new OrderService();
			service.setState(oid);
			resp.getWriter().print("您已经完成付款,页面将在3秒后进行跳转!");
			resp.setHeader("refresh", "3;url=http://localhost:8080/petstore/index.jsp"); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//删除订单
	/*
	 * 直接删除了
	 * 正确做法: 需要数据库设置另一个字段 判断是否删除
	 */
	public String deleteOrder(HttpServletRequest req, HttpServletResponse resp){
		String oid = req.getParameter("oid");
		//删除前需要把orderitem里的oid置空
		OrderService service = new OrderService();
		service.setOid(oid);
		service.deleteOrder(oid);
		return "/order?method=findAllOrder";
	}

}











