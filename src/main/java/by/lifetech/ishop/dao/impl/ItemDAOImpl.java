package by.lifetech.ishop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.lifetech.ishop.dao.ItemDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.Category;
import by.lifetech.ishop.entity.Item;
import by.lifetech.ishop.entity.Review;

@Repository
public class ItemDAOImpl implements ItemDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Item> findItemsByCategory(int categoryID) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Item> theQuery = currentSession.createQuery("from Item where itemCategory.categoryId = :categoryId", Item.class);
		List<Item> items = theQuery.setParameter("categoryId", categoryID).getResultList();
		
		return items;
	}

	@Override
	public List<Category> getCategories() throws DAOException {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Category> theQuery = currentSession.createQuery("from Category", Category.class);
		List<Category> categories = theQuery.getResultList();
		
		return categories;
	}

	@Override
	public Item getItem(int itemId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addItemReview(int userId, int itemId, byte rate, String comment) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Review> getItemReviews(int itemId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
