package model.dao;

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

import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOXmlTest {
	private static ApplicationContext context;
	private static SessionFactory sessionFactory;
	private static CustomerDAO customerDao;
	@BeforeClass
	public static void beforeClass() {
		context = new ClassPathXmlApplicationContext("beans.config.xml");
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		customerDao = (CustomerDAO) context.getBean("customerDAOHibernate");
	}
	@Before
	public void before() {
		sessionFactory.getCurrentSession().beginTransaction();
	}
	@Test
	public void testFindByPrimaryKey() {
		CustomerBean select = customerDao.findByPrimaryKey("Babe");
		Assert.assertNotNull(select);
		Assert.assertEquals("Babe", select.getCustid());
	}

	@Test
	public void testUpdate() {
		boolean update = customerDao.update(
				"EEE".getBytes(), "ellen@iii.org.tw", new java.util.Date(0), "Ellen");
		Assert.assertTrue(update);
	}

	@After
	public void after() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	@AfterClass
	public static void afterClass() {
		((ConfigurableApplicationContext) context).close();
	}
}
