import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

export const getMercados = () => api.get('/mercados');
export const createMercados = (data) => api.post('/mercados', data);
export const updateMercados = (id, data) => api.put(`/mercados/atualizar/${id}`, data);
export const deleteMercados = (id) => api.delete(`/mercados/${id}`);

export const getClientes = () => api.get('/clientes');
export const createClientes = (data) => api.post('/clientes', data);
export const updateClientes = (id, data) => api.put(`/clientes/atualizar/${id}`, data);
export const deleteClientes = (id) => api.delete(`/clientes/${id}`);

export const getFuncionarios = () => api.get('/funcionarios');
export const createFuncionarios = (data) => api.post('/funcionarios', data);
export const updateFuncionarios = (id, data) => api.put(`/funcionarios/atualizar/${id}`, data);
export const deleteFuncionarios = (id) => api.delete(`/funcionarios/${id}`);

export const getProdutos = () => api.get('/produtos');
export const createProdutos = (data) => api.post('/produtos', data);
export const updateProdutos = (id, data) => api.put(`/produtos/atualizar/${id}`, data);
export const deleteProdutos = (id) => api.delete(`/produtos/${id}`);

export const getCompras = () => api.get('/compras');
export const createCompra = (data) => api.post('/compras', data);
export const updateCompra = (id, data) => api.put(`/compras/atualizar/${id}`, data);
export const deleteCompra = (id) => api.delete(`/compras/${id}`);

export const getAtendimentos = () => api.get('/atendimentos');
export const createAtendimento = (data) => api.post('/atendimentos', data);
export const updateAtendimento = (id, data) => api.put(`/atendimentos/atualizar/${id}`, data);
export const deleteAtendimento = (id) => api.delete(`/atendimentos/${id}`);

export const getHistoricoCompras = () => api.get('/historico-compra');
export const createHistoricoCompra = (data) => api.post('/historico-compra', data);
export const updateHistoricoCompra = (id, data) => api.put(`/historico-compra/atualizar/${id}`, data);
export const deleteHistoricoCompra = (id) => api.delete(`/historico-compra/${id}`);

