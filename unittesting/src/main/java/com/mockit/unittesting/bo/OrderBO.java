package com.mockit.unittesting.bo;

import com.mockit.unittesting.Order;
import com.mockit.unittesting.exceptions.BOException;

public interface OrderBO {
	public boolean placeOrder(Order order) throws BOException;
	public boolean cancelOrder(int id) throws BOException;
	public boolean deleteOrder(int id) throws BOException;
}
