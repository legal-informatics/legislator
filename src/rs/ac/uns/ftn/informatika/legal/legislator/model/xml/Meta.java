package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

/**
 * @(#) Clan.java
 */

public class Meta extends Element {
	protected String about = null;
	protected String rel = null;
	protected String href = null;
	protected String property = null;
	protected String content = null;
	
	public Meta() {
		super();
		this.name = "meta";
		this.localNamingConventionMethod = "ordinal";
		this.localNamingConventionScope = "root";
		this.type = "meta";
	}
	
	public Meta(String id, Collection<Namespace> namespaces, Collection<Element> elements, String about, String rel, String href, String property, String content) {
		super("meta", id, "ordinal", "root", "meta", null, namespaces, elements);
		this.about = about;
		this.rel = rel;
		this.href = href;
		this.property = property;
		this.content = content;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
