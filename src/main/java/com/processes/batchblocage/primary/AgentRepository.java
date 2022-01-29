package com.processes.batchblocage.primary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
	Agent findByIdClient(long id);
}
