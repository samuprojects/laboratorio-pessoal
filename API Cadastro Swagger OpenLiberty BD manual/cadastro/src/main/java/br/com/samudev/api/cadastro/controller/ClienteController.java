package br.com.samudev.api.cadastro.controller;

import br.com.samudev.api.cadastro.model.Cliente;
import br.com.samudev.api.cadastro.service.ClienteService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

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
    @Operation(summary = "Salvar um novo cliente", description = "Salva um cliente no banco de dados.")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Cliente criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @APIResponse(responseCode = "400", description = "Erro ao parsear JSON")
    })
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
    @Operation(summary = "Buscar cliente por ID", description = "Busca um cliente pelo ID.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Cliente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @APIResponse(responseCode = "404", description = "Cliente não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response buscarCliente(@Parameter(description = "ID do cliente a ser buscado", required = true) @PathParam("id") long id) {
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
    @Operation(summary = "Buscar todos os clientes", description = "Busca todos os clientes.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Lista de clientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
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
    @Operation(summary = "Atualizar cliente", description = "Atualiza um cliente existente.")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Cliente atualizado com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response atualizarCliente(@Parameter(description = "ID do cliente a ser atualizado", required = true) @PathParam("id") long id, Cliente cliente) {
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
    @Operation(summary = "Deletar cliente", description = "Deleta um cliente.")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response deletarCliente(@Parameter(description = "ID do cliente a ser deletado", required = true) @PathParam("id") long id) {
        try {
            clienteService.deletarCliente(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor").build();
        }
    }
}