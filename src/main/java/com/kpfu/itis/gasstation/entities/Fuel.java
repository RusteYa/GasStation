package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "fuels")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 20)
    private String name;

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Product> productsForFuel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductsForFuel() {
        return productsForFuel;
    }

    public void setProductsForFuel(List<Product> productsForFuel) {
        this.productsForFuel = productsForFuel;
    }
}
