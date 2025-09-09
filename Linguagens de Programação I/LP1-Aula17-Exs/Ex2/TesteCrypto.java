import java.io.File;
public class TesteCrypto{  
   public static void main(String[] args) throws Exception{
      // Create and Open teste.txt
      ReadTextFile application = new ReadTextFile();
      application.openFile();
      String sMsgClara = application.readFile();
      application.closeFile();
      // Create and Open texto_cifrada.txt e texto_decifrada.txt
      CreateTextFile texto_cifrada = new CreateTextFile();
      texto_cifrada.openFile("texto_cifrada.txt");
      CreateTextFile texto_decifrada = new CreateTextFile();
      texto_decifrada.openFile("texto_decifrada.txt");

      String   sMsgCifrada;
      String   sMsgDecifrada;
      byte[]   bMsgClara;
      byte[]   bMsgCifrada;
      byte[]   bMsgDecifrada;
      // Instancia objeto da classe Impressora
      Impressora prn = new Impressora();
      // Imprime marcador de bloco
      System.out.println("---------------------------------------------------------------");
      // Converte o texto String dado no equivalente byte[]
      bMsgClara = sMsgClara.getBytes("ISO-8859-1");
      /*
       * Criptografia Dummy ------------------------------------------------------------
       */
      // Imprime Texto
      System.out.println(">>> Cifrando com o algoritmo Dummy...");
      System.out.println("");
      // Instancia um objeto da classe CryptoDummy
      CryptoDummy cdummy = new CryptoDummy();
      // Gera a chave criptografica Dummy simetrica e nome do arquivo onde sera armazenada
      cdummy.geraChave(new File ("chave.dummy"));
      // Gera a cifra Dummy da mensagem dada, com a chave Dummy simetrica dada
      cdummy.geraCifra(bMsgClara, new File ("chave.dummy"));
      // Recebe o texto cifrado
      bMsgCifrada = cdummy.getTextoCifrado();
      // Converte o texto byte[] no equivalente String
      sMsgCifrada = (new String (bMsgCifrada, "ISO-8859-1"));
      texto_cifrada.addText("Mensagem Cifrada do Dummy:"+sMsgCifrada);
      // Imprime texto
      System.out.println(">>> Decifrando com o algoritmo Dummy...");
      System.out.println("");
      // Gera a decifra Dummy da mensagem dada, segundo a chave Dummy simetrica gerada
      cdummy.geraDecifra(bMsgCifrada, new File ("chave.dummy"));
      // recebe o texto decifrado
      bMsgDecifrada = cdummy.getTextoDecifrado();
      // Converte o texto byte[] no equivalente String
      sMsgDecifrada = (new String (bMsgDecifrada, "ISO-8859-1"));
      texto_decifrada.addText("Mensagem Decifrada do Dummy:"+sMsgDecifrada);
      /*
       * Criptografia AES --------------------------------------------------------------
       */
      // Imprime Texto
      System.out.println(">>> Cifrando com o algoritmo AES...");
      System.out.println("");
      // Instancia um objeto da classe CryptoAES
      CryptoAES   caes = new CryptoAES();
      // Gera a Chave criptografica AES simetrica e o nome do arquivo onde será armazenada
      caes.geraChave(new File ("chave.simetrica"));
      // Gera a cifra AES da mensagem dada, com a chave simetrica dada
      caes.geraCifra(bMsgClara, new File ("chave.simetrica"));
      // Recebe o texto cifrado
      bMsgCifrada = caes.getTextoCifrado();
      // Converte o texto byte[] no equivalente String
      sMsgCifrada = (new String (bMsgCifrada, "ISO-8859-1"));
      texto_cifrada.addText("Mensagem Cifrada do AES:"+sMsgCifrada);
      // Imprime texto
      System.out.println(">>> Decifrando com o algoritmo AES...");
      System.out.println("");
      // Gera a decifra AES da mensagem dada, segundo a chave simetrica gerada
      caes.geraDecifra(bMsgCifrada, new File ("chave.simetrica"));
      // recebe o texto decifrado
      bMsgDecifrada = caes.getTextoDecifrado();
      // Converte o texto byte[] no equivalente String
      sMsgDecifrada = (new String (bMsgDecifrada, "ISO-8859-1"));
      texto_decifrada.addText("Mensagem Decifrada do AES:"+sMsgDecifrada);
      /*
       * Criptografia RSA --------------------------------------------------------------
       */
      // Imprime Texto
      System.out.println(">>> Cifrando com o algoritmo RSA...");
      System.out.println("");
      // Instancia um objeto da classe CryptoRSA
      CryptoRSA   crsa = new CryptoRSA();
      // Gera as Chaves criptografica RSA publica e privada e os arquivos onde armazenar
      crsa.geraParDeChaves(new File ("chave.publica"), new File ("chave.privada"));
      // Gera a cifra RSA da mensagem dada, segundo a chave publica gerada
      crsa.geraCifra(bMsgClara, new File ("chave.publica"));
      // Recebe o texto cifrado
      bMsgCifrada = crsa.getTextoCifrado();
      // Converte o texto byte[] no equivalente String
      sMsgCifrada = (new String (bMsgCifrada, "ISO-8859-1"));
      texto_cifrada.addText("Mensagem Cifrada do RSA:"+sMsgCifrada);
      // Imprime texto
      System.out.println(">>> Decifrando com o algoritmo RSA...");
      System.out.println("");
      // Gera a decifra RSA da mensagem dada, segundo a chave privada gerada
      crsa.geraDecifra(bMsgCifrada, new File ("chave.privada"));
      // recebe o texto decifrado
      bMsgDecifrada = crsa.getTextoDecifrado();
      // Converte o texto byte[] no equivalente String
      sMsgDecifrada = (new String (bMsgDecifrada, "ISO-8859-1"));
      texto_decifrada.addText("Mensagem Decifrada do RSA:"+sMsgDecifrada);
      // Close files
      texto_cifrada.closeFile();
      texto_decifrada.closeFile();
   }
}

