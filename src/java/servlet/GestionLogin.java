/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.UsuarioController;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import modelo.usuario;
/**
 *
 * @author Josue Emmanuel Medina Garcia
 */
public class GestionLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("opc").equals("1")) {
            login(request, response);
        }
        if (request.getParameter("opc").equals("2")) {
            cerrarSesion(request, response);
        }
    }
    
    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        String dni =  request.getParameter("dni");
        String pass = request.getParameter("contrasena");
        UsuarioController uc = new UsuarioController();
        usuario u = null;
        String pagina = "";
      
    
        if (ses.getAttribute("tipo") != null) {
            pagina = "../GoClass/vista/Tienda.jsp";
        }
        else { 
            if (dni.equals("") || pass.equals("")){
                pagina = "../GoClass/vista/Login.jsp";
            }
            else if (uc.userVerify(dni, pass)) {
                u = uc.getUser(dni);
                ses.setAttribute("idUsuario", u.getId_usuario());
                ses.setAttribute("nombre", u.getNombre_us());
                ses.setAttribute("dni", u.getDni_us());
                ses.setAttribute("tipo", u.getId_tipo_us());
                pagina = "../GoClass/vista/Tienda.jsp";
                
            }
            else {
                pagina = "../GoClass/vista/Login.jsp";
            }
        }
        response.sendRedirect(pagina);   
    }
    
    protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession();
        ses.invalidate();
        response.sendRedirect("../GoClass/vista/Home.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}