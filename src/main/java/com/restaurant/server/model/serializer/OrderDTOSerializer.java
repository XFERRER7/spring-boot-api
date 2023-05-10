package com.restaurant.server.model.serializer;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.restaurant.server.model.DTO.OrderDTO;
import com.restaurant.server.model.DTO.ItemDTO;

public class OrderDTOSerializer extends JsonSerializer<OrderDTO> {

    @Override
    public void serialize(OrderDTO orderDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", orderDTO.getId());
        jsonGenerator.writeNumberField("amount", orderDTO.getAmount());
        jsonGenerator.writeStringField("createdAt", orderDTO.getCreatedAt().toString());
        jsonGenerator.writeStringField("clientName", orderDTO.getClientName());
        List<ItemDTO> items = orderDTO.getItems();
        jsonGenerator.writeArrayFieldStart("items");
        for (ItemDTO item : items) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", item.getName());
            jsonGenerator.writeNumberField("quantity", item.getQuantity());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
