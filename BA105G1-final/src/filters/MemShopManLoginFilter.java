package filters;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.http.*;
//一般會員 商家 管理員都可進入的頁面適用此濾器
public class MemShopManLoginFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object loginMemberVO = session.getAttribute("loginMemberVO");
		Object loginShopVO = session.getAttribute("loginShopVO");
		Object loginManagerVO = session.getAttribute("loginManagerVO");
		String url="/front-end/index.jsp";
		if (loginMemberVO == null && loginShopVO == null && loginManagerVO == null ) {
			res.sendRedirect(req.getContextPath() +url );//重導到首頁
			
		} else {
				chain.doFilter(request, response);//已登入則繼續前往該連結
		}
	}
}