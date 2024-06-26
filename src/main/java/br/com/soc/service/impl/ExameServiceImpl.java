package br.com.soc.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import br.com.soc.dao.ConnectionDao;
import br.com.soc.domain.Exame;
import br.com.soc.domain.ExameRealizado;
import br.com.soc.domain.Funcionario;
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
			String sql = "SELECT * FROM EXAME WHERE IC_ATIVO = 1 ORDER BY NM_EXAME LIMIT 20 ";
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
	public List<Exame> buscarExames(Exame exame) throws SQLException, Exception {
		List<Exame> exames = new LinkedList<>();
		getConnection().setAutoCommit(false);
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM EXAME WHERE 1=1 ");
			if (exame.getId() != null)
				sql.append(" AND CD_EXAME = " + exame.getId());
			
			if (exame.getNome() != null && !exame.getNome().equals(""))
				sql.append(" AND NM_EXAME = '" + exame.getNome() + "'");
			
			if (exame.getAtivo() != null && !exame.getAtivo().equals(2L))
				sql.append(" AND IC_ATIVO = " + exame.getAtivo());
			else if (exame.getAtivo() != null)
				sql.append(" AND IC_ATIVO IN (0, 1) ");

			sql.append(" LIMIT 20 ");
			
			PreparedStatement ps = getConnection().prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					exame.setId(rs.getLong("CD_EXAME"));
					exame.setNome(rs.getString("NM_EXAME"));
					exame.setAtivo(rs.getLong("IC_ATIVO"));
					exames.add(exame);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
		return exames;
	}
	
	@Override
	public Exame buscarExamePorId(Long exameId) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		Exame exame = new Exame();
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM EXAME WHERE 1=1 ");
			if (exameId != null)
				sql.append(" AND CD_EXAME = " + exameId);
			
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
		// NÃO DELETA EXAME SE ESTIVER EXAME REALIZADO
		if (!exameRealizado(exameId)) {
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
	}

	private Boolean exameRealizado(Long exameId) {
		int exameRealizado = 0;
		try {
			String sql = "SELECT COUNT(*) AS COUNT FROM EXAME_REALIZADO WHERE CD_EXAME=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, exameId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					exameRealizado = rs.getInt("COUNT");
				}
			}
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return exameRealizado > 0;
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
	
	@Override
	public List<ExameRealizado> buscarExamesRealizados() throws SQLException, Exception {
		List<ExameRealizado> exames = new LinkedList<>();
		getConnection().setAutoCommit(false);
		try {
			String sql = "SELECT e.*, f.*, er.* "
					+ "		FROM exame_realizado er "
					+ "		LEFT join funcionario f on f.cd_funcionario = er.cd_funcionario "
					+ "		JOIN exame e on e.cd_exame = er.cd_exame ";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Exame exame = new Exame();
					exame.setId(rs.getLong("CD_EXAME"));
					exame.setNome(rs.getString("NM_EXAME"));
					exame.setAtivo(rs.getLong("IC_ATIVO"));
					exame.setDsDetalheExame(rs.getString("DS_DETALHE_EXAME"));
					exame.setDsDetalheExame1(rs.getString("DS_DETALHE_EXAME1"));
					
					Funcionario funcionario = new Funcionario();
					funcionario.setId(rs.getLong("CD_FUNCIONARIO"));
					funcionario.setNome(rs.getString("NM_FUNCIONARIO"));
					
					Timestamp dtRealizacao = rs.getTimestamp("DT_REALIZACAO");
					
					ExameRealizado exameRealizado = new ExameRealizado(exame, funcionario, dtRealizacao.toLocalDateTime());
					exames.add(exameRealizado);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
		return exames;
	}
	
}
