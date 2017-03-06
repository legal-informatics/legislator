package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Glava.java
 */

public class Title extends Element
{
	private String text;
	
	public Title() {
		super();
		this.name = "title";
		this.localNamingConventionMethod = "individual";
		this.localNamingConventionScope = "root";
		this.type = "block";
	}

	public Title(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("title", id, "individual", "root", "block", null, namespaces, elements);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
