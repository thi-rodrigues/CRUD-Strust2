package br.com.soc.service;

import java.sql.SQLException;
import java.util.List;

import br.com.soc.domain.Exame;
import br.com.soc.domain.ExameRealizado;

public interface ExameService {

	public void saveExame(Exame exame) throws SQLException, Exception;
	
	public List<Exame> listExame() throws SQLException, Exception;
	
	public List<Exame> buscarExames(Exame exame) throws SQLException, Exception;
	
	public Exame buscarExamePorId(Long exameId) throws SQLException, Exception;
	
	public void deleteExame(Long exameId) throws SQLException, Exception;;
	
	public void updateExame(Exame funcionario) throws SQLException, Exception;

	List<ExameRealizado> buscarExamesRealizados() throws SQLException, Exception;
}
