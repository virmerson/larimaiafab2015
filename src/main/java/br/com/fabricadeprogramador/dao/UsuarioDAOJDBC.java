package br.com.fabricadeprogramador.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.fabricadeprogramador.entidade.Usuario;

@Repository
public class UsuarioDAOJDBC implements UsuarioDAO {

	@Override
	public Usuario salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Usuario usuario) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscarLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
