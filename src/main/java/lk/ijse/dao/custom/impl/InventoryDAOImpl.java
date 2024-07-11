package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.InventoryDAO;
import lk.ijse.entity.Inventory;
import lk.ijse.entity.tm.InventoryCart;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {

    public  boolean update(List<InventoryCart> supplyinventoryinfo) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("UPDATE inventory SET Quantity = Quantity + ? WHERE InventoryID = ?", supplyinventoryinfo);

        }

    @Override
    public ObservableList<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultset = SQLUtil.execute("SELECT InventoryID FROM inventory");
        ObservableList<String> ids = FXCollections.observableArrayList();
        while (resultset.next()) {
            ids.add(resultset.getString("InventoryID"));
        }
        return ids;
    }

    @Override
    public Inventory searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultset = SQLUtil.execute("SELECT * FROM inventory WHERE InventoryID = ?", id);
        if (resultset.next()) {
            String inventoryID = resultset.getString("InventoryID");
            String mealID = resultset.getString("MealID");
            String name = resultset.getString("Name");
            int quantity = resultset.getInt("Quantity");
            int minimumStockLevel = resultset.getInt("MinimumStockLevel");
            String ingredients = resultset.getString("Ingredients");

            return new Inventory(inventoryID, mealID, name, quantity, minimumStockLevel, ingredients);
        }
        return null;
    }

    @Override
    public boolean save(Inventory entity) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtil.execute("INSERT INTO inventory VALUES(?, ?, ?, ?, ?, ?)",
                entity.getInventoryID(), entity.getMealID(), entity.getName(), entity.getQuantity(), entity.getMinimumStockLevel(), entity.getIngredients());
        if (!result) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the Inventory").show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(Inventory entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE inventory SET MealID = ?, Name = ?, Quantity = ?, MinimumStockLevel = ?, Ingredients = ? WHERE InventoryID = ?",
                entity.getMealID(), entity.getName(), entity.getQuantity(), entity.getMinimumStockLevel(), entity.getIngredients(), entity.getInventoryID());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Inventory searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Inventory> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean saveInventorySupplyInfo(List<InventoryCart> inventoryCart) throws SQLException, ClassNotFoundException {
        for (InventoryCart cart : inventoryCart) {
            boolean isSaved = saveInventorySupplyInfo(cart);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveInventorySupplyInfo(InventoryCart cart) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO inventory VALUES(?, ?, ?, ?, ?, ?)",
                cart.getInventoryID(), cart.getSupplierID(), cart.getSupplyQuantity(), cart.getDeliveryDate(), cart.getPricePerUnit(), cart.getTotalPrice());

    }

    @Override
    public boolean updateInventory(List<InventoryCart> supplyInventoryInfo) throws SQLException, ClassNotFoundException {
        for (InventoryCart cart : supplyInventoryInfo) {
            return SQLUtil.execute("UPDATE inventory SET Quantity = Quantity + ? WHERE InventoryID = ?",
                    cart.getSupplyQuantity(), cart.getInventoryID()
            );
        }
        return false;
    }
}