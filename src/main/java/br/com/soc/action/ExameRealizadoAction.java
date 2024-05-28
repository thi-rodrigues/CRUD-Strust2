package br.com.soc.action;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import br.com.soc.domain.ExameRealizadoKey;
import br.com.soc.service.ExameRealizadoService;
import br.com.soc.service.impl.ExameRealizadoServiceImpl;
import lombok.Getter;
import lombok.Setter;

public class ExameRealizadoAction extends ActionSupport implements ModelDriven<ExameRealizadoKey> {

	private static final long serialVersionUID = -6659925652584240539L;

	@Getter
	@Setter
	private ExameRealizadoKey exameRealizadoKey = new ExameRealizadoKey();
	
	private ExameRealizadoService exameRealizadoService = new ExameRealizadoServiceImpl();

	@Override
	public ExameRealizadoKey getModel() {
		return exameRealizadoKey;
	}

	public String realizarExame() throws SQLException, Exception {
		exameRealizadoService.saveExameRealizado(exameRealizadoKey);
		return SUCCESS;
	}
	
	public String deletarExameRealizado() throws SQLException, Exception {
		exameRealizadoService.deletarExameRealizado(exameRealizadoKey);
		return SUCCESS;
	}

}
