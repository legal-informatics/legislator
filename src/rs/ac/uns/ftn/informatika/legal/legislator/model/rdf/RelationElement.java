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
@RdfsClass("norms:RelationElement")
public class RelationElement extends Thing {
	@ManyToOne
	@RdfProperty("norms:hasSubject")
	protected LegalSubject legalSubject = null;
	
	@ManyToOne
	@RdfProperty("norms:isRelationElement")
	protected LegalRelation legalRelation = null;
	
	// correspondsTo is mission
	
	public RelationElement() {
		super();
	}

	public LegalSubject getLegalSubject() {
		return legalSubject;
	}

	public void setLegalSubject(LegalSubject legalSubject) {
		this.legalSubject = legalSubject;
	}

	public LegalRelation getLegalRelation() {
		return legalRelation;
	}

	public void setLegalRelation(LegalRelation legalRelation) {
		this.legalRelation = legalRelation;
	}
}