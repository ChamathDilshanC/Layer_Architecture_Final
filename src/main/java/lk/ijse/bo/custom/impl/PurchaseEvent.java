package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EventBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EventDAO;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EventDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.Event;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Reservation;

import java.sql.Connection;
import java.sql.SQLException;

public class PurchaseEvent implements EventBO {

    EventDAO eventDAO = (EventDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EVENT);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public String getEventNextId() throws SQLException, ClassNotFoundException {
        return eventDAO.getNextId();
    }
    @Override
    public  boolean saveEvent(EventDTO ev, ReservationDTO reservation, PaymentDTO payment) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isReservationSaved = reservationDAO.save(new Reservation(reservation.getReservationID(),reservation.getCheckInDate(),reservation.getCheckOutDate(),reservation.getReservationDate(),reservation.getDuration(),reservation.getNumberofGuests(),reservation.getStatus()));
            if (isReservationSaved) {
                boolean isEventSaved = eventDAO.save(new Event(ev.getEventID(),ev.getReservationID(),ev.getEventName(),ev.getEventDate(),ev.getNumberOfAttendees(),ev.getCustomerPhone(),ev.getHallNumber()));
                if (isEventSaved) {
                    boolean isPaymentSaved = paymentDAO.save(new Payment(payment.getPaymentId(),payment.getReservationId(),payment.getAmount(),payment.getDate(),payment.getMethod(),payment.getStatus()));
                    if (isPaymentSaved) {
                        connection.commit();
                        return true;
                    }
                }
                if (isEventSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
