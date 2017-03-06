package rs.ac.uns.ftn.informatika.legal.legislator.services.resolver;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.legislator.services.Service;

public class ResolverService extends Service {
	
	private static Logger log = Logger.getLogger(ResolverService.class);
	
	private Map<URI, URI> resource = new HashMap<URI, URI>();
	private Map<URI, String> contentType = new HashMap<URI, String>();
	
	public ResolverService() {
		// Set service name
		serviceName = "rs.ac.uns.ftn.informatika.metalex.services.resolver.ResolverService";
		
		// Initialize service registry
		try {
			initialize();
			log.info("ResolverService Initialized.");
		} catch (URISyntaxException e) {
		
		}
	}
	
	public void register(URI urn, URI url, String mimeType) {
		resource.put(urn, url);
		contentType.put(urn, mimeType);
	}
	
	public void unregister(URI urn) {
		resource.remove(urn);
	}
	
	public void initialize() throws URISyntaxException {
		// Ustav Republike Srbije
		register(new URI("urn:lex:rs:narodna-skupstina:ustav:2006-11-08;lex-1@original:sr$text-xml;cen-metalex:sluzbeni-glasnik"), new URI("constitution.xml"), "text/xml");
		register(new URI("urn:lex:rs:narodna-skupstina:ustav:2006-11-08;lex-1@original:sr"), new URI("constitution.xml"), "text/xml");
		register(new URI("urn:lex:rs:narodna-skupstina:ustav:2006-11-08;lex-1"), new URI("constitution.xml"), "text/xml");
		
		// Zakon o zastiti podataka o licnosti
		register(new URI("urn:lex:rs:narodna-skupstina:zakon:2008-10-23;lex-1@original:sr$text-xml;cen-metalex:sluzbeni-glasnik"), new URI("privacy.xml"), "text/xml");
		register(new URI("urn:lex:rs:narodna-skupstina:zakon:2008-10-23;lex-1@original:sr"), new URI("privacy.xml"), "text/xml");
		register(new URI("urn:lex:rs:narodna-skupstina:zakon:2008-10-23;lex-1"), new URI("privacy.xml"), "text/xml");
		
		// Pravilnik o nacinu prethodne provere radnji obrade podataka o licnosti
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-14;lex-1@original:sr$text-xml;cen-metalex:sluzbeni-glasnik"), new URI("bylaw1.xml"), "text/xml");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-14;lex-1@original:sr"), new URI("bylaw1.xml"), "text/xml");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-14;lex-1"), new URI("bylaw1.xml"), "text/xml");
		
		// Prilog 1
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-14;lex-1@original:sr$text-xml;cen-metalex:sluzbeni-glasnik#attachment1"), new URI("attachment1.pdf"), "application/pdf");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-14;lex-1@original:sr#attachment1"), new URI("attachment1.pdf"), "application/pdf");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-14;lex-1#attachment1"), new URI("attachment1.pdf"), "application/pdf");
		
		// Pravilnik o obrascu legitimacije ovlascenog lica za vrsenje nadzora po Zakonu o zastiti podataka o licnosti 
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-21;lex-1@original:sr$text-xml;cen-metalex:sluzbeni-glasnik"), new URI("bylaw2.xml"), "text/xml");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-21;lex-1@original:sr"), new URI("bylaw2.xml"), "text/xml");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-21;lex-1"), new URI("bylaw2.xml"), "text/xml");
		
		// Prilog 1 
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-21;lex-1@original:sr$text-xml;cen-metalex:sluzbeni-glasnik#attachment1"), new URI("attachment2.pdf"), "application/pdf");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-21;lex-1@original:sr#attachment1"), new URI("attachment2.pdf"), "application/pdf");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-21;lex-1#attachment1"), new URI("attachment2.pdf"), "application/pdf");
		
		// Prilog 2 
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-21;lex-1@original:sr$text-xml;cen-metalex:sluzbeni-glasnik#attachment2"), new URI("attachment3.pdf"), "application/pdf");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-21;lex-1@original:sr#attachment2"), new URI("attachment3.pdf"), "application/pdf");
		register(new URI("urn:lex:rs:poverenik-informacije-javnog-znacaja-zastitu-podataka-licnosti:pravilnik:2009-04-21;lex-1#attachment2"), new URI("attachment3.pdf"), "application/pdf");
		
		//register(new URI("privacy"), new URI("privacy.xml"));
		//register(new URI("constitution"), new URI("constitution.xml"));
		//register(new URI("css"), new URI("regulation.css"));
		//register(new URI("default"), new URI("privacy.xml"));
		//register(new URI(""), new URI("privacy.xml"));	
	}
	
	public URI getUrlFromUrn(URI urn) {
		return resource.get(urn);
	}

	public URI getUriFromUriReference(URI reference) {
		if (reference != null) {
			String[] tokens = reference.toString().split("#");
			if (tokens.length > 0) {
				try {
					return new URI(tokens[0]);
				} catch (URISyntaxException e) {
					return null;
				}
			}
		}
		return null;
	}
	
	public String getFragmentIdFromUriReference(URI reference) {
		if (reference != null) {
			String[] tokens = reference.toString().split("#");
			if (tokens.length > 1) {
				return tokens[1];
			}
		}
		return null;
	}

	public String getContentTypeFromUrn(URI urn) {
		return contentType.get(urn);
	}
}