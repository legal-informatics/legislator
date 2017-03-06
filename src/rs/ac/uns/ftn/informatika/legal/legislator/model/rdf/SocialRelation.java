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
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#"})
@RdfsClass("norms:SocialRelation")
public class SocialRelation extends Thing {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasTextualFormulation")
	protected Set<String> textualFormulation = null;
	
	public SocialRelation() {
		super();
	}
	
	public Set<String> getTextualFormulation() {
		return textualFormulation;
	}

	public void setTextualFormulation(Set<String> textualFormulation) {
		this.textualFormulation = textualFormulation;
	}
}