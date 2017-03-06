package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Pododeljak.java
 */

public class Subsection extends Element
{
	public Subsection() {
		super();
		this.name = "subsection";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "parent";
		this.type = "hcontainer";
	}

	public Subsection(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("subsection", id, "ordinal", "parent", "hcontainer", null, namespaces, elements);
	}
}
