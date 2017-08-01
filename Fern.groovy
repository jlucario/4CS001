import buckley.*
def x = 0.5
def y = 0.5
for(i in 1..10000){
if(i%1000==0){
Scribbler.render()
}
def r = Math.random()
def newX
def newY
if(r<=0.01) {
newX = 0
newY = y * 0.16
} else {
if(r<=0.86){
newX = (x * 0.85) + (y * 0.04)
newY = (x * -0.04) + (y * 0.85) + 1.6
} else {
if(r<=0.93){
newX = (x * 0.2) + (y * -0.26)
newY = (x * 0.23) + (y * 0.22) + 1.6
} else {
newX = (x * -0.15) + (y * 0.28)
newY = (x * 0.26) + (y * 0.24) + 0.44
}
}
}
x = newX
y = newY
def dx = x*20 + 128
def dy = y*20
Scribbler.setPixel(dx, dy, Scribbler.DARK_OLIVE_GREEN)
}
Scribbler.render()

