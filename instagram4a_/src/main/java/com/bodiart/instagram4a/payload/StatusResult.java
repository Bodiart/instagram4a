package com.bodiart.instagram4a.payload;

import androidx.annotation.NonNull;

/**
 * Created by root on 08/06/17.
 */

public class StatusResult {
    @NonNull
    private String status;
    private String message;

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
