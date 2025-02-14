import React, { useState, useEffect } from 'react';
import { getClientes, updateClientes } from '../services/api';
import { useNavigate, useParams } from 'react-router-dom';
import '../css/AddClientes.css';

const EditCliente = () => {
  const [cliente, setCliente] = useState({
    cpfClient: '',
    nomeClient: '',
    nacionalidade: '',
    idadeClient: ''
  });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getClientes()
      .then(response => {
        const clienteEncontrado = response.data.find(cliente => cliente.id === parseInt(id));

        if (clienteEncontrado) {
          setCliente(clienteEncontrado);
        } else {
          console.error('Cliente não encontrado');
        }
      })
      .catch(error => {
        console.error('Erro ao carregar cliente', error);
      });
  }, [id]);

  const handleSubmit = (e) => {
    e.preventDefault();
    updateClientes(id, cliente)
      .then(() => {
        navigate('/clientes');
      })
      .catch(error => {
        console.error('Erro ao atualizar cliente', error);
      });
  };

  return (
    <div className="editcliente-container">
      <h1 className="editcliente-title">Editar Cliente</h1>
      <form className="editcliente-form" onSubmit={handleSubmit}>
        <div>
          <label>CPF:</label>
          <p>{cliente.cpfClient}</p>
        </div>
        <div>
          <label>Nome:</label>
          <input
            type="text"
            value={cliente.nomeClient}
            onChange={(e) => setCliente({ ...cliente, nomeClient: e.target.value })}
            required
          />
        </div>
        <div>
          <label>Nacionalidade:</label>
          <input
            type="text"
            value={cliente.nacionalidade}
            onChange={(e) => setCliente({ ...cliente, nacionalidade: e.target.value })}
          />
        </div>
        <div>
          <label>Idade:</label>
          <input
            type="number"
            value={cliente.idadeClient}
            onChange={(e) => setCliente({ ...cliente, idadeClient: e.target.value })}
          />
        </div>
        <button type="submit">Atualizar Cliente</button>
      </form>
    </div>
  );
};

export default EditCliente;
