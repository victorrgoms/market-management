import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Clientes from './pages/Clientes';
import AddCliente from './pages/AddCliente';
import EditCliente from './pages/EditCliente';
import Mercados from './pages/Mercados';
import AddMercado from './pages/AddMercado';
import EditMercado from './pages/EditMercado';
import Funcionarios from './pages/Funcionarios';
import AddFuncionario from './pages/AddFuncionario';
import EditFuncionario from './pages/EditFuncionario';
import Produtos from './pages/Produtos';
import AddProduto from './pages/AddProduto';
import EditProduto from './pages/EditProduto';
import Compras from './pages/Compras';
import AddCompra from './pages/AddCompra';
import EditCompra from './pages/EditCompra';
import Home from './pages/Home';

const App = () => {
  return (
    <Router>
      <Routes>
        {/* Página principal */}
        <Route path="/" element={<Home />} />

        {/* Rotas de Mercados */}
        <Route path="/mercados" element={<Mercados />} />
        <Route path="/add-mercado" element={<AddMercado />} />
        <Route path="/edit-mercado/:id" element={<EditMercado />} />
        
        {/* Rotas de Clientes */}
        <Route path="/clientes" element={<Clientes />} />
        <Route path="/add-cliente" element={<AddCliente />} />
        <Route path="/edit-cliente/:id" element={<EditCliente />} />
        
        {/* Rotas de Funcionários */}
        <Route path="/funcionarios" element={<Funcionarios />} />
        <Route path="/add-funcionario" element={<AddFuncionario />} />
        <Route path="/edit-funcionario/:id" element={<EditFuncionario />} />
        
        {/* Rotas de Produtos */}
        <Route path="/produtos" element={<Produtos />} />
        <Route path="/add-produto" element={<AddProduto />} />
        <Route path="/edit-produto/:id" element={<EditProduto />} />
        
        {/* Rotas de Compras */}
        <Route path="/compras" element={<Compras />} />
        <Route path="/add-compra" element={<AddCompra />} />
        <Route path="/edit-compra/:id" element={<EditCompra />} />
      </Routes>
    </Router>
  );
};

export default App;
