package com.github.rviehmann.aoc2020;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;

public class Day14 {

    private static final String EXAMPLE1 =
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                    "mem[8] = 11\n" +
                    "mem[7] = 101\n" +
                    "mem[8] = 0";

    private static final String EXAMPLE2 =
            "mask = 000000000000000000000000000000X1001X\n" +
                    "mem[42] = 100\n" +
                    "mask = 00000000000000000000000000000000X0XX\n" +
                    "mem[26] = 1";

    // From: https://adventofcode.com/2020/day/14/input
    private static final String INPUT =
            "mask = 1X01XXX001101X00001100X1010X10101101\n" +
                    "mem[62085] = 231745\n" +
                    "mem[14249] = 1252796\n" +
                    "mem[34831] = 33366317\n" +
                    "mem[31126] = 161974\n" +
                    "mask = 10X000X000X0X1X1X11X101X11010001100X\n" +
                    "mem[40311] = 636\n" +
                    "mem[2321] = 5524\n" +
                    "mem[23059] = 7959\n" +
                    "mask = 0110X01X0110XX001X100010010X10110XX1\n" +
                    "mem[42999] = 2102\n" +
                    "mem[41285] = 80603435\n" +
                    "mem[52476] = 223677\n" +
                    "mem[9277] = 6210242\n" +
                    "mask = X1100010011001001XX0100000X11X1XX00X\n" +
                    "mem[42801] = 57157\n" +
                    "mem[13133] = 3221\n" +
                    "mem[22145] = 159400\n" +
                    "mask = X01000100X10X101X110100011X100X11X01\n" +
                    "mem[39436] = 148937739\n" +
                    "mem[25196] = 7003\n" +
                    "mem[45991] = 31492\n" +
                    "mem[57166] = 1060617643\n" +
                    "mem[1222] = 19750079\n" +
                    "mem[49995] = 17327401\n" +
                    "mem[42300] = 332265\n" +
                    "mask = 0X00X110X1100X0XX0111010110000000X01\n" +
                    "mem[34201] = 508316\n" +
                    "mem[52019] = 3264\n" +
                    "mem[52418] = 683841\n" +
                    "mem[54502] = 17768\n" +
                    "mem[18030] = 23234\n" +
                    "mem[51834] = 375311\n" +
                    "mask = 110X1010X1101X001X101100010110001001\n" +
                    "mem[60606] = 42789\n" +
                    "mem[41034] = 7086131\n" +
                    "mem[9425] = 44654861\n" +
                    "mask = 1110X1110110X1X00001X100XX0110XX10X1\n" +
                    "mem[18872] = 5938561\n" +
                    "mem[21278] = 29483\n" +
                    "mem[4236] = 1583056\n" +
                    "mem[49154] = 14693\n" +
                    "mem[4898] = 37095\n" +
                    "mask = 010011110100000X0X11100X0100011000X0\n" +
                    "mem[5162] = 734\n" +
                    "mem[52776] = 386349961\n" +
                    "mask = 1X00101X01101X0011XX00100XX110110X01\n" +
                    "mem[34909] = 5498749\n" +
                    "mem[42592] = 1986929\n" +
                    "mem[8114] = 4008646\n" +
                    "mem[52085] = 267607\n" +
                    "mem[61688] = 961\n" +
                    "mem[37215] = 89761709\n" +
                    "mask = 0X001110011101100001000X0111X0010X00\n" +
                    "mem[47229] = 6089\n" +
                    "mem[22834] = 174447164\n" +
                    "mask = 0001011X0011X0011X101100X0100100X00X\n" +
                    "mem[16560] = 1857\n" +
                    "mem[11065] = 7042275\n" +
                    "mask = 00101010X11011X111101XX1111101110111\n" +
                    "mem[5134] = 41911376\n" +
                    "mem[2941] = 95087\n" +
                    "mem[40272] = 61528\n" +
                    "mem[11917] = 28550\n" +
                    "mask = 0010001000X011X111101011110110X1100X\n" +
                    "mem[40272] = 309398\n" +
                    "mem[12761] = 10225540\n" +
                    "mem[55976] = 2046\n" +
                    "mem[15571] = 472047919\n" +
                    "mem[11089] = 134646490\n" +
                    "mask = 1X101X100X1010X000010010000101XX1X01\n" +
                    "mem[37453] = 31833557\n" +
                    "mem[50660] = 1041452\n" +
                    "mem[34953] = 49542802\n" +
                    "mem[16595] = 2115298\n" +
                    "mask = 00100X10011X10X01111X01001000X11X001\n" +
                    "mem[10299] = 10060397\n" +
                    "mem[16696] = 4786\n" +
                    "mem[12569] = 103548\n" +
                    "mem[5214] = 100891841\n" +
                    "mask = 11XX0X11011011000X0X010X000011011X11\n" +
                    "mem[59400] = 47\n" +
                    "mem[19310] = 346798\n" +
                    "mem[23327] = 1912\n" +
                    "mask = 011X00X0X1101X001110X11100X11X110X01\n" +
                    "mem[16948] = 3831\n" +
                    "mem[41758] = 20612\n" +
                    "mask = 0XX0101X0XX0XX0011100000000X10011101\n" +
                    "mem[10592] = 752\n" +
                    "mem[11917] = 8384276\n" +
                    "mem[63204] = 1649\n" +
                    "mask = 11001011X010X0X00101XX001000111X1000\n" +
                    "mem[34728] = 2694696\n" +
                    "mem[54039] = 9416941\n" +
                    "mem[33814] = 12873\n" +
                    "mem[16392] = 3101\n" +
                    "mem[2095] = 39706\n" +
                    "mem[17489] = 19386587\n" +
                    "mem[1963] = 313\n" +
                    "mask = 0X001010011X1000111011X011XX00011001\n" +
                    "mem[10673] = 12202\n" +
                    "mem[40442] = 119307467\n" +
                    "mem[42409] = 802446\n" +
                    "mask = 01011X10101110X110X101X00100X01110X1\n" +
                    "mem[27993] = 292\n" +
                    "mem[2199] = 3111840\n" +
                    "mem[41510] = 151344\n" +
                    "mem[56672] = 2302\n" +
                    "mem[1836] = 7863863\n" +
                    "mem[29193] = 6539559\n" +
                    "mask = 11X0X0XX01101X000XX1X0X0000110101101\n" +
                    "mem[28994] = 1656531\n" +
                    "mem[42429] = 29247609\n" +
                    "mem[25920] = 2778021\n" +
                    "mem[52054] = 13687283\n" +
                    "mask = 00XX0X1000XX1001111010001000110X10XX\n" +
                    "mem[30554] = 2840\n" +
                    "mem[41145] = 729353\n" +
                    "mem[51825] = 1843\n" +
                    "mem[15571] = 15078\n" +
                    "mem[24179] = 853914\n" +
                    "mask = 110011110X00001X11X11000XX1000101X00\n" +
                    "mem[20793] = 120507\n" +
                    "mem[47475] = 1047969\n" +
                    "mem[7653] = 116502812\n" +
                    "mem[10299] = 262\n" +
                    "mem[4749] = 7708995\n" +
                    "mem[17984] = 292971\n" +
                    "mem[24419] = 3689\n" +
                    "mask = 01X01110X1X00001101X01110X0X0111X100\n" +
                    "mem[28681] = 212518151\n" +
                    "mem[47073] = 234186\n" +
                    "mask = 10101011X1101100000101X001110X0X0XX1\n" +
                    "mem[49011] = 73567490\n" +
                    "mem[35343] = 46031440\n" +
                    "mem[60755] = 295587153\n" +
                    "mem[57953] = 164618\n" +
                    "mem[36780] = 15848\n" +
                    "mask = 000X0110X100X0X0101100X0001000000001\n" +
                    "mem[5272] = 640\n" +
                    "mem[57797] = 386514206\n" +
                    "mem[5181] = 758369\n" +
                    "mem[29826] = 984\n" +
                    "mask = 1110XX11011XX1000001010X01X01X10X101\n" +
                    "mem[17236] = 76737\n" +
                    "mem[1477] = 716539\n" +
                    "mem[779] = 30266\n" +
                    "mem[2830] = 578\n" +
                    "mem[33679] = 661\n" +
                    "mask = 11100111X110010000011100XX1011X011X1\n" +
                    "mem[24512] = 6088914\n" +
                    "mem[32923] = 9885244\n" +
                    "mem[51834] = 680497623\n" +
                    "mem[34053] = 52330882\n" +
                    "mask = 11100111X1X01100000X0001X1XX1X1010XX\n" +
                    "mem[28041] = 180345\n" +
                    "mem[39257] = 833\n" +
                    "mem[53001] = 9520\n" +
                    "mask = X0100X1X0X10101X1111001X0101011X1X01\n" +
                    "mem[8114] = 40449\n" +
                    "mem[7399] = 54988839\n" +
                    "mem[63583] = 91288266\n" +
                    "mem[12600] = 164360624\n" +
                    "mask = X10X111X01XX000110X1000X0X00100000X1\n" +
                    "mem[44594] = 1866\n" +
                    "mem[32966] = 196431\n" +
                    "mem[10211] = 146497708\n" +
                    "mask = 01X000101010000X111X0X001X11001111X1\n" +
                    "mem[2836] = 1851\n" +
                    "mem[32305] = 68507\n" +
                    "mem[35678] = 500215749\n" +
                    "mem[9647] = 129392\n" +
                    "mem[33738] = 7500\n" +
                    "mask = 00X00010011X10001X110010X0XX00X0011X\n" +
                    "mem[43162] = 23474\n" +
                    "mem[35678] = 1020\n" +
                    "mem[37936] = 661919\n" +
                    "mem[63957] = 275633032\n" +
                    "mem[46593] = 75500\n" +
                    "mem[56959] = 3180\n" +
                    "mask = 0010001XX110110110X0101001001X010111\n" +
                    "mem[15524] = 335730672\n" +
                    "mem[60461] = 1503665\n" +
                    "mem[61126] = 521\n" +
                    "mem[31215] = 834049\n" +
                    "mem[59974] = 985\n" +
                    "mem[61947] = 74705\n" +
                    "mem[47240] = 211449028\n" +
                    "mask = 101000100110010X0X1X1X01010100X10011\n" +
                    "mem[51545] = 1355\n" +
                    "mem[28041] = 29594\n" +
                    "mem[19548] = 2869069\n" +
                    "mask = 01X010100010X010X0011010X00111X1X101\n" +
                    "mem[35246] = 2055\n" +
                    "mem[49598] = 27236654\n" +
                    "mem[54450] = 12417324\n" +
                    "mask = 000111100X00XX1110100110X11111000100\n" +
                    "mem[53765] = 1230224\n" +
                    "mem[36868] = 22073\n" +
                    "mem[61947] = 7835\n" +
                    "mem[59391] = 13716798\n" +
                    "mask = 1X00X0110X1X1000X1010X00X00011X01011\n" +
                    "mem[24233] = 1256042\n" +
                    "mem[59296] = 727\n" +
                    "mem[4557] = 16231231\n" +
                    "mem[49269] = 709\n" +
                    "mem[53318] = 19717339\n" +
                    "mask = 001100X000111XX1111000X0110011000000\n" +
                    "mem[38984] = 485566\n" +
                    "mem[8772] = 60209\n" +
                    "mem[63054] = 28701\n" +
                    "mem[27333] = 1914744\n" +
                    "mask = 01001010XX10000X1XX1010X100110X10001\n" +
                    "mem[172] = 784382\n" +
                    "mem[6288] = 2731971\n" +
                    "mem[58427] = 452053\n" +
                    "mem[33321] = 2032017\n" +
                    "mem[15630] = 11599139\n" +
                    "mem[57814] = 5115\n" +
                    "mem[35321] = 1805174\n" +
                    "mask = X110001011101100XX1XX00XX111001X1001\n" +
                    "mem[58826] = 43646\n" +
                    "mem[19973] = 96879\n" +
                    "mem[58485] = 1959\n" +
                    "mem[3406] = 266922\n" +
                    "mask = 111000X00110100X01110X00000X10011X0X\n" +
                    "mem[37011] = 770073\n" +
                    "mem[1189] = 14250\n" +
                    "mem[59168] = 342478\n" +
                    "mask = 0X1X0010X1101X0X11100X10X0X110101001\n" +
                    "mem[59243] = 541548204\n" +
                    "mem[51995] = 2936054\n" +
                    "mem[54940] = 1083\n" +
                    "mem[1489] = 11851\n" +
                    "mask = 1X10101XX11011000XXX010001X100100101\n" +
                    "mem[17844] = 485564893\n" +
                    "mem[42085] = 2854515\n" +
                    "mem[63583] = 12308\n" +
                    "mask = 110X1X1XX00XX0001011000X01X011X01001\n" +
                    "mem[22224] = 287786\n" +
                    "mem[41145] = 462421\n" +
                    "mem[12943] = 12003321\n" +
                    "mem[40653] = 17727\n" +
                    "mask = 0X1000X001001001XX000110X0100011X000\n" +
                    "mem[36463] = 2219\n" +
                    "mem[4525] = 35150501\n" +
                    "mem[11082] = 416596\n" +
                    "mem[41839] = 742\n" +
                    "mem[9880] = 202\n" +
                    "mem[35339] = 2184\n" +
                    "mask = 0X101010011010001010X010XXX101X10XXX\n" +
                    "mem[36326] = 822859408\n" +
                    "mem[41561] = 12981317\n" +
                    "mem[40004] = 922864\n" +
                    "mem[24721] = 1909\n" +
                    "mem[41430] = 226954687\n" +
                    "mem[8435] = 50505503\n" +
                    "mask = 010X1X10101XX0011001010X100010011X11\n" +
                    "mem[9627] = 18804289\n" +
                    "mem[44473] = 407\n" +
                    "mem[52453] = 7941\n" +
                    "mem[14902] = 23033742\n" +
                    "mem[17568] = 752\n" +
                    "mem[49995] = 1028444\n" +
                    "mem[19310] = 41171\n" +
                    "mask = 100000110110110X01110010X10X0000X011\n" +
                    "mem[11582] = 761469\n" +
                    "mem[11244] = 652915\n" +
                    "mask = 0100101001101X00111X0111110X10X00X11\n" +
                    "mem[29328] = 18385\n" +
                    "mem[50749] = 12561174\n" +
                    "mem[16481] = 621\n" +
                    "mem[1445] = 4024359\n" +
                    "mem[51071] = 1565780\n" +
                    "mask = 111001110X00110000001100X0X110X01000\n" +
                    "mem[54560] = 7771727\n" +
                    "mem[12127] = 130749\n" +
                    "mem[53786] = 5107131\n" +
                    "mask = 0010001001X01X0XXXX0X1X011100101010X\n" +
                    "mem[32966] = 107063319\n" +
                    "mem[25108] = 92858\n" +
                    "mem[58943] = 4237759\n" +
                    "mem[37221] = 11660\n" +
                    "mem[10924] = 570740\n" +
                    "mask = 0010X0100X101X0X1110101X100X00011X00\n" +
                    "mem[11416] = 35520355\n" +
                    "mem[46117] = 10773\n" +
                    "mem[37881] = 169053\n" +
                    "mem[33541] = 59788\n" +
                    "mem[243] = 43667684\n" +
                    "mem[1477] = 11184382\n" +
                    "mem[59243] = 541\n" +
                    "mask = 01000010001XX1001110001X0X0111X00001\n" +
                    "mem[28475] = 5928\n" +
                    "mem[1108] = 537\n" +
                    "mem[36780] = 193\n" +
                    "mask = 01X0101101100X00101X10X0X000X11000X1\n" +
                    "mem[58943] = 13764399\n" +
                    "mem[45717] = 911292\n" +
                    "mem[42674] = 2558\n" +
                    "mask = X11010110111110X000101000X0X10000X00\n" +
                    "mem[32537] = 57475722\n" +
                    "mem[28681] = 256821\n" +
                    "mem[26361] = 308230106\n" +
                    "mem[18349] = 258650\n" +
                    "mem[5969] = 23347\n" +
                    "mask = 0100101001X01000111XX01100000X100001\n" +
                    "mem[56828] = 406\n" +
                    "mem[9369] = 7323117\n" +
                    "mem[35343] = 244027592\n" +
                    "mem[23840] = 6272\n" +
                    "mem[13713] = 2972196\n" +
                    "mask = 01X0X01X011010001010101X010000010000\n" +
                    "mem[24207] = 930\n" +
                    "mem[45424] = 360932935\n" +
                    "mem[47647] = 19392366\n" +
                    "mask = X10011X1010000X11X111X00XX10011000XX\n" +
                    "mem[42] = 2095\n" +
                    "mem[28509] = 3766\n" +
                    "mem[18872] = 2313\n" +
                    "mask = 1X11011X0110X01X11010X0X1001X10X1010\n" +
                    "mem[55143] = 12980\n" +
                    "mem[20673] = 71557183\n" +
                    "mem[3338] = 1189\n" +
                    "mem[40311] = 227599\n" +
                    "mask = X10010100110X000X11X1010X1011X011XXX\n" +
                    "mem[24179] = 57988\n" +
                    "mem[49269] = 992\n" +
                    "mem[9396] = 2258529\n" +
                    "mem[432] = 256498\n" +
                    "mem[58864] = 80\n" +
                    "mem[10265] = 127480\n" +
                    "mem[52476] = 629157\n" +
                    "mask = X1100XX1011X11100001000000100010101X\n" +
                    "mem[1929] = 13516293\n" +
                    "mem[20905] = 7502\n" +
                    "mask = 0XX0X01001101000111XX01XX1010011XX01\n" +
                    "mem[42409] = 43965\n" +
                    "mem[9475] = 312209148\n" +
                    "mem[35321] = 174593050\n" +
                    "mem[32779] = 2469855\n" +
                    "mem[9496] = 563512\n" +
                    "mask = 1100111X01000011101111X010000000XX01\n" +
                    "mem[35427] = 125642\n" +
                    "mem[62560] = 896\n" +
                    "mem[41510] = 71676\n" +
                    "mem[36305] = 2240642\n" +
                    "mem[50085] = 1429206\n" +
                    "mask = 1X0X001X011011000X110000010XX01X11X1\n" +
                    "mem[13869] = 364\n" +
                    "mem[10673] = 17499\n" +
                    "mem[33180] = 265864003\n" +
                    "mask = 0010X010X110X0001110X0X0XX010X011101\n" +
                    "mem[9461] = 165603\n" +
                    "mem[9529] = 2812\n" +
                    "mask = 110000XX011010000001X01001000011X101\n" +
                    "mem[34421] = 34973150\n" +
                    "mem[1445] = 2014053\n" +
                    "mem[40684] = 241767966\n" +
                    "mem[24781] = 1247\n" +
                    "mem[8560] = 229877\n" +
                    "mem[31494] = 231550\n" +
                    "mask = 01X011X10100001X11X110000000000X101X\n" +
                    "mem[3316] = 4081\n" +
                    "mem[35569] = 86527493\n" +
                    "mem[63610] = 1414\n" +
                    "mem[172] = 143067729\n" +
                    "mem[33771] = 6941839\n" +
                    "mem[41079] = 405178\n" +
                    "mask = 01001X1001X0000XXXX11010010000010000\n" +
                    "mem[65147] = 3820760\n" +
                    "mem[432] = 201\n" +
                    "mem[2594] = 251365376\n" +
                    "mem[39262] = 85007\n" +
                    "mem[41121] = 161455499\n" +
                    "mem[3465] = 1586\n" +
                    "mask = 101X01X101101010X1X1X00000010XXX1000\n" +
                    "mem[36170] = 421\n" +
                    "mem[23018] = 24486\n" +
                    "mem[9229] = 3646672\n" +
                    "mem[8818] = 107037\n" +
                    "mem[35681] = 1017769481\n" +
                    "mem[15932] = 280934662\n" +
                    "mask = 11100X1XX1101100000001XX0010X11X0010\n" +
                    "mem[3544] = 1152\n" +
                    "mem[54039] = 8885\n" +
                    "mem[13705] = 806210\n" +
                    "mem[39471] = 374\n" +
                    "mem[39257] = 843774\n" +
                    "mem[33244] = 710\n" +
                    "mask = 0X100010X1101X001X1010100X0110X0X101\n" +
                    "mem[62579] = 3307\n" +
                    "mem[11416] = 7171955\n" +
                    "mem[26846] = 1626218\n" +
                    "mem[33516] = 415278605\n" +
                    "mem[42890] = 124624\n" +
                    "mem[48782] = 5921065\n" +
                    "mem[29368] = 2498\n" +
                    "mask = 111X01X111101100000XX00110100XXX10X0\n" +
                    "mem[65353] = 829\n" +
                    "mem[5273] = 2859\n" +
                    "mem[64209] = 33340296\n" +
                    "mem[41121] = 15817\n" +
                    "mask = 0001001000XX100X1X10X10000100XX1000X\n" +
                    "mem[36211] = 60433375\n" +
                    "mem[4682] = 103202303\n" +
                    "mem[35065] = 3482590\n" +
                    "mem[8818] = 1505\n" +
                    "mask = 10X00X100110X10101101010111100X0100X\n" +
                    "mem[4064] = 777015\n" +
                    "mem[18349] = 6775\n" +
                    "mem[33336] = 19024\n" +
                    "mem[15126] = 251175380\n" +
                    "mask = 010X110011101100X1XX1010010001X1X0XX\n" +
                    "mem[52692] = 89617870\n" +
                    "mem[7896] = 830007\n" +
                    "mem[27108] = 63455766\n" +
                    "mem[14129] = 914743\n" +
                    "mem[23840] = 126434342\n" +
                    "mask = 110X00010110XX0X00XXX0011100X0001101\n" +
                    "mem[9845] = 10081\n" +
                    "mem[54100] = 25936890\n" +
                    "mask = X010X01001101X0001X001XX0110X1000101\n" +
                    "mem[17568] = 5998\n" +
                    "mem[43947] = 1799\n" +
                    "mem[36055] = 221472276\n" +
                    "mem[60090] = 66561756\n" +
                    "mask = XXX0111X011X01100001010100110X1X0010\n" +
                    "mem[37769] = 6706\n" +
                    "mem[46742] = 1262125\n" +
                    "mem[36984] = 390909337\n" +
                    "mem[3522] = 94940072\n" +
                    "mask = 0XX00X101X1000001X1X00X000X000110100\n" +
                    "mem[8217] = 4147\n" +
                    "mem[39919] = 161343033\n" +
                    "mem[36099] = 27783926\n" +
                    "mem[2365] = 3960\n" +
                    "mask = 01001010011X10000X10X1110X1000110110\n" +
                    "mem[26558] = 1974\n" +
                    "mem[36186] = 4141804\n" +
                    "mem[24744] = 606\n" +
                    "mem[33244] = 125499\n" +
                    "mask = 00X000100X1010X01110001X01X100X00001\n" +
                    "mem[8628] = 804744\n" +
                    "mem[18658] = 854076\n" +
                    "mem[4227] = 473623\n" +
                    "mask = XX001XX0X110XX0001110010010101011001\n" +
                    "mem[22486] = 602\n" +
                    "mem[57165] = 10237\n" +
                    "mem[55344] = 2051024\n" +
                    "mem[60836] = 328\n" +
                    "mem[35780] = 780686735\n" +
                    "mem[39471] = 102881\n" +
                    "mem[64410] = 241176\n" +
                    "mask = 00XXX110110000101X1X001X001101000XX0\n" +
                    "mem[274] = 5315\n" +
                    "mem[9665] = 9027290\n" +
                    "mem[16620] = 125\n" +
                    "mem[59735] = 3602\n" +
                    "mem[62085] = 49239\n" +
                    "mem[56780] = 9800895\n" +
                    "mem[54928] = 1137375\n" +
                    "mask = 01001010001000X01001X1000X0X1011111X\n" +
                    "mem[12171] = 14382682\n" +
                    "mem[54450] = 165243321\n" +
                    "mem[9425] = 111533929\n" +
                    "mem[45590] = 150122\n" +
                    "mem[30911] = 391886223\n" +
                    "mask = 11100111X11011X0X0010X01110X10110011\n" +
                    "mem[1489] = 313438\n" +
                    "mem[20783] = 6896\n" +
                    "mem[8251] = 37507033\n" +
                    "mask = X100000101101X000X000001111X11000101\n" +
                    "mem[11089] = 56810\n" +
                    "mem[14456] = 1959\n" +
                    "mem[43868] = 184311780\n" +
                    "mask = 1100XXX10010100X0X010000000010X00000\n" +
                    "mem[2248] = 28234\n" +
                    "mem[60165] = 9839303\n" +
                    "mem[6889] = 8698377\n" +
                    "mem[57303] = 313368\n" +
                    "mem[43759] = 882\n" +
                    "mem[2594] = 768\n" +
                    "mask = 10X0X11X011X010101100000000100010X00\n" +
                    "mem[37769] = 439851213\n" +
                    "mem[19914] = 2463\n" +
                    "mem[51322] = 2593\n" +
                    "mem[43396] = 377978334\n" +
                    "mem[38045] = 139890341\n" +
                    "mem[47741] = 43135075\n" +
                    "mem[44825] = 369245611\n" +
                    "mask = 1010X01011101100001X1110011000X0X101\n" +
                    "mem[42999] = 582608\n" +
                    "mem[40796] = 1848240\n" +
                    "mem[58209] = 81060\n" +
                    "mask = XX011110XX0000XX101X0001011X01X00000\n" +
                    "mem[13217] = 402886\n" +
                    "mem[62404] = 37548\n" +
                    "mem[19784] = 9686350\n" +
                    "mem[9615] = 278142\n" +
                    "mem[2253] = 566\n" +
                    "mask = 010X111X01000001X0110000X000001XX00X\n" +
                    "mem[44417] = 225360\n" +
                    "mem[49929] = 2093455\n" +
                    "mem[28757] = 2087678\n" +
                    "mem[52819] = 228\n" +
                    "mem[10194] = 7641\n" +
                    "mask = 010X111101000001XX11100XX01X0011X001\n" +
                    "mem[16696] = 532085\n" +
                    "mem[60755] = 12091\n" +
                    "mask = 0100X0100X10XX001110001001X110010001\n" +
                    "mem[10874] = 56734\n" +
                    "mem[32779] = 938\n" +
                    "mem[1876] = 80598882\n" +
                    "mask = X1110111111011000XX00X0X11010X00X0X0\n" +
                    "mem[15177] = 2738513\n" +
                    "mem[6543] = 233846741\n" +
                    "mem[60708] = 725\n" +
                    "mem[23008] = 2187\n" +
                    "mem[14757] = 52405813\n" +
                    "mem[13196] = 87819\n" +
                    "mask = X111XX11011X110000X10101000011001011\n" +
                    "mem[43091] = 754873\n" +
                    "mem[36326] = 226\n" +
                    "mem[37752] = 6466\n" +
                    "mask = 10100000X000111111101010X10X000X0X00\n" +
                    "mem[41658] = 796696872\n" +
                    "mem[50433] = 41752\n" +
                    "mem[59711] = 22834396\n" +
                    "mem[6860] = 70167341\n" +
                    "mem[3081] = 25830\n" +
                    "mem[13713] = 419002165\n" +
                    "mask = 11X00011011X010X00X1010101X100100000\n" +
                    "mem[57651] = 53575332\n" +
                    "mem[423] = 118\n" +
                    "mem[64209] = 73142089";

