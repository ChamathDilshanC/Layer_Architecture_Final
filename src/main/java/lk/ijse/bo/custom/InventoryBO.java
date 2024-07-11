package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.InventoryDTO;
import lk.ijse.dto.PlaceSupplyDTO;
import java.sql.SQLException;
import java.util.List;

public interface InventoryBO extends SuperBO {

    List<String> getIds() throws SQLException, ClassNotFoundException ;

    InventoryDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    boolean save(InventoryDTO entity) throws SQLException, ClassNotFoundException ;

    boolean update(InventoryDTO entity) throws SQLException, ClassNotFoundException ;

    String getNextId() throws SQLException, ClassNotFoundException ;

    InventoryDTO searchByPhone(String phone) throws SQLException, ClassNotFoundException ;

    ObservableList<InventoryDTO> getAll() throws SQLException, ClassNotFoundException ;

    boolean delete(String id) throws SQLException, ClassNotFoundException ;

    List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException ;

    boolean placeInventorySupply(PlaceSupplyDTO placeSupply) throws SQLException ;

}
