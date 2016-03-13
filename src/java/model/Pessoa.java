/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author adowt
 */
@Entity
@Table(catalog = "RedLab", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cpf"})})
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByLogin", query = "SELECT p FROM Pessoa p WHERE p.login = :login"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findByTelefone", query = "SELECT p FROM Pessoa p WHERE p.telefone = :telefone"),
    @NamedQuery(name = "Pessoa.findBySexo", query = "SELECT p FROM Pessoa p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf"),
    @NamedQuery(name = "Pessoa.findByEndereco", query = "SELECT p FROM Pessoa p WHERE p.endereco = :endereco"),
    @NamedQuery(name = "Pessoa.findByTemPlanoSaude", query = "SELECT p FROM Pessoa p WHERE p.temPlanoSaude = :temPlanoSaude")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String login;
    @Column(length = 150)
    private String nome;
    @Column(length = 20)
    private String telefone;
    @Column(length = 1)
    private String sexo;
    @Column(length = 11)
    private String cpf;
    @Column(length = 255)
    private String endereco;
    private Boolean temPlanoSaude;
    @OneToMany(mappedBy = "loginPessoa")
    private List<Exame> exameList;
    @JoinColumn(name = "login", referencedColumnName = "login", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Pessoa() {
        this.temPlanoSaude = false;
    }
    
    public Pessoa(Usuario usuario) {
        this.temPlanoSaude = false;
        this.usuario = usuario;
        this.login = usuario.getLogin();
    }

    public Pessoa(String login) {
        this.login = login;
    }
    
    public Pessoa(HttpServletRequest request, Usuario usuario) {
        this.temPlanoSaude = false;
        this.populatePessoaUsingParameters(request, usuario);
    }
    
    private void populatePessoaUsingParameters(HttpServletRequest request, Usuario usuario) {        
        this.setLogin(usuario.getLogin());
        this.setUsuario(usuario);
        this.setNome((request.getParameter("nome") == null) ? "" : request.getParameter("nome"));
        this.setTelefone((request.getParameter("telefone")==null) ? "" : request.getParameter("telefone") );
        this.setSexo((request.getParameter("sexo")==null) ? "" : request.getParameter("sexo"));
        this.setCpf((request.getParameter("cpf")==null) ? "":request.getParameter("cpf"));
        this.setEndereco((request.getParameter("endereco")==null) ? "": request.getParameter("endereco"));
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getTemPlanoSaude() {
        return temPlanoSaude;
    }

    public void setTemPlanoSaude(Boolean temPlanoSaude) {
        this.temPlanoSaude = temPlanoSaude;
    }

    public List<Exame> getExameList() {
        return exameList;
    }

    public void setExameList(List<Exame> exameList) {
        this.exameList = exameList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pessoa[ login=" + login + " ]";
    }
    
}
