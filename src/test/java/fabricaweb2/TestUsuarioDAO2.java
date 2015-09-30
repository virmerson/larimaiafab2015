package fabricaweb2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.dao.DAOException;
import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.dao.UsuarioDAOJPA;
import br.com.fabricadeprogramador.entidade.Usuario;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/resources/META-INF/springbeans.xml")
@TransactionConfiguration
public class TestUsuarioDAO2 {

	@Autowired
	@Qualifier("usuarioDAOJPA")
	UsuarioDAO usuarioDAO;

	
	@Test
	public void testSalvar (){
		
		//Criando novo usuario
		
		Usuario usu =  new Usuario();
		usu.setNome("Jão da Silva");
		usu.setLogin("jj");
		usu.setSenha("12345678910");
	
		Usuario usu2 = usuarioDAO.salvar(usu);
		Assert.assertNotNull(usu2.getId());
		
	}
	
	@Test
	public void testBuscarPorId(){
		//Criando um novo usuario
		Usuario usu =  new Usuario();
		usu.setNome("test");
		usu.setLogin("test");
		usu.setSenha("test");
		
		Usuario usuSalvo = usuarioDAO.salvar(usu);
		Integer idSalvo =  usuSalvo.getId();
		
		Usuario usuBuscado = usuarioDAO.buscarPorId(idSalvo);
		
		Assert.assertEquals(usuSalvo, usuBuscado);
		
	}
	
	@Test
	@Transactional
	public void testExcluir() throws DAOException{
		//Criar um novo usuario
		Usuario usu =  new Usuario();
		usu.setNome("test");
		usu.setLogin("test");
		usu.setSenha("test");
		
		//Salvar Usuario de teste
		Usuario usuSalvo = usuarioDAO.salvar(usu);
		
		//Buscar Por ID
		//Usuario  usuExc =  usuarioDAO.buscarPorId(usuSalvo.getId());
		
		//Excluir Usuario
		usuarioDAO.excluir(usuSalvo);
		//Busca por Id
		Usuario usuExcluido = usuarioDAO.buscarPorId(usuSalvo.getId());
		
		//Define correto se o objeto não for encontrado
		Assert.assertEquals(usuExcluido, null);
	}
	
	@Test
	public void testBuscarTodos(){
		//Criar um usuario novo
		Usuario usu =  new Usuario();
		usu.setNome("test");
		usu.setLogin("test");
		usu.setSenha("test");
		
		//Salvar Usuario de teste
		usuarioDAO.salvar(usu);
		
		//buscar no banco
		List<Usuario> todos = usuarioDAO.buscarTodos();
		//verificar se a lista é maior que zero
		Assert.assertTrue(todos.size()>0);
		
	}
	
}
