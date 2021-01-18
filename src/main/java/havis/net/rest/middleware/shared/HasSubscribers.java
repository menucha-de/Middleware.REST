package havis.net.rest.middleware.shared;

import havis.middleware.ale.service.mc.MCSubscriberSpec;
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

public interface HasSubscribers {

	@OPTIONS
	@Path("specs/{id}/subscribers")
	void getSubscribersOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("specs/{id}/subscribers/{subId}")
	void getSubscriberIdOptions(MethodCallback<Void> callback);

	@GET
	@Path("specs/{id}/subscribers")
	void getSubscribers(@PathParam("id") String id,
			MethodCallback<List<MCSubscriberSpec>> callback);

	@GET
	@Path("specs/{id}/subscribers/{subId}")
	void getSubscriber(@PathParam("id") String id,
			@PathParam("subId") String subId,
			MethodCallback<MCSubscriberSpec> callback);

	@PUT
	@Path("specs/{id}/subscribers/{subId}")
	void setSubscriber(@PathParam("id") String id,
			@PathParam("subId") String subId, MCSubscriberSpec spec,
			MethodCallback<Void> callback);

	@POST
	@Path("specs/{id}/subscribers")
	void addSubscriber(@PathParam("id") String id, MCSubscriberSpec spec,
			MethodCallback<SerializableValue<String>> callback);

	@DELETE
	@Path("specs/{id}/subscribers/{subId}")
	void deleteSubscriber(@PathParam("id") String id,
			@PathParam("subId") String subId, MethodCallback<Void> callback);

}
