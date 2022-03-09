package java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.Userposjava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

		userposjava.setId(6L);
		userposjava.setNome("Julia");
		userposjava.setEmail("juliafernandez@yahoo.com");

		userPosDAO.salvar(userposjava);
	}

	@Test
	public void initListar() {
		UserPosDAO userDao = new UserPosDAO();

		try {
			List<Userposjava> lista = userDao.listar();

			for (Userposjava userposjava : lista) {
				System.out.println(userposjava);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initBuscar() {
		UserPosDAO userDao = new UserPosDAO();

		try {
			Userposjava userposjava = userDao.buscar(2L);
			System.out.println(userposjava);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() {

		try {
			UserPosDAO userDao = new UserPosDAO();
			
			Userposjava objetoBanco = userDao.buscar(3L);
			objetoBanco.setNome("Cleito");
			
			userDao.atualizar(objetoBanco);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
