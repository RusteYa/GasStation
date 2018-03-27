package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Rustem.
 */
@Entity
public class Transaction {
    private int id;
    private Integer productAmount;
    private Integer productPrice;
    private Integer totalSum;
    private Timestamp date;
    private GasStation gasStationByIdGasStation;
    private Fuel fuelByIdProduct;
    private Integer idGasStation;
    private Integer idProduct;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_amount", nullable = true)
    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    @Basic
    @Column(name = "product_price", nullable = true)
    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "total_sum", nullable = true)
    public Integer getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Integer totalSum) {
        this.totalSum = totalSum;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                Objects.equals(productAmount, that.productAmount) &&
                Objects.equals(productPrice, that.productPrice) &&
                Objects.equals(totalSum, that.totalSum) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, productAmount, productPrice, totalSum, date);
    }

    @ManyToOne
    @JoinColumn(name = "id_gas_station", referencedColumnName = "id")
    public GasStation getGasStationByIdGasStation() {
        return gasStationByIdGasStation;
    }

    public void setGasStationByIdGasStation(GasStation gasStationByIdGasStation) {
        this.gasStationByIdGasStation = gasStationByIdGasStation;
    }

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    public Fuel getFuelByIdProduct() {
        return fuelByIdProduct;
    }

    public void setFuelByIdProduct(Fuel fuelByIdProduct) {
        this.fuelByIdProduct = fuelByIdProduct;
    }

    @Basic
    @Column(name = "id_gas_station", nullable = true)
    public Integer getIdGasStation() {
        return idGasStation;
    }

    public void setIdGasStation(Integer idGasStation) {
        this.idGasStation = idGasStation;
    }

    @Basic
    @Column(name = "id_product", nullable = true)
    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
}
