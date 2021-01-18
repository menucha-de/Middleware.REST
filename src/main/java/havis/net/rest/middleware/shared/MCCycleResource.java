package havis.net.rest.middleware.shared;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import havis.middleware.ale.base.exception.ImplementationException;
import havis.middleware.ale.base.exception.NoSuchIdException;
import havis.middleware.ale.base.exception.NoSuchNameException;
import havis.middleware.ale.base.exception.NoSuchPathException;
import havis.middleware.ale.base.exception.ParameterException;
import havis.middleware.ale.base.exception.ParameterForbiddenException;
import havis.middleware.ale.base.exception.SecurityException;
import havis.middleware.ale.base.exception.ValidationException;
import havis.middleware.ale.service.IReports;
import havis.middleware.ale.service.mc.MC;
import havis.middleware.ale.service.mc.MCSpec;
import havis.middleware.ale.service.mc.MCSubscriberSpec;
import havis.net.rest.shared.data.SerializableValue;

public class MCCycleResource<M extends MCSpec, R extends IReports> extends MCResource<M> {

	private final String subscriberPath;

	public MCCycleResource(MC mc, String specPath, String versionPath, String subscriberPath) {
		super(mc, specPath, versionPath);
		this.subscriberPath = subscriberPath;
	}

	/**
	 * Return the result of the one-time execution of the cycle with the
	 * specified {@code id}
	 * 
	 * @param id
	 * @return Result of the one-time execution of the cycle
	 * @throws ParameterForbiddenException
	 * @throws ParameterException
	 * @throws ValidationException
	 * @throws NoSuchNameException
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Path("specs/{id}/report")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public R getReport(@PathParam("id") String id)
			throws ImplementationException, NoSuchIdException, NoSuchPathException, SecurityException,
			NoSuchNameException, ValidationException, ParameterException, ParameterForbiddenException {
		return (R) getMC().execute(getSpecPath(), id);
	}

	@POST
	@Path("report")
	@PermitAll
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML })
	public R getReport(R report) {
		return report;
	}
	
	/**
	 * Return a list of the defined Subscribers of the specified cycle
	 * 
	 * @param parent
	 *            The ID of the cycle
	 * @return List of Subscriber IDs
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("specs/{parent}/subscribers")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MCSubscriberSpec> getSubscribers(@PathParam("parent") String parent)
			throws ImplementationException, NoSuchIdException, NoSuchPathException, SecurityException {
		List<MCSubscriberSpec> result = new ArrayList<MCSubscriberSpec>();
		for (String id : getMC().list(subscriberPath, parent)) {
			result.add(getSubscriber(id, parent));
		}
		return result;
	}

	/**
	 * Return the Subscriber with the specfied {@code subId} of the specified
	 * cycle
	 * 
	 * @param id
	 *            The ID of the Subscriber
	 * @param parent
	 *            The ID of the cycle
	 * @return The Subscriber definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("specs/{parent}/subscribers/{id}")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public MCSubscriberSpec getSubscriber(@PathParam("id") String id, @PathParam("parent") String parent)
			throws ImplementationException, NoSuchIdException, NoSuchPathException, SecurityException {
		MCSubscriberSpec spec = (MCSubscriberSpec) getMC().get(subscriberPath, id, parent);
		spec.setId(id);
		return spec;
	}

	/**
	 * Update the subscriber with the specified {@code subId} of the specified
	 * cycle
	 * 
	 * @param parent
	 *            The ID of the cycle
	 * @param id
	 *            The ID of the Subscriber
	 * @param spec
	 *            The new Subscriber definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("specs/{parent}/subscribers/{id}")
	@PermitAll
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setSubscriber(@PathParam("id") String id, @PathParam("parent") String parent, MCSubscriberSpec spec)
			throws ImplementationException, ValidationException, NoSuchIdException, NoSuchPathException,
			SecurityException {
		getMC().update(subscriberPath, id, spec, parent);
	}

	/**
	 * Add a new Subscriber to the cycle with the specified {@code id}
	 * 
	 * @param parent
	 *            The ID of the cycle
	 * @param spec
	 *            The Subscriber definition
	 * @return The ID of the new Subscriber
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@POST
	@Path("specs/{parent}/subscribers")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> addSubscriber(@PathParam("parent") String parent, MCSubscriberSpec spec)
			throws ImplementationException, ValidationException, NoSuchPathException, SecurityException {
		return new SerializableValue<String>(getMC().add(subscriberPath, spec, parent));
	}

	/**
	 * Delete the subscriber with the specified {@code subId} from the Event
	 * Cycle
	 * 
	 * @param parent
	 *            The ID of the cycle
	 * @param id
	 *            The ID of the Subscriber which shall be deleted
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@DELETE
	@Path("specs/{parent}/subscribers/{id}")
	@PermitAll
	public void deleteSubscriber(@PathParam("id") String id, @PathParam("parent") String parent)
			throws ImplementationException, ValidationException, NoSuchIdException, NoSuchPathException,
			SecurityException {
		getMC().remove(subscriberPath, id, parent);
	}
}
