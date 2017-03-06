package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.ArrayList;
import java.util.Collection;

public class Element {
	protected String id = null;
	protected String name = null;
	protected String localNamingConventionMethod = null;
	protected String localNamingConventionScope = null;
	protected String type = null;
	protected String subtype = null;
	protected Collection<Namespace> namespaces = null;
	protected Collection<Element> elements = null;
	
	public Element() {
		namespaces = new ArrayList<Namespace>();
		elements = new ArrayList<Element>();
	}
	
	public Element(String name, String id, String localNamingConventionMethod, String localNamingConventionScope, String type, String subtype, Collection<Namespace> namespaces, Collection<Element> elements) {
		this.name = name;
		this.id = id;
		this.localNamingConventionMethod = localNamingConventionMethod;
		this.localNamingConventionScope = localNamingConventionScope;
		this.type = type;
		this.subtype = subtype;
		this.namespaces = namespaces;
		this.elements = elements;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLocalNamingConventionMethod() {
		return localNamingConventionMethod;
	}
	
	public void setLocalNamingConventionMethod(String localNamingConventionMethod) {
		this.localNamingConventionMethod = localNamingConventionMethod;
	}
	
	public String getLocalNamingConventionScope() {
		return localNamingConventionScope;
	}
	
	public void setLocalNamingConventionScope(String localNamingConventionScope) {
		this.localNamingConventionScope = localNamingConventionScope;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSubtype() {
		return subtype;
	}
	
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	
	public Collection<Namespace> getNamespaces() {
		return namespaces;
	}
	
	public void setNamespaces(Collection<Namespace> namespaces) {
		this.namespaces = namespaces;
	}
	
	public Collection<Element> getElements() {
		return elements;
	}
	
	public void setElements(Collection<Element> elements) {
		this.elements = elements;
	}
}