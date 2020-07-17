package ru.ssau.practice.service.http;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

public class ApiResponseSerializer extends JsonSerializer<ApiResponse>
{
    @Override
    public void serialize(ApiResponse apiResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("ok", apiResponse.isOk());
        jsonGenerator.writeStringField("status", apiResponse.getStatus());
        jsonGenerator.writeArrayFieldStart("errors");
        for (ApiError error : apiResponse.getErrors()) {
            jsonGenerator.writeObject(error);
        }
        jsonGenerator.writeEndArray();
        for(Map.Entry<String, Object> entry : apiResponse.getParameters().entrySet()){
            jsonGenerator.writeObjectField(entry.getKey(), entry.getValue());
        }
        jsonGenerator.writeEndObject();
    }
}
