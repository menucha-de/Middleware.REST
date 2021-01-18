package havis.net.rest.middleware.utils;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;

import havis.net.rest.middleware.utils.model.Uri;
import havis.net.rest.shared.data.SerializableValue;
import havis.net.rest.shared.data.ServiceAsync;

@Path("../rest/ale/utils")
public interface UtilsAsync extends ServiceAsync {

	@OPTIONS
	@Path("uri/create")
	void getUriCreateOptions(MethodCallback<Void> callback);
	
	@OPTIONS
	@Path("uri/get")
	void getUriGetOptions(MethodCallback<Void> callback);

	@PUT
	@Path("uri/create")
	void createUri(SerializableValue<String> uri, MethodCallback<Uri> callback);

	@PUT
	@Path("uri/get")
	void getUri(Uri uri, MethodCallback<SerializableValue<String>> callback);
}
