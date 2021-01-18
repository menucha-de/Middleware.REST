package havis.net.rest.middleware.mapper;

import havis.middleware.ale.base.exception.ParameterException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ParameterExceptionMapper implements
		ExceptionMapper<ParameterException> {
	@Override
	public Response toResponse(ParameterException e) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(e.getClass().getSimpleName() + ": " + e.getReason())
				.type(MediaType.TEXT_PLAIN).build();
	}
}