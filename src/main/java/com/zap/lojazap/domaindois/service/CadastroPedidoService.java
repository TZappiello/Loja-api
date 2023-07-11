package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;
import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;
import com.zap.lojazap.domaindois.exception.PedidoNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.PedidoRepository;

@Service
public class CadastroPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CadastroUsuarioService CadastroUsuario;
	
	@Autowired
	private CadastroCidadesService cadastroCidades;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private CadastroProdutosService cadastroProdutos;

	public PedidoEntity buscarSeTiver(String codigo) {
		PedidoEntity pedido = pedidoRepository.findByCodigo(codigo).orElseThrow(() -> new PedidoNaoEncontradoException(codigo));

		return pedido;
	}

	@Transactional
	public PedidoEntity adicionar(PedidoEntity pedido) {
		validarPedido(pedido);
		validarItens(pedido);
		
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();

		return pedidoRepository.save(pedido);
	}

	private void validarPedido(PedidoEntity pedido) {
		CidadeEntity cidade = cadastroCidades.buscarSeTiver(pedido.getEndereco().getCidade().getId());
		UsuarioEntity usuario = cadastroUsuario.buscarSeTiver(pedido.getCliente().getId());
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(pedido.getRestaurante().getId());
		FormaPagamentoEntity pagamento = cadastroFormaPagamento.buscarSeTiver(pedido.getFormaPagamento().getId());
		
		pedido.getEndereco().setCidade(cidade);
		pedido.setCliente(usuario);
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(pagamento);
	}

	private void validarItens(PedidoEntity pedido) {
		pedido.getItemPedido().forEach(item -> {
			ProdutoEntity produto = cadastroProdutos.buscarSeTiver(
					pedido.getRestaurante().getId(), item.getProduto().getId());
			
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}
}
