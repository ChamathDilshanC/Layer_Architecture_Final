package lk.ijse.bo.custom.impl.custom.forms.impl;

import lk.ijse.bo.custom.impl.custom.forms.LoginBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.impl.custom.forms.LoginFormDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class LoginBOImpl implements LoginBO {

    LoginFormDAO loginFormDAO = (LoginFormDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);

    @Override
    public ResultSet checkLogin(String UserID) throws SQLException, ClassNotFoundException {
        return loginFormDAO.checkLogin(UserID);
    }

    @Override
    public boolean updateLoginTimestamp(String UserID, Date date, Time time) throws SQLException, ClassNotFoundException {
        return loginFormDAO.updateLoginTimestamp(UserID, date, time);
    }
}
