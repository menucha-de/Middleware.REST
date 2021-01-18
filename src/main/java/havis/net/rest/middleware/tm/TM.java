package havis.net.rest.middleware.tm;

import javax.ws.rs.Path;

import havis.middleware.ale.service.mc.MC;
import havis.middleware.ale.service.mc.MCTagMemorySpec;
import havis.net.rest.middleware.shared.MCResource;

@Path("ale/tm")
public class TM extends MCResource<MCTagMemorySpec> {

	private final static String version = havis.middleware.ale.config.service.mc.Path.Service.TM.Version;
	private final static String path = havis.middleware.ale.config.service.mc.Path.Service.TM.TagMemory;

	public TM(MC mc) {
		super(mc, path, version);
	}

}