package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ReservationserviceinfoDTO implements Serializable {

    private String ReservationID;
    private String ServiceID;


}
