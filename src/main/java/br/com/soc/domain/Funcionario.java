package br.com.soc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -8179926310881552772L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_FUNCIONARIO")
	private Long id;

	@Column(name = "NM_FUNCIONARIO")
	private String nome;

}
