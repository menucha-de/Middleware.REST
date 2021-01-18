package havis.net.rest.middleware.mapper;

import havis.middleware.ale.base.exception.NoSuchPropertyException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoSuchPropertyExceptionMapper implements
		ExceptionMapper<NoSuchPropertyException> {
	@Override
	public Response toResponse(NoSuchPropertyException e) {
		return Response.status(Response.Status.NOT_FOUND)
				.entity(e.getClass().getSimpleName() + ": " + e.getReason())
				.type(MediaType.TEXT_PLAIN).build();
	}
}