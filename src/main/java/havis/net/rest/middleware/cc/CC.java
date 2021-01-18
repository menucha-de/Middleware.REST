package havis.net.rest.middleware.cc;

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
import havis.middleware.ale.service.cc.CCParameterListEntry;
import havis.middleware.ale.service.cc.CCReports;
import havis.middleware.ale.service.mc.MC;
import havis.middleware.ale.service.mc.MCAssociationSpec;
import havis.middleware.ale.service.mc.MCCacheSpec;
import havis.middleware.ale.service.mc.MCCommandCycleSpec;
import havis.middleware.ale.service.mc.MCRandomSpec;
import havis.net.rest.middleware.shared.MCCycleResource;
import havis.net.rest.shared.data.SerializableValue;

@Path("ale/cc")
public class CC extends MCCycleResource<MCCommandCycleSpec, CCReports> {

	private final static String version = havis.middleware.ale.config.service.mc.Path.Service.CC.Version;
	private final static String path = havis.middleware.ale.config.service.mc.Path.Service.CC.CommandCycle;
	private final static String subscriber = havis.middleware.ale.config.service.mc.Path.Service.CC.Subscriber;
	private final static String cache = havis.middleware.ale.config.service.mc.Path.Service.CC.Cache;
	private final static String association = havis.middleware.ale.config.service.mc.Path.Service.CC.Association;
	private final static String random = havis.middleware.ale.config.service.mc.Path.Service.CC.Random;

	private MC mc;

	public CC(MC mc) {
		super(mc, path, version, subscriber);
		this.mc = mc;
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
	@POST
	@Path("specs/{id}/report")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public CCReports getReport(@PathParam("id") String id, List<CCParameterListEntry> parameters)
			throws ImplementationException, NoSuchIdException, NoSuchPathException, SecurityException,
			NoSuchNameException, ValidationException, ParameterException, ParameterForbiddenException {
		return (CCReports) mc.execute(getSpecPath(), id, parameters);
	}
	/**
	 * Return a list of the defined Caches
	 * 
	 * @return List of MCCacheSpecs
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("caches")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MCCacheSpec> getCacheSpecs() throws ImplementationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		List<MCCacheSpec> result = new ArrayList<MCCacheSpec>();
		for (String id : mc.list(cache)) {
			result.add(getCacheSpec(id));
		}
		return result;
	}

	/**
	 * Return the definition of the Cache with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Cache
	 * @return The Cache definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("caches/{id}")
	@PermitAll
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public MCCacheSpec getCacheSpec(@PathParam("id") String id)
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		MCCacheSpec spec = (MCCacheSpec) mc.get(cache, id);
		spec.setId(id);
		return spec;
	}

	/**
	 * Update the definition of Cache with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Cache which shall be updated
	 * @param spec
	 *            The Cache definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("caches/{id}")
	@PermitAll
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setCacheSpec(@PathParam("id") String id, MCCacheSpec spec)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.update(cache, id, spec);
	}

	/**
	 * Define a new Cache
	 * 
	 * @param spec
	 *            The Cache definition
	 * @return The ID of the new Cache
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@POST
	@Path("caches")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public SerializableValue<String> addCacheSpec(MCCacheSpec spec)
			throws ImplementationException, ValidationException,
			NoSuchPathException, SecurityException {
		return new SerializableValue<String>(mc.add(cache, spec));
	}

	/**
	 * Delete the Cache with the specified ID
	 * 
	 * @param id
	 *            The ID of the Cache which shall be deleted
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@DELETE
	@Path("caches/{id}")
	@PermitAll
	public void deleteCacheSpec(@PathParam("id") String id)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.remove(cache, id);
	}

	/**
	 * Return a list of the defined Associations
	 * 
	 * @return List of MCAssociationSpecs
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("associations")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MCAssociationSpec> getAssociationSpecs()
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		List<MCAssociationSpec> result = new ArrayList<MCAssociationSpec>();
		for (String id : mc.list(association)) {
			result.add(getAssociationSpec(id));
		}
		return result;
	}

	/**
	 * Return the definition of the Association with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Association
	 * @return The Association definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("associations/{id}")
	@PermitAll
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public MCAssociationSpec getAssociationSpec(@PathParam("id") String id)
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		MCAssociationSpec spec = (MCAssociationSpec) mc.get(association, id);
		spec.setId(id);
		return spec;
	}

	/**
	 * Update the definition of Association with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Association which shall be updated
	 * @param spec
	 *            The Association definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("associations/{id}")
	@PermitAll
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setAssociationSpec(@PathParam("id") String id,
			MCAssociationSpec spec) throws ImplementationException,
			ValidationException, NoSuchIdException, NoSuchPathException,
			SecurityException {
		mc.update(association, id, spec);
	}

	/**
	 * Define a new Association
	 * 
	 * @param spec
	 *            The Association definition
	 * @return The ID of the new Association
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@POST
	@Path("associations")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public SerializableValue<String> addAssociationSpec(MCAssociationSpec spec)
			throws ImplementationException, ValidationException,
			NoSuchPathException, SecurityException {
		return new SerializableValue<String>(mc.add(association, spec));
	}

	/**
	 * Delete the Association with the specified ID
	 * 
	 * @param id
	 *            The ID of the Association which shall be deleted
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@DELETE
	@Path("associations/{id}")
	@PermitAll
	public void deleteAssociationSpec(@PathParam("id") String id)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.remove(association, id);
	}

	/**
	 * Return a list of the defined Randoms
	 * 
	 * @return List of MCRandomSpecs
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("randoms")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MCRandomSpec> getRandomSpecs() throws ImplementationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		List<MCRandomSpec> result = new ArrayList<MCRandomSpec>();
		for (String id : mc.list(random)) {
			result.add(getRandomSpec(id));
		}
		return result;
	}

	/**
	 * Return the definition of the Random with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Random
	 * @return The Random definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ImplementationException
	 */
	@GET
	@Path("randoms/{id}")
	@PermitAll
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public MCRandomSpec getRandomSpec(@PathParam("id") String id)
			throws ImplementationException, NoSuchIdException,
			NoSuchPathException, SecurityException {
		MCRandomSpec spec = (MCRandomSpec) mc.get(random, id);
		spec.setId(id);
		return spec;
	}

	/**
	 * Update the definition of Random with the specified {@code id}
	 * 
	 * @param id
	 *            The ID of the Random which shall be updated
	 * @param spec
	 *            The Random definition
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("randoms/{id}")
	@PermitAll
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setRandomSpec(@PathParam("id") String id, MCRandomSpec spec)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.update(random, id, spec);
	}

	/**
	 * Define a new Random
	 * 
	 * @param spec
	 *            The Random definition
	 * @return The ID of the new Random
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@POST
	@Path("randoms")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public SerializableValue<String> addRandomSpec(MCRandomSpec spec)
			throws ImplementationException, ValidationException,
			NoSuchPathException, SecurityException {
		return new SerializableValue<String>(mc.add(random, spec));
	}

	/**
	 * Delete the Random with the specified ID
	 * 
	 * @param id
	 *            The ID of the Random which shall be deleted
	 * @throws SecurityException
	 * @throws NoSuchPathException
	 * @throws NoSuchIdException
	 * @throws ValidationException
	 * @throws ImplementationException
	 */
	@DELETE
	@Path("randoms/{id}")
	@PermitAll
	public void deleteRandomSpec(@PathParam("id") String id)
			throws ImplementationException, ValidationException,
			NoSuchIdException, NoSuchPathException, SecurityException {
		mc.remove(random, id);
	}
}