package by.lifetech.ishop.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.lifetech.ishop.dao.OrderDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.Item;
import by.lifetech.ishop.entity.Order;
import by.lifetech.ishop.entity.OrderDetail;
import by.lifetech.ishop.entity.PaymentType;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public OrderDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int createEmptyOrder(int userId) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		StoredProcedureQuery  query = currentSession.createStoredProcedureCall("create_order")
				.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
				.setParameter(1, userId);
		
		query.execute();
		
		return (Integer) query.getOutputParameterValue(2);
	}

	@Override
	public void addItem(int orderId, int itemId, int count) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Order order = currentSession.get(Order.class, orderId);
		Item item = currentSession.get(Item.class, itemId);
		
		List<OrderDetail> orderDetails = order.getOrderDetails();
		
		System.out.println("********** orderDetails before: " + orderDetails);
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setItem(item);
		orderDetail.setOrder(order);
		orderDetail.setCount(count);
		orderDetail.setCost(item.getPrice().multiply(BigDecimal.valueOf(count)));
		
		orderDetails.add(orderDetail);
		
		System.out.println("********** orderDetails after: " + orderDetails);
		
		order.setOrderDetails(orderDetails);
		
		System.out.println("********** Order after: " + order);
		
		currentSession.save(orderDetail);
		//currentSession.save(order);
		
	}

	@Override
	public void deleteItem(int orderId, int itemId) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		/*
		Order order = currentSession.get(Order.class, orderId);
		Item item = currentSession.get(Item.class, itemId);
		
		List<OrderDetail> orderDetails = order.getOrderDetails();
		orderDetails.remove();
		*/
		Query theQuery = currentSession.createQuery("delete from OrderDetail where item.itemId = :itemId and order.id = :orderId");
		theQuery.setParameter("itemId", itemId).setParameter("orderId", orderId).executeUpdate();
		
	}

	@Override
	public Order getOrder(int orderId) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		System.out.println("********** orderId: " + orderId);
		
		Query<Order> theQuery = currentSession.createQuery("from Order where id = :orderId and paymentType is null", Order.class);
		return theQuery.setParameter("orderId", orderId).getSingleResult();
		
		//return currentSession.get(Order.class, orderId);
	}

	@Override
	public int getCurrentOrderId(int userId) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PaymentType> getPaymentTypes() throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<PaymentType> theQuery = currentSession.createQuery("from PaymentType", PaymentType.class);
		return theQuery.getResultList();
	}

	@Override
	public void confirmOrder(int orderId, String comment, String address, int paymentType) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		System.out.println("********** paymentType: " + paymentType);
		
		StoredProcedureQuery  query = currentSession.createStoredProcedureCall("confirm_order")
				.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN)
				.setParameter(1, currentSession.get(PaymentType.class, paymentType).getDescritpion())
				.setParameter(2, comment)
				.setParameter(3, address)
				.setParameter(4, orderId);
		
		query.execute();
		
	}

}
