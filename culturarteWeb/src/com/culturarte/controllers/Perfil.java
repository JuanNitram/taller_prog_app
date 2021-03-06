package com.culturarte.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.culturarte.model.EstadoSesion;

/**
 * Servlet implementation class Perfil
 */
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Perfil() {
        super();
    }

	/** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
  
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		servidor.DtUsuario user;
		servidor.PublicadorService service =  new servidor.PublicadorService();
		servidor.Publicador port = service.getPublicadorPort();
		
		try {
			if(request.getSession().getAttribute("estado_sesion") != EstadoSesion.LOGIN_CORRECTO) {
				request.setAttribute("excepcion", true);
				request.setAttribute("excepcionTitulo", "Perfil");
				request.setAttribute("excepcionMensaje", "Inicia sesión para acceder al perfil.");	
				request.getRequestDispatcher("/home").forward(request, response);
			} else {
				if (!port.esProponente((String) request.getSession().getAttribute("usuario_logueado")))
					user = (servidor.DtUsuario) port.infoColaborador((String) request.getSession().getAttribute("usuario_logueado"));
				else 
					user = (servidor.DtUsuario) port.infoProponente((String) request.getSession().getAttribute("usuario_logueado"));
				request.setAttribute("usr", user);
				request.getRequestDispatcher("/WEB-INF/usuarios/perfil.jsp").forward(request, response);
			}
		} catch (Exception ex){
			// no existe el usuario, se trata como deslogueado
			request.getSession().setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
			request.getRequestDispatcher("/home").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
