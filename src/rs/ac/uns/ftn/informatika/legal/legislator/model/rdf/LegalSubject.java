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
@RdfsClass("norms:LegalSubject")
public class LegalSubject extends Subject {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasEnacted")
	protected Set<LegalAct> enactedLegalActs = new HashSet<LegalAct>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasPromulgated")
	protected Set<LegalAct> promulgatedLegalActs = new HashSet<LegalAct>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasSubclass")
	protected Set<LegalSubject> subsubjects = new HashSet<LegalSubject>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:isSubject")
	protected Set<RelationElement> relationElements = new HashSet<RelationElement>();
	
	@ManyToOne
	@RdfProperty("norms:isSublass")
	protected LegalSubject superrelation = null;
	
	public LegalSubject() {
		super();
	}

	public Set<LegalAct> getEnactedLegalActs() {
		return enactedLegalActs;
	}

	public void setEnactedLegalActs(Set<LegalAct> enactedLegalActs) {
		this.enactedLegalActs = enactedLegalActs;
	}

	public Set<LegalAct> getPromulgatedLegalActs() {
		return promulgatedLegalActs;
	}

	public void setPromulgatedLegalActs(Set<LegalAct> promulgatedLegalActs) {
		this.promulgatedLegalActs = promulgatedLegalActs;
	}

	public Set<LegalSubject> getSubsubjects() {
		return subsubjects;
	}

	public void setSubsubjects(Set<LegalSubject> subsubjects) {
		this.subsubjects = subsubjects;
	}

	public LegalSubject getSuperrelation() {
		return superrelation;
	}

	public void setSuperrelation(LegalSubject superrelation) {
		this.superrelation = superrelation;
	}

	public Set<RelationElement> getRelationElements() {
		return relationElements;
	}

	public void setRelationElements(Set<RelationElement> relationElements) {
		this.relationElements = relationElements;
	}
}