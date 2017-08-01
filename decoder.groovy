public String decode(String code) {
    def letters = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789,.;:&!";
    def key = code.substring(0,letters.length())
    def codedMsg = code.substring(letters.length());
    def decodedMsg = "";
    for(character in codedMsg) {
        def index = key.indexOf(character);
        if(index >= 0) {
            decodedMsg = decodedMsg + letters.charAt(index);
        }
        
    }
    
    return decodedMsg;
}

def code = ".BWTpzxiDY8X3KjN05Z6kHsI!1VFa9r2nQyt cEbfRAveJwmdSg&OP,;GLC4MU7hqlou:IytE2.Y.fRrr2rh.f2FeE&.fFAAtfQh.Jmrr2fE&.wy2e2.9Fb2.F.wFAAtfQhBJ.Rn.JRb2.Rf2.Q2fwE&.eFAAtfQh.eFAAtfQ.Fw.b&.9yFba2e.rRReq"
printf("Decoded Message: %s\n",decode(code))