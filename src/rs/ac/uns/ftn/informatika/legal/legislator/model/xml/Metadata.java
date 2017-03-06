package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Odeljak.java
 */

public class Metadata extends Element
{
	public Metadata() {
		super();
		this.name = "metadata";
		this.localNamingConventionMethod = "individual";
		this.localNamingConventionScope = "root";
		this.type = "mcontainer";
	}

	public Metadata(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("metadata", id, "individual", "root", "mcontainer", null, namespaces, elements);
	}
}
