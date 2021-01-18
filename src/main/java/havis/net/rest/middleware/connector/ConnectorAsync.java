package havis.net.rest.middleware.connector;

import havis.middleware.ale.service.mc.MCConnectorSpec;
import havis.middleware.ale.service.mc.MCExitSpec;
import havis.net.rest.shared.data.SerializableValue;

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

@Path("../rest/ale/connector")
public interface ConnectorAsync extends RestService {

	@OPTIONS
	@Path("exits")
	void getExitSpecsOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("exits/{id}")
	void getExitIdOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("readers")
	void getReadersOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("readers/{id}")
	void getReaderIdOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("subscribers")
	void getSubscribersOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("subscribers/{id}")
	void getSubscriberIdOptions(MethodCallback<Void> callback);

	@GET
	@Path("exits")
	void getExitSpecs(MethodCallback<List<MCExitSpec>> callback);

	@GET
	@Path("exits/{id}")
	void getExit(@PathParam("id") String id, MethodCallback<MCExitSpec> callback);

	@PUT
	@Path("exits/{id}")
	void setExit(@PathParam("id") String id, MCExitSpec spec,
			MethodCallback<Void> callback);

	@POST
	@Path("exits")
	void addExit(MCExitSpec spec,
			MethodCallback<SerializableValue<String>> callback);

	@DELETE
	@Path("exits/{id}")
	void deleteExit(@PathParam("id") String id, MethodCallback<Void> callback);

	@GET
	@Path("readers")
	void getReaders(MethodCallback<List<MCConnectorSpec>> callback);

	@GET
	@Path("readers/{id}")
	void getReader(@PathParam("id") String id,
			MethodCallback<MCConnectorSpec> callback);

	@POST
	@Path("readers")
	void addReader(MCConnectorSpec spec,
			MethodCallback<SerializableValue<String>> callback);

	@GET
	@Path("subscribers")
	void getSubscribers(MethodCallback<List<MCConnectorSpec>> callback);

	@GET
	@Path("subscribers/{id}")
	void getSubscriber(@PathParam("id") String id,
			MethodCallback<MCConnectorSpec> callback);

	@PUT
	@Path("subscribers/{id}")
	void setSubscriber(@PathParam("id") String id, MCConnectorSpec spec,
			MethodCallback<Void> callback);
}