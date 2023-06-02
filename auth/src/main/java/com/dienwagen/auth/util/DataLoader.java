package com.dienwagen.auth.util;

import lombok.RequiredArgsConstructor;
import com.dienwagen.auth.entity.Dealer;
import com.dienwagen.auth.entity.Role;
import com.dienwagen.auth.repository.DealerRepository;
import com.dienwagen.auth.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final DealerRepository dealerRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role dealerRole = new Role();
        dealerRole.setName("DEALER");

        Role customerRole = new Role();
        customerRole.setName("CUSTOMER");

        roleRepository.save(dealerRole);
        roleRepository.save(customerRole);

        if (dealerRepository.count() < 1) {
            Dealer dealer = new Dealer();
            dealer.setName("Daniel LaRusso");
            dealer.setAddress("North Hollywood, Los Angeles, CA");
            dealer.setContact("+1(818) 763-0932");
            dealer.setEmail("daniel@larussoauto.com");
            dealerRepository.save(dealer);
        }
    }
}
