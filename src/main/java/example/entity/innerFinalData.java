package example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class innerFinalData {
    private innerBaggage[] baggages0;
    private String airPortClass0;
    private String passengerClass0;
    private String specialUser;
}
