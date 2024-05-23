package br.com.soc.service;

import java.sql.SQLException;

import br.com.soc.domain.ExameRealizadoKey;
import br.com.soc.domain.dto.FiltroExameRealizado;

public interface ExameRealizadoService {

	public void saveExameRealizado(ExameRealizadoKey exameRealizado) throws SQLException, Exception;

	public void downloadExameRealizado(FiltroExameRealizado filtroExameRealizado) throws SQLException, Exception;
	
}
