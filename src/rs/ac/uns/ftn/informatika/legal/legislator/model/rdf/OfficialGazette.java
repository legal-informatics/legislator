package rs.ac.uns.ftn.informatika.legal.legislator.model.rdf;

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
	"rdfs", "http://www.w3.org/2000/01/rdf-schema#",
	"pf", "http://jena.hpl.hp.com/ARQ/property#"})
@RdfsClass("norms:OfficialGazette")
public class OfficialGazette extends Thing {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasPublished")
	protected Set<GeneralLegalAct> legalActs = null;
	
	public OfficialGazette() {
	
	}

	public Set<GeneralLegalAct> getLegalActs() {
		return legalActs;
	}

	public void setLegalActs(Set<GeneralLegalAct> legalActs) {
		this.legalActs = legalActs;
	}
}