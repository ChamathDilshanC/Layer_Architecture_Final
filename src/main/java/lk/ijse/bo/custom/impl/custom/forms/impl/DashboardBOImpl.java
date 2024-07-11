package lk.ijse.bo.custom.impl.custom.forms.impl;

import lk.ijse.bo.custom.impl.custom.forms.DashboardBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.impl.custom.forms.DashboardDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DashboardBOImpl implements DashboardBO {

    DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DASHBOARD);

    public String getDashboardUsername(String userId) throws SQLException, ClassNotFoundException {
        return dashboardDAO.getUsername(userId);
    }
    @Override
    public String getDashboardBestPackage() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getBestPackage();
    }

    @Override
    public int getDashboardAvailableRooms() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getAvailableRooms();
    }

    @Override
    public int getDashboardCheckInCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getCheckInCount();
    }

    @Override
    public int getDashboardCheckOutCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getCheckOutCount();
    }

    @Override
    public int getDashboardTotalReservations() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalReservations();
    }

    @Override
    public int getDashboardCustomerCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getCustomerCount();
    }

    @Override
    public double getDashboardTotalRevenue() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalRevenue();
    }

    @Override
    public List<String> getDashboardRoomIdsByCheckoutDate(Date checkoutDate) throws SQLException, ClassNotFoundException {
        return dashboardDAO.getRoomIdsByCheckoutDate(checkoutDate);
    }

    @Override
    public void updateDashboardRoomStatusToAvailable(String roomId) throws SQLException, ClassNotFoundException {
        dashboardDAO.updateRoomStatusToAvailable(roomId);
    }

    @Override
    public ResultSet setDashboardEmployeeDetails(String UserId) throws SQLException, ClassNotFoundException {
        return dashboardDAO.setEmployeeDetails(UserId);
    }
}
