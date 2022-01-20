package cl.falabella.boost.codingevaluation.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ProductBaseDTO {

    @NotNull(message="name cannot be null")
    @NotBlank(message="name cannot be blank")
    @Size(min = 3, max = 50, message="Character size between 3 - 50")
    private String name;

    @NotNull(message="brand cannot be null")
    @NotBlank(message="brand cannot be blank")
    @Size(min = 3, max = 50, message="Character size between 3 - 50")
    private String brand;

    @NotBlank(message="size cannot be blank")
    private String size;

    @NotNull(message="price cannot be null")
    @Min(value = 1, message="minimum price 1.00")
    @Max(value = 99999999, message="maximum price 99999999.00")
    private float price;

    @NotNull(message="principalimage cannot be null")
    @NotBlank(message="principalimage cannot be blank")
    private String principalimage;

    private List<String> otherimages;

}
