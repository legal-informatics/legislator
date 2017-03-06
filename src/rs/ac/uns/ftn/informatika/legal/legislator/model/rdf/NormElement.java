package rs.ac.uns.ftn.informatika.legal.legislator.model.rdf;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;

@Entity
@MappedSuperclass 
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#"})
@RdfsClass("norms:NormElement")
public class NormElement extends Thing {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:isNormElement")
	protected Set<LegalNorm> legalNorms = new HashSet<LegalNorm>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasTextualFormulation")
	protected Set<String> textualFormulation = null;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasURI")
	protected Set<URI> uri = null;
	
	public NormElement() {
		super();
	}
	
	public Set<LegalNorm> getLegalNorms() {
		return legalNorms;
	}

	public void setLegalNorms(Set<LegalNorm> legalNorms) {
		this.legalNorms = legalNorms;
	}

	public Set<String> getTextualFormulation() {
		return textualFormulation;
	}

	public void setTextualFormulation(Set<String> textualFormulation) {
		this.textualFormulation = textualFormulation;
	}

	public Set<URI> getUri() {
		return uri;
	}

	public void setUri(Set<URI> uri) {
		this.uri = uri;
	}
}