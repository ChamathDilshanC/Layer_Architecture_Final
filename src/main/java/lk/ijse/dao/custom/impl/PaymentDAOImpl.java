package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.entity.Payment;

import java.sql.*;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT PaymentID FROM payment ORDER BY PaymentID DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString("PaymentID");
        } else {
            return null;
        }
    }
    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Payment searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ?, ?, ?)", entity.getPaymentId(), entity.getReservationId(), entity.getAmount(), entity.getDate(), entity.getMethod(), entity.getStatus());
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Payment searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Payment> getAll() throws SQLException, ClassNotFoundException {
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
}
