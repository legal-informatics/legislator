/*
 * Copyright (c) 2009-2010 Clark & Parsia, LLC. <http://www.clarkparsia.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.clarkparsia.empire.impl.sparql;

import com.clarkparsia.empire.impl.sparql.SPARQLDialect;

import com.clarkparsia.openrdf.query.SesameQueryUtils;

import org.openrdf.model.Value;
import org.openrdf.model.BNode;

/**
 * <p>ARQ SPARQL specific query dialect.</p>
 *
 * @author Michael Grove
 * @since 0.6.3
 * @version 0.6.5
 */
public class ARQSPARQLDialect extends SPARQLDialect {
	/**
	 * the singleton instance
	 */
	private static ARQSPARQLDialect INSTANCE;

	/**
	 * Create a new SPARQLDialect
	 */
	protected ARQSPARQLDialect() {
	}

	/**
	 * Return the single instance of SPARQLDialect
	 * @return the SPARQLDialect
	 */
	public static SPARQLDialect instance() {
		if (INSTANCE == null) {
			INSTANCE = new ARQSPARQLDialect();
		}

		return INSTANCE;
	}

	/**
	 * ARQ extension specific rendering of RDF values to query syntax format
	 * @inheritDoc
	 */
	@Override
	public String asQueryString(final Value theValue) {
		if (theValue instanceof BNode) {
			BNode aBNode = (BNode) theValue;

			return "<_:" + aBNode.getID() + ">";
		}
		else {
			return SesameQueryUtils.getSPARQLQueryString(theValue);
		}
	}
}
