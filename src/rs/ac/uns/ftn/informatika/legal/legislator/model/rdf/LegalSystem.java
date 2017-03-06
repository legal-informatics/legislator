package rs.ac.uns.ftn.informatika.legal.legislator.model.rdf;

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
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#", 
			"rdfs", "http://www.w3.org/2000/01/rdf-schema#"})
@RdfsClass("norms:LegalSystem")
public class LegalSystem extends Thing {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasLegalArea")
	protected Set<LegalArea> legalAreas = new HashSet<LegalArea>();
	
	public LegalSystem() {
	
	}
	
	public Set<LegalArea> getLegalAreas() {
		return legalAreas;
	}

	public void setLegalAreas(Set<LegalArea> legalAreas) {
		this.legalAreas = legalAreas;
	}
}