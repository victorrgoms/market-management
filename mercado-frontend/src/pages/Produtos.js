import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getProdutos, deleteProdutos } from '../services/api';
import '../css/Produtos.css';

const Produtos = () => {
  const [produtos, setProdutos] = useState([]);

  useEffect(() => {
    getProdutos()
      .then((response) => {
        setProdutos(response.data);
      })
      .catch((error) => {
        console.error('Erro ao carregar produtos', error);
      });
  }, []);

  const handleDelete = (id) => {
    deleteProdutos(id)
      .then(() => {
        setProdutos(produtos.filter((produto) => produto.id !== id));
      })
      .catch((error) => {
        console.error('Erro ao excluir produto', error);
      });
  };

  return (
    <div className="produtos-container">
      <h1 className="produtos-title">Lista de Produtos</h1>
      <Link to="/add-produto">
        <button className="add-button">Adicionar Produto</button>
      </Link>
      {produtos.length > 0 ? (
        <table className="produtos-table">
          <thead>
            <tr>
              <th>Nome</th>
              <th>Valor</th>
              <th>Fornecedor</th>
              <th>Data de Vencimento</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {produtos.map((produto) => (
              <tr key={produto.id}>
                <td>{produto.nomeProd}</td>
                <td>{produto.valorProd}</td>
                <td>{produto.fornecedor}</td>
                <td>{produto.dataVencimento ? produto.dataVencimento.slice(0, 10) : ''}</td>
                <td className="produto-actions">
                  <Link to={`/edit-produto/${produto.id}`}>
                    <button className="edit-button">Editar</button>
                  </Link>
                  <button
                    className="delete-button"
                    onClick={() => handleDelete(produto.id)}
                  >
                    Excluir
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p className="no-produto-message">Não há produtos cadastrados.</p>
      )}
    </div>
  );
};

export default Produtos;
