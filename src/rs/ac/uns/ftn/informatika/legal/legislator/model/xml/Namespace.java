package rs.ac.uns.ftn.informatika.legal.legislator.model.xml;

public class Namespace {
	private String prefix = null;
	private String url = null;
	
	public Namespace() {
	
	}

	public Namespace(String prefix, String url) {
		this.prefix = prefix;
		this.url = url;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
