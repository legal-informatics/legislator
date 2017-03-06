package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Clan.java
 */

public class Preamble extends Element {
	public Preamble() {
		super();
		this.id = "preamble";
		this.name = "preamble";
		this.localNamingConventionMethod = "individual";
		this.localNamingConventionScope = "root";
		this.type = "container";
	}
	
	public Preamble(Collection<Namespace> namespaces, Collection<Element> elements) {
		super("preamble", "preamble", "individual", "root", "container", null, namespaces, elements);
	}	
}
