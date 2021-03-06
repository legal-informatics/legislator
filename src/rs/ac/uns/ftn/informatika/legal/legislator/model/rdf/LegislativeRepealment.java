package rs.ac.uns.ftn.informatika.legal.legislator.model.rdf;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;

@Entity
@MappedSuperclass
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#",
	"rdfs", "http://www.w3.org/2000/01/rdf-schema#"})
@RdfsClass("norms:LegislativeRepealment")
public class LegislativeRepealment extends LegislativeCompetence {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasRepealed")
	protected Set<LegalNorm> repealedNorms = new HashSet<LegalNorm>();
	
	// correspondsTo is mission
	
	public LegislativeRepealment() {
		super();
	}

	public Set<LegalNorm> getRepealedNorms() {
		return repealedNorms;
	}

	public void setRepealedNorms(Set<LegalNorm> repealedNorms) {
		this.repealedNorms = repealedNorms;
	}
}