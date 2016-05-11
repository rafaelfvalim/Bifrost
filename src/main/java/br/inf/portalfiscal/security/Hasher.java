package br.inf.portalfiscal.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher
{
    private final String ALGORITIMO = "MD5";
    public byte[] getHash(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITIMO);
        messageDigest.update(senha.getBytes());
        byte[] senhaByte = messageDigest.digest();
        return senhaByte;
    }
}