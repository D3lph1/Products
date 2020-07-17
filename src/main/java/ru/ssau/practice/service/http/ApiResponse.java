package ru.ssau.practice.service.http;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonSerialize(using = ApiResponseSerializer.class)
public class ApiResponse
{
    public static final String STATUS_SUCCESS = "success";

    private boolean ok;

    private String status;

    private List<ApiError> errors = new ArrayList<>();

    private final Map<String, Object> parameters;

    public ApiResponse(boolean ok, String status, Map<String, Object> parameters)
    {
        this.ok = ok;
        this.status = status;
        this.parameters = parameters;
    }

    public ApiResponse(boolean ok, String status)
    {
        this(ok, status, new HashMap<>());
    }

    public static ApiResponse success(Map<String, Object> parameters)
    {
        return new ApiResponse(true, ApiResponse.STATUS_SUCCESS, parameters);
    }

    public static ApiResponse success()
    {
        return success(new HashMap<>());
    }

    public static ApiResponse fail(String status, Map<String, Object> parameters)
    {
        return new ApiResponse(false, status, parameters);
    }

    public static ApiResponse fail(String status)
    {
        return fail(status, new HashMap<>());
    }

    public ApiResponse addError(ApiError error)
    {
        errors.add(error);

        return this;
    }

    public boolean isOk()
    {
        return ok;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ApiError> getErrors()
    {
        return errors;
    }

    public ApiResponse add(String key, Object value)
    {
        parameters.put(key, value);

        return this;
    }

    public Map<String, Object> getParameters()
    {
        return parameters;
    }
}
