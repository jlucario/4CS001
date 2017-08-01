import buckley.*
import java.util.*
import java.lang.System
import java.awt.*
import java.awt.event.*
import javax.swing.*;

// ################################################################################################################################################
// ###################    Vector classes  #########################################################################################################
// ################################################################################################################################################
// Vector2
class Vector2{
 def X
 def Y

 Vector2(def x, def y){
  X = x
  Y = y
 }

 static Vector2 zero(){
  return new Vector2(0,0)
 }

}

// Vector3
class Vector3{
 def X
 def Y
 def Z

 Vector3(def x, def y, def z){
  X = x
  Y = y
  Z = z
 }

 static Vector3 Up(){
  return new Vector3(0f, 1f, 0f)
 }

 void Normalize(){
  float num2 = (X * X) + (Y * Y) + (Z * Z)
  float num = 1f / Math.sqrt(num2)
  X *= num
  Y *= num
  Z *= num
 }

 static Vector3 Normalize(Vector3 value){
  float num2 = (value.X * value.X) + (value.Y * value.Y) + (value.Z * value.Z)
  float num = 1f / Math.sqrt(num2)
  return new Vector3(value.X * num, value.Y * num, value.Z * num)
 }

 static float Dot(Vector3 vec1, Vector3 vec2){
  return (((vec1.X * vec2.X) + (vec1.Y * vec2.Y) + (vec1.Z * vec2.Z)))
 }

 Vector3 negative(){
  return new Vector3(-X,-Y,-Z)
 }

 Vector3 plus(Vector3 vec){
  return new Vector3(vec.X + X, vec.Y + Y, vec.Z + Z)
 }

 Vector3 minus(Vector3 vec){
  return new Vector3(vec.X - X, vec.Y - Y, vec.Z - Z)
 }

 static Vector3 Cross(Vector3 vector1, Vector3 vector2){
  def vecX = (vector1.Y * vector2.Z) - (vector1.Z * vector2.Y)
  def vecY = (vector1.Z * vector2.X) - (vector1.X * vector2.Z)
  def vecZ = (vector1.X * vector2.Y) - (vector1.Y * vector2.X)
  return new Vector3(vecX, vecY, vecZ)
 }

 static Vector3 Zero(){
  return new Vector3(0,0,0)
 }
}

// Vector4
class Vector4{
 def D
 def X
 def Y
 def Z

 Vector4(def x, def y, def z, def d){
  X = x
  Y = y
  Z = z
  D = d
 }

 void Normalize(){
  float num2 = (X * X) + (Y * Y) + (Z * Z) + (D * D)
  float num = 1f / Math.sqrt(num2)
  X *= num
  Y *= num
  Z *= num
  D *= num
 }

 static Vector4 Normalize(Vector4 value){
  float num2 = (value.X * value.X) + (value.Y * value.Y) + (value.Z * value.Z) + (value.D * value.D)
  float num = 1f / Math.sqrt(num2)
  return new Vector3(value.X * num, value.Y * num, value.Z * num, value.D * num)
 }

 static float Dot(Vector4 vec1, Vector4 vec2){
  return (((vec1.X * vec2.X) + (vec1.Y * vec2.Y) + (vec1.Z * vec2.Z) + (vec1.D * vec2.D)))
 }

 Vector4 negative(){
  return new Vector4(-X,-Y,-Z)
 }

 Vector4 plus(Vector4 vec){
  return new Vector4(vec.X + X, vec.Y + Y, vec.Z + Z, vec.D + D)
 }

 Vector4 minus(Vector4 vec){
  return new Vector4(vec.X - X, vec.Y - Y, vec.Z - Z, vec.D - D)
 }

 static Vector4 Zero(){
  return new Vector3(0,0,0,0)
 }
}

// ################################################################################################################################################
// ###################    Engine Class  ###########################################################################################################
// ################################################################################################################################################
class Engine{

 def backColour

 // Initialize graphic engine and set the default back colour.
 Engine(){ 
  backColour = Scribbler.BLACK
     
 }
 
