#! /bin/bash

export TDBROOT=/legal/tdb-0.8.7
export JENAROOT=/legal/jena-2.6.4
export CLASSPATH=$TDBROOT/lib:$JENAROOT/lib
export PATH=$TDBROOT/bin:$JENAROOT/bin:$PATH

echo "Creating triple store."
rm -r /legal/data/triples/*
tdbloader --loc=/legal/data/triples /legal/workspace/Norms/norms/norms.n3
tdbloader --loc=/legal/data/triples /legal/workspace/Norms/norms/privacy.n3

echo "Creating LARQ index."
rm -r /legal/data/index/*
CP="$($JENAROOT/bin/jena_path)"
java $SOCKS -cp "$CP" arq.larqbuilder --larq=/legal/data/index --data=/legal/workspace/Norms/norms/norms.n3
java $SOCKS -cp "$CP" arq.larqbuilder --larq=/legal/data/index --data=/legal/workspace/Norms/norms/privacy.n3