    private static final String[] EXAMPLE1_AS_ARRAY = EXAMPLE1.split("\\R");
    private static final String[] EXAMPLE2_AS_ARRAY = EXAMPLE2.split("\\R");
    private static final String[] INPUT_AS_ARRAY = INPUT.split("\\R");

    private static final String REGEX_MASK = "^mask = ([01X]{36})$";
    private static final Pattern PATTERN_MASK = Pattern.compile(REGEX_MASK);

    private static final String REGEX_MEM = "^mem\\[([0-9]+)] = ([0-9]+)$";
    private static final Pattern PATTERN_MEM = Pattern.compile(REGEX_MEM);

    private static final long ALL_POSSIBLE_BITS = (2L << 36L) - 1;

    public static class MaskPuzzle1 {

        /**
         * Here, all bits are true where there are ones in the bitmask.
         */
        private final long ones;

        /**
         * Here, all bits are true where there are zeroes in the bitmask.
         */
        private final long zeroes;

        public MaskPuzzle1(long ones, long zeroes) {
            this.ones = ones;
            this.zeroes = zeroes;
        }

        public static MaskPuzzle1 fromString(String input) {
            String onesString = input.replace("X", "0");
            long ones = parseLong(onesString, 2);

            String zeroesString = input.replace("1", "X")
                                       .replace("0", "1")
                                       .replace("X", "0");
            long zeroes = parseLong(zeroesString, 2);

            return new MaskPuzzle1(ones, zeroes);
        }

