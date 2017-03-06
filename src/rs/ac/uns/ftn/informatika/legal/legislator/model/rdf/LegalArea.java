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
@RdfsClass("norms:LegalArea")
public class LegalArea extends Thing {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasLegalBranch")
	protected Set<LegalBranch> legalBranches = new HashSet<LegalBranch>();
	
	@ManyToOne
	@RdfProperty("norms:isLegalArea")
	protected LegalSystem legalSystem;
	
	public LegalArea() {
	
	}
	
	public Set<LegalBranch> getLegalBranches() {
		return legalBranches;
	}

	public void setLegalBranches(Set<LegalBranch> legalBranches) {
		this.legalBranches = legalBranches;
	}
	
	public LegalSystem getLegalSystem() {
		return legalSystem;
	}

	public void setLegalSystem(LegalSystem legalSystem) {
		this.legalSystem = legalSystem;
	}
}