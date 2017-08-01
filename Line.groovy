import buckley.*
for(i in 0..256) {
Scribbler.setPixel(100, i, Scribbler.CORNFLOWER);
}
Scribbler.render()
for(i in 0..256) {
Scribbler.setPixel(i, 100, Scribbler.PLUM);
}
Scribbler.render()
for(i in 0..256) {
Scribbler.setPixel(i, i, Scribbler.PALE_GREEN);
}
Scribbler.render()
