package by.lifetech.ishop.dao;

import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.entity.Category;
import by.lifetech.ishop.entity.Item;
import by.lifetech.ishop.entity.Review;
import by.lifetech.ishop.entity.User;

import java.util.List;

public interface ItemDAO {
    List<Item> findItemsByCategory(int categoryID) throws DAOException;
    List<Category> getCategories() throws DAOException;
    Item getItem(int itemId) throws DAOException;
    void addItemReview(User user, int itemId, byte rate, String comment) throws DAOException;
    List<Review> getItemReviews(int itemId) throws DAOException;
}
