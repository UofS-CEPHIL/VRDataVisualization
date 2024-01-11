package vrdv.obj3D

import facade.IFThree.{Font, ShapeGeometryExt}
import fmgp.threejs._

/**
  * Created by Dorian Thiessen on 2018-07-18.
  */
object Text {
  var font: Font = _

  def createTextMesh(text: String, lookatCamera: Boolean = true): Mesh = {
    val shapes = font.generateShapes(text, 1)
    val geometry = new ShapeGeometryExt(shapes)
    geometry.computeBoundingBox()

    val textShape = new BufferGeometry()
    textShape.fromGeometry(geometry)
    val material = new LineBasicMaterial()
    material.side = THREE.DoubleSide
    val mesh = new Mesh(textShape, material)

    mesh.scale.set(0.1, 0.1, 0.1)
    mesh.position.set(0, 0, 0)
    mesh
  }
}
