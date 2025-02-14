import React, { useState, useEffect } from 'react';
import { getFuncionarios, updateFuncionarios } from '../services/api';
import { useNavigate, useParams } from 'react-router-dom';
import '../css/AddFuncionarios.css';

const EditFuncionario = () => {
  const [funcionario, setFuncionario] = useState({
    nomeFunc: '',
    cpfFunc: '',
    telFunc: '',
    idadeFunc: ''
  });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getFuncionarios()
      .then(response => {
        const funcionarioEncontrado = response.data.find(f => f.id === parseInt(id));
        if (funcionarioEncontrado) {
          setFuncionario(funcionarioEncontrado);
        } else {
          console.error('Funcionário não encontrado');
        }
      })
      .catch(error => {
        console.error('Erro ao carregar funcionário', error);
      });
  }, [id]);

  const handleSubmit = (e) => {
    e.preventDefault();
    updateFuncionarios(id, funcionario)
      .then(() => {
        navigate('/funcionarios');
      })
      .catch(error => {
        console.error('Erro ao atualizar funcionário', error);
      });
  };

  return (
    <div className="editcliente-container">
      <h1 className="editcliente-title">Editar Funcionário</h1>
      <form className="editcliente-form" onSubmit={handleSubmit}>
        <div>
          <label>CPF:</label>
          <p>{funcionario.cpfFunc}</p>
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
        <button className="editcliente-form button" type="submit">Atualizar Funcionário</button>
      </form>
    </div>
  );
};

export default EditFuncionario;
