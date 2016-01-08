(ns ^:figwheel-load simple-screen-recorder.android.core
  (:require-macros [env.require-img :refer [require-img]])
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [simple-screen-recorder.handlers]
            [simple-screen-recorder.subs]))

(set! js/React (js/require "react-native/Libraries/react-native/react-native.js"))

(def icon (r/adapt-react-class (js/require "react-native-vector-icons/FontAwesome")))
;; (def linear-gradient (js/require "LinearGradient"))
(def app-registry (.-AppRegistry js/React))
(def text (r/adapt-react-class (.-Text js/React)))
(def view (r/adapt-react-class (.-View js/React)))
(def image (r/adapt-react-class (.-Image js/React)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight js/React)))
(def stylesheet (r/adapt-react-class (.-StyleSheet js/React)))
(def logo-img (require-img "./images/cljs.png"))


(defn widget []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      
      [view {:style
             {:flex 1
              :flexDirection "column"
              :backgroundColor "#FFF"
              :margin 0
              :alignItems "center"
              :color "#000"
              ;; :padding 10
              }}
       [view {:style {:flexDirection "row"
                      :alignItems "center"
                      ;; :color "#000"
                      :backgroundColor "#FFB700"
                      :alignSelf "stretch"
                      :height 40
                      :justifyContent "space-between"
                      }}
        [icon {:name "bars"
               :size 15
               :color "#000"
               :style {:margin-left 0}}]
        [text {:style {:color "#000"
                       :textAlign "center"
                       :fontSize 15}}
         "Simple Screen Recorder"]
        [icon {:name "ellipsis-v"
              :size 15
              :color "#000"
               :style {:margin-right 10}}]]
       
       ;; [toolbar-android {:title "Simple Screen Recorder"
       ;;                   :titleColor "#000"
       ;;                   :subtitle "-YYEEESssssssssss"
       ;;                   :style {:justifyContent "center"
       ;;                           :fontSize 5
       ;;                           ;; :alignItems "center"
       ;;                           :alignSelf "stretch"
       ;;                           :textAlign "center"
       ;;                           :flexDirection "row"
       ;;                           :backgroundColor "#FFB700"
       ;;                           :color "#FFF"
       ;;                           :height 40}}]
       ])))


;; [image {:source logo-img :style  {:width 30 :height 30 :marginBottom 10}}]
(defn mount-root []
      (r/render [widget] 1))

(defn ^:export init []
      (dispatch-sync [:initialize-db])
      (.registerRunnable app-registry "SimpleScreenRecorder" #(mount-root)))