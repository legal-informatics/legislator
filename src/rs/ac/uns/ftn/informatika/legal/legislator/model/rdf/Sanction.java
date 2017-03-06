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
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#"})
@RdfsClass("norms:Sanction")
public class Sanction extends NormElement {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:isSanction")
	protected Set<LegalNorm> legalNorms = new HashSet<LegalNorm>();
	
	public Sanction() {
		super();
	}
	
	public Set<LegalNorm> getLegalNorms() {
		return legalNorms;
	}

	public void setLegalNorms(Set<LegalNorm> legalNorms) {
		this.legalNorms = legalNorms;
	}
}