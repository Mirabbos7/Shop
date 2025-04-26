package main.entity;

import jakarta.persistence.*;
import main.User;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreatedDate
    private LocalDateTime purchaseDate;

    private int totalAmount;

    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id")
    main.entity.User user;

    public Purchase(Long id, String name, LocalDateTime purchaseDate, int totalAmount, String paymentMethod, main.entity.User user) {
        this.id = id;
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.user = user;
    }

    public Purchase(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public main.entity.User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return totalAmount == purchase.totalAmount && Objects.equals(id, purchase.id) && Objects.equals(name, purchase.name) && Objects.equals(purchaseDate, purchase.purchaseDate) && Objects.equals(paymentMethod, purchase.paymentMethod) && Objects.equals(user, purchase.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, purchaseDate, totalAmount, paymentMethod, user);
    }

    @Override
    public String toString() {
        return "main.Purchase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", totalAmount=" + totalAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", user=" + user +
                '}';
    }
}
