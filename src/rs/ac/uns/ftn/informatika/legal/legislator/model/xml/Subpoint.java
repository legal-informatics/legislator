package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Podtacka.java
 */

public class Subpoint extends Element
{
	public Subpoint() {
		super();
		this.name = "subpoint";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "parent";
		this.type = "container";
	}

	public Subpoint(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("subpoint", id, "ordinal", "parent", "container", null, namespaces, elements);
	}
}
