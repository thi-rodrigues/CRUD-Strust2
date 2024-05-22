package br.com.soc.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class ExameRealizado {
	
	@EmbeddedId
	ExameRealizadoKey id;
	
	@ManyToOne
	@MapsId("exameId")
	@JoinColumn(name = "cd_exame")
	Exame exame;
	
	@ManyToOne
	@MapsId("funcionarioId")
	@JoinColumn(name = "cd_funcionario")
	Funcionario funcionario;
	
	@Column(name = "dt_realizacao")
	LocalDateTime dtRealizacao;
	

}
