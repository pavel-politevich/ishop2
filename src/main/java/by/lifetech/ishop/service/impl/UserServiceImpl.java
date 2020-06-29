package by.lifetech.ishop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.lifetech.ishop.dao.UserDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.User;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;


@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;

	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	@Transactional
	public void registration(User user) throws ServiceException {
		try {
			userDAO.registration(user);
		} catch (DAOException e) {
			throw new ServiceException("Error while registration", e);
		}
	}

	@Transactional
	public User getUserByLogin(String login) throws ServiceException {
		try {
			return userDAO.getUserByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException("Error while find user", e);
		}
	}

}
