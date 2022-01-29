package com.processes.batchblocage.primary;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="client_bloqué")
@Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class ClientBloqué {
	@Id @GeneratedValue
	//@JsonProperty(access=Access.READ_ONLY)
	private long id;
	private String nom;
	private String prenom;
	private String telephone;
	@Enumerated(EnumType.STRING)
	private TypeClient type;
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	private String profession;
	private String pays_d_adresse;
	private String adresselegale;
	private String email;
	private String ville;
}
