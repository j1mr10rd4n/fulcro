(ns user
  (:require
    [clojure.pprint :refer [pprint]]
    [clojure.repl :refer [doc source]]
    [clojure.tools.namespace.repl :as tools-ns :refer [disable-reload! refresh clear set-refresh-dirs]]
    [figwheel-sidecar.system :as fig]
    [com.stuartsierra.component :as component]
    [fulcro-spec.selectors :as sel]
    [clojure.spec.test.alpha :as st]
    [fulcro-spec.suite :as suite]
    [fulcro.easy-server :as easy]))

;t(st/instrument)
(set-refresh-dirs "src/main" "src/test" "src/dev" "src/tutorial" "src/cards")

(suite/def-test-suite server-test-server
  {:config       {:port 8888}
   :test-paths   ["src/test"]
   :source-paths ["src/main"]}
  {:available #{:focused}
   :default   #{::sel/none :focused}})

(def figwheel (atom nil))

(defn start-figwheel
  "Start Figwheel on the given builds, or defaults to build-ids in `figwheel-config`."
  ([]
   (let [figwheel-config (fig/fetch-config)
         props           (System/getProperties)
         all-builds      (->> figwheel-config :data :all-builds (mapv :id))]
     (start-figwheel (keys (select-keys props all-builds)))))
  ([build-ids]
   (let [figwheel-config   (fig/fetch-config)
         port              (some-> (System/getProperty "figwheel.port") Integer/parseInt)
         default-build-ids (-> figwheel-config :data :build-ids)
         build-ids         (if (empty? build-ids) default-build-ids build-ids)
         preferred-config  (cond-> (assoc-in figwheel-config [:data :build-ids] build-ids)
                             (and port (pos? port)) (assoc-in [:data :figwheel-options :server-port] port))]
     (reset! figwheel (component/system-map
                        :figwheel-system (fig/figwheel-system preferred-config)
                        :css-watcher (fig/css-watcher {:watch-paths ["resources/public/css"]})))
     (println "STARTING FIGWHEEL ON BUILDS: " build-ids)
     (swap! figwheel component/start)
     (fig/cljs-repl (:figwheel-system @figwheel)))))

(comment

  (server/defmutation fulcro.democards.pessimistic-transaction-cards/a [_]
    (action [_] (println "A")
      (Thread/sleep 2000)))

  (server/defmutation fulcro.democards.pessimistic-transaction-cards/c [_]
    (action [_] (println "C") (Thread/sleep 2000)))

  (def server (atom (easy/make-fulcro-server :config-path "config/dev.edn")))
  (swap! server component/start)
  (swap! server component/stop)
  )
