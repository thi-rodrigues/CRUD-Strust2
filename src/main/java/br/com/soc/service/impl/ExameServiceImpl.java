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
import br.com.soc.domain.Exame;
import br.com.soc.service.ExameService;

public class ExameServiceImpl implements ExameService {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	public static Connection getConnection() throws Exception {
		return ConnectionDao.getConnection();
	}

	public List<Exame> listExame() throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		List<Exame> exames = new LinkedList<>();
		try {
			String sql = "SELECT * FROM EXAME ORDER BY 1 DESC LIMIT 20 ";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Exame bean = new Exame();
					bean.setId(rs.getLong("CD_EXAME"));
					bean.setNome(rs.getString("NM_EXAME"));
					exames.add(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exames;
	}

	@Override
	public Exame listExameById(Long codExame) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		Exame exame = new Exame();
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM EXAME WHERE 1=1 ");
			if (codExame != null)
				sql.append(" AND CD_EXAME = " + codExame);

			PreparedStatement ps = getConnection().prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					exame.setId(rs.getLong("CD_EXAME"));
					exame.setNome(rs.getString("NM_EXAME"));
					exame.setAtivo(rs.getLong("IC_ATIVO"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
		return exame;
	}

	@Override
	public void saveExame(Exame exame) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		try {
			String sql = "INSERT INTO EXAME VALUES (?,?,?,?,?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, null);
			ps.setString(2, exame.getNome());
			ps.setLong(3, exame.getAtivo());
			ps.setString(4, null);
			ps.setString(5, null);
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
	public void deleteExame(Long exameId) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		try {
			String sql = "DELETE FROM EXAME WHERE CD_EXAME=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, exameId);
			ps.executeUpdate();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void updateExame(Exame exame) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		try {
			String sql = "UPDATE EXAME SET NM_EXAME=?, IC_ATIVO =? WHERE CD_EXAME=? ";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, exame.getNome());
			ps.setLong(2, exame.getAtivo());
			ps.setLong(3, exame.getId());
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
