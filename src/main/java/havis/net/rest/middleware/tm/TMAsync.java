package havis.net.rest.middleware.tm;

import javax.ws.rs.Path;

import havis.middleware.ale.service.mc.MCTagMemorySpec;
import havis.net.rest.middleware.shared.MCServiceAsync;

@Path("../rest/ale/tm")
public interface TMAsync extends MCServiceAsync<MCTagMemorySpec> {
}