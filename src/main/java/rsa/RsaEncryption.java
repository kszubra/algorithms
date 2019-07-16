package rsa;

import java.math.BigInteger;

public class RsaEncryption {

    /****************** E N C R Y P T I O N   P A R A M E T R E S ******************/

    private BigInteger a;
    private BigInteger b;

    private BigInteger n;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger reversedMod;
    private BigInteger privateKey;

    /****************** C A L C U L A T I N G   F U N C T I O N S ******************/

    private BigInteger countE(BigInteger phi){ // finds e that GCD(e, phi) = 0

        BigInteger tempE = new BigInteger("2");

        while (phi.gcd(tempE).compareTo(BigInteger.ONE) != 0){
            tempE = tempE.add(BigInteger.ONE);
        };

        return tempE;
    }


    private BigInteger reversedModulo (BigInteger e, BigInteger phi){ //

        BigInteger tempU = new BigInteger("1");
        BigInteger tempW = e.multiply(BigInteger.ONE);
        BigInteger tempX = new BigInteger("0");
        BigInteger tempZ = phi.multiply(BigInteger.ONE);
        BigInteger tempQ = new BigInteger("0");

        while(tempW.compareTo(BigInteger.ZERO )!= 0){

            if(tempW.compareTo(tempZ) < 0 ){

                tempQ = tempU.pow(1);
                tempU = tempX.pow(1);
                tempX = tempQ.pow(1);
                tempQ = tempW.pow(1);
                tempW = tempZ.pow(1);
                tempZ = tempQ.pow(1);
            }

            tempQ = tempW.divide(tempZ);
            tempU = tempU.subtract(tempQ.multiply(tempX));
            tempW = tempW.subtract(tempQ.multiply(tempZ));
        }

        if (tempZ.compareTo(BigInteger.ONE) == 0){

            if(tempX.compareTo(BigInteger.ZERO) < 0){tempX = tempX.add(phi);}

            return tempX;
        } else return BigInteger.ZERO;


    }

    /****************** D E F A U L T   V A L U E S   C O N S T R U C T O R ******************/

    public RsaEncryption() {

        this.a = new BigInteger("19787054008813645614053424865406953623699822727612322068593111095276026690452844392615536482172839362180519675696608994925996803425476196110689942647910477907496777435592127613528366148619887952072921473269155502680259887545736555005096901574093312395854297370250602447467939398675796960888502797286414022682553311646957613603823606495554972504206729450782037253137857793045598042076377");
        this.b = new BigInteger("6505799808686839196232095541321881806634099384461919925537363379257488594426729676435347894612555291564466377918177585966771817020700297030053306962208595194342938589491445570151660591166195132751415929014500905267748579656200788860031138723893127314452549813603623942219401631069319124092677522781961068359179619625653151969453269932847356514150435141126436625555106724886545450154339");
        //this.a = new BigInteger("17");
        //this.b = new BigInteger("31");

        this.n = a.multiply(b);
        this.phi = (a.subtract(BigInteger.ONE)).multiply((b.subtract(BigInteger.ONE)));
        this.e = countE(phi);
        this.reversedMod = reversedModulo(e, phi);
        this.privateKey = reversedMod;
    }

    /****************** I N P U T   P R I M E   N U M B E R S   C O N S T R U C T O R ******************/

    public RsaEncryption(BigInteger a, BigInteger b) {

        this.a = new BigInteger(a.toString());
        this.b = new BigInteger(b.toString());
        this.n = a.multiply(b);
        this.phi = (a.subtract(BigInteger.ONE)).multiply((b.subtract(BigInteger.ONE)));
        this.e = countE(phi);
        this.reversedMod = reversedModulo(e, phi);
        this.privateKey = reversedMod;
    }

    /****************** F U N C T I O N A L   M E T H O D S ******************/

    private BigInteger encryptChar(long i, BigInteger e, BigInteger n){

        BigInteger encryptedChar = (BigInteger.valueOf(i)).modPow(e,n);
        return encryptedChar;
    }

    private BigInteger decryptChar(BigInteger encryptedChar, BigInteger privateKey, BigInteger n ){

        BigInteger decryptedChar = encryptedChar.modPow(privateKey, n);
        return decryptedChar;
    }

    public void runTest(){

        int table[] = new int[1000];

        for(int i = 0; i<1000; i++){

            table[i] = i;

            BigInteger encryptedChar = encryptChar(i, e, n);   // (BigInteger.valueOf(i)).modPow(e,n); // encrypted = to_encrypt^e mod n
            BigInteger decryptedChar = decryptChar(encryptedChar, privateKey, n);// encryptedChar.modPow(privateKey, n); // to_encrypt = encrypted^public_key mode n
            System.out.println("For int = " + i + " encrypted number is: " + table[i] + " that is: " + (char)table[i] +  " after encryption: " + decryptedChar + " that means: "  + (char)decryptedChar.intValue() );

        }
    }


    public long[] encryptText(String stringToEncrypt){

        long charsToEncrypt[] = new long[stringToEncrypt.length()];
        long encryptedChars[] = new long[stringToEncrypt.length()];


        for(int i = 0; i < stringToEncrypt.length(); i++){ //string to encryped turned into char table

            charsToEncrypt[i] = (long)stringToEncrypt.charAt(i);
            System.out.println("Char to encryt is: " + charsToEncrypt[i] + " that means: " + (char)charsToEncrypt[i]);
            encryptedChars[i] = encryptChar(charsToEncrypt[i],e,n).intValue();
            System.out.println("Encrypted char is: " + encryptedChars[i] + " that means: " + (char)encryptedChars[i]);
        }
        return encryptedChars;
    }

    public String decryptText(long[] charsToDecrypt){

        long decryptedChars[] = new long [charsToDecrypt.length];
        String decryptedString = new String();

        for(int i = 0; i < charsToDecrypt.length; i++){ // char table decrypted

            System.out.println("Char do decrypt is: " + charsToDecrypt[i] + " that means: " + (char)charsToDecrypt[i]);
            decryptedChars[i] = decryptChar(BigInteger.valueOf(charsToDecrypt[i]), privateKey, n).intValue();
            System.out.println("Decrypted char is: " + decryptedChars[i] + " that means: " + (char)decryptedChars[i]);
            decryptedString += (char)decryptedChars[i];
        } return decryptedString;
    }

}
