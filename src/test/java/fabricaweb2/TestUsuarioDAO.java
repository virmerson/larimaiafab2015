package fabricaweb2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fabricadeprogramador.dao.DAOException;
import br.com.fabricadeprogramador.dao.UsuarioDAOJPA;
import br.com.fabricadeprogramador.entidade.Usuario;

public class TestUsuarioDAO {

	EntityManager em ;
	ClassPathXmlApplicationContext ctx ;
	UsuarioDAOJPA usuarioDAO;
	
	@Before
	public void init(){
		//Contexto do Spring
		ctx =  new ClassPathXmlApplicationContext("file:src/main/resources/META-INF/springbeans.xml");
		EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
		em =  emf.createEntityManager();
		//Criando um usuarioDAO
		 usuarioDAO = new UsuarioDAOJPA(em);
	}
	
	@After
	public void finaliza(){
		ctx.close();
	}
	
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
	public void testExcluir() throws DAOException{
		//Criar um novo usuario
		Usuario usu =  new Usuario();
		usu.setNome("test");
		usu.setLogin("test");
		usu.setSenha("test");
		
		//Salvar Usuario de teste
		Usuario usuSalvo = usuarioDAO.salvar(usu);
		
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
