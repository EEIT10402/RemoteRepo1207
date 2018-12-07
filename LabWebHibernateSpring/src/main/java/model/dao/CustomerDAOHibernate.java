package model.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.CustomerBean;
import model.CustomerDAO;

@Repository
public class CustomerDAOHibernate implements CustomerDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public CustomerBean findByPrimaryKey(String custid) {
		return this.getSession().get(CustomerBean.class, custid);
	}
	@Override
	public boolean update(byte[] password, String email, Date birth, String custid) {
		CustomerBean result = this.getSession().get(CustomerBean.class, custid);
		if(result!=null) {
			result.setPassword(password);
			result.setEmail(email);
			result.setBirth(birth);
			return true;
		}
		return false;
	}
}
