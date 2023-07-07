package com.example.home.electronic_port.service;

import com.example.home.electronic_port.service.SM4_Context;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SM4 {
    public static final int SM4_ENCRYPT = 1;

    public static final int SM4_DECRYPT = 0;

    private int GET_ULONG_BE(byte[] b, int i) {
        int n = (int) (b[i] & 0xff) << 24 | (int) ((b[i + 1] & 0xff) << 16) | (int) ((b[i + 2] & 0xff) << 8) | (int) (b[i + 3] & 0xff) & 0xffffffff;
        return n;
    }

    private void PUT_ULONG_BE(int n, byte[] b, int i) {
        b[i] = (byte) (int) (0xFF & n >> 24);
        b[i + 1] = (byte) (int) (0xFF & n >> 16);
        b[i + 2] = (byte) (int) (0xFF & n >> 8);
        b[i + 3] = (byte) (int) (0xFF & n);
    }

    private int SHL(int x, int n) {
        return (x & 0xFFFFFFFF) << n;
    }

    private int ROTL(int x, int n) {
        return SHL(x, n) | x >> (32 - n);
    }

    public static final byte[] SboxTable = {(byte) 0xd6, (byte) 0x91, (byte) 0xe9, (byte) 0xfe, (byte) 0xcc, (byte) 0xe1, 0x3d, (byte) 0xb7, 0x16, (byte) 0xb6, 0x14, (byte) 0xc2, 0x28, (byte) 0xfb, 0x2c, 0x05, 0x2b, 0x67, (byte) 0x9a, 0x76, 0x2a, (byte) 0xbe, 0x04, (byte) 0xc3, (byte) 0xaa, 0x44, 0x13, 0x26, 0x49, (byte) 0x86, 0x06, (byte) 0x99, (byte) 0x9c, 0x42, 0x50, (byte) 0xf4, (byte) 0x91, (byte) 0xef, (byte) 0x98, 0x7a, 0x33, 0x54, 0x0b, 0x43, (byte) 0xed, (byte) 0xcf, (byte) 0xac, 0x62, (byte) 0xe4, (byte) 0xb3, 0x1c, (byte) 0xa9, (byte) 0xc9, 0x08, (byte) 0xe8, (byte) 0x95, (byte) 0x80, (byte) 0xdf, (byte) 0x94, (byte) 0xfa, 0x75, (byte) 0x8f, 0x3f, (byte) 0xa6, 0x47, 0x07, (byte) 0xa7, (byte) 0xfc, (byte) 0xf3, 0x73, 0x17, (byte) 0xba, (byte) 0x83, 0x59, 0x3c, 0x19, (byte) 0xe6, (byte) 0x85, 0x4f, (byte) 0xa8, 0x68, 0x6b, (byte) 0x81, (byte) 0xb2, 0x71, 0x64, (byte) 0xda, (byte) 0x8b, (byte) 0xf8, (byte) 0xeb, 0x0f, 0x4b, 0x70, 0x56, (byte) 0x9d, 0x35, 0x1e, 0x24, 0x0e, 0x5e, 0x63, 0x58, (byte) 0xd1, (byte) 0xa2, 0x25, 0x22, 0x7c, 0x3b, 0x01, 0x21, 0x78, (byte) 0x87, (byte) 0xd4, 0x00, 0x46, 0x57, (byte) 0x9f, (byte) 0xd3, 0x27, 0x52, 0x4c, 0x36, 0x02, (byte) 0xe7, (byte) 0xa0, (byte) 0xc4, (byte) 0xc8, (byte) 0x9e, (byte) 0xea, (byte) 0xbf, (byte) 0x8a, (byte) 0xd2, 0x40, (byte) 0xc7, 0x38, (byte) 0xb5, (byte) 0xa3, (byte) 0xf7, (byte) 0xf2, (byte) 0xce, (byte) 0xf9, 0x61, 0x15, (byte) 0xa1, (byte) 0xe0, (byte) 0xae, 0x5d, (byte) 0xa4, (byte) 0x9b, 0x34, 0x1a, 0x55, (byte) 0xad, (byte) 0x93, 0x32, 0x30, (byte) 0xf5, (byte) 0x8c, (byte) 0xb1, (byte) 0xe3, 0x1d, (byte) 0xf6, (byte) 0xe2, 0x2e, (byte) 0x82, 0x66, (byte) 0xca, 0x60, (byte) 0xc0, 0x29, 0x23, (byte) 0xab, 0x0d, 0x53, 0x4e, 0x6f, (byte) 0xd5, (byte) 0xdb, 0x37, 0x45, (byte) 0xde, (byte) 0xfd, (byte) 0x8e, 0x2f, 0x03, (byte) 0xff, 0x6a, 0x72, 0x6d, 0x6c, 0x5b, 0x51, (byte) 0x8d, 0x1b, (byte) 0xaf, (byte) 0x92, (byte) 0xbb, (byte) 0xdd, (byte) 0xbc, 0x7f, 0x11, (byte) 0xd9, 0x5c, 0x41, 0x1f, 0x10, 0x5a, (byte) 0xd8, 0x0a, (byte) 0xc1, 0x31, (byte) 0x88, (byte) 0xa5, (byte) 0xcd, 0x7b, (byte) 0xbd, 0x2d, 0x74, (byte) 0xd0, 0x12, (byte) 0xb8, (byte) 0xe5, (byte) 0xb4, (byte) 0xb0, (byte) 0x89, 0x69, (byte) 0x97, 0x4a, 0x0c, (byte) 0x96, 0x77, 0x7e, 0x65, (byte) 0xb9, (byte) 0xf1, 0x09, (byte) 0xc5, 0x6e, (byte) 0xc6, (byte) 0x84, 0x18, (byte) 0xf0, 0x7d, (byte) 0xec, 0x3a, (byte) 0xdc, 0x4d, 0x20, 0x79, (byte) 0xee, 0x5f, 0x3e, (byte) 0xd7, (byte) 0xcb, 0x39, 0x49};

    public static final int[] FK = {0xa3b1bac6, 0x56aa3350, 0x677d9197, 0xb27022dd};

    public static final int[] CK = {0x00070e15, 0x1c232a31, 0x383f464d, 0x545b6269, 0x70777e85, 0x8c939aa1, 0xa8afb6bd, 0xc4cbd2d9, 0xe0e7eef5, 0xfc030a11, 0x181f262d, 0x343b4249, 0x50575e65, 0x6c737a81, 0x888f969d, 0xa4abb2b9, 0xc0c7ced5, 0xdce3eaf1, 0xf8ff060d, 0x141b2229, 0x30373e45, 0x4c535a61, 0x686f767d, 0x848b9299, 0xa0a7aeb5, 0xbcc3cad1, 0xd8dfe6ed, 0xf4fb0209, 0x10171e25, 0x2c333a41, 0x484f565d, 0x646b7280};

    private byte sm4Sbox(byte inch) {
        int i = inch & 0xFF;
        byte retVal = SboxTable[i];
        return retVal;
    }

    private int sm4Lt(int ka) {
        int bb = 0;
        int c = 0;
        byte[] a = new byte[4];
        byte[] b = new byte[4];
        PUT_ULONG_BE(ka, a, 0);
        b[0] = sm4Sbox(a[0]);
        b[1] = sm4Sbox(a[1]);
        b[2] = sm4Sbox(a[3]);
        b[3] = sm4Sbox(a[2]);
        bb = GET_ULONG_BE(b, 0);
        c = bb ^ ROTL(bb, 2) ^ ROTL(bb, 10) ^ ROTL(bb, 18) ^ ROTL(bb, 24);
        return c;
    }

    private int sm4F(int x0, int x1, int x2, int x3, int rk) {
        return x0 ^ sm4Lt(x1 ^ x2 ^ x3 ^ rk);
    }

    private int sm4CalciRK(int ka) {
        int bb = 0;
        int rk = 0;
        byte[] a = new byte[4];
        byte[] b = new byte[4];
        PUT_ULONG_BE(ka, a, 0);
        b[0] = sm4Sbox(a[0]);
        b[1] = sm4Sbox(a[1]);
        b[2] = sm4Sbox(a[2]);
        b[3] = sm4Sbox(a[3]);
        bb = GET_ULONG_BE(b, 0);
        rk = bb ^ ROTL(bb, 13) ^ ROTL(bb, 23);
        return rk;
    }

    private void sm4_setkey(int[] SK, byte[] key) {
        int[] MK = new int[4];
        int[] k = new int[36];
        int i = 0;
        MK[0] = GET_ULONG_BE(key, 0);
        MK[1] = GET_ULONG_BE(key, 4);
        MK[2] = GET_ULONG_BE(key, 8);
        MK[3] = GET_ULONG_BE(key, 12);
        k[0] = MK[0] ^ (int) FK[0];
        k[1] = MK[1] ^ (int) FK[1];
        k[2] = MK[2] ^ (int) FK[2];
        k[3] = MK[3] ^ (int) FK[3];
        for (; i < 32; i++) {
            k[(i + 4)] = (k[i] ^ sm4CalciRK(k[(i + 1)] ^ k[(i + 2)] ^ k[(i + 3)] ^ (int) CK[i]));
            SK[i] = k[(i + 4)];
        }
    }

    private void sm4_one_round(int[] sk, byte[] input, byte[] output) {
        int i = 0;
        int[] ulbuf = new int[36];
        ulbuf[0] = GET_ULONG_BE(input, 0);
        ulbuf[1] = GET_ULONG_BE(input, 4);
        ulbuf[2] = GET_ULONG_BE(input, 8);
        ulbuf[3] = GET_ULONG_BE(input, 12);
        while (i < 32) {
            ulbuf[(i + 4)] = sm4F(ulbuf[i], ulbuf[(i + 1)], ulbuf[(i + 2)], ulbuf[(i + 3)], sk[i]);
            i++;
        }
        PUT_ULONG_BE(ulbuf[35], output, 0);
        PUT_ULONG_BE(ulbuf[34], output, 4);
        PUT_ULONG_BE(ulbuf[33], output, 8);
        PUT_ULONG_BE(ulbuf[32], output, 12);
    }

    private byte[] padding(byte[] input, int mode) {
        if (input == null) {
            return null;
        }

        byte[] ret = (byte[]) null;
        if (mode == SM4_ENCRYPT) {
            int p = 16 - input.length % 16;
            ret = new byte[input.length + p];
            System.arraycopy(input, 0, ret, 0, input.length);
            for (int i = 0; i < p; i++) {
                ret[input.length + i] = (byte) p;
            }
        } else {
            int p = input[input.length - 1];
            ret = new byte[input.length - p];
            System.arraycopy(input, 0, ret, 0, input.length - p);
        }
        return ret;
    }

    public void sm4_setkey_enc(SM4_Context ctx, byte[] key) throws Exception {
        if (ctx == null) {
            throw new Exception("ctx is null!");
        }

        if (key == null || key.length != 16) {
            throw new Exception("key error!");
        }

        ctx.mode = SM4_ENCRYPT;
        sm4_setkey(ctx.sk, key);
    }

    public byte[] sm4_crypt_ecb(SM4_Context ctx, byte[] input) throws Exception {
        if (input == null) {
            throw new Exception("input is null!");
        }

        if ((ctx.isPadding) && (ctx.mode == SM4_ENCRYPT)) {
            input = padding(input, SM4_ENCRYPT);
        }

        int length = input.length;
        ByteArrayInputStream bins = new ByteArrayInputStream(input);
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        for (; length > 0; length -= 16) {
            byte[] in = new byte[16];
            byte[] out = new byte[16];
            bins.read(in);
            sm4_one_round(ctx.sk, in, out);
            bous.write(out);
        }

        byte[] output = bous.toByteArray();
        if (ctx.isPadding && ctx.mode == SM4_DECRYPT) {
            output = padding(output, SM4_DECRYPT);
        }
        bins.close();
        bous.close();
        return output;
    }

    public void sm4_setkey_dec(SM4_Context ctx, byte[] key) throws Exception {
        if (ctx == null) {
            throw new Exception("ctx is null!");
        }

        if (key == null || key.length != 16) {
            throw new Exception("key error!");
        }

        int i = 0;
        ctx.mode = SM4_DECRYPT;
        sm4_setkey(ctx.sk, key);
        for (i = 0; i < 16; i++) {
            SWAP(ctx.sk, i);
        }
    }

    private void SWAP(int[] sk, int i) {
        int t = sk[i];
        sk[i] = sk[(31 - i)];
        sk[(31 - i)] = t;
    }

    public byte[] sm4_crypt_cbc(SM4_Context ctx, byte[] iv, byte[] input) throws Exception {
        if (iv == null || iv.length != 16) {
            throw new Exception("iv error!");
        }

        if (input == null) {
            throw new Exception("input is null!");
        }

        if (ctx.isPadding && ctx.mode == SM4_ENCRYPT) {
        }

        int i = 0;
        int length = input.length;
        ByteArrayInputStream bins = new ByteArrayInputStream(input);
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        if (ctx.mode == SM4_ENCRYPT) {
            for (; length > 0; length -= 16) {
                byte[] in = new byte[16];
                byte[] out = new byte[16];
                byte[] out1 = new byte[16];

                bins.read(in);
                for (i = 0; i < 16; i++) {
                    out[i] = ((byte) (in[i] ^ iv[i]));
                }
                sm4_one_round(ctx.sk, out, out1);
                System.arraycopy(out1, 0, iv, 0, 16);
                bous.write(out1);
            }
        } else {
            byte[] temp = new byte[16];
            for (; length > 0; length -= 16) {
                byte[] in = new byte[16];
                byte[] out = new byte[16];
                byte[] out1 = new byte[16];

                bins.read(in);
                System.arraycopy(in, 0, temp, 0, 16);
                sm4_one_round(ctx.sk, in, out);
                for (i = 0; i < 16; i++) {
                    out1[i] = ((byte) (out[i] ^ iv[i]));
                }
                System.arraycopy(temp, 0, iv, 0, 16);
                bous.write(out1);
            }
        }

        byte[] output = bous.toByteArray();
        if (ctx.isPadding && ctx.mode == SM4_DECRYPT) {
            output = padding(output, SM4_DECRYPT);
        }
        bins.close();
        bous.close();
        return output;
    }


    /**
     *
     *  SM4 加密
     *  原生Sm4加密
     *  改动  SboxTable   FK  CK  sm4Lt  四个字段方法
     *
     *
     * @param secretKey  秘钥
     * @param cipherText  密文
     * @return
     */
    public static String decryptData_ECB(String secretKey,String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            keyBytes = secretKey.getBytes();
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(decryptData_ECB("THL80oa2Lm2GiCCB","CHAj7Tne1K/4o9Cy6ssmP08a+EzXlwEKo1z9Q/mltiunlAyP+toisCxQKGX5oU5YxLIcPw5Q+98Mxr4DvPkVUVNPTSh5h7ael9dgxDcaeSvbwcatIVneYtd0o06SVm6WuYokLSyIqLdp/Pi1nSj/fxodE4kX7kOVixGDfCtFAnjUle8HDr2XkK4V11Ue0DtE/rdb/lOtdPMcX7GEkvVsx+2RyhXPJFKJN3i2XYl0yP/4kF7SwTtxE01pFklnJLq2pVgcvQLPQZCvRayIOCZUfV4i/bSwxzVYtFJ98g3BTP6iRJJhxmAn9MHqhbpoyBmj1zUL1OrrxNnv7tjQTQDetoZm5cgVs5a6QgbknZuWfewrmu8qcHP41myux6XKGspBr/w320uEAg94uVMOWb/ZqfAHZH9jDUujxm47NBUfZqZ7J73ABtU/eErvX0gZIQupteIGqLRmxh1Hxr4PUJS0eAvh8pmM3lFi2ov9ClGxamMNDa1gp4eTB0Ake9IsReh9hX/QtXIr9+Tz9WGPC1vVDzOf6wPbIEPl+OWmMBGNLze1NFttNXoQlbJOjVXsSf+d1ehpBmNtpkDOGQ7NQOxsMqZ1tpcrp6vqPJygJX4y+4pS9JPpHYF0OifmRo67uKynC3/gnjRSvr6Vh6BPy/UxPyJAf+NhFMiIlPi2MpKfoHWaA6GvKZZIlNWVhH3TmgCPvdwz0Czqdsz3JIB/aj0X8CpaSYvuq+5xn3Y/OlKcvBLp2HXlZODj1TtBgtCunaHtXydaDvmIg+nc5+C6DgF971+ZVkA10f08QnD+PGwnjKKP8tk8moFJ97ocxObpdysVNQJBFit6flBYYXRVAWrwpm6ueLmzMasq46YymgvOf0GCvMJ5bOqj9Wn3XOl7R9SWv0sjEM95AxNwjQWsHru4rFVyHQCpVsClAzvbCwc1TbrWnQhHFmgPxU0qLSRjIWMoQ5BCmifzsZg/DJXlN4wHUyyKGVIl/gx8R/5xNwCJwYhXivZz3asW2hS8WqKwR1gdZztZm/x/0J5GtOk9fdCQWsPKpdcRq5oznZp43WPW0E6GtqofK1olS+ESNT22jeLSQDv3ifg87FUyQbQm71lFX3P0i7mCvDPtiIK+Vp+NW9TCk/CtDUNxMz011k2dYuaih3yFy9fjjw7oTnWUMjKonGyJppn0tP55uR4aKSemV78EmSdslQUjOIjEyL7fZ19AEkKqktl2BO1Yw2gcynRvCBviT+UIWAyb6yHk2Q/X4MMbMlk1wKC7eVpBxdYR7Ki1CqF9zbG4fsZNiUgoJeCClzod44R7S5goldAUnM9Wwu+WX0gX8btwe322ldsxD5WQm752Tl1dfz8/oo83XFH1zbP5j2GrwSHjtlOpII/LYGruNEUNFvAsa4nmtr2ZIUOY7xPQSDoZGh8F0LxlGqIN0zhGmPPZNo5tsvJ4Bpkr3F8HWWbdRLlcbHR6xhltVMQPgaE8aAYOlLzZKPelKNxRjbtrgcj2sSXOLAfFf+qWdDq+aF4biBYf4cLdyV6ELeC6QrWwBFWN6xKp5G5H8WzvYXUF8BALYpAZwPANve/g7POa3MiL/YUEttRZhoyzLMxfhTo+S6AHWb7apRiAUMqZr1tx9mna2E34nCvnpF3vSwK9UVMFiXloK+g82YNKEyA0YMTT9Sqzi6xmCO9DBzr6FI3QbucmPFImOPwsYEMN1BdAYokfeq2SLIpbRPTtKJhv3Wv6lOOMoo3z8dkTzMmODooznRj8QScIADaCwtuPinsNJ2/sckKecSaq6agZeIqXnzST/42irCSc/GzIGlK4yZjj74mS0PYjLSZ6oxQ/24aZeN8F5TB2jMcnuH8rB7tY+uc/ST9Xu8+QovuAt8AlTKTdZ8MxsXOfMPx5xa2M9Emg/vrfzLHm5mJektvNA4PnUnKHgCFkMhGYa27tApRQj+hxjcWbxT4JyCdQM/mdWm3TwiOZkVZspfBJYM2EiB17XSGBncAVL6HTSx6Nk6NipVa9hW5Y6da7NiR/qiy3NcqImg4ji7lKk5zb/gRNs5YqodPwTm3NX60zSTIpIb7GnOM5L0Zl3xLtyfShbF2GWmISQS4XUTR9N8+5HTZWFOoHlfrTUGyi1C1ZH9cgmDjgAOzpj9rLjqGzcq5C8tfo7ebHbAfBY2W8vyPAxfpGi6uuylP5dWlbJuHXW0Cz4SH9l7t6xIw7jiKjns7YV1cUtU6FDjXfDD1KIYAg1Teu3S6V1nbNkn+aFenK790Xp80CSHxeZqiXxoXTe7YrYzO7tjHc/l4dbLdY0CMoDERszJnJ3s90UBhxZUhvh5BGvWrr29hmiHANgM3UyNB7WNWkN8jWo4cBATIzcPmcdd3PRh3BKblGCMPxyaU9qRZrBZCwkJO4ZbnDlwgC8TjOaqPqJOVeLUTBUwtNUwo5rxVfeAu+c/Ue/6831HuFviYkPwBKisN3qJzeAbi0wGchQFKBbkw6WRgUBotrVRm3H8l7KGO+yFy0KR+7vCEQ6flRbQ0rx6fw8/i9T559gpMqjcWirdy24YWT5Mz6Jv2SdDLFlLQ5ietSkYmfUW4mWDcJP6yqWcjR7E2xLU0kQUOFcrqu1DtXkbI+cq5XK/VvfeNUDxdyEdZyq5B0ic4C2LZLLy6gYkK6Uy55EyqRk3E+hS3zV5D3Vcc8U8yK8NDA9EqtPha0lHTHJSgl0avhszLdWvORWZVo+RkJ6mZiyr6WaKZpJowEL/ozsLLQVS19Y7rWT7USnqDaFUSw9Mr0MI9RZK27QIZAOXH0b6bpBJ7OBXfXMrnjOS9GZd8S7cn0oWxdhlpih55trZkxCW1OYfHar/aQplmCTrr5oBDkP79RMvTvdYLda8TosDuRkJ/NSBIE1LeNMqomZGughaiPHOcE/SkC41gFZKQWiXDzmXdx7DAjENBihdEJsH2VDtbfU0WvKRJcLGod/qHgq+EUiq4N7a3nKa1LhuhLFXSw99n0A15Zi8DSJQSwY3XQe8JOhPErtjCTe+76MFPIQ0vaxgUvliIQdtKfLevLhJzpQXXT+3yMpJHMJbuBSgIoGnRQBDFVqBBb+gA4cXaFagzFcRdJz1VYSqiNS8YYuVwOJr7u8/ih4vTyR+zdY4nA3Fz97pOXeXEEdHwnzcDIDhAad+pDjlns8C0vF7ZXFwRkWifcr9z0mzsvD1OcWq/oPg1tlChXZmHsPHFwc82nNtAZqfmeLucv1N2mDm6xcnuWfsC5D+t4AJyZ4/XWRe8jf9231xQiZcmDItyazrOiMcMprSvkDVAoAmiwbhxA0w32b0bQot6OKfogtD19dvBOB25+gQZjJrORhI6AKZ8W7HkhmLRkTm6ytmalImRu4Hr/X1BA5fGXTkr5BS0ozpHOlkoUUqx6e4tRBOUoaWDZMUvlRgwGNvSvD5kZWCM5b8ZdY6jHjJSlq5idYDfxdRvZ+erItPQPSjjP/TysvmoESrsgftbBZBpzqvRYnpump6g6xNXFoYNsmRwgpTgyzkTYHc8XkPbd5lrrR9z0Dj0AAEsP/VvCHLJbrhHMrME0Cdkd3PmOeHtErcYVXrJp6HnNEOj0qaIYnshwWLajfxwz6jo5d/Z+KKHpRX/hKwmG6Ns9kxZB+2os3X5HuK/lpoUxZtrpcBX8IgNvzpcFD93MRY1OYKTaNM4jRib0J3RnOswMdtVfO5lR50GNDLwAgWs6wre3Rit93x+9pjX6r9LYJM8Z2ImssffjCJot94NSdLkhMMYu0cF9k7qmlvb84iId4mR33RN98RRIlSPDe9NE03FjqOczsai0y5YMU1onIXFR/1ZWfFilrvJMwGyczxqePtSaI4lY+EscwsdP+9MsY5IiCo9oCcju6DiUUcfvYHDvH6XykoGeqOESWcumTtUcqCcpL2wdfXpZ/0u8CqsykCv4p6BWe86RfbB8TC4oXekDLtuajg2kpddnYgAq6P2pqRjZnf2DecJA52s71qSQ+OMu1XhFvAQQ/NXY5LZTvyx3QNr+rJ+QSaltgYwt5aLSYbT+/OhjOLROFSSAZqOQLryjx0kW6O8W7rp2X9EQCYecZ/Euh5pIeCdQOE9sE2PWly4ChXtqRFDsqiF6I5GeMaQaYjnkCmo0YWvZC7s6wgMT7quD8xnSGDBATpQGJA/uCEjm8/c5OLtg7dJp05Czgp+/JPz8zhOUJIox/Kf7RxMuCGy/VHc9wogYO3gtWVgOhD8Y5hZ9OJ9AtEL4R4uhh2sNr1pneftLHiSPeDmmOQN+U4kPwU3m1ky6heoKiU8wMyJArO/JwSGXAUF5Irrtk9xDE11Q2P7uxagHhpsJSaiYYdxOaM2dAxMfBE6bUQV+q8326rJDCIT2+klhrEb7gtbjNg9YLDQqF/p/j9+Gf+B3LXeyEgK3AowDevqLpQEYhCAK4cynP8ifj0bwV0MGdrqGFH0PVvX7Q3RdUALrdkA9B/GlQwWiYGoaPBM5FRqULGyDt3L8mOLh4/4ETOZZbZC7/nxFdguNn1rRmMdVIRc0ZyDmDaXFgVww/0rJovtkFz33Hu9cwwUuVSa0p8IPsVh8z8o2cOSJSah9VJxPCPmcmGwVXlznP67WU1KxQcWVqqPUIfk0nBZ9+ytTPj9SLcJ4V9dLmhYhi7QF5smgtR/JMb1Ldjv9HgtjbszC+6uFZMwPpKOwXYF46joIyg7EISo932/N8z6DsMM951aVoxS2K7ZoxJUGJO6I1skxkjYxA+jXJ3H+XVee2eU5f/HBhhcqJzdkcLLKgdFXpl9wl9UXoUC2X1a6oG+sEjXENvGginbaI/zbymwRSC96jpc20Davi7AQyMZtCaVHZ/EUx64kITeK+fMBPVxK7eOT9TSLsof0w6I1bqQymJL9Js6Hsnc+hyqltQ1C4aFj6yIwm1T/H3HgpMfZ+2VpsvlGQFzMz4kZXO/s5430t3xuVNvLGe1JG3VOkVssd0prIrXlGdZFFRnmtvGAJCsOsdxgE4dDbwtwNLRsZEGKPUMUA6nMqqJeY52N0Y2mdRG87y5WKz5rBe+5hlMGs0eLu7AGXParCr5b//n0ZiYEtoRIhCMGBGsdebkRZ5u5P0kVElp9L+Xy5rl3xVhbmTKm3TOFBpAZt/qeyNhd2gAstip52T1zNzMeKtoEqybGIT8MtJ1q3L3As86jTb50/oTxxhG6VAaYAp3puzqeNUNvuHeo2nhdRPzUDRq82/3M7gSxfoZmhKJk7IO/GK0U7eElHoTTMHsU2XF1p3iFtRWQHEVNk2m41wN3b2Yk49EIwMJZb7xHtsrQ8p9K0UK57E+Fi61VADEaguttcEw7/nTN44hgL7bncoOonN/b9mJaS6Hk5yLidv18Wl3ZVDXu9zkugC0zmsPzTEWZAs0Av3kZVTaTC8rYJDWTwp4hYSB5nJCiy0rH/yDPc0Do3ngOfPwVIRkCAgBeWCiAnFB1ZgqoqavTvsw/0IouWeR+5WY0TrIkF0grQ7+kwvN8Z2xoIJ9PnMMaRLbonsWNNETd7VUVyreGr9F49JccgIXj6r3qoroOqewp3ut6t7A9iXx2FiQY4SfW2c0SoTsTXiWHGPc+Ff9vvOlXihqf6rfijdqAWWIubcOcayMoxPS4wkfTYe8ib7f3KFZCYQDBmkbrSa3EqfguRMb1iZ6loEFHllsQnUdAlXB6jJq4pusdtAJ+RnMsd6RVHFkkqeBVftDxAP8X0w1YclyRpI/Fc/TonWfwQOglSwYaO7RDZZNUScpvCDOURX2sEJpeYpPZ71qIhGcJxAJI19ImIphfAOYoTioJJ5q3dV6EoJPnK00QOpUqv06LykUjBxswoX7GC45PZwq+7UOUU+sm/DtCBGYZ7qUowTXNKooORiDgSp1f2sahO1LbPHZwA9CouoHQUZvYTyfMELPHjQJ9M38L1rE95rxrFzBCm3th/6ZLJd3wHYEGFGDrVvdBiFOzosjsnskcNn498IdhtZck3F+dMyjMUKW5Y0gHh5GZpZMWH1ayFMkAsrv5mhv4qypiJUokhE8oasBxL7hmHV6seN/Yz0WwY2RziTeeQ1XMM8+SeIDDRthnpdwkxI2tr6MF8RfaQT1eI1CVnFWLPOoQ7FxprFAQ+TbppTLmnAnfr1mAXI1ky6X6qN0wAqb8RJhHESyjHXUvSijcBlmwgxgmgUSALexXMI0osR2dwD0bjiGOnUFQXc+ATBFeznQM2IzZwI+w9rnVu+z6pdHe+BxfIoneHgI/yud0AnRKVRt+xz8sV6agJIbGBhdtlwg7kLCWXR886Pn11acqdDbr5Wsw3rv9OVkXs+L4/gLvQqtiAYkZCinaqDTF1FFFgVLtdMn5FUzESAn/JomYcamtNvDnUpN+3nbVJ1zjr8Yyo1XWMuq6Y2RltYcttKxr2k0bbEJ6hvLwv9Gk+lWyf0XkvgNW9qFFiQxBO2WpYH3BUiRq2hLb/EzeNa+oF5F2IBARlP7gq/YhCn1Teg1wBRCVKOJjKmXUC+HScaVXsaW+dxhaVLVygPe6UNqBfGG8HWjeyttgNCC3yjM8TngNblN0TLmVdz680bfAi97opAGWRBj0J0bItZaOK58xuaLXlD0oefQzqcdVmA91C7dFBXadf1mTZxEXRb8lR/PIp0ipFiKeLsGeeQGfYgtREtVwtpyayJiQaHCh1s1Lj37QQnN3757Rc1KvOjH626KWlv/ObaAF7bUYnlcblr6M31I4jxvSt44Qz0nW5gxtYl4671H48YqdMwxRRcBCgZr8jpUoZhfCUKA3Y17+QVWjWg8mmzoi6ynemFPQzgb/LM9Xue7G7fm0p/ajOf+QuInIsVSXRFtv/AaqQjyVB7YPzKKDa2qAywWWaScHGgcT+wuUrkZJ6wi4/8wv7AtYdKIBTE/eqQlqxNYxkxmKASV+cCkaF9EDJOgks0a0g2lmJFSNXJhOcUdt+o/RiNU6WXGdIZYwJsP0/1ex77ZblQSIxjQPDNhlYiKueddID4yfltgDAu9Cq2IBiRkKKdqoNMXUUUWBUu10yfkVTMRICf8miZjLHjzJVfrlXMXS1CzytkpWvYLNmq/zFf1x1M6Ru4ak9MjRKuheLzpaIkAJdyzYtf24Z+rjLqBRKfe4WXPJHnunJrtmTdAMBQ0u3+zp6qW1X+a+vkSNEJh4iK6/toC92YseGXzfXyMe6zQ+QhFDDAP4IXyMdEvjr3CfrvZUHEAsuCu4RMNokFMT33HZkb2n7JLkc5FKOQpxL9BQ+i5ArxUXyTVBTlGi7eRG7i5oWBgfD/Lqt2/MGbu0CU56HNAjdqBHMr9ncb4g8I2XKk7WkwUiuh9VB1lNxnoOis+QmLh1Pm5Of2MLe5CixB3zRM2DTqwNJ2/sckKecSaq6agZeIqXsF3B3AISemDP6HueksEV5tRmX1jhP1RjDnper47uD1XtWAK4tJ5iRtWOCyww+3edbO1JkXGrnyrKiriU7Tvc/UBKXH6EXUDPTvaY3HjOkrMymZtPHPnrYsYoPDpl0Qwz5A/czVOAASX9bcpzLWZN0iZJxOCMQMDpaeNJ8SDrLr4ER9S2aGvLLuce/VlvNedxMJedhVL3GUZj4lwaNLIyMTuZLqxShO+EcgUr3Xj00naHXLOgX96KA3AXQvtSHcLva39jNdn4jPZnm6KMaSw6BD+8h4b1YuVUs/s5NTJLw5ykDV8/HPTuLELMiRIjRYyuLh5AT0WO2thEegMm8a5OTxfZHSsFsN2zsZcfrqt9yDyQqwgFsFb1mrK5cez/UiH8SpxK318AmD1cJTKHwIMwocckA2Yu5UdiYh/6W3XU/1YWJyjHHKrg26/26MhxDo7eotP5+LLrSm2V2YNSWfy04iI87FkkqYETxfjDjSyDGufCiY8/px/6lss9Yy3HfN+sXqTwpufpLeWk2IxwgTbarDip+bwVeZb84p5yMLfqFq4RW/bCkZBjNNmX1Ff1PbuxYUdyRVWHDIcnFKQhDMY6357G0P4uQA4OtaLqGTi5vpUrc0Hgvu5qtlP9a50/TnvoVyfmrsB6ReFoZqnT/M1CC1W9sesQQdoXNnCKt5tzagqi+3o31wpkbeFmfz76SoGT2KGQcq7B/LOC/n5kYmdCqyo+YbEBl4tVcN9bbYxPgeihr5qTNThqWW5vpn0v3ep6fzHkiavfA5shv5gHX6CldnC/bitfhHu2dX9pniY5ZursnS8+k8JPsoRkxJw8nRL5G1EkR3ZTAhdrqb2O39FjCu1gY3dRyAmXDLsThNYrylxOKURRjwnKEaE7Q/N/uLG+3J4BMZjx4j3+M4W8r8cZjG49u/UWtLu28iZ9JKF5SGQzfauOhrtff3uYfLFzAp9OvaVXVjO13gyCJZl0CVFkZirRq/KJH8zAJO4d65vga43o7Hna0tLtPivQJZVwEeYb9fhlj7YWJsVI/rN8NR4KXthXdpfez7UojZNami5KRUasM3R/z3yPsdlMwXIqVeyS0bMhwTxV2q3+0aOB40VwKa97Gw5PM5KiQoy3ti/K4CDk07MTSoYY84A65sPes3HlbKQjZmaZ5p7adhpuST0IG7xZL93aOR9tDtchYz6q3GO1hoE8dikStXofKQZG6WOCymg4gDQ0/M8mnvfl3/FCvHM4Gf/WGnzY8kCUzeMyKiS4WnUwN2TEwP8k4bB1NVtRl3EfQgwcND4GWJSPwLx+izpZGBQGi2tVGbcfyXsoY77UA71lk0dSYbuSYtmdpMX3ZdiaHpTPNdq3fexx0iLjo4dG6IMCADj6w5Fgr10Wj/ewZjDgp2dYZHByPvA6+v7tLfT/s2d+u1M9fbPPBZ9URrl7PrEPAJqtFvONEs5sJul0hvjmkBf7zdNiDCP7Y8Mk6AjstKyOtx/N9Z8B4Ja+oKkpDkvd/RH1iNxsQUHn14mS23pV+QHc7vzuzNlxdldrUo3vzHorGp/i7h2GkRp5pI56ehCE82KoHrA/6bc3/dYItNPKORwnCzXBJN9f67rFCTsAh2qoXBDIXXtr6sLlUmCrvEyD5O+jdMxE8AIzYlw+RtOj7NuDFRz49/5Zw66wvcuUoxcbN0HBSRd7jlPHnM1lGZn7NldSj1CdFL3qSiEXQk2fJwpp4gL3xCDuc6RJCtv1xMJ0OY8BBT3uBht3BtTSe3R+UKsHVvZMSksulcBjtFXcrgjJhFLXIcp/jI6OzphQR1OlDwcWdgM+pV+8q3gC7coLrq5b7sir/EXEaNUaD2g4YW5OZY3bXBaqzjWGcpleoqihzlmmK/QnUjC52KcY09tfi/oFTw1/6wEIQhty33Bdpn7Bo/EAjX83Hr7pjXxsMEH+QpaqLUYrJ837VwZltSgWu+WVbmk0qVfsonwDSyWf9I7+dBuYxbbabquC5rgogZGE05pr9QylqBI4z2j+BCv/pEWjFpl/YqRb8zycswokuO3n16nceppWB9FIUfRTNzfu2YTsev+N5PmjivKIncsttokL3EkcKllFcSPqG+aoAmw7Z8t1g3M4l2XQNyMwokQd2YWISRhni9OJ2PyIcluVlaboFC8/Lvsem8NJQ38mU8FYWAaMDhyjbSKbpR/2F6bhN2nLUcc43ikYdXXOFw4TrqyNRZL7M5ORKlFDs4RfH1L2M/BoQTbBa6QR8PVDp8lWo2ULyBTekxXzwk92axtO7Z4orb7qojIjRzYllreGn1tmArEkAhNCbv0tJLD+w/dUlNLyjXepZDgyvJoiZxjqwceA08Y7joPJVuycd/xeYphlOvCMAGfvRpS/gW8giGSEyRzGED/zg1CrCmZjgL153lpKGQsZ0sp597JZgk66+aAQ5D+/UTL073WCT96pCWrE1jGTGYoBJX5wKTcgkdrDFqqVbW43Ah7yxQgu6/3kMqfrJP8SYfx9ayBwAu9Cq2IBiRkKKdqoNMXUUWNlkI1363EMRj/7K6fLbs0Kq73bqcXEI7GZ7b6+b6zKkxZMVfy/Gwc1HM3kxzEhsF8WLAoEOJwVlI/JMv4dhIsGGjVY42OjWY5aCIjHZUunMvbTGdtmBitMdN0fMzYqTZNfL3aQl0TLZevQxlIbLTZfGiB3dJb00MBIiqtx/nFGtZ4mHbTfSfiTZ9qRx++blp0TOppelPvzoFb8kEFSYa+kcRzYgjgMOnO+bDitkOWY2zJS/fyJtHD+f/C5WRtlCmRuSGO7cslmQ4mlPMb4W7YK2/XEwnQ5jwEFPe4GG3cG1NJ7dH5QqwdW9kxKSy6VwLfHzUBz9eUrjqoyxtTtByuL4FS7/JqrgHR+aLp15qojr1QrBR9D5s5YwxQwQsK4BN9uRnuhajfe8skCtlGzMzoLt+97S2+9aBl0yDBKrcM1ds9pYRxUMzJcvFFq0BTo2WIN1orlCNMDkjeDUqxofX2CJb6hhqjf/aHnhW99Z/3pP5y3RstMhtAPyQM4Yc+Zpf0BZ0abykwLu/T0yzKd1p0oJFDDEyNITOKUzur2Je6KZ6DrSQ+tDEF3A8XIMY9yxrxZL93aOR9tDtchYz6q3GO1hoE8dikStXofKQZG6WOCFXqjtpFJ6VRuwHXSQ73qApRFfawQml5ik9nvWoiEZwn5qPSspsuYv/KiuD0pUtI7Sgp9cvb/cXKRkn+Pq0XGQLHPuJK6rPPnDQzxx0FGO2mT7Dtpqtovz663ui8lkGIHoM/rmARaulG63PAyBkM5bSkhtXPQr76tvoBQPeD6QZil3nd0v+0tsExoFrEB3H0qv+HeTy4PV/+Z/4bqu9jOCfbwChZKCSXSNbtqV+hc7x4D6sXXgaoFejJV0Xiah1HnNE/9bGikMdAE85RPmTc1if99k2gHhRO6Y+oCfVizMv/DQlAtI797pOU5I2gtByJjhwDrwkQo4RBYoCyzudgcMnU2KkLgYASnRy8Ot7XxX+ltQuKtlRptHS7OqItsHL/vK7+YrxWKdAe5CrIDlij16PSDPpyL22reL4zYXfoo4h9jKONSplSW4F5vSAPdfnYdWeRnyHOB7IQmx1W7EkSc61i0sGtQiz4nOVOQGedH8TyGhI4WyuTwSel6mnUB5hFhDFpxeFiBwkyVUte8aygE0WnqPAlM4zFGuJgQUVmNECQVsN7arXeaPhUqa4O3mnfLVEE//kwvBB6PAIkhhHWc6WtCHXV/OheyylhNdR3H2yFfLGlUO3BdjQMi0VXKU96zZum/XJcZlxwWfa62TBCe0jATTne0SXCpa+XTnLaQVDh4EkKRP/4Bvq/b8aSSrRD6rWVUAEGR6hjtmfl5yxHoiS4iaFeuHJaJEKKNrlqzxKnE95WJiG4xD4GBku74W1ZtvdJzE+JsPjRYVecoq8yb9wdbQRX/mlgydK1Ivunsvc9WCphLj0VwlRQcI/lZhw5KpJ+vPfHW7mjTHL1wJDaJ3mY7uPINjPcJzSn/nC1Cv1HRsyHBPFXarf7Ro4HjRXApr3sbDk8zkqJCjLe2L8rgIHEb5H+ZJswDSgOuj0h3DeLPxbUDZ1Ol0yhZNt8DrpaoSxPbuRosklVfdnMnivRZyuT9mYrmsWAy6t+9QLBLxdqnOtC+YiyNx5L0Lp/r95MirIusVU+HblrgfBr+HyqfQcWcLpETosS9zB8lfSFYXwEAOasVXk3GpkGR0MxaFqIjF4Q3VtE/jUF7KWwSzCW+kWvaTRtsQnqG8vC/0aT6VbK066P/3PhPYPQqrmQ7Jkb9JHfscG1RBmV0glTBwwbv8iUQh9ApktGxtRodE2OX9t65xIWqRp41oVG5rdqtS9xxLI/ZN84bvr7Aq3evxfqjn2xmsPE/J/KhYJkUj8NjUSnpAigWUAO2YxOOwU1Q9PiFrCg0cxeJL/C6zliyZW2TBfcSYLvHgzwYYgnYY5Mwo5uIo6Ij4N3V/DaByg943bJq9Fiem6anqDrE1cWhg2yZHAWeixOIMDcZE5V+5q6Bx0xudNEfY74vabCAIDNeIEq7a5vBosrnQWQIj1odtrV6PpbuZY02D463dKFjxTj4tR1znktKpzLNkQACaaJSWFM+Z6WS70H0FkBbSTiuj4FEyPDl9uRTPOFKY36QamvIYeRZY7F5TzArqBDkwyKVZNlQGT3FWh9K1gJ0rPyBY7HgDA=="));
    }


}
