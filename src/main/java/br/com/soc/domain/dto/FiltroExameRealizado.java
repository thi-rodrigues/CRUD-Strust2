package br.com.soc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroExameRealizado {
	
	private String dtInicial;
	private String dtFinal;

}
