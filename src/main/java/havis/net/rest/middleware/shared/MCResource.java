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
import havis.middleware.ale.base.exception.NoSuchPathException;
import havis.middleware.ale.base.exception.SecurityException;
import havis.middleware.ale.base.exception.ValidationException;
import havis.middleware.ale.service.mc.MC;
import havis.middleware.ale.service.mc.MCSpec;
import havis.middleware.ale.service.mc.MCVersionSpec;
import havis.net.rest.shared.Resource;
import havis.net.rest.shared.data.SerializableValue;

public abstract class MCResource<M extends MCSpec> extends Resource {
	
	private final MC mc;
	private final String specPath;
	private final String versionPath;
	
	public MCResource(MC mc, String specPath, String versionPath) {
		this.mc = mc;
		this.specPath = specPath;
		this.versionPath = versionPath;
	}
	
	/**
	 * Return version information of the service
	 * 
	 * @return version information
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("version")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public MCVersionSpec getVersion() throws ImplementationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		return (MCVersionSpec) mc.get(versionPath);
	}

	/**
	 * Return a list of the defined specs.
	 * 
	 * @return List of specs which do not contain inner spec.
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("specs")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public List<M> getSpecs()
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		List<M> result = new ArrayList<M>();
		for (String id : mc.list(specPath)) {
			result.add(getSpec(id));
		}
		return result;
	}
	
	/**
	 * Return the definition of the spec with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the spec
	 * @return The Logical Reader definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("specs/{id}")
	@PermitAll
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public M getSpec(@PathParam("id") String id)
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		@SuppressWarnings("unchecked")
		M spec = (M) mc.get(specPath, id);
		spec.setId(id);
		return spec;
	}

	/**
	 * Update the definition of spec with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the spec which shall be updated
	 * @param spec
	 *            The spec definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("specs/{id}")
	@PermitAll
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setSpec(@PathParam("id") String id, M spec)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.update(specPath, id, spec);
	}

	/**
	 * Define a new spec
	 * 
	 * @param spec
	 *            The spec definition
	 * @return The ID of the new spec
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@POST
	@Path("specs")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public SerializableValue<String> addSpec(M spec)
			throws ImplementationException, ValidationException,
			NoSuchPathException, SecurityException {
		return new SerializableValue<String>(mc.add(specPath, spec));
	}

	/**
	 * Delete the spec with the specified ID
	 * 
	 * @param id
	 *            The ID of the spec which shall be deleted
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@DELETE
	@Path("specs/{id}")
	@PermitAll
	public void deleteSpec(@PathParam("id") String id)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.remove(specPath, id);
	}

	/**
	 * @return the specPath
	 */
	protected String getSpecPath() {
		return specPath;
	}

	/**
	 * @return the mc
	 */
	public MC getMC() {
		return mc;
	}
	
}
