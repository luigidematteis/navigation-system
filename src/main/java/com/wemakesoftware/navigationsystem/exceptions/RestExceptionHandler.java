package com.wemakesoftware.navigationsystem.exceptions;

import com.wemakesoftware.navigationsystem.entities.dto.MobileStationLocation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MobileStationNotFoundException.class)
    public ResponseEntity<MobileStationLocation> handleMobileStationNotFoundException(MobileStationNotFoundException ex) {
        MobileStationLocation location = new MobileStationLocation();
        location.setErrorCode(HttpStatus.NOT_FOUND.value());
        location.setErrorDescription(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(location);
    }
}
