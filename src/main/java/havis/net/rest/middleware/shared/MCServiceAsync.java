package havis.net.rest.middleware.shared;

import javax.ws.rs.Path;

import org.fusesource.restygwt.client.RestService;

import havis.middleware.ale.service.mc.MCSpec;

public interface MCServiceAsync<M extends MCSpec> extends RestService, HasVersion {
	
	@Path("specs")
	public HasSpecs<M> getSpecs();
}
