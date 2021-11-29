package example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class specialData {
    specialBaggage[]baggages;
}
