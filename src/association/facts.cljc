(ns association.facts
  "Industry rule/policy-statement catalog for the American Water Works
  Association (AWWA, Wikidata Q4745366) -- a 23rd
  industry-association-level source (see cloud-itonami-assoc-6419-jpn-zenginkyo,
  -6512-jpn-sonpo, -6612-jpn-jsda, -6419-deu-bankenverband, -6612-usa-finra,
  -6512-usa-naic, -6920-jpn-jicpa, -6920-usa-aicpa, -6419-fra-fbf,
  -6511-jpn-seiho, -6910-jpn-nichibenren, -6810-jpn-recaj, -6411-jpn-boj,
  -6120-usa-ctia, -5110-usa-a4a, -3510-usa-eei, -2910-deu-vda,
  -5510-usa-ahla, -2100-usa-phrma, -4719-usa-nrf, -4100-usa-agc,
  -6020-usa-nab for the first twenty-two) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation). The FIRST entry aligned
  to ISIC 3600 (water collection, treatment and supply) -- a new
  industry code for this family. A rule not in this table has NO
  spec-basis, full stop; extend `catalog`, do not invent an id/url.
  AWWA's technical Standards (e.g. C100 series) are commercial
  products sold via store.awwa.org and were not used here to avoid a
  paywalled citation; both entries are freely accessible AWWA-published
  pages instead.

  Both entries were directly WebFetch-verified: the Policy Statement on
  Distribution System Water Quality (which states its own adoption date
  of Jan. 26, 1975 and most recent revision date of April 1, 2026) and
  the Who We Are profile page (confirming AWWA's exact 1881-03-29
  founding in its own text).")

(def catalog
  "assoc-slug -> vector of self-regulatory rule entries."
  {"awwa"
   [{:association-rule/id "awwa.policy-distribution-system-water-quality"
     :association-rule/title "AWWA Policy Statement on Distribution System Water Quality"
     :association-rule/association "awwa"
     :association-rule/isic "3600"
     :association-rule/country "USA"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.awwa.org/policy-statement/distribution-system-water-quality/"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1975-01-26"
     :association-rule/last-revised-date "2026-04-01"
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:water-quality :best-practices}}
    {:association-rule/id "awwa.who-we-are-profile"
     :association-rule/title "Who We Are (organization profile)"
     :association-rule/association "awwa"
     :association-rule/isic "3600"
     :association-rule/country "USA"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.awwa.org/who-we-are/"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1881-03-29"
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:governance}}]})

(defn spec-basis [assoc-slug] (get catalog assoc-slug))

(defn coverage
  ([] (coverage (keys catalog)))
  ([slugs]
   (let [have (filter catalog slugs)
         missing (remove catalog slugs)]
     {:requested (count slugs)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-3600-usa-awwa Wave 0 (ADR-2607141700): "
                 (count (get catalog "awwa")) " awwa entries seeded with an "
                 "official awwa.org citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
