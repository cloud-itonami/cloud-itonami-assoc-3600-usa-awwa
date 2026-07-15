# cloud-itonami-assoc-3600-usa-awwa

Industry rule/policy-statement catalog for the **American Water Works
Association (AWWA)** — a 23rd industry-association-level source, and
the FIRST aligned to ISIC 3600 (water collection, treatment and
supply), alongside
[`cloud-itonami-assoc-6419-jpn-zenginkyo`](https://github.com/cloud-itonami/cloud-itonami-assoc-6419-jpn-zenginkyo),
[`cloud-itonami-assoc-6512-jpn-sonpo`](https://github.com/cloud-itonami/cloud-itonami-assoc-6512-jpn-sonpo),
[`cloud-itonami-assoc-6612-jpn-jsda`](https://github.com/cloud-itonami/cloud-itonami-assoc-6612-jpn-jsda),
[`cloud-itonami-assoc-6419-deu-bankenverband`](https://github.com/cloud-itonami/cloud-itonami-assoc-6419-deu-bankenverband),
[`cloud-itonami-assoc-6612-usa-finra`](https://github.com/cloud-itonami/cloud-itonami-assoc-6612-usa-finra),
[`cloud-itonami-assoc-6512-usa-naic`](https://github.com/cloud-itonami/cloud-itonami-assoc-6512-usa-naic),
[`cloud-itonami-assoc-6920-jpn-jicpa`](https://github.com/cloud-itonami/cloud-itonami-assoc-6920-jpn-jicpa),
[`cloud-itonami-assoc-6920-usa-aicpa`](https://github.com/cloud-itonami/cloud-itonami-assoc-6920-usa-aicpa),
[`cloud-itonami-assoc-6419-fra-fbf`](https://github.com/cloud-itonami/cloud-itonami-assoc-6419-fra-fbf),
[`cloud-itonami-assoc-6511-jpn-seiho`](https://github.com/cloud-itonami/cloud-itonami-assoc-6511-jpn-seiho),
[`cloud-itonami-assoc-6910-jpn-nichibenren`](https://github.com/cloud-itonami/cloud-itonami-assoc-6910-jpn-nichibenren),
[`cloud-itonami-assoc-6810-jpn-recaj`](https://github.com/cloud-itonami/cloud-itonami-assoc-6810-jpn-recaj),
[`cloud-itonami-assoc-6411-jpn-boj`](https://github.com/cloud-itonami/cloud-itonami-assoc-6411-jpn-boj),
[`cloud-itonami-assoc-6120-usa-ctia`](https://github.com/cloud-itonami/cloud-itonami-assoc-6120-usa-ctia),
[`cloud-itonami-assoc-5110-usa-a4a`](https://github.com/cloud-itonami/cloud-itonami-assoc-5110-usa-a4a),
[`cloud-itonami-assoc-3510-usa-eei`](https://github.com/cloud-itonami/cloud-itonami-assoc-3510-usa-eei),
[`cloud-itonami-assoc-2910-deu-vda`](https://github.com/cloud-itonami/cloud-itonami-assoc-2910-deu-vda),
[`cloud-itonami-assoc-5510-usa-ahla`](https://github.com/cloud-itonami/cloud-itonami-assoc-5510-usa-ahla),
[`cloud-itonami-assoc-2100-usa-phrma`](https://github.com/cloud-itonami/cloud-itonami-assoc-2100-usa-phrma),
[`cloud-itonami-assoc-4719-usa-nrf`](https://github.com/cloud-itonami/cloud-itonami-assoc-4719-usa-nrf),
[`cloud-itonami-assoc-4100-usa-agc`](https://github.com/cloud-itonami/cloud-itonami-assoc-4100-usa-agc),
and
[`cloud-itonami-assoc-6020-usa-nab`](https://github.com/cloud-itonami/cloud-itonami-assoc-6020-usa-nab).
Part of the [`cloud-itonami`](https://github.com/cloud-itonami)
compliance-fact family (ADR-2607141700,
`cloud-itonami-compliance-fact-federation`, in `com-junkawasaki/root`).

## Scope

A **read-only reference/archive** catalog — not an Advisor⊣Governor
actuation actor. It proposes or executes nothing on AWWA's behalf.

Coverage is reported honestly (see `association.facts/coverage`): an
entry not in `catalog` has **no spec-basis**, full stop — never
fabricate one.

## Data

- `src/association/facts.cljc` — the catalog, source of truth.
- `schema/association-rule.edn` — DataScript schema.
- `data/datascript-tx.edn` — derived DataScript tx-data (query this
  alongside other `cloud-itonami`/`etzhayyim` compliance-fact sources via
  `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`).

AWWA's technical Standards (e.g. the C100 series) are commercial
products sold via store.awwa.org and were deliberately not cited here
to avoid a paywalled reference. Both entries are freely accessible
AWWA-published pages, directly WebFetch-verified: the **Policy
Statement on Distribution System Water Quality** (adopted 1975-01-26,
most recently revised 2026-04-01) and the **Who We Are** profile page
(confirming AWWA's exact 1881-03-29 founding).

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention). Document text
itself remains AWWA's; this repo stores only citation metadata
(id/title/url/dates), not full text.
