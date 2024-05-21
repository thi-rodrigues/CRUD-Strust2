package br.com.soc.service;

import java.sql.SQLException;
import java.util.List;

import br.com.soc.domain.Exame;

public interface ExameService {

	public void saveExame(Exame exame) throws SQLException, Exception;
	
	public List<Exame> listExame() throws SQLException, Exception;
	
	public Exame listExameById(Long exameId) throws SQLException, Exception;
	
	public void deleteExame(Long exameId) throws SQLException, Exception;;
	
	public void updateExame(Exame funcionario) throws SQLException, Exception;
}
