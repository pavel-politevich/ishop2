package by.lifetech.ishop.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import by.lifetech.ishop.dao.UserDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.Role;
import by.lifetech.ishop.entity.User;
import by.lifetech.ishop.entity.UserState;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final String LOGIN_PARAM = "login";
	private static final String HQL_GET_USER_BY_LOGIN = "from User where login = :login";
	
	private SessionFactory sessionFactory;
	private PasswordEncoder encoder;

	
	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory, PasswordEncoder encoder) {
		super();
		this.sessionFactory = sessionFactory;
		this.encoder = encoder;
	}


	@Override
	public void registration(User user) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Role userRole = currentSession.get(Role.class, 2);
		UserState userState = currentSession.get(UserState.class, 1);
		
		user.setUserRole(userRole);
		user.setUserState(userState);
		user.setPassword(encoder.encode(user.getPassword()));
		
		currentSession.save(user);
		
	}


	@Override
	public User getUserByLogin(String login) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = currentSession.createQuery(HQL_GET_USER_BY_LOGIN, User.class);
		User user = theQuery.setParameter(LOGIN_PARAM, login).getSingleResult();
		return user;
	}


}
