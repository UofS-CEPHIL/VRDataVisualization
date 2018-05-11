package facades

import org.scalajs.threejs.{Camera, Object3D, Scene}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSName}
import scala.scalajs.js.|

/**
  * Facades for DatGUIVR by the Google Data Arts Team!
  * Find it here: https://github.com/dataarts/dat.guiVR
  */
object Dat {

  /**
    * Created by Dorian Thiessen on 2018-05-07.
    */
  @js.native
  @JSGlobal("dat.GUIVR")
  object GUIVR extends js.Object {
    def create(title: String): GUI = js.native
    def addInputObject(obj: Object3D): Object3D = js.native
    def addInputObject(obj: js.Any): Unit = js.native
    def enableMouse(camera: Camera): Unit = js.native
  }
  @js.native
  trait GUI extends Object3D {
    def add(something: js.Object): Unit = js.native
    def add(something: js.Object, id: String): Unit = js.native
    def add(something: js.Object, id: String, min: Double, max: Double): GuiSlider = js.native
    def addFolder(title: String): Folder = js.native
    def open(): Unit = js.native
  }

  @js.native
  trait Folder extends GUI {
    /*override def add(something: js.Object): Unit = js.native
    override def add(something: js.Object, id: String): Unit = js.native*/
  }

  @js.native
  trait GuiSlider extends js.Object {
    def listen(): GuiSlider = js.native
    def name(name: String): GuiSlider = js.native
    def min(n: Double): GuiSlider = js.native
    def max(n: Double): GuiSlider = js.native
    def step(n: Double): GuiSlider = js.native
  }

  /*@js.native
  @JSGlobal("createGUI")
  object createGUI extends js.Function {
    def apply(): Unit = js.native
  }

  @js.native
  @JSGlobal("updateGUI")
  object updateGUI extends js.Function {
    def apply(`object`: js.Object): Unit = js.native
  }*/
}