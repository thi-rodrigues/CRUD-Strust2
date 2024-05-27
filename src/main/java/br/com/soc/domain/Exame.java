package br.com.soc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EXAME")
public class Exame {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CD_EXAME")	
	private Long id;
	
	@Column(name="NM_EXAME")
	private String nome;
	
	@Column(name="IC_ATIVO")
	private Long ativo;
	
	@Column(name="DS_DETALHE_EXAME")
	private String dsDetalheExame;
	
	@Column(name="DS_DETALHE_EXAME1")
	private String dsDetalheExame1;
	
	public Exame(Long id) {
		this.id = id;
	}
}
