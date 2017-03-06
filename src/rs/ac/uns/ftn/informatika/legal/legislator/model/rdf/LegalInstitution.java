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
@RdfsClass("norms:LegalInstitution")
public class LegalInstitution extends Thing {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasLegalNorms")
	protected Set<LegalNorm> legalNorms = new HashSet<LegalNorm>();
	
	@ManyToOne
	@RdfProperty("norms:isLegalInstitution")
	protected LegalBranch legalBranch;
	
	public LegalInstitution() {
	
	}
	
	public Set<LegalNorm> getLegalNorms() {
		return legalNorms;
	}

	public void setLegalNorms(Set<LegalNorm> legalNorms) {
		this.legalNorms = legalNorms;
	}
	
	public LegalBranch getLegalBranch() {
		return legalBranch;
	}

	public void setLegalBranch(LegalBranch legalBranch) {
		this.legalBranch = legalBranch;
	}
}