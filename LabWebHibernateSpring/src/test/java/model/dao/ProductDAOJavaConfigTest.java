package model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import model.ProductBean;
import model.ProductDAO;

@FixMethodOrder(value=MethodSorters.NAME_ASCENDING)
public class ProductDAOJavaConfigTest {
	private static ApplicationContext context;
	private static SessionFactory sessionFactory;
	private static ProductDAO productDao;
	@BeforeClass
	public static void beforeClass() {
		context = new AnnotationConfigApplicationContext(
				configuration.SpringJavaConfiguration.class);
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		productDao = (ProductDAO) context.getBean("productDAOHibernate");
	}
	@Before
	public void before() {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@Test
	public void test1_create() {
		System.out.println("createTest");
		ProductBean insert1 = new ProductBean();
		insert1.setId(1000);
		insert1.setName("hahaha");
		insert1.setPrice(12.34);
		insert1.setMake(new java.util.Date());
		insert1.setExpire(56);
		ProductBean result1 = productDao.create(insert1);
		Assert.assertEquals(insert1, result1);

		ProductBean insert2 = new ProductBean();
		insert2.setId(1);
		insert2.setName("hahaha");
		insert2.setPrice(12.34);
		insert2.setMake(new java.util.Date());
		insert2.setExpire(56);
		ProductBean result2 = productDao.create(insert2);
		Assert.assertNull(result2);
	}

	@Test
	public void test2_findByPrimaryKey() {
		System.out.println("findByPrimaryKeyTest");
		ProductBean select1 = productDao.findByPrimaryKey(1);
		Assert.assertNotNull(select1);
		Assert.assertEquals(1, select1.getId());
		
		ProductBean select2 = productDao.findByPrimaryKey(9000);
		Assert.assertNull(select2);
	}
	
	@Test
	public void test3_findAll() {
		System.out.println("findAll");
		List<ProductBean> beans = productDao.findAll();
		Assert.assertNotNull(beans);
		Assert.assertNotEquals(0, beans.size());
	}
	
	@Test
	public void test4_update() {
		System.out.println("updateTest");
		ProductBean update1 = productDao.update(
				"hahaha", 12.34, new java.util.Date(), 56, 1000);
		Assert.assertNotNull(update1);
		
		ProductBean update2 = productDao.update(
				"hahaha", 12.34, new java.util.Date(), 56, 9000);
		Assert.assertNull(update2);
	}
	
	@Test
	public void test5_remove() {
		System.out.println("removeTest");
		boolean result1 = productDao.remove(1000);
		Assert.assertTrue(result1);
		
		boolean result2 = productDao.remove(9000);
		Assert.assertFalse(result2);
	}
	
	@After
	public void after() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	@AfterClass
	public static void afterClass() {
		productDao = null;
		((ConfigurableApplicationContext) context).close();
	}
}
