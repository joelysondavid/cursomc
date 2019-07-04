package com.joedav.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable // especifica que a classe é um subtipo
public class ItemPedidoPK implements Serializable {
	// serial version
	private static final long serialVersionUID = 1L;
	
	// atributos
	@ManyToOne // muitos para um
	@JoinColumn(name="pedido_id") // chave estrageira correspondente
	private Pedido pedido;
	
	@ManyToOne // muitos para um
	@JoinColumn(name = "produto_id") // chave estrageira correspondente
	private Produto produto;

	// getters e setters
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	// hash and equals com os dois atributos para comparar se um pedid/produto é
	// igualk ao outro
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

}
