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
//import com.zap.lojazap.domaindois.entities.CidadeEntity;
//import com.zap.lojazap.domaindois.repository.CidadeRepository;
//
//@Repository
//public class CidadeRepositoryImpl implements CidadeRepository {
//
//	@PersistenceContext
//	private EntityManager manager;
//
//	@Override
//	public List<CidadeEntity> todas() {
//		return manager.createQuery(" from CidadeEntity", CidadeEntity.class).getResultList();
//	}
//
//	@Override
//	public CidadeEntity porId(Long id) {
//		return manager.find(CidadeEntity.class, id);
//	}
//
//	@Transactional
//	@Override
//	public CidadeEntity adicionar(CidadeEntity cidade) {
//		return manager.merge(cidade);
//	}
//
//	@Transactional
//	@Override
//	public void remover(Long id) {
//		CidadeEntity cidade = porId(id);
//		if(cidade == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//		manager.remove(cidade);
//	}
//
//}
