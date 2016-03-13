/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.dao.UsuarioJpaController;

@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
   
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
        
        RequestDispatcher rd;
        
        //Pega os parametros passados para fazer MVC
        String titulo = request.getParameter("titulo");
        String pagina = request.getParameter("page");
        
        //Se o param pagina for nulo e não for login
        //Ele quer ir para a primeira pagina e não fazer o login
        if ((pagina==null || !pagina.equals("login"))) {
            //pega o dispatcher e faz o forward
            rd = request.getRequestDispatcher( "_layout.jsp");
            
            //Carrega os parametros no forward para saber no layout qual a próxima página
            request.setAttribute("page", pagina);
        } else {
            //Se pagina for login ele pega o campo digitado no formulário de usuario e preenche
            Usuario usuario = new Usuario(request);
            
            //Cria uma conexao e tenta achar o usuario digitado na tabela
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
            Usuario usuarioEncontrado = new UsuarioJpaController(emf).findUsuario(usuario.getLogin());
           
            //Se não achar nada ele é nulo então já sai
            //Se achar, verifica se o login e senha realmente são como o digitado
            if ( usuarioEncontrado != null &&
                    (usuarioEncontrado.getSenha().equals(usuario.getSenha()))
                    &&
                (usuarioEncontrado.getLogin().equals(usuario.getLogin()))
                    ) {
                //Então redireciona para dentro do sistema
                rd = request.getRequestDispatcher( "/sistema");
            } else {
                //Se não achar, volta a pagina de login
                rd = request.getRequestDispatcher( "_layout.jsp");
                
                //Aqui, null é passado para forçar a página ser a página inicial de login
                //Ainda não funcionando
                request.setAttribute("page", null);
            }
        }        
        
        //Coloca o título padrão
        request.setAttribute("titulo", "eoqqqqq");
        
        //No final, redireciona
        rd.forward(request, response);
        
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
