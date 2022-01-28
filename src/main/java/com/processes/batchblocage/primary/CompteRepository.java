package com.processes.batchblocage.primary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.processes.batchblocage.primary.*;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	List<Compte> findByClient(Client clientID);
}
