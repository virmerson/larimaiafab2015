package fabricaweb2;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fabricadeprogramador.entidade.Usuario;

public class TestSpringBeans {

	@Test
	public void testContextoSpring(){
		ClassPathXmlApplicationContext ctx =  new ClassPathXmlApplicationContext("file:src/main/resources/META-INF/springbeans.xml");
		
		BasicDataSource bds =  (BasicDataSource) ctx.getBean("dataSource");
		System.out.println(bds.getPassword() + " "+ bds.getUsername() + " "+ bds.getDriverClassName());
		
		ctx.close();
	}
	
	/*@Test
	public void testCadastroCliente(){
		Cliente cli = new Cliente();
		cli.setNome("JAO");
		
		ClienteDAO cliDAO = new ClienteDAO();
		Cliente cliSalvo = cliDAO.salvar(cli);
		
		Assert.assertNotNull(cliSalvo!=null);
		
		
	}*/

}