  void DrawPrimitiveColouredToBuffer(VertexPositionColour[] pointList){
  for(int i = 0; i < pointList.length; i++){
   Vector2 points1 = pointList[i].position
   Vector2 points2
   if ((i + 1) >= pointList.length){
    points2 = pointList[0].position
   }else{
    points2 = pointList[i + 1].position
   }
   DrawLine(points1, points2, pointList[i].colour)
  }
 }
 
 void DrawLine(Vector2 p1, Vector2 p2, int colour)
 {
  for(X in p1.X..p2.X){
   for(Y in p1.Y..p2.Y){
    Scribbler.setPixel(X,Y,colour)
   }
  }
 }
 
 void InitializeEngine(){
  setAllPixelsToClear()
  Scribbler.render()
 }
 
 void Render(){
  Scribbler.render()
 }
 
 void setAllPixelsToClear(){
  for(x in 0..256){
   for(y in 0..256){
    Scribbler.setPixel(x,y, backColour)
   }
  }
 }
 
 void setRectangletoClear(Rectangle rect){
  for(x in rect.location.X..(rect.location.X + rect.Width)){
   for(y in (rect.location.Y - rect.Height)..rect.location.Y){
    Scribbler.setPixel(x, y, backColour)
   }
  }
}
}


// ################################################################################################################################################
// ###################    Vertex data stuctures ###################################################################################################
// ################################################################################################################################################
class VertexPositionColour{
 Vector2 position
 int colour
 
 VertexPositionColour(Vector2 Position, int Colour){
  position = Position
  colour = Colour
 }

 def equals(VertexPositionColour B){
  return ((colour == B.color) && (position == B.position))
 }
}

// ################################################################################################################################################
// ###################    Primitive shapes ########################################################################################################
// ################################################################################################################################################

// ############# Primitive Shape Interface ####################

interface PrimitiveShape{
boolean getFill();
VertexPositionColour[] getDrawData();
int getColour();
}

class Circle extends Rectangle{
int radius
int yCenter
int xCenter
Circle(int Radius, int YCenter, int XCenter){
super(new Vector2(XCenter - Radius, YCenter + Radius), Radius << 1, Radius << 1)
radius = Radius
yCenter = YCenter
xCenter = XCenter

}

int getWidth(){
 return radius << 1
}

int getHeight(){
 return radius << 1
}

private final void drawCirclePointsToBuffer(int cx, int cy, int x, int y, int colour){
 if (x ==0){
 Scribbler.setPixel(cx, cy + y, colour)
 Scribbler.setPixel(cx, cy - y, colour)
 Scribbler.setPixel(cx + y, cy, colour)
 Scribbler.setPixel(cx - y, cy, colour)
 } else if (x == y) {
  Scribbler.setPixel(cx + x, cy + y, colour)
  Scribbler.setPixel(cx - x, cy + y, colour)
  Scribbler.setPixel(cx + x, cy - y, colour)
  Scribbler.setPixel(cx - x, cy - y, colour)
 } else if (x < y) {
  Scribbler.setPixel(cx + x, cy + y, colour)
  Scribbler.setPixel(cx - x, cy + y, colour)
  Scribbler.setPixel(cx + x, cy - y, colour)
  Scribbler.setPixel(cx - x, cy - y, colour)
  Scribbler.setPixel(cx + y, cy + x, colour)
  Scribbler.setPixel(cx - y, cy + x, colour)
  Scribbler.setPixel(cx + y, cy - x, colour)
  Scribbler.setPixel(cx - y, cy - x, colour)
 }
}

void drawCircleToBuffer(int colour){
int x = 0
int y = radius
int p = (5 - (radius << 2)) >> 2

drawCirclePointsToBuffer(xCenter, yCenter, x, y, colour)

while(x < y){
 x++
 if (p < 0) {
  p += (x << 1) + 1
 } else {
  y--
  p += ((x - y) + 1) << 1
 }
 drawCirclePointsToBuffer(xCenter, yCenter, x, y, colour)
}

}
 
}


