"use strict";
var direction;
(function (direction) {
    direction[direction["UP"] = 1] = "UP";
    direction[direction["DOWN"] = 2] = "DOWN";
    direction[direction["LEFT"] = 3] = "LEFT";
    direction[direction["RIGHT"] = 4] = "RIGHT";
})(direction || (direction = {}));
console.log(direction.UP);
console.log(direction.DOWN);
console.log(direction.RIGHT);
console.log(direction.LEFT);
//# sourceMappingURL=5_enums.js.map