package com.cinemaeclipse.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemaeclipse.model.Pagamento;

public class JPAPagamentoDaoImpl implements PagamentoDao{
	
private static JPAPagamentoDaoImpl instance;
	
	private JPAPagamentoDaoImpl() {
		
	}
	
	public static JPAPagamentoDaoImpl getInstance() {
		if(instance==null) {
			instance=new JPAPagamentoDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Pagamento> selectAll() {
		List<Pagamento> listaPagamenti=null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			listaPagamenti = manager.createQuery("select p from Pagamento p", Pagamento.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPagamenti;
	}

	@Override
	public Pagamento selectById(int id) {
		Pagamento pagamento=null;
		try {
			EntityManager manager=JPADaoFactory.getEntityManager();
			Query query=manager.createQuery("select p from Pagamento p where p.idPagamento=:id", Pagamento.class);
			query.setParameter("id", id);
			pagamento=(Pagamento)query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagamento;
	}

	@Override
	public Pagamento add(Pagamento pagamento) {
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(pagamento);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagamento;
	}

	@Override
	public void delete(Pagamento pagamento) {
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Pagamento pagamentoMerge=manager.merge(pagamento);
			manager.remove(pagamentoMerge);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}

	@Override
	public Pagamento update(Pagamento pagamento) {
		Pagamento nuovoPagamento=null;
		try {
			EntityManager manager=JPADaoFactory.getEntityManager();
			EntityTransaction transaction=manager.getTransaction();
			transaction.begin();
			nuovoPagamento=manager.merge(pagamento);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nuovoPagamento;
	}
}
