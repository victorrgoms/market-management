import React, { useState, useEffect } from 'react';
import { getFuncionarios, deleteFuncionarios, deleteAtendimento } from '../services/api';
import { Link } from 'react-router-dom';
import '../css/Funcionarios.css';

const Funcionarios = () => {
  const [funcionarios, setFuncionarios] = useState([]);

  useEffect(() => {
    getFuncionarios()
      .then(response => {
        setFuncionarios(response.data);
      })
      .catch(error => console.error('Erro ao carregar funcionários', error));
  }, []);

  const handleDelete = async (id) => {
    try {
      // Primeiro, exclui os atendimentos relacionados ao funcionário
      await deleteAtendimento(id);
  
      // Depois, exclui o funcionário
      await deleteFuncionarios(id);
  
      // Atualiza a lista de funcionários no estado
      setFuncionarios(funcionarios.filter(funcionario => funcionario.id !== id));
    } catch (error) {
      console.error('Erro ao excluir funcionário e atendimentos', error);
    }
  };
  

  return (
    <div className="funcionarios-container">
      <h1 className="funcionarios-title">Lista de Funcionários</h1>
            <div className="buttons-add-back">
              <Link to="/add-funcionario">
                <button className="add-button">Adicionar Funcionario</button>
              </Link>
              <Link to ="/">
                <button className="back-button">Voltar</button>
              </Link>
            </div>

      {funcionarios.length > 0 ? (
        <ul className="funcionarios-list">
          {funcionarios.map(funcionario => (
            <li key={funcionario.id} className="funcionario-item">
              <h2 className="funcionario-name">{funcionario.nomeFunc}</h2>
              <p><strong>CPF:</strong> {funcionario.cpfFunc}</p>
              <p><strong>Telefone:</strong> {funcionario.telFunc}</p>
              <p><strong>Idade:</strong> {funcionario.idadeFunc}</p>
              <button 
                className="delete-button" 
                onClick={() => handleDelete(funcionario.id)}
              >
                Excluir
              </button>
              <Link to={`/edit-funcionario/${funcionario.id}`}>
                <button className="edit-button">Editar</button>
              </Link>
            </li>
          ))}
        </ul>
      ) : (
        <p className="no-funcionario-message">Não há funcionários cadastrados.</p>
      )}
    </div>
  );
};

export default Funcionarios;
