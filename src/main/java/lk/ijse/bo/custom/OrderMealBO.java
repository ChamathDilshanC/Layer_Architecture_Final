package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.InventoryDetailsDTO;
import lk.ijse.dto.MealDetailsDTO;
import lk.ijse.dto.PlaceOrderMealDTO;
import lk.ijse.dto.tm.OrderMealCartDTO;
import java.sql.SQLException;
import java.util.List;

public interface OrderMealBO  extends SuperBO {

    List<String> getAllMealNames() throws SQLException, ClassNotFoundException;

    MealDetailsDTO getMealIdByMealName(String mealName) throws SQLException, ClassNotFoundException;

    InventoryDetailsDTO getInventoryDetailsByMealId(String mealId) throws SQLException, ClassNotFoundException;

    boolean updateInventory(List<OrderMealCartDTO> ordermealinfo) throws SQLException, ClassNotFoundException;

    boolean placeOrderMeal(PlaceOrderMealDTO placeOrderMeal) throws SQLException ;

}
