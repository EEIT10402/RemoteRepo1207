package model;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerBeanJavaConfigTest {
	private static ApplicationContext context;
	private static SessionFactory sessionFactory;
	@BeforeClass
	public static void beforeClass() {
		context = new AnnotationConfigApplicationContext(
				configuration.SpringJavaConfiguration.class);
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
	}

	private Session session;
	private Transaction transaction;
	@Before
	public void before() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	public void insertTest() {
		CustomerBean insert = new CustomerBean();
		insert.setCustid("hohoho");
		insert.setPassword("H".getBytes());
		insert.setEmail("hehehe@lab.com");
		insert.setBirth(new java.util.Date());
		Serializable pk = session.save(insert);
		Assert.assertEquals("hohoho", pk);
	}
	
	@After
	public void after() {
		transaction.commit();			
		session.close();
	}
	@AfterClass
	public static void afterClass() {
		((ConfigurableApplicationContext) context).close();
	}
}
