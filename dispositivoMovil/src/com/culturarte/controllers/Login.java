package com.culturarte.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.culturarte.model.EstadoSesion;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador port = service.getPublicadorPort();
    	HttpSession objSesion = request.getSession();
    	
    	if(request.getParameter("action").equals("iniciar")) {
	    	String login = request.getParameter("login");
	    	String password = request.getParameter("password");
	    	EstadoSesion nuevoEstado;

	    	// chequea contrase�a
	    	try {
	    		servidor.DataList DtC = port.listarColaboradores();
	    		List<servidor.DtColaborador> colaboradores = (ArrayList) DtC.getDatos();	
	    		int index =0;
	    		while (index < colaboradores.size()
	    				&& !colaboradores.get(index).getNickName().equals(login)
	    				&& !colaboradores.get(index).getEmail().equals(login))
	    			index++;
	
	    		if (index < colaboradores.size()){
	    			servidor.DtColaborador user = colaboradores.get(index);
	    			if (port.checkPassword(login, password)) {
	    				nuevoEstado = EstadoSesion.LOGIN_CORRECTO;
	    				request.getSession().setAttribute("usuario_logueado", user.getNickName());
	    			} else {
	    				nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
	    				System.out.println("Contraseña incorrecta");
	    			}
	
	    		} else {
	    			nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
	    			System.out.println("Usuario incorrecto");
	    		}
	    	} catch(Exception ex) {
	    		nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
	    		System.out.println("Estoy en la exception");
	    	}
	
	    	objSesion.setAttribute("estado_sesion", nuevoEstado);
    	} else if(request.getParameter("action").equals("cerrar")) {
    		objSesion.setAttribute("usuario_logueado", null);
   		 	objSesion.setAttribute("estado_sesion", null);
    	}

    	// redirige a la p�gina principal para que luego rediriga a la p�gina
    	// que corresponde
    	request.getRequestDispatcher("/home").forward(request, response);
    } 
    
	/**
	 * Devuelve el usuario logueado
	 * @param request
	 * @return
	 * @throws UsuarioNoEncontrado 
	 */
	static public servidor.DtColaborador getUsuarioLogueado(HttpServletRequest request){
		
		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador port = service.getPublicadorPort();
		servidor.DtColaborador usuario = null;
		
		try{
			if((String)request.getSession().getAttribute("usuario_logueado") != null)
				usuario = port.infoColaborador((String)request.getSession().getAttribute("usuario_logueado"));
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return usuario;
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
