const SAMPLE_FUEL_AS_TEXT = `1=-0-2
12111
2=0=
21
2=01
111
20012
112
1=-1=
1-12
12
1=
122`;

const REAL_FUEL_AS_TEXT = `1-201-=2
210--1211001
120
1==1-=1=-1=-=1-==-
1--
1-1-2221=-
1021=
2-=-21==02
1-
1-222122-0==020
1-20122---12=2112-2=
221
12=220-2-=1=
21
1-20
1-0=-0-2---
10-=221=-0102
212-020-1=00
201=020=-1=
2=0=01=2==
2=1=0=11-2===20--
2-0-221
1--10=--1-0-2
100=-11222=1
11-=1
11=--=1012--02-
121
1=11=1
1==2---002--11=-1-
11=0==--0
2==0110=2=1==
221=-=-=-02
12-10
2=
2-0-=2
1=0-0=2
10==-2210=-=202
2=-21
2=-=1220
201=2==1
1==1=00-1-=
1200-00-00-21-=-=0
1=0=-22-200=-2-=--
1==-=--12-1112-22
1=0=-00====102
20222010-1
1---
20--==2-201-
1=1
120=02=-=1-0=1
1=-210-1=1
1110--2
1102-1=2----201=-
1=01=2-01
12=2--0-1001
12=1
102
2=1=-11
12=001022
2=1=1=-01==
101---=1--
10=00001-0122=0
12=-=--11=112=0202
1-122====012100
212=020=1022-0
10=22=2
1===212-02=1
2=-2===-01010-121
122=10
2-0-1=-0--1001=-2
1121
1-21--100=1=2=1=2-
1==0
1=-0=0-2=0-=1===0
2-02-==
20-=-2022===-222
1=1100-1
12=-1=-02-2-20-=1-=
10=
1=1-==2
101-=01221-==-2-=-
12-=111=2=11-=1212
12==202=121
22022=000-121001-
12=2
1==0==1--1
222
10
12-0122=01-
10-0=01120
210-0===-011=-=12=
1=-1=0=2-102--0
10111000--0-===0
1=0-=-2-
2-==22100
1-2=-2-1-020=-2-
1=001-1=02=--2
1==0-211-21=-
1-02=2=--221=-012==
1-011-221-0=
10-20=
1=-0--=
10-=022101=1--22
2-0-1202==-==-
1=0-
11=
2200
2-22-212==-0==2=
1012
1=2=12===2-=
1==211-02-
1-001==2101220
2-001
10-=2-=
102110==21=12--02
1-=1122212002=010
1-10-201=221=021-
2-00=2221
2--1
11=12=0
2-21=-
20--2-1=22220-0
2=22=1-02=21
10==10112=1
1-2=0=0=02==
2201=2
10-=00121
10-012-12`;

function sumSnafuFuel(input: string): number {
    return input.split(/\r?\n/)
        .map(snafuToDecimal)
        .reduce((a, b) => a + b, 0);
}

function snafuToDecimal(input: string): number {
    let digitValue = 1;
    let index = input.length - 1;
    let output = 0;
    while (index >= 0) {
        switch (input.charCodeAt(index)) {
            case 50: // 2
                output += 2 * digitValue; break;
            case 49: // 1
                output += digitValue; break;
            case 48: // 0
                break;
            case 45: // -
                output -= digitValue; break;
            case 61: // =
                output -= 2 * digitValue; break;
        }
        digitValue *= 5;
        index--; // The hightest index has the lowest value and vice versa.
    }
    return output;
}

function decimalToSnafu(input: number): string {
    let baseFive = input.toString(5);
    let index = baseFive.length - 1;
    let output = "";
    let overflow = 0;
    while (index >= 0 || overflow > 0) {
        let currentValue = (index >= 0) ? overflow + (baseFive.charCodeAt(index) - "0".charCodeAt(0)) : overflow;
        overflow = (currentValue >= 3) ? 1 : 0;
        switch (currentValue % 5) {
            case 0:
                output = "0" + output; break;
            case 1:
                output = "1" + output; break;
            case 2:
                output = "2" + output; break;
            case 3:
                output = "=" + output; break;
            case 4:
                output = "-" + output; break;
        }
        index--; // The hightest index has the lowest value and vice versa.
    }
    return output;
}

console.log("Year 2022, Day 25, Puzzle 1");
console.log("Result (example, decimal): " + sumSnafuFuel(SAMPLE_FUEL_AS_TEXT));
console.log("Result (example, snafu): " + decimalToSnafu(sumSnafuFuel(SAMPLE_FUEL_AS_TEXT)));
console.log("Result (real, decimal): " + sumSnafuFuel(REAL_FUEL_AS_TEXT));
console.log("Result (real, snafu): " + decimalToSnafu(sumSnafuFuel(REAL_FUEL_AS_TEXT)));
