package com.restaurant.server.controller;

import com.restaurant.server.model.*;
import com.restaurant.server.model.DTO.OrderDTO;
import com.restaurant.server.model.DTO.OrderPdfDTO;
import com.restaurant.server.model.DTO.OrderRequestDto;
import com.restaurant.server.service.OrderService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto orderRequest) {

        try {
            orderService.createOrder(orderRequest.getClientId(), orderRequest.getItems());
            return ResponseEntity.ok("{\"message\": \"Order created\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("get-all")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("get-report")
    public void getReport(HttpServletResponse response) throws JRException, IOException {

        List<OrderPdfDTO> orders = orderService.getReport();

        String filePath = "src\\main\\resources\\templates\\orderReport.jrxml";

        JasperReport report = JasperCompileManager.compileReport(filePath);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("orderDataSet", dataSource);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=relatorio.pdf");

        try (OutputStream out = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(print, out);
            out.flush();
        }

    }

}
