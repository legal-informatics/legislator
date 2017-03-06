package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Clan.java
 */

public class Article extends Element {
	public Article() {
		super();
		this.name = "article";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "root";
		this.type = "container";
	}
	
	public Article(String id, Collection<Namespace> namespaces, Collection<Element> elements) {
		super("article", id, "ordinal", "root", "container", null, namespaces, elements);
	}
}
