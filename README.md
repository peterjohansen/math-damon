# Math Damon
Immutable Java math library

## Examples

### Ranges

```java
IntegerRange range1 = new IntegerRange(5, 10);
System.out.println("Original: " + range1);
IntegerRange range2 = range1.shift(4);
System.out.println(" Shifted: " + range2);
System.out.println("Overlaps: " + range1.overlaps(range2));
```
...would output...

    Original: IntegerRange[5, 10]
     Shifted: IntegerRange[9, 14]
    Overlaps: true

### Rational Numbers

```java
Rational r1 = new Rational(2, 1);
Rational r2 = new Rational(1, 2);
System.out.println("     r1: " + r1);
System.out.println("     r2: " + r2);
System.out.println("r1 * r2: " + r1.multiply(r2));
System.out.println("r1 - r2: " + r1.subtract(r2));
```
...would output...

         r1: Rational[2/1]
         r2: Rational[1/2]
    r1 - r2: Rational[3/2]
    r1 * r2: Rational[1/1]
