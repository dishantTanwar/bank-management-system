package com.github.funds_transfer.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "Beneficiaries")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id = (long) -1;

    @NotEmpty(message = "beneficiary name cannot be empty")
    String name;

    @Positive @Digits(message = "phone number should have 10 digits", integer = 10, fraction = 0)
    Integer phoneNumber;

    @Positive
    Long accountNumber;

    @Pattern(message = "invalid ifsc", regexp = "[A-Z]*[0-9]*")
    @NotNull(message = "ifsc cannot be null")
    String ifsc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Beneficiary that = (Beneficiary) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
