package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Tacka.java
 */

public class Point extends Element
{
	public Point() {
		super();
		this.name = "point";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "parent";
		this.type = "container";
	}

	public Point(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("point", id, "ordinal", "parent", "container", null, namespaces, elements);
	}
}
