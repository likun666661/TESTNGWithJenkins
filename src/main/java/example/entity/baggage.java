package example.entity;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class baggage {
    //假装自己有了一些修改。
    private double weight;
    private double packingSize;
}
