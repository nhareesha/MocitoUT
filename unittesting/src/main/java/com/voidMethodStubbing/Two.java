package com.voidMethodStubbing;

public class Two {
	private One one;//This
	
	public Two(One one){
		this.one=one;
	}
	
	/**
	 * This methods calls the void method of One class
	 * @return
	 */
	public int callVoidMethod(){
		try {
			one.fun();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 1;
	}
}
