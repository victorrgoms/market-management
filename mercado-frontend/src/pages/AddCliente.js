import React, { useState } from 'react';
import { createClientes } from '../services/api';
import { useNavigate } from 'react-router-dom';
import '../css/AddClientes.css';

const AddCliente = () => {
  const [cliente, setCliente] = useState({
    cpfClient: '',
    nomeClient: '',
    nacionalidade: '',
    idadeClient: ''
  });

  const navigate = useNavigate();

  const formatarCPF = (value) => {
    const cpfLimpo = value.replace(/\D/g, '');
    return cpfLimpo
      .replace(/^(\d{3})(\d)/, '$1.$2')
      .replace(/^(\d{3})\.(\d{3})(\d)/, '$1.$2.$3')
      .replace(/\.(\d{3})(\d)/, '.$1-$2')
      .slice(0, 14);
  };

  const handleCPFChange = (e) => {
    const formattedCPF = formatarCPF(e.target.value);
    setCliente({ ...cliente, cpfClient: formattedCPF });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    createClientes(cliente)
      .then(() => {
        navigate('/clientes');
      })
      .catch((error) => {
        console.error('Erro ao adicionar cliente', error);
      });
  };

  return (
    <div className="addcliente-container">
      <h1 className="addcliente-title">Adicionar Cliente</h1>
      <form className="addcliente-form" onSubmit={handleSubmit}>
        <div>
          <label>CPF:</label>
          <input
            type="text"
            value={cliente.cpfClient}
            onChange={handleCPFChange}
            required
            placeholder="123.456.789-00"
          />
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
        <button type="submit">Adicionar Cliente</button>
      </form>
    </div>
  );
};

export default AddCliente;
