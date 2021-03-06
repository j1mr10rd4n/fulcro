(ns fulcro.client.localized-dom
  (:refer-clojure :exclude [map meta time use set symbol filter])
  (:require
    [clojure.future :refer :all]
    [fulcro.util :as util]
    [fulcro.client.dom :as adom]
    fulcro.client.dom-common
    fulcro.client.localized-dom-common)
  (:import (cljs.tagged_literals JSValue)))

(defn emit-tag [str-tag-name args]
  (let [conformed-args (util/conform! ::adom/dom-macro-args args)
        {attrs    :attrs
         children :children
         css      :css} conformed-args
        css-props      (if css `(fulcro.client.localized-dom-common/add-kwprops-to-props nil ~css) nil)
        children       (mapv (fn [[_ c]]
                               (if (or (nil? c) (string? c))
                                 c
                                 `(fulcro.util/force-children ~c))) children)
        attrs-type     (or (first attrs) :nil)              ; attrs omitted == nil
        attrs-value    (or (second attrs) {})
        create-element (case str-tag-name
                         "input" 'fulcro.client.dom/macro-create-wrapped-form-element
                         "textarea" 'fulcro.client.dom/macro-create-wrapped-form-element
                         "select" 'fulcro.client.dom/macro-create-wrapped-form-element
                         "option" 'fulcro.client.dom/macro-create-wrapped-form-element
                         'fulcro.client.dom/macro-create-element*)]
    (case attrs-type
      :js-object
      (let [attr-expr `(fulcro.client.localized-dom-common/add-kwprops-to-props ~attrs-value ~css)]
        `(~create-element
           ~(JSValue. (into [str-tag-name attr-expr] children))))

      :map
      (let [attr-expr (if (or css (contains? attrs-value :classes))
                        `(fulcro.client.localized-dom-common/add-kwprops-to-props ~(adom/clj-map->js-object attrs-value) ~css)
                        (adom/clj-map->js-object attrs-value))]
        `(~create-element ~(JSValue. (into [str-tag-name attr-expr] children))))

      :runtime-map
      (let [attr-expr `(fulcro.client.localized-dom-common/add-kwprops-to-props ~(adom/clj-map->js-object attrs-value) ~css)]
        `(~create-element
           ~(JSValue. (into [str-tag-name attr-expr] children))))

      (:symbol :expression)
      `(fulcro.client.localized-dom/macro-create-element
         ~str-tag-name ~(into [attrs-value] children) ~css)

      ;; also used for MISSING props
      :nil
      `(~create-element
         ~(JSValue. (into [str-tag-name css-props] children)))

      ;; pure children
      `(fulcro.client.localized-dom/macro-create-element
         ~str-tag-name ~(JSValue. (into [attrs-value] children)) ~css))))

(adom/gen-dom-macros fulcro.client.localized-dom/emit-tag)
