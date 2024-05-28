package br.com.soc.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import br.com.soc.dao.ConnectionDao;
import br.com.soc.domain.ExameRealizadoKey;
import br.com.soc.domain.dto.ExameRealizadoDownload;
import br.com.soc.domain.dto.FiltroExameRealizado;
import br.com.soc.service.ExameRealizadoService;

public class ExameRealizadoServiceImpl implements ExameRealizadoService {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	public static Connection getConnection() throws Exception {
		return ConnectionDao.getConnection();
	}

	@Override
	public void saveExameRealizado(ExameRealizadoKey exameRealizadoKey) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		// NÃO PODE HAVER
		if (!exameRealizadoHoje(exameRealizadoKey)) {
			try {
				String sql = "INSERT INTO EXAME_REALIZADO VALUES (?,?,?)";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setLong(1, exameRealizadoKey.getFuncionarioId());
				ps.setLong(2, exameRealizadoKey.getExameId());
				ps.setTimestamp(3, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
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
	}

	private boolean exameRealizadoHoje(ExameRealizadoKey exameRealizadoKey) throws SQLException, Exception {
		int exameRealizado = 0;
		getConnection().setAutoCommit(false);
			try {
				String sql = "SELECT COUNT(CAST(dt_realizacao as DATE)) as COUNT "
						+ "		FROM exame_realizado WHERE 1=1 "
						+ "		AND cd_exame=? "
						+ "		AND cd_funcionario=? "
						+ "		AND CAST(dt_realizacao as DATE) = '" + LocalDate.now() + "'";
				
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setLong(1, exameRealizadoKey.getExameId());
				ps.setLong(2, exameRealizadoKey.getFuncionarioId());
				
				ResultSet rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						exameRealizado = rs.getInt("COUNT");
					}
				}
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				if (getConnection() != null) {
					getConnection().close();
				}
			}
		return exameRealizado > 0;
	}
	
	@Override
	public void downloadExameRealizado(FiltroExameRealizado filtroExameRealizado, File fileToDownload) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		List<ExameRealizadoDownload> exameRealizadoDownloads = new LinkedList<>();
				
		try {
			String sql = "SELECT e.cd_exame, e.nm_exame, er.dt_realizacao, f.cd_funcionario, f.nm_funcionario "
					+ "		FROM exame_realizado er "
					+ "		LEFT join funcionario f on f.cd_funcionario = er.cd_funcionario "
					+ "		JOIN exame e on e.cd_exame = er.cd_exame "
					+ "		WHERE CAST(er.dt_realizacao as date) BETWEEN "
					+ "		'" + filtroExameRealizado.getDtInicial() + "' AND "
					+ "		'" + filtroExameRealizado.getDtFinal() + "'  ";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ExameRealizadoDownload exameRealizadoDownload = new ExameRealizadoDownload();
					exameRealizadoDownload.setCdExame(rs.getLong("cd_exame"));
					exameRealizadoDownload.setNmExame(rs.getString("nm_exame"));
					
					Timestamp dtRealizacao = rs.getTimestamp("DT_REALIZACAO");
					exameRealizadoDownload.setDtRealizacao(dtRealizacao.toLocalDateTime());
					
					exameRealizadoDownload.setCdFuncionario(rs.getLong("cd_funcionario"));
					exameRealizadoDownload.setNmFuncionario(rs.getString("nm_funcionario"));
					exameRealizadoDownloads.add(exameRealizadoDownload);
				}
				// GERAR XLS
				CriarArquivoExcel criarArquivoExcel = new CriarArquivoExcel();
				criarArquivoExcel.criarArquivo(fileToDownload, exameRealizadoDownloads);
			}
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
	public void deletarExameRealizado(ExameRealizadoKey exameRealizadoKey) throws SQLException, Exception {
		getConnection().setAutoCommit(false);

		try {
			String sql = "DELETE FROM EXAME_REALIZADO WHERE CD_EXAME =? AND CD_FUNCIONARIO =?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, exameRealizadoKey.getExameId());
			ps.setLong(2, exameRealizadoKey.getFuncionarioId());
			System.out.println("deletarExameRealizado: " + sql);
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
	
}
