# Math Damon

## Contributing

1. Fork this repository and clone it
2. Make desired changes
3. Run `gradle build` inside the repository
4. If the build succeeded, create a pull request

Please use the formatting style of the existing code. If you're unsure, create the pull request anyway and I'll review it.

## Examples

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
