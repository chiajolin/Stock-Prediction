/**
 * written by: CHIA-JO LIN
 */
package models;

import org.hibernate.Session;

public class Login{
	public ManageUserBean loginUser(String userName, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		ManageUserBean user = null;
		//get ManageUserBean object with primary key "userName"
		user = (ManageUserBean)session.get(ManageUserBean.class, userName);   
		
		if(user != null){ //user is not null -> this user is in the db
			if(user.getPassword().equals(password)){ 
				return user;
			}
			else{
				return null; //wrong password
			}
		}	
		return null; //no this user
	}
}
