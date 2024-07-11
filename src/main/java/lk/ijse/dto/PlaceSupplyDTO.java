package lk.ijse.dto;

import lk.ijse.dto.tm.InventoryCartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceSupplyDTO implements Serializable {
    private List<InventoryCartDTO> supplyinventoryinfo;
}
