package lk.ijse.dto;

import lk.ijse.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO extends Reservation implements Serializable {
    private String ReservationID;
    private Date CheckInDate;
    private Date CheckOutDate;
    private Date ReservationDate;
    private String Duration;
    private int NumberofGuests;
    private String Status;

}
