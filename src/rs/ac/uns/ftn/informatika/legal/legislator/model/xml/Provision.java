package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Odredba.java
 */

public class Provision extends Element
{
	private String text;
	
	public Provision() {
		super();
		this.name = "provision";
		this.localNamingConventionMethod = "positional";
		this.localNamingConventionScope = "parent";
		this.type = "block";
	}
	
	public Provision(String id, Collection<Namespace> namespaces, Collection<Element> elements, String text) {
		super("provision", id, "positional", "parent", "block", null, namespaces, elements);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
