package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.ClienteRequestDTO;
import com.coffeemanager.coffeemanager.dto.ClienteResponseDTO;
import com.coffeemanager.coffeemanager.entity.Cliente;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDTO crearCliente(
            ClienteRequestDTO request) {

        if (clienteRepository.existsByIdentificacion(
                request.getIdentificacion())) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe un cliente con la identificación "
                            + request.getIdentificacion()
            );
        }

        Cliente cliente = new Cliente();

        cliente.setTipoCliente(request.getTipoCliente());
        cliente.setNombre(request.getNombre());
        cliente.setIdentificacion(request.getIdentificacion());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setDireccion(request.getDireccion());

        if (request.getEstado() == null
                || request.getEstado().isBlank()) {

            cliente.setEstado("ACTIVO");

        } else {

            cliente.setEstado(request.getEstado());
        }

        Cliente clienteGuardado =
                clienteRepository.save(cliente);

        return convertirAResponseDTO(clienteGuardado);
    }

    public List<ClienteResponseDTO> listarClientes() {

        return clienteRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public ClienteResponseDTO obtenerCliente(Integer id) {

        Cliente cliente =
                clienteRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El cliente con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(cliente);
    }

    public ClienteResponseDTO actualizarCliente(
            Integer id,
            ClienteRequestDTO request) {

        Cliente cliente =
                clienteRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El cliente con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        if (clienteRepository
                .existsByIdentificacionAndIdClienteNot(
                        request.getIdentificacion(),
                        id)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe otro cliente con la identificación "
                            + request.getIdentificacion()
            );
        }

        cliente.setTipoCliente(request.getTipoCliente());
        cliente.setNombre(request.getNombre());
        cliente.setIdentificacion(request.getIdentificacion());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setDireccion(request.getDireccion());

        if (request.getEstado() == null
                || request.getEstado().isBlank()) {

            cliente.setEstado("ACTIVO");

        } else {

            cliente.setEstado(request.getEstado());
        }

        Cliente clienteActualizado =
                clienteRepository.save(cliente);

        return convertirAResponseDTO(clienteActualizado);
    }

    public void eliminarCliente(Integer id) {

        Cliente cliente =
                clienteRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El cliente con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        clienteRepository.delete(cliente);
    }

    private ClienteResponseDTO convertirAResponseDTO(
            Cliente cliente) {

        return new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getTipoCliente(),
                cliente.getNombre(),
                cliente.getIdentificacion(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getDireccion(),
                cliente.getEstado()
        );
    }
}