class Rectangle implements PrimitiveShape{
Vector2 location                // Indicates up left hand corner location of rectangle.
int Width
int Height
private static Rectangle _empty
private boolean _fill
int fillColour

int getLeft(){ return location.X }
int getRight(){ return location.X + width }
int getTop(){ return location.Y }
int getBottom(){return location.Y - Height}

Vector2 getCenter(){ return new Vector2(location.X + (Width >> 1), location.Y - (Height >> 1))}
public static Rectangle Empty(){ return _empty }
boolean IsEmpty(){ return ((((Width == 0) && (Height == 0)) && (X == 0)) && (Y == 0)) }
public Rectangle(Vector2 Location, int width, int height){
 location = Location
 Width = width
 Height = height
}
void Offset(Vector2 amount){
 location.X += amount.X
 location.Y += amount.Y
}
void Inflate(int horizontalAmount, int verticalAmount){
 location.X -= horizontalAmount
 location.Y -= verticalAmount
 Width += horizontalAmount << 1
 Height += verticalAmount << 1
}
boolean Contains(int x, int y){
 return (((( location.X <= x) && (x < (location.X + Width))) && (location.Y >= y)) && (y > (location.Y - Height)))
}
boolean Contains(Vector2 point){
 return Contains(point.X, point.Y)
}
 // Determines whether a rectangle entirely contains a specified rectangle
boolean Contains(Rectangle value){
 return ((((location.X <= value.location.X) && ((value.location.X + value.Width) <= (location.X + Width))) && (location.Y >= value.location.Y)) && ((value.location.Y - value.Hieght) <= (location.Y -Height)))
}
static boolean Intersects(Rectangle value1, Rectangle value2){}

boolean Intersects(Rectangle value){
 return ((((value.location.X < (location.X + Width)) && (location.X < (value.location.X + value.Width))) && (value.location.Y > (location.Y - Height))) && (location.Y > (value.location.Y - value.Height)))
}
static Rectangle Intersect(Rectangle value1, Rectangle value2){
 Rectangle rectangle = new Rectangle(new Vector2(0,0), 0,0)
 int n8 = value1.location.X + value1.Width
 int n7 = value2.location.X + value2.Width
 int n6 = value1.location.Y - value1.Height
 int n5 = value2.location.Y - value2.Height
 int n2 = (value1.location.X > value2.location.X)? value1.location.X : value2.location.X
 int n = (value1.location.Y < value2.location.Y)? value1.location.Y : value2.location.Y
 int n4 = (n8 < n7) ? n8 : n7
 int n3 = (n6 < n5)? n6 : n5
 
 if((n4 < n2) && (n3 > n)){
 
 
  rectangle.location.X = n2
  rectangle.location.Y = n
  rectangle.Width = n4 - n2
  rectangle.Height = n3 - n
  return rectangle
 }
 
 return rectangle
}

static Rectangle Union(Rectangle value1, Rectangle value2){
 int n6 = value1.location.X + value1.Width
 int n5 = value2.location.X + value2.Width
 int n4 = value1.location.Y - value1.Height
 int n3 = value2.location.Y - value2.Height
 int n2 = (value1.location.X < value2.location.X) ? value1.location.X : value2.location.X
 int n = (value1.location.Y < value2.location.Y) ? value1.location.Y : value2.location.Y
 int n8 = (n6 > n5)? n6 : n5
 int n7 = (n4 > n3)? n4 : n3
 return new Rectangle(new Vector2(n2,n), n8 - n2, n7 - n)
}

boolean getFill(){
return _fill
}
VertexPositionColour[] getDrawData(){
VertexPositionColour[] vpcPoints = new VertexPositionColour[4]
vpcPoints[0] = new VertexPositionColour(location, fillColour)
vpcPoints[1] = new VertexPositionColour(new Vector2(getRight(), location.Y), fillColour)
vpcPoints[2] = new VertexPositionColour(new Vector2(getRight(), getBottom()), fillColour)
vpcPoints[3] = new VertexPositionColour(new Vector2(location.X, getBottom()), fillColour)

return vpcPoints

}
int getColour(){ return fillColour }
}




// ################################################################################################################################################
// ###################    Main entry point  #######################################################################################################
// ################################################################################################################################################
theGame.main()
println()
class theGame{

static void main(){
  println("Welcome to Sam's simple graphics engine.")
  println("You will need to focus on the small windows titled \"Java\" for your input to work")
  
  World world = new World()
  world.Initialize()
 }
}

