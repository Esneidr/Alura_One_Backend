package foro.hub.apiRest.domain.response;

import foro.hub.apiRest.domain.DTO.ApiResponse;

public class ResponseBuilder {
    public static <T> ApiResponse<T> success(String message, T data) {
        Long count = null;

        if (data instanceof java.util.List<?> list) {
            count = (long) list.size();
        } else if (data != null) {
            count = 1L;
        } else {
            count = 0L;
        }

        return new ApiResponse<>(true, message, data, count);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, 0L);
    }
}
