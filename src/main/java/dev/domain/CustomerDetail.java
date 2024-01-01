package dev.domain;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer_detail")
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number format")
    @Column(name = "phone")
    private String phone;

    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "dob")
    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    @Column(name = "gender")
    private String gender;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Updated relationship to Order
    @OneToMany(mappedBy = "customerDetail", cascade = javax.persistence.CascadeType.ALL)
    private List<Order> orders;


}
