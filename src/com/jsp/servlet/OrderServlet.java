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
 * ʵ�ֶ���ģ�������servlet
 * @author asus
 *
 */
public class OrderServlet extends BaseServlet {
	
	/**
	 * ���ɶ���
	 */
	public String addOrder(HttpServletRequest req, HttpServletResponse resp) {
		/*
		 * ���յ�Ŀ�ģ��ǰѶ�������Ϣ�浽���ݿ������
		 * 
		 * 1����װʵ����� order
		 * 	* oid��ʹ��uuid��װ
		 *  * ordertime���õ���ǰʱ���װ
		 *  * total�����ȴ�session����õ����ﳵ���ӹ��ﳵ����õ�total
		 *  * state��ʹ��Ĭ��ֵ 1
		 *  * address���ֶ���д��ַ
		 *  * user�����������ɶ���������Ҫ�ڵ�¼״̬���ӵ�¼session�����ȡuser������ɷ�װ
		 * 2����װ������ orderItem
		 *  * ���ȵõ�order���涩����ʹ��list����
		 *  * ����list���ϣ������򶩵���ʵ�������װ����
		 *  * �ѷ�װ֮��Ķ�����ʵ��ŵ�list����
		 * 3������Щ���ݴ浽���ݿ�
		 * 4��������ʾ��ǰ������ҳ�棬ͬʱ��ʾ��������Ϣ
		 * */
		//��װorder����
		Order order = new Order();
		order.setOid(UUID.randomUUID().toString());
		Date date = new Date();
		order.setOrdertime(date.toLocaleString());
		order.setState(1);//û�и���
		order.setAddress(null);
		//��session�����ȡ���ﳵ�������Ϣ
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		order.setTotal(cart.getTotal());
		//��session�����ȡuser��ֵ
		User user = (User) req.getSession().getAttribute("user");
		if(user == null) {
			req.setAttribute("msg", "���¼");
			return "/mainface/login.jsp";
		}else{
			order.setUser(user);
			//�ӹ��ﳵ�����ȡ���еĹ�����
			for(CartItem cartItem : cart.getCartItems()) {
				//ÿ��������ķ�װ
				OrderItem orderItem = new OrderItem();
				orderItem.setIid(UUID.randomUUID().toString());
				orderItem.setCount(cartItem.getCount());
				orderItem.setSubtotal(cartItem.getSubtotal());
				orderItem.setOrder(order);
				orderItem.setGoods(cartItem.getGoods());
				//�õ����������list����
				order.getOrderItems().add(orderItem);
			}
			//�Ѷ�������Ϣ�浽���ݿ�
			OrderService service = new OrderService();
			service.saveOrder(order);
			req.setAttribute("order", order);
			//���ض���������
			return "/other/order.jsp";
		}
		
	}

	//��ѯ��ǰ�û������еĶ���
	public String findAllOrder(HttpServletRequest req, HttpServletResponse resp) {
		/*
		 * 1���õ���ǰ�û���id ��session�����ȡ��ǰ�û���id
		 * 2�����÷�����ѯ������list����
		 * */
		User user = (User) req.getSession().getAttribute("user");
		if(user == null) {
			req.setAttribute("msg", "���¼");
			return "/mainface/login.jsp";
		}else{
			//�õ��û���id
			String uid = user.getUid();
			OrderService service = new OrderService();
			List<Order> list = service.findOrdersByUid(uid);
			req.setAttribute("order", list);
			return "/other/myorder.jsp";
		}
		
	}
	
	//���ݶ�����id��ѯ���еĶ�����
	public String findOrderItemByOid(HttpServletRequest req, HttpServletResponse resp) {
		User user = (User) req.getSession().getAttribute("user");
		if(user == null) {
			req.setAttribute("msg", "���¼");
			return "/mainface/login.jsp";
		}else{
			//�õ����ݵĶ�����id
			String oid = req.getParameter("oid");
			//���÷���������id���в�ѯ
			//MapListHandler���Ȱ����ݷŵ�map���棬�ڰ�map�ŵ�list����
			//List<Map<String,Object>>
			OrderService service = new OrderService();
			
			List<Order> list = service.findOrdersByUid(user.getUid());
			req.setAttribute("order", list);
			
			List<Map<String,Object>> orderitem = service.findOrderItemByOid(oid);
			req.setAttribute("orderitem", orderitem);
			return "/other/myorder.jsp";
		}	
	}
	
	//��ѯ��ǰ�û������еĶ�������װ�����
	public String findAllOrderTest(HttpServletRequest req, HttpServletResponse resp) {
		try {
			/*
			 * 1���õ���ǰ�û���id ��session�����ȡ��ǰ�û���id
			 * 2�����÷�����ѯ������list����
			 * */
			User user = (User) req.getSession().getAttribute("user");
			if(user == null) {
				req.setAttribute("msg", "���¼");
				return "/mainface/login.jsp";
			}else{
				//�õ��û���id
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
	
	//��̨����Ա��ѯ���еĶ���
	public String findAllOrderAdmin(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//��ѯ���еĶ��� order
			//��װorder���󣨰������������Ʒ��
			OrderService service = new OrderService();
			List<Order> list = service.findAllOrder();
			req.setAttribute("order", list);
			return "/adminjsps/admin/order/list.jsp";
		}catch(Exception e) {
			return null;
		}
	}
	
	//���ݶ�����״̬��ѯ����
	public String findOrderByState(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//�õ����ݵ�Ҫ��ѯ�Ķ���״̬
			String s = req.getParameter("state");
			int state = Integer.parseInt(s);
			//���÷���������״̬��ѯ
			OrderService service = new OrderService();
			List<Order> list = service.findOrderByState(state);
			req.setAttribute("order", list);
			return "/adminjsps/admin/order/list.jsp";
		}catch(Exception e) {
			return null;
		}
	}
	
	//��ɸ���
	public void finish(HttpServletRequest req, HttpServletResponse resp){
		try{
			resp.setContentType("text/html;charset=utf-8");
			//�õ����ݹ�����oid
			String oid = req.getParameter("oid");
			//����oid�޸�״̬Ϊ2
			OrderService service = new OrderService();
			service.setState(oid);
			resp.getWriter().print("���Ѿ���ɸ���,ҳ�潫��3��������ת!");
			resp.setHeader("refresh", "3;url=http://localhost:8080/petstore/index.jsp"); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//ɾ������
	/*
	 * ֱ��ɾ����
	 * ��ȷ����: ��Ҫ���ݿ�������һ���ֶ� �ж��Ƿ�ɾ��
	 */
	public String deleteOrder(HttpServletRequest req, HttpServletResponse resp){
		String oid = req.getParameter("oid");
		//ɾ��ǰ��Ҫ��orderitem���oid�ÿ�
		OrderService service = new OrderService();
		service.setOid(oid);
		service.deleteOrder(oid);
		return "/order?method=findAllOrder";
	}

}











