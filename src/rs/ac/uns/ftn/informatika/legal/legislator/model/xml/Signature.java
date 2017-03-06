package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Clan.java
 */

public class Signature extends Element {
	public Signature() {
		super();
		this.id = "signature";
		this.name = "signature";
		this.localNamingConventionMethod = "individual";
		this.localNamingConventionScope = "root";
		this.type = "container";
	}
	
	public Signature(Collection<Namespace> namespaces, Collection<Element> elements) {
		super("signature", "signature", "individual", "root", "container", null, namespaces, elements);
	}	
}
