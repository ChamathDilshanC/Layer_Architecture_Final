package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplyInventoryInfoDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.tm.InventoryCartDTO;
import lk.ijse.entity.tm.InventoryCart;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SupplyInventoryInfoDAOImpl implements SupplyInventoryInfoDAO {

    public  boolean save(List<InventoryCart> supplyinventoryinfo) throws SQLException, ClassNotFoundException {

        for (InventoryCart inventoryCart : supplyinventoryinfo) {
            return SQLUtil.execute("INSERT INTO inventory_supplier_info VALUES(?,?,?,?,?,?)", inventoryCart.getInventoryID(), inventoryCart.getSupplierID(), inventoryCart.getSupplyQuantity(), inventoryCart.getDeliveryDate(), inventoryCart.getPricePerUnit(), inventoryCart.getTotalPrice());
        }
        return false;
    }
}
