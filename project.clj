(defn- inside-container? []
  (.exists (clojure.java.io/file "/.dockerenv")))


(def figwheel-opts
  (when (inside-container?)
    (let [specified-host (if-let [x (System/getenv "FIGWHEEL_HOST")]
                           (if (clojure.string/blank? (clojure.string/trim x)) nil x))
          host (or specified-host "192.168.99.100")]
      (when (nil? specified-host)
        (println (str "***\n"
                      "*** You did not specify a FIGWHEEL_HOST environment variable.\n"
                      "*** Using the default: " host "\n"
                      "***")))
      {:websocket-url (str "ws://" host ":3449/figwheel-ws")})))


(def figwheel-server-opts
  (when (inside-container?)
    {:hawk-options {:watcher :polling}}))


(defproject angeloocana/tic-tac-toe-ai-clojure "0.0.1"
  :dependencies
  [
   [dmohs/react "0.2.12"]
   [org.clojure/clojure "1.7.0"]
   [org.clojure/clojurescript "1.7.228"]
   ]
  :plugins [[lein-cljsbuild "1.1.2"] [lein-resource "15.10.2"]]
  :profiles {:dev {:plugins [[lein-figwheel "0.5.0" :exclusions [org.clojure/clojure]]]
                   :dependencies [[binaryage/devtools "0.5.2"]
                                  [devcards "0.2.1" :exclusions [cljsjs/react]]]
                   :cljsbuild
                   {:builds {:client {:source-paths ["src/cljs/devtools"]
                                      :compiler
                                      {:optimizations :none
                                       :source-map true
                                       :source-map-timestamp true}
                                      :figwheel ~(merge {} figwheel-opts)}}}
                   :figwheel ~figwheel-server-opts}
             :figwheel {:cljsbuild
                        {:builds
                         {:client {:source-paths ["src/cljs/figwheel"]
                                   :compiler {:main "angeloocana.tic-tac-toe-ai-clojure.main"}}}}}
             :devcards {:cljsbuild
                        {:builds {:client {:source-paths ["src/cljs/devcards"]
                                           :compiler {:main "angeloocana.tic-tac-toe-ai-clojure.devcards"}
                                           :figwheel ~(merge figwheel-opts
                                                             {:devcards true})}}}}
             :deploy {:cljsbuild
                      {:builds {:client {:source-paths ["src/cljs/deploy"]
                                         :compiler
                                         {:main "angeloocana.tic-tac-toe-ai-clojure.main"
                                          :optimizations :simple
                                          :pretty-print false}}}}}}
  :cljsbuild {:builds {:client {:source-paths ["src/cljs/core"]
                                :compiler {:output-dir "target/build"
                                           :asset-path "build"
                                           :output-to "target/compiled.js"}}}}
  :resource {:resource-paths ["src/static"] :skip-stencil [#".*"]})
