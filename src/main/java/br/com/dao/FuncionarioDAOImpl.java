package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import br.com.domain.Funcionario;

public class FuncionarioDAOImpl implements FuncionarioDAO {
	
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public static Connection getConnection() throws Exception {
		return ConnectionDao.getConnection();
	}

	/**
	 * Used to save or update a funcionario.
	 */
	@Override
	public void saveOrUpdateFuncionario(Funcionario funcionario) {
		try {
			session.saveOrUpdate(funcionario);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Used to delete a funcionario.
	 */
	@Override
	public void deleteFuncionario(Long funcionarioId) {
		try {
			Funcionario funcionario = (Funcionario) session.get(Funcionario.class, funcionarioId);
			session.delete(funcionario);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	public List<Funcionario> listFuncionario() {
		List<Funcionario> funcionarios = new LinkedList<>();
		try {
			String sql = "SELECT * FROM FUNCIONARIO ";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					Funcionario bean = new Funcionario();
					bean.setId(rs.getLong("CD_FUNCIONARIO"));
					bean.setNome(rs.getString("NM_FUNCIONARIO"));
					funcionarios.add(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

	/**
	 * Used to list a single funcionario by Id.
	 */
	@Override
	public Funcionario listFuncionarioById(Long funcionarioId) {
		Funcionario funcionario = null;
		try {
			funcionario = (Funcionario) session.get(Funcionario.class, funcionarioId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcionario;
	}

}
