/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tutm
 */
import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data; //our data will be a simple message.
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;

    //Block Constructor.
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        
        this.hash = calculateHash(); //Making sure we do this after we set the other values
        
    }

    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash
                + Integer.toString(nonce)
                + Long.toString(timeStamp)
                + data
        );
        return calculatedhash;
    }
    
    public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
                //System.out.println("target: " + target);
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
                        //System.out.println(hash);
		}
		System.out.println("Block Mined!!! : " + hash);
	}
    
    
    // test function
    public static void main(String[] args) {
        Block block = new Block("Test data", "0");
        System.out.println("cal Hash: " + block.calculateHash());
        
        block.mineBlock(5);
        //2a2e88cc3629c16153689095321fddaaf42126a4a86fdc6a79d31043e61cf172
        // 05533fe44e8c7aafeac313c269e9d8bc9f3c93a80fff01a945460cbae74721ac
    }
}
