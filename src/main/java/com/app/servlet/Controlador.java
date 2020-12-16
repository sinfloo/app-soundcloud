package com.app.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.model.HibernateUtil;
import com.app.model.Usuario;

/**
 * Servlet implementation class Controlador
 */
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Controlador() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unused")
	protected void proccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opc");		
		switch (opc) {
		case "1":
			Transaction transaction = null;
			List<Usuario> students = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				students = session.createQuery("from Usuario", Usuario.class).list();
				students.forEach(s -> System.out.println(s.getFirstName()));
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			request.setAttribute("usuarios", students);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		case "2":
			Transaction transaction2 = null;
			int id = Integer.parseInt(request.getParameter("txtId"));
			String firstName = request.getParameter("txtNombres");
			String lastName = request.getParameter("txtApellidos");
			String email = request.getParameter("txtEmail");
			System.out.println(id);
			Usuario usuario = new Usuario(firstName, lastName, email);
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				transaction2 = session.beginTransaction();
				// save the student objects
				session.save(usuario);
				// commit transaction
				transaction2.commit();
			} catch (Exception e) {
				if (transaction2 != null) {
					transaction2.rollback();
				}
				e.printStackTrace();
			}
			request.getRequestDispatcher("Controlador?opc=1").forward(request, response);
			break;

		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		proccess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		proccess(request, response);
	}

}
