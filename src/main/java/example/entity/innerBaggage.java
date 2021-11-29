package example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class innerBaggage {
    private Integer length;
    private Integer width;
    private Integer height;
    private Integer weight;
}
