package rs.ac.uns.ftn.informatika.legal.legislator.model.rdf;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
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
@RdfsClass("norms:Prohibition")
public class Prohibition extends Duty {
	@ManyToOne
	@RdfProperty("norms:isProhibition")
	protected LegalRelation legalRelation = null;
	
	// correspondsTo is mission
	
	public Prohibition() {
		super();
	}
	
	public LegalRelation getLegalRelation() {
		return legalRelation;
	}

	public void setLegalRelation(LegalRelation legalRelation) {
		this.legalRelation = legalRelation;
	}
}