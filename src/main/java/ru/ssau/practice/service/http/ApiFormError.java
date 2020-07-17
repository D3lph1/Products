package ru.ssau.practice.service.http;

public class ApiFormError extends ApiError
{
    private final String field;

    public ApiFormError(String field, String message, Type type)
    {
        super(message, type);
        this.field = field;
    }

    public ApiFormError(String field, String message)
    {
        super(message);
        this.field = field;
    }

    public static ApiError success(String field, String message)
    {
        return new ApiFormError(field, message, Type.SUCCESS);
    }

    public static ApiError info(String field, String message)
    {
        return new ApiFormError(field, message, Type.INFO);
    }

    public static ApiError warning(String field, String message)
    {
        return new ApiFormError(field, message, Type.WARNING);
    }

    public static ApiError danger(String field, String message)
    {
        return new ApiFormError(field, message, Type.DANGER);
    }

    public String getField()
    {
        return field;
    }
}
