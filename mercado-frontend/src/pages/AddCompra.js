import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getProdutos, getAtendimentos, createCompra } from '../services/api';
import '../css/AddCompras.css';

const AddCompra = () => {
  const [compra, setCompra] = useState({
    idAtend: '',
    idProd: '',
    valorTotal: '',
    formaPagam: '',
    quantProd: '',
    enderEntrega: ''
  });

  const [produtos, setProdutos] = useState([]);
  const [atendimentos, setAtendimentos] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    getProdutos()
      .then((response) => setProdutos(response.data))
      .catch((error) => console.error('Erro ao carregar produtos', error));

    getAtendimentos()
      .then((response) => setAtendimentos(response.data))
      .catch((error) => console.error('Erro ao carregar atendimentos', error));
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    const compraData = { ...compra };
    createCompra(compraData)
      .then(() => {
        navigate('/compras');
      })
      .catch((error) => {
        console.error('Erro ao criar compra', error);
      });
  };

  return (
    <div className="adicionar-compra">
      <h1>Adicionar Compra</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Atendimento:</label>
          <select
            value={compra.idAtend}
            onChange={(e) => setCompra({ ...compra, idAtend: e.target.value })}
            required
          >
            <option value="">Selecione</option>
            {atendimentos.map((atend) => (
              <option key={atend.id} value={atend.id}>
                {atend.id} - {atend.nome}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Produto:</label>
          <select
            value={compra.idProd}
            onChange={(e) => setCompra({ ...compra, idProd: e.target.value })}
            required
          >
            <option value="">Selecione</option>
            {produtos.map((produto) => (
              <option key={produto.id} value={produto.id}>
                {produto.nomeProd} - {produto.valorProd}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Valor Total:</label>
          <input
            type="number"
            step="0.01"
            value={compra.valorTotal}
            onChange={(e) => setCompra({ ...compra, valorTotal: e.target.value })}
            required
          />
        </div>
        <div>
          <label>Forma de Pagamento:</label>
          <input
            type="text"
            value={compra.formaPagam}
            onChange={(e) => setCompra({ ...compra, formaPagam: e.target.value })}
            required
          />
        </div>
        <div>
          <label>Quantidade de Produto:</label>
          <input
            type="number"
            value={compra.quantProd}
            onChange={(e) => setCompra({ ...compra, quantProd: e.target.value })}
            required
          />
        </div>
        <div>
          <label>Endereço de Entrega:</label>
          <input
            type="text"
            value={compra.enderEntrega}
            onChange={(e) => setCompra({ ...compra, enderEntrega: e.target.value })}
            required
          />
        </div>
        <button type="submit">Adicionar Compra</button>
      </form>
    </div>
  );
};

export default AddCompra;
