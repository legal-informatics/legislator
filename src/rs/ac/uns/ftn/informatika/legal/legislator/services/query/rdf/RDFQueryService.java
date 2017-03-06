package rs.ac.uns.ftn.informatika.legal.legislator.services.query.rdf;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.spi.PersistenceProvider;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.ConductNorm;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.GeneralLegalAct;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.LegalAct;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.LegalArea;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.LegalBranch;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.LegalInstitution;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.LegalNorm;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.LegalObject;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.LegalRelation;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.LegalSubject;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.LegalSystem;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.Policy;
import rs.ac.uns.ftn.informatika.legal.legislator.model.rdf.Sanction;
import rs.ac.uns.ftn.informatika.legal.legislator.services.Service;

import com.clarkparsia.empire.Empire;
import com.clarkparsia.empire.annotation.runtime.ProxyAwareList;

@SuppressWarnings("unchecked")
public class RDFQueryService extends Service {
	
	private static Logger log = Logger.getLogger(RDFQueryService.class);
	
	private PersistenceProvider provider = null;
	private EntityManagerFactory factory = null;
	// TODO: Check if EntityManager class is thread safe
	private EntityManager manager = null;
	
	private final String dateFormat = "yyyy-MM-dd";

	public RDFQueryService() {
		serviceName = "rs.ac.uns.ftn.informatika.metalex.services.query.rdf.RDFQueryService";
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("empire.properties");
		System.setProperty("empire.configuration.file", url.getPath());
						
		provider = Empire.get().persistenceProvider();
		factory = provider.createEntityManagerFactory("joseki", null);
		manager = factory.createEntityManager();
		
		log.info("RDFQueryService Initialized.");
	}

	/*
	public Thing getType(String id) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { <" + id + "> rdf:type ?result }", Thing.class);
		
		try {
			return (Thing)query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	*/
	
	public LegalSystem getLegalSystem(String id) {
		return manager.find(LegalSystem.class, java.net.URI.create(id));
	}
	
	public LegalArea getLegalArea(String id) {
		return manager.find(LegalArea.class, java.net.URI.create(id));
	}
	
	public LegalBranch getLegalBranch(String id) {
		return manager.find(LegalBranch.class, java.net.URI.create(id));
	}
	
	public LegalInstitution getLegalInstitution(String id) {
		return manager.find(LegalInstitution.class, java.net.URI.create(id));
	}
	
	public LegalRelation getLegalRelation(String id) {
		return manager.find(LegalRelation.class, java.net.URI.create(id));
	}
	
	public LegalSubject getLegalSubject(String id) {
		return manager.find(LegalSubject.class, java.net.URI.create(id));
	}
	
