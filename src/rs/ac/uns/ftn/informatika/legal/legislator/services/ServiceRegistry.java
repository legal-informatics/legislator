package rs.ac.uns.ftn.informatika.legal.legislator.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.legislator.services.file.FileService;
import rs.ac.uns.ftn.informatika.legal.legislator.services.query.rdf.RDFQueryService;
import rs.ac.uns.ftn.informatika.legal.legislator.services.resolver.ResolverService;

public class ServiceRegistry {
	
	private static Logger log = Logger.getLogger(ServiceRegistry.class);
	
	private static Map<Class<?>, Service> services = new HashMap<Class<?>, Service>();
	
	static {
		services.put(RDFQueryService.class, new RDFQueryService());
		services.put(ResolverService.class, new ResolverService());
		// Isn't used for now
		services.put(FileService.class, new FileService());
		
		log.info("Service Registry Initialized.");
	}
	
	public static RDFQueryService getRDFQueryService() {
		return (RDFQueryService)services.get(RDFQueryService.class);
	}
	
	public static ResolverService getResolverService() {
		return (ResolverService)services.get(ResolverService.class);
	}
	
	public static FileService getFileService() {
		return (FileService)services.get(FileService.class);
	}
	
	public static Object getService(Class<?> c) {
		return c.cast(services.get(c));
	}
	
	public static void register(Class<?> c, Service service) {
		services.put(c, service);
	}
	
	public static void unregister(Class<?> c) {
		services.remove(c);
	}
}
