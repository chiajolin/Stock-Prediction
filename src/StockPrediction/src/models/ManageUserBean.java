/**
 * written by: CHIA-JO LIN
 */
package models;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class ManageUserBean {
	
	@Id
	private String userName;
	private String password;
	
	//need constructor so that hibernate can call Constructor.newInstance()
	public ManageUserBean(){
		
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
