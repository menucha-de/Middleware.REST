package havis.net.rest.middleware.osgi;

import havis.middleware.ale.service.doc.DocumentService;
import havis.middleware.ale.service.mc.MC;
import havis.net.rest.middleware.RESTApplication;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

	private static final Logger log = Logger.getLogger(Activator.class.getName());

	private ServiceTracker<MC, MC> mcTracker;
	private ServiceRegistration<Application> mcRegistration;
	private ServiceTracker<DocumentService, DocumentService> docTracker;
	private ServiceRegistration<Application> docRegistration;

	@Override
	public void start(final BundleContext context) throws Exception {
		long now = new Date().getTime();

		mcTracker = new ServiceTracker<MC, MC>(context, MC.class, null) {
			@Override
			public MC addingService(ServiceReference<MC> reference) {
				MC service = super.addingService(reference);
				log.log(Level.INFO, "Adding service {0}.", service.getClass().getName());
				mcRegistration = context.registerService(Application.class, new RESTApplication(service), null);
				return service;
			}

			@Override
			public void removedService(ServiceReference<MC> reference, MC service) {
				log.log(Level.INFO, "Removing service {0}.", service.getClass().getName());
				mcRegistration.unregister();
				super.removedService(reference, service);
			}
		};
		log.log(Level.INFO, "Opening tracker {0}.", mcTracker.getClass().getName());
		mcTracker.open();
		docTracker = new ServiceTracker<DocumentService, DocumentService>(context, DocumentService.class, null) {
			@Override
			public DocumentService addingService(ServiceReference<DocumentService> reference) {
				DocumentService service = super.addingService(reference);
				log.log(Level.INFO, "Adding service {0}.", service.getClass().getName());
				docRegistration = context.registerService(Application.class, new RESTApplication(service), null);
				return service;
			}

			@Override
			public void removedService(ServiceReference<DocumentService> reference, DocumentService service) {
				log.log(Level.INFO, "Removing service {0}.", service.getClass().getName());
				docRegistration.unregister();
				super.removedService(reference, service);
			}
		};
		log.log(Level.INFO, "Opening tracker {0}.", docTracker.getClass().getName());
		docTracker.open();
		log.info("Bundle start took " + (new Date().getTime() - now) + "ms");
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		log.log(Level.INFO, "Closing tracker {0}.", mcTracker.getClass().getName());
		mcTracker.close();
		log.log(Level.INFO, "Closing tracker {0}.", docTracker.getClass().getName());
		docTracker.close();
	}
}