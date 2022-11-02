/*
 * package com.zap.lojazap.infrastructure.repository;
 * 
 * import java.util.List;
 * 
 * import javax.persistence.EntityManager; import
 * javax.persistence.PersistenceContext;
 * 
 * import org.springframework.dao.EmptyResultDataAccessException; import
 * org.springframework.stereotype.Repository; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import com.zap.lojazap.domaindois.entities.CozinhaEntity; import
 * com.zap.lojazap.domaindois.repository.CozinhaRepository;
 * 
 * @Repository public class CozinhaRepositoryImpl implements CozinhaRepository {
 * 
 * @PersistenceContext private EntityManager manager;
 * 
 * @Override public List<CozinhaEntity> todas() { return
 * manager.createQuery(" from CozinhaEntity",
 * CozinhaEntity.class).getResultList(); }
 * 
 * @Override public List<CozinhaEntity> listarPorNome( String nome) { return
 * manager.createQuery("FROM CozinhaEntity WHERE nome LIKE :nome ",
 * CozinhaEntity.class) .setParameter("nome", "%" + nome + "%" )
 * .getResultList(); }
 * 
 * @Override public CozinhaEntity porId(Long id) { return
 * manager.find(CozinhaEntity.class, id); }
 * 
 * @Transactional
 * 
 * @Override public CozinhaEntity adicionar(CozinhaEntity cozinha) { return
 * manager.merge(cozinha); }
 * 
 * @Transactional
 * 
 * @Override public void remover(Long id) { CozinhaEntity cozinha = porId(id);
 * 
 * if(cozinha == null) { throw new EmptyResultDataAccessException(1); }
 * manager.remove(cozinha); }
 * 
 * }
 */