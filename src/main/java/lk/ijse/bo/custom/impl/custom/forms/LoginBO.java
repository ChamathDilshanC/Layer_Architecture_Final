package lk.ijse.bo.custom.impl.custom.forms;

import lk.ijse.bo.SuperBO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public interface LoginBO extends SuperBO {
    ResultSet checkLogin(String UserID) throws SQLException, ClassNotFoundException ;
    boolean updateLoginTimestamp (String UserID, Date date, Time time) throws SQLException, ClassNotFoundException;
}
