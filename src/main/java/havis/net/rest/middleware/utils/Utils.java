package havis.net.rest.middleware.utils;

import havis.net.rest.middleware.utils.model.Uri;
import havis.net.rest.shared.Resource;
import havis.net.rest.shared.data.SerializableValue;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("ale/utils")
public class Utils extends Resource {

	@PUT
	@Path("uri/create")
	@PermitAll
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Uri createUri(SerializableValue<String> uri)
			throws URISyntaxException {
		URI javaURI = new URI(uri.getValue());
		Uri gwtUri = new Uri(javaURI.getScheme(),
				javaURI.getSchemeSpecificPart(), javaURI.getAuthority(),
				javaURI.getUserInfo(), javaURI.getHost(), javaURI.getPort(),
				javaURI.getPath(), javaURI.getQuery(), javaURI.getRawQuery(),
				javaURI.getFragment());
		gwtUri.setUri(javaURI.toString());
		return gwtUri;
	}

	@PUT
	@Path("uri/get")
	@PermitAll
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getUri(Uri uri) throws URISyntaxException {
		return new SerializableValue<String>(new URI(uri.getScheme(),
				uri.getUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(),
				uri.getQuery(), uri.getFragment()).toString());
	}
}