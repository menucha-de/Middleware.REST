package havis.net.rest.middleware.shared;

import havis.middleware.ale.service.IReports;
import havis.middleware.ale.service.mc.MCSpec;

public interface MCCycleServiceAsync<M extends MCSpec, R extends IReports> extends MCServiceAsync<M>, HasSubscribers, HasReports<R> {

}
