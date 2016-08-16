/*
 * \\\ Example: Processing
 *
 * This is a tiny bit modified version of Processing.org 
 * for Android. 
 * The usage its pretty similar, you just need to prepend
 * the processing object (p.) to the methods to access them
 * example: p.rect(x, y, w, h)
 *
 */

var processing = ui.addProcessing(0, 0, 1, 1, 'P3D')

var size

processing.setup(function (p) {
  p.background(0)
  p.frameRate(25)
  c = 100
})

processing.draw(function (p) {
  p.fill(0, 20)
  p.rect(0, 0, p.width, p.height)
  p.noStroke()
  p.fill(255)
  
  size = p.dist(p.pmouseX, p.pmouseY, p.mouseX, p.mouseY)
  p.ellipse(p.mouseX, p.mouseY, size, size)
})