package com.mockit.unittesting.bo;

import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
	/**
	 * This is negative test for placeOrder method
	 * @throws SQLException
	 * @throws BOException
	 */
	@Test
	public void placeOrderTest_should_not_create_order() throws SQLException, BOException{
		//Setup Expectations
		when(dao.createOrder(order)).thenReturn(new Integer(0));
		
		//call actual method
		boolean result = bo.placeOrder(order);
		
		//assert the result
		assertFalse(result);
		
		//verify, if mock methods are being called
		verify(dao).createOrder(order);		
	}
	/**
	 * This test method is to test the exception scenario
	 * @throws SQLException
	 * @throws BOException
	 */
	@Test(expected=BOException.class)
	public void placeOrderTest_should_throw_BOException() throws SQLException, BOException{
		//Setup Expectations
		when(dao.createOrder(order)).thenThrow(SQLException.class);
		
		//call the actual method
		bo.placeOrder(order);
		
		//verify if method on mock obj is called
		verify(dao).createOrder(order);			
	}
	
	/**
	 * This is the positive test case for cancelOrder
	 * @throws SQLException 
	 * @throws BOException 
	 */
	@Test
	public void cancelOrder_success() throws SQLException, BOException{
		
		//Set Expectations
		when(dao.readOrder(123)).thenReturn(order);
		when(dao.updateOrder(order)).thenReturn(new Integer(1));
		//call actual method on class under test
		boolean result = bo.cancelOrder(123); //123 is the test data under which behavior is set
		
		//Assert the result
		assertTrue(result);
		
		//verify if mock methods are being called
		verify(dao).readOrder(123);
		verify(dao).updateOrder(order);
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
