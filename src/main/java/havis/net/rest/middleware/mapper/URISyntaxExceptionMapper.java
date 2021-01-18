package havis.net.rest.middleware.mapper;

import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class URISyntaxExceptionMapper implements ExceptionMapper<URISyntaxException> {
	@Override
	public Response toResponse(URISyntaxException ex) {
		return Response.status(Response.Status.BAD_REQUEST).entity(ex.getClass().getSimpleName() + ": " + ex.getReason()).type(MediaType.TEXT_PLAIN).build();
	}
}
