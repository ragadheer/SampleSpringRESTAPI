package com.jdpin.api.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JDPINUserRepository extends JpaRepository<User, Long> {

    User findByUsername(final String username);
}
