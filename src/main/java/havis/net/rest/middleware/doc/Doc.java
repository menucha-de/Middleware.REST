package havis.net.rest.middleware.doc;

import havis.middleware.ale.service.doc.DocumentService;
import havis.net.rest.shared.Resource;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("ale/doc")
public class Doc extends Resource {

	private DocumentService doc;

	public Doc(DocumentService doc) {
		this.doc = doc;
	}

	@GET
	@Path("{name}")
	@PermitAll
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public Response download(@PathParam("name") String name) {
		if (doc.hasDocument(name)) {
			return Response.ok(doc.getContent(name), doc.getMimetype(name))
					.header("Content-Length", Long.valueOf(doc.getSize(name)))
					.build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}