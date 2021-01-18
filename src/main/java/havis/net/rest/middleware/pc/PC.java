package havis.net.rest.middleware.pc;

import javax.ws.rs.Path;

import havis.middleware.ale.service.mc.MC;
import havis.middleware.ale.service.mc.MCPortCycleSpec;
import havis.middleware.ale.service.pc.PCReports;
import havis.net.rest.middleware.shared.MCCycleResource;

@Path("ale/pc")
public class PC extends MCCycleResource<MCPortCycleSpec, PCReports> {

	private final static String version = havis.middleware.ale.config.service.mc.Path.Service.PC.Version;
	private final static String path = havis.middleware.ale.config.service.mc.Path.Service.PC.PortCycle;
	private final static String subscriber = havis.middleware.ale.config.service.mc.Path.Service.PC.Subscriber;

	public PC(MC mc) {
		super(mc, path, version, subscriber);
	}
}