import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getCompras, deleteCompra } from '../services/api';
import '../css/Compras.css';

const Compras = () => {
  const [compras, setCompras] = useState([]);

  useEffect(() => {
    getCompras()
      .then((response) => {
        setCompras(response.data);
      })
      .catch((error) => {
        console.error('Erro ao carregar compras', error);
      });
  }, []);

  const handleDelete = (id) => {
    deleteCompra(id)
      .then(() => {
        setCompras(compras.filter((compra) => compra.id !== id));
      })
      .catch((error) => {
        console.error('Erro ao excluir compra', error);
      });
  };

  return (
    <div className="compras-container">
      <h1 className="compras-title">Lista de Compras</h1>

      <Link to="/add-compra">
        <button className="add-button">Adicionar Compra</button>
      </Link>
      <table className="compras-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Atendimento</th>
            <th>Produto</th>
            <th>Valor Total</th>
            <th>Forma de Pagamento</th>
            <th>Quantidade</th>
            <th>Endereço de Entrega</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {compras.map((compra) => (
            <tr key={compra.id} className="compras-row">
              <td>{compra.id}</td>
              <td>{compra.idAtend}</td>
              <td>{compra.idProd}</td>
              <td>{compra.valorTotal}</td>
              <td>{compra.formaPagam}</td>
              <td>{compra.quantProd}</td>
              <td>{compra.enderEntrega}</td>
              <td>
                <Link to={`/edit-compra/${compra.id}`}>
                  <button className="edit-button">Editar</button>
                </Link>
                <button className="delete-button" onClick={() => handleDelete(compra.id)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Compras;
