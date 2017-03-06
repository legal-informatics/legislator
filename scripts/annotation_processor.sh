#! /bin/bash

EMPIRE_HOME=/legal/empire-0.7
apt -cp bin:lib/jpa/ejb3-persistence.jar:lib/clark-parsia/cp-common-utils-1.0.1.jar -factory com.clarkparsia.empire.util.apt.EmpireAnnotationProcessorFactory src/rs/ac/uns/ftn/informatika/metalex/model/rdf/*.java

#cp-common-fourstore-0.3.1.jar  cp-common-utils-1.0.1.jar   cp-common-openrdf-0.2.1.jar   
