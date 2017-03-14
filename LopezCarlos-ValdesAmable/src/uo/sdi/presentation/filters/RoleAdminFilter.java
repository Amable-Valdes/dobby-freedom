package uo.sdi.presentation.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.dto.UserDTO;

/**
 * Servlet Filter implementation class RoleAdminFilter
 */
@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST }
					, 
		description = "Filtro que comprueba que el usuario tenga rol de administrador", 
		urlPatterns = { "/restricted/admin/*" }, 
		initParams = { 
				@WebInitParam(
						name = "returnPage", 
						value = "/index.xhtml", 
						description = "Página de retorno genérica"),
				@WebInitParam(
						name = "returnPageUser", 
						value = "/restricted/user/usuario.xhtml", 
						description = "Página de retorno para usuarios con "
								+ "rol usuario")
		})
public class RoleAdminFilter implements Filter {
	
	private FilterConfig config = null;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		// Comprobamos si la petición es Http
		if (!(request instanceof HttpServletRequest)){
			chain.doFilter(request, response);
			return;
		}
		// Verificamos que se ha hecho login exitoso
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if (session.getAttribute("LOGGEDIN_USER") == null) {
			String loginForm = config.getInitParameter("returnPage");
			//Redireccionamos si no es un usuario logueado
			res.sendRedirect(req.getContextPath() + loginForm);
			return;
		}else{
			if(!((UserDTO)session.getAttribute("LOGGEDIN_USER")).getIsAdmin()){
				String loginForm = config.getInitParameter("returnPageUser");
				res.sendRedirect(req.getContextPath() + loginForm);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		config = fConfig;
	}

    /**
     * Default constructor. 
     */
    public RoleAdminFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

}
