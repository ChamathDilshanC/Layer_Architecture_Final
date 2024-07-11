package lk.ijse.dto;

import lk.ijse.dto.tm.OrderMealCartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderMealDTO implements Serializable {
    List<OrderMealCartDTO> ordermealinfo;
}
