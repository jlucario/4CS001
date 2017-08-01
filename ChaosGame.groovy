import javax.swing.*
import buckley.*

def inputAX = JOptionPane.showInputDialog("Enter your chosen X co-ordinate for point A.")
def inputAY = JOptionPane.showInputDialog("Enter your chosen Y co-ordinate for point A.")

def inputBX = JOptionPane.showInputDialog("Enter your chosen X co-ordinate for point B.")
def inputBY = JOptionPane.showInputDialog("Enter your chosen Y co-ordinate for point B.")

def inputCX = JOptionPane.showInputDialog("Enter your chosen X co-ordinate for point C.")
def inputCY = JOptionPane.showInputDialog("Enter your chosen Y co-ordinate for point C.")

def inputOriginX = JOptionPane.showInputDialog("Enter your chosen X co-ordinate for the origin X.")
def inputOriginY = JOptionPane.showInputDialog("Enter your chosen Y co-ordinate for the origin X.")

int AX = Integer.parseInt(inputAX)
int AY = Integer.parseInt(inputAY)
int BX = Integer.parseInt(inputBX)
int BY = Integer.parseInt(inputBY)
int CX = Integer.parseInt(inputCX)
int CY = Integer.parseInt(inputCY)
int OriginX = Integer.parseInt(inputOriginX)
int OriginY = Integer.parseInt(inputOriginY)

Scribbler.setPixel(AX,AY,Scribbler.RED);
Scribbler.setPixel(BX,BY,Scribbler.WHITE);
Scribbler.setPixel(CX,CY,Scribbler.BLUE);
Scribbler.setPixel(OriginX,OriginY,Scribbler.PALE_GREEN);
Scribbler.render()

def randNumber = Math.random()
def Xmid = 0
def Ymid = 0
if(randNumber <= 0.33){
    Xmid = ((AX + OriginX)/2)
    Ymid = ((AY + OriginY)/2)
    }else{
    if(randNumber <= 0.66){
        Xmid = ((BX + OriginX)/2)
        Ymid = ((BY + OriginY)/2)
        }else{
        if(randNumber <=0.99){
            Xmid = ((CX + OriginX)/2)
            Ymid = ((CY + OriginY)/2)
            }
        }
    }
       
def X = Xmid
def Y = Ymid
Scribbler.setPixel(X, Y, Scribbler.ORANGE)
Scribbler.render()
            
def nextX = 0
def nextY = 0
def newX = X
def newY = Y

for(i in 1..10000){
        if(i%1000==0){
    }
    
def randNumber2 = Math.random()

if(randNumber2 <= 0.33){
    nextX = ((AX+newX)/2)
    nextY = ((AY+newY)/2)
    }else{
    if(randNumber2 <= 0.66){
        nextX = ((BX+newX)/2)
        nextY = ((BY+newY)/2)
        }else{
        if(randNumber2 <=0.99){
            nextX = ((CX+newX)/2)
            nextY = ((CY+newY)/2)
            }
         }
     }
            
newX = nextX
newY = nextY

Scribbler.setPixel(nextX, nextY, Scribbler.ORANGE)
}
Scribbler.render()
