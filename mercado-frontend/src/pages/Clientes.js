import React, { useState, useEffect } from 'react';
import { getClientes, deleteClientes } from '../services/api';
import { Link } from 'react-router-dom';
import '../css/Clientes.css';

const Clientes = () => {
  const [clientes, setClientes] = useState([]);

  useEffect(() => {
    getClientes()
      .then(response => {
        setClientes(response.data);
      })
      .catch(error => console.error('Erro ao carregar clientes', error));
  }, []);

  const handleDelete = (id) => {
    deleteClientes(id)
      .then(() => {
        setClientes(clientes.filter(cliente => cliente.id !== id));
      })
      .catch(error => {
        console.error('Erro ao excluir cliente', error);
      });
  };

  return (
    <div className="clientes-container">
      <h1 className="clientes-title">Lista de Clientes</h1>
      <Link to="/add-cliente">
        <button className="add-button">Adicionar Cliente</button>
      </Link>

      {clientes.length > 0 ? (
        <ul className="clientes-list">
          {clientes.map(cliente => (
            <li key={cliente.id} className="cliente-item">
              <h2 className="cliente-name">{cliente.nomeClient}</h2>
              <p><strong>CPF:</strong> {cliente.cpfClient}</p>
              <p><strong>Nacionalidade:</strong> {cliente.nacionalidade}</p>
              <p><strong>Idade:</strong> {cliente.idadeClient}</p>
              <button 
                className="delete-button" 
                onClick={() => handleDelete(cliente.id)}
              >
                Excluir
              </button>
              <Link to={`/edit-cliente/${cliente.id}`}>
                <button className="edit-button">Editar</button>
              </Link>
            </li>
          ))}
        </ul>
      ) : (
        <p className="no-client-message">Não há clientes cadastrados.</p>
      )}
    </div>
  );
};

export default Clientes;
