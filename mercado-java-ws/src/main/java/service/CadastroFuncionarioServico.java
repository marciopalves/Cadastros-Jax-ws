package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.ufg.supermercado.dao.FuncionarioDao;
import br.ufg.supermercado.model.Funcionario;

@WebService
public class CadastroFuncionarioServico {
	
	private Conexao conn;
	private FuncionarioDao dao;
	
	public CadastroFuncionarioServico(){
		conn = new Conexao.getInstanceof();
		dao  = new FuncionarioDao();
	}

	@WebMethod
	public void cadastrar(Funcionario funcionario) {
		dao.cadastrarFuncionario(conn, funcionario);
	}

	@WebMethod
	public Funcionario pesquisarFuncionarioPorCodigo(int codigo) {
		return dao.getFuncionario(conn, id);	
	}
	
	public void alterar(Funcionario funcionario){
		
	}

}
