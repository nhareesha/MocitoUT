package com.mockit.unittesting.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mockit.unittesting.Order;
import com.mockit.unittesting.dao.OrderDAO;
import com.mockit.unittesting.exceptions.BOException;
public class OrderBOImplTest {
	
	private static final int ORDER_ID = 123;

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
		order.setId(ORDER_ID);
		order.setStatus("NEW");
		
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}


	@Test
	public void placeOrderTest_should_crateOrder_Successfully() throws SQLException, BOException {
		
		//2 . Step - Set Expectations for the mock methods that are being called by placedOrder method
		when(dao.createOrder(any(Order.class))).thenReturn(new Integer(1));
		
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
		when(dao.createOrder(any(Order.class))).thenThrow(SQLException.class);
		
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
		
		final String CANCEL = "CANCEL";
		//Set Expectations
		when(dao.readOrder(ORDER_ID)).thenReturn(order);
		when(dao.updateOrder(order)).thenReturn(new Integer(1));
		//call actual method on class under test
		boolean result = bo.cancelOrder(ORDER_ID); //123 is the test data under which behavior is set
		
		//Assert the result
		assertTrue(result);
		assertEquals(CANCEL, order.getStatus());
		
		//verify if mock methods are being called
		verify(dao).readOrder(ORDER_ID);
		verify(dao).updateOrder(order);
		
	}
	/**
	 * this is the negative test case for cancelOrder
	 * @throws SQLException
	 * @throws BOException
	 */
	@Test
	public void cancelOrder_failure() throws SQLException, BOException{
		
		//Set Expectations
		when(dao.readOrder(ORDER_ID)).thenReturn(order);
		
		//when(dao.readOrder(123)).thenReturn(null); //this is also a possible scenario
		when(dao.updateOrder(order)).thenReturn(new Integer(0));
		
		//call actual method on class under test
		boolean result = bo.cancelOrder(ORDER_ID); //123 is the test data under which behavior is set
		
		//Assert the result
		assertFalse(result);
		
		//verify if mock methods are being called
		verify(dao).readOrder(anyInt()); //we are just verifying the behavior so it doesn't matter what is the exact value
		verify(dao).updateOrder(order);		
	}
	/**
	 * This methods the scenario where SQLException is thrown by read and resulting in BOExcetion
	 * @throws SQLException
	 * @throws BOException
	 */
	@Test(expected=BOException.class)
	public void cancelOrder_should_throw_BOExceptionOnRead() throws SQLException, BOException{
		//set expectations
		when(dao.readOrder(anyInt())).thenThrow(SQLException.class);
		//call aactual method
		bo.cancelOrder(ORDER_ID);
		//verify , id mock object methods are actually being called
		verify(dao).readOrder(anyInt());
	}
	/**
	 * This is the Exception testcase for cancelOrder
	 * @throws SQLException
	 * @throws BOException
	 */
	@Test(expected=BOException.class)
	public void cancelOrder_should_throw_BOException() throws SQLException, BOException{
		//set expectations
		when(dao.readOrder(ORDER_ID)).thenReturn(order);
		when(dao.updateOrder(order)).thenThrow(SQLException.class);
		
		//call actual method
		bo.cancelOrder(ORDER_ID);
		
		//verify , id mock object methods are actually being called
		verify(dao).readOrder(anyInt());
		verify(dao).updateOrder((Order)anyObject());
	}
	
	/**
	 * This is a positive test on deleteOrder
	 * @throws SQLException 
	 * @throws BOException 
	 * 
	 */
	@Test
	public void deleteOrder_success() throws SQLException, BOException{
		//set expectations
		when(dao.delete(ORDER_ID)).thenReturn(new Integer(1));
		
		//call actual method
		boolean result = bo.deleteOrder(ORDER_ID);
		
		//assert result
		assertTrue(result);
		//verify if actual methods are actually being called on mock objects
		verify(dao).delete(anyInt());
	}
	
	/**
	 * This is a negative test on deleteOrder
	 * @throws SQLException
	 * @throws BOException
	 */
	@Test
	public void deleteOrder_failure() throws SQLException, BOException{
		//set expectations
		when(dao.delete(ORDER_ID)).thenReturn(new Integer(0));
		
		//call actual method
		boolean result = bo.deleteOrder(ORDER_ID);
		
		//assert result
		assertFalse(result);
		//verify if actual methods are actually being called on mock objects
		verify(dao).delete(anyInt());
	}
	/**
	 * This testcase on exception scenario for deleteOrder
	 * @throws SQLException
	 * @throws BOException
	 */
	@Test(expected=BOException.class)
	public void deleteOrder_shouldthrow_BOException() throws SQLException, BOException{
		//set expectations
		when(dao.delete(ORDER_ID)).thenThrow(SQLException.class);
		
		//call actual method
		bo.deleteOrder(ORDER_ID);
		
		//verify if actual methods are actually being called on mock objects
		verify(dao).delete(anyInt());
	}
	/**
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
}
