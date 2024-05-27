package br.com.soc.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExameRealizado {
	
	@EmbeddedId
	private ExameRealizadoKey id;
	
	@ManyToOne
	@MapsId("exameId")
	@JoinColumn(name = "cd_exame")
	private Exame exame;
	
	@ManyToOne
	@MapsId("funcionarioId")
	@JoinColumn(name = "cd_funcionario")
	private Funcionario funcionario;
	
	@Column(name = "DT_REALIZACAO")
	private LocalDateTime dtRealizacao;
	
	public ExameRealizado(Exame exame, Funcionario funcionario, LocalDateTime now) {
		this.exame = exame;
		this.funcionario = funcionario;
		this.dtRealizacao = now;
	}

}
