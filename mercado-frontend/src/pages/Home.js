import React from 'react';
import { Link } from 'react-router-dom';
import '../css/Home.css';

const Home = () => {
  return (
    <div className="home-container">
      <h1>Bem-vindo ao Sistema</h1>
      <div>
        <h2>Gerenciamento de Mercados</h2>
        <p>Selecione um para Criar, Ver, Atualizar, Excluir:</p>
        <div>
          <Link to="/clientes">
            <button>Clientes</button>
          </Link>
          <Link to="/mercados">
            <button>Mercados</button>
          </Link>
          <Link to="/funcionarios">
            <button>Funcionários</button>
          </Link>
          <Link to="/produtos">
            <button>Produtos</button>
          </Link>
          <Link to="/compras">
            <button>Compras</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Home;
