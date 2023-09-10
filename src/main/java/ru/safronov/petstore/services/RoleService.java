package ru.safronov.petstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.safronov.petstore.models.Role;
import ru.safronov.petstore.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Роль отсутствует"));
    }
}