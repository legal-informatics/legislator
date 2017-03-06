package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Indeks.java
 */

public class Index extends Element
{
	private String text;
	
	public Index() {
		super();
		this.name = "index";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "parent";
		this.type = "hcontainer";
	}

	public Index(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("index", id, "ordinal", "parent", "hcontainer", null, namespaces, elements);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
