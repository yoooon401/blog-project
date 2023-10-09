package com.app.blog.project.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApiError {
    private String path;
    private String message;

    public static ApiError of(String path, String message) {
        return new ApiError(path, message);
    }
}