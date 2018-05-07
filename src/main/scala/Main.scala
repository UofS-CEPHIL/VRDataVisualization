import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import org.scalajs.{threejs => THREE}
import org.scalajs.dom
import userinput.Controls
import env.Environment
import window.Window
import facades.Stats
import facades.IFThree.{WEBVR, WebGLRendererExt}

/**
  * Created by Dorian Thiessen on 2018-01-05.
  */
@JSExportTopLevel("VRDataVisualization")
object Main {

  var controls: Controls = _
  var env: Environment = _
  var stats: Stats = _
  @JSExport("foundVRHeadset")
  var foundVRHeadset: Boolean = false

  @JSExport("init")
  def init(): Unit = {
    // Setup the Environment (Scene, Camera, Renderer) and the Controls (Mouse, Oculus Controllers and Headset)
    val container = dom.document.getElementById("scene-container")
    env = Environment.setup(container)

    /* TODO: Make the button unfocusable to prevent it from being highlighted on dblclick
    val button = WEBVR.createButton(env.renderer)
    val hasIt = button.hasAttribute("focusable")
    dom.console.log(hasIt)
    if(hasIt) {
      dom.console.log(button.getAttribute("focusable"))
      button.setAttribute("focusable", "false")
    }*/
    dom.document.body.appendChild( WEBVR.createButton(env.renderer) )

    if(foundVRHeadset) {
      dom.console.log("Found headset")
      env.renderer.asInstanceOf[WebGLRendererExt].vr.enabled = true
    } else {
      // This doesn't move camera! TODO: Move camera up ~1.6m when not using Oculus
      //env.camera.translateY(1.6)
    }

    Window.setupEventListeners(env.camera, env.renderer)
    controls = Controls.setup(env)

    // Add FPS stats to the Window
    stats = new Stats()
    container.appendChild(stats.dom)

    // This is only needed if we are not using either Oculus or webvr-polyfill
    //animate(0) // Trigger the animation cycle
  }

  @JSExportTopLevel("animate")
  def animate(timeStamp: Double): Unit = {
    dom.window.requestAnimationFrame(animate)
    if(controls != null) controls.update(timeStamp)
    if(env != null) env.render()
    if(stats != null) stats.update()
  }

  @JSExport("renderer") // Temporary. renderer currently required in global scope.
  def getRenderer: THREE.Renderer = env.renderer

}