        public long applyTo(long input) {
            return (input | ones) & (~zeroes);
        }
    }

    public static class MaskPuzzle2 {

        /**
         * Here, all bits are true where there are ones in the bitmask.
         */
        private final long ones;

        /**
         * Here, all bits are true where there are floating bits in the bitmask.
         */
        private final long floating;

        private final Long[] possibleValuesForFloating;

        public MaskPuzzle2(long ones, long floating, Long[] possibleValuesForFloating) {
            this.ones = ones;
            this.floating = floating;
            this.possibleValuesForFloating = possibleValuesForFloating;
        }

        public static MaskPuzzle2 fromString(String input) {
            String onesString = input.replace("X", "0");
            long ones = parseLong(onesString, 2);

            String floatingString = input.replace("1", "0")
                                         .replace("X", "1");
            long floating = parseLong(floatingString, 2);

            return new MaskPuzzle2(ones, floating, generatePossibleValuesForFloating(input));
        }

        private static Long[] generatePossibleValuesForFloating(String input) {
            input = input.replace("1", "0");
            Set<Long> values = new HashSet<>();
            addAllPossibleValuesForFloatingToSet(values, input);
            return values.toArray(new Long[0]);
        }

        private static void addAllPossibleValuesForFloatingToSet(Set<Long> values, String input) {
            if (!input.matches("^[01X]{36}$")) {
                throw new IllegalArgumentException("Input has unknown structure: '" + input + "'.");
            }
            if (!input.contains("X")) {
                values.add(parseLong(input, 2));
            } else {
                int pos = input.indexOf("X");
                String before = input.substring(0, pos);
                String after = input.substring(pos + 1);
                addAllPossibleValuesForFloatingToSet(values, before + "0" + after);
                addAllPossibleValuesForFloatingToSet(values, before + "1" + after);
            }
        }

