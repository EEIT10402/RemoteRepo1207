package model;


public interface CustomerDAO {
	public abstract CustomerBean findByPrimaryKey(String custid);
	public abstract boolean update(byte[] password, String email,
			java.util.Date birth, String custid);
}