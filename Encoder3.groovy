int randomInt(){
    return 10 + (Math.random() * (20))
}

int[] generateKey() {
    def keyArray  = new ArrayList(10)
    for(int i = 0; i<10; i++) {
        keyArray[i] = randomInt()
        printf("%s \n", keyArray[i])
    }
    return keyArray
}

String encode(String message) {
    def letters = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.;:&!,"
    String ucMessage = message
    def keyArray =  generateKey()
    
    def codedMsg = ""
    for(int i = 0; i<10; i++) {
        codedMsg = codedMsg + keyArray.getAt(i)
    }
    def keyIndex = 0;
    for(character in ucMessage) {
       def messageIndex = letters.indexOf(character)
       codedMsg = codedMsg + (messageIndex + keyArray.getAt(keyIndex))
       keyIndex++
       if(keyIndex >= 10) {
           keyIndex = 0
       }
    }
    return codedMsg
}

String message = "PROBLEM SOLVING"
printf("Encoded Message: %s \n",encode(message))