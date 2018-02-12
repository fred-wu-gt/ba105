package filters;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import com.manager.model.ManagerVO;

public class BE_LoginFilter implements Filter {
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
		Object loginManagerVO = session.getAttribute("loginManagerVO");
		if (loginManagerVO == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/login/be_login.jsp");
		} else {
				chain.doFilter(request, response);

		}
	}
}