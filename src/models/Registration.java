/**
 * written by: CHIA-JO LIN
 */
package models;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Registration{
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return status of registering a new user, either success or user already existed
	 */
	public String storeUserInformation(String userName, String password) {					
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
				
		ManageUserBean user = null;		
		user = (ManageUserBean)session.get(ManageUserBean.class,userName);
		
		//if userName not exists in db -> new this user into db
		if(user == null){
			user = new ManageUserBean();
			user.setUserName(userName);
			user.setPassword(password);
		
			session.save(user);
			transaction.commit();
			session.close();
			return "success";
		}
		else{
			return "exists";
		}	
	}
}
