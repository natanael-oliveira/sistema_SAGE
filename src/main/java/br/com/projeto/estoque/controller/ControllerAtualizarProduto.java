package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings({ "rawtypes" })
public class ControllerAtualizarProduto {
	private static EntityManager manager;

	// Esse método busca o produto pelo ID inserido no JTextField
	public void buscarProduto(JButton btnBuscar, JButton btnResetar, JTextField tfId, JFormattedTextField tfPreco,
			JSpinner jsQuantidade, JEditorPane epDescricao, JComboBox cbGrupo, JDateChooser dcDataFabricacao,
			JDateChooser dcDataVencimento, JButton btnLimpar, JButton btnAtualizar) {
		manager = new JPAUtil().getEntityManager();
		Integer idBuscado = null;
		try {
			idBuscado = Integer.parseInt(tfId.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo de ID precisa estar preenchido e ser coerente!", "ID inválido",
					JOptionPane.ERROR_MESSAGE);
			tfId.transferFocus();
			return;
		}
		Produto produto = manager.find(Produto.class, idBuscado);
		// Se o EntityManager não encontrar nenhum Produto com esse ID, o programa para
		// e exibe esse erro
		if (produto == null) {
			JOptionPane.showMessageDialog(null, "Esse registro não existe!", "Registro inexistente",
					JOptionPane.ERROR_MESSAGE);
			return;
			// Se o Produto existir, os campos serão populados com seus dados
		} else {
			tfPreco.setText(produto.getPreco() + "");
			jsQuantidade.setValue(produto.getQuantidade());
			epDescricao.setText(produto.getDescricao());
			cbGrupo.setSelectedItem(ControllerGrupo.encontrarGrupoPeloProduto(produto.getId()));
			dcDataFabricacao.setDate(produto.getDataFabricacao().getTime());
			dcDataVencimento.setDate(produto.getDataVencimento().getTime());
			tfId.setEnabled(false);
			habilitarAtualizacao(btnBuscar, btnResetar, tfId, tfPreco, jsQuantidade, epDescricao, cbGrupo,
					dcDataFabricacao, dcDataVencimento, btnLimpar, btnAtualizar);
		}
		manager.close();
	}

	// Esse método efetua a atualização do Produto. Ainda não está com todas as
	// validações
	public void atualizarProduto(JTextField tfId, JFormattedTextField tfPreco, JSpinner jsQuantidade,
			JEditorPane epDescricao, JComboBox cbGrupo, JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento) {
		manager = new JPAUtil().getEntityManager();
		if (ControllerAuxiliar.dadosConferem(tfPreco, jsQuantidade, epDescricao, dcDataFabricacao, dcDataVencimento,
				cbGrupo)) {
			Integer idAtualizado = Integer.parseInt(tfId.getText());
			Integer idGrupo = ControllerAuxiliar.pegarIdGrupoSelecionado(cbGrupo);
			BigDecimal preco = null;
			try {
				preco = new BigDecimal(tfPreco.getText());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "O preço inserido é inválido", "Preço inválido",
						JOptionPane.ERROR_MESSAGE);
				tfPreco.transferFocus();
				return;
			}
			
			int quantidade = (Integer) jsQuantidade.getValue();
			
			if (quantidade <= 0) {
				JOptionPane.showMessageDialog(null, "A quantidade não pode ser nula ou negativa!",
						"Quantidade inválida", JOptionPane.ERROR_MESSAGE);
				jsQuantidade.transferFocus();
				return;
			}
			
			String descricao = epDescricao.getText();
			
			// Por padrão, o JDateChooser retorna um objeto do tipo Date, que precisa ser
			// convertido
			// com o método auxiliar ".toCalendar" para um objeto do tipo Calendar
			Calendar dataFabricacao = ControllerAuxiliar.toCalendar(dcDataFabricacao.getDate());
			Calendar dataVencimento = ControllerAuxiliar.toCalendar(dcDataVencimento.getDate());

			// Aqui, as variáveis são inseridas no método que de fato atualiza o Produto no
			// banco

			try {
				if (!ControllerAuxiliar.dataErrada(dcDataFabricacao, dcDataVencimento)) {
					ControllerProduto cp = new ControllerProduto();
					cp.atualizarProduto(idAtualizado, preco, quantidade, descricao, dataFabricacao, dataVencimento,
							idGrupo);

					JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!", "Produto atualizado",
							JOptionPane.INFORMATION_MESSAGE);

					ControllerAuxiliar.resetarTodosOsCampos(tfPreco, jsQuantidade, epDescricao, dcDataFabricacao,
							dcDataVencimento, cbGrupo);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao atualizar o produto. Cheque os dados\ne tente novamente. Se o erro persistir, entre em contato.",
						"Erro desconhecido", JOptionPane.ERROR);
				return;
			}
		}
		manager.close();
	}

	// Esse método desabilita todos os campos, com excessão do de buscar e do de
	// informar o ID, para buscar um novo registro
	public void desabilitarAtualizacao(JButton btnBuscar, JButton btnResetar, JTextField tfId,
			JFormattedTextField tfPreco, JSpinner jsQuantidade, JEditorPane epDescricao, JComboBox cbGrupo,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento, JButton btnLimpar, JButton btnAtualizar) {
		ControllerAuxiliar.resetarTodosOsCampos(tfPreco, jsQuantidade, epDescricao, dcDataFabricacao, dcDataVencimento,
				cbGrupo);
		btnBuscar.setEnabled(true);
		btnResetar.setEnabled(false);
		tfId.setEnabled(true);
		tfPreco.setEnabled(false);
		jsQuantidade.setEnabled(false);
		epDescricao.setEnabled(false);
		cbGrupo.setEnabled(false);
		dcDataFabricacao.setEnabled(false);
		dcDataVencimento.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnAtualizar.setEnabled(false);
	}

	// Esse método habilita todos os campos, com excessão do de buscar e do de
	// informar o ID, para atualizar o registro atual
	public void habilitarAtualizacao(JButton btnBuscar, JButton btnResetar, JTextField tfId,
			JFormattedTextField tfPreco, JSpinner jsQuantidade, JEditorPane epDescricao, JComboBox cbGrupo,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento, JButton btnLimpar, JButton btnAtualizar) {
		btnBuscar.setEnabled(false);
		btnResetar.setEnabled(true);
		tfId.setEnabled(false);
		tfPreco.setEnabled(true);
		jsQuantidade.setEnabled(true);
		epDescricao.setEnabled(true);
		cbGrupo.setEnabled(true);
		dcDataFabricacao.setEnabled(true);
		dcDataVencimento.setEnabled(true);
		btnLimpar.setEnabled(true);
		btnAtualizar.setEnabled(true);
	}
}