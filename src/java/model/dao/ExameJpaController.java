/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Exame;
import model.Pessoa;
import model.Usuario;
import model.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author adowt
 */
public class ExameJpaController implements Serializable {

    public ExameJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Exame exame) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa loginPessoa = exame.getLoginPessoa();
            if (loginPessoa != null) {
                loginPessoa = em.getReference(loginPessoa.getClass(), loginPessoa.getLogin());
                exame.setLoginPessoa(loginPessoa);
            }
            em.persist(exame);
            if (loginPessoa != null) {
                loginPessoa.getExameList().add(exame);
                loginPessoa = em.merge(loginPessoa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exame exame) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exame persistentExame = em.find(Exame.class, exame.getId());
            Pessoa loginPessoaOld = persistentExame.getLoginPessoa();
            Pessoa loginPessoaNew = exame.getLoginPessoa();
            if (loginPessoaNew != null) {
                loginPessoaNew = em.getReference(loginPessoaNew.getClass(), loginPessoaNew.getLogin());
                exame.setLoginPessoa(loginPessoaNew);
            }
            exame = em.merge(exame);
            if (loginPessoaOld != null && !loginPessoaOld.equals(loginPessoaNew)) {
                loginPessoaOld.getExameList().remove(exame);
                loginPessoaOld = em.merge(loginPessoaOld);
            }
            if (loginPessoaNew != null && !loginPessoaNew.equals(loginPessoaOld)) {
                loginPessoaNew.getExameList().add(exame);
                loginPessoaNew = em.merge(loginPessoaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = exame.getId();
                if (findExame(id) == null) {
                    throw new NonexistentEntityException("The exame with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exame exame;
            try {
                exame = em.getReference(Exame.class, id);
                exame.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exame with id " + id + " no longer exists.", enfe);
            }
            Pessoa loginPessoa = exame.getLoginPessoa();
            if (loginPessoa != null) {
                loginPessoa.getExameList().remove(exame);
                loginPessoa = em.merge(loginPessoa);
            }
            em.remove(exame);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Exame> findExameEntities() {
        return findExameEntities(true, -1, -1);
    }

    public List<Exame> findExameEntities(int maxResults, int firstResult) {
        return findExameEntities(false, maxResults, firstResult);
    }

    private List<Exame> findExameEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exame.class));
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

    public Exame findExame(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exame.class, id);
        } finally {
            em.close();
        }
    }

    public int getExameCount() {
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exame> rt = cq.from(Exame.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Exame> findExameByUsuario(Usuario user){
        String jpql = "select e from Exame e where e.loginPessoa = :logPess";
        
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("logPess", user.getPessoa());
        
        return (List<Exame>) q.getResultList();
    }
    
}
