import React, { useState, useEffect } from 'react';
import { getMercados, deleteMercados, deleteAtendimento } from '../services/api';
import { Link } from 'react-router-dom';
import '../css/Mercados.css';

const Mercados = () => {
  const [mercados, setMercados] = useState([]);

  useEffect(() => {
    getMercados()
      .then(response => {
        console.log('Resposta da API:', response.data);
        setMercados(response.data);
      })
      .catch(error => {
        console.error('Erro ao carregar mercados', error);
      });
  }, []);
  
  
  

  const handleDelete = async (id) => {
    try {
      // Primeiro, exclui os atendimentos relacionados ao mercado
      await deleteAtendimento(id);
  
      // Depois, exclui o mercado
      await deleteMercados(id);
  
      // Atualiza a lista de mercados no estado
      setMercados(mercados.filter(market => market.id !== id));
    } catch (error) {
      console.error('Erro ao excluir mercado e atendimentos', error);
    }
  };
  

  return (
    <div className="mercados-container">
      <h1 className="mercados-title">Lista de Mercados</h1>
        <div className="buttons-add-back">
          <Link to="/add-mercado">
            <button className="add-button">Adicionar Mercado</button>
          </Link>
          <Link to ="/">
            <button className="back-button">Voltar</button>
          </Link>
        </div>

      {mercados.length > 0 ? (
        <ul className="mercados-list">
          {mercados.map(market => (
            <li key={market.id} className="mercado-item">
              <h2 className="mercado-name">{market.nomeMerc}</h2>
              <p><strong>Endereço:</strong> {market.enderMerc}</p>
              <p><strong>Horário de Abertura:</strong> {market.horaAbertura}</p>
              <p><strong>Telefone:</strong> {market.telMerc}</p>
              <button 
                className="delete-button" 
                onClick={() => handleDelete(market.id)}
              >
                Excluir
              </button>
              <Link to={`/edit-mercado/${market.id}`}>
                <button className="edit-button">Editar</button>
              </Link>
            </li>
          ))}
        </ul>
      ) : (
        <p className="no-mercado-message">Não há mercados cadastrados.</p>
      )}
    </div>
  );
};

export default Mercados;
