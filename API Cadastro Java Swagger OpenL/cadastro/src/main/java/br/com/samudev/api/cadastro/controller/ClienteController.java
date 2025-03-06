package br.com.samudev.api.cadastro.controller;

import br.com.samudev.api.cadastro.model.Cliente;
import br.com.samudev.api.cadastro.service.ClienteService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    private final ClienteService clienteService = new ClienteService();

    @POST
    public Response salvarCliente(Cliente cliente) {
        try {
            Cliente clienteSalvo = clienteService.salvarCliente(cliente);
            return Response.status(Response.Status.CREATED).entity(clienteSalvo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao parsear JSON").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarCliente(@PathParam("id") long id) {
        try {
            Cliente cliente = clienteService.buscarCliente(id);
            if (cliente != null) {
                return Response.ok(cliente).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor").build();
        }
    }

    @GET
    public Response buscarTodosClientes() {
        try {
            List<Cliente> clientes = clienteService.buscarTodosClientes();
            return Response.ok(clientes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCliente(@PathParam("id") long id, Cliente cliente) {
        try {
            cliente.setId(id);
            clienteService.atualizarCliente(cliente);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarCliente(@PathParam("id") long id) {
        try {
            clienteService.deletarCliente(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor").build();
        }
    }
}