package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderMealDAO;
import lk.ijse.entity.InventoryDetails;
import lk.ijse.entity.MealDetails;
import lk.ijse.entity.tm.OrderMealCart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMealDAOImpl implements OrderMealDAO {

    public  List<String> getAllMealNames() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Name FROM meal ORDER BY Name;");
        List<String> mealNames = new ArrayList<>();
        while (rst.next()) {
            mealNames.add(rst.getString("Name"));
        }
        return mealNames;
    }

    public MealDetails getMealIdByMealName(String mealName) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT MealID, Price FROM meal WHERE Name = ?", mealName);
        if (rst.next()) {
            return new MealDetails(
                    rst.getString("MealID"),
                    rst.getDouble("Price")
            );
        }
        return null;
    }
    public InventoryDetails getInventoryDetailsByMealId(String mealId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT i.InventoryID, i.Quantity, i.MinimumStockLevel, i.Ingredients " +
                "FROM inventory i JOIN meal m ON i.MealID = m.MealID " +
                "WHERE m.MealID = ?", mealId);
        if (rst.next()) {
            return new InventoryDetails(
                    rst.getString("InventoryID"),
                    rst.getInt("Quantity"),
                    rst.getInt("MinimumStockLevel"),
                    rst.getString("Ingredients")
            );
        }
        return null;
    }

    @Override
    public boolean updateInventory(List<OrderMealCart> ordermealinfo) throws SQLException, ClassNotFoundException {
        for (OrderMealCart orderMealCart : ordermealinfo) {
            boolean isUpdated = SQLUtil.execute("UPDATE inventory SET Quantity = Quantity - ? WHERE InventoryID = ?",
                    orderMealCart.getQuantity(), orderMealCart.getInventoryID());
            if (!isUpdated) {
                return false;
            }
        }
        return true;
    }
}
