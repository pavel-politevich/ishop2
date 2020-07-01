package by.lifetech.ishop.dao;

import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.Order;
import by.lifetech.ishop.entity.PaymentType;

import java.util.List;

public interface OrderDAO {
    int createEmptyOrder(int userId) throws DAOException;
    void addItem(int orderId, int itemId, int count) throws DAOException;
    void deleteItem(int orderId, int itemId) throws DAOException;
    Order getOrder(int orderId) throws DAOException;
    int getCurrentOrderId(int userId) throws DAOException;
    List<PaymentType> getPaymentTypes() throws DAOException;
    void confirmOrder(int orderId, String comment, String address, int paymentType) throws DAOException;
}
