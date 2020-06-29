package by.lifetech.ishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.lifetech.ishop.dao.ItemDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.Category;
import by.lifetech.ishop.entity.Item;
import by.lifetech.ishop.entity.Review;
import by.lifetech.ishop.entity.User;
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.exception.ServiceException;


@Service
public class ItemServiceImpl implements ItemService {
	
	private ItemDAO itemDAO;

	@Autowired
	public ItemServiceImpl(ItemDAO itemDAO) {
		super();
		this.itemDAO = itemDAO;
	}


	@Transactional
	public List<Category> getCategories() throws ServiceException {

		try {
			return itemDAO.getCategories();
		} catch (DAOException e) {
			throw new ServiceException("Error while find all categories", e);
		}
	}


	@Transactional
	public List<Item> getItemsByCategory(int categoryId) throws ServiceException {
		try {
			return itemDAO.findItemsByCategory(categoryId);
		} catch (DAOException e) {
			throw new ServiceException("Error while find all categories", e);
		}
	}

	@Transactional
	public Item getItem(int itemId) throws ServiceException {
		try {
			return itemDAO.getItem(itemId);
		} catch (DAOException e) {
			throw new ServiceException("Error while find Item by ID", e);
		}
	}

	@Transactional
	public void addItemReview(User user, int itemId, byte rate, String comment) throws ServiceException {
		try {
			itemDAO.addItemReview(user, itemId, rate, comment);
		} catch (DAOException e) {
			throw new ServiceException("Error while find Item by ID", e);
		}
	}

	@Transactional
	public List<Review> getItemReviews(int itemId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
