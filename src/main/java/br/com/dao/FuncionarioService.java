package br.com.dao;

import java.sql.SQLException;
import java.util.List;
import br.com.domain.Funcionario;

public interface FuncionarioService {

	public void saveFuncionario(Funcionario user) throws SQLException, Exception;
	
	public List<Funcionario> listFuncionario() throws SQLException, Exception;
	
	public Funcionario listFuncionarioById(Long userId) throws SQLException, Exception;
	
	public void deleteFuncionario(Long userId) throws SQLException, Exception;;
	
	public void updateFuncionario(Funcionario funcionario) throws SQLException, Exception;
}
