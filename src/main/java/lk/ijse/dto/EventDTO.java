package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDTO  implements Serializable {
    private String EventID;
    private String ReservationID;
    private String EventName;
    private Date EventDate;
    private int NumberOfAttendees;
    private int CustomerPhone;
    private int HallNumber;
}
