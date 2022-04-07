class Point {
    x:number;
    y:number;

    constructor(x:number, y:number) {
        this.x = x;
        this.y = y;
    }

    move(x:number, y:number):void {
        this.x += x;
        this.y += y;
    }
}

class Rectangle {
    a:Point;
    b:Point;
    c:Point;
    d:Point;

    constructor(a:Point, b:Point, c:Point, d:Point) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    move(x:number, y:number):void {
        this.a.move(x, y);
        this.b.move(x, y);
        this.c.move(x, y);
        this.d.move(x, y);
    }

    getArea():number {
        let sideY = this.getDistanceBetweenPoint(this.a, this.b)
        let sideX = this.getDistanceBetweenPoint(this.b, this.c)
        return sideX * sideY;
    }

    getDistanceBetweenPoint(a:Point, b:Point):number {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}

export default {
    Point,
    Rectangle,
}