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
	"rdfs", "http://www.w3.org/2000/01/rdf-schema#",
	"pf", "http://jena.hpl.hp.com/ARQ/property#"})
@RdfsClass("norms:GeneralLegalAct")
public class GeneralLegalAct extends LegalAct {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:contains")
	protected Set<LegalNorm> legalNorms = new HashSet<LegalNorm>();
	
	@ManyToOne
	@RdfProperty("norms:isEnacted")
	protected LegalSubject enactedBy;
	
	@ManyToOne
	@RdfProperty("norms:isPromulgated")
	protected LegalSubject promulgatedBy;
	
	@ManyToOne
	@RdfProperty("norms:isPublished")
	protected OfficialGazette publishedBy;

	@RdfProperty("norms:enactedOn")
	protected Date enactedOn;
	
	@RdfProperty("norms:promulgatedOn")
	protected Date promulgatedOn;
	
	@RdfProperty("norms:publishedOn")
	protected Date publishedOn;
	
	@RdfProperty("norms:defaultEnteredIntoForceOn")
	protected Date enteredIntoForceOn;
	
	@RdfProperty("norms:defaultRepealedOn")
	protected Date repealedOn;
	
	@RdfProperty("norms:efficacyOn")
	protected Date efficacy;
	
	@RdfProperty("norms:signer")
	protected String signer;
	
	@RdfProperty("norms:number")
	protected String number;
	
	@RdfProperty("norms:type")
	protected String type;
	
	public GeneralLegalAct() {
	
	}

	public Set<LegalNorm> getLegalNorms() {
		return legalNorms;
	}

	public void setLegalNorms(Set<LegalNorm> legalNorms) {
		this.legalNorms = legalNorms;
	}

	public LegalSubject getEnactedBy() {
		return enactedBy;
	}

	public void setEnactedBy(LegalSubject enactedBy) {
		this.enactedBy = enactedBy;
	}

	public LegalSubject getPromulgatedBy() {
		return promulgatedBy;
	}

	public void setPromulgatedBy(LegalSubject promulgatedBy) {
		this.promulgatedBy = promulgatedBy;
	}

	public OfficialGazette getPublishedBy() {
		return publishedBy;
	}

	public void setPublishedBy(OfficialGazette publishedBy) {
		this.publishedBy = publishedBy;
	}

	public Date getEnactedOn() {
		return enactedOn;
	}

	public void setEnactedOn(Date enactedOn) {
		this.enactedOn = enactedOn;
	}

	public Date getPromulgatedOn() {
		return promulgatedOn;
	}

	public void setPromulgatedOn(Date promulgatedOn) {
		this.promulgatedOn = promulgatedOn;
	}

	public Date getPublishedOn() {
		return publishedOn;
	}

	public void setPublishedOn(Date publishedOn) {
		this.publishedOn = publishedOn;
	}

	public Date getEnteredIntoForceOn() {
		return enteredIntoForceOn;
	}

	public void setEnteredIntoForceOn(Date enteredIntoForceOn) {
		this.enteredIntoForceOn = enteredIntoForceOn;
	}

	public Date getRepealedOn() {
		return repealedOn;
	}

	public void setRepealedOn(Date repealedOn) {
		this.repealedOn = repealedOn;
	}

	public Date getEfficacy() {
		return efficacy;
	}

	public void setEfficacy(Date efficacy) {
		this.efficacy = efficacy;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}