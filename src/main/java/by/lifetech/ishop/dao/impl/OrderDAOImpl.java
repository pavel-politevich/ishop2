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
	
	private static final String HQL_CONFIRM_ORDER_PROC = "confirm_order";

	private static final String HQL_GET_PAYMENT_TYPES = "from PaymentType";

	private static final String HQL_GET_ORDER_BY_ID = "from Order where id = :orderId and paymentType is null";

	private static final String ORDER_ID_PARAM = "orderId";

	private static final String ITEM_ID_PARAM = "itemId";

	private static final String HQL_DELETE_ITEM_FROM_ORDER = "delete from OrderDetail where item.itemId = :itemId and order.id = :orderId";

	private static final String HQL_CREATE_ORDER_PROC = "create_order";
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public OrderDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int createEmptyOrder(int userId) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		StoredProcedureQuery  query = currentSession.createStoredProcedureCall(HQL_CREATE_ORDER_PROC)
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
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setItem(item);
		orderDetail.setOrder(order);
		orderDetail.setCount(count);
		orderDetail.setCost(item.getPrice().multiply(BigDecimal.valueOf(count)));
		
		orderDetails.add(orderDetail);		
		order.setOrderDetails(orderDetails);
		
		currentSession.save(orderDetail);
		
	}

	@Override
	public void deleteItem(int orderId, int itemId) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery(HQL_DELETE_ITEM_FROM_ORDER);
		theQuery.setParameter(ITEM_ID_PARAM, itemId).setParameter(ORDER_ID_PARAM, orderId).executeUpdate();
		
	}

	@Override
	public Order getOrder(int orderId) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Order> theQuery = currentSession.createQuery(HQL_GET_ORDER_BY_ID, Order.class);
		return theQuery.setParameter(ORDER_ID_PARAM, orderId).getSingleResult();
	}

	@Override
	public int getCurrentOrderId(int userId) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PaymentType> getPaymentTypes() throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<PaymentType> theQuery = currentSession.createQuery(HQL_GET_PAYMENT_TYPES, PaymentType.class);
		return theQuery.getResultList();
	}

	@Override
	public void confirmOrder(int orderId, String comment, String address, int paymentType) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		StoredProcedureQuery  query = currentSession.createStoredProcedureCall(HQL_CONFIRM_ORDER_PROC)
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
