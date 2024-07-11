package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ReservationpackageinfoDAO;
import lk.ijse.entity.Reservationpackageinfo;

import java.sql.SQLException;
import java.util.List;

public class ReservationpackageinfoDAOImpl implements ReservationpackageinfoDAO {
    public boolean save(List<Reservationpackageinfo> reservationpackageinfo) throws SQLException, ClassNotFoundException {
        boolean isSaved = true;
        for (Reservationpackageinfo reservationpackageinfo1 : reservationpackageinfo) {
            boolean result = SQLUtil.execute("INSERT INTO reservation_package_info VALUES(?,?,?)",
                    reservationpackageinfo1.getReservationID(),
                    reservationpackageinfo1.getPackageID(),
                    reservationpackageinfo1.getCustomerID());
            if (!result) {
                isSaved = false;
                break;
            }
        }
        return isSaved;
    }
}
