package by.lifetech.ishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.lifetech.ishop.dao.OrderDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.Order;
import by.lifetech.ishop.entity.PaymentType;
import by.lifetech.ishop.service.OrderService;
import by.lifetech.ishop.service.exception.ServiceException;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderDAO orderDAO;

	@Autowired
	public OrderServiceImpl(OrderDAO orderDAO) {
		super();
		this.orderDAO = orderDAO;
	}

	@Transactional
	public int createEmptyOrder(int userId) throws ServiceException {
		try {
			return orderDAO.createEmptyOrder(userId);
		} catch (DAOException e) {
			throw new ServiceException("Error while create empty Order", e);
		}
	}

	@Transactional
	public void addItem(int orderId, int itemId, int count) throws ServiceException {
		try {
			orderDAO.addItem(orderId, itemId, count);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding Item to Order", e);
		}

	}

	@Transactional
	public void deleteItem(int orderId, int itemId) throws ServiceException {
		try {
			orderDAO.deleteItem(orderId, itemId);
		} catch (DAOException e) {
			throw new ServiceException("Error while deleting Item from Order", e);
		}

	}

	@Transactional
	public Order getOrder(int orderId) throws ServiceException {
		try {
			return orderDAO.getOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException("Error while getOrder", e);
		}
	}

	@Transactional
	public int getCurrentOrderId(int userId) throws ServiceException {
		try {
			return orderDAO.getCurrentOrderId(userId);
		} catch (DAOException e) {
			throw new ServiceException("Error while getCurrentOrderId", e);
		}
	}

	@Transactional
	public List<PaymentType> getPaymentTypes() throws ServiceException {
		try {
			return orderDAO.getPaymentTypes();
		} catch (DAOException e) {
			throw new ServiceException("Error while getPaymentTypes", e);
		}
	}

	@Transactional
	public void confirmOrder(int orderId, String comment, String address, int paymentType) throws ServiceException {
		try {
			orderDAO.confirmOrder(orderId, comment, address, paymentType);
		} catch (DAOException e) {
			throw new ServiceException("Error while confirmOrder", e);
		}
		
	}

}
