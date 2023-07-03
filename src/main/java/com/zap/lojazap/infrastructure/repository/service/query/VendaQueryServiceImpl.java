package com.zap.lojazap.infrastructure.repository.service.query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.xml.crypto.Data;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.entities.model.VendaDiaria;
import com.zap.lojazap.domaindois.enums.StatusPedido;
import com.zap.lojazap.domaindois.filter.VendaDiariaFilter;
import com.zap.lojazap.domaindois.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		var builder = manager.getCriteriaBuilder(); // instanciando uma fabrica 
		var query = builder.createQuery(VendaDiaria.class); //no query vai ser a classe de retorno não precisa ser a entidade
		var root = query.from(PedidoEntity.class); // aqui faz o from da entidade
		
		var predicates = new ArrayList<Predicate>();
		
		var functionConvertTzDataCriacao = builder.function(
				"convert_tz", 
				Data.class, 
				root.get("dataCriacao"),
				builder.literal("+00:00"), 
				builder.literal(timeOffset));
		
		var functionDateDataCriacao = builder.function(
				"date", 
				Date.class, 
				functionConvertTzDataCriacao);
		
		var selection = builder.construct(VendaDiaria.class, 
				functionDateDataCriacao,
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		
		if(filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}
		
		if(filtro.getDataCriacao() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
					filtro.getDataCriacao()));
		}
		
		if(filtro.getDataFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), 
					filtro.getDataFim()));
		} 
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE)); 
		
		query.select(selection);
		query.where(builder.and(predicates.toArray(new Predicate[0])));
		query.groupBy(functionDateDataCriacao);
		
		return manager.createQuery(query).getResultList();
	}

}
