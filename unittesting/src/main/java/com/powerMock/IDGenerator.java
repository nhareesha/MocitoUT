package com.powerMock;

import java.util.UUID;

public final class IDGenerator {
	private static String id;
	public static String getId(){
		id =  UUID.randomUUID().toString();
		return id;
	}
}
