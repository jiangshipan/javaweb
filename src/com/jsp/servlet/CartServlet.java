package com.jsp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.bean.Goods;
import com.jsp.bean.Cart;
import com.jsp.bean.CartItem;
import com.jsp.service.GoodsService;
import com.jsp.utils.TranJson;
/**
 * ���ﳵ���ܵ�ʵ��servlet
 * @author asus
 *
 */
public class CartServlet extends BaseServlet {

	//��session�����ȡ���ﳵ
	public Cart getCart(HttpServletRequest req) {
		//��session����鿴�Ƿ��й��ﳵ��Ϣ cart
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if(cart == null) {
			//�������ﳵ
			cart = new Cart();
			req.getSession().setAttribute("cart", cart);
		} 
		return cart;
	}
	
	/**
	 * ��ӹ�������ﳵ
	 */
	public String addCart(HttpServletRequest req, HttpServletResponse resp){
		/*
		 * 1����ȡ�����Ʒ��id������
		 * 2����װʵ�壬���ȷ�װ������ CartItem
		 * 3����װ���ﳵ��ʵ�� Cart
		 * 	* ��session��ȡ���ﳵ
		 * 4�����ù��ﳵʵ������ķ���ʵ�����
		 * 
		 * */
		String gid = req.getParameter("gid");
		String con = req.getParameter("count");
		int count = 0;
		if(con != null) {
			count = Integer.parseInt(con);
		}
		//��װ�������ʵ��
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		if(gid!=null&&con!=null){
			//����id��ѯ��Ʒ
			GoodsService service = new GoodsService();
			Goods goods = service.findById(gid);
			if(goods == null) {
				return "/index.jsp";
			}else{
				cartItem.setGoods(goods);
				//��װ���ﳵ
				/*
				 * ���ȴ�session�����ȡ���ﳵ
				 * ���û�д������ﳵ
				 * */
				Cart cart = getCart(req);
				cart.addCart(cartItem);
				return "/other/cart.jsp";
			}
		}
		return null;
	}
	
	//������Ʒ��idɾ��������
	public String removeCart(HttpServletRequest req, HttpServletResponse resp) {
		/*
		 * 1���õ���Ʒ��id
		 * 2����session����õ����ﳵ
		 * 3�����÷���ʵ��ɾ������
		 * */
		String gid = req.getParameter("gid");
		if(gid!=null){
			Cart cart = getCart(req);
			//���÷���ʵ��ɾ������
			cart.removeCart(gid);
			return "/other/cart.jsp";
		}
		return null;	
	}
	//ʵ����չ��ﳵ�Ĺ���
	public String clearCart(HttpServletRequest req, HttpServletResponse resp) {
		//�õ�session����Ĺ��ﳵ
		Cart cart = getCart(req);
		cart.clearCart();
		return "/other/cart.jsp";
	}
	
}











