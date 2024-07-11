package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceDTO implements Serializable {
    private String  Name;
    private String ServiceID;
    private double Price;


}
