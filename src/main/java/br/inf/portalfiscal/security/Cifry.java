package br.inf.portalfiscal.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Cifry {
	private String textoNormal; // Texto a ser criptografado.
	private String textoCifrado;
	private final String ALGORITIMO = "AES";
	private Hasher hash;

	public void criptografa(String senha, String texto) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		hash = new Hasher();
		byte[] chave = hash.getHash(senha);
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(chave, ALGORITIMO);
			Cipher cipher = Cipher.getInstance(ALGORITIMO);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(texto.getBytes());
			this.textoCifrado = new BASE64Encoder().encode(encrypted);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();

		}
	}

	public String getTextoCifrado() {
		return textoCifrado;
	}

	public void decriptografa(String senha, String texto)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] textoDecifrado = null;
		hash = new Hasher();
		try {
			byte[] chave = hash.getHash(senha);
			SecretKeySpec skeySpec = new SecretKeySpec(chave, ALGORITIMO);
			byte[] decoded = new BASE64Decoder().decodeBuffer(texto);
			Cipher cipher = Cipher.getInstance(ALGORITIMO);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			textoDecifrado = cipher.doFinal(decoded);
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			// throw new EncryptorException("Erro ao descriptografar
			// informações.\n" + e.getMessage());
			e.printStackTrace();

		}
		this.textoNormal = new String(textoDecifrado);
	}

	public String getTextoDecifrado() {
		return textoNormal;
	}
}