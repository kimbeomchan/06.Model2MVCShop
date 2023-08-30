package com.model2.mvc.view.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;


import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class GetProductAction extends Action{
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		//HttpSession session = null;
		
		//List<Object> productList = new ArrayList<Object>();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		Cookie[] cookies = request.getCookies();
		
		String history = null;
		
		if(cookies != null) { // 쿠키에 값이 있을 때
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				
				if (cookie.getName().equals("history")) {
					history = cookie.getValue();
					System.out.println("history : " + history);
				}
			}
		}
		
		String tmp = request.getParameter("prodNo");
		System.out.println("tmp : " + tmp);
		
		
		Cookie cookie = new Cookie("history", history + "/" + request.getParameter("prodNo"));
		System.out.println("cookie : " + cookie);
		
		response.addCookie(cookie);
		
//		if(request.getSession() == null) {
//			session = request.getSession();
//		}
		
		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(prodNo);
		
		
//		productList.add(product);
//		
//		session.setAttribute("productList", productList);
		
		request.setAttribute("vo", product);
		
		return "forward:/product/getProduct.jsp";
	}

}
