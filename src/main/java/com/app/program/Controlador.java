package com.app.program;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.model.Usuario;
import com.app.service.UsuarioService;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void proccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion=request.getParameter("accion");
		switch (accion) {
		case "RegistrarUser":
			int id=Integer.parseInt(request.getParameter("txtId"));
			String firstName=request.getParameter("txtNombres");
			String lastName=request.getParameter("txtApellidos");
			String email=request.getParameter("txtEmail");
			System.out.println(id);
			Usuario usuario=new Usuario(firstName, lastName, email);
			UsuarioService service=new UsuarioService();
			int r=0;
			try {
				r=service.add(usuario);
			} catch (Exception e) {
				e.getMessage();
			}			
			request.setAttribute("saludo", r==1?"Se Registró con éxito!":"Error al Registrar");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		case "RegistrarCancion":
			int idc=Integer.parseInt(request.getParameter("txtId"));
			String nombre=request.getParameter("txtNombre");
			String banda=request.getParameter("txtbanda");
			String album=request.getParameter("txtalbum");
			int res=0;
			request.setAttribute("saludo", res==1?"Se Registró con éxito!":"Error al Registrar");
			request.getRequestDispatcher("view/Canciones.jsp").forward(request, response);
			break;
		case "RegistrarPlayList":
			int idp=Integer.parseInt(request.getParameter("txtId"));
			String nombrePlay=request.getParameter("txtNombre");
			int re=0;
			request.setAttribute("saludo", re==1?"Se Registró con éxito!":"Error al Registrar");
			request.getRequestDispatcher("view/Canciones.jsp").forward(request, response);
			break;	

		default:
			break;
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccessRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccessRequest(request, response);
	}

}
