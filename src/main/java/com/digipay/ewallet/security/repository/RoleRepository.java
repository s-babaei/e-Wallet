package com.digipay.ewallet.security.repository;



import com.digipay.ewallet.security.models.ERole;
import com.digipay.ewallet.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
