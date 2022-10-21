//package com.zap.lojazap.model.repository;
//
//import java.math.BigDecimal;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.zap.lojazap.model.entity.Lancamento;
//import com.zap.lojazap.model.enums.TipoLancamento;
//
//public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
//
//	@Query(value = "SELECT SUM (l.valor) FROM Lancamento l "
//			+ " JOIN l.usuario u "
//			+ " WHERE u.id = :idUsuario "
//			+ " AND l.tipo =:tipo group by u ")
//	BigDecimal obterSaldoPorTipoLancamentoEUsuario(
//										@Param("idUsuario") Long idUsuario,
//										@Param("tipo") TipoLancamento tipo);
//}
