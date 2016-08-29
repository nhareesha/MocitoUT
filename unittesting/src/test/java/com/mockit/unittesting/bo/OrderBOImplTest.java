package com.mockit.unittesting.bo;

import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

import com.mockit.unittesting.Order;
import com.mockit.unittesting.dao.OrderDAO;
import com.mockit.unittesting.exceptions.BOException;

public class OrderBOImplTest {
	
	//1. step(a) - Stubbing
	@Mock
	OrderDAO dao;
	
	Order order;

	private OrderBOImpl bo;
	
	@Before
	public void setUp() throws Exception {
		//1. step(b)
		MockitoAnnotations.initMocks(this);  //this initiates the mock objects at runtime 
											//for the current instance
		//setting up pojo 
		order = new Order();
		order.setId(123);
		order.setStatus("NEW");
		
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}


	@Test
	public void placeOrderTest_should_crateOrder_Successfully() throws SQLException, BOException {
		
		//2 . Step - Set Expectations for the mock methods that are being called by placedOrder method
		when(dao.createOrder(order)).thenReturn(new Integer(1));
		
		//3. Actual method call
		boolean result = bo.placeOrder(order);
		
		//4. Assert the result against expected
		assertTrue(result);
		
		//5. Verify - verify if the methods on mock object are actually being called
		verify(dao).createOrder(order);
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
