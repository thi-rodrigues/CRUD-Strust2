package br.com.soc.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExameRealizadoDownload {

	private Long cdExame;
	private String nmExame;
	private LocalDateTime dtRealizacao;
	private Long cdFuncionario;
	private String nmFuncionario;
}
