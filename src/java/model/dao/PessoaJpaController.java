/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Usuario;
import model.Exame;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Pessoa;
import model.dao.exceptions.IllegalOrphanException;
import model.dao.exceptions.NonexistentEntityException;
import model.dao.exceptions.PreexistingEntityException;

/**
 *
 * @author adowt
 */
public class PessoaJpaController implements Serializable {

    public PessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pessoa pessoa) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (pessoa.getExameList() == null) {
            pessoa.setExameList(new ArrayList<Exame>());
        }
        List<String> illegalOrphanMessages = null;
        Usuario usuarioOrphanCheck = pessoa.getUsuario();
        if (usuarioOrphanCheck != null) {
            Pessoa oldPessoaOfUsuario = usuarioOrphanCheck.getPessoa();
            if (oldPessoaOfUsuario != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Usuario " + usuarioOrphanCheck + " already has an item of type Pessoa whose usuario column cannot be null. Please make another selection for the usuario field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = pessoa.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getLogin());
                pessoa.setUsuario(usuario);
            }
            List<Exame> attachedExameList = new ArrayList<Exame>();
            for (Exame exameListExameToAttach : pessoa.getExameList()) {
                exameListExameToAttach = em.getReference(exameListExameToAttach.getClass(), exameListExameToAttach.getId());
                attachedExameList.add(exameListExameToAttach);
            }
            pessoa.setExameList(attachedExameList);
            em.persist(pessoa);
            if (usuario != null) {
                usuario.setPessoa(pessoa);
                usuario = em.merge(usuario);
            }
            for (Exame exameListExame : pessoa.getExameList()) {
                Pessoa oldLoginPessoaOfExameListExame = exameListExame.getLoginPessoa();
                exameListExame.setLoginPessoa(pessoa);
                exameListExame = em.merge(exameListExame);
                if (oldLoginPessoaOfExameListExame != null) {
                    oldLoginPessoaOfExameListExame.getExameList().remove(exameListExame);
                    oldLoginPessoaOfExameListExame = em.merge(oldLoginPessoaOfExameListExame);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPessoa(pessoa.getLogin()) != null) {
                throw new PreexistingEntityException("Pessoa " + pessoa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pessoa pessoa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa persistentPessoa = em.find(Pessoa.class, pessoa.getLogin());
            Usuario usuarioOld = persistentPessoa.getUsuario();
            Usuario usuarioNew = pessoa.getUsuario();
            List<Exame> exameListOld = persistentPessoa.getExameList();
            List<Exame> exameListNew = pessoa.getExameList();
            List<String> illegalOrphanMessages = null;
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Pessoa oldPessoaOfUsuario = usuarioNew.getPessoa();
                if (oldPessoaOfUsuario != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Usuario " + usuarioNew + " already has an item of type Pessoa whose usuario column cannot be null. Please make another selection for the usuario field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getLogin());
                pessoa.setUsuario(usuarioNew);
            }
            List<Exame> attachedExameListNew = new ArrayList<Exame>();
            for (Exame exameListNewExameToAttach : exameListNew) {
                exameListNewExameToAttach = em.getReference(exameListNewExameToAttach.getClass(), exameListNewExameToAttach.getId());
                attachedExameListNew.add(exameListNewExameToAttach);
            }
            exameListNew = attachedExameListNew;
            pessoa.setExameList(exameListNew);
            pessoa = em.merge(pessoa);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setPessoa(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.setPessoa(pessoa);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Exame exameListOldExame : exameListOld) {
                if (!exameListNew.contains(exameListOldExame)) {
                    exameListOldExame.setLoginPessoa(null);
                    exameListOldExame = em.merge(exameListOldExame);
                }
            }
            for (Exame exameListNewExame : exameListNew) {
                if (!exameListOld.contains(exameListNewExame)) {
                    Pessoa oldLoginPessoaOfExameListNewExame = exameListNewExame.getLoginPessoa();
                    exameListNewExame.setLoginPessoa(pessoa);
                    exameListNewExame = em.merge(exameListNewExame);
                    if (oldLoginPessoaOfExameListNewExame != null && !oldLoginPessoaOfExameListNewExame.equals(pessoa)) {
                        oldLoginPessoaOfExameListNewExame.getExameList().remove(exameListNewExame);
                        oldLoginPessoaOfExameListNewExame = em.merge(oldLoginPessoaOfExameListNewExame);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pessoa.getLogin();
                if (findPessoa(id) == null) {
                    throw new NonexistentEntityException("The pessoa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa pessoa;
            try {
                pessoa = em.getReference(Pessoa.class, id);
                pessoa.getLogin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pessoa with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = pessoa.getUsuario();
            if (usuario != null) {
                usuario.setPessoa(null);
                usuario = em.merge(usuario);
            }
            List<Exame> exameList = pessoa.getExameList();
            for (Exame exameListExame : exameList) {
                exameListExame.setLoginPessoa(null);
                exameListExame = em.merge(exameListExame);
            }
            em.remove(pessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pessoa> findPessoaEntities() {
        return findPessoaEntities(true, -1, -1);
    }

    public List<Pessoa> findPessoaEntities(int maxResults, int firstResult) {
        return findPessoaEntities(false, maxResults, firstResult);
    }

    private List<Pessoa> findPessoaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pessoa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pessoa findPessoa(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pessoa.class, id);
        } finally {
            em.close();
        }
    }

    public int getPessoaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pessoa> rt = cq.from(Pessoa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
