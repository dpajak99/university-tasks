"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class Point {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }
    move(x, y) {
        this.x += x;
        this.y += y;
    }
}
class Rectangle {
    constructor(a, b, c, d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    move(x, y) {
        this.a.move(x, y);
        this.b.move(x, y);
        this.c.move(x, y);
        this.d.move(x, y);
    }
    getArea() {
        let sideY = this.getDistanceBetweenPoint(this.a, this.b);
        let sideX = this.getDistanceBetweenPoint(this.b, this.c);
        return sideX * sideY;
    }
    getDistanceBetweenPoint(a, b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}
exports.default = {
    Point,
    Rectangle,
};
