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
@RdfsClass("norms:LegalRelation")
public class LegalRelation extends SocialRelation {
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:isRegulated")
	protected Set<LegalNorm> legalNorms = new HashSet<LegalNorm>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasSubclass")
	protected Set<LegalRelation> subrelations = new HashSet<LegalRelation>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasRelationElement")
	protected Set<RelationElement> relationElements = new HashSet<RelationElement>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasRight")
	protected Set<Right> rights = new HashSet<Right>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasCompetence")
	protected Set<Competence> competences = new HashSet<Competence>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasLegislativeCompetence")
	protected Set<LegislativeCompetence> legislativeCompetences = new HashSet<LegislativeCompetence>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasLegislativeCreation")
	protected Set<LegislativeCreation> legislativeCreations = new HashSet<LegislativeCreation>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasLegislativeModification")
	protected Set<LegislativeModification> legislativeModifications = new HashSet<LegislativeModification>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasLegislativeRepealment")
	protected Set<LegislativeRepealment> legislativeRepealments = new HashSet<LegislativeRepealment>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasProhibition")
	protected Set<Prohibition> prohibitions = new HashSet<Prohibition>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasObligation")
	protected Set<Obligation> obligations = new HashSet<Obligation>();
	
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasDuty")
	protected Set<Obligation> duties = new HashSet<Obligation>();
	
	@ManyToOne
	@RdfProperty("norms:isSublass")
	protected LegalRelation superrelation = null;
	
	public LegalRelation() {
		super();
	}
	
	public Set<LegalNorm> getLegalNorms() {
		return legalNorms;
	}

	public void setLegalNorms(Set<LegalNorm> legalNorms) {
		this.legalNorms = legalNorms;
	}

	public Set<LegalRelation> getSubrelations() {
		return subrelations;
	}

	public void setSubrelations(Set<LegalRelation> subrelations) {
		this.subrelations = subrelations;
	}

	public LegalRelation getSuperrelation() {
		return superrelation;
	}

	public void setSuperrelation(LegalRelation superrelation) {
		this.superrelation = superrelation;
	}

	public Set<RelationElement> getRelationElements() {
		return relationElements;
	}

	public void setRelationElements(Set<RelationElement> relationElements) {
		this.relationElements = relationElements;
	}

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

	public Set<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Set<Competence> competences) {
		this.competences = competences;
	}

	public Set<Prohibition> getProhibitions() {
		return prohibitions;
	}

	public void setProhibitions(Set<Prohibition> prohibitions) {
		this.prohibitions = prohibitions;
	}

	public Set<Obligation> getObligations() {
		return obligations;
	}

	public void setObligations(Set<Obligation> obligations) {
		this.obligations = obligations;
	}

	public Set<Obligation> getDuties() {
		return duties;
	}

	public void setDuties(Set<Obligation> duties) {
		this.duties = duties;
	}

	public Set<LegislativeCompetence> getLegislativeCompetences() {
		return legislativeCompetences;
	}

	public void setLegislativeCompetences(
			Set<LegislativeCompetence> legislativeCompetences) {
		this.legislativeCompetences = legislativeCompetences;
	}

	public Set<LegislativeCreation> getLegislativeCreations() {
		return legislativeCreations;
	}

	public void setLegislativeCreations(
			Set<LegislativeCreation> legislativeCreations) {
		this.legislativeCreations = legislativeCreations;
	}

	public Set<LegislativeModification> getLegislativeModifications() {
		return legislativeModifications;
	}

	public void setLegislativeModifications(
			Set<LegislativeModification> legislativeModifications) {
		this.legislativeModifications = legislativeModifications;
	}

	public Set<LegislativeRepealment> getLegislativeRepealments() {
		return legislativeRepealments;
	}

	public void setLegislativeRepealments(
			Set<LegislativeRepealment> legislativeRepealments) {
		this.legislativeRepealments = legislativeRepealments;
	}
}