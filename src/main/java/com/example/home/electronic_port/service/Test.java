/*
 * Copyright 2006-2022 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2022/9/28 8:06 PM
 * @since 1.0
 **/
public class Test {

    public static String s =
            "    (function (r) {\n" +
            "        if (typeof exports === \"object\" && typeof module !== \"undefined\") {\n" +
            "            module.exports = r()\n" +
            "        } else {\n" +
            "            if (typeof define === \"function\" && define.amd) {\n" +
            "                define([], r)\n" +
            "            } else {\n" +
            "                var e;\n" +
            "                if (typeof window !== \"undefined\") {\n" +
            "                    e = window\n" +
            "                } else {\n" +
            "                    if (typeof global !== \"undefined\") {\n" +
            "                        e = global\n" +
            "                    } else {\n" +
            "                        if (typeof self !== \"undefined\") {\n" +
            "                            e = self\n" +
            "                        } else {\n" +
            "                            e = this\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "                e.base64js = r()\n" +
            "            }\n" +
            "        }\n" +
            "    })(function () {\n" +
            "        var r, e, t;\n" +
            "        return function r(e, t, n) {\n" +
            "            function o(i, a) {\n" +
            "                if (!t[i]) {\n" +
            "                    if (!e[i]) {\n" +
            "                        var u = typeof require == \"function\" && require;\n" +
            "                        if (!a && u) {\n" +
            "                            return u(i, !0)\n" +
            "                        }\n" +
            "                        if (f) {\n" +
            "                            return f(i, !0)\n" +
            "                        }\n" +
            "                        var d = new Error(\"Cannot find module '\" + i + \"'\");\n" +
            "                        throw d.code = \"MODULE_NOT_FOUND\", d\n" +
            "                    }\n" +
            "                    var c = t[i] = {exports: {}};\n" +
            "                    e[i][0].call(c.exports, function (r) {\n" +
            "                        var t = e[i][1][r];\n" +
            "                        return o(t ? t : r)\n" +
            "                    }, c, c.exports, r, e, t, n)\n" +
            "                }\n" +
            "                return t[i].exports\n" +
            "            }\n" +
            "\n" +
            "            var f = typeof require == \"function\" && require;\n" +
            "            for (var i = 0; i < n.length; i++) {\n" +
            "                o(n[i])\n" +
            "            }\n" +
            "            return o\n" +
            "        }({\n" +
            "            \"/\": [function (r, e, t) {\n" +
            "                t.byteLength = c;\n" +
            "                t.toByteArray = v;\n" +
            "                t.fromByteArray = s;\n" +
            "                var n = [];\n" +
            "                var o = [];\n" +
            "                var f = typeof Uint8Array !== \"undefined\" ? Uint8Array : Array;\n" +
            "                var i = \"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\";\n" +
            "                for (var a = 0, u = i.length; a < u; ++a) {\n" +
            "                    n[a] = i[a];\n" +
            "                    o[i.charCodeAt(a)] = a\n" +
            "                }\n" +
            "                o[\"-\".charCodeAt(0)] = 62;\n" +
            "                o[\"_\".charCodeAt(0)] = 63;\n" +
            "\n" +
            "                function d(r) {\n" +
            "                    var e = r.length;\n" +
            "                    if (e % 4 > 0) {\n" +
            "                        throw new Error(\"Invalid string. Length must be a multiple of 4\")\n" +
            "                    }\n" +
            "                    return r[e - 2] === \"=\" ? 2 : r[e - 1] === \"=\" ? 1 : 0\n" +
            "                }\n" +
            "\n" +
            "                function c(r) {\n" +
            "                    return r.length * 3 / 4 - d(r)\n" +
            "                }\n" +
            "\n" +
            "                function v(r) {\n" +
            "                    var e, t, n, i, a;\n" +
            "                    var u = r.length;\n" +
            "                    i = d(r);\n" +
            "                    a = new f(u * 3 / 4 - i);\n" +
            "                    t = i > 0 ? u - 4 : u;\n" +
            "                    var c = 0;\n" +
            "                    for (e = 0; e < t; e += 4) {\n" +
            "                        n = o[r.charCodeAt(e)] << 18 | o[r.charCodeAt(e + 1)] << 12 | o[r.charCodeAt(e + 2)] << 6 | o[r.charCodeAt(e + 3)];\n" +
            "                        a[c++] = n >> 16 & 255;\n" +
            "                        a[c++] = n >> 8 & 255;\n" +
            "                        a[c++] = n & 255\n" +
            "                    }\n" +
            "                    if (i === 2) {\n" +
            "                        n = o[r.charCodeAt(e)] << 2 | o[r.charCodeAt(e + 1)] >> 4;\n" +
            "                        a[c++] = n & 255\n" +
            "                    } else {\n" +
            "                        if (i === 1) {\n" +
            "                            n = o[r.charCodeAt(e)] << 10 | o[r.charCodeAt(e + 1)] << 4 | o[r.charCodeAt(e + 2)] >> 2;\n" +
            "                            a[c++] = n >> 8 & 255;\n" +
            "                            a[c++] = n & 255\n" +
            "                        }\n" +
            "                    }\n" +
            "                    return a\n" +
            "                }\n" +
            "\n" +
            "                function l(r) {\n" +
            "                    return n[r >> 18 & 63] + n[r >> 12 & 63] + n[r >> 6 & 63] + n[r & 63]\n" +
            "                }\n" +
            "\n" +
            "                function h(r, e, t) {\n" +
            "                    var n;\n" +
            "                    var o = [];\n" +
            "                    for (var f = e; f < t; f += 3) {\n" +
            "                        n = (r[f] << 16) + (r[f + 1] << 8) + r[f + 2];\n" +
            "                        o.push(l(n))\n" +
            "                    }\n" +
            "                    return o.join(\"\")\n" +
            "                }\n" +
            "\n" +
            "                function s(r) {\n" +
            "                    var e;\n" +
            "                    var t = r.length;\n" +
            "                    var o = t % 3;\n" +
            "                    var f = \"\";\n" +
            "                    var i = [];\n" +
            "                    var a = 16383;\n" +
            "                    for (var u = 0, d = t - o; u < d; u += a) {\n" +
            "                        i.push(h(r, u, u + a > d ? d : u + a))\n" +
            "                    }\n" +
            "                    if (o === 1) {\n" +
            "                        e = r[t - 1];\n" +
            "                        f += n[e >> 2];\n" +
            "                        f += n[e << 4 & 63];\n" +
            "                        f += \"==\"\n" +
            "                    } else {\n" +
            "                        if (o === 2) {\n" +
            "                            e = (r[t - 2] << 8) + r[t - 1];\n" +
            "                            f += n[e >> 10];\n" +
            "                            f += n[e >> 4 & 63];\n" +
            "                            f += n[e << 2 & 63];\n" +
            "                            f += \"=\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                    i.push(f);\n" +
            "                    return i.join(\"\")\n" +
            "                }\n" +
            "            }, {}]\n" +
            "        }, {}, [])(\"/\")\n" +
            "    });\n" +
            "\n" +
            "\n" +
            "    /**\n" +
            "     * 国密SM4加密算法\n" +
            "     * @email 70255403@qq.com\n" +
            "     * @date 2020-07-14\n" +
            "     */\n" +
            "    function SM4_Context() {\n" +
            "        this.mode = 1;\n" +
            "        this.isPadding = true;\n" +
            "        this.sk = new Array(32);\n" +
            "    }\n" +
            "\n" +
            "    function SM4() {\n" +
            "        this.SM4_ENCRYPT = 1;\n" +
            "        this.SM4_DECRYPT = 0;\n" +
            "\n" +
            "        var SboxTable = [0xd6, 0x91, 0xe9, 0xfe, 0xcc, 0xe1, 0x3d, 0xb7, 0x16, 0xb6, 0x14, 0xc2, 0x28, 0xfb, 0x2c, 0x05,\n" +
            "            0x2b, 0x67, 0x9a, 0x76, 0x2a, 0xbe, 0x04, 0xc3, 0xaa, 0x44, 0x13, 0x26, 0x49, 0x86, 0x06, 0x99,\n" +
            "            0x9c, 0x42, 0x50, 0xf4, 0x91, 0xef, 0x98, 0x7a, 0x33, 0x54, 0x0b, 0x43, 0xed, 0xcf, 0xac, 0x62,\n" +
            "            0xe4, 0xb3, 0x1c, 0xa9, 0xc9, 0x08, 0xe8, 0x95, 0x80, 0xdf, 0x94, 0xfa, 0x75, 0x8f, 0x3f, 0xa6,\n" +
            "            0x47, 0x07, 0xa7, 0xfc, 0xf3, 0x73, 0x17, 0xba, 0x83, 0x59, 0x3c, 0x19, 0xe6, 0x85, 0x4f, 0xa8,\n" +
            "            0x68, 0x6b, 0x81, 0xb2, 0x71, 0x64, 0xda, 0x8b, 0xf8, 0xeb, 0x0f, 0x4b, 0x70, 0x56, 0x9d, 0x35,\n" +
            "            0x1e, 0x24, 0x0e, 0x5e, 0x63, 0x58, 0xd1, 0xa2, 0x25, 0x22, 0x7c, 0x3b, 0x01, 0x21, 0x78, 0x87,\n" +
            "            0xd4, 0x00, 0x46, 0x57, 0x9f, 0xd3, 0x27, 0x52, 0x4c, 0x36, 0x02, 0xe7, 0xa0, 0xc4, 0xc8, 0x9e,\n" +
            "            0xea, 0xbf, 0x8a, 0xd2, 0x40, 0xc7, 0x38, 0xb5, 0xa3, 0xf7, 0xf2, 0xce, 0xf9, 0x61, 0x15, 0xa1,\n" +
            "            0xe0, 0xae, 0x5d, 0xa4, 0x9b, 0x34, 0x1a, 0x55, 0xad, 0x93, 0x32, 0x30, 0xf5, 0x8c, 0xb1, 0xe3,\n" +
            "            0x1d, 0xf6, 0xe2, 0x2e, 0x82, 0x66, 0xca, 0x60, 0xc0, 0x29, 0x23, 0xab, 0x0d, 0x53, 0x4e, 0x6f,\n" +
            "            0xd5, 0xdb, 0x37, 0x45, 0xde, 0xfd, 0x8e, 0x2f, 0x03, 0xff, 0x6a, 0x72, 0x6d, 0x6c, 0x5b, 0x51,\n" +
            "            0x8d, 0x1b, 0xaf, 0x92, 0xbb, 0xdd, 0xbc, 0x7f, 0x11, 0xd9, 0x5c, 0x41, 0x1f, 0x10, 0x5a, 0xd8,\n" +
            "            0x0a, 0xc1, 0x31, 0x88, 0xa5, 0xcd, 0x7b, 0xbd, 0x2d, 0x74, 0xd0, 0x12, 0xb8, 0xe5, 0xb4, 0xb0,\n" +
            "            0x89, 0x69, 0x97, 0x4a, 0x0c, 0x96, 0x77, 0x7e, 0x65, 0xb9, 0xf1, 0x09, 0xc5, 0x6e, 0xc6, 0x84,\n" +
            "            0x18, 0xf0, 0x7d, 0xec, 0x3a, 0xdc, 0x4d, 0x20, 0x79, 0xee, 0x5f, 0x3e, 0xd7, 0xcb, 0x39, 0x49];\n" +
            "\n" +
            "        var FK = [0xa3b1bac6, 0x56aa3350, 0x677d9197, 0xb27022dd];\n" +
            "\n" +
            "        var CK = [0x00070e15, 0x1c232a31, 0x383f464d, 0x545b6269,\n" +
            "            0x70777e85, 0x8c939aa1, 0xa8afb6bd, 0xc4cbd2d9,\n" +
            "            0xe0e7eef5, 0xfc030a11, 0x181f262d, 0x343b4249,\n" +
            "            0x50575e65, 0x6c737a81, 0x888f969d, 0xa4abb2b9,\n" +
            "            0xc0c7ced5, 0xdce3eaf1, 0xf8ff060d, 0x141b2229,\n" +
            "            0x30373e45, 0x4c535a61, 0x686f767d, 0x848b9299,\n" +
            "            0xa0a7aeb5, 0xbcc3cad1, 0xd8dfe6ed, 0xf4fb0209,\n" +
            "            0x10171e25, 0x2c333a41, 0x484f565d, 0x646b7280];\n" +
            "\n" +
            "        this.GET_ULONG_BE = function (b, i) {\n" +
            "            return (b[i] & 0xff) << 24 | ((b[i + 1] & 0xff) << 16) | ((b[i + 2] & 0xff) << 8) | (b[i + 3] & 0xff) & 0xffffffff;\n" +
            "        }\n" +
            "\n" +
            "        this.PUT_ULONG_BE = function (n, b, i) {\n" +
            "            var t1 = (0xFF & (n >> 24));\n" +
            "            var t2 = (0xFF & (n >> 16));\n" +
            "            var t3 = (0xFF & (n >> 8));\n" +
            "            var t4 = (0xFF & (n));\n" +
            "            b[i] = t1 > 128 ? t1 - 256 : t1;\n" +
            "            b[i + 1] = t2 > 128 ? t2 - 256 : t2;\n" +
            "            b[i + 2] = t3 > 128 ? t3 - 256 : t3;\n" +
            "            b[i + 3] = t4 > 128 ? t4 - 256 : t4;\n" +
            "        }\n" +
            "\n" +
            "        this.SHL = function (x, n) {\n" +
            "            return (x & 0xFFFFFFFF) << n;\n" +
            "        }\n" +
            "\n" +
            "        this.ROTL = function (x, n) {\n" +
            "            var s = this.SHL(x, n);\n" +
            "            var ss = x >> (32 - n);\n" +
            "            return this.SHL(x, n) | x >> (32 - n);\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        this.sm4Lt = function (ka) {\n" +
            "            var bb = 0;\n" +
            "            var c = 0;\n" +
            "            var a = new Array(4);\n" +
            "            var b = new Array(4);\n" +
            "            this.PUT_ULONG_BE(ka, a, 0);\n" +
            "            b[0] = this.sm4Sbox(a[0]);\n" +
            "            b[1] = this.sm4Sbox(a[1]);\n" +
            "            b[2] = this.sm4Sbox(a[3]);\n" +
            "            b[3] = this.sm4Sbox(a[2]);\n" +
            "            bb = this.GET_ULONG_BE(b, 0);\n" +
            "            c = bb ^ this.ROTL(bb, 2) ^ this.ROTL(bb, 10) ^ this.ROTL(bb, 18) ^ this.ROTL(bb, 24);\n" +
            "            return c;\n" +
            "        }\n" +
            "\n" +
            "        this.sm4F = function (x0, x1, x2, x3, rk) {\n" +
            "            return x0 ^ this.sm4Lt(x1 ^ x2 ^ x3 ^ rk);\n" +
            "        }\n" +
            "\n" +
            "        this.sm4CalciRK = function (ka) {\n" +
            "            var bb = 0;\n" +
            "            var rk = 0;\n" +
            "            var a = new Array(4);\n" +
            "            var b = new Array(4);\n" +
            "            this.PUT_ULONG_BE(ka, a, 0);\n" +
            "            b[0] = this.sm4Sbox(a[0]);\n" +
            "            b[1] = this.sm4Sbox(a[1]);\n" +
            "            b[2] = this.sm4Sbox(a[2]);\n" +
            "            b[3] = this.sm4Sbox(a[3]);\n" +
            "            bb = this.GET_ULONG_BE(b, 0);\n" +
            "            rk = bb ^ this.ROTL(bb, 13) ^ this.ROTL(bb, 23);\n" +
            "            return rk;\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        this.sm4Sbox = function (inch) {\n" +
            "            var i = inch & 0xFF;\n" +
            "            var retVal = SboxTable[i];\n" +
            "            return retVal > 128 ? retVal - 256 : retVal;\n" +
            "        }\n" +
            "\n" +
            "        this.sm4_setkey_enc = function (ctx, key) {\n" +
            "            if (ctx == null) {\n" +
            "                alert(\"ctx is null!\");\n" +
            "                return false;\n" +
            "            }\n" +
            "            if (key == null || key.length != 16) {\n" +
            "                alert(\"key error!\");\n" +
            "                return false;\n" +
            "            }\n" +
            "            ctx.mode = this.SM4_ENCRYPT;\n" +
            "            this.sm4_setkey(ctx.sk, key);\n" +
            "\n" +
            "        };\n" +
            "        //生成解密密钥\n" +
            "        this.sm4_setkey_dec = function (ctx, key) {\n" +
            "            if (ctx == null) {\n" +
            "                Error(\"ctx is null!\");\n" +
            "            }\n" +
            "\n" +
            "            if (key == null || key.length != 16) {\n" +
            "                Error(\"key error!\");\n" +
            "            }\n" +
            "\n" +
            "            var i = 0;\n" +
            "            ctx.mode = 0;\n" +
            "            this.sm4_setkey(ctx.sk, key);\n" +
            "            ctx.sk = ctx.sk.reverse();\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        this.sm4_setkey = function (SK, key) {\n" +
            "            var MK = new Array(4);\n" +
            "            var k = new Array(36);\n" +
            "            var i = 0;\n" +
            "            MK[0] = this.GET_ULONG_BE(key, 0);\n" +
            "            MK[1] = this.GET_ULONG_BE(key, 4);\n" +
            "            MK[2] = this.GET_ULONG_BE(key, 8);\n" +
            "            MK[3] = this.GET_ULONG_BE(key, 12);\n" +
            "            k[0] = MK[0] ^ FK[0];\n" +
            "            k[1] = MK[1] ^ FK[1];\n" +
            "            k[2] = MK[2] ^ FK[2];\n" +
            "            k[3] = MK[3] ^ FK[3];\n" +
            "            for (var i = 0; i < 32; i++) {\n" +
            "                k[(i + 4)] = (k[i] ^ this.sm4CalciRK(k[(i + 1)] ^ k[(i + 2)] ^ k[(i + 3)] ^ CK[i]));\n" +
            "                SK[i] = k[(i + 4)];\n" +
            "            }\n" +
            "\n" +
            "        }\n" +
            "        this.padding = function (input, mode) {\n" +
            "            if (input == null) {\n" +
            "                return null;\n" +
            "            }\n" +
            "            var ret = null;\n" +
            "            if (mode == this.SM4_ENCRYPT) {\n" +
            "                var p = parseInt(16 - input.length % 16);\n" +
            "                ret = input.slice(0);\n" +
            "                for (var i = 0; i < p; i++) {\n" +
            "                    ret[input.length + i] = p;\n" +
            "                }\n" +
            "            } else {\n" +
            "                var p = input[input.length - 1];\n" +
            "                ret = input.slice(0, input.length - p);\n" +
            "            }\n" +
            "            return ret;\n" +
            "        }\n" +
            "        this.sm4_one_round = function (sk, input, output) {\n" +
            "            var i = 0;\n" +
            "            var ulbuf = new Array(36);\n" +
            "            ulbuf[0] = this.GET_ULONG_BE(input, 0);\n" +
            "            ulbuf[1] = this.GET_ULONG_BE(input, 4);\n" +
            "            ulbuf[2] = this.GET_ULONG_BE(input, 8);\n" +
            "            ulbuf[3] = this.GET_ULONG_BE(input, 12);\n" +
            "            while (i < 32) {\n" +
            "                ulbuf[(i + 4)] = this.sm4F(ulbuf[i], ulbuf[(i + 1)], ulbuf[(i + 2)], ulbuf[(i + 3)], sk[i]);\n" +
            "                i++;\n" +
            "            }\n" +
            "            this.PUT_ULONG_BE(ulbuf[35], output, 0);\n" +
            "            this.PUT_ULONG_BE(ulbuf[34], output, 4);\n" +
            "            this.PUT_ULONG_BE(ulbuf[33], output, 8);\n" +
            "            this.PUT_ULONG_BE(ulbuf[32], output, 12);\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "        this.sm4_crypt_ecb = function (ctx, input) {\n" +
            "            if (input == null) {\n" +
            "                alert(\"input is null!\");\n" +
            "            }\n" +
            "            if ((ctx.isPadding) && (ctx.mode == this.SM4_ENCRYPT)) {\n" +
            "                input = this.padding(input, this.SM4_ENCRYPT);\n" +
            "            }\n" +
            "\n" +
            "            var i = 0;\n" +
            "            var length = input.length;\n" +
            "            var bous = new Array();\n" +
            "            for (; length > 0; length -= 16) {\n" +
            "                var out = new Array(16);\n" +
            "                var ins = input.slice(i * 16, (16 * (i + 1)));\n" +
            "                this.sm4_one_round(ctx.sk, ins, out)\n" +
            "                bous = bous.concat(out);\n" +
            "                i++;\n" +
            "            }\n" +
            "\n" +
            "            var output = bous;\n" +
            "            if (ctx.isPadding && ctx.mode == this.SM4_DECRYPT) {\n" +
            "                output = this.padding(output, this.SM4_DECRYPT);\n" +
            "            }\n" +
            "            for (var i = 0; i < output.length; i++) {\n" +
            "                if (output[i] < 0) {\n" +
            "                    output[i] = output[i] + 256;\n" +
            "                }\n" +
            "            }\n" +
            "            return output;\n" +
            "        }\n" +
            "\n" +
            "        this.sm4_crypt_cbc = function (ctx, iv, input) {\n" +
            "            if (iv == null || iv.length != 16) {\n" +
            "                alert(\"iv error!\");\n" +
            "            }\n" +
            "\n" +
            "            if (input == null) {\n" +
            "                alert(\"input is null!\");\n" +
            "            }\n" +
            "\n" +
            "            if (ctx.isPadding && ctx.mode == this.SM4_ENCRYPT) {\n" +
            "                input = this.padding(input, this.SM4_ENCRYPT);\n" +
            "            }\n" +
            "\n" +
            "            var i = 0;\n" +
            "            var length = input.length;\n" +
            "            var bous = new Array();\n" +
            "            if (ctx.mode == this.SM4_ENCRYPT) {\n" +
            "                var k = 0;\n" +
            "                for (; length > 0; length -= 16) {\n" +
            "                    var out = new Array(16);\n" +
            "                    var out1 = new Array(16);\n" +
            "                    var ins = input.slice(k * 16, (16 * (k + 1)));\n" +
            "\n" +
            "                    for (i = 0; i < 16; i++) {\n" +
            "                        out[i] = (ins[i] ^ iv[i]);\n" +
            "                    }\n" +
            "                    this.sm4_one_round(ctx.sk, out, out1);\n" +
            "                    iv = out1.slice(0, 16);\n" +
            "                    bous = bous.concat(out1);\n" +
            "                    k++;\n" +
            "                }\n" +
            "            } else {\n" +
            "                var temp = [];\n" +
            "                var k = 0;\n" +
            "                for (; length > 0; length -= 16) {\n" +
            "                    var out = new Array(16);\n" +
            "                    var out1 = new Array(16);\n" +
            "                    var ins = input.slice(k * 16, (16 * (k + 1)));\n" +
            "                    temp = ins.slice(0, 16);\n" +
            "                    sm4_one_round(ctx.sk, ins, out);\n" +
            "                    for (i = 0; i < 16; i++) {\n" +
            "                        out1[i] = (out[i] ^ iv[i]);\n" +
            "                    }\n" +
            "                    iv = temp.slice(0, 16);\n" +
            "                    bous = bous.concat(out1);\n" +
            "                    k++;\n" +
            "                }\n" +
            "            }\n" +
            "\n" +
            "            var output = bous;\n" +
            "            if (ctx.isPadding && ctx.mode == this.SM4_DECRYPT) {\n" +
            "                output = this.padding(output, this.SM4_DECRYPT);\n" +
            "            }\n" +
            "\n" +
            "            for (var i = 0; i < output.length; i++) {\n" +
            "                if (output[i] < 0) {\n" +
            "                    output[i] = output[i] + 256;\n" +
            "                }\n" +
            "            }\n" +
            "            return output;\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    function SM4Util() {\n" +
            "        this.secretKey = \"r3ecD2WVRGARmmq9\";\n" +
            "        this.iv = \"\";\n" +
            "        this.hexString = false;\n" +
            "        //加密_ECB\n" +
            "        this.encryptData_ECB = function (plainText) {\n" +
            "            try {\n" +
            "                var sm4 = new SM4();\n" +
            "                var ctx = new SM4_Context();\n" +
            "                ctx.isPadding = true;\n" +
            "                ctx.mode = sm4.SM4_ENCRYPT;\n" +
            "                var keyBytes = stringToByte(this.secretKey);\n" +
            "                sm4.sm4_setkey_enc(ctx, keyBytes);\n" +
            "                var encrypted = sm4.sm4_crypt_ecb(ctx, stringToByte(plainText));\n" +
            "                var cipherText = base64js.fromByteArray(encrypted);\n" +
            "                if (cipherText != null && cipherText.trim().length > 0) {\n" +
            "                    cipherText.replace(/(\\s*|\\t|\\r|\\n)/g, \"\");\n" +
            "                }\n" +
            "                return cipherText;\n" +
            "            } catch (e) {\n" +
            "                return null;\n" +
            "            }\n" +
            "\n" +
            "        }\n" +
            "        //解密_ECB\n" +
            "        this.decryptData_ECB = function (cipherText) {\n" +
            "            try {\n" +
            "                var sm4 = new SM4();\n" +
            "                var ctx = new SM4_Context();\n" +
            "                ctx.isPadding = true;\n" +
            "                ctx.mode = sm4.SM4_ENCRYPT;\n" +
            "                var keyBytes = stringToByte(this.secretKey);\n" +
            "                sm4.sm4_setkey_dec(ctx, keyBytes);\n" +
            "                var decrypted = sm4.sm4_crypt_ecb(ctx, base64js.toByteArray(cipherText));\n" +
            "                return byteToString(decrypted);\n" +
            "            } catch (e) {\n" +
            "                return null;\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        this.encryptData_CBC = function (plainText) {\n" +
            "            try {\n" +
            "                var sm4 = new SM4();\n" +
            "                var ctx = new SM4_Context();\n" +
            "                ctx.isPadding = true;\n" +
            "                ctx.mode = sm4.SM4_ENCRYPT;\n" +
            "\n" +
            "                var keyBytes = stringToByte(this.secretKey);\n" +
            "                var ivBytes = stringToByte(this.iv);\n" +
            "\n" +
            "                sm4.sm4_setkey_enc(ctx, keyBytes);\n" +
            "                var encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, stringToByte(plainText));\n" +
            "                var cipherText = base64js.fromByteArray(encrypted);\n" +
            "                if (cipherText != null && cipherText.trim().length > 0) {\n" +
            "                    cipherText.replace(/(\\s*|\\t|\\r|\\n)/g, \"\");\n" +
            "                }\n" +
            "                return cipherText;\n" +
            "            } catch (e) {\n" +
            "                return null;\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        stringToByte = function (str) {\n" +
            "            var bytes = new Array();\n" +
            "            var len, c;\n" +
            "            len = str.length;\n" +
            "            for (var i = 0; i < len; i++) {\n" +
            "                c = str.charCodeAt(i);\n" +
            "                if (c >= 0x010000 && c <= 0x10FFFF) {\n" +
            "                    bytes.push(((c >> 18) & 0x07) | 0xF0);\n" +
            "                    bytes.push(((c >> 12) & 0x3F) | 0x80);\n" +
            "                    bytes.push(((c >> 6) & 0x3F) | 0x80);\n" +
            "                    bytes.push((c & 0x3F) | 0x80);\n" +
            "                } else if (c >= 0x000800 && c <= 0x00FFFF) {\n" +
            "                    bytes.push(((c >> 12) & 0x0F) | 0xE0);\n" +
            "                    bytes.push(((c >> 6) & 0x3F) | 0x80);\n" +
            "                    bytes.push((c & 0x3F) | 0x80);\n" +
            "                } else if (c >= 0x000080 && c <= 0x0007FF) {\n" +
            "                    bytes.push(((c >> 6) & 0x1F) | 0xC0);\n" +
            "                    bytes.push((c & 0x3F) | 0x80);\n" +
            "                } else {\n" +
            "                    bytes.push(c & 0xFF);\n" +
            "                }\n" +
            "            }\n" +
            "            return bytes;\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        byteToString = function (arr) {\n" +
            "            if (typeof arr === 'string') {\n" +
            "                return arr;\n" +
            "            }\n" +
            "            var str = '',\n" +
            "                _arr = arr;\n" +
            "            for (var i = 0; i < _arr.length; i++) {\n" +
            "                var one = _arr[i].toString(2),\n" +
            "                    v = one.match(/^1+?(?=0)/);\n" +
            "                if (v && one.length == 8) {\n" +
            "                    var bytesLength = v[0].length;\n" +
            "                    var store = _arr[i].toString(2).slice(7 - bytesLength);\n" +
            "                    for (var st = 1; st < bytesLength; st++) {\n" +
            "                        store += _arr[st + i].toString(2).slice(2);\n" +
            "                    }\n" +
            "                    str += String.fromCharCode(parseInt(store, 2));\n" +
            "                    i += bytesLength - 1;\n" +
            "                } else {\n" +
            "                    str += String.fromCharCode(_arr[i]);\n" +
            "                }\n" +
            "            }\n" +
            "            return str;\n" +
            "        }\n" +
            "    };\n" +
            "\n" +
            "    kid = function (key, content) {\n" +
            "        var s4 = new SM4Util();\n" +
            "        s4.secretKey = key\n" +
            "        // 加密\n" +
            "        // 解密\n" +
            "        var dedata = s4.decryptData_ECB(content);\n" +
            "        return dedata;\n" +
            "    }\n" ;


    public static Object jsObjFunc(String jsStr, String function, Object... args) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine scriptEngine = sem.getEngineByName("js");
        scriptEngine.eval(jsStr);
        Invocable inv2 = (Invocable) scriptEngine;
        return inv2.invokeFunction(function, args);

    }

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {

      String ds= (String) jsObjFunc(s, "kid", "r3ecD2WVRGARmmq9", "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");



        System.out.print(s);
    }


}
