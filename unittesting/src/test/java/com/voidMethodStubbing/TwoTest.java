package com.voidMethodStubbing;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TwoTest {
	@Mock
	One one;
	Two two;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		 two= new Two(one);
	}

	@Test
	public void voidMethodTest_shouldCall_voidMethod() throws Exception {
		
		//set expectation on vid method
		doNothing().when(one).fun();
		
		//call actual method
		two.callVoidMethod();
		
		//verify if method is called
		verify(one).fun();
	}
	/**
	 * Should throw exception test
	 * @throws Exception
	 */
	@Test(expected=RuntimeException.class)
	public void voidMethodTest_should_throw_Exception() throws Exception{
		
		//set expectation on vid method
		doThrow(Exception.class).when(one).fun();
		
		//call actual method
		two.callVoidMethod();
		
		//verify if method is called
		verify(one).fun();
	}


	@After
	public void tearDown() throws Exception {
	}
}
