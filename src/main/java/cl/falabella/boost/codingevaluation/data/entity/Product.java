package cl.falabella.boost.codingevaluation.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private Float price;

    /*


     public Product() {

    }

    public Product(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
    }*/

}