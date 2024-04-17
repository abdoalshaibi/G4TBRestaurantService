package itep.resturant.service.dao;

import itep.resturant.service.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse <T> {
    // The status of the API response, indicating success or failure.
    private boolean success;

    // The HTTP status code associated with the API response.
    private Integer httpStatus;

    // A human-readable message providing additional information about the API response.
    private String message;

    // An internal code or identifier for the API response, aiding in error identification.
    private String Code;

    // The data payload included in the API response, holding the actual content.
    private T data;

    /**
     * Creates an APIResponse for a successful operation.
     *
     * @param data             The data to include in the response.
     * @param responseHashMap  A map containing response messages.
     * @param key              The key corresponding to the desired response message.
     * @param <T>              The type of data to be included in the response.
     * @return An APIResponse indicating a successful operation.
     */
    public static <T> APIResponse<T> ok(T data, Map<String, String> responseHashMap, String key) {
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.OK.value())
                .success(Constant.RESULT_SUCCESS)
                .message(responseHashMap.get(key))
                .Code(key)
                .data(data)
                .build();
    }

    /**
     * Creates an APIResponse for a bad request operation.
     *
     * @param data             The data to include in the response.
     * @param responseHashMap  A map containing response messages.
     * @param key              The key corresponding to the desired response message.
     * @param <T>              The type of data to be included in the response.
     * @return An APIResponse indicating a failed operation.
     */
    public static <T> APIResponse<T> badRequest(T data, Map<String, String> responseHashMap, String key) {
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .success(Constant.RESULT_FAIL)
                .message(responseHashMap.get(key))
                .Code(key)
                .data(data)
                .build();
    }

    /**
     * Creates an APIResponse for a not found operation.
     *
     * @param data             The data to include in the response.
     * @param responseHashMap  A map containing response messages.
     * @param key              The key corresponding to the desired response message.
     * @param <T>              The type of data to be included in the response.
     * @return An APIResponse indicating a failed operation.
     */
    public static <T> APIResponse<T> notFound(T data, Map<String, String> responseHashMap, String key) {
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .success(Constant.RESULT_FAIL)
                .message(responseHashMap.get(key))
                .Code(key)
                .data(data)
                .build();
    }

    /**
     * Creates an APIResponse for a internal server error.
     *
     * @param data             The data to include in the response.
     * @param responseHashMap  A map containing response messages.
     * @param key              The key corresponding to the desired response message.
     * @param <T>              The type of data to be included in the response.
     * @return An APIResponse indicating a failed operation.
     */
    public static <T> APIResponse<T> internalServerError(T data, Map<String, String> responseHashMap, String key) {
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .success(Constant.RESULT_FAIL)
                .message(responseHashMap.get(key))
                .Code(key)
                .data(data)
                .build();
    }
}