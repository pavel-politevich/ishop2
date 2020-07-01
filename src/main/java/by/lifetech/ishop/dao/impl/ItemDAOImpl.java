package by.lifetech.ishop.dao.impl;

import java.util.Date;
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
import by.lifetech.ishop.entity.User;

@Repository
public class ItemDAOImpl implements ItemDAO {
	
	private static final String ITEM_ID_PARAM = "itemId";
	private static final String HQL_GET_ITEM_BY_ID = "from Item where itemId = :itemId";
	private static final String HQL_GET_ALL_CATEGORIES = "from Category";
	private static final String CATEGORY_ID_PARAM = "categoryId";
	private static final String HQL_GET_ITEMS_BY_CATEGORY_ID = "from Item where itemCategory.categoryId = :categoryId";
	
	private SessionFactory sessionFactory;
	
	
	@Autowired
	public ItemDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Item> findItemsByCategory(int categoryID) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Item> theQuery = currentSession.createQuery(HQL_GET_ITEMS_BY_CATEGORY_ID, Item.class);
		List<Item> items = theQuery.setParameter(CATEGORY_ID_PARAM, categoryID).getResultList();
		
		return items;
	}

	@Override
	public List<Category> getCategories() throws DAOException {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Category> theQuery = currentSession.createQuery(HQL_GET_ALL_CATEGORIES, Category.class);
		List<Category> categories = theQuery.getResultList();
		
		return categories;
	}

	@Override
	public Item getItem(int itemId) throws DAOException {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Item> theQuery = currentSession.createQuery(HQL_GET_ITEM_BY_ID, Item.class);
		Item item = theQuery.setParameter(ITEM_ID_PARAM, itemId).getSingleResult();
		
		return item;
	}

	@Override
	public void addItemReview(User user, int itemId, byte rate, String comment) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Item item = currentSession.get(Item.class, itemId);
		Review review = new Review();
		review.setItem(item);
		review.setRate(rate);
		review.setReviewDate(new Date());
		review.setUser(user);
		review.setComment(comment);
		
		currentSession.save(review);
		
	}

	@Override
	public List<Review> getItemReviews(int itemId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}



}
