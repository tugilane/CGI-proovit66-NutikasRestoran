package com.org.NutikasRestoran_service.laud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LaudRepository extends JpaRepository<Laud, Long> {}
