package br.com.dao;

import java.util.List;
import br.com.domain.Funcionario;

public interface FuncionarioDAO {

	public void saveOrUpdateFuncionario(Funcionario user);
	public List<Funcionario> listFuncionario();
	public Funcionario listFuncionarioById(Long userId);
	public void deleteFuncionario(Long userId);
}
