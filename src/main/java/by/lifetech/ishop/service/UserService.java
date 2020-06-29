package by.lifetech.ishop.service;

import by.lifetech.ishop.entity.User;
import by.lifetech.ishop.service.exception.ServiceException;

public interface UserService {
    void registration(User user) throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;
}
