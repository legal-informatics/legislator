package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

import java.util.Collection;

public class LegalAct extends Document {

	public LegalAct() {
		this.name = "legal-act";
	}

	public LegalAct(String id, Collection<Namespace> namespaces, Collection<Element> elements, String base) {
		super("legal-act", id, namespaces, elements, base);
	}

}
