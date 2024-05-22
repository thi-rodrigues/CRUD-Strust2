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
	private ExameService exameService = new ExameServiceImpl();
	private FuncionarioService funcionarioService = new FuncionarioServiceImpl();

	@Override
	public Exame getModel() {
		return exame;
	}

	public String saveOrUpdate() throws SQLException, Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);

		if (request.getParameter("id").equals(""))
			exameService.saveExame(exame);
		else
			exameService.updateExame(exame);
		return SUCCESS;
	}

	public String list() throws SQLException, Exception {
		exameList = exameService.listExame();
		buscarfuncionarios();
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

}
