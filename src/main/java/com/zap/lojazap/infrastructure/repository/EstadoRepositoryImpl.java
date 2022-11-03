//package com.zap.lojazap.infrastructure.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Repository;
//
//import com.zap.lojazap.domaindois.entities.EstadoEntity;
//import com.zap.lojazap.domaindois.repository.EstadoRepository;
//
//@Repository
//public class EstadoRepositoryImpl implements EstadoRepository {
//
//	@PersistenceContext
//	private EntityManager manager;
//	
//	@Override
//	public List<EstadoEntity> todas() {
//		return manager.createQuery(" from EstadoEntity", EstadoEntity.class).getResultList();
//	}
//
//	@Override
//	public EstadoEntity porId(Long id) {
//		return manager.find(EstadoEntity.class, id);
//	}
//
//	@Transactional
//	@Override
//	public EstadoEntity adicionar(EstadoEntity estados) {
//		return manager.merge(estados);
//	}
//
//	@Transactional
//	@Override 
//	public void remover(Long id) {
//		EstadoEntity estado = porId(id);
//		
//		if(estado == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//		manager.remove(estado);
//	}
//
//}
