# Math Damon

### Ranges

```java
IntegerRange range1 = new IntegerRange(5, 10);
System.out.println("Original: " + range1);
IntegerRange range2 = range1.shift(4);
System.out.println("Shifted:  " + range2);
System.out.println("Overlaps: " + range1.overlaps(range2));
```
...would output...

    Original: IntegerRange[5, 10]
    Shifted:  IntegerRange[9, 14]
    Overlaps: true
