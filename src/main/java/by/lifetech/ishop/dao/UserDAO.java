package by.lifetech.ishop.dao;

import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.User;

public interface UserDAO {
    void registration(User user) throws DAOException;
    User signIn (String login, byte[] password) throws DAOException;
}
