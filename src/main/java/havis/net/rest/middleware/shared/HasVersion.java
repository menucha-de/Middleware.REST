package havis.net.rest.middleware.shared;

import havis.middleware.ale.service.mc.MCVersionSpec;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;

public interface HasVersion {
	@OPTIONS
	@Path("version")
	void getVersionOptions(MethodCallback<Void> callback);

	@GET
	@Path("version")
	void getVersion(MethodCallback<MCVersionSpec> callback);

}
