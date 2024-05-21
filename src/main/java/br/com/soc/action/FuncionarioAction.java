package br.com.soc.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import br.com.soc.domain.Funcionario;
import br.com.soc.service.FuncionarioService;
import br.com.soc.service.impl.FuncionarioServiceImpl;

public class FuncionarioAction extends ActionSupport implements ModelDriven<Funcionario> {

	private static final long serialVersionUID = -6659925652584240539L;

	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarioList = new ArrayList<Funcionario>();
	private FuncionarioService funcionarioService = new FuncionarioServiceImpl();

	@Override
	public Funcionario getModel() {
		return funcionario;
	}

	public String saveOrUpdate() throws SQLException, Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		
		if (request.getParameter("id").equals(""))
			funcionarioService.saveFuncionario(funcionario);
		else
			funcionarioService.updateFuncionario(funcionario);
		return SUCCESS;
	}

	public String list() throws SQLException, Exception {
		funcionarioList = funcionarioService.listFuncionario();
		return SUCCESS;
	}

	public String delete() throws SQLException, Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		funcionarioService.deleteFuncionario(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public String edit() throws SQLException, Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		funcionario = funcionarioService.listFuncionarioById(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

}
