package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Stav.java
 */

public class Item extends Element
{
	public Item() {
		super();
		this.name = "item";
		this.localNamingConventionMethod = "positional";
		this.localNamingConventionScope = "parent";
		this.type = "container";
	}

	public Item(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("item", id, "positional", "parent", "container", null, namespaces, elements);
	}
}
