package ru.ssau.practice.service.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiError
{
    private final String message;

    @JsonIgnore
    private final Type type;

    public ApiError(String message, Type type)
    {
        this.message = message;
        this.type = type;
    }

    public static ApiError success(String message)
    {
        return new ApiError(message, Type.SUCCESS);
    }

    public static ApiError info(String message)
    {
        return new ApiError(message, Type.INFO);
    }

    public static ApiError warning(String message)
    {
        return new ApiError(message, Type.WARNING);
    }

    public static ApiError danger(String message)
    {
        return new ApiError(message, Type.DANGER);
    }

    public ApiError(String message)
    {
        this(message, Type.INFO);
    }

    public String getMessage()
    {
        return message;
    }

    @JsonProperty("type")
    public String getStringType()
    {
        return type.name().toLowerCase();
    }

    public enum Type
    {
        SUCCESS,
        INFO,
        WARNING,
        DANGER
    }
}
