package com.processes.batchblocage.primary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientBloquéRepository extends JpaRepository<ClientBloqué, Long> {
	
	boolean existsByPrenom(String prenom);
	boolean existsByNom(String nom);
}
