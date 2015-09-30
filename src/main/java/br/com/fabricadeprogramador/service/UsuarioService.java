package br.com.fabricadeprogramador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.dao.DAOException;
import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;


@Service
public class UsuarioService {
	
	@Autowired
	@Qualifier(value="usuarioDAOJPA")
	UsuarioDAO usuarioDAO;
	
	@Transactional
	public Usuario salvar(Usuario usuario) throws ServiceException{
		//verificacoes
		Usuario usuarioExistente = usuarioDAO.buscarLogin(usuario.getLogin());
		//Já existe
		if (usuarioExistente!=null){
			throw new ServiceException("Usuário Já Existe!");
		}
		return usuarioDAO.salvar(usuario);
	}
	

	
	public void excluir(Usuario usuario) throws ServiceException {
			try {
				usuarioDAO.excluir(usuario);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
	}
	
	public Usuario buscarPorId(int id){
		return usuarioDAO.buscarPorId(id);
	}
	
	public List<Usuario> buscarTodos (){
		return usuarioDAO.buscarTodos();
	}

	public Usuario buscarLogin(String login){
		return usuarioDAO.buscarLogin(login);
	}
	
	
	
	
}