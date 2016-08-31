package com.powerMock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.*;
import static org.junit.Assert.*;
/**
 * This test case demos the use of powermock to 
 * write test cases when there is dependency on static method
 * @author SunilKumar
 *
 */
@RunWith(PowerMockRunner.class) //this instructs the JUnit to user PowerMockRunner at runtime
@PrepareForTest(IDGenerator.class) //this prepare the class to be run using PowerMock runner 
public class UserDAOTest {

	private static final String ID = "abcd1234123";
	private User user;
	private UserDAO dao;
	@Before
	public void setUp() throws Exception {
		user = new User();
		dao = new UserDAO(user);
		
	}
	
	@Test
	public void setId_should_return_Id() {
		//to mock the final class
		mockStatic(IDGenerator.class);
		
		//set expectations for static method
		when(IDGenerator.getId()).thenReturn(ID);
		
		//call actual method
		dao.setUserId();
		
		//assertion on user object since this is not returing any ting
		assertEquals(ID, user.getId());
		
		//verify if static method on mock are being called
		verifyStatic();
		
	}

	@After
	public void tearDown() throws Exception {
	}
}
