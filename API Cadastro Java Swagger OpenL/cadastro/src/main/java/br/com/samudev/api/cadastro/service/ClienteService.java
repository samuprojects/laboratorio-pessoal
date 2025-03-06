package br.com.samudev.api.cadastro.service;

import br.com.samudev.api.cadastro.exception.ApiException;
import br.com.samudev.api.cadastro.model.Cliente;
import br.com.samudev.api.cadastro.repository.ClienteRepository;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private final ClienteRepository clienteRepository = new ClienteRepository();

    public Cliente salvarCliente(Cliente cliente) {
        try {
            return clienteRepository.salvar(cliente);
        } catch (SQLException e) {
            throw new ApiException("Erro ao salvar cliente", e);
        }
    }

    public Cliente buscarCliente(Long id) {
        try {
            return clienteRepository.buscar(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente no banco de dados: " + e.getMessage());
            e.printStackTrace();
            throw new ApiException("Erro ao buscar cliente", e);
        }
    }

    public List<Cliente> buscarTodosClientes() {
        try {
            return clienteRepository.buscarTodos();
        } catch (SQLException e) {
            throw new ApiException("Erro ao buscar todos os clientes", e);
        }
    }

    public void atualizarCliente(Cliente cliente) {
        try {
            clienteRepository.atualizar(cliente);
        } catch (SQLException e) {
            throw new ApiException("Erro ao atualizar cliente", e);
        }
    }

    public void deletarCliente(Long id) {
        try {
            clienteRepository.deletar(id);
        } catch (SQLException e) {
            throw new ApiException("Erro ao deletar cliente", e);
        }
    }
}