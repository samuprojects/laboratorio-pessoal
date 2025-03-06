package br.com.samudev.api.cadastro;

import br.com.samudev.api.cadastro.controller.ClienteController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/clientes", new ClienteController());
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor iniciado na porta 8080");
    }
}