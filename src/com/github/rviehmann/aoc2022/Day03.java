package com.github.rviehmann.aoc2022;

public class Day03 {

    // From: https://adventofcode.com/2022/day/3/input
    private static final String INPUT =
            "QtGHnGlcwQGvHwMHGnnGfRFCJnRBfVVJRVBfCFBh\n" +
                    "qsDjzNWhmspNNqVrfjVfjJCbBVfB\n" +
                    "DLhPTmNDTzTqzpmzLpswGMHtMgwglcPQMQvSGM\n" +
                    "ctwDjvgtltgglHrSHCsQWBBDfS\n" +
                    "ZJZdVnmhmfCprnBTfS\n" +
                    "dNddhhdPRRdbdNcvwCPCgCctjPlF\n" +
                    "MNNDGMWrQsfhtrTnrm\n" +
                    "jcvRPRPJdvccRNFNCjLdcwJCSHtHPmzhsSzPHmfhnftnTffH\n" +
                    "RddRcjRvZgZNWNgQQb\n" +
                    "fPrvhqhRPSfCCfhrqtbtLcmZmtHCtpCFcc\n" +
                    "WFQJlTdzgGJVQGWQTzdJcHbQZtLtHmbbZMZHsZQm\n" +
                    "JFNlzTBNJGlNPSRhfqRRrN\n" +
                    "tdVVgttdDtJJsNPmzdQmjcPP\n" +
                    "CBTMmmmbTBHqHMwQcZPWWsWnzbbnzW\n" +
                    "CGlLFCTMGvqBGvGvGSRSRfmtRRtgVgrSLS\n" +
                    "LHSGJLWZLqHQHpRHRz\n" +
                    "bpBPVtpCwPgBCtqqvzfzMQvjbbjz\n" +
                    "lhVsTgPlBNThpBNwBtVThPnrGGWJZnLZFnFrGWsFrWZr\n" +
                    "nQbbbjpnsdsGCSlmHtJmvljHMjWt\n" +
                    "cDcgThLVcPTHVlVlHllvWp\n" +
                    "cwpgghTcRRczDhnQGGCSdfszdSbS\n" +
                    "vTMFDNTlDhjTDNMNJDwmWWWfGPfLnnzLGfMmmL\n" +
                    "bVQgbpqqtctzLflltnCz\n" +
                    "VSqlVSHRFvNwJSDj\n" +
                    "RfZfjljffssWLWPTGPjhzT\n" +
                    "FDCvMDMJJpBJpBFJCMBDMtPbWbLVPGztWPVTtPmLWGhQ\n" +
                    "DpgHggpwBJwzJMrFFvrCrHsffnRScNsdclSnnRnSNs\n" +
                    "wTvfHSvSwssPGCLCPhgSCn\n" +
                    "QNpMRqRJQMZlBBbRRRMfbZlBhPChghhlChGCtnthctjPhchh\n" +
                    "rrrNBbMZqRbqDsFHTwfWTFFwrF\n" +
                    "fVgfQhhgfTLHSLNZZHfZ\n" +
                    "CDtsqrqlDtqDddsqjsstHrSlNLWLcSZScFWWcNBFcBcc\n" +
                    "jrCJrHjDCMMDsJjJMstThppgwvGzVGQzhJvvng\n" +
                    "DGlTlSzbSnzmwbmblTBBPgffpNjFHppZBH\n" +
                    "WqQdWqqJdCrtvQtJLQjtdcBNfPsNZfHZZsHNNZHsBBHL\n" +
                    "cCJcjdRvqrQhqvWMzlbDGVGbSMSnbR\n" +
                    "BfQmpfpmsRCRPDSZVSjjSg\n" +
                    "MhNJGMTMHGGTbnGMJrbJHTzzdPgSwSqqPjPdZddSqNSwPZDw\n" +
                    "TWbWbhnHWBFBFBDD\n" +
                    "FrlJbTcBTcrlTlqwZTwWdhWLChLtVhdfLhmwWC\n" +
                    "pppSpzBGMMMjSSMSzMNzHRSDVWGWDWVCDtGdtdhLhftthd\n" +
                    "RNNgpMPRNgzggFJcBPqFZJZrbc\n" +
                    "SdvnnvvntnbDDqtz\n" +
                    "wRpFRRspZhgZgFggFMgtczcGGDzQcGlGDMqcDG\n" +
                    "CLLCFNZhsppRvvttBPCjStdT\n" +
                    "JJDMhSwmMwBnnJPJmQcFtfQtfgQhQQfcgH\n" +
                    "ZvCvTGqGVWTZZWptQggGtHfDgcpz\n" +
                    "dsVCvDTZWWbZqqCZqVsqsqmNlPNnSJmbNNmlmMSnJLBl\n" +
                    "MGnMBMBJFpBcNpdwRWRvRNdblmwW\n" +
                    "LSgtPCChgjCPgSSzCSqWLLbrdWlmwmwmFvml\n" +
                    "QhHPVSHghCtQFgCzgQhVnDGMGffnpcZMcMfJDf\n" +
                    "QwrJqMLRLtBQRtWLJLvQLMrMgNNzPDngNHlHnbbbggHlgZNt\n" +
                    "GdfThGcmShTGSfppmFfTHDNNblSZDbbzlBzZZHHg\n" +
                    "TffmGBBFcCFFmTGFhBwjJQJMQWqWQCrrvQMR\n" +
                    "mRbrjZzRLchHqqdLDllh\n" +
                    "mCGCsJSggCmgSGShlhhDVVVwhqSltV\n" +
                    "PsfsGNGJvGPfspvcmrTRBrjTZzvT\n" +
                    "NRNqFFtqqFrDDQRcJJJNrWDswHwWwbDVTgTwLbVlVD\n" +
                    "ZpdHHGPmgwTwldbw\n" +
                    "HnnnMPMzChPmnpnmfFJCRqcJqJRRrrtSBc\n" +
                    "LnNNLBmTNBNTwNZZjZmRMHJwMHvbWrvPMMWdcP\n" +
                    "GtSFDFpzsGVvMJdJsrMbvr\n" +
                    "SqQQGGtqqSVtDpGtplVDpVJClmnhnTZNjNfmnmBChCZL\n" +
                    "CHltTDsjHNHHdpDRmqdPQpzd\n" +
                    "fMMbbZwLJgMVVFmcRqRpzdqbRm\n" +
                    "MJJWfnVhMzTNGlNTsh\n" +
                    "WtWbtWvrrQGrbcSWHHfSHfgdWJ\n" +
                    "zpnPhLPhPnTMMnzMzpMnDzLPjTVjccSgdmjdvSJdHSgdjScj\n" +
                    "qqvMMCnDCCCzqqCzhPlLsRsGtrsNlRbZtBRtbrrQ\n" +
                    "mPTmlGQnjNTPQQNljnlQnmmCtgRPhghqgqgzqLRPtMqqMzhq\n" +
                    "pDsVSZcdVspbdBpcBHBcHhqLWzMbfhMMrMgMgzLRft\n" +
                    "pFZpJJFSBFRllvlFNC\n" +
                    "pbMVjJbwbbLqlZlstRhjstsN\n" +
                    "FSTnfCBcnBSBvSmmCFCCRQhZBZNsRGlsQQNsqZlG\n" +
                    "dvcdfFcqmfcmdMdJgwJJHMbH\n" +
                    "QSRlWDhQQqDqlTCjTRCSljSmVsPmmGmzsgvppzPVVzdPSP\n" +
                    "HHwtMNMZvfPGbdwdggwG\n" +
                    "ZFcNMFFvHLMjlCWrqhqchh\n" +
                    "jccDSGVVlRLVSVVScWZZwLvbphgwdpLwZv\n" +
                    "BQHBtfQQHBfmMtmFznrHzmDwghWphvZZFpDhZvgvpZhh\n" +
                    "DmQrPQnBtTnzrrfHrtfMrmTCGqPNjSPCRRGjRCRRqCNjVN\n" +
                    "bRDbbtVgPFFbDfgVCtPcGsNNsHccqcGGWCNNzW\n" +
                    "ZwlmJwwRSmlJdQRhqGNTqzGTwzqGhc\n" +
                    "ZQdZjpljldrSMjmjdRLdmdbVvvtDVFFffvBFPVBgMnBg\n" +
                    "rzwGrwPGppqzPMzrdqsssSsvgtQgTTTJsH\n" +
                    "LCNNHLmHWvCJstsJss\n" +
                    "cfVLLNBmZBVMVDHGwrhM\n" +
                    "FCGFrJltMGdFdGtlFtCgFzQfQnZfVVVMDcQDVzVjfD\n" +
                    "PSPSPZPRbPRsbBRbHBTPvfVzQcDQqWWnDjnnHDjzcz\n" +
                    "RmSTPwSTRpvSlZglgZprZtNp\n" +
                    "njtLGLbcNTbbLnLmNTtqnFzVPwJFFzPVFPwMVmfMmV\n" +
                    "srpZlSpsSVqgSPJJ\n" +
                    "HZsHsrZhrBQpqQvpdprnTnnHTbGLbHnjHGbjbT\n" +
                    "vFFnJjvnVSjgCqSCDZ\n" +
                    "lswrrcsQrHTHwdrDdhmqqsRhzmggghgsGC\n" +
                    "QPrWQWcDNDHdddNrQPfDMLMLtvvMbnBpPFvpFFvL\n" +
                    "LgltlRMtcccgMdlzqTVwwrdsqm\n" +
                    "FpvfSDNzSpnvGSnNpmsmVqVVrHwvTqVmmw\n" +
                    "pGjDFFDpffnWQbnzjLJhhLchPPJMPBLt\n" +
                    "lJqBqPvtvVPnttnJjJBjVMzMrFhdQrQFbGbQnrbzGG\n" +
                    "msRgTgmTLLmsTDRfQRllMdGzdRbMHQFF\n" +
                    "msfDWglWTpSLCgTpSCDsfPjvjtwVjJZqqSSvPVZSqB\n" +
                    "VZZWGZGlZZZMZrGMlQBQGWltFzqvzzcVcgzvmgzddccgvqFc\n" +
                    "rLjNrDRLDRLTqdCqFCNzFgqg\n" +
                    "TJSSbHfDDfSnbpHpJnGhWPtrZWnPhBwWPWBt\n" +
                    "ssvsDDjNwhjNhGGgDDRZllltLCLvCWbMffLCfWzb\n" +
                    "qVRmJFSQqqVcqSMWMWLMlllbtbVM\n" +
                    "PqQBPcFJFcmdrJPJrFrQpsNjnTGgwTwGssRNRnRBsG\n" +
                    "sHsJJfSsrpBLHCLJmdqgdZgRNZQlgRNC\n" +
                    "VtnFbhFhwjwVPPjhbccQDQqgRlWDQmdWZZDd\n" +
                    "hFhFvbhGwzvFbtbPnttGbtjsLBfspSrMvJLrpJTrMsLZrH\n" +
                    "nzJDJvvZznnqnZrpTgTpzzTNjlmtsglBjjttQQCgCCtlBj\n" +
                    "SRLRfdVfSVcfHdVVdSLdMHQjPtClsmcwNQltjwjCQQwm\n" +
                    "RShVLWWfWWGvsTZz\n" +
                    "vcdptvndNzrndjctrvcnvtTdbwwRTgwbHDFDTWgqWlWqwqbl\n" +
                    "ffVBmQPhshhfPmSfBPfChmmCWFWbDWFbWwwqRDbsgwWMbWFb\n" +
                    "SJJCBLZBhfvnJnpGtvRc\n" +
                    "HLMMHHpNqWHqFqppPWnshtsLhBnVDtthLLms\n" +
                    "JgmSRgCRGbgwGSbgJJjRRDntztrVhnsDrllllwhhzt\n" +
                    "bRRCCjgRvJQQdcdjjvRcQdgbZPZpqfqpHHMfNFqNTPWqQWmq\n" +
                    "PPwQTlFrLqrLQrPfPJrHtcjtSvdCNNctLCjNdc\n" +
                    "DGZMhmVRMGqVmVDnGMbBZhsjHNvjdstddjdjccSBSjds\n" +
                    "mqzVnWDmmGzRhqRbbWQQFwPQQTlzPFpTlPJJ\n" +
                    "RMmnMrZMlQQpGrQt\n" +
                    "gPPgcDBcjdgjBCBcQtNDNzzthltGphhz\n" +
                    "FSvPSSjFWFScSWdjWvbTwWJQZQVRsMTQVwTmVR\n" +
                    "PwlbMTDQDvMwttThPthhvhvvZzNLzNZjQCNNLWLCRZWQRzNW\n" +
                    "VpJHFpgfqdFqqHFGHVSdGmZZRnSsnLjNZCWmmszLSz\n" +
                    "drrJRGJVrcMBDwhDbcMh\n" +
                    "hghHTgbwHbDqHrgbbbSLzzFNnsVqNVBnLnfs\n" +
                    "jMlcMRRmGcRlZQMMJRJSPPmCFzBBzpfBfLfpFLszLNNFVCfz\n" +
                    "JlmGlZmvRMgvrgbrrSTw\n" +
                    "ZtTTHpprHtQtHtpqPmjVSScjVSjjSNqw\n" +
                    "nRvGWhRbnRGzvWCBGPjNdwcSwcnlJdmmSj\n" +
                    "vCbsmbWfRsRWWzsMWBQFFrDZpgpFHLgQLMgQ\n" +
                    "QlJBfZssjgZsQsDNbScdDZdNcvvF\n" +
                    "zrGrVVpVMWMwMVLMwqVWpRdNFSDcFNDFbSddqHldDdqd\n" +
                    "VLVtrWpMRCRLWGWMRwVGCVPfCPJnBTQsJlmjsfsPBQjC\n" +
                    "GQqpnnFWFcnWpBMhhSZRhQCCCfRQRD\n" +
                    "HsbcczlrjzflffLVfDlt\n" +
                    "jssvwsHPsjzWdMcgBnvqMB\n" +
                    "JMJdMptTbbtpJZhtTMZwJdNWQBPNBlwNBNQWHBCHHPlg\n" +
                    "rVqsvVmjrcCqCPsPNQgnsnQglW\n" +
                    "rVvzfqvLjFVDVcLjCqLmJFGbpJpRJTGMtbRpdGRG\n" +
                    "LrmwdZHlSlcVlSrrDWCzbtpjpgzjtD\n" +
                    "gBGgPTTBqPQTqnnPJhMTTCzzppnCvptpnCtzNpDntz\n" +
                    "qQPTTPshRRQqcgsflHZcsSLV\n" +
                    "smvdTvlPzslmVjMhJlbjrblJ\n" +
                    "cqFffFffcwFfpcDSjcnZwZWrQVDrQBhNbrrNBbWVbNhr\n" +
                    "nSpRZRjpcHqpCpqnqFRGmvPLtLGmCdCsGLLdtC\n" +
                    "PJPLPPHDdPvdnbctfcpt\n" +
                    "NRrGNjNzLjltppvpRSRS\n" +
                    "LzVWLjTWGLQqBgZWJWBBFg\n" +
                    "znzccGnswWrWNCpvvdTCzddC\n" +
                    "RfZlRDDDZflSBMhhhPSMMZpmpHHGNCGTmCJTmvmCvJGR\n" +
                    "GbBMhhbSMhMffDMZPggSGfhVWLwjsLttWtrjWscVLWnQWswn\n" +
                    "jwRzdZZhwhwHSQqpStJHhp\n" +
                    "GVGPBvNPvffpvtvp\n" +
                    "nsGGDVtFNGgGWjWlTwrwTmjwTR\n" +
                    "MQtmmMMmnHGGTmhw\n" +
                    "llqvqvvpqdvvrSfrpSfdfnhFSTMNDHFMNFFSGFwMHT\n" +
                    "JqvvRgpCfrzCpqqzdfRpRllWZZBQJbVVtMttsVWJbjPbcc\n" +
                    "zgqBczqBbBGjdHVgdDFd\n" +
                    "lNVhNNnLtZZZGWjdWrSW\n" +
                    "PCQhNflCtCwtJwhClMmVcRmpbPqTcmBqpm\n" +
                    "wWbqbTwVrQLqlMtrZtZffNfMfP\n" +
                    "vhjqqRpRvZsfssZN\n" +
                    "ccBjJBqGpRhDGbJldHlgLwddLd\n" +
                    "hVScjtgcchVhSgZJSchVwLbNLWnLNwwnwrbWNLgr\n" +
                    "BzMdBqzBQzZQPpfrsrbrNdNLsrRR\n" +
                    "zPMzmmGpmzGlFQPPmlZPzSVTSVJJcSlcDhTlvSCtjJ\n" +
                    "hzdwlqngZQvDfmzF\n" +
                    "vcRrcsVNpVQsFfBfPPQB\n" +
                    "HCHVWVcCpNMHWRjLwhvhggdjvggM\n" +
                    "LBHCTPzHHBcClwppwjjNNl\n" +
                    "JddZMRmmssVmvDvJddSJWVWBwpQvwfpwvwwfrrFNQfwjpl\n" +
                    "BsVMVZDVZssZhMhJqLzhzzHzPTPnLGqb\n" +
                    "bcnNQnBTvvvWTQTJhdHJzhSjpJJjStjd\n" +
                    "mMwqwVMMRwhBtjdSVBVl\n" +
                    "GfsrwCRmZBcZTvBf\n" +
                    "bBjQmmmqWdLqvLbWLtNJccNZZgCBCZgCTg\n" +
                    "phfpfzpHPDwfMwvVzlhlJJlggcZgZcRFFlCgZg\n" +
                    "zfhpMMDpVVzzhHhzzMMwfnMfSmQmbQvvWqWjmLSqdQmrqjjn\n" +
                    "ZzdcbGTwLZwPPwpMPWpjWPpWChBC\n" +
                    "nmznNRFHQrqQvqqmmMfWWWpMFjspWFMWjB\n" +
                    "zqRDSmDrmvnvntGccGdcTVtLTlVG\n" +
                    "nfnVscTcDzsZqmRtjtsqjJ\n" +
                    "wnnhWPWLBNwPLHdQdWdHPHLdJjqRRqrvjrmqFtvRqtQRqFjm\n" +
                    "bWhHdBBLdLnShWHcTVzCfCpVDTblpT\n" +
                    "WSjVFSVTSWvgvFwpgTTFsfdVclhNCfsHnHlHsCfV\n" +
                    "zRqLqZqZZQcdNltfHzsd\n" +
                    "ZqBqGLLRcjWWBvTT\n" +
                    "wwBqgZfbgbZsvWmfJrtFrdDttqdFdLct\n" +
                    "SNSSVTnnThnCSPPnzVztQJJQdLPtFcGLdFGDsD\n" +
                    "lSlszRVsRjmmfjfRRf\n" +
                    "NGzGWSLzMdQtcctbgWjt\n" +
                    "vqTnfVVHBffnCmHgMtrrrHrgtcrt\n" +
                    "vqCDVlFnnCnnBSzlspSzLSNMLL\n" +
                    "rwVlfVMRSTqTgRhT\n" +
                    "pppjLtpZjHFHNhsctSqDDVtnns\n" +
                    "HNjJLFBFHZZzWbVBZGfwdGrMfCCfGvmzlM\n" +
                    "zFzztqWnJttqqzJtdqbtqbPVMGcGZwgPGVwGQjwCCggMZQ\n" +
                    "hssLlrsSrBmBrZGSfgwgSwQCcj\n" +
                    "BmBBDBHLrlNhLNHrpsDvLJNtddczdRzzcTqJzzdWnc\n" +
                    "pLWcpGSscLQPDzLBgdbmdZHBHmZB\n" +
                    "lntfCfVMvtttqVqnZHHbbBZmwwwCdHhc\n" +
                    "qqRJFfRFRNlTzSSWWzSJpc\n" +
                    "DrphDfFrdGGjTTgTgCSV\n" +
                    "vbsMZPnPvqHMQvtZZPWBBVVBJjWSVTPjJC\n" +
                    "nMbnsRSQvvLRMsssQhdFrcwczLlDrrDzhz\n" +
                    "LLlDQlcLDqLSlCDRlRDCqTTzzgNzShSBdZGBFBBgNG\n" +
                    "nHnMwPPbrWwpmztdMtzFZZFtTN\n" +
                    "PJfbZmHrpJfZDRLlDlLcVsVJ\n" +
                    "wZwQsrrzQFQSrRSFbzDglcdCcdljjdlljcvslLhv\n" +
                    "VPqGJtqqfnJcCLFllFMGcd\n" +
                    "fNHPJWpmHfffqJHmwzgbrBDZrDbFRQ\n" +
                    "HggtSJtzrgltshlTsrzbzsgbFddfffDZwZRZZZGfGDRTNNZZ\n" +
                    "LnjWccmQBPWqmWPqMfFNdGBRCDFFSfDfRd\n" +
                    "WpMjWpMcWSjqqPjQLmvmjMgllsbgbtvsrrtvJgJHhrHl\n" +
                    "PMcMcBpztMztzPPhbSgGSvgfHD\n" +
                    "nsmsVQVZVQmTRmddrLgfFhfLgLDzgvffDhbF\n" +
                    "dwsjrQQrVwTmZRTzsTdBcjqlqWpcCCppcllltC\n" +
                    "DbVjbjwNMDTCVbhbTNDrVTTmzFGLFLHcLHzmcWFczLHwmH\n" +
                    "PPWsfBZngtZqZZnPgZnPqvmFmczgRHGmGSRRSSLGFF\n" +
                    "sntBlWPdnspnnPQZtnNNNMjhMNrVMjMThjpr\n" +
                    "rCrCWVvvWDnBWvDnWDMCMZTpThhGGhGRGqGqZmhZqV\n" +
                    "NNjQMzNzQjjwNmJNmZddpdTG\n" +
                    "QwtwLPcjPtQlHSSslzwHSlPQDfDWsggsvvBvDvnvgngCnvFM\n" +
                    "ZDCbvbDvvHbCGTWpZTtWWQnn\n" +
                    "hLqzLVhVwNwVfMnJrfqMzWrQddgmQQGdgpgQGmgjTj\n" +
                    "wlVNwJJfVRLPRnHRbFHRsP\n" +
                    "lLmvLGRCrmDwVPVsjmtBBQmt\n" +
                    "NSHWNbzSHWpSpfChqqJPnzBJsBPPJnQJ\n" +
                    "pWcHbNfWpgZcWbhbZSgchWpRdCZMMdGDFFrLwlwMRRZFdd\n" +
                    "PnNrFVZQHNDrmNQPflbhbfqbvhwbVfvq\n" +
                    "TCdTtpCtCWTBMtjTJMWtBMCLSfvbqqzqbljflwDLvLjLLf\n" +
                    "MCCGcpCJRWTTMGmPnDFNQQnDns\n" +
                    "FhSmttZmgLjJVMlVGllGjcrj\n" +
                    "NRNvNWNCdnNrBvWvwRdWvvvRCGcbVVQbMQGcGcVcMclzVVlG\n" +
                    "qwBsBWwnvdtHJJSrtHsr\n" +
                    "wnwPrnBBPgqRPjhgCRGCLvvbRZcJvCbb\n" +
                    "dWVdVdlMHtsTslMtzztFGGczvvGJzL\n" +
                    "fMflHpddJfNNVfNWWmDqQpwBgwnjPBQDjqBh\n" +
                    "WttGctsNCNrfwTMlBtlr\n" +
                    "HJLzLJJVnFjHbnbVjDHDdFqwwffTTfTGwrqwBBTT\n" +
                    "jgVgJSbnHjNWsWCSWWGG\n" +
                    "ssLfHLvrrfMMlBMRMfpd\n" +
                    "qbbhmGCCVDQhQQVzDDbqmhMBtDZdBZHlddpltpBpdlJd\n" +
                    "QhQbzqCGzVzSbqzLHjLgrcjvPSnnSL\n" +
                    "NGGZGsGFCGvCcnCcgnZWrPHjsJjHjTDjJHfrjHDP\n" +
                    "RzzQmwwmdRRwMqRQmZwtfrDPfTfrbjHqrDPTHr\n" +
                    "MBLphmLpSzdRlnWZCNcGLgLG\n" +
                    "LwPPHZLLWRMWMsHqTCjmTTjSTn\n" +
                    "lFpNNpzplbfphzNcQzVcpvzsnqTsggmnjfgqCDsnCDqsgS\n" +
                    "vFJJhQVhbcVddLtmWZJdmm\n" +
                    "GwMNMGBMRMGjdSGsDntglDrDGQ\n" +
                    "WTmPPzfmlQrsnQmg\n" +
                    "ZPTJVvllhBwvLvwRwj\n" +
                    "nlSvqqFqzfnbnvFwwsGHTBHgPfWsTrRPgG\n" +
                    "dMdjphVhhgdLVJhNRBGPRWWBBGPTTGHV\n" +
                    "thJgLpghLNLhNQMCtdMhSmFlQnzzwnbzlbqqSQFb\n" +
                    "ZJJLBWhJBNwJwLvgRgGRgbZqbHTbGT\n" +
                    "cCrfFFnzjmdrdrmmzsgSPSHSbHWPRgrgGR\n" +
                    "dmCFntnFctjfdVtttjtDVncfBDlNJJppwQMwNBNvvplppWNh\n" +
                    "GGNNNbNbqmsmTVbCDQQhDJQT\n" +
                    "rgPvdgMdPdBddvMcrMnvgcSwhClTTJCwClDTvLJJthTlth\n" +
                    "zBdWpnDBDmfFGRffzH\n" +
                    "WNNNWcDcdVHNQTNT\n" +
                    "lLvLctvrvvvPCpHLQQTZVQLTTHBV\n" +
                    "zltgpbPCrrmtlmrlhhbqcWMqDDMDFMfbGScc\n" +
                    "dzSHRCGZGdRQWQWtllWB\n" +
                    "mNmbLbbmwjsmsNjDmcPvpVHBVPQvWDWBBpvB\n" +
                    "jnmNNrLcnnjjHhSCdGMSfrfd\n" +
                    "mgGvfvJSmSSrbvScmGccTPCqNnRjlpNNfNjqwqlpNpfw\n" +
                    "zZLLQFFFtsFwWjjRqbbCsW\n" +
                    "hBBLBdFHddVrGbVc\n" +
                    "bJGchsCcHwbHfgvg\n" +
                    "qdlSSNqLPqlPQZDffgfZRZMghvZr\n" +
                    "PLtqtFTPNLlWlqtQqqQzBBmcnschGtjtnChmzC\n" +
                    "llNLDLzjDtCTVtClCDMHSlmwdmmdSmddwZSS\n" +
                    "gPJszvsBPbwSHZRQSJQJ\n" +
                    "bgFssFPcWhDjCzLcnNDD\n" +
                    "ZTGMwdhMwMJphZMJlNdVBCnncNPLBCCRPnCncn\n" +
                    "fWsqmfSsqvfFFFvmWjfSSsPLCwVPRnPRCmRnRRCVzRCV\n" +
                    "FQfQfvStwjtqsjvvQWFfWqWtlMDdGTJJGdMdhJtDMbllDZ\n" +
                    "DzZLpBDptpVPrrrFPqRmBj\n" +
                    "WsfCTTgMllCWJMlhslNsCFJrGqvmRPqmqZjGFjvmvm\n" +
                    "HfghcNghHhgNNfbDDZdcdpndDzpt\n" +
                    "RRBRdJfccVRdDJqdDrvwShwvztWJlWwSJn\n" +
                    "mPtCHPPmHbmHNZjjjQHbMSgrSWhWzWnnQwvzzrlrll\n" +
                    "tpFmjpmjFMCmppmVTVLFVfBqTqVDLd\n" +
                    "jtljZcTcVVtdggVmdczzzDPzvCsvRbDsDmvm\n" +
                    "MHHnnJMFMNpwSNprpMJrfsbPRRbWDGLbHLDRvzzssR\n" +
                    "rJFNvQfBSwQBwNwldZTqTtctQchthc\n" +
                    "WsWTWsfjgTJGSnTz\n" +
                    "RHNmvRLCpJclcNcvHpNcPBzgQgMnnGFnLFgPgnPF\n" +
                    "mHlcdbbccZcCCCNZZNcCWtthWbWqwwJsbDfthfwW\n" +
                    "vFFzppnHFrjmpljQhg\n" +
                    "JPGfPWVdwfPgQzVQlTrQrZ\n" +
                    "PwRJfGDBfCHMzRHNHMNR\n";

