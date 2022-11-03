//package com.zap.lojazap.infrastructure.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//
//import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;
//import com.zap.lojazap.domaindois.repository.FormaPagamentoRepository;
//
//@Repository
//public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {
//
//	@PersistenceContext
//	private EntityManager manager;
//	
//	@Override
//	public List<FormaPagamentoEntity> todas() {
//		return (List<FormaPagamentoEntity>) manager.createQuery("from FormaPagamentoEntity ", FormaPagamentoEntity.class).getResultList();
//	}
//
//	@Override
//	public FormaPagamentoEntity porId(Long id) {
//		return manager.find(FormaPagamentoEntity.class, id);
//	}
//
//	@Override
//	public FormaPagamentoEntity adicionar(FormaPagamentoEntity pagamento) {
//		return manager.merge(pagamento);
//	}
//
//	@Override
//	public void remover(FormaPagamentoEntity pagamento) {
//		pagamento = porId(pagamento.getId());
//		manager.remove(pagamento);
//	}
//
//}
