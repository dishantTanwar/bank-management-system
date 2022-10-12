package com.github.funds_transfer.repositories;

import com.github.funds_transfer.entities.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    Optional<Beneficiary> findByPhoneNumber(Integer phoneNumber);
    Boolean existsByPhoneNumber(Integer phoneNumber);

}