class World extends JFrame implements KeyListener{

Rectangle WorldRect
Player player
World(){ 
WorldRect = new Rectangle(new Vector2(0,256), 256, 256)
  this.addKeyListener(this);
	
  this.setSize(100,100);
  this.setVisible(true)
}

void Initialize(){
Engine eng = new Engine()
  eng.InitializeEngine()
  def f = 1;
  def currElapsed
  long lastTimeElapsed = 0
  def counter = 0
  def fps = 0
  long lastCapElapsed = 0
  long gameTime
  
  Ball ball = new Ball(this)
  player = new Player(this)
  int startTime = System.currentTimeMillis()
  
  while(true){
  gameTime = System.currentTimeMillis() - startTime
  if (((gameTime) - lastTimeElapsed) >= 1000){
   lastTimeElapsed = gameTime
   fps = counter
   counter = 0;
  }
  
  printf("\r%d fps.", fps) 
  
  
  // Set a draw cap
  if (((gameTime) - lastCapElapsed) >= 100){
  eng.setAllPixelsToClear()
  ball.Update(player,gameTime)
  VertexPositionColour[] vpc = player.getDrawData()
  eng.DrawPrimitiveColouredToBuffer(vpc)
  eng.Render()
  lastCapElapsed = gameTime
  counter++
  }
  
  
  }
}
 
 public void keyPressed(KeyEvent e) {

  if(e.getKeyCode() == KeyEvent.VK_LEFT){
   KeyLeftFunction()
  }
  
  if(e.getKeyCode() == KeyEvent.VK_RIGHT){
   KeyRightFunction()
  }
  
  if(e.getKeyCode() == KeyEvent.VK_DOWN){
   KeyDownFunction()
  }
  
  if(e.getKeyCode() == KeyEvent.VK_UP)
  {
   KeyUpFunction()
  }
 }

  public void keyReleased(KeyEvent e) {
   // Do stuff.
  }
	
  public void keyTyped(KeyEvent e) {
   // Do stuff.
  }

  void KeyLeftFunction()
  {
   player.Move(new Vector2(player.location.X - 2, player.location.Y))
  }
  
  void KeyRightFunction()
  {
   player.Move(new Vector2(player.location.X + 2, player.location.Y))
  }
  
  void KeyUpFunction()
  {}
  
  void KeyDownFunction()
  {}
}




/// PLAYER DATA
public class Player extends Rectangle{
Rectangle world

Player(World World){ 
super(new Vector2(20,30), 10, 1)
world = World.WorldRect
}

void Move(Vector2 to){
// Move but keep to our world contraints
if(world.Contains(to)){
location = to
}
}

}

public class Ball extends Circle{
Rectangle world
Vector2 direction

Ball(World World){
super(5, 128, 128)
 world = World.WorldRect
 direction = new Vector2(900, 900)
}

void Move(Vector2 to){ 
  location = to
}

void Update(Rectangle player, long gameTime){

 // Here we will make collision detections between the world and the player.
 
 // Check bottom center of the ball
 // Perhaps we could improve to perform a pixel collision check?
 int midX = location.X + radius
 int lmidY = getBottom()

 if(((midX >= player.getLeft()) && (midX <= player.getRight())) && (player.getBottom() >= lmidY)){
 direction.Y += 1000
 }   
 
 
 if ((256) <= location.Y){
 // We hit the top of the game.
  direction.Y -= 1000
 }

 if ((world.location.X + world.Width) <= (location.X + getWidth())){
  // We hit the right hand side of the game.
  direction.X -= 1000
 }

 if (1 >= (location.Y - getHeight())){
  // We hit the bottom of the game
  // Perhaps we could create some sort of counter to keep track of hits here?
  direction.Y += 1000
 }

 if(1 >= location.X){
  // We hit the left hand side of the game.
  direction.X += 1000
 }
  def X = location.X..direction.X
  def Y = location.Y..direction.Y
  Move(new Vector2(X[2],Y[2]))
  Vector2 center = getCenter()
  xCenter = center.X
  yCenter = center.Y
  drawCircleToBuffer(Scribbler.WHITE)
  return
 
}

}



