(ns untangled-devguide.N-Twitter-Bootstrap
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [devcards.util.edn-renderer :refer [html-edn]]
            [devcards.core :as dc :refer-macros [defcard defcard-doc deftest]]
            [cljs.reader :as r]
            [om.next.impl.parser :as p]
            [devcards.core :as dc :refer-macros [defcard defcard-doc]]
            [untangled.ui.elements :as ele]
            [untangled.ui.bootstrap :as b]
            [untangled.client.core :as uc]))

(declare render-example)

(defcard-doc
  "
  # Twitter Bootstrap

  Untangled includes functions that emit the DOM with CSS for version 3 of Twitter's Bootstrap CSS and Components.

  Notice that the CSS affects many DOM elements, which means you'll see examples that use Om's DOM functions, which
  in turn require a JavaScript object as the first argument (for performance). The helper functions from the
  `bootstrap` namespace need to modify the incoming arguments, so the first argument (if it takes DOM props) is
  a cljs map instead. ")

(defn- col [attrs children] (b/col (assoc attrs :className "boxed") children))

(defcard grids
  "
  The four iframes below represent the widths of a large, medium, small, and xsmall screen. The content being
  rendered is:

  ```
  (b/container-fluid {}
    (b/row {}
      (b/col {:xs 12 :md 8} \"xs 12 md 8\") (b/col {:xs 6 :md 4} \"xs 6 md 4\"))
    (b/row {}
      (b/col {:xs 6 :md 4} \"xs 6 md 4\")
      (b/col {:xs 6 :md 4} \"xs 6 md 4\")
      (b/col {:xs 6 :md 4} \"xs 6 md 4\"))
    (b/row {} (b/col {:xs 6 } \"xs 6\") (b/col {:xs 6 } \"xs 6\")))
  ```

  See the Bootstrap documetation for more details.
  "
  (fn [state _]
    (dom/div nil
      (dom/h4 nil "Large")
      (render-example "1400px" "100px"
        (b/container-fluid {}
          (b/row {}
            (col {:xs 12 :md 8} "xs 12 md 8") (col {:xs 6 :md 4} "xs 6 md 4"))
          (b/row {}
            (col {:xs 6 :md 4} "xs 6 md 4")
            (col {:xs 6 :md 4} "xs 6 md 4")
            (col {:xs 6 :md 4} "xs 6 md 4"))
          (b/row {} (col {:xs 6} "xs 6") (col {:xs 6} "xs 6"))))
      (dom/h4 nil "Medium")
      (render-example "1000px" "100px"
        (b/container-fluid {}
          (b/row {}
            (col {:xs 12 :md 8} "xs 12 md 8") (col {:xs 6 :md 4} "xs 6 md 4"))
          (b/row {}
            (col {:xs 6 :md 4} "xs 6 md 4")
            (col {:xs 6 :md 4} "xs 6 md 4")
            (col {:xs 6 :md 4} "xs 6 md 4"))
          (b/row {} (col {:xs 6} "xs 6") (col {:xs 6} "xs 6"))))
      (dom/h4 nil "Small")
      (render-example "800px" "120px"
        (b/container-fluid {}
          (b/row {}
            (col {:xs 12 :md 8} "xs 12 md 8") (col {:xs 6 :md 4} "xs 6 md 4"))
          (b/row {}
            (col {:xs 6 :md 4} "xs 6 md 4")
            (col {:xs 6 :md 4} "xs 6 md 4")
            (col {:xs 6 :md 4} "xs 6 md 4"))
          (b/row {} (col {:xs 6} "xs 6") (col {:xs 6} "xs 6"))))
      (dom/h4 nil "X-Small")
      (render-example "600px" "120px"
        (b/container-fluid {}
          (b/row {}
            (col {:xs 12 :md 8} "xs 12 md 8") (col {:xs 6 :md 4} "xs 6 md 4"))
          (b/row {}
            (col {:xs 6 :md 4} "xs 6 md 4")
            (col {:xs 6 :md 4} "xs 6 md 4")
            (col {:xs 6 :md 4} "xs 6 md 4"))
          (b/row {} (col {:xs 6} "xs 6") (col {:xs 6} "xs 6")))))))

(defcard typography
  "Various elements support modified typography."
  (render-example "100%" "400px"
    (b/container {}
      (b/row nil
        (b/col {:xs 6} "(b/lead {} \"Lead Text\")")
        (b/col {:xs 6} (b/lead {} "Lead Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/p #js {} \"Paragraph Text\")")
        (b/col {:xs 6} (dom/p #js {} "Paragraph Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/mark #js {} \"Highlighted Text\")")
        (b/col {:xs 6} (dom/mark #js {} "Highlighted Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/del #js {} \"Deleted Text\")")
        (b/col {:xs 6} (dom/del #js {} "Deleted Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/s #js {} \"Strikethrough Text\")")
        (b/col {:xs 6} (dom/s #js {} "Strikethrough Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/ins #js {} \"Inserted Text\")")
        (b/col {:xs 6} (dom/ins #js {} "Inserted Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/u #js {} \"Underlined Text\")")
        (b/col {:xs 6} (dom/u #js {} "Underlined Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/small #js {} \"Small Text\")")
        (b/col {:xs 6} (dom/small #js {} "Small Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/strong #js {} \"Bold Text\")")
        (b/col {:xs 6} (dom/strong #js {} "Bold Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/em #js {} \"Italic Text\")")
        (b/col {:xs 6} (dom/em #js {} "Italic Text")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(b/address \"Some Company\" :street \"111 NW 1st St.\" :city-state \"Chicago, IL 43156\" :phone \"(316) 555-1212\")")
        (b/col {:xs 6} (b/address "Some Company" :street "111 NW 1st St." :city-state "Chicago, IL 43156"
                         :phone "(316) 555-1212")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/blockquote nil (dom/p nil \"Block quoted Text\"))")
        (b/col {:xs 6} (dom/blockquote nil (dom/p nil "Block quoted Text"))))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(b/quotation {:source \"in Joe's Diary\" :credit \"Joe\"} (dom/p nil \"Some crap he said.\"))")
        (b/col {:xs 6} (b/quotation {:source "in Joe's Diary" :credit "Joe"} (dom/p nil "Some crap he said."))))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(b/quotation {:align :right :source \"in Joe's Diary\" :credit \"Joe\"} (dom/p nil \"Some crap he said.\"))")
        (b/col {:xs 6} (b/quotation {:align :right :source "in Joe's Diary" :credit "Joe"} (dom/p nil "Some crap he said."))))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/ul #js {} (dom/li nil \"A\") (dom/li nil \"B\")")
        (b/col {:xs 6} (dom/ul #js {} (dom/li nil "A") (dom/li nil "B"))))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(b/plain-ul {} (dom/li nil \"A\") (dom/li nil \"B\")")
        (b/col {:xs 6} (b/plain-ul {} (dom/li nil "A") (dom/li nil "B"))))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(b/inline-ul {} (dom/li nil \"A\") (dom/li nil \"B\")")
        (b/col {:xs 6} (b/inline-ul {} (dom/li nil "A") (dom/li nil "B")))))))


(defcard formatting-code
  "The look of formatted code."
  (render-example "100%" "400px"
    (b/container {}
      (b/row nil
        (b/col {:xs 6} "(dom/code #js {} \"pwd\")")
        (b/col {:xs 6} (dom/code #js {} "pwd")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/kbd #js {} \"CTRL-C\")")
        (b/col {:xs 6} (dom/kbd #js {} "CTRL-C")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/pre #js {} \"(map identity\\n  list)\")")
        (b/col {:xs 6} (dom/pre #js {} "(map identity\n  list)")))
      (dom/hr nil)
      (b/row nil
        (b/col {:xs 6} "(dom/samp #js {} \"This is supposed to represent computer output\")")
        (b/col {:xs 6} (dom/samp #js {} "This is supposed to represent computer output")))
      (dom/hr nil))))

(defcard tables
  "Bootstrip includes styles for tables. The `b/table` wrapper handles remember the classes for you."
  (render-example "100%" "400px"
    (dom/div nil
      (dom/h4 nil "A plain table (table class automatically added with `(b/table ...)`:")
      (b/table {}
        (dom/thead nil
          (dom/tr nil
            (dom/th nil "H1")
            (dom/th nil "H2")
            (dom/th nil "H3")))
        (dom/tbody nil
          (dom/tr nil
            (dom/td nil "1")
            (dom/td nil "2")
            (dom/td nil "3"))
          (dom/tr nil
            (dom/td nil "1")
            (dom/td nil "2")
            (dom/td nil "3"))
          (dom/tr nil
            (dom/td nil "1")
            (dom/td nil "2")
            (dom/td nil "3"))))
      (dom/h4 nil "A table with `(b/table { :styles #{:striped :hover} } ...)`:")
      (b/table {:styles #{:striped :hover}}
        (dom/thead nil
          (dom/tr nil
            (dom/th nil "H1")
            (dom/th nil "H2")
            (dom/th nil "H3")))
        (dom/tbody nil
          (dom/tr nil
            (dom/td nil "1")
            (dom/td nil "2")
            (dom/td nil "3"))
          (dom/tr nil
            (dom/td nil "1")
            (dom/td nil "2")
            (dom/td nil "3"))
          (dom/tr nil
            (dom/td nil "1")
            (dom/td nil "2")
            (dom/td nil "3"))))
      (dom/h4 nil "A table with `(b/table { :styles #{:bordered :condensed} } ...)`:")
      (b/table {:styles #{:bordered :condensed}}
        (dom/thead nil
          (dom/tr nil
            (dom/th nil "H1")
            (dom/th nil "H2")
            (dom/th nil "H3")))
        (dom/tbody nil
          (dom/tr nil
            (dom/td nil "1")
            (dom/td nil "2")
            (dom/td nil "3"))
          (dom/tr nil
            (dom/td nil "1")
            (dom/td nil "2")
            (dom/td nil "3"))
          (dom/tr nil
            (dom/td nil "1")
            (dom/td nil "2")
            (dom/td nil "3")))))))

(defcard form-fields
  "Labelled fields for forms"
  (render-example "100%" "400px"
    (dom/div #js {}
      (b/labeled-input {:id "name" :type "text"} "Name:")
      (b/labeled-input {:id "address" :type "text" :error "Must not be empty!"} "Address:")
      (b/labeled-input {:id "phone" :type "text" :success "You can leave this blank."} "Phone:")
      (b/labeled-input {:id "email" :type "email" :help "Your primary email"} "Email:"))))

(defcard form-fields
  "Labelled fields for forms"
  (render-example "100%" "400px"
    (dom/div #js {:className "form-horizontal"}
      (b/labeled-input {:id "name" :split 2 :type "text"} "Name:")
      (b/labeled-input {:id "address" :split 2 :type "text" :error "Must not be empty!"} "Address:")
      (b/labeled-input {:id "phone" :split 2 :type "text"} "Phone:")
      (b/labeled-input {:id "email" :split 2 :type "email" :help "Your primary email"} "Email:"))))

(defn render-example [width height & children]
  (ele/ui-iframe {:frameBorder 0 :height height :width width}
    (dom/div nil
      (dom/style nil ".boxed {border: 1px solid black}")
      (dom/link #js {:rel         "stylesheet"
                     :href        "/bootstrap-3.3.7/css/bootstrap-theme.min.css"
                     ;:integrity   "sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
                     :crossorigin "anonymous"})
      (dom/link #js {:rel         "stylesheet"
                     :href        "/bootstrap-3.3.7/css/bootstrap.min.css"
                     ;:integrity   "sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
                     :crossorigin "anonymous"})
      children)))