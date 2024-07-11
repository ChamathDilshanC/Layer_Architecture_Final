package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrderMealBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.OrderMealDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.InventoryDetailsDTO;
import lk.ijse.dto.MealDetailsDTO;
import lk.ijse.dto.PlaceOrderMealDTO;
import lk.ijse.dto.tm.OrderMealCartDTO;
import lk.ijse.entity.InventoryDetails;
import lk.ijse.entity.MealDetails;
import lk.ijse.entity.tm.OrderMealCart;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderMeal implements OrderMealBO {

    OrderMealDAO orderMealDAO = (OrderMealDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERMEAL);

    @Override
    public List<String> getAllMealNames() throws SQLException, ClassNotFoundException {
        return orderMealDAO.getAllMealNames();
    }

    @Override
    public MealDetailsDTO getMealIdByMealName(String mealName) throws SQLException, ClassNotFoundException {
        MealDetails meal = orderMealDAO.getMealIdByMealName(mealName);
        return new MealDetailsDTO(meal.getMealId(),meal.getPrice());
    }

    @Override
    public InventoryDetailsDTO getInventoryDetailsByMealId(String mealId) throws SQLException, ClassNotFoundException {
        InventoryDetails ins =  orderMealDAO.getInventoryDetailsByMealId(mealId);
        return new InventoryDetailsDTO(ins.getInventoryID(),ins.getQuantity(),ins.getMinimumStockLevel(),ins.getIngredients());
    }

    @Override
    public boolean updateInventory(List<OrderMealCartDTO> ordermealinfo) throws SQLException, ClassNotFoundException {
        List<OrderMealCart> orderMealCartList = new ArrayList<>();
        for (OrderMealCartDTO dto : ordermealinfo) {
            orderMealCartList.add(convertToEntity(dto));
        }

        return orderMealDAO.updateInventory(orderMealCartList);
    }

    private OrderMealCart convertToEntity(OrderMealCartDTO dto) {
        return new OrderMealCart(
                dto.getMealId(),
                dto.getInventoryID(),
                dto.getMealPrice(),
                dto.getQuantity(),
                dto.getTotalPrice()
        );
    }


    @Override
    public boolean placeOrderMeal(PlaceOrderMealDTO placeOrderMeal) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            List<OrderMealCart> orderMealCartList = new ArrayList<>();
            for (OrderMealCartDTO dto : placeOrderMeal.getOrdermealinfo()) {
                orderMealCartList.add(convertToEntity(dto));
            }

            boolean isInventoryQtyUpdated = orderMealDAO.updateInventory(orderMealCartList);
            if (isInventoryQtyUpdated) {
                System.out.println("Inventory quantity updated");
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }


}
