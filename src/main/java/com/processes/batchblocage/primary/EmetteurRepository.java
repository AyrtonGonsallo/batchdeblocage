package com.processes.batchblocage.primary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmetteurRepository extends JpaRepository<Emetteur, Long> {
	Emetteur findByIdClient(long id);
}
