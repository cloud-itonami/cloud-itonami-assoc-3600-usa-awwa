# ADR 0001: Kotoba is the AWWA catalog source authority

- Status: Accepted
- Date: 2026-07-21

## Decision

`src/association_facts.kotoba` is the sole production source. Both citations
retain every scalar field. Full adoption, revision, and founding dates remain
unchanged. Topic count plus indexed access preserves the ordered water-quality
and best-practices pair and the governance singleton. Unknown values and
indexes return zero or typed option-none; no effects are declared.

CI executes reference semantics, restricted JavaScript, instantiated typed
WebAssembly, and production source-authority checks. Clojure and the JVM are
compiler/test hosts only.

## Consequences

- Exact source date precision remains observable.
- Multi-topic entries remain complete without host sets.
