package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserNome;
import model.Telefone;
import model.Userposjava;

// Faz as operações no banco de dados
public class UserPosDAO {

	// Estabelece nossa conexão com o banco
	private Connection connection;

	// Quando instanciar um obj UserPosDAO vai injetar uma SingleConnection para
	// dentro do
	// connection
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {

		try {
			String sql = "insert into userposjava(nome, email) values(?, ?)";

			// Prepara o sql
			PreparedStatement insert = connection.prepareStatement(sql);

			// Passamos posição do parametro da sql que vamos adicionar depois o valor
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();

			// Salva no banco
			connection.commit();

		} catch (Exception e) {

			try {
				// Caso de um erro ele reverte a operação no banco de dados
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void salvarTelefone(Telefone telefone) {

		try {

			String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa) VALUES (?, ?, ?);";

			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getIdUsuario());
			insert.execute();

			connection.commit();

		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}

	}

	public List<Userposjava> listar() throws SQLException {

		List<Userposjava> list = new ArrayList<Userposjava>();

		// Instrução que vai retorna todos os dados da tabela
		String sql = "select * from userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);

		// Vai executar a query
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			Userposjava userposjava = new Userposjava();

			// Passamos o nome da coluna de onde vai pegar o dado
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

			list.add(userposjava);
		}

		return list;
	}

	public Userposjava buscar(Long id) throws SQLException {

		Userposjava retorno = new Userposjava();

		// Instrução que vai retorna todos os dados da tabela
		String sql = "select * from userposjava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);

		// Vai executar a query
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			// Passamos o nome da coluna
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}
		return retorno;
	}

	public List<BeanUserNome> buscaUserFone(Long idUser) {

		List<BeanUserNome> beanUserNomes = new ArrayList<BeanUserNome>();

		String sql = "SELECT userp.nome, fone.numero, userp.email FROM telefoneuser AS fone "
				+ " INNER JOIN userposjava AS userp ON fone.usuariopessoa = userp.id " 
				+ " WHERE userp.id = " + idUser;

		try {

			PreparedStatement statement = connection.prepareStatement(sql);

			// Traz o resultado da busca no banco
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				BeanUserNome userFone = new BeanUserNome();

				userFone.setNome(resultado.getString("nome"));
				userFone.setNumero(resultado.getString("numero"));
				userFone.setEmail(resultado.getString("email"));

				beanUserNomes.add(userFone);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return beanUserNomes;
	}

	public void atualizar(Userposjava userposjava) {

		try {

			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());
			statement.execute();

			// Salva no banco
			connection.commit();

		} catch (Exception e) {

			try {
				// Caso de um erro ele reverte a operação no banco de dados
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			e.printStackTrace();
		}

	}

	public void deletar(Long id) {

		try {

			String sql = "delete from userposjava where id = " + id;

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();

			connection.commit();

		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void deleteFonesPorUsuario(Long idUsuario) {

		try {

			String sqlFone = "delete from telefoneuser where usuariopessoa = " + idUsuario;
			String sqlUsuario = "delete from userposjava where id = " + idUsuario;

			PreparedStatement statement = connection.prepareStatement(sqlFone);
			statement.executeUpdate();
			connection.commit();
			
			statement = connection.prepareStatement(sqlUsuario);
			statement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

}
