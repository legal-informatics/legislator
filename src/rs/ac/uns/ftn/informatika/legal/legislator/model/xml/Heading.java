package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Glava.java
 */

public class Heading extends Element
{
	private String text;
	
	public Heading() {
		super();
		this.name = "heading";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "parent";
		this.type = "hcontainer";
	}

	public Heading(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("heading", id, "ordinal", "parent", "hcontainer", null, namespaces, elements);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
