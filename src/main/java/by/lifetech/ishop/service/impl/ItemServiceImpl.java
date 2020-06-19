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
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.exception.ServiceException;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDAO itemDAO;

	//@Override
	@Transactional
	public List<Category> getCategories() throws ServiceException {

		try {
			return itemDAO.getCategories();
		} catch (DAOException e) {
			throw new ServiceException("Error while find all categories", e);
		}
	}

	//@Override
	@Transactional
	public List<Item> getItemsByCategory(int categoryId) throws ServiceException {
		try {
			return itemDAO.findItemsByCategory(categoryId);
		} catch (DAOException e) {
			throw new ServiceException("Error while find all categories", e);
		}
	}

	@Override
	public Item getItem(int itemId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addItemReview(int userId, int itemId, byte rate, String comment) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Review> getItemReviews(int itemId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
