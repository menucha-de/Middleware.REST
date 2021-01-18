package havis.net.rest.middleware.configuration;

import havis.middleware.ale.base.exception.ImplementationException;
import havis.middleware.ale.base.exception.NoSuchPropertyException;
import havis.middleware.ale.base.exception.SecurityException;
import havis.middleware.ale.config.service.mc.Property;
import havis.middleware.ale.service.mc.ImplementationExceptionResponse;
import havis.middleware.ale.service.mc.MC;
import havis.net.rest.middleware.model.License;
import havis.net.rest.middleware.model.LicenseBuilder;
import havis.net.rest.shared.Resource;
import havis.net.rest.shared.data.SerializableValue;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("ale/config")
public class Configuration extends Resource {

	private MC mc;

	public Configuration(MC mc) {
		this.mc = mc;
	}

	/**
	 * Return the name of the Middleware Service
	 * 
	 * @return Name of the Middleware Service
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("name")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getName() throws ImplementationException,
			NoSuchPropertyException, SecurityException {
		return new SerializableValue<String>(
				mc.getProperty(Property.Global.Name));
	}

	/**
	 * Return the ID of the Middleware
	 * 
	 * @return ID of the Middleware
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("aleid")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getAleId() throws ImplementationException,
			NoSuchPropertyException, SecurityException {
		return new SerializableValue<String>(
				mc.getProperty(Property.Global.ALEID));
	}

	/**
	 * Set the ID of the Middleware
	 * 
	 * @param aleId
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("aleid")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setAleId(SerializableValue<String> aleId)
			throws ImplementationException, NoSuchPropertyException, SecurityException {
		mc.setProperty(Property.Global.ALEID, aleId.getValue());
	}

	/**
	 * Return whether the SOAP service is enabled or disabled
	 * 
	 * @return whether the SOAP service is enabled or disabled
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("soapservice")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getSoapService()
			throws ImplementationException, NoSuchPropertyException, SecurityException {
		return new SerializableValue<String>(mc.getProperty(Property.Global.SOAPService));
	}

	/**
	 * Set the SOAP service
	 * 
	 * @param soapService
	 *            whether the SOAP service is enabled or disabled
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("soapservice")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setSoapService(SerializableValue<String> soapService)
			throws ImplementationException, NoSuchPropertyException, SecurityException {
		mc.setProperty(Property.Global.SOAPService, soapService.getValue());
	}

	/**
	 * Return the License of the Middleware
	 * 
	 * @return License of the Middleware
	 * @throws ImplementationExceptionResponse
	 */
	@GET
	@Path("license")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public License getLicense() throws ImplementationException {
		return LicenseBuilder.getLicense(Property.Global.License);
	}

