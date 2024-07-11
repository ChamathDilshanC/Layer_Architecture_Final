package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ObservableList<String> getSupplierIds() throws SQLException, ClassNotFoundException {
        return FXCollections.observableArrayList(supplierDAO.getIds());
    }

    @Override
    public SupplierDTO searchSupplierById(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchById(id);
        if (supplier == null) {
            return null;
        }
        return new SupplierDTO(supplier.getSupplierId(), supplier.getName(), supplier.getPostalCode(), supplier.getEmailAddress(), supplier.getContactNumber());
    }

    @Override
    public boolean saveSupplier(SupplierDTO entity) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(entity.getSupplierId(), entity.getName(), entity.getPostalCode(), entity.getEmailAddress(), entity.getContactNumber()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO entity) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(entity.getSupplierId(), entity.getName(), entity.getPostalCode(), entity.getEmailAddress(), entity.getContactNumber()));
    }

    @Override
    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getNextId();
    }

    @Override
    public SupplierDTO searchBySupplierPhone(String phone) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchByPhone(phone);
        return new SupplierDTO(supplier.getSupplierId(), supplier.getName(), supplier.getPostalCode(), supplier.getEmailAddress(), supplier.getContactNumber());
    }

    @Override
    public ObservableList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        List<Supplier> all = supplierDAO.getAll();
        List<SupplierDTO> allDTO = new ArrayList<>();
        for (Supplier s : all) {
            allDTO.add(new SupplierDTO(s.getSupplierId(), s.getName(), s.getPostalCode(), s.getEmailAddress(), s.getContactNumber()));
        }
        return FXCollections.observableArrayList(allDTO);
    }

    @Override
    public boolean DeleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.Delete(id);
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return new ArrayList<>();
    }
}
