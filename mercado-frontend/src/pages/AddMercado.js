import React, { useState } from 'react';
import { createMercados } from '../services/api';
import { useNavigate } from 'react-router-dom';
import '../css/AddMercados.css';

const AddMercado = () => {
  const [nomeMerc, setNomeMerc] = useState('');
  const [enderMerc, setEnderMerc] = useState('');
  const [horaAbertura, setHoraAbertura] = useState('');
  const [telMerc, setTelMerc] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    const mercado = {
      nomeMerc,
      enderMerc,
      horaAbertura,
      telMerc,
    };
    createMercados(mercado)
      .then(() => {
        navigate('/mercados');
      })
      .catch((error) => {
        console.error('Erro ao criar mercado', error);
      });
  };

  return (
    <div className="addmercado-container">
      <h1 className="addmercado-title">Adicionar Mercado</h1>
      <form className="addmercado-form" onSubmit={handleSubmit}>
        <div>
          <label>Nome:</label>
          <input
            type="text"
            value={nomeMerc}
            onChange={(e) => setNomeMerc(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Endereço:</label>
          <input
            type="text"
            value={enderMerc}
            onChange={(e) => setEnderMerc(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Hora de Abertura:</label>
          <input
            type="text"
            value={horaAbertura}
            onChange={(e) => setHoraAbertura(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Telefone:</label>
          <input
            type="text"
            value={telMerc}
            onChange={(e) => setTelMerc(e.target.value)}
            required
          />
        </div>
        <button type="submit">Adicionar Mercado</button>
      </form>
    </div>
  );
};

export default AddMercado;
