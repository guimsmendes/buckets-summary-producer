package br.com.guimsmendes.entrypoint.exception;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class ResourceExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        log.error(exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }
}
