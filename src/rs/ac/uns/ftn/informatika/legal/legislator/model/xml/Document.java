package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @(#) LegalAct.java
 */

public class Document extends Element {
	protected String naming = null;
	protected String base = null;
	protected String grddl = null;
	protected String transformation = null;
	protected String lang = null;

	public Document() {
		super();
		this.type = "container";
		this.subtype = "root";
		this.localNamingConventionMethod = "individual";
		this.localNamingConventionScope = "root";
		this.naming = "urn:metalex:sr";
	}

	public Document(String name, String id, Collection<Namespace> namespaces, Collection<Element> elements, String base) {
		super(name, id, "individual", "root", "container", "root", namespaces, elements);
		this.naming = "urn:metalex:sr";
		this.base = base;
	}
	
	public String getNaming() {
		return naming;
	}

	public void setNaming(String naming) {
		this.naming = naming;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getGrddl() {
		return grddl;
	}

	public void setGrddl(String grddl) {
		this.grddl = grddl;
	}

	public String getTransformation() {
		return transformation;
	}

	public void setTransformation(String transformation) {
		this.transformation = transformation;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}