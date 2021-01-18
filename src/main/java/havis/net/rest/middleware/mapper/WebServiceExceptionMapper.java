package havis.net.rest.middleware.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.xml.ws.WebServiceException;

public class WebServiceExceptionMapper implements ExceptionMapper<WebServiceException> {
	@Override
	public Response toResponse(WebServiceException ex) {
		return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ex.getClass().getSimpleName() + ": " + ex.getMessage())
				.type(MediaType.TEXT_PLAIN).build();
	}
}
