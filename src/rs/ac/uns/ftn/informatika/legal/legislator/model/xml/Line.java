package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Alineja.java
 */

public class Line extends Element
{
	public Line() {
		super();
		this.name = "line";
		this.localNamingConventionMethod = "positional";
		this.localNamingConventionScope = "parent";
		this.type = "container";
	}

	public Line(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("line", id, "positional", "parent", "container", null, namespaces, elements);
	}
}
