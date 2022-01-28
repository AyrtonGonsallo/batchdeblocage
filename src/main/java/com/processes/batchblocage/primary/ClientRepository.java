package com.processes.batchblocage.primary;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client,Long> {
	@Query("select c from Client c where c.idClient=:idclient")
	Client findWithIdClient(@Param("idclient")long id);
	
	Client findByIdClient(long id);
}
