package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UsuarioBean;
import dao.UsuarioDAO;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String acao = request.getParameter("acao");
		String usuario = request.getParameter("usuario");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (acao.equalsIgnoreCase("deletar")) {
			
			usuarioDAO.deletarUsuario(usuario);
			
		} else if (acao.equalsIgnoreCase("editar")) {
			
			UsuarioBean usuarioBean = usuarioDAO.buscarUsuario(Long.parseLong(id));
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuario", usuarioBean);
			dispatcher.forward(request, response);
			
		}
		
		try {
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuarios", usuarioDAO.listarUsuarios());
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.setId(!id.isEmpty() ? Long.parseLong(id) : 0L);
		usuarioBean.setUsuario(usuario);
		usuarioBean.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (id == null || id.isEmpty()) {
			
			usuarioDAO.salvarUsuario(usuarioBean);
			
			
		} else {
			
			usuarioDAO.atualizar(usuarioBean);
			
		}
		
		try {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuarios", usuarioDAO.listarUsuarios());
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
