package entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Rustem.
 */
@Entity
public class Product {
    private Integer price;
    private int id;
    private Fuel fuelByIdFuel;
    private Integer idFuel;

    @Basic
    @Column(name = "price", nullable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(price, id);
    }

    @ManyToOne
    @JoinColumn(name = "id_fuel", referencedColumnName = "id")
    public Fuel getFuelByIdFuel() {
        return fuelByIdFuel;
    }

    public void setFuelByIdFuel(Fuel fuelByIdFuel) {
        this.fuelByIdFuel = fuelByIdFuel;
    }

    @Basic
    @Column(name = "id_fuel", nullable = true)
    public Integer getIdFuel() {
        return idFuel;
    }

    public void setIdFuel(Integer idFuel) {
        this.idFuel = idFuel;
    }
}
