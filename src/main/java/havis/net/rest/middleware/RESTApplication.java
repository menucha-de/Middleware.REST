package havis.net.rest.middleware;

import havis.middleware.ale.service.doc.DocumentService;
import havis.middleware.ale.service.mc.MC;
import havis.net.rest.middleware.cc.CC;
import havis.net.rest.middleware.configuration.Configuration;
import havis.net.rest.middleware.connector.Connector;
import havis.net.rest.middleware.doc.Doc;
import havis.net.rest.middleware.ec.EC;
import havis.net.rest.middleware.lr.LR;
import havis.net.rest.middleware.mapper.ImplementationExceptionMapper;
import havis.net.rest.middleware.mapper.NoSuchIdExceptionMapper;
import havis.net.rest.middleware.mapper.NoSuchNameExceptionMapper;
import havis.net.rest.middleware.mapper.NoSuchPathExceptionMapper;
import havis.net.rest.middleware.mapper.NoSuchPropertyExceptionMapper;
import havis.net.rest.middleware.mapper.ParameterExceptionMapper;
import havis.net.rest.middleware.mapper.ParameterForbiddenExceptionMapper;
import havis.net.rest.middleware.mapper.SecurityExceptionMapper;
import havis.net.rest.middleware.mapper.URISyntaxExceptionMapper;
import havis.net.rest.middleware.mapper.ValidationExceptionMapper;
import havis.net.rest.middleware.pc.PC;
import havis.net.rest.middleware.tm.TM;
import havis.net.rest.middleware.utils.Utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RESTApplication extends Application {

	private final static String PROVIDERS = "javax.ws.rs.ext.Providers";

	private Set<Object> singletons = new HashSet<Object>();
	private Map<String, Object> properties = new HashMap<>();

	public RESTApplication(MC mc) {
		singletons.add(new LR(mc));
		singletons.add(new TM(mc));
		singletons.add(new EC(mc));
		singletons.add(new CC(mc));
		singletons.add(new PC(mc));
		singletons.add(new Configuration(mc));
		singletons.add(new Connector(mc));
		singletons.add(new Utils());

		properties.put(PROVIDERS, new Class<?>[] { NoSuchNameExceptionMapper.class, ParameterExceptionMapper.class, ParameterForbiddenExceptionMapper.class,
				ValidationExceptionMapper.class, ImplementationExceptionMapper.class, NoSuchIdExceptionMapper.class, NoSuchPathExceptionMapper.class,
				NoSuchPropertyExceptionMapper.class, SecurityExceptionMapper.class, URISyntaxExceptionMapper.class });
	}

	public RESTApplication(DocumentService doc) {
		singletons.add(new Doc(doc));
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}
}