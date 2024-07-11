package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.InventoryBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.InventoryDAO;
import lk.ijse.dao.custom.SupplyInventoryInfoDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.InventoryDTO;
import lk.ijse.dto.PlaceSupplyDTO;
import lk.ijse.dto.tm.InventoryCartDTO;
import lk.ijse.entity.Inventory;
import lk.ijse.entity.tm.InventoryCart;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryBOImpl implements InventoryBO {

    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);
    SupplyInventoryInfoDAO supplyInventoryInfoDAO = (SupplyInventoryInfoDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLYINVENTORYINFO);

    @Override
    public ObservableList<String> getIds() throws SQLException, ClassNotFoundException {
        return (ObservableList<String>) inventoryDAO.getIds();
    }

    @Override
    public InventoryDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Inventory inventory = inventoryDAO.searchById(id);
        return new InventoryDTO(inventory.getInventoryID(), inventory.getMealID(), inventory.getName(), inventory.getQuantity(), inventory.getMinimumStockLevel(), inventory.getIngredients());
    }

    @Override
    public boolean save(InventoryDTO entity) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(entity.getInventoryID(), entity.getMealID(), entity.getName(), entity.getQuantity(), entity.getMinimumStockLevel(), entity.getIngredients()));
    }

    @Override
    public boolean update(InventoryDTO entity) throws SQLException, ClassNotFoundException {
        return inventoryDAO.update(new Inventory(entity.getInventoryID(), entity.getMealID(), entity.getName(), entity.getQuantity(), entity.getMinimumStockLevel(), entity.getIngredients()));
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return inventoryDAO.getNextId();
    }

    @Override
    public InventoryDTO searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<InventoryDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Inventory> all = inventoryDAO.getAll();
        ObservableList<InventoryDTO> allInventory = FXCollections.observableArrayList();
        for (Inventory i : all) {
            allInventory.add(new InventoryDTO(i.getInventoryID(), i.getMealID(), i.getName(), i.getQuantity(), i.getMinimumStockLevel(), i.getIngredients()));
        }
        return allInventory;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean placeInventorySupply(PlaceSupplyDTO placeSupply) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            List<InventoryCart> inventoryCartList = new ArrayList<>();
            for (InventoryCartDTO dto : placeSupply.getSupplyinventoryinfo()) {
                inventoryCartList.add(new InventoryCart(dto.getInventoryID(), dto.getSupplierID(), dto.getSupplyQuantity(), dto.getDeliveryDate(), dto.getPricePerUnit(), dto.getTotalPrice()));
            }

            boolean isSupplyInventoryInfoSaved = supplyInventoryInfoDAO.save(inventoryCartList);
            if (isSupplyInventoryInfoSaved) {
                boolean isInventoryQtyUpdated = inventoryDAO.updateInventory(inventoryCartList);
                if (isInventoryQtyUpdated) {
                    System.out.println("Inventory Updated");
                    connection.commit();
                    return true;
                } else {
                    System.err.println("Failed to update inventory quantity.");
                }
            } else {
                System.err.println("Failed to save supply inventory info.");
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            System.err.println("Exception during placeInventorySupply: " + e.getMessage());
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
