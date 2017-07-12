package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufg.supermercado.model.Funcionario;

public class FuncionarioDao {

	public List<Funcionario> getFuncionarios(Connection conn)
			throws SQLException {
		List<Funcionario> lista = new ArrayList<Funcionario>();

		if (conn != null) {
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				preparedStatement = conn
						.prepareStatement("select nome, cpf, email, telefone from funcionario;");
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					Funcionario func = new Funcionario();
					func.setId(resultSet.getInt("id"));
					func.setNome(resultSet.getString("nome"));
					// func.setNascimento(resultSet.getDate(1, ("nascimento");
					func.setEmail(resultSet.getString("email"));
					func.setTelefone(resultSet.getString("telefone"));

					lista.add(func);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				resultSet.close();
				preparedStatement.close();
				conn.close();
			}
		}

		return lista;
	}

	public Funcionario getFuncionario(Connection conn, int id)
			throws SQLException {
		PreparedStatement preparedStatement = conn
				.prepareStatement("select nome, cpf, email, telefone from funcionario where id = ?");
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		Funcionario func = null;

		while (resultSet.next()) {
			func = new Funcionario();
			func.setId(resultSet.getInt("id"));
			func.setNome(resultSet.getString("nome"));
			// func.setNascimento(resultSet.getDate(1, ("nascimento");
			func.setEmail(resultSet.getString("email"));
			func.setTelefone(resultSet.getString("telefone"));

		}

		return func;
	}

	public Funcionario cadastrarFuncionario(Connection conn,
			Funcionario funcionario) throws SQLException {
		PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO funcionario (nome, cpf, email, telefone) VALUES (? , ?, ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, funcionario.getNome());
		preparedStatement.setString(2, funcionario.getCpf());
		preparedStatement.setString(3, funcionario.getEmail());
		preparedStatement.setString(4, funcionario.getTelefone());
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();

		if (rs.next()) {
			funcionario.setId(rs.getInt(1));
		}

		return funcionario;
	}

	public Funcionario atualizarFuncionario(Connection conn,
			Funcionario funcionario) throws SQLException {
		PreparedStatement preparedStatement = conn
				.prepareStatement("UPDATE funcionario SET nome = ?, cpf = ?, email = ?, telefone = ? where id = ?");
		preparedStatement.setString(1, funcionario.getNome());
		preparedStatement.setString(2, funcionario.getCpf());
		preparedStatement.setString(3, funcionario.getEmail());
		preparedStatement.setString(4, funcionario.getTelefone());

		preparedStatement.setInt(5, funcionario.getId());
		int caso = preparedStatement.executeUpdate();

		return (caso == 1) ? funcionario : new Funcionario();
	}

	public boolean deletarFuncionario(Connection conn, int id)
			throws SQLException {
		PreparedStatement preparedStatement = conn
				.prepareStatement("delete from funcionario where id = ?");
		preparedStatement.setInt(1, id);
		int caso = preparedStatement.executeUpdate();

		return caso == 1;
	}

}

