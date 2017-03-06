package rs.ac.uns.ftn.informatika.legal.legislator.model.rdf;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.clarkparsia.empire.SupportsRdfId;
import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;
import com.clarkparsia.empire.annotation.SupportsRdfIdImpl;

@Entity
@MappedSuperclass
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#",
	"rdfs", "http://www.w3.org/2000/01/rdf-schema#",
	"rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#"})
@RdfsClass("norms:Thing")
public class Thing implements SupportsRdfId {
	protected SupportsRdfId supportsRdfId = new SupportsRdfIdImpl();
	
	// This relationship is actually @ManyToMany
	@OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("rdf:type")
	protected Set<Thing> types;
	
	@RdfProperty("rdfs:label")
	protected String label;
	
	public Thing() {
		super();
	}
	
	public RdfKey getRdfId() {
		return supportsRdfId.getRdfId();
	}

	public void setRdfId(final RdfKey theId) {
		supportsRdfId.setRdfId(theId);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public SupportsRdfId getSupportsRdfId() {
		return supportsRdfId;
	}

	public void setSupportsRdfId(SupportsRdfId supportsRdfId) {
		this.supportsRdfId = supportsRdfId;
	}

	public Set<Thing> getTypes() {
		return types;
	}

	public void setTypes(Set<Thing> types) {
		this.types = types;
	}

	@Override
	public boolean equals(final java.lang.Object object) {
		if (this == object) {
			return true;
		}
		
		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		final Thing thing = (Thing) object;
		if (getRdfId() != null) {
			return getRdfId().equals(thing.getRdfId());
		}

		return true;
	}
	
	@Override
	public int hashCode() {
		return getRdfId() == null ? 0 : getRdfId().value().hashCode();
	}
	
	@Override
	public String toString() {
		return "[" + getClass().getSimpleName() + ": <" + getRdfId() + ">, " + getLabel() + "]";
	}
}