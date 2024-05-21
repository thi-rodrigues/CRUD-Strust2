package br.com.soc.service;

import java.sql.SQLException;
import java.util.List;

import br.com.soc.domain.Funcionario;

public interface FuncionarioService {

	public void saveFuncionario(Funcionario funcionario) throws SQLException, Exception;
	
	public List<Funcionario> listFuncionario() throws SQLException, Exception;
	
	public Funcionario listFuncionarioById(Long funcionarioId) throws SQLException, Exception;
	
	public void deleteFuncionario(Long funcionarioId) throws SQLException, Exception;;
	
	public void updateFuncionario(Funcionario funcionario) throws SQLException, Exception;
}
