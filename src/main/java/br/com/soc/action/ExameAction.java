package br.com.soc.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import br.com.soc.domain.Exame;
import br.com.soc.domain.ExameRealizado;
import br.com.soc.domain.ExameRealizadoKey;
import br.com.soc.domain.Funcionario;
import br.com.soc.service.ExameService;
import br.com.soc.service.FuncionarioService;
import br.com.soc.service.impl.ExameServiceImpl;
import br.com.soc.service.impl.FuncionarioServiceImpl;
import lombok.Getter;
import lombok.Setter;

public class ExameAction extends ActionSupport implements ModelDriven<Exame> {

	private static final long serialVersionUID = -6659925652584240539L;

	@Getter
	@Setter
	private Exame exame = new Exame();
	@Getter
	@Setter
	private List<Exame> exameList = new ArrayList<>();
	@Getter
	@Setter
	private List<Funcionario> funcionarioList = new ArrayList<>();
	@Getter
	@Setter
	private ExameRealizadoKey exameRealizadoKey = new ExameRealizadoKey();
	@Getter
	@Setter
	private List<ExameRealizado> examesRealizados = new ArrayList<>();
	
	private ExameService exameService = new ExameServiceImpl();
	private FuncionarioService funcionarioService = new FuncionarioServiceImpl();

	@Override
	public Exame getModel() {
		return exame;
	}
	
	public String redirect() {
		return SUCCESS;
	}

	public String saveOrUpdate() throws SQLException, Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);

		if (request.getParameter("id").equals("") && exame.getNome() != null && exame.getAtivo() != null)
			exameService.saveExame(exame);
		else if (!request.getParameter("id").equals(""))
			exameService.updateExame(exame);
		return SUCCESS;
	}

	public String list() throws SQLException, Exception {
		exameList = exameService.listExame();
		buscarfuncionarios();
		buscarExamesRealizados();
		return SUCCESS;
	}

	public String delete() throws SQLException, Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		exameService.deleteExame(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public String edit() throws SQLException, Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		exame = exameService.buscarExamePorId(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public String buscarExames() throws SQLException, Exception {
		exameList = exameService.buscarExames(exame);
		return SUCCESS;
	}

	public String buscarfuncionarios() throws SQLException, Exception {
		funcionarioList = funcionarioService.listFuncionario();
		return SUCCESS;
	}
	
	public String buscarExamesRealizados() throws SQLException, Exception {
		examesRealizados = exameService.buscarExamesRealizados();
		return SUCCESS;
	}

}
