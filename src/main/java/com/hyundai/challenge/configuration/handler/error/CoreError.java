package com.hyundai.challenge.configuration.handler.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CoreError {
    private CoreError() {
    }

    public static final ResponseStatusException ERROR_IN_RETRIEVE_VEHICLE_NOT_FOUND
            = new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se encuentra información del vehiculo (Modelo / Versión) indicada" );
    public static final ResponseStatusException ERROR_IN_SERIALIZE_VEHICLE
            = new ResponseStatusException(HttpStatus.CONFLICT,"El objeto de tipo vehicleVersion tiene un problema al momento de serializar" );
    public static final ResponseStatusException ERROR_IN_DESERIALIZE_VEHICLE
            = new ResponseStatusException(HttpStatus.CONFLICT,"El objeto de tipo vehicleVersion tiene un problema al momento de deserializar" );

    public static final ResponseStatusException MODEL_NOT_FOUND
            = new ResponseStatusException(HttpStatus.BAD_REQUEST,"El Api no maneja el modelo requerido enviada" );

    public static final ResponseStatusException CRYPTOCURRENCY_NOT_FOUND
            = new ResponseStatusException(HttpStatus.BAD_REQUEST,"El Api no maneja la criptocurrency enviada" );

    public static final ResponseStatusException VERSION_NOT_FOUND
            = new ResponseStatusException(HttpStatus.NOT_FOUND,"La version especificada del modelo vehicular no existe" );

}