        public Long[] applyTo(long input) {
            input = input | ones;
            Set<Long> values = new HashSet<>();
            for (long l : possibleValuesForFloating) {
                long tmpVal = l | (input & (~floating));
                values.add(tmpVal);
            }
            return values.toArray(new Long[0]);
        }
    }

    private static Map<Long, Long> interpretPuzzle1(String[] commands) {
        Map<Long, Long> ram = new HashMap<>();
        MaskPuzzle1 currentMaskPuzzle1 = new MaskPuzzle1(0, 0);

        for (String command : commands) {
            Matcher maskMatch = PATTERN_MASK.matcher(command);
            Matcher memMatch = PATTERN_MEM.matcher(command);

            if (maskMatch.matches()) {
                currentMaskPuzzle1 = MaskPuzzle1.fromString(maskMatch.group(1));
            } else if (memMatch.matches()) {
                long address = parseLong(memMatch.group(1));
                long rawValue = parseLong(memMatch.group(2));
                // Make sure that only the lowest 36 bits are set
                address = address & ALL_POSSIBLE_BITS;
                rawValue = rawValue & ALL_POSSIBLE_BITS;
                long value = currentMaskPuzzle1.applyTo(rawValue);
                ram.put(address, value);
            } else {
                throw new IllegalArgumentException("Command has unknown structure: '" + command + "'.");
            }
        }
        return ram;
    }

