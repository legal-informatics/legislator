package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Odeljak.java
 */

public class Section extends Element
{
	public Section() {
		super();
		this.name = "section";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "parent";
		this.type = "hcontainer";
	}

	public Section(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("section", id, "ordinal", "parent", "hcontainer", null, namespaces, elements);
	}
}
