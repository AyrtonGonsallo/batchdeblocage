package com.processes.batchblocage.primary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {
	Beneficiaire findByIdClient(long id);
}
