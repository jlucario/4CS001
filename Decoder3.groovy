String decodeMessage(String codedMsg) {
    def letters = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.;:&!,"
    String strKey = codedMsg.substring(0,20)
    String msg = codedMsg.substring(20)
    def keyArray  = new ArrayList(10)
    int index = 0
    int arrayIndex = 0
    while(index<20) {
        int endIndex = index + 2
        keyArray[arrayIndex] = strKey.substring(index,endIndex)
        endIndex = 0
        index += 2
        arrayIndex++
    }
    
    int arraySize = msg.length()/2
    def msgArray = new ArrayList(arraySize)
    int sindex = 0;
    int sarrayIndex = 0
    while(sindex<msg.length()) {
        int endIndex = sindex + 2
        msgArray[sarrayIndex] = msg.substring(sindex,endIndex);
        endIndex = 0
        sindex += 2
        sarrayIndex++
    }
    
    String ucMessage = ""
    int keyIndex = 0
    for(int i=0; i<arraySize; i++) {
        int msgValue =( Integer.parseInt(msgArray[i]) -  Integer.parseInt(keyArray[keyIndex]))
        keyIndex++
        ucMessage = ucMessage + letters.charAt(msgValue)
        if(keyIndex >= 10 ) {
            keyIndex = 0;
        }
    } 
    return ucMessage;    
}

def codedMsg = "19112121181812222517352936233023252244323133303525"
decodeMessage(codedMsg)
