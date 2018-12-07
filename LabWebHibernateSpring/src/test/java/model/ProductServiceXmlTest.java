package model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductServiceXmlTest {
	private static ApplicationContext context;
	private static SessionFactory sessionFactory;
	private static ProductService productService;
	@BeforeClass
	public static void beforeClass() {
		context = new ClassPathXmlApplicationContext("beans.config.xml");
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		productService = (ProductService) context.getBean("productService");
	}
	@Before
	public void before() {
		sessionFactory.getCurrentSession().beginTransaction();
	}
	
	@Test
	public void testSelect() {
		List<ProductBean> beans = productService.select(null);
		Assert.assertNotNull(beans);
		Assert.assertTrue(beans.size()>1);
	}
	
	@After
	public void after() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	@AfterClass
	public static void afterClass() {
		productService = null;
		((ConfigurableApplicationContext) context).close();
	}
}