	/**
	 * Set the license of the Middleware
	 * 
	 * @param license
	 *            Type of Base64-String
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("license")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setLicense(SerializableValue<String> license)
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		mc.setProperty(Property.Global.License, license.getValue());
	}

	/**
	 * Return the maximum number of simultaneously used threads
	 * 
	 * @return maximum number of simultaneously used threads
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("thread/max")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getThreadMax()
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		return new SerializableValue<String>(
				mc.getProperty(Property.Global.MaxThreads));
	}

	/**
	 * Set the maximum number of simultaneously used threads
	 * 
	 * @param threadMax
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("thread/max")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setThreadMax(SerializableValue<String> threadMax)
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		mc.setProperty(Property.Global.MaxThreads, threadMax.getValue());
	}

	/**
	 * Return whether the persist mode is enabled or disabled
	 * 
	 * @return whether the persist mode is enabled or disabled
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("persistmode")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getPersistMode()
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		return new SerializableValue<String>(
				mc.getProperty(Property.Global.PersistMode));
	}

	/**
	 * Set the persist mode
	 * 
	 * @param persistMode
	 *            whether the persist mode is enabled or disabled
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("persistmode")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setPersistMode(SerializableValue<String> persistMode)
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		mc.setProperty(Property.Global.PersistMode,
				persistMode.getValue());
	}

	/**
	 * Return the minimum duration of a reader cycle
	 * 
	 * @return time in milliseconds
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("readercycle/duration")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getDuration()
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		return new SerializableValue<String>(
				mc.getProperty(Property.Global.ReaderCycle.Duration));
	}

	/**
	 * Set the minimum duration of a reader cycle
	 * 
	 * @param duration
	 *            time in milliseconds
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("readercycle/duration")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setDuration(SerializableValue<String> duration)
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		mc.setProperty(Property.Global.ReaderCycle.Duration,
				duration.getValue());
	}

	/**
	 * Return the number of stored TAGs
	 * 
	 * @return Number of stored TAGs
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("readercycle/count")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getCount() throws ImplementationException,
			NoSuchPropertyException, SecurityException {
		return new SerializableValue<String>(
				mc.getProperty(Property.Global.ReaderCycle.Count));
	}

	/**
	 * Set the number of stored TAGs
	 * 
	 * @param count
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("readercycle/count")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setCount(SerializableValue<String> count)
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		mc.setProperty(Property.Global.ReaderCycle.Count, count.getValue());
	}

	/**
	 * Return the lifetime in milliseconds how long a TAG will be stored
	 * 
	 * @return time in milliseconds
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("readercycle/lifetime")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getLifetime()
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		return new SerializableValue<String>(
				mc.getProperty(Property.Global.ReaderCycle.Lifetime));
	}

	/**
	 * Set the lifetime in milliseconds how long a TAG will be stored
	 * 
	 * @param lifetime
	 *            time in milliseconds
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("readercycle/lifetime")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setLifetime(SerializableValue<String> lifetime)
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		mc.setProperty(Property.Global.ReaderCycle.Lifetime,
				lifetime.getValue());
	}

	/**
	 * Return whether the extended mode is enabled or disabled
	 * 
	 * @return whether the extended mode is enabled or disabled
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("readercycle/extendedmode")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<Boolean> getExtendedMode()
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		return new SerializableValue<Boolean>(Boolean.valueOf(
				mc.getProperty(Property.Global.ReaderCycle.ExtendedMode)));
	}

	/**
	 * Set the extended mode
	 * 
	 * @param extendedMode
	 *            whether the extended mode is enabled or disabled
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("readercycle/extendedmode")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setExtendedMode(SerializableValue<Boolean> extendedMode)
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		mc.setProperty(Property.Global.ReaderCycle.ExtendedMode,
				extendedMode.getValue().toString());
	}

	/**
	 * Return the subscriber connect timeout in milliseconds
	 * 
	 * @return time in milliseconds
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("subscriber/connecttimeout")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getSubscriberConnectTimeout()
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		return new SerializableValue<String>(
				mc.getProperty(Property.Global.Subscriber.ConnectTimeout));
	}

	/**
	 * Set the subscriber connect timeout in milliseconds
	 * 
	 * @param connectTimeout
	 *            time in milliseconds
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("subscriber/connecttimeout")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setSubscriberConnectTimeout(SerializableValue<String> connectTimeout)
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		mc.setProperty(Property.Global.Subscriber.ConnectTimeout,
				connectTimeout.getValue());
	}

	/**
	 * Return whether subscriber HTTPS security ensured
	 * 
	 * @return true or false
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@GET
	@Path("subscriber/httpssecurity")
	@PermitAll
	@Produces({ MediaType.APPLICATION_JSON })
	public SerializableValue<String> getSubscriberHttpsSecurity()
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		return new SerializableValue<String>(
				mc.getProperty(Property.Global.Subscriber.HttpsSecurity));
	}

	/**
	 * Set whether subscriber HTTPS security is ensured
	 * 
	 * @param httpsSecurity true or false
	 * @throws SecurityException
	 * @throws NoSuchPropertyException
	 * @throws ImplementationException
	 */
	@PUT
	@Path("subscriber/httpssecurity")
	@RolesAllowed("admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void setSubscriberHttpsSecurity(SerializableValue<String> httpsSecurity)
			throws ImplementationException, NoSuchPropertyException,
			SecurityException {
		mc.setProperty(Property.Global.Subscriber.HttpsSecurity,
				httpsSecurity.getValue());
	}
}
