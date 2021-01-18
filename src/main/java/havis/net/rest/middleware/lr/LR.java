package havis.net.rest.middleware.lr;

import javax.ws.rs.Path;

import havis.middleware.ale.service.mc.MC;
import havis.middleware.ale.service.mc.MCLogicalReaderSpec;
import havis.net.rest.middleware.shared.MCResource;

@Path("ale/lr")
public class LR extends MCResource<MCLogicalReaderSpec> {

	private final static String version = havis.middleware.ale.config.service.mc.Path.Service.LR.Version;
	private final static String path = havis.middleware.ale.config.service.mc.Path.Service.LR.LogicalReader;

	public LR(MC mc) {
		super(mc, path, version);
	}
}