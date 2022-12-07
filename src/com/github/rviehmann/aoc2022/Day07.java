package com.github.rviehmann.aoc2022;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public class Day07 {

    private static final String SAMPLE_INPUT =
            "$ cd /\n" +
                    "$ ls\n" +
                    "dir a\n" +
                    "14848514 b.txt\n" +
                    "8504156 c.dat\n" +
                    "dir d\n" +
                    "$ cd a\n" +
                    "$ ls\n" +
                    "dir e\n" +
                    "29116 f\n" +
                    "2557 g\n" +
                    "62596 h.lst\n" +
                    "$ cd e\n" +
                    "$ ls\n" +
                    "584 i\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd d\n" +
                    "$ ls\n" +
                    "4060174 j\n" +
                    "8033020 d.log\n" +
                    "5626152 d.ext\n" +
                    "7214296 k";

    private static final String REAL_INPUT =
            "$ cd /\n" +
                    "$ ls\n" +
                    "dir cmvqf\n" +
                    "dir dcgbjvj\n" +
                    "57426 gszshjwr.lrs\n" +
                    "dir nsgms\n" +
                    "124423 rjqns.prb\n" +
                    "dir wqvv\n" +
                    "$ cd cmvqf\n" +
                    "$ ls\n" +
                    "6852 cnsb.cmm\n" +
                    "319810 cwqbmjb.vpl\n" +
                    "dir dcgbjvj\n" +
                    "dir ddnclwtd\n" +
                    "dir gccnrw\n" +
                    "dir qwzphd\n" +
                    "dir rvqwnjv\n" +
                    "dir ssmf\n" +
                    "107040 trttdw.jsn\n" +
                    "dir wcn\n" +
                    "296426 wqvv\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "dir dcgbjvj\n" +
                    "dir rlvcvj\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "214674 gsqcwfmz.hlm\n" +
                    "$ cd ..\n" +
                    "$ cd rlvcvj\n" +
                    "$ ls\n" +
                    "151752 cnsb.cmm\n" +
                    "256829 sjlwgf.mqn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ddnclwtd\n" +
                    "$ ls\n" +
                    "177893 fpwznlp.zsf\n" +
                    "$ cd ..\n" +
                    "$ cd gccnrw\n" +
                    "$ ls\n" +
                    "dir mbfw\n" +
                    "dir rlvcvj\n" +
                    "dir wsrdh\n" +
                    "dir wvq\n" +
                    "dir zgpdl\n" +
                    "$ cd mbfw\n" +
                    "$ ls\n" +
                    "dir dcgbjvj\n" +
                    "dir mhnjvrl\n" +
                    "271166 ptrv\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "dir npjmq\n" +
                    "$ cd npjmq\n" +
                    "$ ls\n" +
                    "26712 fpwznlp.zsf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mhnjvrl\n" +
                    "$ ls\n" +
                    "190094 mgrdrbl.lqg\n" +
                    "199191 zgczmvng\n" +
                    "22082 zgczmvng.rld\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rlvcvj\n" +
                    "$ ls\n" +
                    "244617 mbjprm\n" +
                    "264738 wpgglg\n" +
                    "$ cd ..\n" +
                    "$ cd wsrdh\n" +
                    "$ ls\n" +
                    "dir mgmp\n" +
                    "111558 vnqmnjpb.bnc\n" +
                    "$ cd mgmp\n" +
                    "$ ls\n" +
                    "dir whzjb\n" +
                    "$ cd whzjb\n" +
                    "$ ls\n" +
                    "235442 mgrdrbl.lqg\n" +
                    "63642 sphms.tzw\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wvq\n" +
                    "$ ls\n" +
                    "23240 dcgbjvj.rwc\n" +
                    "79015 hcb\n" +
                    "155120 jjc\n" +
                    "dir wqvv\n" +
                    "207559 wqvv.cwp\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "130961 cnsb.cmm\n" +
                    "dir fcl\n" +
                    "208524 hgbr.snf\n" +
                    "dir lzs\n" +
                    "14868 mgrdrbl.lqg\n" +
                    "dir sqpgtrn\n" +
                    "143653 zgczmvng\n" +
                    "$ cd fcl\n" +
                    "$ ls\n" +
                    "dir jfjgnz\n" +
                    "$ cd jfjgnz\n" +
                    "$ ls\n" +
                    "225416 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd lzs\n" +
                    "$ ls\n" +
                    "111949 vtcmf\n" +
                    "$ cd ..\n" +
                    "$ cd sqpgtrn\n" +
                    "$ ls\n" +
                    "289955 cnsb.cmm\n" +
                    "dir crstpjjv\n" +
                    "dir dcgbjvj\n" +
                    "6334 nwv.blw\n" +
                    "dir vpnhcsfr\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "144739 jtcndb.wht\n" +
                    "16215 qdccst.dsg\n" +
                    "$ cd ..\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "dir bnzmws\n" +
                    "dir crstpjjv\n" +
                    "82495 fjmbgql\n" +
                    "248051 hlcwhnf\n" +
                    "145452 qwzjc.sth\n" +
                    "dir spmr\n" +
                    "268967 wqvv\n" +
                    "23371 wqvv.vdm\n" +
                    "dir zgczmvng\n" +
                    "$ cd bnzmws\n" +
                    "$ ls\n" +
                    "dir dcgbjvj\n" +
                    "dir dgbtqdn\n" +
                    "119626 pvgrqjf.ftq\n" +
                    "204879 tscrpt.szt\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "152171 msf.qhf\n" +
                    "$ cd ..\n" +
                    "$ cd dgbtqdn\n" +
                    "$ ls\n" +
                    "64965 fpwznlp.zsf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "97804 mgrdrbl.lqg\n" +
                    "88837 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd spmr\n" +
                    "$ ls\n" +
                    "302501 dcgbjvj\n" +
                    "$ cd ..\n" +
                    "$ cd zgczmvng\n" +
                    "$ ls\n" +
                    "dir crstpjjv\n" +
                    "187957 prznqbn\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "218211 jlb.nvs\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd vpnhcsfr\n" +
                    "$ ls\n" +
                    "220411 qtcdjgz\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zgpdl\n" +
                    "$ ls\n" +
                    "dir llstdpv\n" +
                    "dir rtftjm\n" +
                    "$ cd llstdpv\n" +
                    "$ ls\n" +
                    "318556 qqccwwjf.mbw\n" +
                    "$ cd ..\n" +
                    "$ cd rtftjm\n" +
                    "$ ls\n" +
                    "117705 fwphh.zrz\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd qwzphd\n" +
                    "$ ls\n" +
                    "dir crstpjjv\n" +
                    "dir fvfmlgql\n" +
                    "dir ldbts\n" +
                    "dir ljtcgz\n" +
                    "dir llvhbzpz\n" +
                    "dir plcbgmwc\n" +
                    "dir pwp\n" +
                    "dir qjstb\n" +
                    "58078 wmc\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "171196 sbf.vcc\n" +
                    "320608 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd fvfmlgql\n" +
                    "$ ls\n" +
                    "dir hfdnml\n" +
                    "298497 trttdw.jsn\n" +
                    "$ cd hfdnml\n" +
                    "$ ls\n" +
                    "43441 crstpjjv.vrr\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ldbts\n" +
                    "$ ls\n" +
                    "211746 crstpjjv\n" +
                    "224627 rcw.rcl\n" +
                    "$ cd ..\n" +
                    "$ cd ljtcgz\n" +
                    "$ ls\n" +
                    "dir dfrnh\n" +
                    "179456 fmbpcdbd.vrl\n" +
                    "141254 fpwznlp.zsf\n" +
                    "86291 pcmqcl.jmz\n" +
                    "266763 pzvg.qcg\n" +
                    "dir zjjbjn\n" +
                    "$ cd dfrnh\n" +
                    "$ ls\n" +
                    "dir crstpjjv\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "220983 wqvv.hhn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zjjbjn\n" +
                    "$ ls\n" +
                    "215454 nwcbbv.mbb\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd llvhbzpz\n" +
                    "$ ls\n" +
                    "206731 cnsb.cmm\n" +
                    "$ cd ..\n" +
                    "$ cd plcbgmwc\n" +
                    "$ ls\n" +
                    "223141 fpwznlp.zsf\n" +
                    "dir hplrsb\n" +
                    "309856 jhdwr.jfc\n" +
                    "dir mhmnmd\n" +
                    "218364 mmfzhj.zvg\n" +
                    "dir nwnj\n" +
                    "316432 trttdw.jsn\n" +
                    "dir vrgj\n" +
                    "$ cd hplrsb\n" +
                    "$ ls\n" +
                    "dir lbscwd\n" +
                    "dir lsffhj\n" +
                    "dir mlfp\n" +
                    "dir pqfbf\n" +
                    "dir tcvjzzhj\n" +
                    "dir wqvv\n" +
                    "$ cd lbscwd\n" +
                    "$ ls\n" +
                    "157261 wvblz.hmp\n" +
                    "$ cd ..\n" +
                    "$ cd lsffhj\n" +
                    "$ ls\n" +
                    "171621 crstpjjv\n" +
                    "$ cd ..\n" +
                    "$ cd mlfp\n" +
                    "$ ls\n" +
                    "80994 vvjzm.pzt\n" +
                    "$ cd ..\n" +
                    "$ cd pqfbf\n" +
                    "$ ls\n" +
                    "67861 ltd.zbw\n" +
                    "dir nnsg\n" +
                    "dir nwcl\n" +
                    "107828 rlvcvj\n" +
                    "160956 trttdw.jsn\n" +
                    "$ cd nnsg\n" +
                    "$ ls\n" +
                    "18252 tzcrqv.rsr\n" +
                    "$ cd ..\n" +
                    "$ cd nwcl\n" +
                    "$ ls\n" +
                    "38378 cnsb.cmm\n" +
                    "217283 dqwpwzz\n" +
                    "220081 mgrdrbl.lqg\n" +
                    "28106 sbf.vcc\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd tcvjzzhj\n" +
                    "$ ls\n" +
                    "152965 dhv\n" +
                    "316034 gvtdrj.rft\n" +
                    "$ cd ..\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "281962 mfzf.nfn\n" +
                    "95321 rlvcvj.zwf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mhmnmd\n" +
                    "$ ls\n" +
                    "213145 cnsb.cmm\n" +
                    "dir hnzcz\n" +
                    "273060 mnwhg\n" +
                    "dir qcwdvq\n" +
                    "318596 trttdw.jsn\n" +
                    "$ cd hnzcz\n" +
                    "$ ls\n" +
                    "177795 fpwznlp.zsf\n" +
                    "188898 rlvcvj\n" +
                    "317234 wqvv.jsv\n" +
                    "dir zgczmvng\n" +
                    "$ cd zgczmvng\n" +
                    "$ ls\n" +
                    "dir qzfw\n" +
                    "$ cd qzfw\n" +
                    "$ ls\n" +
                    "134097 rlvcvj\n" +
                    "73145 sbf.vcc\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd qcwdvq\n" +
                    "$ ls\n" +
                    "83084 wqvv\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd nwnj\n" +
                    "$ ls\n" +
                    "84366 hgpmqh\n" +
                    "317603 mgrdrbl.lqg\n" +
                    "$ cd ..\n" +
                    "$ cd vrgj\n" +
                    "$ ls\n" +
                    "136595 fpwznlp.zsf\n" +
                    "78517 sbf.vcc\n" +
                    "dir wqvv\n" +
                    "242465 wqvv.cpl\n" +
                    "dir zln\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "3191 sbf.vcc\n" +
                    "$ cd ..\n" +
                    "$ cd zln\n" +
                    "$ ls\n" +
                    "dir crstpjjv\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "86511 btqgw\n" +
                    "17597 rcstn.jpj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd pwp\n" +
                    "$ ls\n" +
                    "dir crstpjjv\n" +
                    "dir jlqdbv\n" +
                    "290915 mgrdrbl.lqg\n" +
                    "219909 nfgj\n" +
                    "207313 sbf.vcc\n" +
                    "dir zpjf\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "298992 crnfs.fgn\n" +
                    "172934 jqh\n" +
                    "$ cd ..\n" +
                    "$ cd jlqdbv\n" +
                    "$ ls\n" +
                    "300436 zgczmvng\n" +
                    "$ cd ..\n" +
                    "$ cd zpjf\n" +
                    "$ ls\n" +
                    "78904 sbf.vcc\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd qjstb\n" +
                    "$ ls\n" +
                    "dir dtb\n" +
                    "dir gcd\n" +
                    "dir gmcnhh\n" +
                    "85552 htm.lzc\n" +
                    "219773 mzb.fvt\n" +
                    "208419 schz\n" +
                    "$ cd dtb\n" +
                    "$ ls\n" +
                    "167147 crstpjjv.zlb\n" +
                    "$ cd ..\n" +
                    "$ cd gcd\n" +
                    "$ ls\n" +
                    "239595 rlvcvj\n" +
                    "$ cd ..\n" +
                    "$ cd gmcnhh\n" +
                    "$ ls\n" +
                    "111653 fpwznlp.zsf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rvqwnjv\n" +
                    "$ ls\n" +
                    "59215 cnsb.cmm\n" +
                    "37164 jtlcr.rlm\n" +
                    "dir mnc\n" +
                    "$ cd mnc\n" +
                    "$ ls\n" +
                    "32159 cnsb.cmm\n" +
                    "76204 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ssmf\n" +
                    "$ ls\n" +
                    "dir fvcd\n" +
                    "127458 hpdzv\n" +
                    "dir jcg\n" +
                    "288242 jtjp.mjj\n" +
                    "dir jzp\n" +
                    "268857 mgrdrbl.lqg\n" +
                    "223968 nhfmbvc\n" +
                    "dir wqvv\n" +
                    "235806 wqvv.fnl\n" +
                    "$ cd fvcd\n" +
                    "$ ls\n" +
                    "26479 wcs.bdp\n" +
                    "$ cd ..\n" +
                    "$ cd jcg\n" +
                    "$ ls\n" +
                    "dir nsvtrs\n" +
                    "dir zgczmvng\n" +
                    "$ cd nsvtrs\n" +
                    "$ ls\n" +
                    "dir dcgbjvj\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "36633 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zgczmvng\n" +
                    "$ ls\n" +
                    "221381 vszcg.jdb\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd jzp\n" +
                    "$ ls\n" +
                    "1957 dcgbjvj\n" +
                    "$ cd ..\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "9330 wqvv.pvs\n" +
                    "46963 ztlh\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wcn\n" +
                    "$ ls\n" +
                    "dir cqdzdnq\n" +
                    "dir cszzg\n" +
                    "dir fqmcr\n" +
                    "123361 pjfdtvzf.rdf\n" +
                    "dir rmrg\n" +
                    "dir rsfddzs\n" +
                    "dir vqrpdwv\n" +
                    "dir wpgddhdq\n" +
                    "dir wpgv\n" +
                    "$ cd cqdzdnq\n" +
                    "$ ls\n" +
                    "dir dqhpbsg\n" +
                    "dir qlq\n" +
                    "dir vfwhcpwl\n" +
                    "dir wqvv\n" +
                    "dir zpbbspcv\n" +
                    "$ cd dqhpbsg\n" +
                    "$ ls\n" +
                    "245289 glbfq.vpw\n" +
                    "51357 vsvvzbns.ftf\n" +
                    "$ cd ..\n" +
                    "$ cd qlq\n" +
                    "$ ls\n" +
                    "210318 mqgnjht.vqq\n" +
                    "$ cd ..\n" +
                    "$ cd vfwhcpwl\n" +
                    "$ ls\n" +
                    "109892 mmpzcjmp.znn\n" +
                    "$ cd ..\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "dir chwdzfsg\n" +
                    "dir crstpjjv\n" +
                    "dir dcgbjvj\n" +
                    "dir rllbccjt\n" +
                    "dir rlvcvj\n" +
                    "$ cd chwdzfsg\n" +
                    "$ ls\n" +
                    "108951 fpwznlp.zsf\n" +
                    "dir vgc\n" +
                    "$ cd vgc\n" +
                    "$ ls\n" +
                    "273011 fpwznlp.zsf\n" +
                    "248078 ntc.ghp\n" +
                    "77305 thgbb.mfn\n" +
                    "73383 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "dir qmswb\n" +
                    "$ cd qmswb\n" +
                    "$ ls\n" +
                    "68252 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "111 qtcs.llc\n" +
                    "dir szzthsmj\n" +
                    "$ cd szzthsmj\n" +
                    "$ ls\n" +
                    "dir qhztdv\n" +
                    "dir wqvv\n" +
                    "dir zmqrftlm\n" +
                    "$ cd qhztdv\n" +
                    "$ ls\n" +
                    "138433 qrdsrrb.chw\n" +
                    "dir rlvcvj\n" +
                    "$ cd rlvcvj\n" +
                    "$ ls\n" +
                    "dir jfcm\n" +
                    "dir lzf\n" +
                    "249984 rlvcvj.nmb\n" +
                    "dir twrs\n" +
                    "$ cd jfcm\n" +
                    "$ ls\n" +
                    "117884 dtrc.wsm\n" +
                    "237577 rlvcvj.mhd\n" +
                    "$ cd ..\n" +
                    "$ cd lzf\n" +
                    "$ ls\n" +
                    "60342 nrc.clh\n" +
                    "$ cd ..\n" +
                    "$ cd twrs\n" +
                    "$ ls\n" +
                    "97201 cnsb.cmm\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "73047 gcqzjf.gcb\n" +
                    "$ cd ..\n" +
                    "$ cd zmqrftlm\n" +
                    "$ ls\n" +
                    "dir ltd\n" +
                    "$ cd ltd\n" +
                    "$ ls\n" +
                    "120673 fpwznlp.zsf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rllbccjt\n" +
                    "$ ls\n" +
                    "299611 cnsb.cmm\n" +
                    "dir lnfvlqh\n" +
                    "36418 qwh\n" +
                    "dir rlvcvj\n" +
                    "255907 trttdw.jsn\n" +
                    "dir zqvzpv\n" +
                    "$ cd lnfvlqh\n" +
                    "$ ls\n" +
                    "195010 fpwznlp.zsf\n" +
                    "72496 llrznf.rwc\n" +
                    "dir lvgzb\n" +
                    "53126 mgrdrbl.lqg\n" +
                    "90191 mnrqtn\n" +
                    "310156 nthdm.crh\n" +
                    "$ cd lvgzb\n" +
                    "$ ls\n" +
                    "209837 mgrdrbl.lqg\n" +
                    "210074 rwhgmd\n" +
                    "70338 sbf.vcc\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rlvcvj\n" +
                    "$ ls\n" +
                    "252080 dcgbjvj\n" +
                    "$ cd ..\n" +
                    "$ cd zqvzpv\n" +
                    "$ ls\n" +
                    "249229 zpt.lbc\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rlvcvj\n" +
                    "$ ls\n" +
                    "dir dcgbjvj\n" +
                    "dir fdz\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "191363 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd fdz\n" +
                    "$ ls\n" +
                    "291107 bqsdfc.rcn\n" +
                    "dir dcgbjvj\n" +
                    "64333 fpwznlp.zsf\n" +
                    "dir lfb\n" +
                    "280608 mgrdrbl.lqg\n" +
                    "125554 trttdw.jsn\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "169326 qwjhpdh\n" +
                    "$ cd ..\n" +
                    "$ cd lfb\n" +
                    "$ ls\n" +
                    "35299 zqfnjtr.clt\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zpbbspcv\n" +
                    "$ ls\n" +
                    "54549 mgrdrbl.lqg\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd cszzg\n" +
                    "$ ls\n" +
                    "dir wqvv\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "136042 crstpjjv.jtq\n" +
                    "10879 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd fqmcr\n" +
                    "$ ls\n" +
                    "188798 bchvt.dvw\n" +
                    "276819 fpwznlp.zsf\n" +
                    "dir gdr\n" +
                    "dir rlvcvj\n" +
                    "5623 zgczmvng.fqs\n" +
                    "158621 znddbv\n" +
                    "$ cd gdr\n" +
                    "$ ls\n" +
                    "dir btg\n" +
                    "213096 cnsb.cmm\n" +
                    "dir dhbcmzbz\n" +
                    "$ cd btg\n" +
                    "$ ls\n" +
                    "dir hjm\n" +
                    "$ cd hjm\n" +
                    "$ ls\n" +
                    "144774 zgczmvng.llz\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd dhbcmzbz\n" +
                    "$ ls\n" +
                    "148108 rlvcvj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rlvcvj\n" +
                    "$ ls\n" +
                    "201103 qrdlf.pvg\n" +
                    "272776 vnpgw.wts\n" +
                    "153826 zgczmvng\n" +
                    "290248 zgczmvng.gsv\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rmrg\n" +
                    "$ ls\n" +
                    "dir jzb\n" +
                    "dir nsslsw\n" +
                    "dir rlvcvj\n" +
                    "$ cd jzb\n" +
                    "$ ls\n" +
                    "273968 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd nsslsw\n" +
                    "$ ls\n" +
                    "226370 sbf.vcc\n" +
                    "$ cd ..\n" +
                    "$ cd rlvcvj\n" +
                    "$ ls\n" +
                    "294706 gsbqswjj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd rsfddzs\n" +
                    "$ ls\n" +
                    "dir cphvtp\n" +
                    "205384 crstpjjv\n" +
                    "82103 dfrjwrnz.bfl\n" +
                    "dir fntvngpm\n" +
                    "297145 pqtrvd\n" +
                    "237572 sbf.vcc\n" +
                    "dir zgczmvng\n" +
                    "$ cd cphvtp\n" +
                    "$ ls\n" +
                    "dir phvc\n" +
                    "$ cd phvc\n" +
                    "$ ls\n" +
                    "52239 dcgbjvj.lbj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd fntvngpm\n" +
                    "$ ls\n" +
                    "18297 mgrdrbl.lqg\n" +
                    "$ cd ..\n" +
                    "$ cd zgczmvng\n" +
                    "$ ls\n" +
                    "dir lzcwf\n" +
                    "dir pqmc\n" +
                    "179956 tzqjcn\n" +
                    "dir zgczmvng\n" +
                    "$ cd lzcwf\n" +
                    "$ ls\n" +
                    "284166 cnsb.cmm\n" +
                    "157214 jhmmn.qwn\n" +
                    "$ cd ..\n" +
                    "$ cd pqmc\n" +
                    "$ ls\n" +
                    "215883 nlvdqw.jmt\n" +
                    "dir qjfr\n" +
                    "209722 wqvv.fgg\n" +
                    "$ cd qjfr\n" +
                    "$ ls\n" +
                    "53013 mgrdrbl.lqg\n" +
                    "191236 sgmnjc\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zgczmvng\n" +
                    "$ ls\n" +
                    "260649 dglqpjs\n" +
                    "141213 mgrdrbl.lqg\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd vqrpdwv\n" +
                    "$ ls\n" +
                    "dir ftw\n" +
                    "150895 mgrdrbl.lqg\n" +
                    "227641 nbrzfl.dpf\n" +
                    "dir nwjdnpdd\n" +
                    "$ cd ftw\n" +
                    "$ ls\n" +
                    "99672 dbsgvvbp\n" +
                    "dir dnzld\n" +
                    "146730 mgrdrbl.lqg\n" +
                    "$ cd dnzld\n" +
                    "$ ls\n" +
                    "37598 bhjbfl.svw\n" +
                    "dir qspsslt\n" +
                    "$ cd qspsslt\n" +
                    "$ ls\n" +
                    "dir vhgpwvf\n" +
                    "$ cd vhgpwvf\n" +
                    "$ ls\n" +
                    "146936 ghgl\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd nwjdnpdd\n" +
                    "$ ls\n" +
                    "46000 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wpgddhdq\n" +
                    "$ ls\n" +
                    "dir rqgf\n" +
                    "$ cd rqgf\n" +
                    "$ ls\n" +
                    "197374 rlvcvj.fmr\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wpgv\n" +
                    "$ ls\n" +
                    "dir fdh\n" +
                    "286086 fpwznlp.zsf\n" +
                    "dir pljq\n" +
                    "258062 wqvv\n" +
                    "dir zgczmvng\n" +
                    "$ cd fdh\n" +
                    "$ ls\n" +
                    "76173 fpwznlp.zsf\n" +
                    "230947 nczhtpcn\n" +
                    "62630 rlvcvj\n" +
                    "$ cd ..\n" +
                    "$ cd pljq\n" +
                    "$ ls\n" +
                    "dir mzmm\n" +
                    "41117 rjms.dcg\n" +
                    "$ cd mzmm\n" +
                    "$ ls\n" +
                    "144202 zgczmvng.ttl\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zgczmvng\n" +
                    "$ ls\n" +
                    "dir dncr\n" +
                    "dir mcdmfdp\n" +
                    "dir pgqglmj\n" +
                    "dir qldrmn\n" +
                    "$ cd dncr\n" +
                    "$ ls\n" +
                    "198052 dcgbjvj\n" +
                    "dir dqdgft\n" +
                    "dir hpmwvnsr\n" +
                    "2829 rlvcvj.qwg\n" +
                    "$ cd dqdgft\n" +
                    "$ ls\n" +
                    "dir fng\n" +
                    "dir nlsmb\n" +
                    "$ cd fng\n" +
                    "$ ls\n" +
                    "198899 zgczmvng\n" +
                    "$ cd ..\n" +
                    "$ cd nlsmb\n" +
                    "$ ls\n" +
                    "257121 gmr.vmg\n" +
                    "9276 zsmd.bng\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd hpmwvnsr\n" +
                    "$ ls\n" +
                    "241101 jjwqbwl.fpl\n" +
                    "64151 wqvv\n" +
                    "196139 zgczmvng\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mcdmfdp\n" +
                    "$ ls\n" +
                    "276856 lrgbhq\n" +
                    "$ cd ..\n" +
                    "$ cd pgqglmj\n" +
                    "$ ls\n" +
                    "36476 fpwznlp.zsf\n" +
                    "$ cd ..\n" +
                    "$ cd qldrmn\n" +
                    "$ ls\n" +
                    "295686 trttdw.jsn\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "dir brdfvd\n" +
                    "dir crstpjjv\n" +
                    "dir fzdqcgv\n" +
                    "dir fzw\n" +
                    "dir mrllpw\n" +
                    "dir wnh\n" +
                    "119561 zgczmvng.jsm\n" +
                    "$ cd brdfvd\n" +
                    "$ ls\n" +
                    "272932 mhdjc.mng\n" +
                    "dir wqvv\n" +
                    "91053 zgczmvng.jwg\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "dir bpplph\n" +
                    "121367 jhlqfn.sbs\n" +
                    "61760 nsgbt\n" +
                    "46653 sbf.vcc\n" +
                    "16952 trttdw.jsn\n" +
                    "$ cd bpplph\n" +
                    "$ ls\n" +
                    "1288 rlvcvj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "117722 sbf.vcc\n" +
                    "$ cd ..\n" +
                    "$ cd fzdqcgv\n" +
                    "$ ls\n" +
                    "289819 fpwznlp.zsf\n" +
                    "$ cd ..\n" +
                    "$ cd fzw\n" +
                    "$ ls\n" +
                    "dir dcgbjvj\n" +
                    "dir zsfqwdth\n" +
                    "dir zswdl\n" +
                    "$ cd dcgbjvj\n" +
                    "$ ls\n" +
                    "272427 cnsb.cmm\n" +
                    "$ cd ..\n" +
                    "$ cd zsfqwdth\n" +
                    "$ ls\n" +
                    "dir bdbgtqjj\n" +
                    "dir hcgrqbhl\n" +
                    "$ cd bdbgtqjj\n" +
                    "$ ls\n" +
                    "318980 tnqmspdf.cwd\n" +
                    "$ cd ..\n" +
                    "$ cd hcgrqbhl\n" +
                    "$ ls\n" +
                    "135307 fpwznlp.zsf\n" +
                    "dir wqvv\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "68708 ctz.wms\n" +
                    "149578 wlvdrfsw.qcj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zswdl\n" +
                    "$ ls\n" +
                    "259754 cnsb.cmm\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd mrllpw\n" +
                    "$ ls\n" +
                    "44007 tvsm\n" +
                    "$ cd ..\n" +
                    "$ cd wnh\n" +
                    "$ ls\n" +
                    "dir mjnrmb\n" +
                    "156515 wpdhq.hvp\n" +
                    "$ cd mjnrmb\n" +
                    "$ ls\n" +
                    "293592 fpwznlp.zsf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd nsgms\n" +
                    "$ ls\n" +
                    "dir dnhzj\n" +
                    "dir ptc\n" +
                    "dir tnfrr\n" +
                    "dir vjt\n" +
                    "32152 zgczmvng.wmt\n" +
                    "$ cd dnhzj\n" +
                    "$ ls\n" +
                    "281195 flqbvrw.gmf\n" +
                    "177042 jjsfrmc.drz\n" +
                    "$ cd ..\n" +
                    "$ cd ptc\n" +
                    "$ ls\n" +
                    "dir gjvnrcln\n" +
                    "290797 pccmrnn\n" +
                    "59802 rzl.tjm\n" +
                    "dir zgczmvng\n" +
                    "$ cd gjvnrcln\n" +
                    "$ ls\n" +
                    "dir jph\n" +
                    "$ cd jph\n" +
                    "$ ls\n" +
                    "105648 hflqlwr.mph\n" +
                    "158151 hmlqsp\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd zgczmvng\n" +
                    "$ ls\n" +
                    "3700 bwn.wqq\n" +
                    "240004 jbvhs.chq\n" +
                    "224969 mvftsj\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd tnfrr\n" +
                    "$ ls\n" +
                    "dir hlbrpt\n" +
                    "237956 mgrdrbl.lqg\n" +
                    "dir wqvv\n" +
                    "$ cd hlbrpt\n" +
                    "$ ls\n" +
                    "34424 crstpjjv.rlw\n" +
                    "dir dzs\n" +
                    "275267 mwrvw\n" +
                    "313095 nwqzrc.tnf\n" +
                    "61808 wzhgm.fft\n" +
                    "$ cd dzs\n" +
                    "$ ls\n" +
                    "274302 cbbvq.vvh\n" +
                    "234166 dcgbjvj.cbq\n" +
                    "253156 fpwznlp.zsf\n" +
                    "7239 nzdbr\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "120918 hchsfcp.clm\n" +
                    "105770 nfhrd.tts\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd vjt\n" +
                    "$ ls\n" +
                    "dir crstpjjv\n" +
                    "$ cd crstpjjv\n" +
                    "$ ls\n" +
                    "81225 fpwznlp.zsf\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd wqvv\n" +
                    "$ ls\n" +
                    "72054 bcld.nwh\n" +
                    "284293 cvsmmh\n" +
                    "32684 ndgnz\n" +
                    "130836 rlvcvj\n" +
                    "233437 sbf.vcc\n" +
                    "dir szwnlv\n" +
                    "$ cd szwnlv\n" +
                    "$ ls\n" +
                    "133507 bnmhmpr.vww";

    private static final String[] SAMPLE_INPUT_ARRAY = SAMPLE_INPUT.split("\\R");
    private static final String[] REAL_INPUT_ARRAY = REAL_INPUT.split("\\R");

    public interface FileSystemEntry {
        long getSize();

        String getName();
    }

    public static class Directory implements FileSystemEntry {
        private final List<FileSystemEntry> contents;
        private final String name;
        private final Directory parent;

        public Directory(List<FileSystemEntry> contents, String name, Directory parent) {
            this.contents = contents;
            this.name = name;
            this.parent = parent;
        }

        public Directory(String name, Directory parent) {
            this.contents = new ArrayList<>();
            this.name = name;
            this.parent = parent;
        }

        public void addEntry(FileSystemEntry entry) {
            this.contents.add(entry);
        }

        public long getSize() {
            long size = 0;
            for (FileSystemEntry entry : contents) {
                // Do the necessary recursion (in case the entry is a subdirectory, otherwise it's not a recursion).
                size += entry.getSize();
            }
            return size;
        }

        public String getName() {
            return name;
        }

        public Directory getParent() {
            // Otherwise, doing a `cd ..` on the root directory would cause NullPointerExceptions.
            return Objects.requireNonNullElse(parent, this);
        }

        public FileSystemEntry getEntry(String name) {
            for (FileSystemEntry entry : contents) {
                if (entry.getName().equals(name)) {
                    return entry;
                }
            }
            return null;
        }

        public List<Directory> getAllSubdirectories() {
            List<Directory> findings = new ArrayList<>();
            for (FileSystemEntry entry : contents) {
                if (entry.getClass().equals(Directory.class)) {
                    findings.add((Directory) entry);
                }
            }
            return findings;
        }
    }

    public static class File implements FileSystemEntry {
        private final long size;
        private final String name;

        public File(long size, String name) {
            this.size = size;
            this.name = name;
        }

        public long getSize() {
            return size;
        }

        public String getName() {
            return name;
        }
    }

    private static Directory parseTerminalOutput(String[] terminalOutput) {
        Directory root = new Directory("/", null);
        Directory pwd = root;
        for (String terminalLine : terminalOutput) {
            if (terminalLine.equals("$ cd /")) {
                pwd = root;
            } else if (terminalLine.equals("$ cd ..")) {
                pwd = pwd.getParent();
            } else if (terminalLine.startsWith("$ cd ")) {
                String subdirName = terminalLine.substring("$ cd ".length());
                FileSystemEntry entry = pwd.getEntry(subdirName);
                if (entry == null || !entry.getClass().equals(Directory.class)) {
                    throw new IllegalArgumentException("'" + subdirName + "' is not a valid name for a subdirectory of the current one.");
                }
                pwd = (Directory) entry;
            } else if (!terminalLine.startsWith("$")) {
                // We just assume for now that it's the output of the ls command, maybe this is good enough for now.
                String[] outputParts = terminalLine.split(" +", 2);
                FileSystemEntry entry;
                if (outputParts[0].equals("dir")) {
                    entry = new Directory(outputParts[1], pwd);
                } else {
                    entry = new File(Long.parseLong(outputParts[0]), outputParts[1]);
                }
                pwd.addEntry(entry);
            }
        }
        return root;
    }

    private static List<Directory> findDirectories(Directory directory, Function<Directory, Boolean> inclusionFilter) {
        List<Directory> findings = new ArrayList<>();
        return findDirectories(directory, inclusionFilter, findings);
    }

    private static List<Directory> findDirectories(Directory directory, Function<Directory, Boolean> inclusionFilter, List<Directory> findings) {
        List<Directory> subdirectories = directory.getAllSubdirectories();
        for (Directory subdirectory : subdirectories) {
            if (inclusionFilter.apply(subdirectory)) {
                findings.add(subdirectory);
            }
            // Do the necessary recursion.
            findDirectories(subdirectory, inclusionFilter, findings);
        }
        return findings;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 07: Examples for puzzle 1 ###");
        Directory root = parseTerminalOutput(SAMPLE_INPUT_ARRAY);
        Function<Directory, Boolean> inclusionFilter = directory -> directory.getSize() <= 100000;
        List<Directory> directoriesMatchingCriteria = findDirectories(root, inclusionFilter);
        long sum = 0;
        for (Directory dir : directoriesMatchingCriteria) {
            sum += dir.getSize();
        }
        System.out.println("Sum of sizes: " + sum);
    }

    public static long doPuzzle1() {
        Directory root = parseTerminalOutput(REAL_INPUT_ARRAY);
        Function<Directory, Boolean> inclusionFilter = directory -> directory.getSize() <= 100000;
        List<Directory> directoriesMatchingCriteria = findDirectories(root, inclusionFilter);
        long sum = 0;
        for (Directory dir : directoriesMatchingCriteria) {
            sum += dir.getSize();
        }
        return sum;
    }

    public static long doPuzzle2() {
        Directory root = parseTerminalOutput(REAL_INPUT_ARRAY);
        long totalDiskSize = 70000000;
        long neededForUpdate = 30000000;
        long inUse = root.getSize();
        long free = totalDiskSize - inUse;
        long needsToBeDeleted = neededForUpdate - free;
        Function<Directory, Boolean> inclusionFilter = directory -> directory.getSize() >= needsToBeDeleted;
        List<Directory> directoriesMatchingCriteria = findDirectories(root, inclusionFilter);
        return directoriesMatchingCriteria.stream()
                                          .mapToLong(Directory::getSize)
                                          .min()
                                          .orElseThrow(NoSuchElementException::new);
    }
}
