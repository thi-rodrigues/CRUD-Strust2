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
import br.com.soc.service.ExameService;
import br.com.soc.service.impl.ExameServiceImpl;

public class ExameAction extends ActionSupport implements ModelDriven<Exame> {

	private static final long serialVersionUID = -6659925652584240539L;

	private Exame exame = new Exame();
	private List<Exame> exameList = new ArrayList<Exame>();
	private ExameService exameService = new ExameServiceImpl();

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
		exame = exameService.listExameById(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<Exame> getExameList() {
		return exameList;
	}

	public void setExameList(List<Exame> exameList) {
		this.exameList = exameList;
	}

}
