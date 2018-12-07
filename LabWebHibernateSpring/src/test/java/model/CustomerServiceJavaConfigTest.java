package model;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerServiceJavaConfigTest {
	private static ApplicationContext context;
	private static SessionFactory sessionFactory;
	private static CustomerService customerService;
	@BeforeClass
	public static void beforeClass() {
		context = new AnnotationConfigApplicationContext(
				configuration.SpringJavaConfiguration.class);
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		customerService = (CustomerService) context.getBean("customerService");
	}
	@Before
	public void before() {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@Test
	public void testLogin() {
		CustomerBean alex = customerService.login("Alex", "A");
		Assert.assertNotNull(alex);
		
		CustomerBean login = customerService.login("Alex", "AAA");
		Assert.assertNull(login);
	}
	@Test
	public void testChangePassword() {
		boolean change = customerService.changePassword("Ellen", "EEE", "EEE");
		Assert.assertTrue(change);
	}

	@After
	public void after() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	@AfterClass
	public static void afterClass() {
		customerService = null;
		((ConfigurableApplicationContext) context).close();
	}
}
