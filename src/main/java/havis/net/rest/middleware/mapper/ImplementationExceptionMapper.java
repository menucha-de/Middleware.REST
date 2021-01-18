package havis.net.rest.middleware.mapper;

import havis.middleware.ale.base.exception.ImplementationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ImplementationExceptionMapper implements
		ExceptionMapper<ImplementationException> {
	@Override
	public Response toResponse(ImplementationException e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(e.getClass().getSimpleName() + ": " + e.getReason())
				.type(MediaType.TEXT_PLAIN).build();
	}
}