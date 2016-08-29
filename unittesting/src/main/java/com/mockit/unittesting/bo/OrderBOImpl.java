package com.mockit.unittesting.bo;

import java.sql.SQLException;

import com.mockit.unittesting.Order;
import com.mockit.unittesting.dao.OrderDAO;
import com.mockit.unittesting.exceptions.BOException;

public class OrderBOImpl implements OrderBO {

	private OrderDAO dao;

	public boolean placeOrder(Order order) throws BOException {
		try {
			int result = getDao().createOrder(order);
			if (result == 0)
				return false;
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	public boolean cancelOrder(int id) throws BOException {
		try {
			Order order = getDao().readOrder(id);
			if(order!=null){
				order.setStatus("CANCEL");
				int result = getDao().updateOrder(order);
				if(result == 0){
					return false;
				}
			}else
				return false;
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	public boolean deleteOrder(int id) throws BOException {
		try {
			int result = getDao().delete(id);
			if(result == 0){
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		
		return true;
	}

	public OrderDAO getDao() {
		return dao;
	}

	public void setDao(OrderDAO dao) {
		this.dao = dao;
	}

}
