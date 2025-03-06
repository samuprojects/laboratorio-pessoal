package br.com.samudev.api.cadastro.controller;

import br.com.samudev.api.cadastro.exception.ApiException;
import br.com.samudev.api.cadastro.model.Cliente;
import br.com.samudev.api.cadastro.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteController implements HttpHandler {
    private final ClienteService clienteService = new ClienteService();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Pattern idPattern = Pattern.compile("/clientes/(\\d+)");

    public ClienteController() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String method = exchange.getRequestMethod();
            URI uri = exchange.getRequestURI();
            String path = uri.getPath();

            System.out.println("Método HTTP: " + method);
            System.out.println("URI: " + uri);
            System.out.println("Caminho: " + path);

            Matcher matcher = idPattern.matcher(path);
            System.out.println("Matcher encontrou ID: " + matcher.matches());

            if (matcher.matches()) {
                long id = Long.parseLong(matcher.group(1));
                System.out.println("ID extraído: " + id);

                if ("GET".equals(method)) {
                    buscarCliente(exchange, id);
                } else if ("PUT".equals(method)) {
                    atualizarCliente(exchange, id);
                } else if ("DELETE".equals(method)) {
                    deletarCliente(exchange, id);
                } else {
                    exchange.sendResponseHeaders(405, -1);
                }
            } else if ("/clientes".equals(path) && "POST".equals(method)) {
                salvarCliente(exchange);
            } else if ("/clientes".equals(path) && "GET".equals(method)) {
                buscarTodosClientes(exchange);
            } else {
                exchange.sendResponseHeaders(404, -1);
            }
        } catch (ApiException e) {
            String response = objectMapper.writeValueAsString(e.getMessage());
            exchange.sendResponseHeaders(500, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (NumberFormatException e) {
            String response = objectMapper.writeValueAsString("Id inválido");
            exchange.sendResponseHeaders(400, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (Exception e) {
            String response = objectMapper.writeValueAsString("Erro interno do servidor");
            exchange.sendResponseHeaders(500, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private void salvarCliente(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        try {
            Cliente cliente = objectMapper.readValue(requestBody, Cliente.class);
            Cliente clienteSalvo = clienteService.salvarCliente(cliente);
            String response = objectMapper.writeValueAsString(clienteSalvo);
            exchange.sendResponseHeaders(201, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch(Exception e){
            String response = objectMapper.writeValueAsString("Erro ao parsear JSON");
            exchange.sendResponseHeaders(400, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private void buscarCliente(HttpExchange exchange, long id) throws IOException {
        try {
            Cliente cliente = clienteService.buscarCliente(id);
            if (cliente != null) {
                String response = objectMapper.writeValueAsString(cliente);
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(404, -1);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
            e.printStackTrace();
            String response = objectMapper.writeValueAsString("Erro interno do servidor");
            exchange.sendResponseHeaders(500, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private void buscarTodosClientes(HttpExchange exchange) throws IOException {
        try {
            List<Cliente> clientes = clienteService.buscarTodosClientes();
            String response = objectMapper.writeValueAsString(clientes);
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os clientes: " + e.getMessage());
            e.printStackTrace();
            String response = objectMapper.writeValueAsString("Erro interno do servidor");
            exchange.sendResponseHeaders(500, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private void atualizarCliente(HttpExchange exchange, long id) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        Cliente cliente = objectMapper.readValue(requestBody, Cliente.class);
        cliente.setId(id);
        clienteService.atualizarCliente(cliente);
        exchange.sendResponseHeaders(204, -1);
    }

    private void deletarCliente(HttpExchange exchange, long id) throws IOException {
        clienteService.deletarCliente(id);
        exchange.sendResponseHeaders(204, -1);
    }
}