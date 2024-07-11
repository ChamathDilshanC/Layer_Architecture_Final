package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationpackageinfoDTO implements Serializable {
    private  String ReservationID;
    private String PackageID;
    private String CustomerID;

}
