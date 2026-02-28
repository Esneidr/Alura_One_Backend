package foro.hub.apiRest.domain.DTO;

public record ApiResponse<T>(
        boolean success,
        String message,
        T result,
        Long count
) {
}
