package havis.net.rest.middleware.mapper;

import havis.middleware.ale.base.exception.NoSuchIdException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoSuchIdExceptionMapper implements
		ExceptionMapper<NoSuchIdException> {
	@Override
	public Response toResponse(NoSuchIdException e) {
		return Response.status(Response.Status.NOT_FOUND)
				.entity(e.getClass().getSimpleName() + ": " + e.getReason())
				.type(MediaType.TEXT_PLAIN).build();
	}
}