package cl.falabella.boost.codingevaluation.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ProductDTO extends ProductBaseDTO {

    @NotNull(message="SKU cannot be null")
    @NotBlank(message="SKU cannot be blank")
    private String sku;



    /*public ProductDTO(String sku, String name, String brand, String size, float price, String principalimage, List<String> otherimages) {
        this.sku = sku;
        this.setName(name);
        this.setBrand(brand);
        this.setSize(size);
        this.setPrice(price);
        this.setPrincipalimage(principalimage);
        this.setOtherimages(otherimages);
    }*/

}
