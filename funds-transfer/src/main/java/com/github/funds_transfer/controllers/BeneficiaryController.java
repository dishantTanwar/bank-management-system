package com.github.funds_transfer.controllers;

import com.github.funds_transfer.entities.Beneficiary;
import com.github.funds_transfer.services.BeneficiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.util.Optional;

@Controller
@Validated
@RequestMapping("/beneficiaries")
public class BeneficiaryController {
    private final BeneficiaryService beneficiaryService;

    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<Beneficiary> getBeneficiary(
            @PathVariable
            @Digits(integer = 10, fraction = 0, message = "invalid phone-number")
                    int phoneNumber) {
        Optional<Beneficiary> beneficiary = beneficiaryService.getBeneficiary(phoneNumber);
        return beneficiary.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody @Valid Beneficiary b) {
        Beneficiary beneficiary = beneficiaryService.addBeneficiary(b);
        return ResponseEntity.ok().body(beneficiary);
    }

    @PutMapping
    public ResponseEntity<Beneficiary> updateBeneficiary(@RequestBody @Valid Beneficiary b) {
        Beneficiary beneficiary = beneficiaryService.updateBeneficiary(b);
        return ResponseEntity.ok().body(beneficiary);
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteBeneficiary(
            @PathVariable
            @Digits(integer = 10, fraction = 0, message = "invalid phone-number")
                    int phoneNumber) {
        boolean isDeleted = beneficiaryService.deleteBeneficiary(phoneNumber);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
