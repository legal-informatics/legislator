package rs.ac.uns.ftn.informatika.legal.legislator.model.rdf;

import java.util.Date;
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
@RdfsClass("norms:LegalNorm")
public class LegalNorm extends Thing {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasCaseLaw")
	protected Set<Sentence> caseLaws = new HashSet<Sentence>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasExpertOpinion")
	protected Set<ExpertOpinion> expertOpinions = new HashSet<ExpertOpinion>();
	
	@ManyToOne
	@RdfProperty("norms:hasDisposition")
	protected Disposition disposition;
	
	@ManyToOne
	@RdfProperty("norms:hasDispositionHypothesis")
	protected DispositionHypothesis dispositionHypothesis;
	
	@ManyToOne
	@RdfProperty("norms:hasSanction")
	protected Sanction sanction;
	
	@ManyToOne
	@RdfProperty("norms:hasSanctionHypothesis")
	protected SanctionHypothesis sanctionHypothesis;

	@ManyToOne
	@RdfProperty("norms:hasException")
	protected Exception exception;
	
	@ManyToOne
	@RdfProperty("norms:regulates")
	protected LegalRelation legalRelation;
	
	@ManyToOne
	@RdfProperty("norms:hasPolicy")
	protected Policy policy;
	
	@ManyToOne
	@RdfProperty("norms:isLegalNorm")
	protected LegalInstitution legalInstitution;
	
	@ManyToOne
	@RdfProperty("norms:isContained")
	protected GeneralLegalAct legalAct;
	
	@ManyToOne
	@RdfProperty("norms:isCreated")
	protected LegislativeCompetence creationCompetence;
	
	@ManyToOne
	@RdfProperty("norms:isRepealed")
	protected LegislativeCompetence repealmentCompetence;
	
	@RdfProperty("norms:enteredIntoForceOn")
	protected Date enteredIntoForce;
	
	@RdfProperty("norms:repealedOn")
	protected Date repealed;
	
	@RdfProperty("norms:efficacyOn")
	protected Date efficacy;
	
	public LegalNorm() {
		super();
	}
	
	public Set<Sentence> getCaseLaws() {
		return caseLaws;
	}

	public void setCaseLaws(Set<Sentence> caseLaws) {
		this.caseLaws = caseLaws;
	}

	public Set<ExpertOpinion> getExpertOpinions() {
		return expertOpinions;
	}

	public void setExpertOpinions(Set<ExpertOpinion> expertOpinions) {
		this.expertOpinions = expertOpinions;
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public void setDisposition(Disposition disposition) {
		this.disposition = disposition;
	}

	public DispositionHypothesis getDispositionHypothesis() {
		return dispositionHypothesis;
	}

	public void setDispositionHypothesis(DispositionHypothesis dispositionHypothesis) {
		this.dispositionHypothesis = dispositionHypothesis;
	}

	public Sanction getSanction() {
		return sanction;
	}

	public void setSanction(Sanction sanction) {
		this.sanction = sanction;
	}

	public SanctionHypothesis getSanctionHypothesis() {
		return sanctionHypothesis;
	}

	public void setSanctionHypothesis(SanctionHypothesis sanctionHypothesis) {
		this.sanctionHypothesis = sanctionHypothesis;
	}
	
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public LegalRelation getLegalRelation() {
		return legalRelation;
	}

	public void setLegalRelation(LegalRelation legalRelation) {
		this.legalRelation = legalRelation;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public LegalInstitution getLegalInstitution() {
		return legalInstitution;
	}

	public void setLegalInstitution(LegalInstitution legalInstitution) {
		this.legalInstitution = legalInstitution;
	}

	public GeneralLegalAct getLegalAct() {
		return legalAct;
	}

	public void setLegalAct(GeneralLegalAct legalAct) {
		this.legalAct = legalAct;
	}

	public LegislativeCompetence getCreationCompetence() {
		return creationCompetence;
	}

	public void setCreationCompetence(LegislativeCompetence creationCompetence) {
		this.creationCompetence = creationCompetence;
	}

	public LegislativeCompetence getRepealmentCompetence() {
		return repealmentCompetence;
	}

	public void setRepealmentCompetence(LegislativeCompetence repealmentCompetence) {
		this.repealmentCompetence = repealmentCompetence;
	}

	public Date getEnteredIntoForce() {
		return enteredIntoForce;
	}

	public void setEnteredIntoForce(Date enteredIntoForce) {
		this.enteredIntoForce = enteredIntoForce;
	}
	
	public Date getRepealed() {
		return repealed;
	}

	public void setRepealed(Date repealed) {
		this.repealed = repealed;
	}

	public Date getEfficacy() {
		return efficacy;
	}

	public void setEfficacy(Date efficacy) {
		this.efficacy = efficacy;
	}
}