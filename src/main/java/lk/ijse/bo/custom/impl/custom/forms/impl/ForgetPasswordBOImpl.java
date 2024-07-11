package lk.ijse.bo.custom.impl.custom.forms.impl;

import lk.ijse.bo.custom.impl.custom.forms.ForgetPasswordBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.impl.custom.forms.ForgetPasswordDAO;

import java.sql.SQLException;

public class ForgetPasswordBOImpl implements ForgetPasswordBO {

    ForgetPasswordDAO forgetPasswordDAO = (ForgetPasswordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FORGETPASSWORD);
    @Override
    public boolean checkForgetPasswordUserIDName(String userID, String name) throws SQLException, ClassNotFoundException {
        return forgetPasswordDAO.checkUserIDName(userID, name);
    }

    @Override
    public boolean updateForgetPassword(String userID, String password) throws SQLException, ClassNotFoundException {
        return forgetPasswordDAO.updatePassword(userID, password);
    }
}
