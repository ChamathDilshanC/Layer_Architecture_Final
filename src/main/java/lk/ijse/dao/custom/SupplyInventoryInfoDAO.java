package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.tm.InventoryCart;
import java.sql.SQLException;
import java.util.List;

public interface SupplyInventoryInfoDAO  extends SuperDAO {
    boolean save(List<InventoryCart> supplyinventoryinfo) throws SQLException, ClassNotFoundException;
}
