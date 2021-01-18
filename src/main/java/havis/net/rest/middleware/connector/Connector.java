package havis.net.rest.middleware.connector;

import havis.middleware.ale.base.exception.ImplementationException;
import havis.middleware.ale.base.exception.NoSuchIdException;
import havis.middleware.ale.base.exception.NoSuchPathException;
import havis.middleware.ale.base.exception.SecurityException;
import havis.middleware.ale.base.exception.ValidationException;
import havis.middleware.ale.service.mc.ImplementationExceptionResponse;
import havis.middleware.ale.service.mc.MC;
import havis.middleware.ale.service.mc.MCConnectorSpec;
import havis.middleware.ale.service.mc.MCExitSpec;
import havis.middleware.ale.service.mc.NoSuchIdExceptionResponse;
import havis.middleware.ale.service.mc.NoSuchPathExceptionResponse;
import havis.middleware.ale.service.mc.SecurityExceptionResponse;
import havis.net.rest.shared.Resource;
import havis.net.rest.shared.data.SerializableValue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceException;

@Path("ale/connector")
public class Connector extends Resource {

	private final static String exit = havis.middleware.ale.config.service.mc.Path.Connector.Exit;
	private final static String reader = havis.middleware.ale.config.service.mc.Path.Connector.Reader;
	private final static String subscriber = havis.middleware.ale.config.service.mc.Path.Connector.Subscriber;

	private MC mc;

	public Connector(MC mc) {
		this.mc = mc;
	}

	/**
	 * Return a list of the defined Exits
	 * 
	 * @return List of MCExitSpecs
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("exits")
	@RolesAllowed("admin")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MCExitSpec> getExitSpecs()
			throws ImplementationExceptionResponse, NoSuchIdExceptionResponse,
			NoSuchPathExceptionResponse, SecurityExceptionResponse,
			WebServiceException, ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		List<MCExitSpec> result = new ArrayList<MCExitSpec>();
		for (String id : mc.list(exit)) {
			result.add(getExitSpec(id));
		}
		return result;
	}

	/**
	 * Return the definition of the Exit with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Exit
	 * @return The Exit definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("exits/{id}")
	@RolesAllowed("admin")
	@Produces({ MediaType.APPLICATION_JSON })
	public MCExitSpec getExitSpec(@PathParam("id") String id)
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		MCExitSpec spec = (MCExitSpec) mc.get(exit, id);
		spec.setId(id);
		return spec;
	}

	/**
	 * Update the definition of Exit with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Exit which shall be updated
	 * @param spec
	 *            The Exit definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("exits/{id}")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setExitSpec(@PathParam("id") String id, MCExitSpec spec)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.update(exit, id, spec);
	}

	/**
	 * Define a new Exit
	 * 
	 * @param spec
	 *            The Exit definition
	 * @return The ID of the new Exit
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@POST
	@Path("exits")
	@RolesAllowed("admin")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> addExitSpec(MCExitSpec spec)
			throws ImplementationException, ValidationException,
			NoSuchPathException, SecurityException {
		return new SerializableValue<String>(mc.add(exit, spec));
	}

	/**
	 * Delete the Exit with the specified ID
	 * 
	 * @param id
	 *            The ID of the Exit which shall be deleted
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@DELETE
	@Path("exits/{id}")
	@RolesAllowed("admin")
	public void deleteExitSpec(@PathParam("id") String id)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.remove(exit, id);
	}

	/**
	 * Return a list of the defined Reader Connectors
	 * 
	 * @return List of MCConnectorSpecs
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("readers")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MCConnectorSpec> getReaderSpecs()
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		List<MCConnectorSpec> result = new ArrayList<MCConnectorSpec>();
		for (String id : mc.list(reader)) {
			result.add(getReaderSpec(id));
		}
		return result;
	}

	/**
	 * Return the definition of the Reader Connector with the specified
	 * {@code id}
	 * 
	 * @param id
	 *            The ID of the Reader Connector
	 * @return The Reader Connector definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("readers/{id}")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public MCConnectorSpec getReaderSpec(@PathParam("id") String id)
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		MCConnectorSpec spec = (MCConnectorSpec) mc.get(reader, id);
		spec.setId(id);
		return spec;
	}

	/**
	 * Update the definition of Reader Connector with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Reader Connector which shall be updated
	 * @param spec
	 *            The Reader Connector definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("readers/{id}")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setReaderSpec(@PathParam("id") String id, MCConnectorSpec spec)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.update(reader, id, spec);
	}

	/**
	 * Return a list of the Subscriber Connectors
	 * 
	 * @return List of MCConnectorSpecs
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("subscribers")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MCConnectorSpec> getSubscriberSpecs()
			throws ImplementationExceptionResponse, NoSuchIdExceptionResponse,
			NoSuchPathExceptionResponse, SecurityExceptionResponse,
			WebServiceException, ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		List<MCConnectorSpec> result = new ArrayList<MCConnectorSpec>();
		for (String id : mc.list(subscriber)) {
			result.add(getSubscriberSpec(id));
		}
		return result;
	}

	/**
	 * Return the Subscriber with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Subscriber Connector
	 * @return The Subscriber definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("subscribers/{id}")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public MCConnectorSpec getSubscriberSpec(@PathParam("id") String id)
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		MCConnectorSpec spec = (MCConnectorSpec) mc.get(subscriber, id);
		spec.setId(id);
		return spec;
	}

	/**
	 * Update the subscriber connector with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Subscriber Connector
	 * @param spec
	 *            The new Subscriber definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("subscribers/{id}")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setSubscriberSpec(@PathParam("id") String id,
			MCConnectorSpec spec) throws ImplementationException,
			ValidationException, NoSuchIdException, NoSuchPathException,
			SecurityException {
		mc.update(subscriber, id, spec);
	}
}