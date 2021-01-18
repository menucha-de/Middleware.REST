package havis.net.rest.middleware.ec;

import javax.ws.rs.Path;

import havis.middleware.ale.service.ec.ECReports;
import havis.middleware.ale.service.mc.MC;
import havis.middleware.ale.service.mc.MCEventCycleSpec;
import havis.net.rest.middleware.shared.MCCycleResource;

@Path("ale/ec")
public class EC extends MCCycleResource<MCEventCycleSpec, ECReports> {

	private final static String version = havis.middleware.ale.config.service.mc.Path.Service.EC.Version;
	private final static String path = havis.middleware.ale.config.service.mc.Path.Service.EC.EventCycle;
	private final static String subscriber = havis.middleware.ale.config.service.mc.Path.Service.EC.Subscriber;

	public EC(MC mc) {
		super(mc, path, version, subscriber);
	}
}