package com.processes.batchblocage.primary;

import java.util.Date;

import javax.persistence.*;

import lombok.*;
@Entity
@Table(name="Compte")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Compte {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private long idCompte;
	private double montant;
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;
	private Date date_ouverture;
	@Enumerated(EnumType.STRING)
	private TypeCompte type;
}
