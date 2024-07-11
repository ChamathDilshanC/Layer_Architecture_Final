package lk.ijse.dao.custom;
import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.InventoryDetails;
import lk.ijse.entity.MealDetails;
import lk.ijse.entity.tm.OrderMealCart;

import java.sql.SQLException;

import java.util.List;

public interface OrderMealDAO extends SuperDAO {
    List<String> getAllMealNames() throws SQLException, ClassNotFoundException;

    MealDetails getMealIdByMealName(String mealName) throws SQLException, ClassNotFoundException;

    InventoryDetails getInventoryDetailsByMealId(String mealId) throws SQLException, ClassNotFoundException;

    boolean updateInventory(List<OrderMealCart> ordermealinfo) throws SQLException, ClassNotFoundException;
}
