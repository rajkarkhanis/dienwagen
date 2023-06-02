package com.dienwagen.auth.service;

import com.dienwagen.auth.dto.DealerDto;
import com.dienwagen.auth.entity.Dealer;
import com.dienwagen.auth.repository.DealerRepository;
import org.springframework.stereotype.Service;

@Service
public class DealerService {
    private final DealerRepository dealerRepository;
    private final RoleService roleService;

    public DealerService(DealerRepository dealerRepository, RoleService roleService) {
        this.dealerRepository = dealerRepository;
        this.roleService = roleService;
    }

    // Save a new dealer
    public DealerDto saveDealer(DealerDto dealerDto) {
        Dealer savedDealer = dealerRepository.save(convertToEntity(dealerDto));
        return convertToDto(savedDealer);
    }

    // Get any dealer
    public Dealer getAnyDealer() {
        return dealerRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow();
    }

    // Convert to Entity
    public Dealer convertToEntity(DealerDto dealerDto) {
        Dealer dealer = new Dealer();
        dealer.setName(dealerDto.getName());
        dealer.setContact(dealerDto.getContact());
        dealer.setEmail(dealerDto.getEmail());
        dealer.setAddress(dealerDto.getAddress());
        dealer.setRole(roleService.getRoleByName("ADMIN"));
        return dealer;
    }

    // Convert to DTO
    public DealerDto convertToDto(Dealer dealer) {
        DealerDto dto = new DealerDto();
        dto.setId(dealer.getId());
        dto.setName(dealer.getName());
        dto.setContact(dealer.getContact());
        dto.setEmail(dealer.getEmail());
        dto.setAddress(dealer.getAddress());
        return dto;
    }
}
