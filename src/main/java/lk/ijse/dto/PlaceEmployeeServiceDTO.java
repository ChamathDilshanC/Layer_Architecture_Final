package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceEmployeeServiceDTO implements Serializable {
    List<EmployeeServiceDTO> employees;

}
