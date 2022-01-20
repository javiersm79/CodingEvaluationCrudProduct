package cl.falabella.boost.codingevaluation.data.entity;


import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Entity
@Table(name = "image_product")
@Setter
@Getter
@NoArgsConstructor
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @Column(name = "sku")
    private String sku;

    @NonNull
    @Column(name = "idproduct")
    private long idproduct;

    @NonNull
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "principal")
    private Boolean principal;
}
