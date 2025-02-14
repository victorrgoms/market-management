import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createProdutos } from '../services/api';
import '../css/AddProdutos.css';

const AddProduto = () => {
  const [produto, setProduto] = useState({
    nomeProd: '',
    valorProd: '',
    fornecedor: '',
    dataVencimento: ''
  });

  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    createProdutos(produto)
      .then(() => {
        navigate('/produtos');
      })
      .catch((error) => {
        console.error('Erro ao adicionar produto', error);
      });
  };

  return (
    <div className="addproduto-container">
      <h1 className="addproduto-title">Adicionar Produto</h1>
      <form className="addproduto-form" onSubmit={handleSubmit}>
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
            value={produto.dataVencimento ? produto.dataVencimento.slice(0, 10) : ''}
            onChange={(e) => setProduto({ ...produto, dataVencimento: e.target.value })}
          />
        </div>
        <button type="submit">Adicionar Produto</button>
      </form>
    </div>
  );
};

export default AddProduto;
