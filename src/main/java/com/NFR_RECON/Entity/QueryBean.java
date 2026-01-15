package com.NFR_RECON.Entity;

public class QueryBean {

        private String query;

        private boolean cacheable = false;

        public QueryBean(String query) {
            super();
            this.query = query;
        }

        public QueryBean(String query, boolean cacheable) {
            super();
            this.query = query;
            this.cacheable = false;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public boolean isCacheable() {
            return cacheable;
        }

        public void setCacheable(boolean cacheable) {
            this.cacheable = false;
        }
}
