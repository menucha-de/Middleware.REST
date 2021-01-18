package havis.net.rest.middleware.pc;

import javax.ws.rs.Path;

import havis.middleware.ale.service.mc.MCPortCycleSpec;
import havis.middleware.ale.service.pc.PCReports;
import havis.net.rest.middleware.shared.MCCycleServiceAsync;

@Path("../rest/ale/pc")
public interface PCAsync extends MCCycleServiceAsync<MCPortCycleSpec, PCReports> {
}