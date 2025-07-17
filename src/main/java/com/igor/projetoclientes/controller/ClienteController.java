package com.igor.projetoclientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.projetoclientes.dto.ClienteCadastroDTO;
import com.igor.projetoclientes.model.Cliente;
import com.igor.projetoclientes.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    // listar todos os clientes
    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public List<Cliente> listarTodosClientes() {
        return clienteService.listarTodosClientes();
    }

    // listar cliente por id
    @Operation(summary = "Listar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.listarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    // cadastrar cliente
    @Operation(summary = "Cadastrar cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody ClienteCadastroDTO clienteDTO) {
        //Cliente clienteCadastrado = clienteService.cadastrarCliente(clienteDTO);
        //return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
        try {
            // Converter o DTO para o modelo Cliente
            Cliente cliente = new Cliente(
                clienteDTO.getNome(),
                clienteDTO.getCpf(),
                clienteDTO.getEmail(),
                clienteDTO.getDataNascimento()
            );
            Cliente clienteCadastrado = clienteService.cadastrarCliente(cliente);
            return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // atualizar cliente
    @Operation(summary = "Atualizar cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }


}
