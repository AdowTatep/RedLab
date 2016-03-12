/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import model.Usuario;
import model.dao.PessoaJpaController;
import model.dao.UsuarioJpaController;

/**
 *
 * @author adowt
 */
@WebServlet(name = "CadastroController", urlPatterns = {"/cadastro"})
public class CadastroController extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8"); 
            
            ArrayList<String> errors = new ArrayList<>();
            
            //Preenche o usuario
            Usuario usuario = createUsuarioUsingParameters(request);
            
            //Testa se os campos foram preenchidos corretamente
            if(validaCamposCadastro(request, errors)){
               //Retorna ao cadastro
               RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
               request.setAttribute("page", "cadastro");
               request.setAttribute("errors", errors);
               rd.forward(request, response); 
            } else {
               //Cria uma conexao
               EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLab");
               //Tenta inserir no banco
               new UsuarioJpaController(emf).create(usuario);
               new PessoaJpaController(emf).create(createPessoaUsingParameters(request, usuario));

               //Retorna para o login(não passou parametro então é login)
               RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
               rd.forward(request, response);   
            }
        } catch (Exception ex) {
            ArrayList<String> errors = new ArrayList<>();
            errors = (ArrayList<String>) request.getAttribute("errors");
            
            RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
            request.setAttribute("page", "cadastro");
            request.setAttribute("errors", errors);
            rd.forward(request, response);
        }
        
    }
    
    protected boolean validaCamposCadastro(HttpServletRequest request, List<String> errors){
        try {
            boolean temErro = false;
            Usuario usuario = createUsuarioUsingParameters(request);
            Pessoa pessoa = createPessoaUsingParameters(request, usuario);
            
            if(usuario.getLogin().equals("")  || usuario.getLogin()==null){
                temErro = true;
                errors.add("Usuário precisa ser preenchido");
            } 
            //Eu testo se é maior que 20 mesmo sendo limitado no html
            //Pois a pessoa pode ir no html e alterar o valor
            else if(usuario.getLogin().length()>20) {
                temErro = true;
                errors.add("Usuário não pode ter mais que 20 caracteres");
            }
            
            if(usuario.getSenha().equals("") || usuario.getSenha()==null){
                temErro = true;
                errors.add("Senha precisa ser preenchida");
            }
            
            //Eu testo se é maior que 50 mesmo sendo limitado no html
            //Pois a pessoa pode ir no html e alterar o valor
            if(usuario.getSenha().length()>50) {
                temErro = true;
                errors.add("A senha não pode ter mais que 50 caracteres");
            } else if(usuario.getSenha().length()<6){
                temErro = true;
                errors.add("A senha precisa ter pelo menos 6 caracteres");
            }
            
            if(pessoa.getNome().equals("")  || pessoa.getNome()==null){
                temErro = true;
                errors.add("O nome não pode estar vazio");
            } else if(pessoa.getNome().length()>150){
                temErro = true;
                errors.add("O nome pode ter no máximo 150 caracteres");
            }
            
            if(pessoa.getTelefone().equals("")  || pessoa.getTelefone()==null){
                temErro = true;
                errors.add("O telefone precisa ser preenchido");
            } else if (pessoa.getTelefone().length()>20) {
                temErro = true;
                errors.add("O telefone só pode ter no máximo 20 caracteres");
            } else if (pessoa.getTelefone().length()<8) {
                temErro = true;
                errors.add("O telefone precisa ter no mínimo 8 caracteres");
            }
            
            if(pessoa.getSexo().equals("") || !pessoa.getSexo().equals("M") || !pessoa.getSexo().equals("F")  || pessoa.getSexo()==null){
                temErro = true;
                errors.add("O valor do sexo é inválido");
            }
            
            if (pessoa.getCpf().equals("") || pessoa.getCpf() == null) {
                temErro = true;
                errors.add("CPF não pode estar vazio");
            } else if(pessoa.getCpf().length() != 11){
                temErro = true;
                errors.add("O campo CPF precisa ter 11 caracteres");
            }
            
            if (pessoa.getEndereco().length()>255) {
                temErro = true;
                errors.add("Seu endereço precisa ter até 255 caracteres");
            }
            
            return temErro;
        } catch (Exception ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }
    
    protected Usuario createUsuarioUsingParameters(HttpServletRequest request) throws Exception{       
        Usuario usuario = new Usuario();
        usuario.setLogin(request.getParameter("login"));
        usuario.setSenha(request.getParameter("senha"));
        return usuario;
    }
    
    protected Pessoa createPessoaUsingParameters(HttpServletRequest request, Usuario usuario) throws Exception{        
        Pessoa pessoa = new Pessoa(usuario); //Passa usuario para criar pessoa com login
        pessoa.setNome(request.getParameter("nome"));
        pessoa.setTelefone(request.getParameter("telefone"));
        pessoa.setSexo(request.getParameter("sexo"));
        pessoa.setCpf(request.getParameter("cpf"));
        pessoa.setEndereco(request.getParameter("endereco"));
        return pessoa;
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
