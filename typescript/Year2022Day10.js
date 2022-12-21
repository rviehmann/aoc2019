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
function parseIntoCommands(commandsAsString) {
    return commandsAsString.split(/\r?\n/).map(parseIntoCommand);
}
function parseIntoCommand(commandAsString) {
    const input = commandAsString.split(/ /);
    if (input.length == 1) {
        return { command: input[0], argument: Number.NaN };
    }
    return { command: input[0], argument: Number(input[1]) };
}
function executeCommands(commands) {
    let registerX = 1;
    let cycle = 1;
    // Programm counter, a pointer to the currently executed command
    let pc = 0;
    let allCommandsExecuted = false;
    let cycleForCurrentCommand = 1;
    let sumStrengths = 0;
    const importantCycles = [20, 60, 100, 140, 180, 220];
    let crtX = 0;
    let crtY = 0;
    let crtScreen = "";
    do {
        if (importantCycles.includes(cycle)) {
            sumStrengths += (registerX * cycle);
        }
        if (Math.abs(registerX - crtX) <= 1) {
            crtScreen += "█"; // White, should be "#", but this is better to read.
        }
        else {
            crtScreen += "."; // Black.
        }
        crtX++;
        if (crtX == 40) {
            crtX = 0;
            crtY++;
            crtScreen += "\n";
        }
        const command = commands[pc];
        let holdPc = false;
        switch (command.command.toLowerCase()) {
            case "addx":
                if (cycleForCurrentCommand == 2) {
                    registerX += command.argument;
                    cycleForCurrentCommand = 1;
                }
                else {
                    // During the first cycle of the two-cycle add command, we must not modify the PC or the register.
                    cycleForCurrentCommand++; // Prepare for the next cycle.
                    holdPc = true;
                }
                break;
            case "noop":
                break;
            default:
                console.log("Invalid command: " + command.command);
        }
        if (!holdPc) {
            pc++; // Prepare for the next cycle.
            if (pc >= commands.length) {
                allCommandsExecuted = true; // There is no next cycle.
            }
        }
        cycle++;
    } while (!allCommandsExecuted);
    console.log(crtScreen);
    return sumStrengths;
}
console.log("Year 2022, Day 10, Puzzle 1 and 2");
// Example
let commands = parseIntoCommands(SAMPLE_COMMANDS_AS_TEXT);
let result = executeCommands(commands);
console.log("Result (example): " + result);
// Real
commands = parseIntoCommands(REAL_COMMANDS_AS_TEXT);
result = executeCommands(commands);
console.log("Result (real): " + result);
