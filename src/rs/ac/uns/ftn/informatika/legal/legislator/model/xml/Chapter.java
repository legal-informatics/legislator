package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Chapter.java
 */

public class Chapter extends Element
{
	public Chapter() {
		super();
		this.name = "chapter";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "parent";
		this.type = "hcontainer";
	}

	public Chapter(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("chapter", id, "ordinal", "parent", "hcontainer", null, namespaces, elements);
	}
}
