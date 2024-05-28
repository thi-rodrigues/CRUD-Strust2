package br.com.soc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;

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

	@Getter
	@Setter
	private InputStream inputStream;
	@Getter
	@Setter
	private long contentLength;
	@Getter
	@Setter
	private String fileName;

	@Override
	public FiltroExameRealizado getModel() {
		return filtroExameRealizado;
	}

	public String downloadExameRealizado() throws SQLException, Exception {
		String ldtNow = LocalDateTime.now().toString();
		String ldt = ldtNow.substring(0, ldtNow.length() - 3).replaceAll("\\D", "");
		File fileToDownload = new File("D:\\WS-BACKEND\\Struts2\\atividadeCRUDWEBStruts2\\Exames_Realizados_" + ldt +".xls");
		exameRealizadoService.downloadExameRealizado(filtroExameRealizado, fileToDownload);
		
		if (!fileToDownload.exists())
			fileToDownload.createNewFile();
		
		inputStream = new FileInputStream(fileToDownload);
		contentLength = fileToDownload.length();
		fileName = fileToDownload.getName();
		
		return SUCCESS;
	}
}
