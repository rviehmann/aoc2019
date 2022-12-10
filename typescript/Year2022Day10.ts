const SAMPLE_COMMANDS_AS_TEXT = `addx 15
addx -11
addx 6
addx -3
addx 5
addx -1
addx -8
addx 13
addx 4
noop
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx -35
addx 1
addx 24
addx -19
addx 1
addx 16
addx -11
noop
noop
addx 21
addx -15
noop
noop
addx -3
addx 9
addx 1
addx -3
addx 8
addx 1
addx 5
noop
noop
noop
noop
noop
addx -36
noop
addx 1
addx 7
noop
noop
noop
addx 2
addx 6
noop
noop
noop
noop
noop
addx 1
noop
noop
addx 7
addx 1
noop
addx -13
addx 13
addx 7
noop
addx 1
addx -33
noop
noop
noop
addx 2
noop
noop
noop
addx 8
noop
addx -1
addx 2
addx 1
noop
addx 17
addx -9
addx 1
addx 1
addx -3
addx 11
noop
noop
addx 1
noop
addx 1
noop
noop
addx -13
addx -19
addx 1
addx 3
addx 26
addx -30
addx 12
addx -1
addx 3
addx 1
noop
noop
noop
addx -9
addx 18
addx 1
addx 2
noop
noop
addx 9
noop
noop
noop
addx -1
addx 2
addx -37
addx 1
addx 3
noop
addx 15
addx -21
addx 22
addx -6
addx 1
noop
addx 2
addx 1
noop
addx -10
noop
noop
addx 20
addx 1
addx 2
addx 2
addx -6
addx -11
noop
noop
noop`;

const REAL_COMMANDS_AS_TEXT = `addx 1
noop
addx 5
addx -1
noop
addx 5
noop
addx -2
addx 8
addx -1
addx 7
noop
addx -1
addx 4
noop
addx 1
noop
noop
addx 6
addx -1
addx 3
addx 2
addx -5
addx -27
addx -3
addx 2
noop
addx 3
addx 2
addx 5
addx 2
addx 3
noop
addx 5
noop
noop
addx -2
addx 2
noop
addx -13
addx 23
noop
noop
addx -9
addx -18
addx 30
noop
addx -34
addx 3
addx -2
noop
addx 1
addx 6
noop
addx 28
addx -25
addx 5
addx 5
addx -11
addx 9
addx 4
noop
addx -26
addx 34
noop
addx -2
noop
noop
addx 4
addx -34
noop
addx 11
addx -7
addx 7
addx -9
addx 5
addx 5
addx 2
addx 1
noop
noop
noop
addx 22
addx -17
addx 2
noop
addx -19
addx 29
noop
addx -2
noop
addx 3
noop
noop
addx -36
addx 7
noop
noop
addx 3
addx -2
addx 2
addx 5
addx 2
addx 3
noop
addx 2
addx 11
addx -10
addx 2
addx 7
noop
addx -2
addx 5
addx 2
addx -36
addx 1
addx -1
addx 3
addx 4
addx -1
addx 5
noop
noop
noop
noop
noop
addx 3
addx 5
addx 15
addx -13
addx 6
addx -3
addx -1
addx 9
addx -1
addx 5
noop
addx 1
noop
noop
noop
noop`;

interface Command {
    command: string;
    argument: number;
}

function parseIntoCommands(commandsAsString: string): Command[] {
    var commands = commandsAsString.split(/\r?\n/);
    var output: Command[] = [];
    for (var i: number = 0; i < commands.length; i++) {
        output[i] = parseIntoCommand(commands[i]);
    }
    return output;
}

function parseIntoCommand(commandAsString: string): Command {
    var input = commandAsString.split(/ /);
    if (input.length == 1) {
        return { command: input[0], argument: Number.NaN };
    }
    return { command: input[0], argument: Number(input[1]) };
}

function executeCommands(commands: Command[], numCycles: number): number {
    var registerX: number = 1;
    var cycle: number = 1;
    // Programm counter, a pointer to the currently executed command
    var pc: number = 0;
    var cycleForCurrentCommand: number = 1;
    var sumStrengths: number = 0;
    const importantCycles: number[] = [20, 60, 100, 140, 180, 220];
    do {
        var command: Command = commands[pc];
        switch (command.command.toLowerCase()) {
            case "addx":
                if (cycleForCurrentCommand == 2) {
                    registerX += command.argument;
                    pc++;
                    cycleForCurrentCommand = 1;
                } else {
                    cycleForCurrentCommand++;
                }
                break;
            case "noop":
                pc++;
                break;
            default:
                console.log("Invalid command: " + command.command);
        }
        if (importantCycles.includes(cycle)) {
            sumStrengths += (registerX * cycle);
        }
        cycle++;
    } while (cycle <= numCycles);
    return sumStrengths;
}

console.log("Year 2022, Day 10, Puzzle 1");

// Example
var commands = parseIntoCommands(SAMPLE_COMMANDS_AS_TEXT);
var result = executeCommands(commands, 220);
console.log("Result (example): " + result);

// Real
var commands = parseIntoCommands(REAL_COMMANDS_AS_TEXT);
var result = executeCommands(commands, 220);
console.log("Result (real): " + result);