    private static final String[] INPUT_AS_LINE_ARRAY = INPUT.split("\\R");

    private static String[] getCompartments(String rucksack) {
        // even length
        if (rucksack.length() % 2 == 0) {
            int compartmentLength = rucksack.length() / 2;
            String firstCompartment = rucksack.substring(0, compartmentLength);
            String secondCompartment = rucksack.substring(compartmentLength);
            return new String[]{
                    firstCompartment, secondCompartment
            };
        }

        // odd length
        throw new IllegalArgumentException("Invalid nr. of rucksack items: " + rucksack.length());
    }

    private static char getDuplicatedItem(String[] compartments) {
        for (char itemInFirst : compartments[0].toCharArray()) {
            for (char itemInSecond : compartments[1].toCharArray()) {
                if (itemInFirst == itemInSecond) {
                    return itemInFirst;
                }
            }
        }
        throw new IllegalArgumentException("Could not find duplicated item.");
    }

    private static int getPriority(char item) {
        if (item >= 'a' && item <= 'z') {
            return item - 'a' + 1;
        }
        if (item >= 'A' && item <= 'Z') {
            return item - 'A' + 27;
        }
        throw new IllegalArgumentException("Invalid item: " + item);
    }

    public static long doPuzzle1() {
        long sum = 0;
        for (String rucksack : INPUT_AS_LINE_ARRAY) {
            sum += getPriority(getDuplicatedItem(getCompartments(rucksack)));
        }
        return sum;
    }
}
