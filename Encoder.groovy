int randomInt(int upperLimit){
    return (Math.random() * upperLimit)
}

String generateKey (){
    def letters = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.;:&!,"
    def remaining = letters
    def key = ""
    letters.length().times {
        int index = randomInt(remaining.length());
        def letter = remaining[index];
        remaining = remaining.replace(letter, "");
        key = key + letter
    }
    return key
}

String encodeMessage(String message, String key) {
    def letters = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789,.;:&!,"
    String ucMessage = message;
    String code = key
    for(character in ucMessage){
        def index = letters.indexOf(character)
        if(index >= 0) {
            code = code + key[index]
        }
    }
    return code
}

key = generateKey()
def message = "While I nodded, nearly napping, suddenly there came a tapping,As of some one gently rapping, rapping at my chamber door."
def codedMessage = encodeMessage(message, key)
printf("Coded Message: %s \n", codedMessage)