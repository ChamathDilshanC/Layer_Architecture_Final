package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO implements Serializable {
    private  String RoomID;
    private String Type;
    private double PricePerNight;
    private String Status;


}
