(ns test-re-agent-multi.core
    (:require
      [reagent.dom :as d]))

;; -------------------------
;; Styles

(def button-style {:box-sizing "border-box"
                   :display "block"
                   :text-decoration "none"
                   :text-align "center"
                   :margin-bottom "1em"
                   :background "palevioletred"
                   :color "papayawhip"
                   :font-size "1.5rem"
                   :font-weight 700
                   :padding "0.5em 1em"
                   :border-radius "9999px"
                   :width "100%"
                   :cursor "pointer"
                   :border "none"
                   :outline "none"})

;; -------------------------
;; Views

(defn button-type-dispatcher [props]
  (cond (contains? props :href) :link-button
        :else :cta-button))

(defmulti button button-type-dispatcher)

(defmethod button :link-button [{:keys [text href]}]
  [:a {:href href
       :style button-style} text])

(defmethod button :cta-button [{:keys [text]}]
  [:button {:style button-style} text])

(defn page []
  [:div
   [:h2 "Hello Pitchers 👋"]
   [button {:text "Button"}]
   [button {:text "Link"
                   :href "https://pitch.com"}]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
