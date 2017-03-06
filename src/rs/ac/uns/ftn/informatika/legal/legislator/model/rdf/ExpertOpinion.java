package rs.ac.uns.ftn.informatika.legal.legislator.model.rdf;

import java.net.URI;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;

@Entity
@MappedSuperclass
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#",
	"rdfs", "http://www.w3.org/2000/01/rdf-schema#",
	"pf", "http://jena.hpl.hp.com/ARQ/property#"})
@RdfsClass("norms:ExpertOpinion")
public class ExpertOpinion extends Thing {
	@ManyToOne
	@RdfProperty("norms:isExpertOpinion")
	protected LegalNorm legalNorm = null;
	
	@RdfProperty("norms:hasTextualFormulation")
	protected String textualFormulation = null;
	
	@RdfProperty("norms:hasURI")
	protected URI uri = null;
	
	public ExpertOpinion() {
	
	}
	
	public LegalNorm getLegalNorm() {
		return legalNorm;
	}

	public void setLegalNorm(LegalNorm legalNorm) {
		this.legalNorm = legalNorm;
	}

	public String getTextualFormulation() {
		return textualFormulation;
	}

	public void setTextualFormulation(String textualFormulation) {
		this.textualFormulation = textualFormulation;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}
}