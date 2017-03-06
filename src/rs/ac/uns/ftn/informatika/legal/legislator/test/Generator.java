package rs.ac.uns.ftn.informatika.legal.legislator.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * Bazna klasa za sve generatore. Odgovorna je za definisanje direktorijuma gde
 * se vrsiti generisanje, preuzimanje modela, ucitavanje sablona, definisanje
 * naziva izlaznog fajla. Klase naslednice treba da implementiraju metodu
 * generate koja ce pozvati super implementaciju i zatim
 * izvrsiti generisanje putem sablona (metoda getTemplate) i pisaca (getWriter).
*/

public abstract class Generator {

	private String outputPath;	
	private String templateName;
	private String templateDir;
	private String outputFileName;
	private boolean overwrite = false;
	private String filePackage;
	private Configuration cfg;
	private Template template;	
	
	public Generator(String outputPath, String templateName, String templateDir, String outputFileName, boolean overwrite,
			String filePackage) {
		this.outputPath = outputPath;
		this.templateName = templateName;
		this.templateDir = templateDir;
		this.outputFileName = outputFileName;
		this.overwrite = overwrite;
		this.filePackage = filePackage;
	}

	public void generate() throws IOException {		
		if (outputPath == null) {
			throw new IOException("Putanja za generisanje koda nije podesena!");
		}	
		if (templateName == null) {
			throw new IOException("Nije navedeno ime sablona!");
		}
		if (outputFileName == null) {
			throw new IOException("Nije definisano ime izlazne datoteke!");
		}
		if (filePackage == null) {
			throw new IOException("Nije definisano ime paketa za generisane klase!");
		}

		cfg = new Configuration();		

		final String tName = templateName + ".ftl";
		try {
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			template = cfg.getTemplate(tName);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			File op = new File(outputPath);
			if (!op.exists())
				if (!op.mkdirs()) {
					throw new IOException(
							"Greska pri kreiranju direktorijuma " + outputPath);
				}
			cfg.setWhitespaceStripping(true);
		} catch (IOException e) {
			throw new IOException("Greska pri ucitavanju sablona " + tName + ".",
					e);
		}

	}

	public Writer getWriter(String fileNamePart, String packageName) throws IOException {
		if (packageName != filePackage) {
			packageName.replace(".", File.separator);		
			filePackage = packageName;
		}
			
		String fullPath = outputPath
				+ File.separator
				+ (filePackage.isEmpty() ? "" : packageToPath(filePackage)
						+ File.separator)
				+ outputFileName.replace("{0}", fileNamePart);

		File of = new File(fullPath);
		if (!of.getParentFile().exists())
			if (!of.getParentFile().mkdirs()) {
				throw new IOException("Greska pri kreiranju izlaznog direktorijuma "
						+ outputPath);
			}

		System.out.println(of.getPath());
		System.out.println(of.getName());

		if (!isOverwrite() && of.exists())
			return null;

		return new OutputStreamWriter(new FileOutputStream(of));

	}

	protected String packageToPath(String pack) {
		return pack.replace(".", File.separator);
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public Writer getWriter() throws IOException {
		return getWriter("", filePackage);

	}

	public void setOutputPath(String output) {
		this.outputPath = output;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}		
	
	public Configuration getCfg() {
		return cfg;
	}

	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public String getTemplateName() {
		return templateName;
	}
	
	public String getTemplateDir() {
		return templateDir;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public String getFilePackage() {
		return filePackage;
	}

	public void setFilePackage(String filePackage) {
		this.filePackage = filePackage;
	}

}
