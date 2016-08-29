package com.mockit.unittesting.dao;

import java.sql.SQLException;

import com.mockit.unittesting.Order;

public interface OrderDAO {
	
	public int createOrder(Order order) throws SQLException;
	public Order readOrder(int id) throws SQLException;
	public int updateOrder(Order order) throws SQLException;
}
