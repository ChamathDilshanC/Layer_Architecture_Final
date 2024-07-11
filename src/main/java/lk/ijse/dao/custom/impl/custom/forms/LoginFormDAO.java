package lk.ijse.dao.custom.impl.custom.forms;

import lk.ijse.dao.SuperDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public interface LoginFormDAO extends SuperDAO {
     ResultSet checkLogin(String UserID) throws SQLException, ClassNotFoundException ;
     boolean updateLoginTimestamp (String UserID, Date date, Time time) throws SQLException, ClassNotFoundException;

}
