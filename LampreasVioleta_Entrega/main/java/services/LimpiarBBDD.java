package services;

import dao.*;

import java.sql.SQLException;

public class LimpiarBBDD {

    private final DetallePedidoDAO detallePedidoDAO;
    private final PedidoDAO pedidoDAO;
    private final DetalleClienteDAO detalleClienteDAO;
    private final ProductoDAO productoDAO;
    private final ClienteDAO clienteDAO;
    private final RepartidorDAO repartidorDAO;
    private final ComercialDAO comercialDAO;

    public LimpiarBBDD() {

        this.detallePedidoDAO = new DetallePedidoDAO();
        this.pedidoDAO = new PedidoDAO();
        this.detalleClienteDAO = new DetalleClienteDAO();
        this.productoDAO = new ProductoDAO();
        this.clienteDAO = new ClienteDAO();
        this.repartidorDAO = new RepartidorDAO();
        this.comercialDAO = new ComercialDAO();
    }

    public void clearAll() throws SQLException {

        // Borrado por orden para evitar problemas con FKs
        // 1) Tablas hijas
        detallePedidoDAO.clear();
        detalleClienteDAO.clear();

        // 2) Tabla intermedia
        pedidoDAO.clear();

        // 3) Tablas independientes
        productoDAO.clear();
        repartidorDAO.clear();
        comercialDAO.clear();
        clienteDAO.clear();
    }

}
