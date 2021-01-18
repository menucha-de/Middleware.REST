package havis.net.rest.middleware.shared;

import havis.middleware.ale.service.IReports;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;

public interface HasReports<T extends IReports> {
	@OPTIONS
	@Path("specs/{id}/report")
	void getReportOptions(MethodCallback<Void> callback);

	@GET
	@Path("specs/{id}/report")
	void getReport(@PathParam("id") String id,
			MethodCallback<T> callback);

}
