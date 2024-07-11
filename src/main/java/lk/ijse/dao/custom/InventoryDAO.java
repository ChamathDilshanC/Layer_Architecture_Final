package lk.ijse.dao.custom;
import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Inventory;
import lk.ijse.entity.tm.InventoryCart;

import java.sql.SQLException;
import java.util.List;


public interface InventoryDAO extends CrudDAO<Inventory> {

      boolean saveInventorySupplyInfo(List<InventoryCart> inventoryCart) throws SQLException, ClassNotFoundException;

      boolean saveInventorySupplyInfo(InventoryCart cart) throws SQLException, ClassNotFoundException;

      boolean updateInventory(List<InventoryCart> supplyinventoryinfo) throws SQLException, ClassNotFoundException;

}
