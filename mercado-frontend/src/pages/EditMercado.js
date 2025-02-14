import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getMercados, updateMercados } from '../services/api';
import '../css/AddMercados.css';

const EditMercado = () => {
  const [nomeMerc, setNomeMerc] = useState('');
  const [enderMerc, setEnderMerc] = useState('');
  const [horaAbertura, setHoraAbertura] = useState('');
  const [telMerc, setTelMerc] = useState('');
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getMercados()
      .then((response) => {
        const mercado = response.data.find(mercado => mercado.id === parseInt(id));
        if (mercado) {
          setNomeMerc(mercado.nomeMerc);
          setEnderMerc(mercado.enderMerc);
          setHoraAbertura(mercado.horaAbertura);
          setTelMerc(mercado.telMerc);
        } else {
          console.error('Mercado não encontrado');
        }
      })
      .catch((error) => {
        console.error('Erro ao carregar mercado', error);
      });
  }, [id]);

  const handleSubmit = (event) => {
    event.preventDefault();
    const mercadoAtualizado = {
      nomeMerc,
      enderMerc,
      horaAbertura,
      telMerc,
    };
    updateMercados(id, mercadoAtualizado)
      .then(() => {
        navigate('/mercados');
      })
      .catch((error) => {
        console.error('Erro ao atualizar mercado', error);
      });
  };

  return (
    <div className="editmercado-container">
      <h1 className="editmercado-title">Editar Mercado</h1>
      <form className="editmercado-form" onSubmit={handleSubmit}>
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
        <button type="submit">Atualizar Mercado</button>
      </form>
    </div>
  );
};

export default EditMercado;
