package io.github.igordonxiao.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

/**
 * Exception define in HTTP endpoint interaction
 */
public final class HttpException extends RuntimeException {
    private HttpException(String message) {
        super(message);
    }

    // ------------------------- Exception classes definition start -------------------

    /**
     * Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static final class BadRequest extends RuntimeException {
        private static final BadRequest DEFAULT = new BadRequest("请求错误");

        public BadRequest(@NotNull String message) {
            super(message);
        }
    }


    /**
     * Client is unauthorized
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static final class Unauthorized extends RuntimeException {
        private static final Unauthorized DEFAULT = new Unauthorized("未授权");

        public Unauthorized(@NotNull String message) {
            super(message);
        }
    }

    /**
     * Resources are not found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static final class NotFound extends RuntimeException {
        private static final NotFound DEFAULT = new NotFound("资源未找到");

        public NotFound(@NotNull String message) {
            super(message);
        }
    }

    /**
     * Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static final class ServerError extends RuntimeException {
        private static final ServerError DEFAULT = new ServerError("服务器错误");

        public ServerError(@NotNull String message) {
            super(message);
        }

    }

    // ------------------------- Exception classes definition start -------------------
    /**
     * Bad Request
     */
    public final static BadRequest BAD_REQUEST = BadRequest.DEFAULT;

    /**
     * Unauthorized
     */
    public final static Unauthorized UNAUTHORIZED = Unauthorized.DEFAULT;

    /**
     * Not Found
     */
    public final static NotFound NOT_FOUND = NotFound.DEFAULT;

    /**
     * Server Error
     */
    public final static ServerError SERVER_ERROR = ServerError.DEFAULT;
}
