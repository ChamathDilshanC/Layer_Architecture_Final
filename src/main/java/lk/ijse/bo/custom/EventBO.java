package lk.ijse.bo.custom;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EventDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.ReservationDTO;

import java.sql.SQLException;

public interface EventBO extends SuperBO {

    String getEventNextId() throws SQLException, ClassNotFoundException ;

    boolean saveEvent(EventDTO ev, ReservationDTO reservation, PaymentDTO payment) throws SQLException;
}
