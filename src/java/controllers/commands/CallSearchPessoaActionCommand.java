package controllers.commands;

import controllers.Helpers;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.dao.UsuarioJpaController;

public class CallSearchPessoaActionCommand implements CommandApp{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
        
        //Aqui eu crio um usuario usando as informações passadas no formulário
        //De busca, e uso esse usuario como termo de pesquisa, assim, eu posso
        //Depois, mudar a forma de como a busca é feita, sem remexer no
        //Código. Para isso, os nomes dos campos de busca devem ser
        //Iguais aos do campo de criação de usuário. Qualquer coisa, só olhar o construtor
        //Para saber os nomes de cada parâmetro
        Usuario searchUsu = new Usuario(request);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");        
        //Busca os usuarios
        List<Usuario> usuarios = new UsuarioJpaController(emf).findUsuariosByUsuario(searchUsu);
        
        String pagina = "buscaUsuarios";
        //Gera o caminho correto do mvc baseado na pagina
        String caminho = new Helpers().geraCaminho(pagina);
        
        //Passa a tela de usuarios
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        request.setAttribute("users", usuarios);
        request.setAttribute("searchUsu", searchUsu);
        rd.forward(request, response);
    }
    
}
