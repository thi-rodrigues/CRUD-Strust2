package br.com.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import br.com.dao.FuncionarioDAO;
import br.com.dao.FuncionarioDAOImpl;
import br.com.domain.Funcionario;

public class FuncionarioAction extends ActionSupport implements ModelDriven<Funcionario> {

	private static final long serialVersionUID = -6659925652584240539L;

	private Funcionario user = new Funcionario();
	private List<Funcionario> userList = new ArrayList<Funcionario>();
	private FuncionarioDAO userDAO = new FuncionarioDAOImpl();

	@Override
	public Funcionario getModel() {
		return user;
	}

	/**
	 * To save or update user.
	 * 
	 * @return String
	 */
	public String saveOrUpdate() {
		userDAO.saveOrUpdateFuncionario(user);
		return SUCCESS;
	}

	/**
	 * To list all users.
	 * 
	 * @return String
	 */
	public String list() {
		userList = userDAO.listFuncionario();
		return SUCCESS;
	}

	/**
	 * To delete a user.
	 * 
	 * @return String
	 */
	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		userDAO.deleteFuncionario(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	public String edit() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		user = userDAO.listFuncionarioById(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public Funcionario getFuncionario() {
		return user;
	}

	public void setFuncionario(Funcionario user) {
		this.user = user;
	}

	public List<Funcionario> getFuncionarioList() {
		return userList;
	}

	public void setFuncionarioList(List<Funcionario> userList) {
		this.userList = userList;
	}

}
