package havis.net.rest.middleware.ec;

import javax.ws.rs.Path;

import havis.middleware.ale.service.ec.ECReports;
import havis.middleware.ale.service.mc.MCEventCycleSpec;
import havis.net.rest.middleware.shared.MCCycleServiceAsync;

@Path("../rest/ale/ec")
public interface ECAsync extends MCCycleServiceAsync<MCEventCycleSpec, ECReports> {
}