package havis.net.rest.middleware.shared;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.XmlCallback;

import havis.middleware.ale.service.mc.MCSpec;
import havis.net.rest.shared.data.SerializableValue;

public interface HasSpecs<T extends MCSpec> extends RestService {
	@GET
	@Path("")
	void getList(MethodCallback<List<T>> callback);

	@POST
	@Path("")
	void add(T spec, MethodCallback<SerializableValue<String>> callback);

	@OPTIONS
	@Path("")
	void getListOptions(MethodCallback<Void> callback);

	@GET
	@Path("{id}")
	void get(@PathParam("id") String id, MethodCallback<T> callback);

	@GET
	@Path("{id}")
	void get(@PathParam("id") String id, XmlCallback callback);

	@PUT
	@Path("{id}")
	void set(@PathParam("id") String id, T spec, MethodCallback<Void> callback);

	@OPTIONS
	@Path("{id}")
	void setOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("{id}")
	void getOptions(MethodCallback<Void> callback);

	@DELETE
	@Path("{id}")
	void delete(@PathParam("id") String id, MethodCallback<Void> callback);

}
