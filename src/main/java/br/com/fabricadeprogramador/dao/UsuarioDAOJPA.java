package br.com.fabricadeprogramador.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.entidade.Usuario;

@Repository
public class UsuarioDAOJPA implements UsuarioDAO {
	// Dependencia
	@PersistenceContext
	EntityManager em;

	public UsuarioDAOJPA(EntityManager em) {
		this.em = em;
	}

	public UsuarioDAOJPA() {

	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario u = em.merge(usuario);
		return u;

	}

	/*
	 * @Transactional public void excluir(Usuario usuario) {
	 * 
	 * em.remove(usuario);
	 * 
	 * }
	 */

	public void excluir(Usuario usuario) throws DAOException {
		try {
			em.remove(usuario);
		} catch (Exception e) {
			throw new DAOException("Não foi possivel Excluir!", e);
		}
	}

	public Usuario buscarPorId(int id) {
		return em.find(Usuario.class, id);
	}

	public List<Usuario> buscarTodos() {
		Query q = em.createQuery("select u from Usuario u");
		return q.getResultList();
	}

	@Override
	public Usuario buscarLogin(String login) {
		try {
			Query q =em.createQuery("select u from Usuario u where u.login=:loginParam");
			q.setParameter("loginParam", login);
			q.setMaxResults(1);
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
			//throw new DAOException("Registro não encontrado", e);
		}
		
	}

}
