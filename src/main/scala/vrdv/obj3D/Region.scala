package vrdv.obj3D

import fmgp.threejs.Object3D
import vrdv.obj3D
import vrdv.obj3D.plots._

import scala.scalajs.js

/**
  * A Region represents a position in the scene,
  * it is used to anchor multiple objects to one another.
  */
case class Region(id: Int, object3D: Object3D = new Object3D()) {
  var plot: Option[Plot] = None // TODO: Make this private (required changes to rendering section in Environment)
  var gui: Option[DatGui] = None
  obj3D.setMoveable(object3D)

  private var maybeAxes: Option[CoordinateAxes] = None
  def maybeGetAxes(): Option[CoordinateAxes] = maybeAxes

  def addPlot(p: Plot): Unit = {
    // Remove previous plot if it exists and add the new one
    if(plot.nonEmpty) remove(plot.get.getPoints)
    plot = Some(p)
    object3D.add(p.getPoints)

    var axes: CoordinateAxes =
    p match {
      case p3D: Plot3D ⇒
        val axes: CoordinateAxes3D = default3DAxes()
        axes.createAxesTitles(p3D.xVar, p3D.yVar, p3D.zVar)
        axes
      case p2D: Plot2D ⇒
        val axes = default2DAxes()
        axes.createAxesTitles(p2D.xVar, p2D.yVar)
        axes
    }
    maybeAxes = Some(axes)
    add(axes)
  }

  // Convenience methods for Object3D

  def add(something: Object3D): Unit = object3D.add(something)
  def remove(something: Object3D): Unit = object3D.remove(something)

  // Private methods

  private def default2DAxes(): CoordinateAxes2D =
    CoordinateAxes.create2D(1, color = CustomColors.White, centeredOrigin = false, planeGrids = false)

  private def default3DAxes(): CoordinateAxes3D =
    CoordinateAxes.create3D(1, color = CustomColors.White, centeredOrigin = true, planeGrids = false)
}