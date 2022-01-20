package cl.falabella.boost.codingevaluation.client.controllers;


import cl.falabella.boost.codingevaluation.business.services.ProductService;
import cl.falabella.boost.codingevaluation.business.utils.CustomUtil;
import cl.falabella.boost.codingevaluation.client.dto.ProductBaseDTO;
import cl.falabella.boost.codingevaluation.client.dto.ProductDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value="/api/v1")
public class ProductController {
    private final CustomUtil customtuils = new CustomUtil();

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResponseEntity<List<ProductDTO>> getAllProdcuts(){
        List<ProductDTO> products =this.productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/products/{sku}", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResponseEntity<ProductDTO> getAllProdcuts(@PathVariable String sku){
        ProductDTO productDTO = this.productService.getProductBySKU(sku);
        return productDTO.getSku() != null ? ResponseEntity.ok(productDTO) : ResponseEntity.notFound().build();
    }


    @RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();

        // Validate sku format
        if (!this.customtuils.validSKU(productDTO.getSku())) return ResponseEntity.badRequest().body("Not Valid SKU format");

        // Validate if sku exists
        if (this.productService.countProductsBySku(productDTO.getSku()) > 0) return ResponseEntity.badRequest().body("SKU already registerted");

        // Validate url format in  principal image
        if (!this.customtuils.validURL(productDTO.getPrincipalimage())) return ResponseEntity.badRequest().body("Not Valid URL in principalimage");

        // Validate url format in  other images
        for (String url : productDTO.getOtherimages()) {
            if (!this.customtuils.validURL(url)) return ResponseEntity.badRequest().body("Not Valid URL of one o more in otherimages");
        }

        this.productService.createProduct(productDTO);

        return ResponseEntity.ok(gson.toJson(productDTO));

    }

    @RequestMapping(value = "/products/{sku}", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.PUT})
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductBaseDTO productDTO, @PathVariable String sku) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();

        // Validate sku format
        if (!this.customtuils.validSKU(sku)) return ResponseEntity.badRequest().body("Not Valid SKU format");

        // Validate if sku exists
        if (this.productService.countProductsBySku(sku) == 0) return ResponseEntity.badRequest().body("SKU not found");

        // Validate url format in  principal image
        if (!this.customtuils.validURL(productDTO.getPrincipalimage())) return ResponseEntity.badRequest().body("Not Valid URL in principalimage");

        // Validate url format in  other images
        for (String url : productDTO.getOtherimages()) {
            if (!this.customtuils.validURL(url)) return ResponseEntity.badRequest().body("Not Valid URL of one o more in otherimages");
        }

        this.productService.updateProduct(sku, productDTO);

        return ResponseEntity.ok(gson.toJson(productDTO));

    }

    @RequestMapping(value = "/products/{sku}", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.DELETE})
    public ResponseEntity<?> deleteProduct(@PathVariable String sku) {

        // Validate sku format
        if (!this.customtuils.validSKU(sku)) return ResponseEntity.badRequest().body("Not Valid SKU format");

        // Validate if sku exists
        if (this.productService.countProductsBySku(sku) == 0) return ResponseEntity.badRequest().body("SKU not found");

        this.productService.deleteProduct(sku);

        return ResponseEntity.ok("Product with SKU: " + sku + " DELETED");

    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
