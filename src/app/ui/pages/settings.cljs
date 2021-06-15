(ns app.ui.pages.settings
  "Example homepage 2 3"
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [helix.hooks :as hooks]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.controllers.router :as router]
            ;;[keechma.next.helix.template :refer [defnt fill-slot configure]]
            [keechma.next.helix.classified :refer [defclassified]]
            [app.ui.components.main :refer [Main]]
            [app.ui.components.hello :refer [Hello]]))

(defclassified HomepageWrapper :div "h-screen w-screen flex bg-gray-200")

(defnc Progress []
  (let [[width set-width] (hooks/use-state 0)]
    (hooks/use-effect :once (set-width 10))

    (println width)

    (d/div {:class "bg-blue-200 transition-all duration-1000" 
            :style {:width (str width "%")}}
           "Settings")))

(defnc HomeRenderer [props]
  (let [[toggle set-toggle] (hooks/use-state false)]
    
    ($ HomepageWrapper
       (d/div {:class "flex flex-1 flex-col items-center justify-center px-2"}
              ($ Main)
              ($ Hello)
              (d/a
               {:href (router/get-url props :router {:page "home"})}
               "Home")
              (d/button {:on-click #(set-toggle (not toggle))} "Toggle")
              (when
               true
               ;toggle
               ($ Progress))))))

(def Settings (with-keechma HomeRenderer))