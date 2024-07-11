package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableReservationDTO extends ReservationDTO implements Serializable {
    private String ReservationID;
    private Date CheckInDate;
    private Date CheckOutDate;
    private Date ReservationDate;
    private String Duration;
    private int NumberofGuests;
    private String Status;



}
