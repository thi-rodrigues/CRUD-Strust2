package br.com.soc.action;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import br.com.soc.domain.dto.FiltroExameRealizado;
import br.com.soc.service.ExameRealizadoService;
import br.com.soc.service.impl.ExameRealizadoServiceImpl;
import lombok.Getter;
import lombok.Setter;

public class FiltroExameRealizadoAction extends ActionSupport implements ModelDriven<FiltroExameRealizado> {

	private static final long serialVersionUID = -525097741700218454L;
	
	@Getter
	@Setter
	private FiltroExameRealizado filtroExameRealizado = new FiltroExameRealizado();
	
	private ExameRealizadoService exameRealizadoService = new ExameRealizadoServiceImpl();
	
	@Override
	public FiltroExameRealizado getModel() {
		return filtroExameRealizado;
	}


	public String downloadExameRealizado() throws SQLException, Exception {
		System.out.println(filtroExameRealizado);
		exameRealizadoService.downloadExameRealizado(filtroExameRealizado);
		return SUCCESS;
	}
}
