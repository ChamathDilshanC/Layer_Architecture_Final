package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.entity.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {


    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT SupplierID FROM supplier");
        ObservableList<String> ids = FXCollections.observableArrayList();
        while (resultSet.next()) {
            ids.add(resultSet.getString("SupplierID"));
        }
        while (resultSet.next()) {
            ids.add(resultSet.getString("SupplierID"));
        }
        return ids;
    }

    @Override
    public Supplier searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE SupplierID = ?", id);
        if (resultSet.next()) {
            return new Supplier(
                    resultSet.getString("SupplierID"),
                    resultSet.getString("Name"),
                    resultSet.getString("PostalCode"),
                    resultSet.getString("EmailAddress"),
                    resultSet.getString("ContactNumber")
            );
        }
        return null;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO supplier VALUES(?,?,?,?,?)", entity.getSupplierId(), entity.getName(), entity.getPostalCode(), entity.getEmailAddress(), entity.getContactNumber());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET Name = ?, PostalCode = ?, EmailAddress = ?, ContactNumber = ? WHERE SupplierID = ?", entity.getName(), entity.getPostalCode(), entity.getEmailAddress(), entity.getContactNumber(), entity.getSupplierId());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT SupplierID FROM supplier ORDER BY SupplierID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString("SupplierID");
            int numericPart = Integer.parseInt(lastId.substring(3));
            numericPart++;
            return String.format("SUP%03d", numericPart);
        } else {
            return "SUP001";
        }
    }

    @Override
    public Supplier searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE ContactNumber = ?", phone);
        if (resultSet.next()) {
            return new Supplier(
                    resultSet.getString("SupplierID"),
                    resultSet.getString("Name"),
                    resultSet.getString("PostalCode"),
                    resultSet.getString("EmailAddress"),
                    resultSet.getString("ContactNumber")
            );
        }
        return null;
    }

    @Override
    public ObservableList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        while (resultSet.next()) {
            suppliers.add(new Supplier(
                    resultSet.getString("SupplierID"),
                    resultSet.getString("Name"),
                    resultSet.getString("PostalCode"),
                    resultSet.getString("EmailAddress"),
                    resultSet.getString("ContactNumber")
            ));
        }
        return suppliers;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        boolean deleted = SQLUtil.execute("DELETE FROM supplier WHERE SupplierID = ?", id);
        return deleted;

    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
