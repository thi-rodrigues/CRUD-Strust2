package br.com.soc.service;

import java.io.File;
import java.sql.SQLException;

import br.com.soc.domain.ExameRealizadoKey;
import br.com.soc.domain.dto.FiltroExameRealizado;

public interface ExameRealizadoService {

	public void saveExameRealizado(ExameRealizadoKey exameRealizado) throws SQLException, Exception;

	public void downloadExameRealizado(FiltroExameRealizado filtroExameRealizado, File fileToDownload) throws SQLException, Exception;

	public void deletarExameRealizado(ExameRealizadoKey exameRealizadoKey) throws SQLException, Exception;
	
}
