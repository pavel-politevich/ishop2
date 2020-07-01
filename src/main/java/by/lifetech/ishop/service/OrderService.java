package by.lifetech.ishop.service;

import by.lifetech.ishop.entity.Order;
import by.lifetech.ishop.entity.PaymentType;
import by.lifetech.ishop.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    int createEmptyOrder(int userId) throws ServiceException;
    void addItem(int orderId, int itemId, int count) throws ServiceException;
    void deleteItem(int orderId, int itemId) throws ServiceException;
    Order getOrder(int orderId) throws ServiceException;
    int getCurrentOrderId(int userId) throws ServiceException;
    List<PaymentType> getPaymentTypes() throws ServiceException;
    void confirmOrder(int orderId, String comment, String address, int paymentType) throws ServiceException;
}
