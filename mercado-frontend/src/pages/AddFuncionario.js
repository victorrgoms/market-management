import React, { useState } from 'react';
import { createFuncionarios } from '../services/api';
import { useNavigate } from 'react-router-dom';
import '../css/AddFuncionarios.css';

const AddFuncionario = () => {
  const [funcionario, setFuncionario] = useState({
    cpfFunc: '',
    nomeFunc: '',
    telFunc: '',
    idadeFunc: ''
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
    setFuncionario({ ...funcionario, cpfFunc: formattedCPF });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    createFuncionarios(funcionario)
      .then(() => {
        navigate('/funcionarios');
      })
      .catch((error) => {
        console.error('Erro ao adicionar funcionário', error);
      });
  };

  return (
    <div className="addcliente-container">
      <h1 className="addcliente-title">Adicionar Funcionário</h1>
      <form className="addcliente-form" onSubmit={handleSubmit}>
        <div>
          <label>CPF:</label>
          <input
            type="text"
            value={funcionario.cpfFunc}
            onChange={handleCPFChange}
            required
            placeholder="123.456.789-00"
          />
        </div>
        <div>
          <label>Nome:</label>
          <input
            type="text"
            value={funcionario.nomeFunc}
            onChange={(e) => setFuncionario({ ...funcionario, nomeFunc: e.target.value })}
            required
          />
        </div>
        <div>
          <label>Telefone:</label>
          <input
            type="text"
            value={funcionario.telFunc}
            onChange={(e) => setFuncionario({ ...funcionario, telFunc: e.target.value })}
          />
        </div>
        <div>
          <label>Idade:</label>
          <input
            type="number"
            value={funcionario.idadeFunc}
            onChange={(e) => setFuncionario({ ...funcionario, idadeFunc: e.target.value })}
          />
        </div>
        <button className="addcliente-form button" type="submit">Adicionar Funcionário</button>
      </form>
    </div>
  );
};

export default AddFuncionario;