	public Collection<LegalRelation> getLegalRelations() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalRelation }", LegalRelation.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalRelation>();
		}
	}
	
	public List<LegalRelation> getRootLegalRelations(GeneralLegalAct legalAct) {
		List<LegalRelation> legalRelations = new ProxyAwareList<LegalRelation>();
		
		Query query = manager.createNativeQuery(
				"SELECT DISTINCT ?result \n" +
				"WHERE { " +
				"	?result a norms:LegalRelation . \n" +
				"	?result norms:isRegulated ?norm2 .\n" +
				"	?norm2 norms:isContained <" + legalAct.getRdfId() + "> .\n" +
				"	OPTIONAL { " +
				"		?result norms:isSubclass ?Y ." +
				"		?Y norms:isRegulated ?norm1 .\n" +
				"		?norm1 norms:isContained <" + legalAct.getRdfId() + "> } \n" +
				"	FILTER (! BOUND (?Y)) .\n" +
				"	OPTIONAL { " +
				"		?result norms:hasRelationElement ?Z ." +
				"		?Z a norms:Competence } \n" +
				"	FILTER (! BOUND (?Z)) .\n" +
				"} \n", LegalRelation.class);
		
		try {
			legalRelations = query.getResultList();
		} catch (NoResultException e) {
			log.warn(e);
		}
		
		return legalRelations;
	}
	
	public List<LegalRelation> getSortedRootLegalRelations(GeneralLegalAct legalAct) {
		List<LegalRelation> sortedLegalRelations = new ProxyAwareList<LegalRelation>();
		
		List<LegalRelation> legalRelations = getRootLegalRelations(legalAct);
		for (LegalRelation legalRelation: legalRelations) {
			// Add only rights and duties
			if (legalRelation.getCompetences() == null || legalRelation.getCompetences().size() == 0) {
				sortedLegalRelations.add(legalRelation);
			}
			sortedLegalRelations.addAll(getSortedSubrelations(legalRelation));
		}
		
		return sortedLegalRelations;
	}
	
	public List<LegalRelation> getSortedSubrelations(LegalRelation parent) {
		List<LegalRelation> sortedSubrelations = new ProxyAwareList<LegalRelation>();
		
		List<LegalRelation> legalRelations = getSubrelations(parent);
		for (LegalRelation legalRelation: legalRelations) {
			// Add only rights and duties
			if (legalRelation.getCompetences() == null || legalRelation.getCompetences().size() == 0) {
				sortedSubrelations.add(legalRelation);
			}
			sortedSubrelations.addAll(getSortedSubrelations(legalRelation));
		}
		
		return sortedSubrelations;
	}
	
	// Gets direct subrelations (without inferring transitive properties)
	public List<LegalRelation> getSubrelations(LegalRelation legalRelation) {
		List<LegalRelation> legalRelations = new ProxyAwareList<LegalRelation>();	
		
		Query query = manager.createNativeQuery(
				"SELECT ?result \n" +
				"WHERE { \n" +
				"	?result a norms:LegalRelation . \n" +
				"	?result norms:isSubclass <" + legalRelation.getRdfId() + "> . \n"	+ 
				"	OPTIONAL { \n" +
				"		?result norms:isSubclass ?Y . \n" +
				"		?Y norms:isSubclass <" + legalRelation.getRdfId() + "> . } \n" +
				"	FILTER (! BOUND (?Y)) \n" +
				"} \n", LegalRelation.class);
		
		try {
			legalRelations = query.getResultList();
		} catch (NoResultException e) {
			log.warn(e);
		}
		
		return legalRelations;
	}
	
	public int getRootLegalRelationsRank(GeneralLegalAct legalAct) {
		List<LegalRelation> legalRelations = getRootLegalRelations(legalAct);
		return getSubrelationsRank(legalRelations);
	}
	
	private int getSubrelationsRank(Collection<LegalRelation> legalRelations) {
		if (legalRelations != null && legalRelations.size() != 0) {
			int maxDistance = 0;
			for (LegalRelation legalRelation: legalRelations) {
				int currentDistance = getSubrelationsRank(getSubrelations(legalRelation));
				if (currentDistance > maxDistance) {
					maxDistance = currentDistance;
				}
			}
			if (legalRelations.size() > 1)
				return maxDistance + 1;
			else
				return maxDistance;
		}
		return 0;
	}
	
	public Collection<LegalSubject> getLegalSubjects() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalSubject }", LegalSubject.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalSubject>();
		}
	}
	
	public List<LegalSubject> getRootLegalSubjects(GeneralLegalAct legalAct) {
		List<LegalSubject> rootLegalSubjects = new ProxyAwareList<LegalSubject>();
	
		Query query = manager.createNativeQuery(
				"SELECT DISTINCT ?result \n" +
				"WHERE { " +
				"	?result a norms:LegalSubject . \n" +
				"	?result norms:isSubject ?relationElement1 . \n" +
				"	?relationElement1 norms:isRelationElement ?legalRelation1 . \n" +
				"	?legalRelation1 norms:isRegulated ?legalNorm1 . \n" +
				"	?legalNorm1 norms:isContained <" + legalAct.getRdfId() + "> .\n" +
				"	OPTIONAL { ?result norms:isSubclass ?Y .\n" +
				"		?Y norms:isSubject ?relationElement2 . \n" +
				"		?relationElement2 norms:isRelationElement ?legalRelation2 . \n" +
				"		?legalRelation2 norms:isRegulated ?legalNorm2 . \n" +
				"		?legalNorm2 norms:isContained <" + legalAct.getRdfId() + "> .}\n" +
				"	FILTER (! BOUND (?Y)) \n" +
				"} \n", LegalSubject.class);
		
		try {
			rootLegalSubjects = query.getResultList();	
		} catch (NoResultException e) {
			log.warn(e);
		}
		
		return rootLegalSubjects;
	}
	
	public List<LegalSubject> getSortedRootLegalSubjects(GeneralLegalAct legalAct) {
		List<LegalSubject> sortedLegalSubjects = new ProxyAwareList<LegalSubject>();
		
		List<LegalSubject> legalSubjects = getRootLegalSubjects(legalAct);
		for (LegalSubject legalSubject: legalSubjects) {
			sortedLegalSubjects.add(legalSubject);
			sortedLegalSubjects.addAll(getSortedSubsubjects(legalSubject));
		}
		
		return sortedLegalSubjects;
	}
	
	public List<LegalSubject> getSortedSubsubjects(LegalSubject parent) {
		List<LegalSubject> sortedSubsubjects = new ProxyAwareList<LegalSubject>();
		
		List<LegalSubject> legalSubjects = getSubsubjects(parent);
		for (LegalSubject legalSubject: legalSubjects) {
			sortedSubsubjects.add(legalSubject);
			sortedSubsubjects.addAll(getSortedSubsubjects(legalSubject));
		}
		
		return sortedSubsubjects;
	}
	
	// Gets direct subsubjects (without inferring transitive properties)
	public List<LegalSubject> getSubsubjects(LegalSubject legalSubject) {
		List<LegalSubject> legalSubjects = new ProxyAwareList<LegalSubject>();	
		
		Query query = manager.createNativeQuery(
				"SELECT ?result \n" +
				"WHERE { \n" +
				"	?result a norms:LegalSubject . \n" +
				"	?result norms:isSubclass <" + legalSubject.getRdfId() + "> . \n"	+ 
				"	OPTIONAL { \n" +
				"		?result norms:isSubclass ?Y . \n" +
				"		?Y norms:isSubclass <" + legalSubject.getRdfId() + "> . } \n" +
				"	FILTER (! BOUND (?Y)) \n" +
				"} \n", LegalSubject.class);
		
		try {
			legalSubjects = query.getResultList();
		} catch (NoResultException e) {
			log.warn(e);
		}
		
		return legalSubjects;
	}
	
	public LegalAct getLegalAct(String id) {
		return manager.find(LegalAct.class, java.net.URI.create(id));
	}
	
	public GeneralLegalAct getGeneralLegalAct(String id) {
		return manager.find(GeneralLegalAct.class, java.net.URI.create(id));
	}
	
	/*
	public IndividualLegalAct getIndividualLegalAct(String id) {
		return manager.find(IndividualLegalAct.class, java.net.URI.create(id));
	}
	*/
	
	public LegalNorm getLegalNorm(String id) {
		return manager.find(LegalNorm.class, java.net.URI.create(id));
	}
	
	public String getLegalNormIdByProvisionId(String provisionId) {
		Query query = manager.createNativeQuery(
				"SELECT ?result " +
				"WHERE { " +
				"	?result a norms:LegalNorm.\n" +
				"	?result norms:hasNormElement ?normElement.\n" +
				"	?normElement norms:hasURI \"" + provisionId + "\"^^xsd:anyURI.\n" +
				"}", LegalNorm.class);
		
		try {
			LegalNorm legalNorm = (LegalNorm)query.getSingleResult();
			return legalNorm.getRdfId().toString();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Collection<LegalNorm> getLegalNormsByProvisionId(String provisionId) {
		Query query = manager.createNativeQuery(
				"SELECT ?result " +
				"WHERE { " +
				"	?result a norms:LegalNorm.\n" +
				"	?result norms:hasNormElement ?normElement.\n" +
				"	?normElement norms:hasURI \"" + provisionId + "\"^^xsd:anyURI.\n" +
				"}", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getLegalNorms() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalNorm }", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getRetroactiveLegalNorms(GeneralLegalAct legalAct) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { " +
				"	?result a norms:LegalNorm . " +
				" 	?result norms:efficacyOn ?X ." +
				" 	?result norms:isContained <" + legalAct.getRdfId() + "> }", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getCompeteneceNorms() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:CompetenceNorm }", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getLegislativeCompeteneceNorms(GeneralLegalAct legalAct) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegislativeCompetenceNorm ." +
				" 	?result norms:isContained <" + legalAct.getRdfId() + ">}", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getLegislativeCreationNorms(GeneralLegalAct legalAct) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { " +
				"	?result a norms:LegislativeCreationNorm ." +
				" 	?result norms:isContained <" + legalAct.getRdfId() + ">}", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getLegislativeModificationNorms(GeneralLegalAct legalAct) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegislativeModificationNorm ." +
				" 	?result norms:isContained <" + legalAct.getRdfId() + ">}", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getLegislativeRepealmentNorms(GeneralLegalAct legalAct) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { " +
				"	?result a norms:LegislativeRepealmentNorm ." +
				" 	?result norms:isContained <" + legalAct.getRdfId() + ">}", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getNormsOfConduct(GeneralLegalAct legalAct) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { " +
				"	?result a norms:ConductNorm ." +
				" 	?result norms:isContained <" + legalAct.getRdfId() + ">}", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getLegalNorms(LegalInstitution legalInstitution) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalNorm." +
				" ?result norms:isLegalNorm " + legalInstitution.getRdfId() +". }", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getLegalNorms(LegalRelation legalRelation) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalNorm." +
				" ?result norms:regulates " + legalRelation.getRdfId() +". }", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getLegalNorms(LegalSubject legalSubject) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalNorm }", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalNorm> getLegalNorms(LegalInstitution legalInstitution, 
			LegalRelation legalRelation,  
			LegalSubject legalSubject, 
			boolean rightSubject, 
			boolean dutySubject, 
			boolean obligationSubject, 
			boolean prohibitionSubject, 
			boolean competenceSubject, 
			Date date,
			LegalSubject jurisdiction) {
		
		StringBuffer sb = new StringBuffer(
				"SELECT ?result\n" +
				"WHERE {\n" +
				"?result a norms:LegalNorm.\n");
				
		if (legalInstitution != null) {
				sb.append("?result norms:isLegalNorm <" + legalInstitution.getRdfId() + ">.\n");
		}
		
		if (legalRelation != null) {
				sb.append("{ {?result norms:regulates <" + legalRelation.getRdfId() + ">.} UNION {?result norms:regulates ?relation. ?relation norms:isSubclass <" + legalRelation.getRdfId() + ">.} }\n");
		}
		
		if (legalSubject != null) {
			if (rightSubject) {
				sb.append("{ ?result norms:regulates ?relation .\n" +
						"{ {?relation norms:hasRightSubject <" + legalSubject.getRdfId() + ">.} UNION {?relation norms:hasRightSubject ?subject. ?subject norms:isSubclass <" + legalSubject.getRdfId() + ">.} } }\n" +
						"UNION\n");
			}
			if (dutySubject) {
				sb.append("{ ?result norms:regulates ?relation .\n" +
						"{ {?relation norms:hasDutySubject <" + legalSubject.getRdfId() + ">.} UNION {?relation norms:hasDutySubject ?subject. ?subject norms:isSubclass <" + legalSubject.getRdfId() + ">.} } }\n" +
						"UNION\n");
			}
			if (obligationSubject) {
				sb.append("{ ?result norms:regulates ?relation .\n" +
						"{ {?relation norms:hasObligationSubject <" + legalSubject.getRdfId() + ">.} UNION {?relation norms:hasObligationSubject ?subject. ?subject norms:isSubclass <" + legalSubject.getRdfId() + ">.} } }\n" +
						"UNION\n");
			}
			if (prohibitionSubject) {
				sb.append("{ ?result norms:regulates ?relation .\n" +
						"{ {?relation norms:hasProhibitionSubject <" + legalSubject.getRdfId() + ">.} UNION {?relation norms:hasProhibitionSubject ?subject. ?subject norms:isSubclass <" + legalSubject.getRdfId() + ">.} } }\n" +
						"UNION\n");
			}
			if (competenceSubject) {
				sb.append("{ ?result norms:regulates ?relation .\n" +
						"{ {?relation norms:hasCompetenceSubject <" + legalSubject.getRdfId() + ">.} UNION {?relation norms:hasCompetenceSubject ?subject. ?subject norms:isSubclass <" + legalSubject.getRdfId() + ">.} } }\n" +
						"UNION\n");
			}
			
			if (rightSubject || dutySubject || competenceSubject || prohibitionSubject || obligationSubject) {
				sb.delete(sb.length() - 6, sb.length());
			}
		}
		
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sb.append(
					"?result norms:hasEnteredIntoForce ?enteredIntoForceDate.\n" +
					"FILTER (?enteredIntoForceDate < \"" + sdf.format(new Date()) + "\"^^xsd:date)\n" +
					"OPTIONAL {?result norms:isRepealed ?repealedDate.\n" +
					"FILTER (?repealedDate > \"" + sdf.format(new Date()) + "\"^^xsd:date)}\n");
		}
		
		if (jurisdiction != null) {
			sb.append("?result norms:isContained ?legalAct.\n");
			sb.append("?legalAct norms:isIssued <" + jurisdiction.getRdfId() + ">.\n");
		}
		
		sb.append("}");
		
		Query query = manager.createNativeQuery(sb.toString(), LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}
	
	public Collection<LegalSystem> getLegalSystems() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalSystem }", LegalSystem.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalSystem>();
		}
	}
	
	public Collection<LegalArea> getLegalAreas() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalArea }", LegalArea.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalArea>();
		}
	}
	
	public Collection<LegalArea> getLegalAreas(LegalSystem legalSystem) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalArea. " +
				" ?result norms:isLegalArea " + legalSystem.getRdfId() + "}", LegalArea.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalArea>();
		}
	}
	
	public Collection<LegalBranch> getLegalBranches() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalBranch }", LegalBranch.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalBranch>();
		}
	}
	
	public Collection<LegalBranch> getLegalBranches(LegalArea legalArea) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalBranch." +
				" ?result norms:isLegalBranch " + legalArea.getRdfId() + " }", LegalBranch.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalBranch>();
		}
	}
	
	public Collection<LegalInstitution> getLegalInstitutions() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalInstitution }", LegalInstitution.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalInstitution>();
		}
	}
	
	/* Those queries are not necessary. Similar result can be accomplished with get method */
	public Collection<LegalInstitution> getLegalInstitutions(LegalBranch legalBranch) {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalInstitution " +
				" ?result norms:isLegalInstitution " + legalBranch.getRdfId() + " }", LegalInstitution.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalInstitution>();
		}
	}

	public Collection<GeneralLegalAct> getLegalActs(
			LegalInstitution legalInstitution,
			String title, 
			String gazette, 
			String content, 
			Date date,
			LegalSubject jurisdiction) {
		
		// SQL injection + - && || ! ( ) { } [ ] ^ " ~ * ? : \
		
		StringBuffer sb = new StringBuffer(
				"SELECT ?result\n" +
				"WHERE {\n" +
				"?result a norms:GeneralLegalAct.\n");
				
		if (legalInstitution != null) {
				sb.append("?result norms:isLegalNorm <" + legalInstitution.getRdfId() + ">.\n");
		}
		
		if (title != null && !title.equals("")) {
			sb.append(
					"?result rdfs:label ?title.\n" +
					"?title pf:textMatch '\"" + title + "\"'.\n"
			);
		}
		
		if (gazette != null && !gazette.equals("")) {
			sb.append("?result norms:hasGazette ?gazzette.\n" +
					"?gazzette pf:textMatch '\"" + gazette + "\"'.\n"
			);
		}
		
		if (content != null && !content.equals("")) {
			sb.append(
					"?result norms:hasForm ?content.\n" +
					"?content pf:textMatch '\"" + content + "\"'.\n"
			);
		}
		
		/*
		if (content != null && !content.equals("")) {
			sb.append(
					"?result rdfs:label ?title.\n" +
					"?title pf:textMatch '" + content + "*'.\n"
			);
		}
		*/
		
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sb.append(
					"?result norms:hasEnteredIntoForce ?enteredIntoForceDate.\n" +
					"FILTER (?enteredIntoForceDate < \"" + sdf.format(new Date()) + "\"^^xsd:date)\n" +
					"OPTIONAL {?result norms:isRepealed ?repealedDate.\n" +
					"FILTER (?repealedDate > \"" + sdf.format(new Date()) + "\"^^xsd:date)}\n");
		}
		
		if (jurisdiction != null) {
			sb.append("?result norms:isIssued <" + jurisdiction.getRdfId() + ">.\n");
		}
		
		sb.append("}");
		
		Query query = manager.createNativeQuery(sb.toString(),GeneralLegalAct.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<GeneralLegalAct>();
		}
	}

	public Collection<LegalObject> getLegalObjects() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:LegalObject }", LegalObject.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalObject>();
		}
	}

	public Collection<Policy> getPolicies() {
		Query query = manager.createNativeQuery(
				"SELECT ?result" +
				" WHERE { ?result a norms:Policy }", Policy.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<Policy>();
		}
	}
	
	public Collection<Sanction> getSanctions(GeneralLegalAct legalAct) {
		Query query = manager.createNativeQuery(
				"SELECT DISTINCT ?result\n" +
				"WHERE {\n" +
				"	?result a norms:Sanction .\n" +
				"	?result norms:isSanction ?legalAct.\n" +
				"	?legalAct norms:isContained <" + legalAct.getRdfId() + "> .\n" +
				"}", Sanction.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<Sanction>();
		}
	}
	
	public Collection<LegalNorm> getBasicPrinciples(GeneralLegalAct legalAct) {
		GeneralLegalAct creationAct = legalAct.getLegalNorms().iterator().next().getCreationCompetence().getLegalRelation().getLegalNorms().iterator().next().getLegalAct(); 
		
		Query query = manager.createNativeQuery(
				"SELECT DISTINCT ?result" +
				" WHERE { " +
				"	?result a norms:LegalNorm .\n" +
				"	?result norms:isContained <" + creationAct.getRdfId() + "> .\n" +
				"	?result norms:regulates ?legalRelation .\n" +
				"	?legalRelation norms:isRegulated ?legalNorm .\n" +
				"	?legalNorm norms:isContained <" + legalAct.getRdfId() + "> .\n" +
				"}", LegalNorm.class);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ProxyAwareList<LegalNorm>();
		}
	}

	public static void main(String args[]) {
		RDFQueryService qs = new RDFQueryService();
		//Collection<LegalNorm> lns = test.getLegalNorms();
		LegalNorm norm = qs.getLegalNorm("http://informatika.ftn.uns.ac.rs/legal/privacy.owl#art12");
		System.out.println(norm.getRdfId());
	}
}