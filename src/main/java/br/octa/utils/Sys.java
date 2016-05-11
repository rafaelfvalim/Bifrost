package br.octa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.UnknownHostException;

public class Sys {
    public final static String getHDSerial() throws IOException {
        String os = System.getProperty("os.name");
        try {
            if (os.startsWith("Windows")) {
                return getHDSerialWindows("C");
            } else if (os.startsWith("Linux")) {
                return getHDSerialLinux();
            } else {
                throw new IOException("unknown operating system: " + os);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException(ex.getMessage());
        }
    }
    public final static String getCPUS() throws IOException {
        String os = System.getProperty("os.name");
        try {
            if (os.startsWith("Windows")) {
                return getCPUSerialWindows();
            } else if (os.startsWith("Linux")) {
                return getCPUSerialLinux();
            } else {
                throw new IOException("unknown operating system: " + os);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException(ex.getMessage());
        }
    }
    public final static String getMS() throws IOException {
        String os = System.getProperty("os.name");
        try {
            if (os.startsWith("Windows")) {
                return getMotherboardSerialWindows();
            } else if (os.startsWith("Linux")) {
                return getMotherboardSerialLinux();
            } else {
                throw new IOException("unknown operating system: " + os);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException(ex.getMessage());
        }
    }
    // Implementacoes
    /*
     * Captura serial de placa mae no WINDOWS, atraves da execucao de script
     * visual basic
     */
    public static String getMotherboardSerialWindows() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }
    /*
     * Captura serial de placa mae em sistemas LINUX, atraves da execucao de
     * comandos em shell.
     */
    public static String getMotherboardSerialLinux() {
        String result = "";
        try {
            String[] args = {"bash", "-c", "lshw -class bus | grep serial"};
            Process p = Runtime.getRuntime().exec(args);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
        }
        if (result.trim().length() < 1 || result == null) {
            result = "NO_DISK_ID";
        }
        return filtraString(result, "serial: ");
    }
    /*
     * Captura serial de HD no WINDOWS, atraves da execucao de script visual
     * basic
     */
    public static String getHDSerialWindows(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
        }
        if (result.trim().length() < 1 || result == null) {
            result = "NO_DISK_ID";
        }
        return result.trim();
    }
    /*
     * Captura serial de HD em sistemas Linux, atraves da execucao de comandos
     * em shell.
     */
    public static String getHDSerialLinux() {
        String result = "";
        try {
            String[] args = {"bash", "-c", "lshw -class disk | grep serial"};
            Process p = Runtime.getRuntime().exec(args);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
        }
        if (result.trim().length() < 1 || result == null) {
            result = "NO_DISK_ID";
        }
        return filtraString(result, "serial: ");
    }
    /*
     * Captura serial da CPU no WINDOWS, atraves da execucao de script visual
     * basic
     */
    public static String getCPUSerialWindows() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "On Error Resume Next \r\n\r\n"
                    + "strComputer = \".\"  \r\n"
                    + "Set objWMIService = GetObject(\"winmgmts:\" _ \r\n"
                    + "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\") \r\n"
                    + "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\")  \r\n "
                    + "For Each objItem in colItems\r\n "
                    + "    Wscript.Echo objItem.ProcessorId  \r\n "
                    + "    exit for  ' do the first cpu only! \r\n"
                    + "Next                    ";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
        }
        if (result.trim().length() < 1 || result == null) {
            result = "NO_CPU_ID";
        }
        return result.trim();
    }
    /*
     * Captura serial de CPU em sistemas Linux, atraves da execucao de comandos
     * em shell.
     */
    public static String getCPUSerialLinux() {
        String result = "";
        try {
            String[] args = {"bash", "-c", "lshw -class processor | grep serial"};
            Process p = Runtime.getRuntime().exec(args);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
        }
        if (result.trim().length() < 1 || result == null) {
            result = "NO_DISK_ID";
        }
        return filtraString(result, "serial: ");
    }
    public static String filtraString(String nome, String delimitador) {
        return nome.split(delimitador)[1];
    }
    public static String retornaMacAddress() throws SocketException, UnknownHostException, java.net.UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface netInter = NetworkInterface.getByInetAddress(localHost);
        byte[] macAddressBytes = netInter.getHardwareAddress();
        String macAddress = String.format(
                "%1$02x-%2$02x-%3$02x-%4$02x-%5$02x-%6$02x",
                macAddressBytes[0], macAddressBytes[1], macAddressBytes[2],
                macAddressBytes[3], macAddressBytes[4], macAddressBytes[5]).toUpperCase();
        return macAddress;
    }
    public static void main(String[] args) {
        try {
            System.out.println("Serial do HD: " + getHDSerial());
            System.out.println("Serial da CPU: " + getCPUS());
            System.out.println("Serial da Placa Mae: " + getMS());
            System.out.println("Serial do MAC: " + retornaMacAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
