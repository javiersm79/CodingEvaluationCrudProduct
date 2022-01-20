package cl.falabella.boost.codingevaluation.business.services;

import cl.falabella.boost.codingevaluation.client.dto.ProductBaseDTO;
import cl.falabella.boost.codingevaluation.client.dto.ProductDTO;
import cl.falabella.boost.codingevaluation.data.entity.ImageProduct;
import cl.falabella.boost.codingevaluation.data.entity.Product;
import cl.falabella.boost.codingevaluation.data.repository.ImageProductRepository;
import cl.falabella.boost.codingevaluation.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageProductRepository imageproductRepository;

    public int countProductsBySku(String sku) {
       return this.productRepository.countProductBySku(sku);

    }

    public List<ProductDTO> getAllProducts() {
        List<Product> productlist = (List<Product>) this.productRepository.findAll();
        List<ProductDTO> productDTOlist = new ArrayList<>();

        for (Product p : productlist) {
            ProductDTO pdto = new ProductDTO();
            pdto.setSku(p.getSku());
            pdto.setName(p.getName());
            pdto.setBrand(p.getBrand());
            pdto.setSize(p.getSize());
            pdto.setPrice(p.getPrice());
            List<ImageProduct> imagesOfProduct = this.imageproductRepository.findImageProductBySku(p.getSku());
            pdto.setPrincipalimage(this.findMainImageOfProduct(imagesOfProduct));
            pdto.setOtherimages(this.findOtherImageOfProduct(imagesOfProduct));
            productDTOlist.add(pdto);
        }
        return productDTOlist;
    }

    public ProductDTO getProductBySKU(String sku) {
        Optional<Product> p = this.productRepository.findProductBySku(sku);
        ProductDTO pdto = new ProductDTO();
        if (p.isPresent()) {
            pdto.setSku(p.get().getSku());
            pdto.setName(p.get().getName());
            pdto.setBrand(p.get().getBrand());
            pdto.setSize(p.get().getSize());
            pdto.setPrice(p.get().getPrice());
            List<ImageProduct> imagesOfProduct = this.imageproductRepository.findImageProductBySku(p.get().getSku());
            pdto.setPrincipalimage(this.findMainImageOfProduct(imagesOfProduct));
            pdto.setOtherimages(this.findOtherImageOfProduct(imagesOfProduct));
        }
        return pdto;



    }

    public Boolean createProduct(ProductDTO productDTO) {
        Product product = this.saveProduct(productDTO);
        this.saveImagesProduct(product.getId(), productDTO);
        return true;
    }

    public void deleteProduct(String sku) {
        this.productRepository.deleteImageProductBySku(sku);
        this.imageproductRepository.deleteImageProductBySku(sku);
        return;
    }

    public Boolean updateProduct(String sku, ProductBaseDTO productDTO) {
        Optional<Product> productfinded = this.productRepository.findProductBySku(sku);
        Product productupdated = new Product();
        productupdated.setId(productfinded.get().getId());
        productupdated.setSku(sku);
        productupdated.setName(productDTO.getName());
        productupdated.setBrand(productDTO.getBrand());
        productupdated.setSize(productDTO.getSize());
        productupdated.setPrice(productDTO.getPrice());
        this.productRepository.save(productupdated);
        this.imageproductRepository.deleteImageProductBySku(sku);
        ProductDTO prdtoimage = new ProductDTO();
        prdtoimage.setSku(sku);
        prdtoimage.setPrincipalimage(productDTO.getPrincipalimage());
        prdtoimage.setOtherimages(productDTO.getOtherimages());
        this.saveImagesProduct(productupdated.getId(), prdtoimage);
        return true;
    }

    private Product saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setSku(productDTO.getSku());
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setSize(productDTO.getSize());
        product.setPrice(productDTO.getPrice());
        return this.productRepository.save(product);

    }


    private void saveImagesProduct(long idproduct, ProductDTO productDTO) {
        List<ImageProduct> images = new ArrayList<>();

        ImageProduct mainImage = new ImageProduct();

        mainImage.setIdproduct(idproduct);
        mainImage.setSku(productDTO.getSku());
        mainImage.setUrl(productDTO.getPrincipalimage());
        mainImage.setPrincipal(true);

        images.add(mainImage);

        for (String url : productDTO.getOtherimages()) {
            ImageProduct otherimg = new ImageProduct();
            otherimg.setIdproduct(idproduct);
            otherimg.setSku(productDTO.getSku());
            otherimg.setUrl(url);
            otherimg.setPrincipal(false);
            images.add(otherimg);
        }
        this.imageproductRepository.saveAll(images);

        return;
    }

    private String findMainImageOfProduct (List<ImageProduct> imagesOfProduct) {
        ImageProduct mainImage = imagesOfProduct.stream()
                .filter(mainimg -> mainimg.getPrincipal()).findAny().orElse(null);
        return mainImage.getUrl();

    }

    private List<String> findOtherImageOfProduct (List<ImageProduct> imagesOfProduct) {
        return imagesOfProduct.stream()
                .map(ImageProduct::getUrl).collect(Collectors.toList());

    }



}
