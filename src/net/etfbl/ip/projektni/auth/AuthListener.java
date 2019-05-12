package net.etfbl.ip.projektni.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthListener implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginURI = request.getContextPath() + "/login.xhtml";
		String usersURI = request.getContextPath() + "/pages/users.xhtml";
		String objaveURI = request.getContextPath() + "/pages/objave.xhtml";
		String dogadjaURI = request.getContextPath() + "/pages/dogadjaji.xhtml";
		String vijestURI = request.getContextPath() + "/pages/vijesti.xhtml";
		String podaciURI = request.getContextPath() + "/pages/podaci.xhtml";


		if (loginURI.equals(request.getRequestURI().toString()) || dogadjaURI.equals(request.getRequestURI().toString())
				|| usersURI.equals(request.getRequestURI().toString()) || vijestURI.equals(request.getRequestURI().toString())
				|| objaveURI.equals(request.getRequestURI().toString()) || podaciURI.equals(request.getRequestURI().toString())
				|| (session!=null && session.getAttribute("user") != null)) {
			chain.doFilter(request, response);
		}  else {
			response.sendRedirect(loginURI);
		}

	}

}
