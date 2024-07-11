package lk.ijse.dao;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    List<String> getIds() throws SQLException, ClassNotFoundException;
    T searchById(String id) throws SQLException, ClassNotFoundException;
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException, ClassNotFoundException;
    T searchByPhone(String phone) throws SQLException, ClassNotFoundException;
    ObservableList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean Delete(String id) throws SQLException, ClassNotFoundException;
    List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException;

}
