package havis.net.rest.middleware.mapper;

import havis.middleware.ale.base.exception.ValidationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ValidationExceptionMapper implements
		ExceptionMapper<ValidationException> {
	@Override
	public Response toResponse(ValidationException e) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(e.getClass().getSimpleName() + ": " + e.getReason())
				.type(MediaType.TEXT_PLAIN).build();
	}
}