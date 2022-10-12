package com.github.funds_transfer.services;

import com.github.funds_transfer.entities.Beneficiary;
import com.github.funds_transfer.repositories.BeneficiaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    public BeneficiaryService(BeneficiaryRepository repository) {
        this.beneficiaryRepository = repository;
    }

    //CRUD
    // Add Beneficiary
    @Transactional
    public Beneficiary addBeneficiary(Beneficiary b) {
        return beneficiaryRepository.save(b);
    }

    // Get Beneficiary
    public Optional<Beneficiary> getBeneficiary(int phoneNumber) {
        return beneficiaryRepository.findByPhoneNumber(phoneNumber);
    }

    // Update Beneficiary
    @Transactional
    public Beneficiary updateBeneficiary(Beneficiary b) {
        return beneficiaryRepository.save(b);
    }

    // Delete Beneficiary
    @Transactional
    public boolean deleteBeneficiary(int phoneNumber) {
        try {
            Optional<Beneficiary> b = beneficiaryRepository.findByPhoneNumber(phoneNumber);
            if(b.isEmpty()) {
                return false;
            }
            beneficiaryRepository.delete(b.get());
            return true;
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return false;
    }

}
