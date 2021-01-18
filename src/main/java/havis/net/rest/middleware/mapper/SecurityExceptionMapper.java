package havis.net.rest.middleware.mapper;

import havis.middleware.ale.base.exception.SecurityException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class SecurityExceptionMapper implements
		ExceptionMapper<SecurityException> {
	@Override
	public Response toResponse(SecurityException e) {
		return Response.status(Response.Status.FORBIDDEN)
				.entity(e.getClass().getSimpleName() + ": " + e.getReason())
				.type(MediaType.TEXT_PLAIN).build();
	}
}