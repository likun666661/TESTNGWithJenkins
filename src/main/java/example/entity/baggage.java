package example.entity;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class baggage {
    private double weight;
    private double packingSize;
}
