package havis.net.rest.middleware.lr;

import javax.ws.rs.Path;

import havis.middleware.ale.service.mc.MCLogicalReaderSpec;
import havis.net.rest.middleware.shared.MCServiceAsync;

@Path("../rest/ale/lr")
public interface LRAsync extends MCServiceAsync<MCLogicalReaderSpec> {
}