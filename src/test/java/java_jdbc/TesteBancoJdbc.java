package java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.BeanUserNome;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

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

	@Test
	public void initDeletar() {

		try {

			UserPosDAO dao = new UserPosDAO();
			dao.deletar(6L);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testeInsertTelefone() {
		UserPosDAO dao = new UserPosDAO();
		Telefone telefone = new Telefone();

		telefone.setIdUsuario(5L);
		telefone.setNumero("(34) 3432-1124");
		telefone.setTipo("Telefone");

		dao.salvarTelefone(telefone);
	}

	@Test
	public void buscaTelefone() {
		UserPosDAO dao = new UserPosDAO();

		List<BeanUserNome> beanUserNomes = dao.buscaUserFone(5L);
		for (BeanUserNome beanUserNome : beanUserNomes) {
			System.out.println(beanUserNome);
		}

	}

	@Test
	public void deleteUsuarioFone() {

		try {
			
			UserPosDAO dao = new UserPosDAO();
			dao.deleteFonesPorUsuario(8L);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
