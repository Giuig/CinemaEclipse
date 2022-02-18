package com.cinemaeclipse.dao;

import java.util.List;

import com.cinemaeclipse.model.Pagamento;

public interface PagamentoDao {

	public List<Pagamento> selectAll();
	public Pagamento selectById (int id);
	public Pagamento add(Pagamento pagamento);
	public void delete(Pagamento pagamento);
	public Pagamento update(Pagamento pagamento);
}
