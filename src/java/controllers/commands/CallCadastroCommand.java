package controllers.commands;

import controllers.Mensagem;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import model.Usuario;
import model.dao.PessoaJpaController;
import model.dao.UsuarioJpaController;

public class CallCadastroCommand implements CommandApp {
   
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("/control");
        
        ArrayList<Mensagem> mensagens = (ArrayList<Mensagem>)request.getAttribute("msgs");
        try {
            //Preenche o usuario
            Usuario usuario = new Usuario(request);
            
            request.setAttribute("page", "cadastro");
            
            //Testa se os campos foram preenchidos corretamente
            if(!validaCamposCadastro(request, mensagens)) {
               //Cria uma conexao
               EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
               //Tenta inserir no banco
               new UsuarioJpaController(emf).create(usuario);
               new PessoaJpaController(emf).create(new Pessoa(request, usuario));

               //Retorna para o login pois o cadastro foi com sucesso              
               request.setAttribute("page", "home");
            }
        } catch (Exception ex) {
            //Se deu erro no banco, então
            Mensagem msg = new Mensagem("erro", "cadastro", "Houve algum erro ao tentar conectar ao banco<br>"+ex.getMessage());
            if (!mensagens.contains(msg)) mensagens.add(msg);
            Logger.getLogger(controllers.commands.CallCadastroCommand.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //No final de tudo
            //Faz o forward de acordo e passa as mensagens de erro
            request.setAttribute("msgs", mensagens);
            rd.forward(request, response);
        }
    }
    
    
    protected boolean validaCamposCadastro(HttpServletRequest request, ArrayList<Mensagem> mensagens){
        try {
            boolean temErro = false;
            Usuario usuario = new Usuario(request);
            Pessoa pessoa = new Pessoa(request, usuario);
            
            if(usuario.getLogin().equals("")  || usuario.getLogin()==null){
                temErro = true;
                Mensagem msg = new Mensagem("erro", "login", "O campo de usuário precisa ser preenchido");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            } 
            //Eu testo se é maior que 20 mesmo sendo limitado no html
            //Pois a pessoa pode ir no html e alterar o valor
            else if(usuario.getLogin().length()>20) {
                temErro = true;
                Mensagem msg = new Mensagem("erro", "login", "O campo de usuário só pode ter até 20 caracteres");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            } else if (usuario.getLogin().length()<5) {
                temErro = true;
                Mensagem msg = new Mensagem("erro", "login", "Login precisa ter pelo menos 5 caracteres");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            }
            
            if(usuario.getSenha().equals("") || usuario.getSenha()==null){
                temErro = true;
                Mensagem msg = new Mensagem("erro", "senha", "A senha não pode estar vazia");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            }            
            //Eu testo se é maior que 50 mesmo sendo limitado no html
            //Pois a pessoa pode ir no html e alterar o valor
            else if(usuario.getSenha().length()>50) {
                temErro = true;
                Mensagem msg = new Mensagem("erro", "senha", "A senha não pode ter mais que 50 caracteres");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            } else if(usuario.getSenha().length()<6){
                temErro = true;
                Mensagem msg = new Mensagem("erro", "senha", "A senha precisa ter pelo menos 6 caracteres");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            }
            
            if(pessoa.getNome().equals("")  || pessoa.getNome()==null){
                temErro = true;
                Mensagem msg = new Mensagem("erro", "nome", "O campo nome não pode estar vazio");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            } else if(pessoa.getNome().length()>150){
                temErro = true;
                Mensagem msg = new Mensagem("erro", "nome", "O campo nome só pode ter 150 caracteres");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            }
            
            if(pessoa.getTelefone().equals("")  || pessoa.getTelefone()==null){
                temErro = true;
                Mensagem msg = new Mensagem("erro", "telefone", "O campo telefone precisa ser preenchido");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            } else if (pessoa.getTelefone().length()>20) {
                temErro = true;
                Mensagem msg = new Mensagem("erro", "telefone", "O campo de telefone só pode ter no máximo 20 caracteres");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            } else if (pessoa.getTelefone().length()<8) {
                temErro = true;
                Mensagem msg = new Mensagem("erro", "telefone", "O telefone precisa ter no mínimo 8 caracteres");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            }
            
            if((pessoa.getSexo()==null) || (pessoa.getSexo().equals("") && !pessoa.getSexo().equals("M") && !pessoa.getSexo().equals("F"))){
                temErro = true;
                Mensagem msg = new Mensagem("erro", "sexo", "O valor do sexo é inválido");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            }
            
            if (pessoa.getCpf().equals("") || pessoa.getCpf() == null) {
                temErro = true;
                Mensagem msg = new Mensagem("erro", "cpf", "O campo CPF não pode estar vazio");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            } else if(pessoa.getCpf().length() != 11){
                temErro = true;
                Mensagem msg = new Mensagem("erro", "cpf", "O campo CPF precisa de 11 caracteres");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            }
            
            if (pessoa.getEndereco().length()>255) {
                temErro = true;
                Mensagem msg = new Mensagem("erro", "endereço", "Endereço só pode ter 255 caracteres");
                if (!mensagens.contains(msg)) mensagens.add(msg);
            }
            
            return temErro;
        }  catch (Exception ex){
            Mensagem msg = new Mensagem("erro", "cadastro", ex.getMessage());
            if (!mensagens.contains(msg)) mensagens.add(msg);
            Logger.getLogger(controllers.commands.CallCadastroCommand.class.getName()).log(Level.SEVERE, null, ex);
            //Se der problema, retorna que deu erro
            return true;
        }
    }

}
