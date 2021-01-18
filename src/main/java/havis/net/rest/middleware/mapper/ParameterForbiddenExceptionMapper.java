package havis.net.rest.middleware.mapper;

import havis.middleware.ale.base.exception.ParameterForbiddenException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ParameterForbiddenExceptionMapper implements
		ExceptionMapper<ParameterForbiddenException> {
	@Override
	public Response toResponse(ParameterForbiddenException e) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(e.getClass().getSimpleName() + ": " + e.getReason())
				.type(MediaType.TEXT_PLAIN).build();
	}
}