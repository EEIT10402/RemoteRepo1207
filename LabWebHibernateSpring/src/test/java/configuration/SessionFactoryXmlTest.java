package configuration;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.CustomerBean;

public class SessionFactoryXmlTest {
	private static ApplicationContext context;
	@BeforeClass
	public static void beforeClass() {
		context = new ClassPathXmlApplicationContext("beans.config.xml");
	}
	
	@Test
	public void testSessionFactory() {
		SessionFactory sessionFactory =
				(SessionFactory) context.getBean("sessionFactory");
		Assert.assertNotNull(sessionFactory);
		
		sessionFactory.getCurrentSession().beginTransaction();
		List<CustomerBean> beans = sessionFactory.getCurrentSession()
				.createQuery("from CustomerBean", CustomerBean.class).list();
		Assert.assertNotNull(beans);
		Assert.assertTrue(beans.size()>1);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	@AfterClass
	public static void afterClass() {
		((ConfigurableApplicationContext) context).close();
	}
}
