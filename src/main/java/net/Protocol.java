package net;

/**
 * Created by KVN on 29/05/2016.
 */
public class Protocol {
    public static final int port = 9998;

    public static final byte CONNECT_REQUEST = (byte) 0x10;

    public static final byte GET_INTERFACE_LIST = (byte) 0x11;
    public static final byte SERVER_SENDING_ITF_LIST = (byte) 0x12;

    public static final byte OK = (byte) 0x90;
    public static final byte NOK = (byte) 0x91;
    public static final byte GENERAL_ERROR = (byte) 0x92;

    public static final byte TEST = (byte) 0x99;


}
