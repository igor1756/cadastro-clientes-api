package com.igor.projetoclientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.projetoclientes.dao.ClienteDAO;
import com.igor.projetoclientes.model.Cliente;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    
    private final ClienteDAO clienteDAO;
    
    @Autowired
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        // Lógica de validação e cadastro do cliente
        if (clienteDAO.findByCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        if (clienteDAO.findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        return clienteDAO.save(cliente); // metodo implicito do JPA
    }

    // metodo listar todos os clientes
    @Transactional
    public List<Cliente> listarTodosClientes() {
        return clienteDAO.findAll();
    }

    // metodo listar cliente por id
    @Transactional
    public Cliente listarClientePorId(Long id) {
        return clienteDAO.findById(id).orElse(null);
    }

    // metodo atualizar cliente
    @Transactional
    public Cliente atualizarCliente(Long id, Cliente cliente) {
        
        // 1. Verificar se o cliente existe
        return clienteDAO.findById(id) // retorna um Optional<Cliente>
            // 2. Se o cliente existe, atualizar os dados (optional.isPresent() é verdadeiro se o Optional não está vazio)
            .map(clienteExistente -> {
                // 3. Validar os dados do cliente
                if (cliente.getCpf() != null && !cliente.getCpf().equals(clienteExistente.getCpf()) && clienteDAO.findByCpf(cliente.getCpf()).isPresent()) {
                    throw new IllegalArgumentException("CPF já cadastrado");
                }
                if (cliente.getEmail() != null && !cliente.getEmail().equals(clienteExistente.getEmail()) && clienteDAO.findByEmail(cliente.getEmail()).isPresent()) {
                    throw new IllegalArgumentException("Email já cadastrado");
                }
                // 4. Atualizar os dados do cliente
                clienteExistente.setNome(cliente.getNome());
                clienteExistente.setCpf(cliente.getCpf());
                clienteExistente.setEmail(cliente.getEmail());
                clienteExistente.setDataNascimento(cliente.getDataNascimento());
                // 5. Salvar o cliente atualizado
                return clienteDAO.save(clienteExistente);
            })
            // 6. Se o cliente não existe, retornar exceção
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: ID: " + id));
    }

    // metodo deletar cliente
    @Transactional
    public void deletarCliente(Long id) {
        // 1. Verificar se o cliente existe
        if (!clienteDAO.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado: ID: " + id);
        }
        clienteDAO.deleteById(id);
    }


    // Outros métodos relacionados ao cliente
}
