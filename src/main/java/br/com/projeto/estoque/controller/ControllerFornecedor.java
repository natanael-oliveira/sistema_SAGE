package br.com.projeto.estoque.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.util.JPAUtil;

public class ControllerFornecedor {
	private static EntityManager manager;

	public Fornecedor criarFornecedor(String cnpj, String nome) {
		manager = new JPAUtil().getEntityManager();

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCnpj(cnpj);
		fornecedor.setNome(nome);
		manager.getTransaction().begin();
		manager.persist(fornecedor);
		manager.getTransaction().commit();
		manager.close();

		return fornecedor;
	}

	@SuppressWarnings("unchecked")
	public static List<Fornecedor> listarFornecedores() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select f from Fornecedor f");
		List<Fornecedor> fornecedores = query.getResultList();
		return fornecedores;
	}
	
	public void atualizarFornecedor(Integer idInserido, String cnpj, String nome) {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select id from Fornecedor f where f.id=:idInserido");
		query.setParameter("idInserido", idInserido);
		Object idBruto = query.getSingleResult();
		Integer id = Integer.parseInt(idBruto.toString());
		Fornecedor fornecedorAtualizado = manager.find(Fornecedor.class, id);
		try {
			fornecedorAtualizado.setCnpj(cnpj);
			fornecedorAtualizado.setNome(nome);
			manager.getTransaction().begin();
			manager.merge(fornecedorAtualizado);
			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
