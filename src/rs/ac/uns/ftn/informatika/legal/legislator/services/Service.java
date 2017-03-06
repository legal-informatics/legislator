package rs.ac.uns.ftn.informatika.legal.legislator.services;

public class Service {
	protected static String serviceName = null;

	public static String getServiceName() {
		return serviceName;
	}

	public static void setServiceName(String serviceName) {
		Service.serviceName = serviceName;
	}
}
