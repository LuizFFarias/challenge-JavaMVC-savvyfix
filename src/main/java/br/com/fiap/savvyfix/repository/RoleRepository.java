package br.com.fiap.savvyfix.repository;

import br.com.fiap.savvyfix.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
