package com.powerMock;
/**
 * This class has dependency on a final class static method
 * This is used to demonstrate how test can be written for static methods
 * @author Hareesha
 *
 */
public class UserDAO {
	private User user;
	public UserDAO(User user){
		this.user = user;
	}
	
	/**
	 * This method uses a static method of final class
	 */
	public void setUserId(){
		String id = IDGenerator.getId();
		user.setId(id);
	}

}
