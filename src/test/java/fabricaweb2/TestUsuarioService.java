package fabricaweb2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;
import br.com.fabricadeprogramador.service.ServiceException;
import br.com.fabricadeprogramador.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration( transactionManager="transactionManager", defaultRollback=true)
public class TestUsuarioService {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	@Qualifier("usuarioDAOJPA")
	UsuarioDAO usuarioDAO;

	//@Transactional
	//@Test (expected=ServiceException.class)
	public void testNaoDeveSalvar() throws ServiceException {
		Usuario usu = new Usuario();
		usu.setLogin("testsalvar2");
		usu.setSenha("123");
		usu.setNome("Test Nome");

		usuarioDAO.salvar(usu);
		
		usuarioService.salvar(usu);

	}
	
	@Transactional
	@Test
	public void testDeveSalvar() throws ServiceException {
		Usuario usu = new Usuario();
		usu.setLogin("novousu332313443443ssas");
		usu.setSenha("123");
		usu.setNome("novo");
		
		usuarioService.salvar(usu);
		
	}

}
