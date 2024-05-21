package br.com.soc.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import br.com.soc.dao.ConnectionDao;
import br.com.soc.domain.Funcionario;
import br.com.soc.service.FuncionarioService;

public class FuncionarioServiceImpl implements FuncionarioService {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	public static Connection getConnection() throws Exception {
		return ConnectionDao.getConnection();
	}

	public List<Funcionario> listFuncionario() throws SQLException, Exception {
		getConnection().setAutoCommit(false);
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

	@Override
	public Funcionario listFuncionarioById(Long codFuncionario) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		Funcionario funcionario = new Funcionario();
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM FUNCIONARIO WHERE 1=1 ");
			if (codFuncionario != null)
				sql.append(" AND CD_FUNCIONARIO = " + codFuncionario);

			PreparedStatement ps = getConnection().prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					funcionario.setId(rs.getLong("CD_FUNCIONARIO"));
					funcionario.setNome(rs.getString("NM_FUNCIONARIO"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
		return funcionario;
	}

	@Override
	public void saveFuncionario(Funcionario funcionario) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		try {
			String sql = "INSERT INTO FUNCIONARIO VALUES (?,?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, null);
			ps.setString(2, funcionario.getNome());
			ps.executeUpdate();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	@Override
	public void deleteFuncionario(Long funcionarioId) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		try {
			String sql = "DELETE FROM FUNCIONARIO WHERE CD_FUNCIONARIO=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, funcionarioId);
			ps.executeUpdate();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void updateFuncionario(Funcionario funcionario) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		try {
			String sql = "UPDATE FUNCIONARIO SET NM_FUNCIONARIO=? WHERE CD_FUNCIONARIO=? ";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, funcionario.getNome());
			ps.setLong(2, funcionario.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			getConnection().rollback();
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

}
