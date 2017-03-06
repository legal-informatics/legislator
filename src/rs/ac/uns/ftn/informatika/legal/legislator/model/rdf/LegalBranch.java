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
@RdfsClass("norms:LegalBranch")
public class LegalBranch extends Thing {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasLegalInstitution")
	protected Set<LegalInstitution> legalInstitutions = new HashSet<LegalInstitution>();
	
	@ManyToOne
	@RdfProperty("norms:isLegalBranch")
	protected LegalArea legalArea;
	
	public LegalBranch() {
	
	}
	
	public Set<LegalInstitution> getLegalInstitutions() {
		return legalInstitutions;
	}

	public void setLegalInstitutions(Set<LegalInstitution> legalInstitutions) {
		this.legalInstitutions = legalInstitutions;
	}
	
	public LegalArea getLegalArea() {
		return legalArea;
	}

	public void setLegalArea(LegalArea legalArea) {
		this.legalArea = legalArea;
	}
}