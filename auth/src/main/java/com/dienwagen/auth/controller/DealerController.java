package com.dienwagen.auth.controller;

import com.dienwagen.auth.dto.DealerDto;
import com.dienwagen.auth.service.DealerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dealers")
public class DealerController {
    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping
    public ResponseEntity<DealerDto> saveDealer(@RequestBody DealerDto dealerDto) {
        return ResponseEntity.ok(dealerService.saveDealer(dealerDto));
    }
}
