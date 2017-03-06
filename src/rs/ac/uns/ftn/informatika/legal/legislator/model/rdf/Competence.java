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
@RdfsClass("norms:Competence")
public class Competence extends RelationElement {
	@ManyToOne
	@RdfProperty("norms:isCompetence")
	protected LegalRelation legalRelation = null;
	
	// correspondsTo is missing
	
	public Competence() {
		super();
	}

	public LegalRelation getLegalRelation() {
		return legalRelation;
	}

	public void setLegalRelation(LegalRelation legalRelation) {
		this.legalRelation = legalRelation;
	}
}