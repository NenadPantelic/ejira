package net.radionica.ejira.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
/*
 * NAPOMENA - moze da se brise
 * ova klasa se i ne koristi - cela solr logika i config su u solr service-u
 */
public class SolrJConnector {

    private SolrClient _solr;

    public SolrJConnector() {
	String urlString = "http://localhost:8983/solr/tickets";
	_solr = new HttpSolrClient.Builder(urlString).build();
    }

    public SolrClient getSolr() {
	return _solr;
    }

    public void setSolr(SolrClient solr) {
	_solr = solr;
    }

}
