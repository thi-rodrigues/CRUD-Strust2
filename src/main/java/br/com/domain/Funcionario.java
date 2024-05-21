package br.com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="FUNCIONARIO")
public class Funcionario {

	@Id
	@GeneratedValue
	@Column(name="CD_FUNCIONARIO")	
	private Long id;
	
	@Column(name="NM_FUNCIONARIO")
	private String nome;
	
}
