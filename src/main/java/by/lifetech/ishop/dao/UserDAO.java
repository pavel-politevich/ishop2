package by.lifetech.ishop.dao;

import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.User;

public interface UserDAO {
    void registration(User user) throws DAOException;
    User getUserByLogin(String login) throws DAOException;
}
