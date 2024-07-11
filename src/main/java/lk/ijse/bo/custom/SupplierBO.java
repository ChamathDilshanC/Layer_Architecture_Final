package lk.ijse.bo.custom;


import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;

import lk.ijse.dto.SupplierDTO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {

    public List<String> getSupplierIds() throws SQLException, ClassNotFoundException;

    public SupplierDTO searchSupplierById(String id) throws SQLException, ClassNotFoundException;

    public boolean saveSupplier(SupplierDTO entity) throws SQLException, ClassNotFoundException;

    public boolean updateSupplier(SupplierDTO entity) throws SQLException, ClassNotFoundException;

    public String getNextSupplierId() throws SQLException, ClassNotFoundException;

    public SupplierDTO searchBySupplierPhone(String phone) throws SQLException, ClassNotFoundException;

    public ObservableList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    public boolean DeleteSupplier(String id) throws SQLException, ClassNotFoundException;

    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException;
}
