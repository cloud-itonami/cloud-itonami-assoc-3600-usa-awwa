(ns association-facts-test
  (:require [clojure.java.io :as io] [clojure.java.shell :as shell]
            [clojure.test :refer [deftest is testing]]
            [kotoba.compiler.core :as compiler] [kotoba.kir :as ir]))
(def source (slurp "src/association_facts.kotoba"))
(defn call [kir function & args] (ir/execute kir function (vec args)))
(defn present [option] (when (second option) (nth option 2)))
(def fields ["id" "title" "association" "isic" "country" "kind" "url"
             "url-provenance" "established-date" "last-revised-date" "retrieved-at"])
(def expected
  [{"id" "awwa.policy-distribution-system-water-quality"
    "title" "AWWA Policy Statement on Distribution System Water Quality"
    "association" "awwa" "isic" "3600" "country" "USA" "kind" "self-regulatory-code"
    "url" "https://www.awwa.org/policy-statement/distribution-system-water-quality/"
    "url-provenance" "official-association-site" "established-date" "1975-01-26"
    "last-revised-date" "2026-04-01" "retrieved-at" "2026-07-15"}
   {"id" "awwa.who-we-are-profile" "title" "Who We Are (organization profile)"
    "association" "awwa" "isic" "3600" "country" "USA" "kind" "governance-program"
    "url" "https://www.awwa.org/who-we-are/"
    "url-provenance" "official-association-site" "established-date" "1881-03-29"
    "last-revised-date" nil "retrieved-at" "2026-07-15"}])

(deftest reference-preserves-fields-dates-and-topics
  (let [kir (:kir (compiler/compile-source source :js-kotoba-v1))
        observed (mapv (fn [i] (into {} (map (fn [f] [f (present (call kir 'entry-field "awwa" i f))]) fields))) [0 1])]
    (is (= expected observed))
    (is (= ["1975-01-26" "1881-03-29"] (mapv #(present (call kir 'entry-field "awwa" % "established-date")) [0 1])))
    (is (= "2026-04-01" (present (call kir 'entry-field "awwa" 0 "last-revised-date"))))
    (is (= [2 1] (mapv #(call kir 'topic-count "awwa" %) [0 1])))
    (is (= ["water-quality" "best-practices"] (mapv #(present (call kir 'topic "awwa" 0 %)) [0 1])))
    (is (= "awwa.who-we-are-profile" (present (call kir 'by-topic-id "awwa" "governance" 0))))
    (is (= #{} (set (:effects kir))))
    (testing "unknown values and invalid indexes fail closed"
      (is (zero? (call kir 'entry-count "wef")))
      (is (nil? (present (call kir 'entry-field "awwa" -1 "id"))))
      (is (nil? (present (call kir 'entry-field "awwa" 2 "id"))))
      (is (nil? (present (call kir 'entry-field "awwa" 1 "last-revised-date"))))
      (is (nil? (present (call kir 'topic "awwa" 1 1))))
      (is (zero? (call kir 'by-topic-count "awwa" "labor")))
      (is (nil? (present (call kir 'by-topic-id "awwa" "governance" 1)))))))

(defn compiler-root []
  (nth (iterate #(.getParent ^java.nio.file.Path %)
                (java.nio.file.Path/of (.toURI (io/resource "kotoba/compiler/core.clj")))) 4))
(defn base64 [value] (.encodeToString (java.util.Base64/getEncoder) value))
(deftest restricted-javascript-and-typed-wasm-conform-semantically
  (let [javascript (compiler/compile-source source :js-kotoba-v1)
        wasm (compiler/compile-source source :wasm32-browser-kotoba-v1)
        js64 (base64 (.getBytes ^String (:source javascript) "UTF-8")) wasm64 (base64 ^bytes (:bytes wasm))
        probe (shell/sh "node" "--input-type=module" "-e"
                (str "import(process.argv[1]).then(async host=>{const j=await import('data:text/javascript;base64," js64 "');"
                     "const w=await host.instantiateKotoba(Buffer.from(process.argv[2],'base64'));const run=x=>{"
                     "if(x['entry-count']('awwa')!==2n||x['entry-field']('awwa',0n,'established-date')[2]!=='1975-01-26'||x['entry-field']('awwa',0n,'last-revised-date')[2]!=='2026-04-01'||x['entry-field']('awwa',1n,'established-date')[2]!=='1881-03-29')throw Error('dates');"
                     "if(x['topic-count']('awwa',0n)!==2n||x['topic']('awwa',0n,1n)[2]!=='best-practices'||x['topic-count']('awwa',1n)!==1n)throw Error('topics');"
                     "if(x['by-topic-id']('awwa','governance',0n)[2]!=='awwa.who-we-are-profile'||x['topic']('awwa',1n,1n)[1]!==false)throw Error('query');};"
                     "run(j.instantiateKotoba({}));run(w.instance.exports);}).catch(e=>{console.error(e);process.exit(99)})")
                (.toString (.toUri (.resolve (compiler-root) "runtime/browser-host.mjs"))) wasm64)]
    (is (zero? (:exit probe)) (str (:out probe) (:err probe)))))
(deftest production-source-authority
  (is (= ["src/association_facts.kotoba"]
         (->> (file-seq (io/file "src")) (filter #(.isFile %)) (map str) sort vec))))
