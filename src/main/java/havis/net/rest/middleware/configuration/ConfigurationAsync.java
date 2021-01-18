package havis.net.rest.middleware.configuration;

import havis.net.rest.middleware.model.License;
import havis.net.rest.shared.data.SerializableValue;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

@Path("../rest/ale/config")
public interface ConfigurationAsync extends RestService {

	@OPTIONS
	@Path("name")
	void getNameOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("aleid")
	void getAleidOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("soapservice")
	void getSoapServiceOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("license")
	void getLicenseOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("thread/max")
	void getThreadMaxOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("readercycle/duration")
	void getReadercycleDurationOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("readercycle/count")
	void getReadercycleCountOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("readercycle/lifetime")
	void getReadercycleLifetimeOptions(MethodCallback<Void> callback);

	@GET
	@Path("name")
	void getName(MethodCallback<SerializableValue<String>> callback);

	@GET
	@Path("aleid")
	void getAleId(MethodCallback<SerializableValue<String>> callback);

	@PUT
	@Path("aleid")
	void setAleId(SerializableValue<String> aleId, MethodCallback<Void> callback);

	@GET
	@Path("soapservice")
	void getSoapService(MethodCallback<SerializableValue<String>> callback);

	@PUT
	@Path("soapservice")
	void setSoapService(SerializableValue<String> soapService, MethodCallback<Void> callback);

	@GET
	@Path("license")
	void getLicense(MethodCallback<License> callback);

	@PUT
	@Path("license")
	void setLicense(SerializableValue<String> licenseStr,
			MethodCallback<Void> callback);

	@GET
	@Path("thread/max")
	void getThreadMax(MethodCallback<SerializableValue<String>> callback);

	@PUT
	@Path("thread/max")
	void setThreadMax(SerializableValue<String> threadMax,
			MethodCallback<Void> callback);

	@GET
	@Path("persistmode")
	void getPersistMode(MethodCallback<SerializableValue<String>> callback);

	@PUT
	@Path("persistmode")
	void setPersistMode(SerializableValue<String> persistMode,
			MethodCallback<Void> callback);

	@GET
	@Path("readercycle/duration")
	void getReadercycleDuration(
			MethodCallback<SerializableValue<String>> callback);

	@PUT
	@Path("readercycle/duration")
	void setReadercycleDuration(SerializableValue<String> readercycleDuration,
			MethodCallback<Void> callback);

	@GET
	@Path("readercycle/count")
	void getReadercycleCount(MethodCallback<SerializableValue<String>> callback);

	@PUT
	@Path("readercycle/count")
	void setReadercycleCount(SerializableValue<String> readercycleCount,
			MethodCallback<Void> callback);

	@GET
	@Path("readercycle/lifetime")
	void getReadercycleLifetime(
			MethodCallback<SerializableValue<String>> callback);

	@PUT
	@Path("readercycle/lifetime")
	void setReadercycleLifetime(SerializableValue<String> readercycleLifetime,
			MethodCallback<Void> callback);

	@GET
	@Path("readercycle/extendedmode")
	void getReadercycleExtendedMode(
			MethodCallback<SerializableValue<Boolean>> callback);

	@PUT
	@Path("readercycle/extendedmode")
	void setReadercycleExtendedMode(SerializableValue<Boolean> readercycleExtendedMode,
			MethodCallback<Void> callback);

	@GET
	@Path("subscriber/connecttimeout")
	void getSubscriberConnectTimeout(
			MethodCallback<SerializableValue<String>> callback);

	@PUT
	@Path("subscriber/connecttimeout")
	void setSubscriberConnectTimeout(SerializableValue<String> subscriberConnectTimeout,
			MethodCallback<Void> callback);

	@GET
	@Path("subscriber/httpssecurity")
	void getSubscriberHttpsSecurity(
			MethodCallback<SerializableValue<String>> callback);

	@PUT
	@Path("subscriber/httpssecurity")
	void setSubscriberHttpsSecurity(SerializableValue<String> subscriberHttpsSecurity,
			MethodCallback<Void> callback);
}