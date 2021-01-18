package havis.net.rest.middleware.mapper;

import havis.middleware.ale.base.exception.NoSuchNameException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoSuchNameExceptionMapper implements
		ExceptionMapper<NoSuchNameException> {
	@Override
	public Response toResponse(NoSuchNameException e) {
		return Response.status(Response.Status.NOT_FOUND)
				.entity(e.getClass().getSimpleName() + ": " + e.getReason())
				.type(MediaType.TEXT_PLAIN).build();
	}
}