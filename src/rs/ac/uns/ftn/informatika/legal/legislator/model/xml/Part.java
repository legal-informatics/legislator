package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Deo.java
 */

public class Part extends Element
{
	public Part() {
		super();
		this.name = "part";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "parent";
		this.type = "hcontainer";
	}

	public Part(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("part", id, "ordinal", "parent", "hcontainer", null, namespaces, elements);
	}
}
