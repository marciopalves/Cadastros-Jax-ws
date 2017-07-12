package service;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import model.Funcionario;
import dao.Conexao;
import dao.FuncionarioDao;

@WebService
public class FuncionarioServico {

	public FuncionarioServico() {
		System.out.println("#Criando servico!");
	}

	@WebMethod
	public void cadastrar(Funcionario funcionario) throws Exception {
		System.out.println("#Chamando servico cadastrar!");
		FuncionarioDao dao = new FuncionarioDao();
		Conexao dbconexao = Conexao.getInstanceof();
		Connection conn = dbconexao.getConnection();
		dao.cadastrarFuncionario(conn, funcionario);
		System.out.println("#Pessoa cadastrada com sucesso!");
	}

	@WebMethod
	public void alterar(Funcionario funcionario) throws Exception {
		System.out.println("#Chamando servico alterar!");
		FuncionarioDao dao = new FuncionarioDao();
		Conexao dbconexao = Conexao.getInstanceof();
		Connection conn = dbconexao.getConnection();
		dao.atualizarFuncionario(conn, funcionario);
		System.out.println("#Pessoa alterada com sucesso!");
	}

	@WebMethod
	public Funcionario pesquisarPorCodigo(int codigo) throws Exception {
		FuncionarioDao dao = new FuncionarioDao();
		Conexao dbconexao = Conexao.getInstanceof();
		Connection conn = dbconexao.getConnection();
		System.out.println("#Chamando servico pesquisarPorCodigo " + codigo);
		return dao.getFuncionario(conn, codigo);
	}

	@WebMethod
	public List<Funcionario> pesquisarTodos() throws Exception {
		FuncionarioDao dao = new FuncionarioDao();
		Conexao dbconexao = Conexao.getInstanceof();
		Connection conn = dbconexao.getConnection();

		List<Funcionario> lista = new LinkedList<Funcionario>();
		System.out.println("#Chamando servico pesquisarTodos ");
		lista = dao.getFuncionarios(conn);
		return lista;
	}

}
