/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia03.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.MenuConsumible;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Pelicula;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Sucursal;

/**
 *
 * @author CF14010
 */
@WebServlet(name = "UtilitiesServlet", urlPatterns = {"/UtilitiesServlet"})
public class UtilitiesServlet extends HttpServlet {
    
    @Inject
    private UtilitiesFacade uf;
    List<Pelicula> p;
    List<MenuConsumible> mc;
    List<Sucursal> su;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UtilitiesServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("</br>");
            
            
           String btnClaGe = request.getParameter("btnClaGe");             
if (null == btnClaGe) {             
} else {                 
List<Pelicula> lista1 = new ArrayList<>();                 
try {                     
String clasificacion = request.getParameter("txtClasificacion");                     
String genero = request.getParameter("txtgenero");                     
lista1 = uf.tipoPelicula(clasificacion, genero);                     
out.println("<table border=\"1\">");                     
out.println(" <thead>");                     
out.println(" <tr>");                     
out.println("<th>Valores Lista</th>");                     
out.println("</tr>");                     
out.println(" </thead>");                     
out.println("  <tbody>");                     
for (int i = 0; i < lista1.size(); i++) {                         
out.println("\n");                         
out.println(" <tr>");                         
out.println(" <td>" + lista1.getClass() + "</td>");                          
out.println(" </tr>");                     
}                 
} catch (Exception ex) {                     
Logger.getLogger(UtilitiesServlet.class.getName()).log(Level.SEVERE, null, ex); 
System.out.println("error" + ex.getMessage());                 
}                  
out.println(" </tbody>");                 
out.println("  </table>");             
}             
            
            
            
            
            out.println("<h1>Servlet UtilitiesServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