    private static Map<Long, Long> interpretPuzzle2(String[] commands) {
        Map<Long, Long> ram = new HashMap<>();
        MaskPuzzle2 currentMaskPuzzle2 = new MaskPuzzle2(0, 0, new Long[0]);

        for (String command : commands) {
            Matcher maskMatch = PATTERN_MASK.matcher(command);
            Matcher memMatch = PATTERN_MEM.matcher(command);

            if (maskMatch.matches()) {
                currentMaskPuzzle2 = MaskPuzzle2.fromString(maskMatch.group(1));
            } else if (memMatch.matches()) {
                long rawAddress = parseLong(memMatch.group(1));
                long value = parseLong(memMatch.group(2));
                // Make sure that only the lowest 36 bits are set
                rawAddress = rawAddress & ALL_POSSIBLE_BITS;
                value = value & ALL_POSSIBLE_BITS;
                Long[] adresses = currentMaskPuzzle2.applyTo(rawAddress);
                for (Long address : adresses) {
                    ram.put(address, value);
                }
            } else {
                throw new IllegalArgumentException("Command has unknown structure: '" + command + "'.");
            }
        }
        return ram;
    }

    private static long sumAllRamCells(Map<Long, Long> ram) {
        long accu = 0;
        for (Map.Entry<Long, Long> entry : ram.entrySet()) {
            accu += entry.getValue();
        }
        return accu;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 14: Examples for puzzle 1 ###");
        Map<Long, Long> ram = interpretPuzzle1(EXAMPLE1_AS_ARRAY);
        System.out.println("Sum of all RAM cells in example 1: " + sumAllRamCells(ram));
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 14: Examples for puzzle 2 ###");
        Map<Long, Long> ram = interpretPuzzle2(EXAMPLE2_AS_ARRAY);
        System.out.println("Sum of all RAM cells in example 2: " + sumAllRamCells(ram));
    }

    public static long doPuzzle1() {
        Map<Long, Long> ram = interpretPuzzle1(INPUT_AS_ARRAY);
        return sumAllRamCells(ram);
    }

    public static long doPuzzle2() {
        Map<Long, Long> ram = interpretPuzzle2(INPUT_AS_ARRAY);
        return sumAllRamCells(ram);
    }
}
