package havis.net.rest.middleware.cc;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;

import havis.middleware.ale.service.cc.CCParameterListEntry;
import havis.middleware.ale.service.cc.CCReports;
import havis.middleware.ale.service.mc.MCAssociationSpec;
import havis.middleware.ale.service.mc.MCCacheSpec;
import havis.middleware.ale.service.mc.MCCommandCycleSpec;
import havis.middleware.ale.service.mc.MCRandomSpec;
import havis.net.rest.middleware.shared.HasSpecs;
import havis.net.rest.middleware.shared.MCCycleServiceAsync;

@Path("../rest/ale/cc")
public interface CCAsync extends MCCycleServiceAsync<MCCommandCycleSpec, CCReports> {

	@POST
	@Path("specs/{id}/report")
	void getReport(@PathParam("id") String id, List<CCParameterListEntry> parameters, MethodCallback<CCReports> callback);

	@Path("caches")
	public HasSpecs<MCCacheSpec> getCaches();
	
	@Path("randoms")
	public HasSpecs<MCRandomSpec> getRandoms();
	
	@Path("associations")
	public HasSpecs<MCAssociationSpec> getAssociations();
}