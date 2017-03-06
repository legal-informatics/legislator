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
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#"})
@RdfsClass("norms:ClassificationElement")
public class ClassificationElement extends Thing {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasPart")
	protected Set<ClassificationElement> parts = new HashSet<ClassificationElement>();
	
	@ManyToOne
	@RdfProperty("norms:isPart")
	protected ClassificationElement whole;
	
	public ClassificationElement() {
	
	}
	
	public Set<ClassificationElement> getParts() {
		return parts;
	}

	public void setParts(Set<ClassificationElement> parts) {
		this.parts = parts;
	}
	
	public ClassificationElement getWhole() {
		return whole;
	}

	public void setWhole(ClassificationElement whole) {
		this.whole = whole;
	}
}