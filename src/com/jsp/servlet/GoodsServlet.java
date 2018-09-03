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
 * ��Ʒģ�������servlet
 * @author asus
 *
 */
public class GoodsServlet extends BaseServlet {
	
	//��̨�޸���Ʒ�Ĺ���
	public String mod(HttpServletRequest req, HttpServletResponse resp) {
		//����������ݷ�װ��javabean����
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, req.getParameterMap());
			//�ֶ��Ѽ۸��װ��ȥ����Ϊ��������ļ۸���double
			double price = Double.parseDouble(req.getParameter("price"));
			goods.setPrice(price);
			//���÷�������Ʒ�浽���ݿ�
			GoodsService service = new GoodsService();
			service.updateGoods(goods);
			//��������Ʒ��ҳ��
			return "/goods?method=findAlladmin";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//��̨ɾ����Ʒ�Ĺ���
	public String del(HttpServletRequest req, HttpServletResponse resp) {
		//��ȡ��Ʒ��bid
		String gid = req.getParameter("gid");
		//���÷���ʵ��ɾ��
		GoodsService service = new GoodsService();
		service.delGoods(gid);
		//��������Ʒ��ҳ��
		return "/goods?method=findAlladmin";
	}
	
	//��ת����ʾ��Ʒ�����ҳ��
	public String findGoodsAdmin(HttpServletRequest request, HttpServletResponse response) {
		//�õ���Ʒ��id
		String gid = request.getParameter("gid");
		GoodsService service = new GoodsService();
		Goods goods = service.findById(gid);
		//��ѯ���еķ���
		CategoryService categoryservice = new CategoryService();
		List<Category> list = categoryservice.findAllCategory();
		request.setAttribute("category", list);
		request.setAttribute("goods", goods);
		return "/adminjsps/admin/goods/desc.jsp";
	}
	
	//��̨����Ա��ѯ���е���Ʒ
	public String findAlladmin(HttpServletRequest request, HttpServletResponse response) {
		//����service����ķ���ʵ�ֲ�ѯ
		GoodsService service = new GoodsService();
		List<Goods> list = service.findAllGoods();
		request.setAttribute("list", list);
		return "/adminjsps/admin/goods/list.jsp";
	}

	//��ת��������Ʒ��ҳ��
	public String disAddGoods(HttpServletRequest request, HttpServletResponse response) {
		//���÷�����ѯ���еķ���
		CategoryService service = new CategoryService();
		List<Category> list = service.findAllCategory();
		//�ѷ��ഫ�ݵ����ӵ�ҳ��
		request.setAttribute("list", list);
		return "/adminjsps/admin/goods/add.jsp";
	}
	
	/**
	 * ��ѯ���е���Ʒ
	 */
	public String findAllGoods(HttpServletRequest request, HttpServletResponse response){
		//����service����ķ���ʵ�ֲ�ѯ
		GoodsService service = new GoodsService();
		List<Goods> list = service.findAllGoods();
		request.setAttribute("list2", list);
		return "/mainface/buttom.jsp";
	}

	//����cid��ѯ��Ʒ
	public String findGoodsByCid(HttpServletRequest request, HttpServletResponse response) {
		
		/* * 1����ȡ���ݵķ����id 
		 * 2�����÷���ʵ�ַ���Ĳ�ѯ
		 * */
		String cid = request.getParameter("cid");
		GoodsService service = new GoodsService();
		List<Goods> list = service.findGoodsByCid(cid);
		request.setAttribute("list2", list);
		return "/other/goods.jsp";
	}
	public String findGoodsByCid1(HttpServletRequest request, HttpServletResponse response) {
		
		/* * 1����ȡ���ݵķ����id 
		 * 2�����÷���ʵ�ַ���Ĳ�ѯ
		 * */
		String cid = request.getParameter("cid");
		/*GoodsService service = new GoodsService();
		List<Goods> list = service.findGoodsByCid(cid);*/
		request.setAttribute("cid", cid);
		return "/mainface/middle.jsp";
	}
	
	//��ѯĳ����Ʒ����Ϣ
	public String findGoods(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1����ȡҪ��ѯ����Ʒ��id
		 * 2�����÷�����ѯ��Ʒ��Ϣ
		 * */
		String gid = request.getParameter("gid");
		GoodsService service = new GoodsService();
		Goods goods = service.findById(gid);
		request.setAttribute("goods", goods);
		return "/other/goodsinfo.jsp";
	}
	//ʵ����������
	public String findBySimple(HttpServletRequest req, HttpServletResponse resp) {
		//�õ����ݹ�����name
		String name = req.getParameter("name");
		GoodsService service = new GoodsService();
		List<Goods> list = service.findBySimpleName(name);
		req.setAttribute("list", list);
		return "null";
	}
	//����Ƽ���Ʒ
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













