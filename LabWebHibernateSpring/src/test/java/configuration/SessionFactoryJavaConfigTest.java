package configuration;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import model.ProductBean;

public class SessionFactoryJavaConfigTest {
	private static ApplicationContext context;
	@BeforeClass
	public static void beforeClass() {
		context = new AnnotationConfigApplicationContext(
				configuration.SpringJavaConfiguration.class);
	}
	
	@Test
	public void testSessionFactory() {
		SessionFactory sessionFactory =
				(SessionFactory) context.getBean("sessionFactory");
		Assert.assertNotNull(sessionFactory);
		
		sessionFactory.getCurrentSession().beginTransaction();
		List<ProductBean> beans = sessionFactory.getCurrentSession()
				.createQuery("from ProductBean", ProductBean.class).list();
		Assert.assertNotNull(beans);
		Assert.assertTrue(beans.size()>1);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	@AfterClass
	public static void afterClass() {
		((ConfigurableApplicationContext) context).close();
	}
}
