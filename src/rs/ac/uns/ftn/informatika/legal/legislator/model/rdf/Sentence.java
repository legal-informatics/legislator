package rs.ac.uns.ftn.informatika.legal.legislator.model.rdf;

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
@RdfsClass("norms:Sentence")
public class Sentence extends IndividualLegalAct {
	@ManyToOne
	@RdfProperty("norms:isCaseLaw")
	protected LegalNorm legalNorm = null;
	
	public Sentence() {
	
	}

	public LegalNorm getLegalNorm() {
		return legalNorm;
	}

	public void setLegalNorm(LegalNorm legalNorm) {
		this.legalNorm = legalNorm;
	}
}