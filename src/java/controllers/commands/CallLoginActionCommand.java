package controllers.commands;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.dao.PessoaJpaController;
import model.dao.UsuarioJpaController;

public class CallLoginActionCommand implements CommandApp {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Cria o dispatcher, pega o dispatcher e faz o forward
        RequestDispatcher rd = request.getRequestDispatcher("/control");
        
         //Se não achar o usuário, quer voltar para a home então já seta
         //Pois se achar usuario, este atributo será alterado
        String pagina = "home";

        //Pega o login preenchido
        Usuario usuario = new Usuario(request);

        //Cria uma conexao e tenta achar se o usuario digitado existe
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        Usuario usuarioEncontrado = new UsuarioJpaController(emf).findUsuarioByLoginAndSenha(usuario.getLogin(), usuario.getSenha());

        //Se não achar nada ele é nulo então já sai
        //Se achar, verifica se o login e senha realmente são como o digitado
        if (usuarioEncontrado != null){
            //Preenche o usuario com todas as suas informações(não somente login e senha)
            usuario = usuarioEncontrado;
            usuario.setPessoa(new PessoaJpaController(emf).findPessoa(usuario.getLogin()));
            
            //Passa o usuário como atributo
            request.getSession().setAttribute("usuario", usuario);
            
            //Então redireciona para dentro do sistema de acordo com seu tipo
            pagina = (usuarioEncontrado.getIsAdmin()) ? "admin" : "usuario" ;
            
        } 
        
        //Muda a página carregada em questão(admin ou usuario)
        request.setAttribute("page", pagina);

        rd.forward(request, response);
    }
}
