import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getProdutos, updateProdutos } from '../services/api';
import '../css/AddProdutos.css';

const EditProduto = () => {
  const [produto, setProduto] = useState({
    nomeProd: '',
    valorProd: '',
    fornecedor: '',
    dataVencimento: ''
  });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getProdutos()
      .then((response) => {
        const produtoEncontrado = response.data.find(produto => produto.id === parseInt(id));
        if (produtoEncontrado) {
          setProduto({
            nomeProd: produtoEncontrado.nomeProd,
            valorProd: produtoEncontrado.valorProd,
            fornecedor: produtoEncontrado.fornecedor || '',
            dataVencimento: produtoEncontrado.dataVencimento ? produtoEncontrado.dataVencimento.slice(0, 10) : ''
          });
        } else {
          console.error('Produto não encontrado');
        }
      })
      .catch((error) => {
        console.error('Erro ao carregar produto', error);
      });
  }, [id]);

  const handleSubmit = (event) => {
    event.preventDefault();
    const produtoAtualizado = {
      nomeProd: produto.nomeProd,
      valorProd: produto.valorProd,
      fornecedor: produto.fornecedor,
      dataVencimento: produto.dataVencimento
    };
    updateProdutos(id, produtoAtualizado)
      .then(() => {
        navigate('/produtos');
      })
      .catch((error) => {
        console.error('Erro ao atualizar produto', error);
      });
  };

  return (
    <div className="editproduto-container">
      <h1 className="editproduto-title">Editar Produto</h1>
      <form className="editproduto-form" onSubmit={handleSubmit}>
        <div>
          <label>Nome:</label>
          <input
            type="text"
            value={produto.nomeProd}
            onChange={(e) => setProduto({ ...produto, nomeProd: e.target.value })}
            required
          />
        </div>
        <div>
          <label>Valor:</label>
          <input
            type="number"
            step="0.01"
            value={produto.valorProd}
            onChange={(e) => setProduto({ ...produto, valorProd: e.target.value })}
            required
          />
        </div>
        <div>
          <label>Fornecedor:</label>
          <input
            type="text"
            value={produto.fornecedor}
            onChange={(e) => setProduto({ ...produto, fornecedor: e.target.value })}
          />
        </div>
        <div>
          <label>Data de Vencimento:</label>
          <input
            type="date"
            value={produto.dataVencimento}
            onChange={(e) => setProduto({ ...produto, dataVencimento: e.target.value })}
          />
        </div>
        <button type="submit">Atualizar Produto</button>
      </form>
    </div>
  );
};

export default EditProduto;
