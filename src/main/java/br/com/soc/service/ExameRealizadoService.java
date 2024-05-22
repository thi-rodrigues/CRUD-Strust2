package br.com.soc.service;

import java.sql.SQLException;

import br.com.soc.domain.ExameRealizadoKey;

public interface ExameRealizadoService {

	public void saveExameRealizado(ExameRealizadoKey exameRealizado) throws SQLException, Exception;
	
}
